package com.example.henry.a3_2_consumo_de_servicios_web;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Henry on 22/03/2018.
 */

class GetJson extends AsyncTask<Void,Void,Void>{

    String data = "";
    String dataParsed = "";
    String singleParsed = "";

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            URL url = new URL("https://api.coinmarketcap.com/v1/ticker/?limit=10");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            while (line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONArray JA = new JSONArray(data);
            for(int i =0 ;i <4; i++) {
                JSONObject JO = (JSONObject) JA.get(i);
            singleParsed = "Criptomoneda: "+ JO.get("name")+ "\n"+
                    "Clave: "+ JO.get("symbol")+ "\n"+
                    "Precio Unitario en Dolares: "+ JO.get("price_usd")+ "\n"+
                    "Porcentaje de cambio ultima hora: "+ JO.get("percent_change_1h")+ " \n"+
                    "Porcentaje de cambio las ultimas 24 h: "+ JO.get("percent_change_24h")+ " \n"+
                    "Porcentaje de cambio ultimos 7 dias: "+ JO.get("percent_change_7d")+ " \n";
            dataParsed = dataParsed + singleParsed +"\n";
            }

        } catch (MalformedURLException e ){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }


        return null;
    }

    protected void onPostExecute(Void aVoid){
        super.onPostExecute(aVoid);

        MainActivity.data.setText(this.dataParsed);
    }

}
