package com.ifwaxtel.myandroidlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LibraryJokeActivity extends AppCompatActivity {

    public static final String JOKE_KEY = "joke";
    TextView jokeText;
    String joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_joke);

        Intent intent = getIntent();
        if (intent.hasExtra(JOKE_KEY) ){
            joke = getIntent().getStringExtra(JOKE_KEY);
        }

        jokeText = findViewById(R.id.jokeText);

        jokeText.setText(joke);

    }
}
