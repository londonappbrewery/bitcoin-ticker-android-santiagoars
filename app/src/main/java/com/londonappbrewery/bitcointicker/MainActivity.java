package com.londonappbrewery.bitcointicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    // Constants:
    // TODO: Create the base URL
    private final String BASE_URL = "https://apiv2.bitcoinaverage.com/indices/global/ticker/BTCUSD";

    // Member Variables:
    TextView mPriceTextView;
    String mCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPriceTextView = (TextView) findViewById(R.id.priceLabel);
        Spinner spinner = (Spinner) findViewById(R.id.currency_spinner);

        // Create an ArrayAdapter using the String array and a spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency_array, R.layout.spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // TODO: Set an OnItemSelected listener on the spinner
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Bitcoin", "" + parent.getItemAtPosition(position));
                letsDoSomeNetworking(parent.getItemAtPosition(position).toString());
                mCurrency = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("Bitcoin", "Nothing was selected");
            }
        });
    }

    // TODO: complete the letsDoSomeNetworking() method
    private void letsDoSomeNetworking(String url) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(BASE_URL, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                Log.d("Bitcoin", "JSON: " + response.toString());
                try {
                    String value = response.getString("ask");
                    currencyExchange(mCurrency, Double.valueOf(value));
                    Log.d("Bitcoin", "" + mCurrency);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("Bitcoin", "Request fail! Status code: " + statusCode);
                Log.d("Bitcoin", "Fail response: " + response);
                Log.e("ERROR", e.toString());

            }
        });


    }

    private void currencyExchange(String currency, double value){
        if(mCurrency.equals("AUD")){
            value *= 1.36;
            value = Math.round(value*100);
            value /= 100;
            mPriceTextView.setText((String.valueOf(value)));
        }else if(mCurrency.equals("BRL")){
            value *= 3.94;
            value = Math.round(value*100);
            value /= 100;
            mPriceTextView.setText((String.valueOf(value)));
        }else if(mCurrency.equals("CAD")){
            value *= 1.31;
            value = Math.round(value*100);
            value /= 100;
            mPriceTextView.setText((String.valueOf(value)));
        }else if(mCurrency.equals("CNY")){
            value *= 6.63;
            value = Math.round(value*100);
            value /= 100;
            mPriceTextView.setText((String.valueOf(value)));
        }else if(mCurrency.equals("EUR")){
            value *= 0.86;
            value = Math.round(value*100);
            value /= 100;
            mPriceTextView.setText((String.valueOf(value)));
        }else if(mCurrency.equals("GBP")){
            value *= 0.76;
            value = Math.round(value*100);
            value /= 100;
            mPriceTextView.setText((String.valueOf(value)));
        }else if(mCurrency.equals("HKD")){
            value *= 7.85;
            value = Math.round(value*100);
            value /= 100;
            mPriceTextView.setText((String.valueOf(value)));
        }else if(mCurrency.equals("JPY")){
            value *= 110.68;
            value = Math.round(value*100);
            value /= 100;
            mPriceTextView.setText((String.valueOf(value)));
        }else if(mCurrency.equals("PLN")){
            value *= 3.73;
            value = Math.round(value*100);
            value /= 100;
            mPriceTextView.setText((String.valueOf(value)));
        }else if(mCurrency.equals("RUB")){
            value *= 63.10;
            value = Math.round(value*100);
            value /= 100;
            mPriceTextView.setText((String.valueOf(value)));
        }else if(mCurrency.equals("SEK")){
            value *= 8.78;
            value = Math.round(value*100);
            value /= 100;
            mPriceTextView.setText((String.valueOf(value)));
        }else if(mCurrency.equals("USD")){
            mPriceTextView.setText((String.valueOf(value)));
        }else if(mCurrency.equals("ZAR")){
            value *= 13.54;
            value = Math.round(value*100);
            value /= 100;
            mPriceTextView.setText((String.valueOf(value)));
        }
    }


}
