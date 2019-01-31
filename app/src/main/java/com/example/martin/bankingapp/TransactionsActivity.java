package com.example.martin.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class TransactionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        Intent i = this.getIntent();
        Bundle bundle = i.getExtras();
        ArrayList<MainActivity.transactionData> transactions = bundle.getSerializable(MainActivity.ARRAY_LIST);


        /*
        RecyclerView rw = (RecyclerView) findViewById(R.id.lbl_transactions);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, transactions);
        rw.setAdapter(adapter);
        */
    }
}
