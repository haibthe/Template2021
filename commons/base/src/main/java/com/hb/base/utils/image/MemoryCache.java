package com.hb.base.utils.image;

import android.graphics.Bitmap;

import androidx.collection.LruCache;

public class MemoryCache extends LruCache<String, Bitmap> {
  public static final String TAG = MemoryCache.class.getName();

  protected static final long KB = 1024;
  protected static final long MB = 1024 * KB;

  private static final long LIMIT = 10 * MB;// max memory in bytes

  public MemoryCache() {
    super((int) LIMIT);
  }

  @Override
  protected int sizeOf(String key, Bitmap bmp) {
    return getSizeInBytes(bmp);
  }

  public void clear() {
    evictAll();
  }

  int getSizeInBytes(Bitmap bitmap) {
    if (bitmap == null)
      return 0;
    return bitmap.getRowBytes() * bitmap.getHeight();
  }
}
