package com.example.reddit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReplyActivity extends AppCompatActivity {

    //Google Firebase Database References
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private Message newMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("messages");
    }

    public void addReply(View view){
        EditText editName = findViewById(R.id.editTextName);
        String name = editName.getText().toString();
        EditText editReply = findViewById(R.id.editTextReply);
        String reply = editReply.getText().toString();

        if(name.length() > 0){
            //String key = myRef.push().getKey(); // Generates a unique random key

            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Message receiveMessage = dataSnapshot.getValue(Message.class);
                    newMessage = receiveMessage;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            Reply r = new Reply(name, reply, newMessage.getUid(), 0, 0, 0);
            newMessage.setReply(r);
            myRef.child(newMessage.getUid()).setValue(newMessage);
            Toast.makeText(this, r.getName() + "'s reply has been successfully added into the database.", Toast.LENGTH_LONG).show();
        }

        //Reset Fields
        editName.setText("");
        editReply.setText("");
    }

    public void goHome( View view )
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
