package com.bodystem.android.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.bodystem.android.R;
import com.bodystem.android.adapters.FirebaseAdapterTypes;
import com.bodystem.android.interfaces.OnListItemClick;
import com.bodystem.android.models.TypesModel;
import com.bodystem.android.utils.Auth;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class CategoriesActivity extends AppCompatActivity implements OnListItemClick {
    SharedPreferences sharedPreferences;

    Auth auth = new Auth();

    MaterialToolbar toolbar;
    RecyclerView recyclerView;

    FirebaseAdapterTypes adapterTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        sharedPreferences = getSharedPreferences("general", MODE_PRIVATE);

        toolbar = findViewById(R.id.topAppBarHome);
        recyclerView = findViewById(R.id.recyclerViewHome);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                auth.signOut(CategoriesActivity.this);
                return false;
            }
        });

        createRecyclerView();
    }

    public void createRecyclerView() {
        Query query = FirebaseFirestore.getInstance().collection("types")
                .orderBy("name");

        PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(10)
                .setPageSize(3)
                .build();

        FirestorePagingOptions<TypesModel> options = new FirestorePagingOptions.Builder<TypesModel>()
                .setLifecycleOwner(this)
                .setQuery(query, config, TypesModel.class)
                .build();

        adapterTypes = new FirebaseAdapterTypes(options, this);

        adapterTypes.startListening();

        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterTypes);
    }

    @Override
    public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("type_id", documentSnapshot.getId());
        editor.apply();

        startActivity(new Intent(CategoriesActivity.this, FoodsActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}