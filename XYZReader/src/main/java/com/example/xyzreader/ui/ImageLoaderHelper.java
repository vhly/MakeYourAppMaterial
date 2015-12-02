package com.example.xyzreader.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.util.LruCache;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;

import java.io.File;

public class ImageLoaderHelper {
    private static ImageLoaderHelper sInstance;

    public static ImageLoaderHelper getInstance(Context context) {
        if (sInstance == null) {
            synchronized (ImageLoaderHelper.class) {
                if (sInstance == null) {
                    sInstance = new ImageLoaderHelper(context.getApplicationContext());
                }
            }
        }

        return sInstance;
    }

    private final LruCache<String, Bitmap> mImageCache = new LruCache<String, Bitmap>(20);
    private ImageLoader mImageLoader;

    private RequestQueue queue;

    private ImageLoaderHelper(Context applicationContext) {

//        RequestQueue queue = Volley.newRequestQueue(applicationContext);

        // Add Cache dir support.
        File cacheDir = applicationContext.getCacheDir();

        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File dir = applicationContext.getExternalCacheDir();
            if (dir != null && dir.exists() && dir.canWrite()) {
                cacheDir = dir;
            }
        }

        DiskBasedCache cache = new DiskBasedCache(cacheDir);

        queue = new RequestQueue(cache, new BasicNetwork(new HurlStack()));

        queue.start();

        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
            @Override
            public void putBitmap(String key, Bitmap value) {
                mImageCache.put(key, value);
            }

            @Override
            public Bitmap getBitmap(String key) {
                return mImageCache.get(key);
            }
        };
        mImageLoader = new ImageLoader(queue, imageCache);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public void shutdown() {
        mImageLoader = null;
        queue.stop();
        queue = null;
    }
}
