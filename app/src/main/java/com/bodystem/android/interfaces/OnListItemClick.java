package com.bodystem.android.interfaces;

import com.google.firebase.firestore.DocumentSnapshot;

public interface OnListItemClick {
    void onItemClick(DocumentSnapshot documentSnapshot, int position);
}
