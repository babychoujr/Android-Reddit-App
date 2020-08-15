package com.example.reddit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createMessage( View view)
    {
        Intent intent = new Intent( this, MessageActivity.class);
        startActivity(intent);
    }

    public void viewThread( View view)
    {
        Intent intent = new Intent( this, ViewActivity.class);
        startActivity(intent);
    }
}
