package com.ramyfradwan.TheRestaurantApp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int totPr = 0, price, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//      UI Elements Definition

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final EditText num = (EditText)findViewById(R.id.mealNumber);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Starter");
        categories.add("Side Dish");
        categories.add("Main Dish");
        categories.add("Drink");
        categories.add("Dessert");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Context context  ;
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        final TextView totalPrice = (TextView) findViewById(R.id.totalPrice);
        Button order = (Button) findViewById(R.id.order);

//      Order Click
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    number = Integer.parseInt(num.getText().toString());

                    if (num.getText()!= null) {
                        totPr = totPr + number * price;
                        num.setText("");
                        Toast.makeText(getApplicationContext(), "Okay, What else?", Toast.LENGTH_SHORT).show();
                        Log.v("Total Price", "" + totPr);
                    } else {
                        Toast.makeText(getApplicationContext(), "How many?", Toast.LENGTH_SHORT).show();

                    }
                } catch (NumberFormatException e) {
                    Log.e("NumberFormatexception", "" + e);
                }
            }

    });

//      Order Long Click
        order.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                number = Integer.parseInt(num.getText().toString());

                try {
                    if (num.getText() != null) {
//                        totPr = totPr + number * price;
                        totalPrice.setVisibility(View.VISIBLE);
                        totalPrice.setText("Total Price is: " + totPr+ " LE");
                        Log.v("Total Price", "" + totPr);
                        totPr = 0;
                        Toast.makeText(getApplicationContext(), "Thanks Sir!", Toast.LENGTH_SHORT).show();

                    }

                } catch (NumberFormatException e) {
                    Log.e("NumberFormatexception", "" + e);
                }

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        if(item == "Starter" ){price = 15;}
        else if (item == "Side Dish"){price = 20;}
        else if (item == "Main Dish"){price = 45;}
        else if (item == "Drink"){price = 8;}
        else if (item == "Dessert"){price = 20;}

        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        Toast.makeText(parent.getContext(),"Please select a meal",Toast.LENGTH_LONG).show();
    }
}
