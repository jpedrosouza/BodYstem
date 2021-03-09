package com.bodystem.android.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bodystem.android.utils.Auth;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.bodystem.android.R;

public class LoginActivity extends AppCompatActivity {

    Auth auth = new Auth();

    MaterialToolbar toolbar;
    TextInputEditText inputEditTextEmail, inputEditTextPassword;
    MaterialButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = findViewById(R.id.topAppBar);
        inputEditTextEmail = findViewById(R.id.textInputEmail);
        inputEditTextPassword = findViewById(R.id.textInputPassword);
        loginButton = findViewById(R.id.loginButton);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String email =  inputEditTextEmail.getText().toString();
        String password = inputEditTextPassword.getText().toString();

        auth.signIn(email, password, LoginActivity.this);
    }

}