package com.example.reddit;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends ArrayAdapter<Message> {

    private Context mContext;
    private List<Message> messageList = new ArrayList<Message>();

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private ArrayList<Message> messagelist;

    public ContactAdapter(Context context, ArrayList<Message> list){
        super(context, 0, list);
        mContext = context;
        messageList = list;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.contact_view, parent, false);
        }

        //Individually handles each Message in the messageList
        final Message currentMessage = messageList.get(position);

        //Extracts the name of the Message
        TextView name = (TextView)listItem.findViewById(R.id.textView_Name);
        name.setText(currentMessage.getName());

        //Extracts the message of the Message
        TextView message = (TextView)listItem.findViewById(R.id.textView_Message);
        message.setText(currentMessage.getMessage());

        //Extracts the upvotes

        //final TextView up = (TextView)listItem.findViewById(R.id.textView_Up);
        //up.setText(Integer.toString(currentMessage.getUpVote()));

        //Extracts the downvotes
        final TextView down = (TextView)listItem.findViewById(R.id.textView_Down);
        down.setText(Integer.toString(currentMessage.getDownVote()));

        //Test score
        final TextView score = (TextView)listItem.findViewById(R.id.textView_Score);
        score.setText(Integer.toString(currentMessage.getScore()));

        //Initializes the references to the database and messages
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("messages");
        //final DatabaseReference updateData = myRef.child(currentMessage.getUid());

        /*
        Button addUpVote = (Button)listItem.findViewById(R.id.buttonUpVote);
        addUpVote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                currentMessage.addUpVote();
                updateData.child("upvote").setValue(currentMessage.getUpVote());
                //int s = currentMessage.getUpVote()+ currentMessage.getDownVote();
                //up.setText(Integer.toString(currentMessage.getUpVote()));

            }
        });

        Button addDownVote = (Button)listItem.findViewById(R.id.buttonDownVote);
        addDownVote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                currentMessage.addDownVote();
                updateData.child("downvote").setValue(currentMessage.getDownVote());
                //int s = currentMessage.getUpVote()+ currentMessage.getDownVote();
                //down.setText(Integer.toString(currentMessage.getDownVote()));
            }
        });
        */
        //test
        Button addUpVote = (Button)listItem.findViewById(R.id.buttonUpVote);
        addUpVote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                currentMessage.addUpVote();
                myRef.child(currentMessage.getUid()).child("upVote").setValue(currentMessage.getUpVote());
                //int s = currentMessage.getUpVote()- currentMessage.getDownVote();
                //updateData.child("score").setValue(currentMessage.getScore());
                myRef.child(currentMessage.getUid()).child("score").setValue(currentMessage.getScore());
                score.setText(Integer.toString(currentMessage.getScore()));
                //updateData.child("score").setValue(currentMessage.getScore());
            }
        });

        Button addDownVote = (Button)listItem.findViewById(R.id.buttonDownVote);
        addDownVote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                currentMessage.addDownVote();
                myRef.child(currentMessage.getUid()).child("downVote").setValue(currentMessage.getDownVote());
                //down.setText(Integer.toString(currentMessage.getScore()));
                //int s = currentMessage.getUpVote()- currentMessage.getDownVote();
                //updateData.child("score").setValue(currentMessage.getScore());
                myRef.child(currentMessage.getUid()).child("score").setValue(currentMessage.getScore());
                score.setText(Integer.toString(currentMessage.getScore()));
            }
        });



        Button buttonDelete = (Button) listItem.findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View parentRow = (View)view.getParent();
                ListView listView = (ListView)parentRow.getParent();
                final int position = listView.getPositionForView(parentRow);

                Message selectedItem = (Message)listView.getItemAtPosition(position);
                //Removes that Contact from firebase
                myRef.child(selectedItem.getUid()).removeValue();

                //Removes the Contact from the local data structure
                messageList.remove(selectedItem);
                notifyDataSetChanged();
                //Refreshes the results on the ListView to reflect removal of selected Message



            }
        });

        Button buttonReply = (Button)listItem.findViewById(R.id.buttonReply);
        buttonReply.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(mContext, ReplyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);

            }
        });

        return listItem;
    }



   // public void refresh(View view){

        //ListView results = view.findViewById(R.id.listViewResults);
        //results.setAdapter(listAdapter);
        //listAdapter.clear();
        /*
        if(messageList.isEmpty() != true){
            for (Message m : messageList) {
                listAdapter.add(m);
            }
        }
        */
    //}
    /*
    public void deleteMessage(View view) {
        //Initializes the references to the database and contacts
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("messages");

        //Initializes the local data structures
        messageList = new ArrayList<Message>();

        //Sets up the event listener that will specify what happens when access of a node occurs in the database
        childEventListener = new ChildEventListener(){
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // Adds the Contact to the local data structure
                messageList.add(dataSnapshot.getValue(Message.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

        //Need to add the event listener to the database
        myRef.addChildEventListener(childEventListener);

        //Sets up the list view/list adapter to read from the search results array
        listAdapter = new ContactAdapter(this, messageResults);

    }
    */

}
