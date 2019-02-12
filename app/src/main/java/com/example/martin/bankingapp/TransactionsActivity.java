package com.example.martin.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.martin.bankingapp.MainActivity.ARRAY_LIST;

public class TransactionsActivity<transactionData> extends AppCompatActivity {

    // Tbh i dont really understand how recyclerview works yet, i followed a lot of youtube tutorials and it worked. However my code is not by any means best practice.
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

        // Retrives the objects sent from main activity and puts them into an arraylist to be displayed later.
        Intent i = this.getIntent();
        Bundle bundle = i.getExtras();
        ArrayList<transactionData> transactions = (ArrayList<transactionData>) Objects.requireNonNull(bundle).getParcelableArrayList(ARRAY_LIST);

        // Retrieves the data from each of the objects in the arraylist, and puts it into an arraylist of string, to easier adapt into the recyclerview.
        ArrayList<String> printOut = new ArrayList<>();
        assert transactions != null;
        for (int j = 0; j < transactions.size(); j++) {
            printOut.add(transactions.get(j).toString().replace(",", "").replace("[", "").replace("]", "").trim());
        }
        adapter = new RecyclerAdapter(printOut);

        // Long click shows a toast, short click does nothing, almost all of the code for these are in RecylcerAdapter.java.
        adapter.setOnItemClickListener(new RecyclerAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.d("onItemClick", Integer.toString(position));
            }

            @Override
            public void onItemLongClick(int position, View v) {
                Log.d("onItemLongClick", Integer.toString(position));
            }
        });
        rw.setHasFixedSize(true);
        rw.setAdapter(adapter);


    }
}
