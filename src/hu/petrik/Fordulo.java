package hu.petrik;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Fordulo {

    private int ev;
    private int het;
    private int forduloAHeten;
    private LocalDate datum;
    private List<Talalat> talalatLista = new ArrayList<>();
    private List<Eredmeny> eredmenyLista = new ArrayList<>();

    public Fordulo()
    {

    }



    public int getEv() {
        return ev;
    }

    public int getHet() {
        return het;
    }

    public int getForduloAHeten() {
        return forduloAHeten;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setEv(int ev) {
        this.ev = ev;
    }

    public void setHet(int het) {
        this.het = het;
    }

    public void setForduloAHeten(int forduloAHeten) {
        this.forduloAHeten = forduloAHeten;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public List<Talalat> getTalalatok()
    {
        return talalatLista;
    }
    public void setTalalatok(List<Talalat> t)
    {
        this.talalatLista.clear();
        for (int i = 0; i < t.size(); i++) {
            this.talalatLista.add(t.get(i));
        }
    }

    public List<Eredmeny> getEredmenyek()
    {
        return eredmenyLista;
    }

    public void setEredmenyek(List<Eredmeny> e)
    {
        this.eredmenyLista.clear();
        for (int i = 0; i < e.size() ; i++) {
            this.eredmenyLista.add(e.get(i));
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < talalatLista.size() ; i++) {
            s += talalatLista.get(i).getTalalatokSzama()+";"+talalatLista.get(i).getNyeremeny()+" Ft";
        }
        String s2 = "";
        for (int i = 0; i < eredmenyLista.size(); i++) {
            switch (eredmenyLista.get(i))
            {
                case _1:
                    s2 += "1;";
                    break;
                case _2:
                    s2 += "2;";
                    break;
                case X:
                    s2 += "X;";
                    break;
            }
        }

        return ev + " " + het + " " + forduloAHeten + " " + datum + " " + s + " " + s2;
    }
}
