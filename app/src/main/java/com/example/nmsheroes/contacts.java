package com.example.nmsheroes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class contacts extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;
    TextView no1, no2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(getResources().getColor(R.color.bg));

        no1 = findViewById(R.id.e1);
        no2 = findViewById(R.id.e2);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mFirebaseDatabase.getReference();
        FirebaseUser user= mAuth.getCurrentUser();
        userID=user.getUid();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                showData(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showData(DataSnapshot snapshot) {
        for(DataSnapshot ds: snapshot.getChildren()){
            contactInfo uinfo= new contactInfo();
            uinfo.setContact1(ds.child(userID).getValue(contactInfo.class).getContact1());
            uinfo.setContact2(ds.child(userID).getValue(contactInfo.class).getContact2());

            no1.setText(uinfo.getContact1());
            no2.setText(uinfo.getContact2());

        }
    }



}