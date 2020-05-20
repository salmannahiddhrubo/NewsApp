package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.newsapp.model.Article;
import com.example.newsapp.model.Headlines;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    public  static final String baseUrl ="https://newsapi.org/";
    private Adapter adapter;
    private Button RefreshBtn;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String country = getCountry();
        retrieveJson(country,API_KEY);*/
        intWidget();
        getApi();

        RefreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getApi();
                Toast.makeText(MainActivity.this, "Refreshed", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });
    }


    public void intWidget(){
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RefreshBtn = findViewById(R.id.btn);


    }

    public void getApi(){

        ApiInterface apiInterface  = ApiClient
                .getClient(baseUrl)
                .create(ApiInterface.class);


        apiInterface.getHeadlines()
                .enqueue(new Callback<Headlines>() {
                    @Override
                    public void onResponse(Call<Headlines> call, Response<Headlines> response) {

                        if(response.isSuccessful()) {
                            Headlines headlines = response.body();


                            LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(MainActivity.this);
                            adapter = new Adapter(MainActivity.this,headlines);
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.setAdapter(adapter);

                            Toast.makeText(MainActivity.this, "Succesful: " +headlines.getArticles().size(), Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(MainActivity.this, "Api key experied ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Headlines> call, Throwable t) {
                        Log.e("onFailure: ",t.getLocalizedMessage());
                    }

                });

    }



  /* public void retrieveJson(String country, String apiKey){
        Call<Headlines> call = ApiClient.getInstance().getApi().getHeadlines(country,apiKey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if(response.isSuccessful()&& response.body().getArticles()!=null){
                    articles.clear();
                    articles = response.body().getArticles();

                    adapter = new Adapter(MainActivity.this,articles);
                    recyclerView.setAdapter(adapter);
                    Toast.makeText(MainActivity.this, ""+articles.size(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
   }
    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }*/
}
