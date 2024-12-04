package com.Sentomero.Currency;

public class CurrencyConverter {

    private String fromCurrency;
    private String toCurrency;
    private double currencyAmount;
    private ApiService apiService; // Create an instance of the ApiService

    public CurrencyConverter() {
        this.apiService = new ApiService(); // Initialize the ApiService
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public void setCurrencyAmount(double currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    // Convert the amount using the exchange rate fetched from the API
    public double convertAmount() {
        double exchangeRate = apiService.getExchangeRate(fromCurrency, toCurrency);
        return exchangeRate * currencyAmount;
    }
}
