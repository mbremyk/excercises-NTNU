import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Oppgave1
{
    public static void main(String[] args)
    {
        String meny = "0: Vis liste over arrangementer\n" +
                "1: Opprett nytt arrangement\n" +
                "2: Finn alle arrangementer på et gitt sted\n" +
                "3: Finn alle arrangementer på en gitt dato\n" +
                "4: Finn alle arrangementer mellom to datoer\n" +
                "5: Avslutt";
        ArrangementRegister arrangementRegister = new ArrangementRegister();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = -1;

        do
        {
            System.out.println(meny);
            try
            {
                input = Integer.parseInt(br.readLine());

                switch (input)
                {
                    case 0:
                        ArrayList<Arrangement> ar = arrangementRegister.getArrangementerSorterEtter(ArrangementRegister.SorterEtter.TIDSPUNKT);
                        for (Arrangement a : ar)
                        {
                            System.out.println(a);
                        }
                        System.out.println();
                        break;
                    case 1:
                        System.out.print("Arrangementnavn: ");
                        String arrangementNavn = br.readLine();
                        System.out.print("Sted: ");
                        String sted = br.readLine();
                        System.out.print("Arrangør: ");
                        String arrangeur = br.readLine();
                        System.out.print("Type: ");
                        String type = br.readLine();
                        System.out.print("Tidspunkt (YYYYMMDDhhmm): ");
                        long tidspunkt = Long.parseLong(br.readLine());
                        arrangementRegister.registrerNyttArrangement(arrangementNavn, sted, arrangeur, type, tidspunkt);
                        break;
                    case 2:
                        System.out.println("Sted: ");
                        ar = arrangementRegister.getArrangementerBySted(br.readLine());
                        for (Arrangement a : ar)
                        {
                            System.out.println(a);
                        }
                        System.out.println();
                        break;
                    case 3:
                        System.out.println("Dato (YYYYMMDD): ");
                        ar = arrangementRegister.getArrangementerByDato(Long.parseLong(br.readLine()));
                        for (Arrangement a : ar)
                        {
                            System.out.println(a);
                        }
                        System.out.println();
                        break;
                    case 4:
                        System.out.println("Fra dato (YYYYMMDD): ");
                        long fraDato = Long.parseLong(br.readLine());
                        System.out.println("Til dato (YYYYMMDD): ");
                        long tilDato = Long.parseLong(br.readLine());
                        ar = arrangementRegister.getArrangementerBetween(fraDato, tilDato);
                        for (Arrangement a : ar)
                        {
                            System.out.println(a);
                        }
                        System.out.println();
                        break;
                    case 5:
                        System.out.println("Takk for at du brukte vårt system :)");
                        break;
                    default:
                        break;
                }
            }
            catch (IOException ioe)
            {
                System.out.println("Jeg kan ikke høre deg");
            }
            catch (NumberFormatException nfe)
            {
                System.out.println("Hva faen er det tallet");
            }
            catch (Exception e)
            {
                System.out.println("Det har oppstått en ukjent feil");

            }


        } while (input != 5);
    }
}
