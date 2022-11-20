package com.example.pm01p2ej2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pm01p2ej2_2.Model.JsonPlaceHolderAPI;
import com.example.pm01p2ej2_2.Model.JsonPlaceHolderAPI1;
import com.example.pm01p2ej2_2.Model.Posts;
import com.example.pm01p2ej2_2.Model.Posts1;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mjsonText;
    Button btnapi1,btnapi2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mjsonText = findViewById(R.id.jsonText);
        btnapi1 = findViewById(R.id.btnapi1);
        btnapi2 = findViewById(R.id.btnapi2);

        btnapi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mjsonText.setText("");
                getPosts();
            }
        });
        btnapi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mjsonText.setText("");
                getPosts1();
            }
        });





    }

    private void getPosts(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        Call<List<Posts>> call = jsonPlaceHolderAPI.getPosts();

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {

                if (!response.isSuccessful()){
                    mjsonText.setText("Codigo: "+response.code());
                }

                List<Posts> postsList = response.body();

                for (Posts post: postsList){
                    String content = "";
                    content += "userId:" + post.getUserId() +"\n";
                    content += "id:" + post.getId() +"\n";
                    content += "title:" + post.getTitle() +"\n";
                    content += "body:" + post.getBody() +"\n\n";
                    mjsonText.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                mjsonText.setText(t.getMessage());
            }
        });
    }

    private void getPosts1(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI1 jsonPlaceHolderAPI1 = retrofit.create(JsonPlaceHolderAPI1.class);

        Call<Posts1> call = jsonPlaceHolderAPI1.getPosts1();

        call.enqueue(new Callback<Posts1>() {
            @Override
            public void onResponse(Call<Posts1> call, Response<Posts1> response) {

                String content = "";
                content += "userId:" + response.body().getUserId() +"\n";
                content += "id:" + response.body().getId() +"\n";
                content += "title:" + response.body().getTitle() +"\n";
                content += "body:" + response.body().getBody() +"\n\n";
                mjsonText.append(content);

            }

            @Override
            public void onFailure(Call<Posts1> call, Throwable t) {
                mjsonText.setText(t.getMessage());
            }
        });
    }

}