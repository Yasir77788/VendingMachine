package com.sg.assignment3.vendingmachine.view;

public interface UserIO {

    void print(String msg);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);


    String readString(String prompt);
}
