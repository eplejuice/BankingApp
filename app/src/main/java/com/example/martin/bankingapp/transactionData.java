package com.example.martin.bankingapp;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.Serializable;

@SuppressLint("Registered")
public class transactionData extends AppCompatActivity implements Serializable {
    public String time;
    public String name;
    public String amount;

    @Override
    public String toString() {
        return time  + "     |     "   + name + "     |     " +  amount + "     |     " + nowBalance + "\n\n\n";
    }

    public String nowBalance;

    public transactionData(String t, String n, String a, String nb){
        time= t;
        name = n;
        amount = a;
        nowBalance = nb;
    }
}