package com.coffeeshopdevelopment.eatcheap;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Coen Byrne on 16/02/2018.
 */

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ShopViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private Context mContext;

    RecyclerViewAdapter(List<Shop> shopList, Context context) {
        mContext = context;
    }

    @Override
    public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // called by the layout manager when it needs a new view
        Log.d(TAG, "onCreateViewHolder: view requested");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_layout, parent, false);
        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopViewHolder holder, final int position) {
        // called by the Layout manager when it wants new data in an existing row
        Log.d(TAG, "onBindViewHolder: ");
        Shop shopItem = MainActivity.shopList.get(position);
        // grabs the title of the Shop
        Log.d(TAG, "onBindViewHolder: " + shopItem.getTitle() + " --> " + position );
        holder.title.setText(shopItem.getTitle());
        holder.description.setText(shopItem.getDescription());
        holder.rating.setRating(shopItem.getRating());
        holder.location.setText(shopItem.getLocation());
        holder.image.setImageResource(shopItem.getImage());

    }

    @Override
    public int getItemCount() {
        /*
        if (mPhotoList != null && mPhotoList.size() != 0){
            return mPhotoList.size();
        } else {
            return 0;
        }
         */
        return ((MainActivity.shopList != null && MainActivity.shopList.size() != 0) ? MainActivity.shopList.size() : 0);
    }

    void loadNewData (List<Shop> newShops){
        MainActivity.shopList = newShops;
        notifyDataSetChanged();
    }

    public Shop getShop(int position){
        return ((MainActivity.shopList != null && MainActivity.shopList.size() != 0) ? MainActivity.shopList.get(position) : null);
    }

    static class ShopViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "ShopViewHolder";

        ImageView image = null;
        TextView title = null;
        TextView description = null;
        TextView location = null;
        RatingBar rating = null;


        private ShopViewHolder(View itemView) {

            super(itemView);
            // assigning the views
            Log.d(TAG, "ShopViewHolder: starts");
            this.image = (ImageView) itemView.findViewById(R.id.imageView);
            this.title = (TextView) itemView.findViewById(R.id.Title);
            this.description = (TextView) itemView.findViewById(R.id.Description);
            this.location = (TextView) itemView.findViewById(R.id.LocationText);
            this.rating = (RatingBar) itemView.findViewById(R.id.Rating);

            rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                    MainActivity.shopList.get(getAdapterPosition()).setRating(v);
                    Log.d(TAG, "onRatingChanged: to " + MainActivity.shopList.get(getAdapterPosition()).getRating());
                }
            });
        }
    }
}
