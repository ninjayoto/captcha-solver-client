package com.example;

public class Input {

    String fname, lname, subject;

    public Input(String fname, String lname, String subject) {
        this.fname = fname;
        this.lname = lname;
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Input{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }

}
