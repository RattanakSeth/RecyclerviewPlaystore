package com.example.rattanak.recyclerviewplaystore.PatternDesign;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by rattanak on 7/2/17.
 */

public class AppSingleton {
    public static AppSingleton instance;
    public RequestQueue requestQueue;
    public ImageLoader imageLoader;
    private Context context;

    private AppSingleton(Context context){
        requestQueue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }
    public void setRequestQueue (RequestQueue requestQueue){
        this.requestQueue = requestQueue;
    }
    public ImageLoader getImageLoader(){
        return imageLoader;
    }
    public void setImageLoader (ImageLoader imageLoader){
        this.imageLoader = imageLoader;
    }

    public Context getContext(){
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    public static AppSingleton getInstance(Context context){
        if(instance == null){
            instance = new AppSingleton(context);
        }
        //else if note null
        return instance;
    }
}
