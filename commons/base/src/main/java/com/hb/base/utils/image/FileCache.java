package com.hb.base.utils.image;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class FileCache {

  public static final String TAG = FileCache.class.getName();

  private File mCacheDir;

  public FileCache(Context context, String folderCache) {
    String state = Environment.getExternalStorageState();
    if (state.equals(Environment.MEDIA_MOUNTED)) {
      mCacheDir = new File(folderCache);
    } else {
      mCacheDir = context.getCacheDir();
    }

    if (!mCacheDir.exists())
      mCacheDir.mkdirs();
  }

  public File getFile(String url) {
    String fileName = Utils.md5(url);
    File fileTest = new File(mCacheDir + "/" + fileName);
    if (fileTest.exists()) {
      return fileTest;
    } else {
      File file = new File(mCacheDir, fileName);
      return file;
    }
  }

  @SuppressLint("LongLogTag")
  public File getFileNoCache(String url) {
    String fileName = Utils.md5(url);
    File fileTest = new File(mCacheDir + "/" + fileName);
    if (fileTest.exists()) {
      return fileTest;
    } else {
      try {
        fileTest.createNewFile();
      } catch (IOException e) {
        Log.e("FileCache - getFileNoCache", e.toString());
      }
      return fileTest;
    }
  }

  public void clear() {
    File[] files = mCacheDir.listFiles();
    if (files == null)
      return;
    for (File f : files) {
      f.delete();
    }
  }

}
