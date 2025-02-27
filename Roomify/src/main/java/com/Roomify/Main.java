package com.Roomify;

import com.Roomify.UI.LoginMenu;

import java.awt.*;
import java.sql.SQLOutput;
import java.time.*;
import java.util.ArrayList;
import java.util.Scanner;
import com.Roomify.UI.Menu;

public class Main {

    private static Menu menu;
    private static Roomify roomify;

    public static void main(String[] args) throws Exception {
        roomify= Roomify.getInstance();
        roomify.populate();
        menu = new LoginMenu();
        while (true){
            menu.menu();
        }

    }
}