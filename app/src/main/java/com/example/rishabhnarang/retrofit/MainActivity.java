package com.example.rishabhnarang.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //some changes

        final ListView listView = findViewById(R.id.listView);
//        OkHttpClient c = new OkHttpClient();
//        c.interceptors().add(new Interceptor() {
//            @Override
//            public okhttp3.Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                HttpUrl url = request.url().newBuilder()
//                        .addQueryParameter("consumer_key","ck_fef0cba6ad9375d009c7f264f7d2176935d01ab9")
//                        .addQueryParameter("consumer_secret","cs_08ce17479dee8a322534dc1ec5c4459556d1f578")
//                        .build();
//                request = request.newBuilder().url(url).build();
//                return chain.proceed(request);
//            }
//
//        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                //.client(c)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Product>> call= api.getProduct();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product>products =response.body();

                String[] productName = new String[products.size()];

                for (int i = 0; i<products.size(); i++){

                    productName[i] = products.get(i).getName();
                }

                listView.setAdapter(
                        new ArrayAdapter<String>(
                                getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                productName
                        )
                );



            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });



    }
}
