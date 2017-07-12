package com.shop.denisa.shop;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.shop.denisa.shop.database.BuyRequests;
import com.shop.denisa.shop.domain.ShoppingItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<ShoppingItem> adapter;
    ListView listView;
    Button addButton;
    List<ShoppingItem> buyRequests;
    BuyRequests buyRequestsDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        addButton = (Button) findViewById(R.id.add);
        buyRequests = new ArrayList<>();
        buyRequestsDb = new BuyRequests(this);
        registerListeners();
        populate();
    }

    private void registerListeners() {

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view;
                String id = textView.getText().toString().split(" ")[0];
                ShoppingItem newItem = new ShoppingItem();
                newItem.setId(Integer.parseInt(id));

                if(isOnline()) {
                    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

                    Retrofit.Builder builder =
                            new Retrofit.Builder()
                                    .baseUrl(ServiceInterface.SERVICE_ENDPOINT)
                                    .addConverterFactory(GsonConverterFactory.create()
                                    );

                    Retrofit retrofit = builder.client(httpClient.build())
                            .build();

                    ServiceInterface service = retrofit.create(ServiceInterface.class);

                    Call<ShoppingItem> call = service.buy(newItem);
                    call.enqueue(new Callback<ShoppingItem>() {
                        @Override
                        public void onResponse(Call<ShoppingItem> call, Response<ShoppingItem> response) {
                            if (response.code() == 404) {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                                alertDialogBuilder.setMessage("Item not found");
                                alertDialogBuilder.setCancelable(false)
                                        .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                            }
                                        });
                                AlertDialog alert = alertDialogBuilder.create();
                                alert.show();
                            } else {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                                alertDialogBuilder.setMessage("Successful!");
                                alertDialogBuilder.setCancelable(false)
                                        .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });
                                AlertDialog alert = alertDialogBuilder.create();
                                alert.show();
                                populate();
                            }
                        }

                        @Override
                        public void onFailure(Call<ShoppingItem> call, Throwable t) {

                        }
                    });
                }else{
                    buyRequests.add(newItem);
                    buyRequestsDb.insertData(newItem.getId(), newItem.getName(), newItem.getQuantity(), newItem.getStatus());
                    populate();
                }

            }
        });
    }

    private void populate() {
        if(!(isOnline())){
            setTitle("Shop(offline)" + " requests:" + buyRequests.size());
        }else {
            setTitle("Shop");
            adapter = new ArrayAdapter<ShoppingItem>(this, R.layout.list_layout, new ArrayList<ShoppingItem>());

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            Retrofit.Builder builder =
                    new Retrofit.Builder()
                            .baseUrl(ServiceInterface.SERVICE_ENDPOINT)
                            .addConverterFactory(GsonConverterFactory.create()
                            );

            Retrofit retrofit = builder.client(httpClient.build())
                    .build();

            ServiceInterface service = retrofit.create(ServiceInterface.class);

            Call<List<ShoppingItem>> call = service.getGoods();
            call.enqueue(new Callback<List<ShoppingItem>>() {
                @Override
                public void onResponse(Call<List<ShoppingItem>> call, Response<List<ShoppingItem>> response) {
                    adapter.addAll(response.body());
                }

                @Override
                public void onFailure(Call<List<ShoppingItem>> call, Throwable t) {

                }
            });


           if(buyRequestsDb.getAllitems().size() != 0){
                for(ShoppingItem newItem : buyRequestsDb.getAllitems()){
                    OkHttpClient.Builder httpClient2 = new OkHttpClient.Builder();

                    Retrofit.Builder builder2 =
                            new Retrofit.Builder()
                                    .baseUrl(ServiceInterface.SERVICE_ENDPOINT)
                                    .addConverterFactory(GsonConverterFactory.create()
                                    );

                    Retrofit retrofit2 = builder2.client(httpClient2.build())
                           .build();

                    ServiceInterface service2 = retrofit2.create(ServiceInterface.class);

                   Call<ShoppingItem> call2 = service2.buy(newItem);
                    call2.enqueue(new Callback<ShoppingItem>() {
                        @Override
                        public void onResponse(Call<ShoppingItem> call, Response<ShoppingItem> response) {
                            if (response.code() == 404) {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                                alertDialogBuilder.setMessage("Item not found");
                                alertDialogBuilder.setCancelable(false)
                                        .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                            }
                                        });
                                AlertDialog alert = alertDialogBuilder.create();
                                alert.show();
                            } else {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                                alertDialogBuilder.setMessage("Successful!");
                                alertDialogBuilder.setCancelable(false)
                                        .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });
                                AlertDialog alert = alertDialogBuilder.create();
                                alert.show();
                                populate();
                            }
                        }

                        @Override
                        public void onFailure(Call<ShoppingItem> call, Throwable t) {

                        }
                    });

                }



            }
            listView.setAdapter(adapter);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        populate();
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
