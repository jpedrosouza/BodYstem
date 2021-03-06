package com.bodystem.android.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bodystem.android.R;
import com.bodystem.android.activitys.admin.AdminLoginActivity;
import com.bodystem.android.utils.Auth;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    Auth auth = new Auth();

    MaterialButton buttonLogin, buttonRegister, buttonInvite, buttonAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogin = findViewById(R.id.buttonHaveAccount);
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonInvite = findViewById(R.id.buttonInvite);
        buttonAdmin = findViewById(R.id.buttonAdmin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        buttonInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signGuest(MainActivity.this);
            }
        });

        buttonAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AdminLoginActivity.class));
            }
        });
    }
}