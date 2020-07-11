package com.example.place;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

class MainActivity extends AppCompatActivity {
    TextView tv;
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView);

        new PlaceOp().execute();
    }
    @SuppressWarnings("MultipleVariablesInDeclaration")
    private class PlaceOp
        extends Asynctask<Void, Void, String>{
        private String data;

        @Override
                    protected String doInBackground(Void... params){
                String result ="";
                try {
                    URL url = new URL("");
                    InputStream stream = url.openConnection().getInputStream();
                    InputStreamReader ireader = new InputStreamReader(stream);
                    BufferedReader reader = new BufferedReader(ireader);
                    String line = "";
                    data = "";
                    while ((line = reader.readLine()) !=null) {
                        data = data + line + "\n";
                    }
                    Gson gson = new Gson();
                    PlacesApiParser response = gson.fromJson(s, PlacesApiParser.class);
                    String status = response.getStatus();
                    if(status.matches("OK")){
                        List<PlacesApiParser.ResultsEntity> resultList =response.getResults();
                        PlacesApiParser.ResultsEntity results = resultList.get(0);
                        results.get
                    }
                    else
                    {
                        result = "cannot fetch information";
                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return data;
            }
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);

            }

        public void execute() {
        }
    }
}
}
    }
}