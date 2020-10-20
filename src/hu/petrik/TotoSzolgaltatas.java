package hu.petrik;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TotoSzolgaltatas {

    public List<Fordulo> forduloLista;

    public TotoSzolgaltatas(String fajlNev)
    {
        forduloLista = new ArrayList<>();
        Scanner s = new Scanner(System.in);

        try
        {
            s = new Scanner(new File(fajlNev));
        } catch( Exception x)
        {
            System.out.println(x);
        }

        while(s.hasNext())
        {
            String sor = s.nextLine();
            String[] adatok = sor.split(";");
            Fordulo f = new Fordulo();
            f.setEv(Integer.parseInt(adatok[0]));
            f.setHet(Integer.parseInt(adatok[1]));

            int fordulo = 0;
            if(!adatok[2].equals(""))
            {
                fordulo = Integer.parseInt(adatok[2]);
            }
            else
                {
                    fordulo = 1;
                }

            f.setForduloAHeten(fordulo);
            String a = "";
            if(!adatok[3].equals(""))
            {
                a = adatok[3];
            }
            else
                {
                    a = getDatum(Integer.parseInt(adatok[0]),Integer.parseInt(adatok[1]),fordulo);
                }
            String[] datum = a.split("\\.");
            f.setDatum(LocalDate.of(Integer.parseInt(datum[0]),Integer.parseInt(datum[1]),Integer.parseInt(datum[2])));
            List<Talalat> tLista= new ArrayList<>();
            for (int i = 4; i < 14 ; i+=2) {
                String szam = "";
                for (int j = 0; j < adatok[i+1].indexOf("Ft"); j++) {
                    if(adatok[i+1].substring(j,j+1).hashCode() >= 48 && adatok[i+1].substring(j,j+1).hashCode() <= 57)
                    {
                        szam += adatok[i+1].charAt(j);
                    }
                }
                tLista.add(new Talalat(Integer.parseInt(adatok[i]),Integer.parseInt(szam)));
            }

            f.setTalalatok(tLista);
            List<Eredmeny> eLista = new ArrayList<>();
            for (int i = 14; i < 28; i++) {
                if(adatok[i].length() == 2)
                {
                    adatok[i] = adatok[i].substring(1,2);
                }
                switch (adatok[i])
                {
                    case "1":
                        eLista.add(Eredmeny._1);
                        break;
                    case "2":
                        eLista.add(Eredmeny._2);
                        break;
                    case "X":
                    case "x":
                        eLista.add(Eredmeny.X);
                        break;
                }
            }
            f.setEredmenyek(eLista);
            forduloLista.add(f);
        }
    }

    public String getDatum(int ev,int het,int nap)
    {
        LocalDate lD = LocalDate.of(ev,1,1);
        int elsoHetNapja = 0;
        int n = 1;

        if(lD.getDayOfWeek().getValue() <= 4)
        {
            n -= (lD.getDayOfWeek().getValue()-1);
            if(n < 1)
            {
                n+= 31;
            }
            ev--;
            n = LocalDate.of(ev,12,n).getDayOfYear();
        }
        else
            {
                n += (8-lD.getDayOfWeek().getValue());
            }
        int evNapok = n+(7*(het-1));
        if(evNapok > 365 && ev%4!=0)
        {
            ev++;
            evNapok -= 365;
        }
        else if(evNapok > 366 && ev%4 == 0)
        {
            ev++;
            evNapok -= 366;
        }
        int h = 0;
        int hn = 0;

        while(evNapok > 0)
        {
            h++;
            switch (h)
            {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    evNapok -= 31;
                    hn = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    evNapok -= 30;
                    hn = 30;
                    break;
                case 2:
                    if(ev%4 == 0)
                    {
                        evNapok -= 29;
                        hn = 29;
                    }
                    else
                        {
                            evNapok -= 28;
                            hn = 28;
                        }
                    break;
            }
        }
        evNapok += hn;
        return ev + "." + h + "." + evNapok + ".";
    }

    public int legnagyobbNyeremeny()
    {
        int max = this.forduloLista.get(0).getTalalatok().get(0).getNyeremeny();
        for (int i = 0; i < this.forduloLista.size(); i++) {
            for (int j = 0; j < this.forduloLista.get(i).getTalalatok().size(); j++) {
                int aktualisNyeremeny = this.forduloLista.get(i).getTalalatok().get(j).getNyeremeny();
                if(aktualisNyeremeny > max)
                {
                    max = aktualisNyeremeny;
                }
            }
        }
        return max;
    }
    public String tippeles(LocalDate datum,List<Eredmeny> eredmenyLista)
    {
        int talalat = 0;
        int find = -1;
        int i2 = 0;
        boolean l = false;
        while(i2<this.forduloLista.size() && !l)
        {
            if(this.forduloLista.get(i2).getDatum().equals(datum))
            {
                l = true;
                find = i2;
            }
            i2++;
        }
        for (int i = 0; i < 14; i++) {
            if(this.forduloLista.get(find).getEredmenyek().get(i) == eredmenyLista.get(i))
            {
                talalat++;
            }
        }
        int nyeremeny = 0;
        if(talalat >= 10)
        {
            nyeremeny = this.forduloLista.get(find).getTalalatok().get(14-talalat).getNyeremeny();
        }
        return "Eredmény: Találat: "+talalat+", nyeremény: "+nyeremeny+" Ft";
    }

    public double nyeresiArany(Eredmeny e)
    {
        int db = 0;
        int ind = 0;
        for (int i = 0; i < this.forduloLista.size(); i++) {
            for (int j = 0; j < this.forduloLista.get(i).getEredmenyek().size(); j++) {
                if(this.forduloLista.get(i).getEredmenyek().get(j) == e)
                {
                    db ++;
                }
            }
        }
        double d = this.forduloLista.size()*14;
        return db/d*100;
    }
}
