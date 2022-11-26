package com.pablo.sideb.service;

import android.os.AsyncTask;

import com.pablo.sideb.model.Cep;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ResponseJSON extends AsyncTask<Void, Void, Cep> {

    private final String cep;

    public ResponseJSON(String cep) {
        this.cep = cep;
    }

    @Override
    protected Cep doInBackground(Void... voids) {
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL("http://viacep.com.br/ws/" + this.cep + "/json/");
            HttpURLConnection connection = (HttpURLConnection)
                    url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.connect();
            Scanner in = new Scanner(url.openStream());
            while (in.hasNext()) {
                response.append(in.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Cep cep = new Cep();

        try {
            JSONObject cepObject = new JSONObject(String.valueOf(response));
            cep.setLongadouro(cepObject.get("logradouro").toString());
            cep.setBairro(cepObject.get("bairro").toString());
            cep.setLocalidade(cepObject.get("localidade").toString());
            cep.setUf(cepObject.get("uf").toString());
            cep.setCep(cepObject.get("cep").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cep;
    }


}
