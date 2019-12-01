package com.example.lab2tehtava3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.Console;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        findViewById (R.id.stockBtn).setOnClickListener (this);

        ListView stockList = findViewById (R.id.osakeLista);

        StockEngine engine = StockApplication.giveEngine ();

        OsakeQuery task = new OsakeQuery ( );






    }

    @Override
    public void onClick(View v) {
        StockEngine engine = StockApplication.giveEngine ();
        Log.d("Nappia painettu", "NAPPIA PAINETTU");

        String resulttia = "";

        Osake lisattava = null;

        if (v.getId() == R.id.stockBtn){

            lisattava = new Osake ();
            try {
                EditText stockId = findViewById (R.id.editTextStockID);

                resulttia = String.valueOf (new OsakeQuery ().execute(stockId.getText ().toString ()).get ());
                Log.d("Resulttia---------", resulttia);

            } catch (ExecutionException e) {
                e.printStackTrace ( );
            } catch (InterruptedException e) {
                e.printStackTrace ( );
            }

        }

        EditText stockName = findViewById (R.id.editTextStockName);
        lisattava.setHinta ( resulttia);
        lisattava.setNimi ( stockName.getText ().toString ());
        engine.lisaaOsake (lisattava);
        updateList();



    }

    private void updateList(){

        ListView stockList = findViewById (R.id.osakeLista);
        ArrayList<String> stockItems = new ArrayList<> ();

        StockEngine engine = StockApplication.giveEngine ();
        for (int i = 0; i < engine.osakkeidenLukumaara (); i++){
            Osake osake = engine.osakeIndeksillä (i);
            stockItems.add(String.valueOf (osake.getNimi () + " " + osake.getHinta ()));
        }

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<> (this,
                android.R.layout.simple_list_item_1,
                stockItems);

        stockList.setAdapter(itemsAdapter);

    }

}
