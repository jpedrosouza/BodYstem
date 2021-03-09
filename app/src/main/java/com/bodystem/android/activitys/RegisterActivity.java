package com.bodystem.android.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bodystem.android.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    MaterialToolbar toolbar;
    TextInputEditText inputEditTextEmail, inputEditTextName, inputEditTextPassword;
    TextInputEditText inputEditTextConfirmPassword;
    MaterialButton registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = findViewById(R.id.topAppBarRegister);
        inputEditTextEmail = findViewById(R.id.textInputEmailRegister);
        inputEditTextName = findViewById(R.id.textInputNameRegister);
        inputEditTextPassword = findViewById(R.id.textInputPasswordRegister);
        inputEditTextConfirmPassword = findViewById(R.id.textInputConfirmPasswordRegister);
        registerButton = findViewById(R.id.registerButton);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    // TODO: Realiza o registro do usuário no FirebaseAuth e no Banco de Dados.
    private void register() {

    }
}