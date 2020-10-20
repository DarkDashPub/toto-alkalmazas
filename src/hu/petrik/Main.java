package hu.petrik;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            TotoSzolgaltatas t = new TotoSzolgaltatas("toto.csv");
        System.out.println("A legnagyobb rögzített nyeremény: "+t.legnagyobbNyeremeny());
        System.out.println("Statisztika: \nHazai csapat nyert:);
    }
}
