package com.example.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginapp.R;

import java.util.Objects;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mEmail = username.getText().toString().trim();
                String mPass = password.getText().toString().trim();

                if(mEmail.isEmpty() || mPass.isEmpty()){
                    username.setError("Can't be empty!");
                    password.setError("Can't be empty!");

                    Toast.makeText(MainActivity.this, "Username / Password cannot be empty!",Toast.LENGTH_SHORT).show();

                }

                if(mPass.length() < 8 || mPass.length() > 15)
                {
                    password.setError("Password must 8 to 15 characters long!");
                }
                else if(!AlphaUPattern.matcher(mPass).find())
                {
                    password.setError("Password must contain one uppercase character!");
                }
                else if(!AlphaLPattern.matcher(mPass).find())
                {
                    password.setError("Password must contain one lowercase character!");
                }
                else if(!SymbolPattern.matcher(mPass).find())
                {
                    password.setError("Password must contain a symbol!");
                }
                else if(!DigitPattern.matcher(mPass).find())
                {
                    password.setError("Password must contain a number!");
                }
                else {
                    String jsonString = "{\n" +
                            "  \"user\": {\n" +
                            "    \"username\": \"Vignesh_S\",\n" +
                            "    \"password\": \"Viki@0011\"\n" +
                            "  }\n" +
                            "}";
                    try {
                        JSONObject obj = new JSONObject(jsonString);
                        if(mEmail.equals(obj.getJSONObject("user").getString("username")) && mPass.equals(obj.getJSONObject("user").getString("password"))) {
                            Intent newIntent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(newIntent);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Username / Password is incorrect!", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                }
        });
    }

    private static final Pattern SymbolPattern =
            Pattern.compile("([@#$&+])");

    private static final Pattern AlphaUPattern =
            Pattern.compile("([A-Z ])");

    private static final Pattern AlphaLPattern =
            Pattern.compile("([a-z ])");

    private static final Pattern DigitPattern =
            Pattern.compile("([0-9 ])");
}





