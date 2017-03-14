package com.example.aa.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Welcomepage extends AppCompatActivity {
Button locationforclient;
    Button Locationfrcompany;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomepage);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        locationforclient = (Button) findViewById(R.id.signupasclient);
        Locationfrcompany = (Button) findViewById(R.id.signupascompany);
        locationforclient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Welcomepage.this, MapsActivityforclient.class);
                startActivity(i);
            }
        });
        Locationfrcompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Welcomepage.this,companylocation.class);
                startActivity(i);
            }
        });

    }
}
