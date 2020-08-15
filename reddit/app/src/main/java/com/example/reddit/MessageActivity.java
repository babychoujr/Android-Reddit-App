package com.example.reddit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MessageActivity extends AppCompatActivity {

    //Google Firebase Database References
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private Reply r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        //Initializes the references to the database and contacts

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("messages");

    }

    public void addMessage(View view){
        EditText editName = findViewById(R.id.editTextName);
        String name = editName.getText().toString();
        EditText editMessage = findViewById(R.id.editTextMessage);
        String message = editMessage.getText().toString();

        if(name.length() > 0){
            String key = myRef.push().getKey(); // Generates a unique random key
            Message m = new Message(name, message, key, 0, 0, 0, r);
            myRef.child(key).setValue(m);
            Toast.makeText(this, m.getName() + "'s message has been successfully added into the database.", Toast.LENGTH_LONG).show();
        }

        //Reset Fields
        editName.setText("");
        editMessage.setText("");
    }

    public void goHome( View view )
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
