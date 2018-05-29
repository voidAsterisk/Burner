package com.example.user.burner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText pricePerUnitsBox;
    EditText unitsToPriceBox;
    EditText priceToUnitsBox;
    Button unitsToPriceBtn;
    Button priceToUnitsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pricePerUnitsBox = (EditText)findViewById(R.id.pricePerUnitBox);
        unitsToPriceBox = (EditText)findViewById(R.id.unitsToPriceBox);
        priceToUnitsBox = (EditText)findViewById(R.id.priceToUnitsBox);
        unitsToPriceBtn = (Button)findViewById(R.id.unitsToPriceBtn);
        priceToUnitsBtn = (Button)findViewById(R.id.priceToUnitsBtn);

        // Calculate the price of the specified amount of units.
        unitsToPriceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get price per units from text box.
                String s = pricePerUnitsBox.getText().toString();
                double pricePerUnit;
                try {
                    pricePerUnit = Double.parseDouble(s);
                } catch (Exception e)
                {
                    pricePerUnitsBox.requestFocus();
                    getSupportActionBar().setTitle("Invalid input in Price per Unit box.");
                    return;
                }

                // Get amount from text box
                s = unitsToPriceBox.getText().toString();
                double p;
                try {
                    // Get target amount
                    p = Double.parseDouble(s);
                } catch (Exception e)
                {
                    unitsToPriceBox.requestFocus();
                    getSupportActionBar().setTitle("Invalid input in Units to Price box.");
                    return;
                }

                double price = pricePerUnit * p;
                getSupportActionBar().setTitle(Double.toString(p) + " units cost " + Double.toString(price));
            }
        });

        // Calculate the amount of units which can be afforded for the specified price.
        priceToUnitsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get price per units from text box.
                String s = pricePerUnitsBox.getText().toString();
                double pricePerUnit;
                try {
                    pricePerUnit = Double.parseDouble(s);
                } catch (Exception e)
                {
                    pricePerUnitsBox.requestFocus();
                    getSupportActionBar().setTitle("Invalid input in Price per Unit box.");
                    return;
                }

                // Get price from text box
                s = priceToUnitsBox.getText().toString();
                double p;
                try {
                    // Get target price
                    p = Double.parseDouble(s);
                } catch (Exception e)
                {
                    priceToUnitsBox.requestFocus();
                    getSupportActionBar().setTitle("Invalid input in Price to Units box.");
                    return;
                }

                double pricePerUnitsInCents = pricePerUnit * 100.0f;
                double amountOfUnitsForOneCent = 1.0f / pricePerUnitsInCents;
                double unitsForPrice = (p * 100) * amountOfUnitsForOneCent;

                getSupportActionBar().setTitle(Double.toString(unitsForPrice) + " units for " + Double.toString(p));
            }
        });
    }
}
