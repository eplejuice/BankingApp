package com.example.martin.bankingapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Variables to keep track of values sent as intent.
    public static final String TRANSFER_OBJECT = "obj";
    public static final int RESULT_CODE_TRANSFER = 0;
    public static final String TRANSFER_NOWBALANCE = "now_balance";
    public static final String TRANSFER_AMOUNT = "amount";
    public static final String TRANSFER_NAME = "name";
    public static final String TRANSFER_TIME = "time";
    public static final String ARRAY_LIST = "arr";

    public static int balance;
    private TextView lbl_balance;

    // An in-memory database in the form of an array list containing objects to contain the data of all transactions.
    ArrayList<transactionData> transactions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Generates a random balance every time the app is restarted.
        this.lbl_balance = findViewById(R.id.lbl_balance);
        Random r = new Random();
        balance = r.nextInt(110 - 90) + 90;
        String j = Integer.toString(balance);
        lbl_balance.setText(j);

        // The function for starting the transactions activity, bound to the lbl_transactions button.
        Button btn_transactions = findViewById(R.id.btn_transactions);
        btn_transactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent startTransactions = new Intent(MainActivity.this, TransactionsActivity.class);
                Bundle bundle = new Bundle();
                // Sends the arraylist as a bundle using serializable, easier to retrieve on the other side.
                bundle.putSerializable(ARRAY_LIST, transactions);
                startTransactions.putExtras(bundle);
                startActivity(startTransactions);
            }
        });

        // The function which starts the transfer activity.
        Button btn_transfer = findViewById(R.id.btn_transfer);
        btn_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent startTransfer = new Intent(MainActivity.this, TransferActivity.class);
                // The function always gets its balance from the textView also shown to the user on screen.
                final float balance = Float.parseFloat(lbl_balance.getText().toString());
                startTransfer.putExtra(TRANSFER_OBJECT, balance);
                startActivityForResult(startTransfer, RESULT_CODE_TRANSFER);

            }
        });
    }

    // Retrieves the data from the transfer activity, updates the balance and creates a new transactions object based on data retrieved.
    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_CODE_TRANSFER) {
            if (resultCode == RESULT_OK) {
                final float i = data.getFloatExtra(TRANSFER_NOWBALANCE, 0);
                lbl_balance.setText(Float.toString(i));
                transactionData t = new transactionData(data.getStringExtra(TRANSFER_TIME), data.getStringExtra(TRANSFER_NAME), data.getStringExtra(TRANSFER_AMOUNT ), Float.toString(i));
                transactions.add(t);
            }
        }
    }


}
