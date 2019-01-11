
package logiikka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import scenes.BoardGameAppFXMain;

public class DataAnalyzer {

    public ArrayList<String> readFile(String tiedosto) {
        ArrayList<String> rivit = new ArrayList<>();
        try {
            Files.lines(Paths.get(tiedosto)).forEach(rivi -> rivit.add(rivi));
        } catch (IOException e) {
            System.out.println("Lukeminen epäonnistui. Virhe: " + e.getMessage());
        }

        return rivit;
    }

    public void kirjoitaTiedostoon(String tiedosto, ArrayList<String> rivit) {
        try {
            Files.write(Paths.get(tiedosto), rivit, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Kirjoittaminen epäonnistui. Virhe: " + e.getMessage());
        }
    }

    public void kirjoitaTiedostoon(String tiedosto, String rivi) {
        lisaaTiedostoon(tiedosto, merkkijonoListana(rivi));
    }

    public void lisaaTiedostoon(String tiedosto, ArrayList<String> rivit) {
        try {
            Files.write(Paths.get(tiedosto), rivit,
                StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Lisääminen epäonnistui. Virhe: " + e.getMessage());
        }
    }

    public ArrayList<String> merkkijonoListana(String rivi) {
        ArrayList<String> rivit = new ArrayList<>();
        rivit.add(rivi);

        return rivit;
    }
    
    public ArrayList<String> lueIsoTiedosto(String tiedostonimi) {
        ArrayList<String> rivitTiedostosta = new ArrayList<>();
        try {
            InputStreamReader lukija = new InputStreamReader(
                    BoardGameAppFXMain.class.getResourceAsStream("ohjeita.txt"));
            BufferedReader in = new BufferedReader(lukija);
            
            while (true) { 
                String line = in.readLine(); 
                if (line == null) break; 
                rivitTiedostosta.add(line);
            } 
        } catch (IOException e) {
            System.out.println("Tiedostoa ei löydy. Virhe: " + e.getMessage());
        }
        return rivitTiedostosta;
    }
}
