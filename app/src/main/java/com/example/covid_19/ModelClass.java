package com.example.covid_19;

public class ModelClass {

    String cases, todayCases, deaths, todayDeaths, recovered, todayRecovered, active, todayActive, country, critical, todayCritical;

    public ModelClass(String cases, String todayCases, String deaths, String todayDeaths, String recovered, String todayRecovered, String active, String todayActive, String country, String critical, String todayCritical) {
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.todayRecovered = todayRecovered;
        this.active = active;
        this.todayActive = todayActive;
        this.country = country;
        this.critical = critical;
        this.todayCritical = todayCritical;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(String todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getTodayRecovered() {
        return todayRecovered;
    }

    public void setTodayRecovered(String todayRecovered) {
        this.todayRecovered = todayRecovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getTodayActive() {
        return todayActive;
    }

    public void setTodayActive(String todayActive) {
        this.todayActive = todayActive;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public String getTodayCritical() {
        return todayCritical;
    }

    public void setTodayCritical(String todayCases) {
        this.todayCritical = todayCritical;
    }
}