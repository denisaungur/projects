package com.shop.denisa.shop;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shop.denisa.shop.domain.ShoppingItem;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddActivity extends AppCompatActivity {

    EditText name;
    EditText quantity;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name = (EditText) findViewById(R.id.nameInput);
        quantity = (EditText) findViewById(R.id.quantityInput);
        addButton = (Button) findViewById(R.id.addButton);
        addListeners();
    }


    private void addListeners(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ShoppingItem newItem = new ShoppingItem();
                newItem.setName(name.getText().toString());
                newItem.setQuantity(Integer.parseInt(quantity.getText().toString()));
                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

                Retrofit.Builder builder =
                        new Retrofit.Builder()
                                .baseUrl(ServiceInterface.SERVICE_ENDPOINT)
                                .addConverterFactory(GsonConverterFactory.create()
                                );

                Retrofit retrofit = builder.client(httpClient.build())
                        .build();

                ServiceInterface service = retrofit.create(ServiceInterface.class);

                Call<ShoppingItem> call = service.postUser(newItem);
                call.enqueue(new Callback<ShoppingItem>() {
                    @Override
                    public void onResponse(Call<ShoppingItem> call, Response<ShoppingItem> response) {
                        if(response.code() == 404) {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddActivity.this);
                            alertDialogBuilder.setMessage("Item already foud!");
                            alertDialogBuilder.setCancelable(false)
                                    .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                        }
                                    })
                                    .setNegativeButton("Cancel",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                                                    startActivity(intent);
                                                }
                                            });
                            AlertDialog alert = alertDialogBuilder.create();
                            alert.show();
                        }
                        else{
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddActivity.this);
                            alertDialogBuilder.setMessage("Added!");
                            newItem.setId(response.body().getId());
                            newItem.setStatus(response.body().getStatus());
                            alertDialogBuilder.setCancelable(false)
                                    .setPositiveButton("Back", new DialogInterface.OnClickListener(){
                                        public void onClick(DialogInterface dialog, int id){
                                            dialog.cancel();
                                            Intent intent = new Intent(AddActivity.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                            AlertDialog alert = alertDialogBuilder.create();
                            alert.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ShoppingItem> call, Throwable t) {

                    }
                });
            }
        });
    }
}
