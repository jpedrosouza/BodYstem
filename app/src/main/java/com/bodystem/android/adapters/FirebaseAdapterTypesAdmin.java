package com.bodystem.android.adapters;

import android.util.Log;
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
import com.firebase.ui.firestore.paging.LoadingState;
import com.google.android.material.button.MaterialButton;

public class FirebaseAdapterTypesAdmin extends FirestorePagingAdapter<TypesModel, FirebaseAdapterTypesAdmin.TypesAdminModelHolder> {

    OnListItemClick onListItemClick;

    /**
     * Construct a new FirestorePagingAdapter from the given {@link FirestorePagingOptions}.
     *
     * @param options
     */
    public FirebaseAdapterTypesAdmin(@NonNull FirestorePagingOptions<TypesModel> options,
                                     OnListItemClick onListItemClick) {
        super(options);
        this.onListItemClick = onListItemClick;
    }

    @Override
    protected void onBindViewHolder(@NonNull TypesAdminModelHolder typesAdminModelHolder, int i, @NonNull TypesModel typesModel) {
        typesAdminModelHolder.textViewTitle.setText(typesModel.getName());
    }

    @NonNull
    @Override
    public TypesAdminModelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_view_types_admin, parent, false);
        return new TypesAdminModelHolder(view);
    }

    @Override
    protected void onLoadingStateChanged(@NonNull LoadingState state) {
        super.onLoadingStateChanged(state);
        switch (state) {
            case LOADING_INITIAL:
                Log.d("PAGING_LOG", "Loading initial data");
                break;
            case LOADING_MORE:
                Log.d("PAGING_LOG", "Loading next Page");
                break;
            case FINISHED:
                Log.d("PAGING_LOG", "All data loaded");
                break;
            case ERROR:
                Log.d("PAGING_LOG", "Error loading data");
                break;
            case LOADED:
                Log.d("PAGING_LOG", "Total items loaded: " + getItemCount());
                break;
        }
    }

    public class TypesAdminModelHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public View view;
        public TextView textViewTitle;
        public MaterialButton editButton;

        public TypesAdminModelHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            textViewTitle = itemView.findViewById(R.id.textViewListTypesAdmin);
            editButton = itemView.findViewById(R.id.buttonEditListTypesAdmin);

            editButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onListItemClick.onItemClick(getItem(getAdapterPosition()), getAdapterPosition());
        }
    }
}


