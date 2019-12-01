package com.example.lab2tehtava3;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

public class OsakeQuery extends AsyncTask<String, Void, String> {






    public static String fromStream(InputStream in) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader (in));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append(newLine);
        }
        return out.toString();
    }

    @Override
    protected String doInBackground(String... strings) {

        String nimi = strings[0];
        String result = "";
        double result1 = 0.0;

        try{
            URL url;
            url = new URL ("https://financialmodelingprep.com/api/company/price/" + nimi +"?datatype=json");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection ( );
            result = fromStream (urlConnection.getInputStream ());
            result1 = parse (result);

            //  Log.d ("testi", "Elegant price:" + betterParse (result));

        } catch (Exception e){
            Log.d("JOKU VIRHE:   ", e.toString ());

        }

        return Double.toString (result1);

    }

    private Double parse(String string){

        String dataString = string;
        double price = 0.0;

        try{
            JSONObject stock = null;
            JSONObject jsonObject = new JSONObject (dataString);
            JSONObject stockElement = jsonObject.getJSONObject ("AAPL");
            double stockPrice = stockElement.getDouble ("price");

            Iterator it = jsonObject.keys();
            while (it.hasNext ()){
                String key = (String) it.next();
                stock = jsonObject.getJSONObject (key);
                Log.d("luento", "Avain löytyi: " + key + " " + stock.getDouble ("price"));
                price = stock.getDouble("price");
            }






            return price;

        }
        catch (Exception e){

        }



        return price;

    }




}
