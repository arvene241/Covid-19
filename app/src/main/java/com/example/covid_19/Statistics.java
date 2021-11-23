package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Statistics extends AppCompatActivity {

    CountryCodePicker countryCodePicker;
    TextView totalActive, totalCases, totalDeaths, todayActive, todayDeaths, totalRecovered, todayRecovered, todayCases, critical, todayCritical;
    //    String[] types = {"cases", "deaths", "recovered", "active", "critical"};
    private List<ModelClass> modelClassList;
    //    private List<ModelClass> modelClassList2;
    String country;
    Spinner spinner;
//    private RecyclerView recyclerView;

    ImageView back;
    Button buttonTotal, buttonToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        back = findViewById(R.id.imageView);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Statistics.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        countryCodePicker = findViewById(R.id.country);
        todayActive = findViewById(R.id.active);
        totalActive = findViewById(R.id.active);
        totalCases = findViewById(R.id.cases);
        todayCases = findViewById(R.id.cases);
        todayDeaths = findViewById(R.id.deaths);
        totalDeaths = findViewById(R.id.deaths);
        todayRecovered = findViewById(R.id.recovered);
        totalRecovered = findViewById(R.id.recovered);
        critical = findViewById(R.id.critical);
        todayCritical = findViewById(R.id.critical);
        modelClassList = new ArrayList<>();
//        modelClassList2 = new ArrayList<>();
        buttonTotal = findViewById(R.id.total);
        buttonToday = findViewById(R.id.today);

        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this); // spinner selected country
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);

        countryCodePicker.setAutoDetectedCountry(true);
        country = countryCodePicker.getSelectedCountryName(); // get the selected country
        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                country = countryCodePicker.getDefaultCountryName();
                fetchdataTotal();
            }
        });

        fetchdataTotal();

        buttonTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchdataTotal();
            }
        });

        buttonTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchdataToday();
            }
        });

        }


    private void fetchdataTotal() {

        ApiUtilities.getAPIInterface().getcountryData().enqueue(new Callback<List<ModelClass>>() {
            @Override
            public void onResponse(Call<List<ModelClass>> call, Response<List<ModelClass>> response) {
                modelClassList.addAll(response.body());

                for (int i = 0; i < modelClassList.size(); i++) {
                    if (modelClassList.get(i).getCountry().equals(country)) {

                        totalActive.setText((modelClassList.get(i).getActive()));
                        totalCases.setText((modelClassList.get(i).getCases()));
                        totalDeaths.setText((modelClassList.get(i).getDeaths()));
                        totalRecovered.setText((modelClassList.get(i).getRecovered()));
                        critical.setText((modelClassList.get(i).getCritical()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ModelClass>> call, Throwable t) {

            }
        });
    }

    private void fetchdataToday() {

        ApiUtilities.getAPIInterface().getcountryData().enqueue(new Callback<List<ModelClass>>() {
            @Override
            public void onResponse(Call<List<ModelClass>> call, Response<List<ModelClass>> response) {
                modelClassList.addAll(response.body());

                for (int i = 0; i < modelClassList.size(); i++) {
                    if (modelClassList.get(i).getCountry().equals(country)) {

                        todayActive.setText((modelClassList.get(i).getTodayActive()));
                        todayCases.setText((modelClassList.get(i).getTodayCases()));
                        todayDeaths.setText((modelClassList.get(i).getTodayDeaths()));
                        todayRecovered.setText((modelClassList.get(i).getTodayRecovered()));
                        todayCritical.setText((modelClassList.get(i).getTodayCritical()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ModelClass>> call, Throwable t) {

            }
        });
    }
}
