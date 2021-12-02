package com.example.covid_19;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    String BASE_URL = "https://disease.sh/v3/covid-19/";
    String BASE_URL2 = "https://disease.sh/v3/covid-19/all";

    @GET("countries")
    Call<List<ModelClass>> getcountryData();

}
