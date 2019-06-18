package com.example.wake.demo.neglide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.LinkedBlockingDeque;

public class BitmapDispather extends Thread {

    private Handler handler = new Handler(Looper.getMainLooper());
    private LinkedBlockingDeque<BitmapRequest> requestQueue;

    public BitmapDispather(LinkedBlockingDeque requestQueue) {
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        super.run();
        while (!isInterrupted()) {
            try {
                BitmapRequest br = requestQueue.take();
                setLoadingImg(br);
                Bitmap bitmap = findBitmap(br);
                showImg(bitmap, br);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void showImg(final Bitmap bitmap, final BitmapRequest br) {
        if (bitmap != null && br.getImageView() != null &&
                br.getUrlMd5().equals(br.getImageView().getTag())) {
            final ImageView imageView = br.getImageView();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);
                    if (br.getRequestListener() != null){
                       RequestListener listener = br.getRequestListener();
                       listener.onSuccess(bitmap);
                    }
                }
            });
        }

    }

    private Bitmap findBitmap(BitmapRequest br) {
        Bitmap bitmap = downloadImg(br.getUrl());
        return bitmap;
    }

    private Bitmap downloadImg(String url) {
        InputStream inputStream = null;
        Bitmap bitmap = null;
        try {
            URL uri = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) uri.openConnection();
            inputStream = urlConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return bitmap;
    }


    private void setLoadingImg(BitmapRequest br) {
        if (br.getResId() > 0 && br.getImageView() != null) {
            final int resId = br.getResId();
            final ImageView imageView = br.getImageView();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageResource(resId);
                }
            });
        }
    }
}
