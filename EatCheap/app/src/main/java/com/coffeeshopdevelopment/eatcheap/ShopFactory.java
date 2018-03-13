package com.coffeeshopdevelopment.eatcheap;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Coen Byrne on 16/02/2018.
 */

class ShopFactory {

    private static final String TAG = "ShopFactory";
    private Hashtable<Character, Integer> alphabetWeighting = new Hashtable<Character, Integer>();

    List<Shop> buildShopList(List<Shop> shopList){
        shopList = new ArrayList<>();
        shopList.add(buildDonDons());
        shopList.add(buildShanghiDumplings());
        shopList.add(buildBorekAndGozleme());
        shopList.add(buildMcDonalds());
        shopList.add(buildTest1());
        shopList.add(buildTest2());
        shopList.add(buildTest3());


        return shopList;
    }

    List<Shop> OrderByRating(List<Shop> shopList){
        for(int x=1; x<shopList.size(); x++) {
            Shop shop = shopList.get(x);
            double rating = shop.getRating();
            if (rating > shopList.get(x - 1).getRating()) {
                shopList.remove(x);
                for (int y = x - 1; y >= 0; y--) {
                    if (rating <= shopList.get(y).getRating()) {
                        shopList.add(y+1, shop);
                        break;
                    } else if (y == 0) {
                        shopList.add(y, shop);
                    }
                }
            }
        }
        return shopList;
    }

    List<Shop> OrderByTitle(List<Shop> shopList){
        Log.d(TAG, "OrderByTitle: starts");
        if(alphabetWeighting.isEmpty()){
            initializeHashSet();
        }

        for(int x=1; x<shopList.size()-1; x++) {
            Shop shop = shopList.get(x);
            String title = shop.getTitle().toLowerCase();
            char firstLetter = title.charAt(0);
            if (alphabetWeighting.get(firstLetter) > alphabetWeighting.get(shopList.get(x - 1).getTitle().toLowerCase().charAt(0))) {
                shopList.remove(shop);
                for (int y = (x - 1); y >= 0; y--) {
                    if (alphabetWeighting.get(firstLetter) <= alphabetWeighting.get(shopList.get(y).getTitle().toLowerCase().charAt(0))) {
                        shopList.add(y+1, shop);
                        break;
                    } else if (y == 0) {
                        shopList.add(y, shop);
                    }
                }
            }
        }
        return shopList;
    }

    private void initializeHashSet(){
        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z'};
        for (int x = 0; x < alphabet.length; x++){
            alphabetWeighting.put(alphabet[x], 25-x);
        }
    }

    List<Shop> OrderByType(List<Shop> shopList){
        return shopList;
    }


    private Shop buildDonDons(){
        String title = "Don-Don";
        String description = "Japanese street food \n $7-$10 meals";
        float rating = 4.5f;
        String location = "198 Little Lonsdale St, Melbourne CBD";
        int image = R.drawable.don_don;

        LatLng shopLocation = new LatLng(-37.8105249, 144.9617298);

        return new Shop(title, description, location, rating, image, shopLocation);
    }

    private Shop buildShanghiDumplings(){
        String title = "Shanghai Dumplings";
        String description = "Chinese dumplings \n $5-$10 meals";
        float rating = 4;
        String location = "Tattersalls Lane, Melbourne CBD";
        int image = R.drawable.shanghai_dumpling_house;

        LatLng shopLocation = new LatLng(-37.8119048, 144.9656045);

        return new Shop(title, description, location, rating, image, shopLocation);
    }

    private Shop buildBorekAndGozleme(){
        String title = "Borek & Gozleme";
        String description = "Turkish street food \n $3-$6 meals";
        float rating = 5;
        String location = "473 Elizabeth St, Melbourne CBD";
        int image = R.drawable.borek_and_gozleme;

        LatLng shopLocation = new LatLng(-37.8078085, 144.959913);

        return new Shop(title, description, location, rating, image, shopLocation);
    }

    private Shop buildMcDonalds(){
        String title = "McDonalds";
        String description = "American fast food \n $3-$6 meals (penny-pincher's menu)";
        float rating = 3;
        String location = "Everywhere in Melbourne";
        int image = R.drawable.mcdonalds;

        LatLng shopLocation = new LatLng(-37.813611, 144.963056);

        return new Shop(title, description, location, rating, image, shopLocation);
    }

    private Shop buildTest1(){
        String title = "Test";
        String description = "Test";
        float rating = 2;
        String location = "Location:";
        int image = R.drawable.ic_photo_black_48dp;

        LatLng shopLocation = new LatLng(-37.8105249, 144.9617298);

        return new Shop(title, description, location, rating, image, shopLocation);
    }

    private Shop buildTest2(){
        String title = "Test";
        String description = "Test";
        float rating = 2;
        String location = "Location:";
        int image = R.drawable.ic_photo_black_48dp;

        LatLng shopLocation = new LatLng(-37.8105249, 144.9617298);

        return new Shop(title, description, location, rating, image, shopLocation);
    }

    private Shop buildTest3(){
        String title = "Test";
        String description = "Test";
        float rating = 2;
        String location = "Location:";
        int image = R.drawable.ic_photo_black_48dp;

        LatLng shopLocation = new LatLng(-37.8105249, 144.9617298);

        return new Shop(title, description, location, rating, image, shopLocation);
    }
}
