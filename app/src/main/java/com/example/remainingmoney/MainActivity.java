package com.example.remainingmoney;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView remainingDays;
    private TextView averageValue;
    private TextView averageValue2;
    private TextView averageValue3;
    private EditText publicTransportPrice;
    private EditText balance;

    private int remainingDaysInt;
    private int averageValueInt;
    private int averageValue2Int;
    private int averageValue3Int;
    private int publicTransportPriceInt;
    private int balanceInt;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setData();

        publicTransportPrice.addTextChangedListener(new MyTextWatcher());
        balance.addTextChangedListener(new MyTextWatcher());
    }

    public class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }


        @RequiresApi(api = Build.VERSION_CODES.O)
        public void afterTextChanged(Editable editable) {
            setData();
        }
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setData() {
        String a = String.valueOf(publicTransportPrice.getText());
        String b = String.valueOf(balance.getText());
        if (a == null || a.length() == 0) {
            publicTransportPriceInt = 0;
        } else {
            publicTransportPriceInt = Integer.parseInt(a);
        }
        if (b == null || b.length() == 0) {
            balanceInt = 0;
        } else {
            balanceInt = Integer.parseInt(b);
        }

        remainingDaysInt = Days.getRemainingDays();
        averageValueInt = (int) balanceInt / Days.getRemainingDays();
        averageValue2Int = (int) (balanceInt - Days.getTravelDays() * publicTransportPriceInt * 2) / Days.getRemainingDays();
        averageValue3Int = (int) (balanceInt - Days.getTravelDays() * publicTransportPriceInt * 4) / Days.getRemainingDays();

        if (averageValueInt < 0) {
            averageValueInt = 0;
        }
        if (averageValue2Int < 0) {
            averageValue2Int = 0;
        }
        if (averageValue3Int < 0) {
            averageValue3Int = 0;
        }

        remainingDays.setText("Осталось дней: " + remainingDaysInt);
        averageValue.setText("В среднем: " + averageValueInt);
        averageValue2.setText("С учётом 2 проездов: " + averageValue2Int);
        averageValue3.setText("С учётом 4 проездов: " + averageValue3Int);
    }

    private void init() {
        remainingDays = (TextView) findViewById(R.id.remainingDays);
        averageValue = (TextView) findViewById(R.id.averageValue);
        averageValue2 = (TextView) findViewById(R.id.averageValue2);
        averageValue3 = (TextView) findViewById(R.id.averageValue3);
        publicTransportPrice = (EditText) findViewById(R.id.publicTransportPrice);
        balance = (EditText) findViewById(R.id.balance);
    }
}