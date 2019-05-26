package edu.melb.ds.pj2.gui;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class Util {
    private Util() {}

    public static String getResourcePath(String fileName) {
        URL url = Cell.class.getClassLoader().getResource(fileName);
        String path = url.getFile();
        return path.replaceAll("ScrabbleGame.jar!/", "");
    }

    public static List<String> getLetters() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        List<String> result = new LinkedList<>();
        for (int i = 0; i < letters.length(); i++) {
            result.add(letters.charAt(i) + "");
        }
        return result;
    }
}
