package com.bodystem.android.controllers;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bodystem.android.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class UserController {
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    boolean admin;

    // TODO: CRIA O USUÁRIO NO BANCO DE DADOS
    public void createUser(UserModel user) {
        Map<String, Object> userData = new HashMap<>();

        userData.put("email", user.getEmail());
        userData.put("name", user.getName());
        userData.put("id", user.getUserId());
        userData.put("admin", false);

        firebaseFirestore.collection("users")
                .add(userData);
    }

    public boolean checkAdmin(String email) {

        firebaseFirestore.collection("users")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                if (documentSnapshot.getBoolean("admin")) {
                                    admin = true;
                                } else {
                                    admin = false;
                                }
                            }
                        }
                     }
                });

        return admin;
    }
}
