package com.bodystem.android.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bodystem.android.R;
import com.bodystem.android.interfaces.OnListItemClick;
import com.bodystem.android.models.TypesModel;
import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.android.material.button.MaterialButton;

public class FirebaseAdapterTypes extends FirestorePagingAdapter<TypesModel, FirebaseAdapterTypes.TypesModelHolder> {
    OnListItemClick onListItemClick;

    /**
     * Construct a new FirestorePagingAdapter from the given {@link FirestorePagingOptions}.
     *
     * @param options
     */
    public FirebaseAdapterTypes(@NonNull FirestorePagingOptions<TypesModel> options,
                                OnListItemClick onListItemClick) {
        super(options);
        this.onListItemClick = onListItemClick;
    }

    @Override
    protected void onBindViewHolder(@NonNull TypesModelHolder typesModelHolder, int i, @NonNull TypesModel typesModel) {
        typesModelHolder.textViewTitle.setText(typesModel.getName());
    }

    @NonNull
    @Override
    public TypesModelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_view_types, parent, false);
        return new TypesModelHolder(view);
    }


    public class TypesModelHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public View view;
        public TextView textViewTitle;
        public MaterialButton editButton;

        public TypesModelHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            textViewTitle = itemView.findViewById(R.id.textViewListTypes);
            editButton = itemView.findViewById(R.id.buttonGoTypeHome);

            editButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onListItemClick.onItemClick(getItem(getAdapterPosition()), getAdapterPosition());
        }
    }
}
