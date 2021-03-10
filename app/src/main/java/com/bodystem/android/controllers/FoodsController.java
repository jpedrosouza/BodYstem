package com.bodystem.android.controllers;

import androidx.annotation.NonNull;

import com.bodystem.android.models.FoodsModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FoodsController {
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    volatile FoodsModel foodsModel = null;

    public void createFood(String name, String typeId, double calories) {
        final Map<String, Object> dataFood = new HashMap<>();

        dataFood.put("name", name);
        dataFood.put("type_id", typeId);
        dataFood.put("calories", calories);

        firebaseFirestore.collection("foods")
                .add(dataFood).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Map<String, Object> dataUpdate =  new HashMap<>();

                dataUpdate.put("id", documentReference.getId());

                documentReference.update(dataUpdate);
            }
        });
    }

    public void deleteFood(String foodId) {
        firebaseFirestore.collection("foods")
                .document(foodId).delete();
    }


}
