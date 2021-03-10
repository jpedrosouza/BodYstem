package com.bodystem.android.activitys.admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bodystem.android.R;
import com.bodystem.android.activitys.MainActivity;
import com.bodystem.android.adapters.FirebaseAdapterTypesAdmin;
import com.bodystem.android.controllers.TypesController;
import com.bodystem.android.interfaces.OnListItemClick;
import com.bodystem.android.models.TypesModel;
import com.bodystem.android.utils.Auth;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class AdminTypesActivity extends AppCompatActivity implements OnListItemClick {
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    TypesController typesController = new TypesController();
    Auth auth = new Auth();
    FirebaseAdapterTypesAdmin adapterTypesAdmin;

    MaterialToolbar toolbar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_types);

        toolbar = findViewById(R.id.topAppBarAdminTypes);
        recyclerView = findViewById(R.id.listViewAdminTypes);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.addTypeAdmin) {
                    createAlertDialog();
                } else {
                    auth.signOut(AdminTypesActivity.this);
                }

                return false;
            }
        });

        createListView();
    }

    public void createListView() {
        Query query = firebaseFirestore.collection("types")
                .orderBy("name");

        PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(10)
                .setPageSize(3)
                .build();

        FirestorePagingOptions<TypesModel> options = new FirestorePagingOptions.Builder<TypesModel>()
                .setLifecycleOwner(this)
                .setQuery(query, config, TypesModel.class)
                .build();

        adapterTypesAdmin = new FirebaseAdapterTypesAdmin(options, this);

        adapterTypesAdmin.startListening();

        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterTypesAdmin);
    }

    public void createAlertDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText editText = new EditText(this);

        editText.setPadding(16, 0, 16, 0);

        alert.setTitle("Crie um novo tipo");
        alert.setMessage("Insira o nome do novo tipo");
        alert.setView(editText);

        alert.setPositiveButton("Criar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                typesController.createType(editText.getText().toString());
            }
        });

        alert.show();
    }

    @Override
    public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
        SharedPreferences sharedPreferences = getSharedPreferences("admin", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("type_id", documentSnapshot.getId());
        editor.apply();

        startActivity(new Intent(AdminTypesActivity.this, AdminTypesEditActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}