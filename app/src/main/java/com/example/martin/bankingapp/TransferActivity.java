package com.example.martin.bankingapp;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TransferActivity extends AppCompatActivity {
    int amount;
    private float afterSubtraction;
    String friend;
    public static final String RETURN_INT = "int";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        final Button btn_pay = findViewById(R.id.btn_pay);
        btn_pay.setEnabled(false);
        final TextView lbl_amount_check = findViewById(R.id.lbl_amount_check);
        lbl_amount_check.setVisibility(View.INVISIBLE);

        final Spinner dropdown = findViewById(R.id.dropdown_friends);
        String friends[] = new String[]{"Alice", "Bob", "Charlie", "Dawn", "Elvis", "Frode"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, friends);
        dropdown.setAdapter(adapter);

        final EditText txt_amount = findViewById(R.id.txt_amount);

        final Intent i = getIntent();
        final int valueAsInt = i.getIntExtra(MainActivity.TRANSFER_OBJECT, 0);


        txt_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
               if (txt_amount.getText().toString().trim().length() > 0) {
                   float getAmount = Float.parseFloat((txt_amount.getText().toString()));
                   amount = Math.round(getAmount * 100);
                   // amount = Integer.parseInt(txt_amount.getText().toString());

                   if (amount == 0) {
                       btn_pay.setEnabled(false);
                       lbl_amount_check.setVisibility(View.VISIBLE);
                       lbl_amount_check.setText("ERROR, VALUE CAN NOT BE ZERO");
                   } else if (amount > (valueAsInt * 100)) {
                       btn_pay.setEnabled(false);
                       lbl_amount_check.setVisibility(View.VISIBLE);
                       lbl_amount_check.setText("ERROR, AMOUNT OUT OF BOUNDS");
                   } else {
                       lbl_amount_check.setVisibility(View.INVISIBLE);
                       afterSubtraction = (valueAsInt *100) - amount;
                       btn_pay.setEnabled(true);
                   }

               }
               else {
                   lbl_amount_check.setVisibility(View.INVISIBLE);
                   btn_pay.setEnabled(false);
               }

            }
        });

            btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friend = dropdown.getSelectedItem().toString();
                String timeStamp = new SimpleDateFormat("HH.mm.ss").format(new Date());
                final Intent sendBack = new Intent();
                final Bundle bundle = new Bundle();
                bundle.putFloat(MainActivity.TRANSFER_NOWBALANCE, (afterSubtraction / 100));
                bundle.putString(MainActivity.TRANSFER_AMOUNT, Integer.toString(amount));
                bundle.putString(MainActivity.TRANSFER_NAME, friend);
                bundle.putString(MainActivity.TRANSFER_TIME, timeStamp);
                sendBack.putExtras(bundle);
                setResult(RESULT_OK, sendBack);
                finish();
            }
        });
    }
}
