package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.ifwaxtel.myandroidlibrary.LibraryJokeActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

class BackendAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private String mResult;
    private ProgressBar mProgressBar;
    private InterstitialAd mInterstitialAd;

    public BackendAsyncTask(ProgressBar progressBar) {
        this.mProgressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0];
        //String name = params[0].second;

        try {
            return myApiService.joke().execute().getData();
        } catch (IOException e) {
            Log.e("BackendAsyncTask",e.getMessage());
            return "";
        }
    }

    @Override
    protected void onPostExecute(final String result) {

        mResult = result;
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
        showActivityJoke();
    }

    private void showActivityJoke(){
        Intent intent = new Intent(context, LibraryJokeActivity.class);
        intent.putExtra(LibraryJokeActivity.JOKE_KEY, mResult);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
