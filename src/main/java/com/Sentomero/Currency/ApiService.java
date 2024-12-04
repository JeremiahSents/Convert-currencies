package com.Sentomero.Currency;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class ApiService {

    private static final String API_KEY = "0ddb7d0d6fd9f1d12a5ed55b";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    // Method to fetch the exchange rate from the API
    public double getExchangeRate(String fromCurrency, String toCurrency) {
        OkHttpClient client = new OkHttpClient();
        String url = API_URL + fromCurrency;

        // Request to the API
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                // Parse the response body to get the exchange rate
                String responseBody = response.body().string();
                return parseExchangeRate(responseBody, toCurrency);
            } else {
                System.out.println("Failed to fetch exchange rate");
                return 0.0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    // Method to parse the exchange rate from the JSON response
    private double parseExchangeRate(String responseBody, String toCurrency) {
        JSONObject jsonResponse = new JSONObject(responseBody);

        if (jsonResponse.getString("result").equals("success")) {
            JSONObject conversionRates = jsonResponse.getJSONObject("conversion_rates");
            if (conversionRates.has(toCurrency)) {
                return conversionRates.getDouble(toCurrency);
            } else {
                System.out.println("Currency not available in the rates");
                return 0.0;
            }
        } else {
            System.out.println("API Response Error");
            return 0.0;
        }
    }
}
