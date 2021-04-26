package com.jeerankbooster.iithandwrittennotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    static final int Request_Code = 100;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    Button math, phy;

    TextView youtube, telegram, instagram, facebook, website, shareApp, noteTitle, noteDes;
    FirebaseDatabase database;
    DatabaseReference title,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayoutId);
        toolbar = findViewById(R.id.toolBarId);

        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setTitle(null);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        database = FirebaseDatabase.getInstance();
        title = database.getReference("Note_Title");
        description = database.getReference("Note_Description");

        math = findViewById(R.id.mathematicsId);
        phy = findViewById(R.id.physicsId);

        noteDes = findViewById(R.id.noteDesId);
        noteTitle = findViewById(R.id.noteTitleId);

        youtube = findViewById(R.id.youtubeId);
        youtube.setOnClickListener(this);
        telegram = findViewById(R.id.telegramId);
        telegram.setOnClickListener(this);
        instagram = findViewById(R.id.instagramId);
        instagram.setOnClickListener(this);
        facebook = findViewById(R.id.facebookId);
        facebook.setOnClickListener(this);
        website = findViewById(R.id.websiteId);
        website.setOnClickListener(this);
        shareApp = findViewById(R.id.shareId);
        shareApp.setOnClickListener(this);

        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Class11.class);
                startActivity(intent);
            }
        });

        phy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Class12.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view==youtube)
        {
            youtube.setMovementMethod(LinkMovementMethod.getInstance());
        }
        if(view==telegram)
        {
            telegram.setMovementMethod(LinkMovementMethod.getInstance());
        }
        if(view==instagram)
        {
            instagram.setMovementMethod(LinkMovementMethod.getInstance());
        }
        if(view==facebook)
        {
            facebook.setMovementMethod(LinkMovementMethod.getInstance());
        }
        if(view==website)
        {
            website.setMovementMethod(LinkMovementMethod.getInstance());
        }
        if(view==shareApp)
        {
            Intent intent= new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.jeerankbooster.iithandwrittennotes");
            intent.setType("text/plain");
            startActivityForResult(intent,Request_Code);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        title.child("Title").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueTitle = (String) dataSnapshot.getValue();
                noteTitle.setText(valueTitle);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        description.child("Description").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueDes = (String) dataSnapshot.getValue();
                noteDes.setText(valueDes);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
