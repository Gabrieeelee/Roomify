package com.Roomify;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Roomify roomify= Roomify.getInstance();
        roomify.inserisciBeB(1,"la dolce mela","un bellissimo beb", "italia", "taormina", "messina", 98034, "non lo so");

        ArrayList<Integer> listcod= new ArrayList();
        listcod.add(1);
        listcod.add(4);
        roomify.inserisciStanza("luxury", 4,111,123,"notti hot",listcod);
        roomify.confermaBeB();
        roomify.inserisciBeB(2,"la dolce melaaaaaa","un bellissimo beb", "italia", "taormina", "messina", 98034, "non lo so");
        roomify.confermaBeB();

    }
}