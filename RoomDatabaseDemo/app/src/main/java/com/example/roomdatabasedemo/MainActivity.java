package com.example.roomdatabasedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Initialize variable
    EditText countryText;
    EditText popText;
    EditText capitalText;
    Button btAdd, btReset;
    RecyclerView recyclerView;
    List<Country> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variable

        countryText = findViewById(R.id.countryInput);
        popText = findViewById(R.id.populationInput);
        capitalText = findViewById(R.id.capitalInput);
        btAdd = findViewById(R.id.bt_add);
        btReset = findViewById(R.id.bt_reset);
        recyclerView = findViewById(R.id.recycler_view);

        // initialize database
        database = RoomDB.getInstance(this);

        // store database value in data list

        dataList = database.mainDao().getAll();

        //Initialize linear layout manager
        linearLayoutManager = new LinearLayoutManager(this);

        // Set layout manager
        recyclerView.setLayoutManager(linearLayoutManager);

        // Initialize adapter
        adapter = new MainAdapter(MainActivity.this, dataList);

        // set adapter

        recyclerView.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get string from edit text
                String sName = countryText.getText().toString().trim();
                int sPop = Integer.parseInt(String.valueOf(popText.getText()));
                String sCap = capitalText.getText().toString().trim();

                // check condition
                if (!sName.equals("") && !sCap.equals("") && sPop > 0) {
                    // when text is not empty
                    // initialize main data
                    Country country = new Country();
                    //Set text on main data
                    country.setName(sName);
                    country.setCapital(sCap);
                    country.setPopulation(sPop);

                    //Insert text in database
                    database.mainDao().insert(country);

                    //Clear edit text
                    countryText.setText("");
                    capitalText.setText("");
                    popText.setText("");

                    //Notify when data is inserted
                    dataList.clear();
                    dataList.addAll(database.mainDao().getAll());
                    adapter.notifyDataSetChanged();

                }
            }
        });
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.mainDao().reset(dataList);
                dataList.clear();
                dataList.addAll(database.mainDao().getAll());
                adapter.notifyDataSetChanged();
            }
        });
    }
}
