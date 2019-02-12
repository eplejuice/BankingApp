package com.example.martin.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TransferActivity extends AppCompatActivity {
    float amount;
    private float afterSubtraction;
    String friend;
    public static final String RETURN_INT = "int";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        // The btn_pay should be invisible unless the input by the user is correct.
        final Button btn_pay = findViewById(R.id.btn_pay);
        btn_pay.setEnabled(false);
        final TextView lbl_amount_check = findViewById(R.id.lbl_amount_check);
        lbl_amount_check.setVisibility(View.INVISIBLE);

        // A dropdown to select which friend should get the money, using an arraylist.
        final Spinner dropdown = findViewById(R.id.dropdown_friends);
        String friends[] = new String[]{"Alice", "Bob", "Charlie", "Dawn", "Elvis", "Frode"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, friends);
        dropdown.setAdapter(adapter);

        final EditText txt_amount = findViewById(R.id.txt_amount);

        final Intent i = getIntent();
        final float valueAsInt = i.getFloatExtra(MainActivity.TRANSFER_OBJECT, 0);


        // This funcion listens and checks if the user enters an amount into the txt_amout field.
        txt_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Nothing happens.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Nothing happens.
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Checks if the amount entered was something before trying to parse to prevent null errors.
               if (txt_amount.getText().toString().trim().length() > 0) {
                   float getAmount = Float.parseFloat((txt_amount.getText().toString()));
                   // Times 100 to convert int to float later on.
                   amount = Math.round(getAmount * 100);
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
        // If the input by the user passes all test, the btn_pay becomes clickable.
            btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gets the selected friend frm the list.
                friend = dropdown.getSelectedItem().toString();
                String timeStamp = new SimpleDateFormat("HH.mm.ss").format(new Date());
                final Intent sendBack = new Intent();
                final Bundle bundle = new Bundle();
                // Sends all the input data back to MainActivity to create a new TransactionsData object.
                bundle.putFloat(MainActivity.TRANSFER_NOWBALANCE, (afterSubtraction / 100));
                bundle.putString(MainActivity.TRANSFER_AMOUNT, Float.toString(amount / 100));
                bundle.putString(MainActivity.TRANSFER_NAME, friend);
                bundle.putString(MainActivity.TRANSFER_TIME, timeStamp);
                sendBack.putExtras(bundle);
                // Returns the RESULT_OK code to let MainActivity know everything went ok.
                setResult(RESULT_OK, sendBack);
                finish();
            }
        });
    }
}
