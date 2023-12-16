package com.example.nmsheroes;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;



public class MainActivity extends AppCompatActivity {

    //DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sheroes-ce0e7-default-rtdb.firebaseio.com");
    EditText input_mail, input_pass;
    Button login_button, signup_button;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @SuppressWarnings("CodeBlock2Expr")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(getResources().getColor(R.color.bg));

        input_mail = findViewById(R.id.user);
        input_pass = findViewById(R.id.passtxt);
        login_button = findViewById(R.id.lgin);
        signup_button= findViewById(R.id.sigin);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        login_button.setOnClickListener(v -> performLogin());

        signup_button.setOnClickListener(v1 -> {
            startActivity(new Intent(MainActivity.this, signup.class));
        });


    }

    private void performLogin(){
        String emails= input_mail.getText().toString();
        String passwords= input_pass.getText().toString();

        if(passwords.isEmpty() || passwords.length()<6 || emails.isEmpty()){
            input_pass.setError("Fill up all the fields properly");
        }else {

            mAuth.signInWithEmailAndPassword(emails, passwords).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(MainActivity.this, dashboard.class);
                    intent.putExtra("email", emails);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Login Successfully Done", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Invalid password or email", Toast.LENGTH_SHORT).show();
                }
//+task.getException()
            });
        }
    }
}