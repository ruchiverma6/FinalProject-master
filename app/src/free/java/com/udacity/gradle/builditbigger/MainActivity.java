package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jokedisplaylibrary.JokeDisplayActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends AppCompatActivity {
    InterstitialAd mInterstitialAd;
    Context context;
    MainActivityFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.addunitid));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                fetchAndDisplayJoke();
            }
        });

        requestNewInterstitial();
    }

    private void fetchAndDisplayJoke() {
        if (null != fragment && fragment.isAdded()) {
            fragment.showProgressBar();
        }
        new EndpointsAsyncTask(new OnTaskCompleted() {
            @Override
            public void onTaskCompleted(String result) {
                if (null != fragment && fragment.isAdded()) {
                    fragment.hideProgressBar();
                }
           navigteToJokeDisplayActivity(result);
            }
        }).execute(new Pair<Context, String>(context, "Joking.."));
    }

    private void navigteToJokeDisplayActivity(String result) {
        Intent intent = new Intent(context, JokeDisplayActivity.class);
        intent.putExtra(Constant.JOKE_VALUE, result);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fragment = (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            fetchAndDisplayJoke();
        }

    }


}
