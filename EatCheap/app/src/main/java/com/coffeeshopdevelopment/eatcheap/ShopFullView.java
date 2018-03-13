package com.coffeeshopdevelopment.eatcheap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ShopFullView extends AppCompatActivity {
    private static final String TAG = "ShopFullView";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_full_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView image = (ImageView) findViewById(R.id.ShopImage);
        TextView title = (TextView) findViewById(R.id.TitleText);
        TextView description = (TextView) findViewById(R.id.DescriptionText);
        TextView location = (TextView) findViewById(R.id.LocationText);

        Intent mIntent = getIntent();
        final int position = mIntent.getIntExtra("position", 0);
        Shop shop = MainActivity.shopList.get(position);

        //  this still needs to be assigned
        TextView menu = (TextView) findViewById(R.id.MenuText);

        image.setImageResource(shop.getImage());
        title.setText(shop.getTitle());
        description.setText(shop.getDescription());
        location.setText(shop.getLocation());

        // for test purposes only please delete this method
        Button backButton = (Button) findViewById(R.id.backButton);
        View.OnClickListener backListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: called");
                Intent intent = new Intent(ShopFullView.this, MapsActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        };
        backButton.setOnClickListener(backListener);


    }

}
