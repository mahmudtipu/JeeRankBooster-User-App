package com.jeerankbooster.iithandwrittennotes;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import com.jeerankbooster.iithandwrittennotes.Common.Common;

/**
 * Created by Tipu on 3/15/2018.
 */

public class DetailList extends ArrayAdapter<ItemDetail> implements Filterable {
    private Activity context;
    private List<ItemDetail> detailList;
    ImageView itemImage;
    int n=0;


    DatabaseReference cartItem;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    FirebaseUser user;

    String uid;
    String[] cha;

    public DetailList(Activity context, List<ItemDetail> detailList){
        super(context,R.layout.list_layout, detailList);
        this.context = context;
        this.detailList = detailList;
    }

    @Override
    public int getCount() {
        return detailList.size();
    }

    @Override
    public ItemDetail getItem(int position) {
        return detailList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        itemImage = (ImageView) listViewItem.findViewById(R.id.itemImageId);

        final ItemDetail detail = detailList.get(position);
        Picasso.with(getContext())
                .load(detail.getImageLink()).networkPolicy(NetworkPolicy.OFFLINE)
                .into(itemImage, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Picasso.with(getContext())
                                .load(detail.getImageLink())
                                .into(itemImage);
                    }
                });

        return listViewItem;
    }
}
