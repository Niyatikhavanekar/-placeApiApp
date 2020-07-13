package com.example.place;

import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.google.gson.stream.JsonReader;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

class MainActivity extends AppCompatActivity {
    TextView data;
    Button bt1;
    private String username;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = (TextView) findViewById(R.id.textView);
        bt1 = (Button) findViewById(R.id.bottom);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = data.getText().toString();
                new PlaceOp().execute();
            }

        });
    }

    private class PlaceOp extends AsyncTask<Void, Void, String> {
        private String result;

        @Override
        protected String doInBackground(Void... params) {
            String result = "";
            try {
                URL url = new URL("https://jsonplaceholder.typicode.com/posts");
                String postrequest = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                URLConnection con = url.openConnection();
                con.setDoInput(true);
                OutputStream outputStream = con.getOutputStream();
                outputStream.write(postrequest.getBytes());
                outputStream.flush();
                InputStream stream = con.getInputStream();
                InputStreamReader ireader = new InputStreamReader(stream);
                BufferedReader br = new BufferedReader(ireader);
                String line = "";
                result = "";
                while ((line = br.readLine()) != null) {
                    result = result + line + "\n";
                }
                Gson gson = new Gson();




            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (!result.equals("")) {
                JsonReader reader = new JsonReader(new StringReader(result.trim()));
                reader.setLenient(true);
                JsonElement resultTree = new JsonParser().parse(reader);
                JsonObject responseJsonObject = null;
                if (resultTree.isJsonObject()) {
                    responseJsonObject = resultTree.getAsJsonObject();
                }

                if (responseJsonObject.get("result").getAsBoolean() == true) {
                    responseJsonObject.get("data").getAsJsonArray().get(0).getAsJsonObject().get("user").getAsJsonObject().get("username").getAsCharacter();
                }


            }
        }
    }
}







