package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {
    //declaring all componenets
    private EditText editTextName, editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findViewById returns a reference to the object with the specified id
        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogIn);
        //sets the rquired button to response to long click, otherwise it won't
        buttonLogin.setOnLongClickListener(this);

        SharedPreferences sp = getSharedPreferences("settings",MODE_PRIVATE);
        String email = sp.getString("email","");
        String password = sp.getString("password","");

        if(!email.equals("") && !password.equals("")){
            editTextName.setText(email);
            editTextPassword.setText(password);
        }
    }


    public void login(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        if(!editTextName.getText().toString().equals(""))
        {
            //saving email and password of user in a local file for future use
            //create sp file
            SharedPreferences sp = getSharedPreferences("settings",MODE_PRIVATE);
            //open editor for editing
            SharedPreferences.Editor editor = sp.edit();
            //write the wanted settings
            editor.putString("email", editTextName.getText().toString());
            editor.putString("password", editTextPassword.getText().toString());

            //save and close file
            editor.commit();
            intent.putExtra("name",editTextName.getText().toString());
            startActivity(intent);
        }

    }
    //clears the email and password input on long click by user
    @Override
    public boolean onLongClick(View view) {
        editTextName.setText("");
        editTextPassword.setText("");
        return true;
    }
}