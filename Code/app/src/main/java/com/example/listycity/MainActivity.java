package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private ListView cityList;
    private ArrayAdapter<String> cityAdapter;
    private ArrayList<String> dataList;
    private Button btnAddCity, btnDeleteCity, btnConfirmAdd;
    private EditText etNewCity;
    private LinearLayout addSection;
    private int selectedPos = ListView.INVALID_POSITION;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1) findViewById
        cityList = findViewById(R.id.city_list);
        btnAddCity = findViewById(R.id.btnAddCity);
        btnDeleteCity = findViewById(R.id.btnDeleteCity);
        btnConfirmAdd = findViewById(R.id.btnConfirmAdd);
        etNewCity = findViewById(R.id.etNewCity);
        addSection = findViewById(R.id.addSection);

        String []cities = {"Edmonton","Vancouver","Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPos = position;
            cityList.setItemChecked(position, true);
        });

        btnAddCity.setOnClickListener(v -> {
            addSection.setVisibility(View.VISIBLE);
            etNewCity.requestFocus();
        });


        btnConfirmAdd.setOnClickListener(v -> {
            String newCity = etNewCity.getText().toString().trim();

            if (newCity.isEmpty()) {
                Toast.makeText(this, "Please enter a city's name", Toast.LENGTH_SHORT).show();
                return;
            }

            dataList.add(newCity);
            cityAdapter.notifyDataSetChanged();

            etNewCity.setText("");
            addSection.setVisibility(View.GONE);
        });
        btnDeleteCity.setOnClickListener(v -> {
            if (selectedPos == ListView.INVALID_POSITION) {
                Toast.makeText(this, "Tap a city first to select it", Toast.LENGTH_SHORT).show();
                return;
            }

            dataList.remove(selectedPos);
            cityAdapter.notifyDataSetChanged();
            cityList.clearChoices();
            selectedPos = ListView.INVALID_POSITION;
        });


    }
}