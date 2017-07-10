package com.example.rattanak.recyclerviewplaystore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.rattanak.recyclerviewplaystore.Adapter.OnRecyclerviewItemClickListener;
import com.example.rattanak.recyclerviewplaystore.Adapter.RecyclerViewDataAdapter;
import com.example.rattanak.recyclerviewplaystore.Adapter.SectionListDataAdapter;
import com.example.rattanak.recyclerviewplaystore.Data.SectionDataModel;
import com.example.rattanak.recyclerviewplaystore.Data.SingleItemModel;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnRecyclerviewItemClickListener {
    private Toolbar toolbar;
    ArrayList<SectionDataModel> allSampleData;
    ArrayList<SingleItemModel> sampleData;
    private RecyclerViewDataAdapter adapter;
    private SectionListDataAdapter sectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        allSampleData = new ArrayList<SectionDataModel>();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle("G PlayStore");

        }

        //createDummyData();

        //create new section adapter
        sectionAdapter = new SectionListDataAdapter(this, sampleData);
        RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);

        my_recycler_view.setHasFixedSize(true);

        adapter = new RecyclerViewDataAdapter(this, allSampleData);

        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        my_recycler_view.setAdapter(adapter);




    }

    @Override
    protected void onStart() {
        super.onStart();
        //web service test
        loadBooks("http://10.0.2.2/testrcl/testbooks.php", "testing one");
        loadBooks("http://10.0.2.2/testrcl/self_help.php", "Self_help");
        loadBooks("http://10.0.2.2/elibrary/readingbook.php", "technology");
    }
    ///process of  add data in array
    //1.create sectionModel //to add it to singleitem by using function
    //2. add title to section data
    //3. create singleItem array and set data to single item array
    //4. after set data to singleItem array we add this single item to SigleItem array in Section data class
    //5. and then add singleData item in Section DataModel class to Section Data Model

    //CREATE DATA TO PASS IT IN RECYCLERVIEW
//            private void createDummyData() {
//                //loop in loop
//                for (int i = 1; i <= 1; i++) {
//
//                    SectionDataModel dm = new SectionDataModel();
//                    //get section tilte
//                    dm.setHeaderTitle("Section " + i);
//                    //create a new array list
//                    ArrayList<SingleItemModel> singleItem = new ArrayList<SingleItemModel>();
//                    //add data to array list
//                    for (int j = 0; j <= 5; j++) {
//                        singleItem.add(new SingleItemModel("Item " + j, R.mipmap.ic_tablet));
//                    }
//
//                    dm.setAllItemsInSection(singleItem);
//
//                    allSampleData.add(dm);
//
//                }
//                addData(1,3);
//                SectionDataModel dm = new SectionDataModel();
//                dm.setHeaderTitle("section test");
//                ArrayList<SingleItemModel> singleitem = new ArrayList<SingleItemModel>();
//                singleitem.add(new SingleItemModel("book 1", R.mipmap.ic_launcher));
//                singleitem.add(new SingleItemModel("book 2", R.drawable.ic_business));
//                singleitem.add(new SingleItemModel("book 3", R.drawable.teens));
//                dm.setAllItemsInSection(singleitem);
//                //add it to sample data
//                allSampleData.add(dm);
//
//        }
//
//
//    //add data
//    private void addData(int section, int data ){
//        //loop in loop
//        for (int i = 1; i <= section; i++) {
//
//            SectionDataModel dm = new SectionDataModel();
//            //get section tilte
//            dm.setHeaderTitle("Section " + i);
//            //create a new array list
//            ArrayList<SingleItemModel> singleItem = new ArrayList<SingleItemModel>();
//            //add data to array list
//            for (int j = 0; j <= data; j++) {
//                singleItem.add(new SingleItemModel("Item " + j, R.drawable.teens));
//            }
//
//            dm.setAllItemsInSection(singleItem);
//
//            allSampleData.add(dm);
//
//        }
//    }
    //load data from web service
    private void loadBooks(String urlText, final String SectionHeader)
    {
        Log.d("Book","Loading books");
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = urlText;

        final JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //convert response to document object
                //Apply document to adapter
                SectionDataModel dm = new SectionDataModel();
                dm.setHeaderTitle(SectionHeader);
                Log.i("Book","Loaded Books success"+ response.toString());
                ArrayList<SingleItemModel> singleItemlist = new ArrayList<SingleItemModel>();
                //SingleItemModel[] singleItemModels = new SingleItemModel[response.length()];
                boolean isError = false;
                Gson gson = new Gson();
                for (int i=0; i < response.length();i++){
                    try {
                        //try to get json object one by one in web service
                        JSONObject jsonObject = response.getJSONObject(i);
                        singleItemlist.add(gson.fromJson(jsonObject.toString(), SingleItemModel.class));


                    }catch (JSONException e){
                        Log.d("Book", "Error converting document");
                        isError = true;
                    }
                }
                if(isError){
                    Toast.makeText(MainActivity.this,"Error while loading documents", Toast.LENGTH_LONG).show();
                }
                //get it inot section model
                Log.d("Book", "show itemlist" + singleItemlist);
                //get one by one to single item model
                //sectionAdapter.setBook(singleItemlist);//on null object
                dm.setAllItemsInSection(singleItemlist);
                allSampleData.add(dm);
                // And then add all single item data to section Data to combine it together
                //adapter.setBooks(singleItemlist);
               //Toast.makeText(MainActivity.this, "test it" + singleItemlist, Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                //show that your file can not load
                Toast.makeText(MainActivity.this, "Error while loading docuements 1", Toast.LENGTH_LONG).show();
                Log.d("Book", "Error load docs: " + error.getMessage());
            }
        });
        //add all request to queue
        requestQueue.add(request);
    }

    @Override
    public void onRecyclerViewItemClickListener(int position) {

    }
}
