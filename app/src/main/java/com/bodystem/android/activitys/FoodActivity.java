package com.bodystem.android.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bodystem.android.R;
import com.bodystem.android.controllers.FoodsController;
import com.bodystem.android.models.ExerciseTimeModel;
import com.bodystem.android.models.FoodsModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;

public class FoodActivity extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    SharedPreferences sharedPreferences;

    FoodsController foodsController = new FoodsController();
    ExerciseTimeModel exerciseTimeModel = new ExerciseTimeModel();
    DecimalFormat decimalFormat = new DecimalFormat("#,###.0");
    FoodsModel foodsModel = new FoodsModel();

    MaterialToolbar toolbar;
    TextView textViewFoodName, textViewCalories, textViewCaminhada, textViewCorrida,
            textViewMountainBike, textViewCozinhar, textViewDormir, textViewEstudar,
            textViewFutebol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        sharedPreferences = getSharedPreferences("general", MODE_PRIVATE);

        toolbar = findViewById(R.id.topAppBarFood);
        textViewFoodName = findViewById(R.id.textViewFoodName);
        textViewCalories = findViewById(R.id.textViewCalories);
        textViewCaminhada = findViewById(R.id.textViewCaminhada);
        textViewCorrida = findViewById(R.id.textViewCorrida);
        textViewMountainBike = findViewById(R.id.textViewMountainBike);
        textViewCozinhar = findViewById(R.id.textViewCozinhar);
        textViewDormir = findViewById(R.id.textViewDormir);
        textViewEstudar = findViewById(R.id.textViewEstudar);
        textViewFutebol = findViewById(R.id.textViewFutebol);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FoodActivity.this, FoodsActivity.class));
            }
        });


        getData();
    }

    public void getData() {
        firebaseFirestore.collection("foods")
                .document(sharedPreferences.getString("food_id", null))
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        foodsModel.setName(documentSnapshot.getString("name"));
                        foodsModel.setCalories(documentSnapshot.getDouble("calories"));
                        foodsModel.setId(documentSnapshot.getId());
                        foodsModel.setType_id(documentSnapshot.getString("type_id"));

                        exerciseTimeModel.setCalories(foodsModel.getCalories());

                        setData();
                    }
                });
    }

    @SuppressLint("SetTextI18n")
    public void setData() {
        textViewFoodName.setText(foodsModel.getName());
        textViewCalories.setText(String.valueOf(foodsModel.getCalories()));
        textViewCaminhada.setText(decimalFormat.format(exerciseTimeModel.getCaminhadaTime()) + " minutos");
        textViewCorrida.setText(decimalFormat.format(exerciseTimeModel.getCorridaTime()) + " minutos");
        textViewMountainBike.setText(decimalFormat.format(exerciseTimeModel.getMountainBikeTime()) + " minutos");
        textViewCozinhar.setText(decimalFormat.format(exerciseTimeModel.getCozinharTime()) + " minutos");
        textViewDormir.setText(decimalFormat.format(exerciseTimeModel.getDormirTime()) + " minutos");
        textViewEstudar.setText(decimalFormat.format(exerciseTimeModel.getEstudarTime()) + " minutos");
        textViewFutebol.setText(decimalFormat.format(exerciseTimeModel.getFutebolTime()) + " minutos");
    }

}
