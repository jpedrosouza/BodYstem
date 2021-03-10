package com.bodystem.android.activitys.admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import com.bodystem.android.R;
import com.bodystem.android.adapters.FirebaseAdapterFoodsAdmin;
import com.bodystem.android.controllers.FoodsController;
import com.bodystem.android.controllers.TypesController;
import com.bodystem.android.interfaces.OnListItemClick;
import com.bodystem.android.models.FoodsModel;
import com.bodystem.android.models.TypesModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class AdminTypesEditActivity extends AppCompatActivity implements OnListItemClick {
    TypesController typesController = new TypesController();
    FoodsController foodsController = new FoodsController();
    SharedPreferences sharedPreferences;

    TypesModel typesModel = new TypesModel();

    MaterialToolbar toolbar;
    TextInputEditText inputEditTextTypeName;
    RecyclerView recyclerView;

    FirebaseAdapterFoodsAdmin adapterFoodsAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_types_edit);

        sharedPreferences = getSharedPreferences("admin", MODE_PRIVATE);

        toolbar = findViewById(R.id.topAppBarAdminTypesEdit);
        inputEditTextTypeName = findViewById(R.id.textInputTypeNameAdmin);
        recyclerView = findViewById(R.id.recyclerViewTypesEditAdmin);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminTypesEditActivity.this, AdminTypesActivity.class));
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.deleteTypeAdmin:
                        typesController.deleteType(sharedPreferences.getString("type_id",
                                null));

                        startActivity(new Intent(AdminTypesEditActivity.this, AdminTypesActivity.class));
                        break;
                    case R.id.addFoodAdmin:
                        createAlertDialog();
                        break;
                    case R.id.confirmTypeAdmin:
                        typesController.updateTypeName(inputEditTextTypeName.getText().toString(),
                                sharedPreferences.getString("type_id", null));

                        startActivity(new Intent(AdminTypesEditActivity.this, AdminTypesActivity.class));
                        break;
                }
                return false;
            }
        });

        createRecyclerView();
    }

    void createRecyclerView() {
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

        adapterFoodsAdmin = new FirebaseAdapterFoodsAdmin(options, this);

        adapterFoodsAdmin.startListening();

        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterFoodsAdmin);
    }

    public void createAlertDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.layout_alert_dialog_add_food, null);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setView(view);

        final EditText editTextName = view.findViewById(R.id.editTextAlertDialogNameFoodAdmin);
        final EditText editTextCalories = view.findViewById(R.id.editTextAlertDialogCaloriesFoodAdmin);

        alert.setPositiveButton("Criar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               foodsController.createFood(editTextName.getText().toString(),
                       sharedPreferences.getString("type_id", null),
                       Double.parseDouble(editTextCalories.getText().toString()));
            }
        });

        AlertDialog alertDialog = alert.create();

        alertDialog.show();
    }

    @Override
    public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
        foodsController.deleteFood(documentSnapshot.getId());

        Toast.makeText(this, "Comida deletada com sucesso", Toast.LENGTH_SHORT).show();
    }
}