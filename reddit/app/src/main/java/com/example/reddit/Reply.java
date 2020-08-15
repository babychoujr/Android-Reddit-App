package com.example.reddit;

public class Reply {
    private String name;
    private String reply;
    private String uid;
    private int score;
    private int upvote;
    private int downvote;
    private Reply r;

    public Reply(){
        name = "NA";
        reply = "NA";
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
    public Reply(String name, String reply, String uid, int upvote, int downvote, int score){
        this.name = name;
        this.reply = reply;
        this.uid = uid;
        this.upvote = upvote;
        this.downvote = downvote;
        this.score = score;
    }
    public String getName(){
        return name;
    }

    public String getReply(){
        return reply;
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
        results += reply + "\n";
        results += "\n";
        results += "- " + name + upvote + downvote + score;
        return results;
    }
}
