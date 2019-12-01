package com.example.lab2tehtava3;

public class StockApplication {

    private static final StockEngine stock = new StockEngine ();

    public static StockEngine giveEngine() {

        return stock;
    }
}
