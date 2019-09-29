/**
 * Arrangement.java
 * <p>
 * Klasse for å jobbe med Arrangementer. Skal ikke brukes direkte, men være tilgjengelig gjennom ArrangementRegister
 * Implementerer Comparable for enkel sortering gjennom Collections.sort(List<T>)
 * </p>
 *
 * @author Brevik Magnus
 */

public class Arrangement implements Comparable
{
    private final long arrangementNummer;
    private String navn;
    private String sted;
    private String arrangeur;
    private String type;
    private long tidspunkt;

    /**
     * @param arrangementNummer Nummer på arrangementet som skal være entydig. Dette fikser vi i ArrangementRegister
     * @param navn              Navn på arrangementet
     * @param sted              Stedet arrangementet skal være. Dette er en atributt vi skal kunne sortere etter
     * @param arrangeur         Arrangøren til arrangementet
     * @param type              Typen til arrangementet. Dette er en atributt vi skal kunne sortere etter
     * @param tidspunkt         YYYYMMDDhhmm    Tidspunktet arrangementet skal starte
     */
    public Arrangement(long arrangementNummer, String navn, String sted, String arrangeur, String type, long tidspunkt)
    {
        this.arrangementNummer = arrangementNummer;
        this.navn = navn;
        this.sted = sted;
        this.arrangeur = arrangeur;
        this.type = type;
        this.tidspunkt = tidspunkt;
    }

    /**
     * compareTo-metode arvet fra interfacet Comparable. Denne må være implementert for å kunne sortere Arrangementer på denne måten
     *
     * @param o Object this skal sammenlignes med
     * @return  Negativ int hvis this er mindre enn o, positiv int hvis o er større enn this, og 0 this er lik o
     * @throws  IllegalArgumentException hvis o ikke er et Arrangement
     */
    @Override
    public int compareTo(Object o)
    {
        int before = 0;
        if (o instanceof Arrangement)
        {
            Arrangement a = (Arrangement) o;
            return (int)(this.tidspunkt - a.getTidspunkt());
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @return arrangementets entydige arrangementsnummer
     */
    public long getArrangementNummer()
    {
        return arrangementNummer;
    }

    /**
     * @return arrangementets navn
     */
    public String getNavn()
    {
        return navn;
    }

    /**
     * @return stedet arrangementet skal foregå
     */
    public String getSted()
    {
        return sted;
    }

    /**
     * @return arrangementets arrangør
     */
    public String getArrangeur()
    {
        return arrangeur;
    }

    /**
     * @return arrangementets type
     */
    public String getType()
    {
        return type;
    }

    /**
     * @return YYYYMMDDhhmm     Tidspunktet arrangementet skal starte
     */
    public long getTidspunkt()
    {
        return this.tidspunkt;
    }

    /**
     * @return YYYYMMDD     Datoen arrangementet skal finne sted
     */
    public long getDato()
    {
        return Long.parseLong(Long.toString(this.tidspunkt).substring(0,8));
    }

    /**
     * @return hhmm     Klokkeslettet arrangementet skal starte
     */
    public long getKlokkeslett()
    {
        return Long.parseLong(Long.toString(this.tidspunkt).substring(8,12));
    }

    /**
     * @return en som representerer all viktig informasjon i arrangementet
     */
    @Override
    public String toString()
    {
        return "" +
                "Arrangement: " + navn +
                ", Sted: " + sted +
                ", Arrangør: " + arrangeur +
                ", Type: " + type +
                ", Tidspunkt: " + tidspunkt;
    }
}
