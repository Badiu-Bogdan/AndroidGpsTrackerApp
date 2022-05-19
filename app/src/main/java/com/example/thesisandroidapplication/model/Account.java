package com.example.thesisandroidapplication.model;

import java.util.ArrayList;

public class Account {
    String text;

/*    public Account(ArrayList<String> newImeiList)
    {
        imeiList = newImeiList;
    }*/

    public Account(String imei)
    {

        text = imei;
    }

    public String getText()
    {
        return text;
    }


/*    public ArrayList<String> getImeiList() {
        return imeiList;
    }*/
}
