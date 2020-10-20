package hu.petrik;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            TotoSzolgaltatas t = new TotoSzolgaltatas("toto.csv");
        System.out.println("A legnagyobb rögzített nyeremény: "+t.legnagyobbNyeremeny());
        System.out.println("Statisztika: \nHazai csapat nyert:" + t.nyeresiArany(Eredmeny._1)+ " %\n" +
                "Vendég csapat nyert: "+t.nyeresiArany(Eredmeny._2)+" %\n" +
                "Döntetlen: "+ t.nyeresiArany(Eredmeny.X)+" %");
        System.out.print("Kérem adjon meg egy dátumot,kötőjellel elválasztva!: ");
        String datum = sc.nextLine();
        System.out.print("Kérem adjon meg egy maximum 14 sorozatból álló tippet!: ");
        String tipp = sc.nextLine();

        List<Eredmeny> eredmenyLista = new ArrayList<>();
        for (int i = 0; i < tipp.length() ; i++) {
            switch (tipp.charAt(i))
            {
                case '1':
                    eredmenyLista.add(Eredmeny._1);
                    break;
                case '2':
                    eredmenyLista.add(Eredmeny._2);
                    break;
                case 'X':
                case 'x':
                    eredmenyLista.add(Eredmeny.X);
                    break;
            }
        }
        String[] d = datum.split("-");
        LocalDate ido = LocalDate.of(Integer.parseInt(d[0]),Integer.parseInt(d[1]),Integer.parseInt(d[2]));
        System.out.println(t.tippeles(ido,eredmenyLista));
    }
}
