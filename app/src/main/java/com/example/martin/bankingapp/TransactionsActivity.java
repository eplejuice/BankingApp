package com.example.martin.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.martin.bankingapp.MainActivity.*;

public class TransactionsActivity<transactionData> extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        Intent i = this.getIntent();
        Bundle bundle = i.getExtras();
        ArrayList<transactionData> transactions = (ArrayList<transactionData>) Objects.requireNonNull(bundle).getParcelableArrayList(ARRAY_LIST);

        ArrayList<String> printOut = new ArrayList<String>();
        assert transactions != null;
        printOut.add(transactions.toString());

        Log.d("TESTTT", printOut.toString());

        /*
        RecyclerView rw = (RecyclerView) findViewById(R.id.lbl_transactions);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, printOut);
        rw.setAdapter(adapter);
        */

    }
}
