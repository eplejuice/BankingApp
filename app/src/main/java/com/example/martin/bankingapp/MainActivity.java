package com.example.martin.bankingapp;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static final String TRANSFER_NAME = "name";
    public static final int RESULT_CODE_TRANSFER = 0;
    public static final String RESPONSE = "main_activity_response";
    public static int balance;
    private TextView lbl_balance;

    public class transactionData {
        String time;
        String name;
        String amount;
        String nowBalance;

        public transactionData(String t, String n, String a, String nb){
            time= t;
            name = n;
            amount = a;
            nowBalance = nb;
        }
        public void writeInfo() {
            Log.d("OUTPUT", time + name + amount + nowBalance);
        }
    }

    ArrayList<transactionData> transactions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.lbl_balance = findViewById(R.id.lbl_balance);
        Random r = new Random();
        balance = r.nextInt(110 - 90) + 90;
        String j = Integer.toString(balance);
        lbl_balance.setText(j);

        Button btn_transactions = findViewById(R.id.btn_transactions);
        btn_transactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //*//
            }
        });

        Button btn_transfer = findViewById(R.id.btn_transfer);
        btn_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent startTransfer = new Intent(MainActivity.this, TransferActivity.class);
                final int balance = Integer.parseInt(lbl_balance.getText().toString());
                startTransfer.putExtra(TRANSFER_NAME, balance);
                startActivityForResult(startTransfer, RESULT_CODE_TRANSFER);

            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_CODE_TRANSFER) {
            if (resultCode == RESULT_OK) { // user has not pressed Back Button
                final int i = data.getIntExtra(RESPONSE, 0);
                lbl_balance.setText(Integer.toString(i));

            }
        }
    }


}
