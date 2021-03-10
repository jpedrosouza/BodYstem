package com.bodystem.android.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bodystem.android.R;
import com.bodystem.android.interfaces.OnListItemClick;
import com.bodystem.android.models.FoodsModel;
import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class FirebaseAdapterFoodsAdmin extends FirestorePagingAdapter<FoodsModel, FirebaseAdapterFoodsAdmin.FoodsAdminModelHolder> {
    private OnListItemClick onListItemClick;

    /**
     * Construct a new FirestorePagingAdapter from the given {@link FirestorePagingOptions}.
     *
     * @param options
     */
    public FirebaseAdapterFoodsAdmin(@NonNull FirestorePagingOptions<FoodsModel> options,
                                     OnListItemClick onListItemClick) {
        super(options);
        this.onListItemClick = onListItemClick;
    }

    @Override
    protected void onBindViewHolder(@NonNull FoodsAdminModelHolder foodsAdminModelHolder, int i, @NonNull FoodsModel foodsModel) {
        foodsAdminModelHolder.textView.setText(foodsModel.getName());
    }

    @NonNull
    @Override
    public FoodsAdminModelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_view_foods_admin, parent, false);
        return new FoodsAdminModelHolder(view);
    }

    public class FoodsAdminModelHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;
        private MaterialButton deleteButton;

        public FoodsAdminModelHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textViewListFoodsAdmin);
            deleteButton = itemView.findViewById(R.id.buttonDeleteListFoodsAdmin);

            deleteButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onListItemClick.onItemClick(getItem(getAdapterPosition()), getAdapterPosition());
        }
    }
}
