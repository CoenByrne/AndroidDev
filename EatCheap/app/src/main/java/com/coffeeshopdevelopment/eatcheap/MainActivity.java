package com.coffeeshopdevelopment.eatcheap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerOnClickListener.OnRecyclerClickListener {
    private static final String TAG = "MainActivity";
    public static List<Shop> shopList;
    RecyclerViewAdapter mRecyclerViewAdapter;
    ShopFactory mShopFactory = new ShopFactory();
    boolean orderByTitle;
    boolean orderByRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: starts");
        setContentView(R.layout.activity_main_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        shopList = mShopFactory.buildShopList(shopList);

        refreshStream();
    }

    private void refreshStream (){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return null;
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerOnClickListener(this, recyclerView, this));
        mRecyclerViewAdapter = new RecyclerViewAdapter(shopList, this);
        recyclerView.setAdapter(mRecyclerViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.SortByRating){
            shopList = mShopFactory.OrderByRating(shopList);
            refreshStream();
            orderByRating = true;
            orderByTitle = false;
            return true;
        } else if (id == R.id.SortByTitle) {
            shopList = mShopFactory.OrderByTitle(shopList);
            refreshStream();
            orderByTitle = true;
            orderByRating = false;
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.i(TAG, "onItemClick: starts");
        Toast.makeText(MainActivity.this, "tap at position " + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ShopFullView.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Log.d(TAG, "onItemLongClick: starts");
        Toast.makeText(MainActivity.this, "long tap at position " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("OrderByTitle", orderByTitle);
        outState.putBoolean("OrderByRating", orderByRating);
        Log.d(TAG, "onSaveInstanceState: ends   " + orderByRating + "  " + orderByTitle);

        float[] ratingArray = new float[shopList.size()];

        for (int x=0; x < shopList.size(); x++){
            ratingArray[x] = shopList.get(x).getRating();
        }
        outState.putFloatArray("rating" ,ratingArray);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState: starts");
        orderByRating = savedInstanceState.getBoolean("OrderByRating");
        orderByTitle = savedInstanceState.getBoolean("OrderByTitle");
        Log.d(TAG, "onRestoreInstanceState: ends  " + orderByTitle + "  " + orderByRating);

        mShopFactory = new ShopFactory();
        // if ordered saved state will reassign list to ordered
        if(orderByRating){
            shopList = mShopFactory.OrderByTitle(shopList);
            for (int x=0; x<shopList.size(); x++){
                Log.d(TAG, "onRestoreInstanceState: " + shopList.get(x).toString());
            }

            Log.d(TAG, "onRestoreInstanceState: order by rating called");
        } if (orderByTitle){
            shopList = mShopFactory.OrderByTitle(shopList);
        }


        /*
        float[] ratingArray = savedInstanceState.getFloatArray("rating");
        for (int x=0; x<ratingArray.length; x++){
            shopList.get(x).setRating(ratingArray[x]);
        }
        */

        refreshStream();
        super.onRestoreInstanceState(savedInstanceState);
    }
}
