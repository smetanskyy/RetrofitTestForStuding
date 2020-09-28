package com.retrofittestforstudying;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.retrofittestforstudying.models.Currency;
import com.retrofittestforstudying.network.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.textView);

        NetworkService.getInstance()
                .getJSONApi()
                .getAllCurrency()
                .enqueue(new Callback<List<Currency>>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(@NonNull Call<List<Currency>> call, @NonNull Response<List<Currency>> response) {
                        textView.append("\n");
                        List<Currency> currencies = response.body();
                        assert currencies != null;
                        currencies.forEach(currency -> {
                                    textView.append("\nCurrency " + currency.getCcy() + "\n");
                                    textView.append(String.format("BUY: 1 %s -> %s %s\n", currency.getCcy(),
                                            currency.getBuy(), currency.getBase_ccy()));
                                    textView.append(String.format("SALE: 1 %s -> %s %s\n", currency.getCcy(),
                                            currency.getSale(), currency.getBase_ccy()));
                                }
                        );
                        textView.append("\n");
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Currency>> call, @NonNull Throwable t) {

                        textView.append("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });
    }
}