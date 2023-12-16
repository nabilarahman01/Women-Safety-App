package com.example.nmsheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dashboard extends AppCompatActivity {

    Button con,sos1,heno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(getResources().getColor(R.color.bg));
        String mail= getIntent().getStringExtra("email");

        con= findViewById(R.id.btn11);
        sos1= findViewById(R.id.button_sos);
        heno=findViewById(R.id.hlno);

        sos1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this, map.class));
            }
        });

        con.setOnClickListener(v2 -> {
            //String value = t1.getText().toString().trim();
            Intent intent2 = new Intent(dashboard.this, contacts.class);
            intent2.putExtra("email_1",mail);
            startActivity(intent2);
        });

        heno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this, helpline.class));
            }
        });
    }
}