package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.Joker;
import com.example.jokedisplaylibrary.JokeDisplayActivity;


public class MainActivity extends AppCompatActivity implements OnTaskCompleted{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
      /*  Joker joker=new Joker();
       String joke= joker.getJoke();
        Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, JokeDisplayActivity.class);
        intent.putExtra(Constant.JOKE_VALUE,joke);
        startActivity(intent);*/

        new EndpointsAsyncTask(this).execute(new Pair<Context, String>(this, "Manfred"));
    }


    @Override
    public void onTaskCompleted(String result) {

        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        Intent intent=new Intent(this, JokeDisplayActivity.class);
        intent.putExtra(Constant.JOKE_VALUE,result);
        startActivity(intent);

    }
}
