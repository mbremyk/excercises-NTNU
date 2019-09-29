/**
 * Matrett.java
 *
 * Klasse for å representere en matrett
 */

import java.util.ArrayList;

public class Matrett
{
    private String navn;
    private String type;
    private int pris;
    private String oppskrift;

    /**
     * Konstruktør til Matrett.
     * I denne oppgaven skal navnet definere Matretten unikt, men det er ingen fornuftig måte å sjekke dette i klassen, altså må dette skje utenfor.
     * Andre som bruker klassen vil kunne gi flere Matretter samme navn uten problem
     *
     * @param navn Unikt navn til Matretten
     * @param type Type Matrett. Vanligvis forrett, hovedrett, dessert el.
     * @param pris int Prisen til Matretten. Kunne vært et flytpunkttall, men de fleste restauranter har heltallspriser på maten sin, så den tar en int
     * @param oppskrift Oppskriften til Matretten.
     */
    public Matrett(String navn, String type, int pris, String oppskrift)
    {
        this.navn = navn;
        this.type = type;
        this.pris = pris;
        this.oppskrift = oppskrift;
    }

    /**
     * Sammenlikningsfunksjon for Matrett. Brukes for eskempel av {@link ArrayList#contains(Object)}
     * Objektene sammeliknes på navn
     *
     * @param o Objektet this skal sammeliknes med
     * @return true hvis this og o har samme navn, false hvis de er ulike, eller hvis o er null
     */
    public boolean equals(Object o)
    {
        if(o instanceof Matrett)
        {
            return this.navn.equalsIgnoreCase(((Matrett)o).getNavn());
        }
        return false;
    }

    /**
     * Standard get-metode
     *
     * @return this.navn
     */
    public String getNavn()
    {
        return navn;
    }

    /**
     * Standard get-metode
     *
     * @return this.type
     */
    public String getType()
    {
        return type;
    }

    /**
     * Standard get-metode
     *
     * @return this.pris
     */
    public int getPris()
    {
        return pris;
    }

    /**
     * Standard get-metode
     *
     * @return this.oppskrift
     */
    public String getOppskrift()
    {
        return oppskrift;
    }

    /**
     * Den siste toStringen. Endelig ferdig. Hvorfor leser du dette? Har du noen grunn til å lese dokumentasjonen?
     *
     * @return Hvis du ikke vet hva den returnerer kan du sende meg en mail på <a href="mailto:magbre@stud.ntnu.no">Magnus Brevik</a>
     */
    @Override
    public String toString()
    {
        return "Matrett{" +
                "navn='" + navn + '\'' +
                ", type='" + type + '\'' +
                ", pris=" + pris +
                ", oppskrift='" + oppskrift + '\'' +
                '}';
    }
}
