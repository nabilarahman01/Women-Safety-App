package com.example.nmsheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class signup extends AppCompatActivity {

    EditText fulltext, age_text, mail_text, password_text, context1, context2;
    Button regBtn;
    //DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sheroes-ce0e7-default-rtdb.firebaseio.com");

    DatabaseReference databaseReference;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(getResources().getColor(R.color.bg));

        fulltext = findViewById(R.id.edit1);
        age_text = findViewById(R.id.edit2);
        mail_text = findViewById(R.id.edit3);
        password_text = findViewById(R.id.edit4);
        context1 = findViewById(R.id.edit5);
        context2 = findViewById(R.id.edit6);
        regBtn= findViewById(R.id.button01);

        databaseReference = FirebaseDatabase.getInstance().getReference("User");

        mAuth=FirebaseAuth.getInstance();
        regBtn.setOnClickListener(v -> {
            String full_name= fulltext.getText().toString();
            String age= age_text.getText().toString();
            String email= mail_text.getText().toString();
            String password= password_text.getText().toString();
            String contact1= context1.getText().toString();
            String contact2= context2.getText().toString();

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(full_name) || TextUtils.isEmpty(age) || TextUtils.isEmpty(password) || TextUtils.isEmpty(contact1) || TextUtils.isEmpty(contact2)){
                Toast.makeText(signup.this, "Please fill up all the fields", Toast.LENGTH_SHORT).show();
            }
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(signup.this, task -> {
                if (task.isSuccessful()){

                    users info = new users(
                            full_name, age, email, password, contact1, contact2
                    );
                    FirebaseDatabase.getInstance().getReference("User").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                            .setValue(info).addOnCompleteListener(task1 -> {
                                Toast.makeText(signup.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),dashboard.class));

                            });

                }
            });

        });



    }

}