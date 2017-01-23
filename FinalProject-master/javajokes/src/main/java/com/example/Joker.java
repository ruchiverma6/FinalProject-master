package com.example;


import java.util.ArrayList;
import java.util.Random;

public class Joker {

    static{
        fillJokes();
    }

    private static ArrayList<String> jokesArrayList;

    private static void fillJokes(){
        jokesArrayList=new ArrayList<>();
        jokesArrayList.add("Laughing on u....");
        jokesArrayList.add("chuckles.....");
        jokesArrayList.add("u r insane...kidding...");
        jokesArrayList.add("rofl...");
    }

    public String getJoke(){
        Random rand = new Random();

        int  n = rand.nextInt(3) + 0;
       return jokesArrayList.get(n);
    }
}
