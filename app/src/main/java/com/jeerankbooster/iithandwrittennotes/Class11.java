package com.jeerankbooster.iithandwrittennotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Class11 extends AppCompatActivity {
    Button math, phy, che;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class11);

        math = findViewById(R.id.mathematicsId);
        phy = findViewById(R.id.physicsId);
        che = findViewById(R.id.chemistryId);

        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Class11.this,Mathematics.class);
                startActivity(intent);
            }
        });

        phy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Class11.this,Physics.class);
                startActivity(intent);
            }
        });

        che.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Class11.this,Chemistry.class);
                startActivity(intent);
            }
        });
    }
}
