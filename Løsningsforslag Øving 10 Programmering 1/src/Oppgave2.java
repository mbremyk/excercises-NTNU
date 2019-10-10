import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Oppgave2
{
    private static String menyStr = "0:\tRegistrere en ny matrett\n" +
            "1:\tSkrive ut alle registrerte matretter\n" +
            "2:\tFinne en matrett på navn\n" +
            "3:\tSkrive ut alle matretter av en gitt type\n" +
            "4:\tRegistrere en ny meny\n" +
            "5:\tLegg til rett i meny\n" +
            "6:\tFinn menyer med totalpris i et intervall\n" +
            "7:\tAvslutt";

    public static void main(String[] args)
    {
        MenyRegister menyRegister = new MenyRegister();
        menyRegister.registrerNyRett("Spaghetti Carbonara", "Hovedrett", 299, "Yeet litt kjøttsaus oppå litt spaghetti. Bon appetite");
        menyRegister.registrerNyRett("Spaghetti Carbonar", "Forrett", 299, "Yeet litt kjøttsaus oppå litt spaghetti. Bon appetite");
        menyRegister.registrerNyRett("Spaghetti Carbonaa", "Dessert", 299, "Yeet litt kjøttsaus oppå litt spaghetti. Bon appetite");
        menyRegister.registrerNyRett("Spaghetti Carbonra", "Hovedrett", 299, "Yeet litt kjøttsaus oppå litt spaghetti. Bon appetite");
        menyRegister.registrerNyRett("Spaghetti Carboara", "Hovedrett", 299, "Yeet litt kjøttsaus oppå litt spaghetti. Bon appetite");
        menyRegister.registrerNyMeny(new ArrayList<Matrett>(), "Egon");
        menyRegister.leggTilMatrettIMeny(menyRegister.getMatrettByNavn("Spaghetti Carbonara"), menyRegister.getMenyByNavn("Egon"));
        System.out.println(menyRegister);
        System.out.println(menyRegister.getMatretterByType("Hovedrett"));

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in)))
        {
            System.out.println("Velkommen til vårt menyregister.");
            int valg;
            do
            {
                System.out.printf("Registeret har for øyeblikket %d menyer med til sammen %d unike matretter\n", menyRegister.getMenyer().size(), menyRegister.getMatretter().size());
                System.out.println("Hva ønsker du å gjøre?");
                System.out.println(menyStr);
                valg = Integer.parseInt(br.readLine());
                switch (valg)
                {
                    case 0:
                        System.out.println("Skriv inn navn på ny matrett. Hvis en rett med samme navn allerede ligger i registeret vil du ikke kunne legge til retten");
                        String navn = br.readLine();
                        System.out.println("Hvilken type matrett er det?");
                        String type = br.readLine();
                        System.out.println("Hva er prisen i hele kroner?");
                        int pris = Integer.parseInt(br.readLine());
                        System.out.println("Vennligst legg inn en oppskrift");
                        String oppskrift = br.readLine();
                        if (menyRegister.registrerNyRett(navn, type, pris, oppskrift) != null)
                        {
                            System.out.println("Matrett registrert. Du kan nå legge den til i en meny");
                        }
                        else
                        {
                            System.out.println("Noe gikk galt. Enten eksisterer matretten fra før, eller noe annet gikk galt.");
                        }
                        break;
                    case 1:
                        ArrayList<Matrett> matretter = menyRegister.getMatretter();
                        for (Matrett matrett : matretter)
                        {
                            System.out.println("\t" + matrett.getNavn());
                        }
                        break;
                    case 2:
                        System.out.println("Hvilken matrett ser du etter?");
                        String matrettStr = br.readLine();
                        Matrett matrett = menyRegister.getMatrettByNavn(matrettStr);
                        System.out.printf("Navn på rett:\t%s\n" +
                                                  "Type:\t\t\t%s\n" +
                                                  "Pris:\t\t\t%dkr\n" +
                                                  "Oppskrift:\n" +
                                                  "%s\n",
                                          matrett.getNavn(),
                                          matrett.getType(),
                                          matrett.getPris(),
                                          matrett.getOppskrift()
                        );
                        break;
                    case 3:
                        System.out.println("Hvilken type matrett ser du etter?");
                        String typeStr = br.readLine();
                        matretter = menyRegister.getMatretterByType(typeStr);
                        for(Matrett mr : matretter)
                        {
                            System.out.println("\t" + mr);
                        }
                        break;
                    case 4:
                        System.out.println("Hvilken restaurant skal menyen tilhøre?");
                        String restaurantNavn = br.readLine();
                        matretter = new ArrayList<Matrett>();
                        System.out.println("Hvilke matretter skal være på menyen? Trykk ENTER ved tomt felt for å avslutte");
                        matrettStr = br.readLine();
                        while(matrettStr != null && !matrettStr.equals(""))
                        {
                            matrett = menyRegister.getMatrettByNavn(matrettStr);
                            if(matrett != null && !matretter.contains(matrett))
                            {
                                matretter.add(matrett);
                            }
                            else
                            {
                                System.out.println("Matrett allerede registrert eller ikke funnet");
                            }
                            matrettStr = br.readLine();
                        }
                        menyRegister.registrerNyMeny(matretter, restaurantNavn);
                        break;
                    case 5:
                        System.out.println("Hvilken meny vil du legge til en rett i? (Restaurantnavn)");
                        restaurantNavn = br.readLine();
                        if(!menyRegister.getMenyer().contains(new Meny(restaurantNavn)))
                        {
                            System.out.println("Menyen finnes ikke");
                            break;
                        }
                        System.out.println("Hvilken rett vil du legge til?");
                        matrettStr = br.readLine();
                        matrett = menyRegister.getMatrettByNavn(matrettStr);
                        if(matrett == null)
                        {
                            System.out.println("Matretten er ikke registrert");
                        }
                        menyRegister.leggTilMatrettIMeny(matrett, menyRegister.getMenyByNavn(restaurantNavn));
                        break;
                    case 6:
                        System.out.println("Hvilket prisområde ser du etter?");
                        System.out.print("Fra: ");
                        int nedre = Integer.parseInt(br.readLine());
                        System.out.print("Til: ");
                        int ovre = Integer.parseInt(br.readLine());
                        ArrayList<Meny> menyer = menyRegister.getMenyerInPriceRange(nedre, ovre);
                        for(Meny meny : menyer)
                        {
                            System.out.print(meny + "\t\tPris: " + meny.getTotalPris() + "\n");
                        }
                        break;
                    default:
                        System.out.println("Ikke gyldig kommando");
                        break;
                }
            } while (valg != 7);
        }
        catch (NumberFormatException nfe)
        {

        }
        catch (IOException ioe)
        {

        }
        catch (Exception e)
        {

        }
    }
}
