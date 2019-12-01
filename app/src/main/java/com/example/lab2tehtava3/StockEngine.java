package com.example.lab2tehtava3;

import java.util.ArrayList;

public class StockEngine {

    private ArrayList<Osake> kaikkiOsakkeet = new ArrayList<> ();

    public void lisaaOsake(Osake osake){

        kaikkiOsakkeet.add(osake);
    }

    public int osakkeidenLukumaara() {

        return kaikkiOsakkeet.size ();
    }

    public Osake osakeIndeksillä(int index){
        if(kaikkiOsakkeet.size () > index){
            return kaikkiOsakkeet.get(index);
        } else {
            return null;
        }
    }

}
