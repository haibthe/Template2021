package com.hb.base.utils.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.hb.base.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

public class ImageLoader {

  public static final String TAG = ImageLoader.class.getName();

  private static final int REQ_SIZE_DEFAULT = -1;

  private MemoryCache mMemoryCache = new MemoryCache();
  private FileCache mFileCache;

//	private Map<ImageView, String> mImageViews =
//			Collections.synchronizedMap(new HashMap<ImageView, String>());

  private int mImageWaiting = R.drawable.no_image;
  private boolean mNoWaitingImage = false;
  private int mReqWidth;
  private int mReqHeight;
  private float mRotation;
  private Map<String, String> mHeaders;
  private String mMethodType = "GET";
  private boolean mCached = true;

  public ImageLoader(Context context, String folderCache) {
    mFileCache = new FileCache(context, folderCache);
    mReqWidth = REQ_SIZE_DEFAULT;
    mReqHeight = REQ_SIZE_DEFAULT;
  }

  public void setCacheMem(boolean enabled) {
    mCached = enabled;
  }

  public void setImageWaiting(int imageWaiting) {
    mImageWaiting = imageWaiting;
  }

  public void clearCache() {
    mMemoryCache.clear();
    mFileCache.clear();
//		mImageViews.clear();
    System.gc();
  }

  public void setReqSize(int reqWidth, int reqHeight) {
    mReqWidth = reqWidth;
    mReqHeight = reqHeight;
  }

  public void setRotation(float rotation) {
    mRotation = rotation;
  }

  public void setNoWaitingImage(boolean value) {
    mNoWaitingImage = value;
  }

  public void setHeaders(Map<String, String> headers) {
    mHeaders = headers;
  }

  public void setMethod(String type) {

  }

  public void displayImage(String url, ImageView imageView, boolean isFile) {
//		mImageViews.put(imageView, url);
    String key = url + "_" + mReqWidth + "_" + mReqHeight;
    Bitmap bmp = mCached ? mMemoryCache.get(key) : null;
    if (bmp != null) {
      imageView.setImageBitmap(bmp);
    } else {
      if (mNoWaitingImage) {
        mNoWaitingImage = false;
      } else {
        imageView.setImageResource(mImageWaiting);
      }
      imageView.setRotation(0);
      fetchPhoto(url, imageView, isFile, mRotation);
    }
  }

  private void fetchPhoto(String url, ImageView imageView, boolean isFile, float rotation) {
    new FetchTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, imageView, url, isFile, rotation);
  }

  public String getFileName(String urlString) {
    File file = null;
    file = mFileCache.getFile(urlString);
    return file.getAbsolutePath();
  }

  public File getFile(String urlString) {
    File file = mFileCache.getFile(urlString);
    return file;
  }

  public synchronized Bitmap getBitmap(String urlString, boolean isFile) {
    File file = null;

    if (isFile) {
      file = new File(urlString);
    } else {
      if (!mCached) {
        file = mFileCache.getFileNoCache(urlString);
      } else {
        file = mFileCache.getFile(urlString);
      }
    }

    Bitmap bmp = mCached ? decodeFile(file) : null;
    if (bmp != null)
      return bmp;

    if (isFile)
      return decodeFile(file);

    try {
      URL url = new URL(urlString);
      HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
      urlConnection.setConnectTimeout(3000);
      urlConnection.setReadTimeout(3000);

      urlConnection.setInstanceFollowRedirects(true);
      if (mHeaders != null) {
        urlConnection.setRequestMethod(mMethodType);
        Set<Map.Entry<String, String>> entries = mHeaders.entrySet();
        for (Map.Entry<String, String> e : entries) {
          urlConnection.setRequestProperty(e.getKey(), e.getValue());
        }
        mHeaders = null;
      }
      InputStream is = urlConnection.getInputStream();
      OutputStream os = new FileOutputStream(file);
      Utils.copyStream(is, os);
      os.close();
      bmp = decodeFile(file);

      return bmp;
    } catch (Exception e) {
      Log.e(TAG + " - getBitmap", e.toString());
      return null;
    }
  }

  public Bitmap decodeFile(File file) {
    try {
      BitmapFactory.Options options = new BitmapFactory.Options();
      options.inJustDecodeBounds = true;
      BitmapFactory.decodeStream(new FileInputStream(file), null, options);

      int w_temp, h_temp;
      w_temp = options.outWidth;
      h_temp = options.outHeight;
      int scale = 1;
      while (true && mReqWidth > 0 && mReqHeight > 0) {
        if (w_temp / 2 < mReqWidth || h_temp / 2 < mReqHeight)
          break;
        w_temp /= 2;
        h_temp /= 2;
        scale *= 2;
      }

      BitmapFactory.Options options2 = new BitmapFactory.Options();
      options2.inSampleSize = scale;
      Bitmap bmp = BitmapFactory.decodeStream(new FileInputStream(file), null, options2);


      Matrix matrix = new Matrix();
      ExifInterface exif = new ExifInterface(file.getAbsolutePath());
      int orientation =
        exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

      switch (orientation) {
        case ExifInterface.ORIENTATION_ROTATE_90: {
          matrix.setRotate(90);
          Bitmap newBmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, false);
          bmp.recycle();
          bmp = newBmp;
          break;
        }
        case ExifInterface.ORIENTATION_ROTATE_180: {
          matrix.setRotate(180);
          Bitmap newBmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, false);
          bmp.recycle();
          bmp = newBmp;
          break;
        }
        case ExifInterface.ORIENTATION_ROTATE_270: {
          matrix.setRotate(270);
          Bitmap newBmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, false);
          bmp.recycle();
          bmp = newBmp;
          break;
        }
        default:
          break;
      }

      if (mRotation != 0) {
        matrix.setRotate(mRotation);
        Bitmap newBmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, false);
        bmp.recycle();
        mRotation = 0;
        bmp = newBmp;
      }

      return bmp;
    } catch (Exception e) {
      Log.e(TAG, e.toString());
      return null;
    }
  }

//	boolean imageViewReused(ImageView imgView, String urlString) {
//		String tag = mImageViews.get(imgView);
//		if (tag == null || !tag.equals(urlString))
//			return true;
//		return false;
//	}

  public void addImage2Cache(String urlString, boolean isFile) {
    Bitmap bmp = getBitmap(urlString, isFile);
    String key = urlString + "_" + mReqWidth + "_" + mReqHeight;
    if (bmp != null)
      mMemoryCache.put(key, bmp);


  }

  public Bitmap getImage(String urlString, int w, int h) {
    String key = urlString + "_" + w + "_" + h;
    return mMemoryCache.get(key);
  }

  class FetchTask extends AsyncTask<Object, Void, Object[]> {

    @Override
    protected Object[] doInBackground(Object... params) {
      // TODO Auto-generated method stub
      ImageView imgView = (ImageView) params[0];
      String urlString = (String) params[1];
      boolean isFile = (Boolean) params[2];
      float rotation = (Float) params[3];
//			if (imageViewReused(imgView, urlString))
//				return null;


      Bitmap bmp = getBitmap(urlString, isFile);
      String key = urlString + "_" + mReqWidth + "_" + mReqHeight;
      if (bmp != null && mCached) {
        mMemoryCache.put(key, bmp);
      }

//			if (imageViewReused(imgView, urlString))
//				return null;

      Object[] objs = new Object[4];
      objs[0] = imgView;
      objs[1] = urlString;
      objs[2] = bmp;
      objs[3] = rotation;

      return objs;
    }

    @Override
    protected void onPostExecute(Object[] result) {
      // TODO Auto-generated method stub
      if (result == null)
        return;

      final ImageView imgView = (ImageView) result[0];
//			final String urlString = (String) result[1];
      final Bitmap bmp = (Bitmap) result[2];
      final float rotation = (Float) result[3];

      imgView.post(new Runnable() {

        @Override
        public void run() {
          // TODO Auto-generated method stub
//					if (imageViewReused(imgView, urlString))
//						return;
          if (bmp == null) {
            imgView.setImageResource(mImageWaiting);
          } else {
//						imgView.setRotation(rotation);
            imgView.setImageBitmap(bmp);
          }
        }
      });


    }
  }


  public static synchronized Bitmap decodeFile(File file, int w, int h) {
    try {
      BitmapFactory.Options options = new BitmapFactory.Options();
      options.inJustDecodeBounds = true;
      BitmapFactory.decodeStream(new FileInputStream(file), null, options);

      int w_temp, h_temp;
      w_temp = options.outWidth;
      h_temp = options.outHeight;
      int scale = 1;
      while (true && w > 0 && h > 0) {
        if (w_temp / 2 < w || h_temp / 2 < h)
          break;
        w_temp /= 2;
        h_temp /= 2;
        scale *= 2;
      }

      BitmapFactory.Options options2 = new BitmapFactory.Options();
      options2.inSampleSize = scale;
      Bitmap bmp = BitmapFactory.decodeStream(new FileInputStream(file), null, options2);


      return bmp;
    } catch (Exception e) {
      Log.e(TAG, e.toString());
      return null;
    }

  }
}
