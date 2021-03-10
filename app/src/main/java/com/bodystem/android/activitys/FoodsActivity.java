package com.bodystem.android.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bodystem.android.R;
import com.bodystem.android.adapters.FirebaseAdapterFoods;
import com.bodystem.android.interfaces.OnListItemClick;
import com.bodystem.android.models.FoodsModel;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class FoodsActivity extends AppCompatActivity implements OnListItemClick {
    SharedPreferences sharedPreferences;

    MaterialToolbar toolbar;
    RecyclerView recyclerView;

    FirebaseAdapterFoods adapterFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);

        sharedPreferences = getSharedPreferences("general", MODE_PRIVATE);

        toolbar = findViewById(R.id.topAppBarFoods);
        recyclerView =  findViewById(R.id.recyclerViewFoods);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FoodsActivity.this, CategoriesActivity.class));
            }
        });

        createRecyclerView();
    }

    public void createRecyclerView() {
        Query query = FirebaseFirestore.getInstance().collection("foods")
                .whereEqualTo("type_id", sharedPreferences.getString("type_id", null));

        PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(10)
                .setPageSize(3)
                .build();

        FirestorePagingOptions<FoodsModel> options = new FirestorePagingOptions.Builder<FoodsModel>()
                .setLifecycleOwner(this)
                .setQuery(query, config, FoodsModel.class)
                .build();

        adapterFoods = new FirebaseAdapterFoods(options, this);

        adapterFoods.startListening();

        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterFoods);
    }

    @Override
    public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("food_id", documentSnapshot.getId());
        editor.apply();

        startActivity(new Intent(FoodsActivity.this, FoodActivity.class));
    }
}