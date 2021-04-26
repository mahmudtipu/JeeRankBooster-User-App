package com.jeerankbooster.iithandwrittennotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.jeerankbooster.iithandwrittennotes.Common.Common.categoryId;

public class Start11 extends AppCompatActivity {

    List<ItemDetail> detailList;
    FirebaseDatabase database;
    DatabaseReference mathImages;
    String foodName, foodImage, foodPrice, foodDescription, wsRate, av;
    DetailList adapter;
    ItemDetail detail;
    ListView listViewFoodItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start11);

        database = FirebaseDatabase.getInstance();
        mathImages = database.getReference("Math_Content12");
        mathImages.keepSynced(true);

        detailList = new ArrayList<>();

        listViewFoodItem = (ListView) findViewById(R.id.listBuyerViewId);

        mathImages.orderByChild("CategoryId").equalTo(categoryId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        detailList.clear();

                        for(DataSnapshot detailSnapshot: dataSnapshot.getChildren()){
                            detail = detailSnapshot.getValue(ItemDetail.class);

                            detailList.add(detail);
                        }

                        adapter = new DetailList(Start11.this, detailList);

                        listViewFoodItem.setAdapter(adapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}

