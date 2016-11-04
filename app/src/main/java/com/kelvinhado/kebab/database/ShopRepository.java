package com.kelvinhado.kebab.database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kelvinhado.kebab.model.Shop;
import com.kelvinhado.kebab.model.Shops;

/**
 * Created by kelvin on 15/10/2016.
 */

public class ShopRepository {

    // Write a message to the database
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference ref = database.getReference();
    public static DatabaseReference shopRef = database.getReference().child("shops");

    public static void addShop(Shop shop){
        ref.child("shops").child(shop.getId()).setValue(shop);
    }


//    public static Shops getShops(){
//
//    }

    public static Shop toShop(DataSnapshot dataSnapshot){
       return dataSnapshot.getValue(Shop.class);

    }


}
