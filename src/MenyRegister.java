/**
 * MenyRegister.java
 *
 * Klasse for å lagre og jobbe med lister med menyer og matretter
 */

import java.util.ArrayList;

public class MenyRegister
{
    private ArrayList<Meny> menyer;
    private ArrayList<Matrett> matretter;

    /**
     *
     */
    public MenyRegister()
    {
        menyer = new ArrayList<Meny>();
        matretter = new ArrayList<Matrett>();
    }

    /**
     * Metode for å registrere en ny rett i listen med matretter
     * @see Matrett konstruktør
     *
     * @param navn Unikt navn på matretten
     * @param type Hvilken type rett det er (Forrett, hovedrett, dessert)
     * @param pris Prisen på retten
     * @param oppskrift Tekststreng med oppskriften
     * @return Matretten som ble registrert, eller null hvis en matrett med samme navn allerede finnes i registeret
     */
    public Matrett registrerNyRett(String navn, String type, int pris, String oppskrift)
    {
        Matrett matrett = new Matrett(navn, type, pris, oppskrift);
        if(matretter.contains(matrett))
        {
            return null;
        }
        matretter.add(matrett);
        return matrett;
    }

    /**
     * Henter ut en matrett fra registeret med dens unike navn
     *
     * @param navn Det unike navnet til matretten du vil finne
     * @return Matretten hvis navn er like navn, eller null hvis den ikke finnes i registeret
     */
    public Matrett getMatrettByNavn(String navn)
    {
        for(Matrett matrett : matretter)
        {
            if(matrett.getNavn().equalsIgnoreCase(navn))
            {
                return matrett;
            }
        }
        return null;
    }

    /**
     * Henter ut en liste med matretter etter type
     *
     * @param type Typen du ønsker å hente ut. Som regel hovedrett, forrett, dessert eller liknende
     * @return ArrayList med Matretter som har type lik type
     */
    public ArrayList<Matrett> getMatretterByType(String type)
    {
        ArrayList<Matrett> matretterByType = new ArrayList<Matrett>();
        for(Matrett matrett : matretter)
        {
            if(matrett.getType().equalsIgnoreCase(type))
            {
                matretterByType.add(matrett);
            }
        }
        return matretterByType;
    }

    /**
     * Registrerer en ny meny og initialiserer den med de gitte rettene
     *
     * @param matretter ArrayList med Matretter
     * @param restaurantNavn Det unike navnet som Menyen kan identifiseres med
     * @return Den registrerte Menyen, eller null hvis det allerede eksisterer en Meny med samme navn
     */
    public Meny registrerNyMeny(ArrayList<Matrett> matretter, String restaurantNavn)
    {
        Meny meny = new Meny(matretter, restaurantNavn);
        if (menyer.contains(meny)) return null;
        menyer.add(meny);
        return meny;
    }

    /**
     * Henter en liste med menyer med totalpris innenfor det gitte området
     *
     * @param nedre int nedre grense for sammenlikningen
     * @param ovre int øvre grense for sammenlikningen
     * @return ArrayList med Menyer
     */
    public ArrayList<Meny> getMenyerInPriceRange(int nedre, int ovre)
    {
        ArrayList<Meny> menyerInPriceRange = new ArrayList<Meny>();
        for(Meny meny : menyer)
        {
            int pris = meny.getTotalPris();
            if(pris >= nedre && pris < ovre)
            {
                menyerInPriceRange.add(meny);
            }
        }
        return menyerInPriceRange;
    }

    /**
     * Legger til en Matrett i gitte Meny
     *
     * @param matrett Matretten som skal legges til i Menyen
     * @param meny Menyen Matretten skal legges til i
     * @throws IllegalArgumentException fra {@link Meny#leggTilMatrett(Matrett)}
     */
    public void leggTilMatrettIMeny(Matrett matrett, Meny meny) throws IllegalArgumentException
    {
        meny.leggTilMatrett(matrett);
    }

    /**
     * Henter Menyene fra registeret
     *
     * @return ArrayList med Menyene i registeret
     */
    public ArrayList<Meny> getMenyer()
    {
        return menyer;
    }

    /**
     * Henter Matrettene fra registeret
     *
     * @return ArrayList med Matrettene i registeret
     */
    public ArrayList<Matrett> getMatretter()
    {
        return matretter;
    }

    /**
     * Henter en meny med sitt unike navn
     *
     * @param restaurantNavn Det unike navnet til menyen
     * @return Menyen med det spesifiserte navnet, eller null hvis den ikke finnes i registeret
     */
    public Meny getMenyByNavn(String restaurantNavn)
    {
        for(Meny meny : menyer)
        {
            if(meny.getRestaurantNavn().equalsIgnoreCase(restaurantNavn))
            {
                return meny;
            }
        }
        return null;
    }

    /**
     * toString. Hva tror du egentlig? Skal jeg skrive en vittig kommentar om hva som skjer her, eller forstår du det selv?
     *
     * @return gjett
     */
    @Override
    public String toString()
    {
        return "MenyRegister{" +
                "menyer=" + menyer +
                ", matretter=" + matretter +
                '}';
    }
}
