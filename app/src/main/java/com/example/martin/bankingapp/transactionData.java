package com.example.martin.bankingapp;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;

// This is the object which stores all data of a transfer made in TransferActivity.
@SuppressLint("Registered")
public class transactionData extends AppCompatActivity implements Serializable {
    public String time;
    public String name;
    public String amount;

    // Each object prints out it's own data.
    @Override
    public String toString() {
        return time  + "     |     "   + name + "     |     " +  amount + "     |     " + nowBalance;
    }

    public String nowBalance;

    public transactionData(String t, String n, String a, String nb){
        time= t;
        name = n;
        amount = a;
        nowBalance = nb;
    }
}