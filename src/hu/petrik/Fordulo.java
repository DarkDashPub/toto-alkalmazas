package hu.petrik;

import java.time.LocalDate;

public class Fordulo {

    private int ev;
    private int het;
    private int forduloAHeten;
    private LocalDate datum;

    public Fordulo(int ev,int het,int forduloAHeten,LocalDate datum)
    {
        this.ev = ev;
        this.het = het;
        this.forduloAHeten = forduloAHeten;
        this.datum = datum;
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
}
