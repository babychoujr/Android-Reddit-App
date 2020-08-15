package com.example.reddit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    //Google Firebase database reference
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    //Event Listener that listens to each child in the data
    private ChildEventListener childEventListener;

    //Array Adapter allows the results to be displayed in a list on the app
    private ContactAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        //Initializes the references to the database and messages
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("messages");

        //Set up an array that will have the contents that you want to display
        ArrayList<Message> messagesList = new ArrayList<Message>();


        //Sets up the event listener that will specify what happens when access
        //of a node occurs in the database
        childEventListener = new ChildEventListener(){
            @Override
            // Method is run when any new node is added to the database, and once
            // for every existing node when the activity is loaded
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                listAdapter.add( dataSnapshot.getValue(Message.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };

        //Need to add the event listener to the database
        myRef.addChildEventListener(childEventListener);

        //Sets up the list adapter to read from the results array
        listAdapter = new ContactAdapter(this, messagesList);
        ListView results = findViewById(R.id.listViewResults);
        results.setAdapter(listAdapter);

    }

    public void goHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
