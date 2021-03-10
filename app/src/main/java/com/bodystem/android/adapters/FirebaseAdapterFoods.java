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

public class FirebaseAdapterFoods extends FirestorePagingAdapter<FoodsModel, FirebaseAdapterFoods.FoodsModelHolder> {

    OnListItemClick onListItemClick;

    /**
     * Construct a new FirestorePagingAdapter from the given {@link FirestorePagingOptions}.
     *
     * @param options
     */
    public FirebaseAdapterFoods(@NonNull FirestorePagingOptions<FoodsModel> options,
                                OnListItemClick onListItemClick) {
        super(options);
        this.onListItemClick = onListItemClick;
    }

    @Override
    protected void onBindViewHolder(@NonNull FoodsModelHolder foodsModelHolder, int i, @NonNull FoodsModel foodsModel) {
        foodsModelHolder.textViewTitle.setText(foodsModel.getName());
    }

    @NonNull
    @Override
    public FoodsModelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_view_foods, parent, false);
        return new FoodsModelHolder(view);
    }

    public class FoodsModelHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public View view;
        public TextView textViewTitle;
        public MaterialButton editButton;

        public FoodsModelHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            textViewTitle = itemView.findViewById(R.id.textViewListFoods);
            editButton = itemView.findViewById(R.id.buttonGoFoods);

            editButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onListItemClick.onItemClick(getItem(getAdapterPosition()), getAdapterPosition());
        }
    }
}
