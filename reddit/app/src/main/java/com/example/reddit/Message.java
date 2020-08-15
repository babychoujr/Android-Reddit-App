package com.example.reddit;

public class Message {
    private String name;
    private String message;
    private String uid;
    private int score;
    private int upvote;
    private int downvote;
    private Reply r;

    public Message(){
        name = "NA";
        message = "NA";
        //score = 0;
        //upvote = 0;
        //downvote = 0;
    }
    /*
    public Message(String name, String message, String uid, int upvote, int downvote){
        this.name = name;
        this.message = message;
        this.uid = uid;
        this.upvote = upvote;
        this.downvote = downvote;
    }
    */
    public Message(String name, String message, String uid, int upvote, int downvote, int score, Reply r){
        this.name = name;
        this.message = message;
        this.uid = uid;
        this.upvote = upvote;
        this.downvote = downvote;
        this.score = score;
        this.r = r;
    }
    public String getName(){
        return name;
    }

    public String getMessage(){
        return message;
    }

    public String getUid(){
        return uid;
    }

    public int getUpVote(){
        return upvote;
    }

    public int getDownVote(){
        return downvote;
    }

    public Reply getReply(){
        return r;
    }

    public void setReply(Reply r){
        this.r = r;
    }
    public int getScore(){
        score = upvote-downvote;
        return score;
    }

    public void addUpVote(){
        upvote += 1;
    }


    public void addDownVote(){
        downvote += 1;
    }

    public String toString(){
        String results = "";
        results += message + "\n";
        results += "\n";
        results += "- " + name + upvote + downvote + score;
        return results;
    }
}
