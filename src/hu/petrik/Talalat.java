package hu.petrik;

public class Talalat extends Fordulo {

    private int talalatokSzama;
    private int nyeremeny;

    public Talalat(int talalatokSzama, int nyeremeny)
    {
       this.talalatokSzama = talalatokSzama;
       this.nyeremeny = nyeremeny;
    }

    public int getTalalatokSzama() {
        return talalatokSzama;
    }
    
    public int getNyeremeny() {
        return nyeremeny;
    }
}
