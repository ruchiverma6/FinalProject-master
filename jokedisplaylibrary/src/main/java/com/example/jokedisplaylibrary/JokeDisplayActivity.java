package com.example.jokedisplaylibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {
private TextView mJokeTextView;
    private String jokeValue;
    private final String JOKE_RETRIEVAL_VAL="jokevalue";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
        jokeValue=getIntent().getStringExtra(JOKE_RETRIEVAL_VAL);
        initComponents();
    }

    private void initComponents() {
        mJokeTextView=(TextView)findViewById(R.id.joke_text_view);
        if(null!=jokeValue) {
            mJokeTextView.setText(jokeValue);
        }
    }
}
