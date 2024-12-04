package com.Sentomero.Currency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Currency_Gui extends JFrame {
    private JLabel fromCurrencyLabel, toCurrencyLabel, resultLabel, amountLabel;
    private JTextField amountField;
    private JButton convertButton;
    private JComboBox<String> fromCurrencyComboBox, toCurrencyComboBox;
    private CurrencyConverter currencyConverter;

    // List of supported currencies (you can expand this list as needed)
    private static final String[] CURRENCIES = {"USD","UGX","KES"};

    public Currency_Gui() {
        super("Currency Converter");

        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);  // Using absolute layout for simplicity

        // Initialize components
        fromCurrencyLabel = new JLabel("From Currency:");
        toCurrencyLabel = new JLabel("To Currency:");
        amountLabel = new JLabel("Amount:");
        resultLabel = new JLabel("Result:");
        amountField = new JTextField(20);
        convertButton = new JButton("Convert");

        // JComboBox for currency selection
        fromCurrencyComboBox = new JComboBox<>(CURRENCIES);
        toCurrencyComboBox = new JComboBox<>(CURRENCIES);

        // Position components on the frame
        fromCurrencyLabel.setBounds(20, 20, 100, 25);
        toCurrencyLabel.setBounds(20, 60, 100, 25);
        amountLabel.setBounds(20, 100, 100, 25);
        resultLabel.setBounds(20, 180, 300, 25);

        amountField.setBounds(120, 100, 150, 25);
        fromCurrencyComboBox.setBounds(120, 20, 150, 25);
        toCurrencyComboBox.setBounds(120, 60, 150, 25);
        convertButton.setBounds(120, 140, 100, 25);

        // Add components to the frame
        add(fromCurrencyLabel);
        add(toCurrencyLabel);
        add(amountLabel);
        add(resultLabel);
        add(amountField);
        add(fromCurrencyComboBox);
        add(toCurrencyComboBox);
        add(convertButton);

        // Add action listener to the convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate the input amount and selected currencies
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    String fromCurrency = (String) fromCurrencyComboBox.getSelectedItem();
                    String toCurrency = (String) toCurrencyComboBox.getSelectedItem();

                    // Create an instance of CurrencyConverter and set values
                    currencyConverter = new CurrencyConverter();
                    currencyConverter.setFromCurrency(fromCurrency);
                    currencyConverter.setToCurrency(toCurrency);
                    currencyConverter.setCurrencyAmount(amount);

                    // Perform conversion
                    double convertedAmount = currencyConverter.convertAmount();
                    resultLabel.setText("Result: " + convertedAmount);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Error: Invalid amount entered!");
                }
            }
        });

        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        new Currency_Gui();
    }
}
