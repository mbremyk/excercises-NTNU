/**
 * Meny.java
 *
 * Klasse for å lagre og jobbe med en liste med Matretter
 */

import java.util.ArrayList;
import java.util.Comparator;

public class Meny
{
    ArrayList<Matrett> matretter;
    private String restaurantNavn;

    /**
     * Konstruktør for Meny som setter Menyens restaurantNavn lik restaurantNavn
     *
     * @param restaurantNavn det unike navnet til Menyen
     */
    public Meny(String restaurantNavn)
    {
        this.restaurantNavn = restaurantNavn;
    }

    /**
     * Konstruktør for Meny som setter Menyens restaurantNavn lik restaurantNavn og matretter lik matretter
     *
     * @param matretter ArrayList med matretter for å initialisere Menyen
     * @param restaurantNavn det unike navnet til Menyen
     */
    public Meny(ArrayList<Matrett> matretter, String restaurantNavn)
    {
        this.matretter = (ArrayList<Matrett>) matretter.clone();
        this.restaurantNavn = restaurantNavn;
    }

    /**
     * Legger til en Matrett i Menyen
     *
     * @param matrett Matretten som skal legges til
     * @throws IllegalArgumentException hvis en Matrett med samme navn allerede eksisterer i Menyen
     */
    public void leggTilMatrett(Matrett matrett) throws IllegalArgumentException
    {
        if(matretter.contains(matrett))
        {
            throw new IllegalArgumentException("Matrett eksisterer allerede i menyen");
        }
        matretter.add(matrett);
    }

    /**
     * Legger sammen prisen til alle Matrettene i Menyen
     *
     * @return totalprisen til alle Matrettene i Menyen
     */
    public int getTotalPris()
    {
        int totalPris = 0;
        for(Matrett matrett : matretter)
        {
            totalPris += matrett.getPris();
        }
        return totalPris;
    }

    /**
     * @return ArrayList med Matrettene i Menyen
     */
    public ArrayList<Matrett> getMatretter()
    {
        return matretter;
    }

    /**
     * @return String med Menyens navn
     */
    public String getRestaurantNavn()
    {
        return restaurantNavn;
    }

    /**
     * Sammenlikningsfunksjon for Meny. Brukes for eskempel av {@link ArrayList#contains(Object)}
     * Objektene sammeliknes på restaurantNavn
     *
     * @param o Objektet this skal sammeliknes med
     * @return true hvis this og o har samme restaurantNavn, false hvis de er ulike, eller hvis o er null
     */
    public boolean equals(Object o)
    {
        if(o instanceof Meny)
        {
            return this.restaurantNavn.equalsIgnoreCase(((Meny)o).getRestaurantNavn());
        }
        return false;
    }

    /**
     * Da var vi her igjen. En toString. Kos deg med denne.
     * Skriver ut antall matretter siden en Meny kan ha ganske mange, og de kan hentes på en annen måte
     *
     * @return return return return return return return return return return return return return return return return
     */
    @Override
    public String toString()
    {
        return "Meny{" +
                "restaurantNavn='" + restaurantNavn + '\'' +
                ", antall matretter=" + matretter.size() +
                '}';
    }
}
