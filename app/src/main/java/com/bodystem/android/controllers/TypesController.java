package com.bodystem.android.controllers;

import androidx.annotation.NonNull;

import com.bodystem.android.models.TypesModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TypesController {
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    public TypesModel getType(final String typeId) {
        final TypesModel typesModel = new TypesModel();

        firebaseFirestore.collection("types")
                .document(typeId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        typesModel.setId(documentSnapshot.getId());
                        typesModel.setName(documentSnapshot.getString("name"));
                    }
                });

        return typesModel;
    }

    public void createType(String name) {
        Map<String, Object> typeData = new HashMap<>();

        typeData.put("name", name);

        firebaseFirestore.collection("types")
            .add(typeData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Map<String, Object> dataUpdate = new HashMap<>();

                dataUpdate.put("id", documentReference.getId());

                documentReference.update(dataUpdate);
            }
        });
    }

    public void updateTypeName(String newName, String typeId) {
        firebaseFirestore.collection("types")
                .document(typeId)
                .update("name", newName);
    }

    public void deleteType(String typeId) {
        firebaseFirestore.collection("types")
                .document(typeId).delete();

        firebaseFirestore.collection("foods")
                .whereEqualTo("type_id", typeId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                documentSnapshot.getReference().delete();
                            }
                        }
                    }
                });
    }
}
