/**
 * ArrangementRegister.java
 *
 * Klasse for å jobbe med en liste med Arrangementer.
 *
 * @author Brevik Magnus
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ArrangementRegister
{
    private ArrayList<Arrangement> arrangementRegister = new ArrayList<Arrangement>();
    private long arrangementNummer;

    /**
     * Legger et nytt arrangement i lista ved å kalle konstruktøren til Arrangement
     * @see Arrangement#Arrangement(long, String, String, String, String, long)
     * Her blir også arrangementNummer satt til et unikt nummer ved å inkrementere en teller
     *
     * @return Arrangementet som nettopp ble lagt inn i lista
     */
    public Arrangement registrerNyttArrangement(String navn, String sted, String arrangeur, String type, long tidspunkt)
    {
        Arrangement arrangement = new Arrangement(arrangementNummer++, navn, sted, arrangeur, type, tidspunkt);
        if (arrangementRegister.add(arrangement)) return arrangement;
        return null;
    }

    /**
     * Går gjennom arrangementRegister og finner alle Arrangementer som har getSted() lik sted
     *
     * @param sted Navnet på stedet der du vil ha arrangementer fra. Ikke case-sensitiv pga. equalsIgnoreCase()
     * @return en ArrayList med Arrangementer som finner sted ved sted
     */
    public ArrayList<Arrangement> getArrangementerBySted(String sted)
    {
        ArrayList<Arrangement> arrangementerBySted = new ArrayList<Arrangement>();
        for(Arrangement arrangement : arrangementRegister)
        {
            if(arrangement.getSted().equalsIgnoreCase(sted))
            {
                arrangementerBySted.add(arrangement);
            }
        }
        return arrangementerBySted;
    }

    /**
     * Går gjennom arrangementRegister og finner alle Arrangementer som har getDato() lik dato
     *
     * @param dato YYYYMMDD     Datoen man vil finne arrangementer på
     * @return en ArrayList med Arrangementer som finner sted på dato
     */
    public ArrayList<Arrangement> getArrangementerByDato(long dato)
    {
        ArrayList<Arrangement> arrangementerByDato = new ArrayList<Arrangement>();
        for(Arrangement arrangement : arrangementRegister)
        {
            if(arrangement.getDato() == dato)
            {
                arrangementerByDato.add(arrangement);
            }
        }
        return arrangementerByDato;
    }

    /**
     * Går gjennom arrangementRegister og finner alle Arrangementer som har fraDato <= getDato() <= tilDato
     *
     * @param fraDato YYYYMMDD      Nedre grense for datoene. Inklusic
     * @param tilDato YYYYMMDD      Øvre grense for datoene. Inklusiv
     * @return en ArrayList med arrangementer mellom spesifiserte datoer
     */
    public ArrayList<Arrangement> getArrangementerBetween(long fraDato, long tilDato)
    {
        ArrayList<Arrangement> arrangementerBetween = new ArrayList<Arrangement>();
        for(Arrangement arrangement : arrangementRegister)
        {
            if(arrangement.getDato() >= fraDato && arrangement.getDato() <= tilDato)
            {
                arrangementerBetween.add(arrangement);
            }
        }
        Collections.sort(arrangementerBetween);
        return arrangementerBetween;
    }

    /**
     * Sorterer arrangementRegister etter spesifiserte enum
     *
     * @param sorterEtter enum som beskriver hvilken attributt det skal sorteres etter. Mulige verdier er {STED, TYPE, TIDSPUNKT}
     * @see SorterEtter
     * @return en sortert kopi av arrangementRegister
     */
    public ArrayList<Arrangement> getArrangementerSorterEtter(SorterEtter sorterEtter)
    {
        ArrayList<Arrangement> out = (ArrayList<Arrangement>)arrangementRegister.clone();
        Collections.sort(out, sorterEtter.getComparator());
        return out;
    }

    /**
     * En liten curve-ball hvis noen av førsteklassingene skulle få tak i dette. Hva har jeg gjort her? Hvem vet?
     */
    public enum SorterEtter
    {
        STED(CompareBySted),
        TYPE(CompareByType),
        TIDSPUNKT(CompareByTidspunkt);

        private Comparator<Arrangement> comparator;
        SorterEtter(Comparator<Arrangement> comparator)
        {
            this.comparator = comparator;
        }

        public Comparator getComparator()
        {
            return this.comparator;
        }
    }

    /**
     * Comparators brukes til å sammenligne to objekter av samme klasse, og brukes her i getArrangementSorterEtter()
     * De krever en implementasjon av compare(T,T) og kan f.eks. brukes i Collections.sort(List<T>, Comparator<T>)
     */
    private static Comparator<Arrangement> CompareBySted = new Comparator<Arrangement>()
    {
        public int compare(Arrangement a1, Arrangement a2)
        {
            return a1.getSted().compareTo(a2.getSted());
        }
    };

    private static Comparator<Arrangement> CompareByType = new Comparator<Arrangement>()
    {
        public int compare(Arrangement a1, Arrangement a2)
        {
            return a1.getType().compareTo(a2.getType());
        }
    };

    private static Comparator<Arrangement> CompareByTidspunkt = new Comparator<Arrangement>()
    {
        public int compare(Arrangement a1, Arrangement a2)
        {
            long l = a1.getTidspunkt() - a2.getTidspunkt();
            return l > 0 ? 1 : (l < 0 ?  -1 : 0);
        }
    };

    /**
     * @return you get it by now
     */
    public ArrayList<Arrangement> getArrangementRegister()
    {
        return arrangementRegister;
    }

    /**
     * Detta skjønner jeg ikke bæret av, kan noen forklare?
     *
     * @return
     */
    @Override
    public String toString()
    {
        String out = "ArrangementRegister{\n";
        for(Arrangement arrangement : arrangementRegister)
        {
            out += "\t" + arrangement + "\n";
        }
        out += "}";
        return out;
    }
}
