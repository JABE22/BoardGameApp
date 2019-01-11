/*

---Copyright by JAMA Softwares---

Software name: Inventory Management
Date: 23.8.2017

Sovelluksen tekstikäyttöliittymä! 

Tällä main -luokalla on testailtu sovelluksen toimintaa ennen Graafisen JavaFX:n
luomista. (23.8.2017 Jarno Matarmaa)
Tämä luokka ei toimi enää tällaisenaan. Sovelluksen toimintalogiikkaa on muutettu.

Tämä on arkistoitu kommentteihin mahdollista myöhempää tarkastelua tai käyttöä 
varten.

*/

/*
package varastonhallinta;

import java.util.Scanner;
import logiikka.Tiedostonkasittelija;
import logiikka.VarastoHallinta;

public class Varastonhallinta {

    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);
        Tiedostonkasittelija tiedostonkasittelija = new Tiedostonkasittelija();
        VarastoHallinta varasto = new VarastoHallinta(tiedostonkasittelija);
        varasto.lueVarastoraportti();

        System.out.println("--- Varastonhallinta ---");
        System.out.println("\nKomennot:\n"
                + "lisaa\n"
                + "ota\n"
                + "hae\n"
                + "tallenna\n"
                + "tyhjenna\n"
                + "lopeta\n");

        while (true) {
            System.out.print("\nAnna komento: ");
            String komento = lukija.nextLine();

            if (komento.equals("lopeta")) {
                System.out.print("Tallennetaanko muutokset? k/e: ");
                String valinta = lukija.nextLine();
                if (valinta.equals("k")) {
                    varasto.tallennaMuutokset();
                    System.out.println("Muutokset tallennettu.");
                }
                System.out.println("\n...lopetetaan varastonhallinta!");
                break;
            } else if (komento.equals("lisaa")) {
                while (true) {
                    System.out.print("Anna varaston tunnus: ");
                    String tunnus = lukija.nextLine();
                    if (tarkistaSyote(tunnus, "P/K+2numeroa")) {
                        System.out.print("Anna tuotenimi: ");
                        String tuotenimi = lukija.nextLine();
                        if (tarkistaSyote(tuotenimi, "kirjaimia")) {
                            System.out.print("Anna vuosi: ");
                            String vuosi = lukija.nextLine();
                            System.out.print("Anna määrä litroina: ");
                            String maara = lukija.nextLine();
                            if (tarkistaSyote(vuosi, "numero") && tarkistaSyote(maara, "numero")) {
                                int vuosiLukuna = Integer.parseInt(vuosi);
                                double doubleMaara = Double.parseDouble(maara);

                                varasto.lisaaVarastoon(tunnus, tuotenimi, vuosiLukuna, doubleMaara);
                                break;
                                
                            } else {
                                System.out.println("Vuosiluku tai määrä virheellinen");
                            }
                        } else {
                            System.out.println("Tuotenimi voi sisältää vain kirjaimia ja välilyöntejä");
                        }
                    } else {
                        System.out.println("Tunnus oltava 3 merkkiä pitkä. "
                                + "Alussa K tai P, jonka jälkeen kaksinumeroinen luku (01-99)");
                    }
                }

            } else if (komento.equals("ota")) {
                System.out.print("Anna tuotenimi: ");
                String tuotenimi = lukija.nextLine();
                System.out.print("Anna vuosi: ");
                String vuosi = lukija.nextLine();
                System.out.print("Anna määrä: ");
                String maara = lukija.nextLine();
                if (tarkistaSyote(vuosi, "numero") && tarkistaSyote(maara, "numero")) {
                    int vuosiLukuna = Integer.parseInt(vuosi);
                    int maaraLukuna = Integer.parseInt(maara);
                    varasto.otaVarastosta(tuotenimi, vuosiLukuna, maaraLukuna);
                } else {
                    System.out.println("Tarkista syötteet 'pakkauskoko' ja 'määrä'.");
                }

            } else if (komento.equals("hae")) {
                System.out.print("Anna tuotenimi: ");
                String tuotenimi = lukija.nextLine();               
                varasto.haeVarastostaTuotenimella(tuotenimi);
            } else if (komento.equals("tyhjenna")) {
                System.out.print("Kaikki tiedot menetetään! Haluatko jatkaa? k/e: ");
                String valinta = lukija.nextLine();
                if (valinta.equals("k")) {
                    varasto.alustaVarastoraporttiTxt();
                    System.out.println("Kaikki varaston sisältö tyhjennetty!");
                }
            } else if (komento.equals("tallenna")) {
                varasto.tallennaMuutokset();
                System.out.println("Muutokset tallennettu!");
            } else {
                System.out.println("Komento ei kelpaa!");
            }
        }
    }

    public static boolean tarkistaSyote(String syote, String ehto) {
        if (ehto.equals("numero")) {
            if (syote.matches("[0-9]+")) {
                return true;
            }
        } else if (ehto.equals("P/K+2numeroa")) {
            if (syote.matches("[P|K][0-9]{2}")) {
                return true;
            }
        } else if (ehto.equals("kirjaimia")) {
            if (syote.matches("[a-ö| |A-Ö]{2,25}")) {
                return true;
            }
        }
        return false;
    }
}
*/