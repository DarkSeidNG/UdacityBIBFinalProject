package com.ifwaxtel.myandroidlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LibraryJokeActivity extends AppCompatActivity {

    TextView jokeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_joke);

        String joke = getIntent().getStringExtra("joke");

        jokeText = findViewById(R.id.jokeText);

        jokeText.setText(joke);

    }
}
