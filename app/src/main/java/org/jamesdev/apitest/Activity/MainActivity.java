package org.jamesdev.apitest.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.jamesdev.apitest.R;
import org.jamesdev.apitest.models.UserInfo;
import org.jamesdev.apitest.utils.api.service.MainAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText name, age;
    RadioGroup gender_options;
    RadioButton gender;
    Button submit, peek;
    Context context;

    static final String URL = "192.168.1.27/test_api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        submit = (Button) findViewById(R.id.button_submit);
        name = (EditText) findViewById(R.id.name_value);
        age = (EditText) findViewById(R.id.age_value);
        gender_options = (RadioGroup) findViewById(R.id.gender_options);
        peek = (Button) findViewById(R.id.button_peek);

        context = this;


        gender_options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                gender = findViewById(checkedId);
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_value = name.getText().toString();
                int age_value = Integer.parseInt(age.getText().toString());
                String gender_value = gender.getText().toString();

                UserInfo user_data = new UserInfo(name_value, age_value, gender_value);

                MainAPI.userService().addUser(user_data).enqueue(new Callback<UserInfo>() {
                    @Override
                    public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                        makeToast("MY RESPONSE: ");
                    }

                    @Override
                    public void onFailure(Call<UserInfo> call, Throwable t) {
                        makeToast(formatName(name_value, false));
                    }
                });



            }
        });




        peek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<UserInfo>> userInfo = MainAPI.userService().getAllUserInfo();

                userInfo.enqueue(new Callback<List<UserInfo>>() {
                    @Override
                    public void onResponse(Call<List<UserInfo>> call, Response<List<UserInfo>> response) {
                        Log.e("Sucess by me!", response.body().toString());
                        Intent i = new Intent(context, ResultActivity.class);
                        i.putExtra("Result", response.body().toString());
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<List<UserInfo>> call, Throwable t) {
                        Log.e("Fail by me!", t.getLocalizedMessage());
                    }
                });
            }
        });












    }

    public void makeToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public String formatName(String name, boolean success){
        if(success){
            return String.format("Successfully added user: %s", name);
        }
        return String.format("Failed to add user %s", name);
    }





}


