package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Statistics extends AppCompatActivity  {

    CountryCodePicker countryCodePicker;
    TextView totalCases, totalDeaths, active, todayDeaths, totalRecovered, todayRecovered, todayCases, critical;
    private List<ModelClass> modelClassList;
    String country;

    ImageView back;
    Button graphButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        back = findViewById(R.id.imageView);
        graphButton = findViewById(R.id.graphButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Statistics.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        graphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://covid19.who.int";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        countryCodePicker = findViewById(R.id.countrtyFlag);
        active = findViewById(R.id.active);
        totalCases = findViewById(R.id.cases);
        todayCases = findViewById(R.id.todayCases);
        todayDeaths = findViewById(R.id.todayDeaths);
        totalDeaths = findViewById(R.id.deaths);
        todayRecovered = findViewById(R.id.todayRecovered);
        totalRecovered = findViewById(R.id.recovered);
        critical = findViewById(R.id.critical);
        modelClassList = new ArrayList<>();

        countryCodePicker.setAutoDetectedCountry(true);
        country = countryCodePicker.getSelectedCountryName(); // get the selected country
        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                country = countryCodePicker.getSelectedCountryName();
                fetchdataData();
            }
        });

        fetchdataData();

        }


    private void fetchdataData() {

        ApiUtilities.getAPIInterface().getcountryData().enqueue(new Callback<List<ModelClass>>() {
            @Override
            public void onResponse(Call<List<ModelClass>> call, Response<List<ModelClass>> response) {
                modelClassList.addAll(response.body());

                for (int i = 0; i < modelClassList.size(); i++) {
                    if (modelClassList.get(i).getCountry().equals(country)) {

                        active.setText((modelClassList.get(i).getActive()));
                        totalCases.setText((modelClassList.get(i).getCases()));
                        totalDeaths.setText((modelClassList.get(i).getDeaths()));
                        totalRecovered.setText((modelClassList.get(i).getRecovered()));
                        critical.setText((modelClassList.get(i).getCritical()));
                        todayCases.setText((modelClassList.get(i).getTodayCases()));
                        todayDeaths.setText((modelClassList.get(i).getTodayDeaths()));
                        todayRecovered.setText((modelClassList.get(i).getTodayRecovered()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ModelClass>> call, Throwable t) {

            }
        });
    }
}
