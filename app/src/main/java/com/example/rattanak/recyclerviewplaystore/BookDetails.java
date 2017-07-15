package com.example.rattanak.recyclerviewplaystore;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.rattanak.recyclerviewplaystore.Data.SingleItemModel;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rattanak on 7/13/17.
 */

public class BookDetails extends AppCompatActivity {

    ImageView imgBook;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_details);

        imgBook = (ImageView)findViewById(R.id.book_image);

        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Book Detail");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String serializedBook = getIntent().getStringExtra("serializedBook");
        Gson gson = new Gson();
        SingleItemModel book = gson.fromJson(serializedBook, SingleItemModel.class);
        processSaveHitHistory(book.getId());
        loadBookImageFromServer("http://10.0.2.2" + book.getThumbnailUrl());
    }


    //save process history to know whare user click
    private void processSaveHitHistory(int documentId){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject params = new JSONObject();
        String serverUrl = "http://10.0.2.2/testrcl/testbooks.php?id=" + documentId;
        JsonObjectRequest request = new JsonObjectRequest(serverUrl, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String image = response.getString("_thumbnail_url");
                    Log.d("book",response.toString());
                    loadBookImageFromServer(response.getString(image));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("rupp", "Save hits history success");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("rupp", "Save hits history fail: " + error.getMessage());
            }
        });
        requestQueue.add(request);
    }

    private void loadBookImageFromServer(final String imageUrl){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //request from server and scalable image
        ImageRequest imageRequest = new ImageRequest(imageUrl, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imgBook.setImageBitmap( response);
            }
        }, 512, 512, ImageView.ScaleType.FIT_CENTER, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BookDetails.this, "Fail to load image ", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(imageRequest);
    }

}
