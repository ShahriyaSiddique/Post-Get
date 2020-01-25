package com.example.postget.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.postget.Interface.ApiInterface;
import com.example.postget.Model.ServerResponse;
import com.example.postget.Model.User;
import com.example.postget.R;
import com.example.postget.Retrofit.RetrofitApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiInterface apiInterface;
    EditText userNameET,passwordET,nameET;
    TextView validityTV,quoteTV;
    Button checkBTN,quoteBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameET = findViewById(R.id.user_name_et);
        passwordET = findViewById(R.id.password_et);
        nameET = findViewById(R.id.name_et);
        quoteTV=findViewById(R.id.quote_tv);
        validityTV=findViewById(R.id.valid_tv);
        checkBTN = findViewById(R.id.check_btn);
        quoteBTN= findViewById(R.id.quote_btn);


        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);

        checkBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });

        quoteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                quote();

            }

        });
    }

    private void quote() {
        String name;

        name = nameET.getText().toString();

        getQuote(name);
    }

    private void getQuote(String name) {

        Call<ServerResponse> call = apiInterface.getQuote(name);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse quoteResponse = response.body();

                if (quoteResponse!=null)
                {
                    quoteTV.setText(quoteResponse.getMessageString());
                }

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                quoteTV.setText(t.getMessage());

            }
        });
    }

    private void validate() {

        String userId;
        String password;
        User user = new User();

        userId = userNameET.getText().toString();
        password = passwordET.getText().toString();

        user.setUserId(userId);
        user.setPassword(password);
        
        checkUserValidity(user);

    }

    private void checkUserValidity(User user) {

        Call<ServerResponse> call = apiInterface.getUserValidity(user);

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                ServerResponse sResponse = response.body();
                if (sResponse!=null)
                {
                    validityTV.setText(sResponse.getMessageString());
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                validityTV.setText(t.getMessage());

            }
        });
    }
}
