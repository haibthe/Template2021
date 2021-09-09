package com.hb.base.utils.image;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MemoryCache_temp {
  public static final String TAG = MemoryCache_temp.class.getName();

  private static final long KB = 1024;
  private static final long MB = 1024 * KB;

  private Map<String, Bitmap> cache = Collections
    .synchronizedMap(new LinkedHashMap<String, Bitmap>(10, 1.5f, true));
  private long size = 0;
  private long limit = 40 * MB;// max memory in bytes

  public MemoryCache_temp() {
    // use 25% of available heap size
    // Log.d(TAG,Runtime.getRuntime().maxMemory()+"");
    setLimit(1024 * 1024 * 4);
  }

  public void setLimit(long new_limit) {
    limit = new_limit;
    Log.i(TAG, "MemoryCache will use up to " + limit / 1024. / 1024. + "MB");
  }

  public Bitmap get(String key) {
    try {
      if (!cache.containsKey(key))
        return null;
      return cache.get(key);
    } catch (NullPointerException ex) {
      ex.printStackTrace();
      return null;
    }
  }

  public void put(String key, Bitmap bitmap) {
    try {
      if (cache.containsKey(key))
        size -= getSizeInBytes(cache.get(key));
      cache.put(key, bitmap);
      size += getSizeInBytes(bitmap);
      checkSize();
    } catch (Throwable th) {
      th.printStackTrace();
    }
  }

  private void checkSize() {
    Log.i(TAG, "cache size=" + size + " length=" + cache.size());
    if (size > limit) {
      Iterator<Entry<String, Bitmap>> iter = cache.entrySet().iterator();
      while (iter.hasNext()) {
        Entry<String, Bitmap> entry = iter.next();
        Bitmap bmp = entry.getValue();
        long s = getSizeInBytes(bmp);
        Log.i(TAG, s + "");
        size -= s;
        if (bmp != null && !bmp.isRecycled()) {
          bmp.recycle();
          bmp = null;
          System.gc();
        }
        iter.remove();
        if (size <= limit)
          break;
      }
      Log.i(TAG, "Clean cache. New size " + cache.size());
    }
  }

  public void clear() {
    try {
      Iterator<Entry<String, Bitmap>> iter = cache.entrySet().iterator();
      while (iter.hasNext()) {
        Entry<String, Bitmap> entry = iter.next();
        Bitmap bmp = entry.getValue();
        if (bmp != null && !bmp.isRecycled()) {
          bmp.recycle();
          bmp = null;
        }
        iter.remove();
      }
      cache.clear();
      System.gc();
      size = 0;
    } catch (NullPointerException ex) {
      ex.printStackTrace();
    }
  }

  long getSizeInBytes(Bitmap bitmap) {
    if (bitmap == null)
      return 0;
    return bitmap.getRowBytes() * bitmap.getHeight();
  }
}
