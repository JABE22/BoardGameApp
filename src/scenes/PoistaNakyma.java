package scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import logiikka.Syotteentarkistaja;

public class PoistaNakyma {

// Viitteet aiemmin luotuihin olioihin
    private final MainPage paaikkuna;
    private final Syotteentarkistaja syotteentarkistaja;

// Näkymän komponentit
    private final GridPane asettelu;

// Vasemman puoleiset komponentit
    private final Text varastoText, tuoteText, vuosiText;
    private final TextField varastoSyote, tuoteSyote, vuosiSyote;
    private final Button poistaTuoteNappi;
    
// Oikean puoleiset komponentit
    private final Text varastotunnusText;
    private final TextField varastotunnusSyote;
    private final Button poistaVarastoNappi;

    public PoistaNakyma(Syotteentarkistaja syotteentarkistaja, 
            MainPage paaikkuna) {
        this.paaikkuna = paaikkuna;
        this.syotteentarkistaja = syotteentarkistaja;
        this.asettelu = new GridPane();

    // Vasemman puoleiset komponentit 
        this.varastoText = new Text("Varastotunnus: ");
        this.tuoteText = new Text("Tuotenimi: ");
        this.vuosiText = new Text("Vuosi: ");
        this.varastoSyote = new TextField();
        this.tuoteSyote = new TextField();
        this.vuosiSyote = new TextField();
        this.poistaTuoteNappi = new Button("Poista tuote");

    // Oikean puoleiset komponentit
        this.varastotunnusText = new Text("Varastotunnus: ");
        this.varastotunnusSyote = new TextField();
        this.poistaVarastoNappi = new Button("Poista varasto");
        
    // Asetetaan tekstien fontit
        varastoText.setId("scenes-text");
        tuoteText.setId("scenes-text");
        vuosiText.setId("scenes-text");
        varastotunnusText.setId("scenes-text");
        

    // Muotoillaan tekstikentät
        varastoSyote.setId("scenes-textbox");
        varastoSyote.setMaxWidth(80);
        tuoteSyote.setId("scenes-textbox");
        vuosiSyote.setId("scenes-textbox");
        vuosiSyote.setMaxWidth(120);
        varastotunnusSyote.setId("scenes-textbox");
        varastotunnusSyote.setMaxWidth(80);
        
    // Muotoillaan napit
        poistaTuoteNappi.setId("button-style");
        poistaTuoteNappi.setMinSize(100, 50);
        poistaVarastoNappi.setId("button-style");
        poistaVarastoNappi.setMinSize(100, 50);
        
    // Asetetaan onActionit
        poistaTuoteNappi.setOnAction((event) -> {
            tuoteDoAction();
        });
        poistaVarastoNappi.setOnAction((event) -> {
            varastoDoAction();
        });

    // Asettelun rakentaminen ja muotoilut
        asettelu.addColumn(0, varastoText, tuoteText, vuosiText);
        asettelu.addColumn(1, varastoSyote, tuoteSyote, vuosiSyote, poistaTuoteNappi);
        asettelu.addColumn(2, varastotunnusText);
        asettelu.addColumn(3, varastotunnusSyote);
        asettelu.add(poistaVarastoNappi, 3, 3);

        asettelu.setAlignment(Pos.CENTER);
        asettelu.setVgap(10);
        asettelu.setHgap(10);
        asettelu.setPadding(new Insets(50, 50, 50, 20));
        asettelu.setId("pane-background");
        asettelu.getStylesheets().addAll(this.getClass().getResource("/styles/styles.css").toExternalForm());
        
    }

    public GridPane getPane() {
        return this.asettelu;
    }

    public void tuoteDoAction() {
        varastoSyote.setId("scenes-textbox");
        tuoteSyote.setId("scenes-textbox");
        vuosiSyote.setId("scenes-textbox");

        String v = varastoSyote.getText();
        String t = tuoteSyote.getText();
        String y = vuosiSyote.getText();

        if (syotteentarkistaja.tarkistaSyote(v, "A-Zx2+1-2numeroa")) {
            if (syotteentarkistaja.tarkistaSyote(t, "kirjaimia")) {
                if (syotteentarkistaja.tarkistaSyote(y, "numero")) {
                    int vuosiLukuna = Integer.parseInt(y);
                    
                    boolean tuotePoistettu = false;
                    
                    if (tuotePoistettu) {
                    // tyhjennetään tekstikentät
                        varastoSyote.clear();
                        tuoteSyote.clear();
                        vuosiSyote.clear();
                       
                        paaikkuna.asetaIlmoitusteksti("Tuote '" + t + "' poistettu.");
                    } else {
                        paaikkuna.asetaIlmoitusteksti("Tuotetta ei löytynyt annetusta varastosta");
                    }
                } else {
                    paaikkuna.asetaIlmoitusteksti("Tarkista vuosiluku!");
                    vuosiSyote.setId("text-field-error");
                }
            } else {
                paaikkuna.asetaIlmoitusteksti("Tuotenimi voi sisältää vain kirjaimia ja välilyöntejä.\n"
                        + "Vähintään kaksi, enintään 25 merkkiä.");
                tuoteSyote.setId("text-field-error");
            }
        } else {
            paaikkuna.asetaIlmoitusteksti("Tunnus oltava muotoa 'AB123'.\n Alussa "
                        + "kaksi isoa kirjainta väliltä A-Z jonka jälkeen 1-3 numeroa.\n "
                        + "Max pituus 5 merkkiä.");
            varastoSyote.setId("text-field-error");
        }
    }

    private void varastoDoAction() {
        varastotunnusSyote.setId("scenes-textbox");
        
        String v = varastotunnusSyote.getText();
        
        if (syotteentarkistaja.tarkistaSyote(v, "A-Zx2+1-2numeroa")) {
            boolean varastoPoistettu = false;
            
            if (varastoPoistettu) {
                varastotunnusSyote.clear();
                
                paaikkuna.asetaIlmoitusteksti("Varasto '" + v + "' poistettu.");
            } else {
                paaikkuna.asetaIlmoitusteksti("Varastoa '" + v + "' ei löytynyt.");
            }
        } else {
            paaikkuna.asetaIlmoitusteksti("Tunnus oltava muotoa 'AB123'.\n Alussa "
                        + "kaksi isoa kirjainta väliltä A-Z jonka jälkeen 1-3 numeroa.\n "
                        + "Max pituus 5 merkkiä.");
            varastotunnusSyote.setId("text-field-error");
        }
    }
}
