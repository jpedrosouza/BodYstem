package com.bodystem.android.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bodystem.android.activitys.MainActivity;
import com.bodystem.android.activitys.RegisterActivity;
import com.bodystem.android.controllers.UserController;
import com.bodystem.android.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Auth {
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    UserController userController = new UserController();

    // TODO: REALIZA O LOGIN DO USUÁRIO NO APP.
    public void signIn(final String email, String password, final Context context) {

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    if (userController.checkAdmin(email)) {
                        context.startActivity(new Intent(context, MainActivity.class));
                    } else {
                        context.startActivity(new Intent(context, MainActivity.class));
                    }
                } else {
                    Toast.makeText(context, "Usuário ou senha incorretos.", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Houve um erro, tente novamente.", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    public void createUser(final String email, String password, final String name, final Context context) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    String userId = firebaseAuth.getCurrentUser().getUid();

                    UserModel userModel = new UserModel(email, name, userId);

                    userController.createUser(userModel);

                    context.startActivity(new Intent(context, MainActivity.class));
                } else {
                    Toast.makeText(context, "Houve um erro, tente novamente.", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    // TODO: REALIZA O LOGOUT DO USUÁRIO NO APP.
    public void signOut(Context context) {
        firebaseAuth.signOut();

        context.startActivity(new Intent(context, MainActivity.class));
    }
}
