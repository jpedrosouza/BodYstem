package com.bodystem.android.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bodystem.android.R;
import com.bodystem.android.controllers.UserController;
import com.bodystem.android.utils.Auth;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AdminLoginActivity extends AppCompatActivity {
    UserController userController = new UserController();
    Auth auth = new Auth();

    MaterialToolbar toolbar;
    TextInputEditText inputEditTextEmail, inputEditTextPassword;
    MaterialButton buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        toolbar = findViewById(R.id.topAppBarAdminLogin);
        inputEditTextEmail = findViewById(R.id.textInputEmailAdminLogin);
        inputEditTextPassword = findViewById(R.id.textInputPasswordAdmninLogin);
        buttonLogin = findViewById(R.id.loginButtonAdmninLogin);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLoginActivity.this, MainActivity.class));
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    void login() {
        String email = inputEditTextEmail.getText().toString();
        String password = inputEditTextPassword.getText().toString();

        if (userController.checkAdmin(email)) {
            auth.signIn(email, password, this);
        } else {
            Toast.makeText(this, "Acesso permitido apenas para administradores.",
                    Toast.LENGTH_SHORT).show();
        }
    }

}