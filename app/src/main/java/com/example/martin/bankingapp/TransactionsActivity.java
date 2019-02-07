package com.example.martin.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.martin.bankingapp.MainActivity.*;

public class TransactionsActivity<transactionData> extends AppCompatActivity {

    private RecyclerView rw;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        rw = findViewById(R.id.lbl_transactions);
        layoutManager = new LinearLayoutManager(this);
        rw.setLayoutManager(layoutManager);

        Intent i = this.getIntent();
        Bundle bundle = i.getExtras();
        ArrayList<transactionData> transactions = (ArrayList<transactionData>) Objects.requireNonNull(bundle).getParcelableArrayList(ARRAY_LIST);

        ArrayList<String> printOut = new ArrayList<String>();
        assert transactions != null;
        printOut.add(transactions.toString().replace(",", "").replace("[","").replace("]","").trim());

        adapter = new RecyclerAdapter(printOut);
        rw.setHasFixedSize(true);
        rw.setAdapter(adapter);
    }
}
