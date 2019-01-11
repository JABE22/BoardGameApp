package scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import logiikka.Syotteentarkistaja;

public class SiirraNakyma {

    private final MainPage paaikkuna;
    private final Syotteentarkistaja syotteentarkistaja;

// Näkymän komponentit
    private final GridPane asettelu;

// Vasemman puoleiset komponentit
    private final Text varastoText, tuoteText, vuosiText, kohdeText;
    private final TextField varastoSyote, tuoteSyote, vuosiSyote, kohdeSyote;
    private final Button siirraTuoteNappi;

// Oikean puoleiset komponentit
    private final Text varastotunnusText, kohdevarastoText;
    private final TextField varastotunnusSyote, kohdevarastoSyote;
    private final Button siirraVarastoNappi;

    public SiirraNakyma(Syotteentarkistaja syotteentarkistaja,
            MainPage paaikkuna) {
        this.paaikkuna = paaikkuna;
        this.syotteentarkistaja = syotteentarkistaja;
        this.asettelu = new GridPane();

    // Vasemman puoleiset komponentit 
        this.varastoText = new Text("Varastotunnus:");
        this.tuoteText = new Text("Tuotenimi:");
        this.vuosiText = new Text("Vuosi:");
        this.kohdeText = new Text("Kohdevarasto:");
        this.varastoSyote = new TextField();
        this.tuoteSyote = new TextField();
        this.vuosiSyote = new TextField();
        this.kohdeSyote = new TextField();
        this.siirraTuoteNappi = new Button("Siirrä tuote");

    // Oikean puoleiset komponentit
        this.varastotunnusText = new Text("Varastotunnus:");
        this.kohdevarastoText = new Text("Kohdevarasto:");
        this.varastotunnusSyote = new TextField();
        this.kohdevarastoSyote = new TextField();
        this.siirraVarastoNappi = new Button("Siirrä varasto");

    // Asetetaan tekstien fontit
        varastoText.setId("scenes-text");
        tuoteText.setId("scenes-text");
        vuosiText.setId("scenes-text");
        kohdeText.setId("scenes-text");
        varastotunnusText.setId("scenes-text");
        kohdevarastoText.setId("scenes-text");

    // Muotoillaan tekstikentät
        varastoSyote.setId("scenes-textbox");
        varastoSyote.setMaxWidth(80);
        tuoteSyote.setId("scenes-textbox");
        vuosiSyote.setId("scenes-textbox");
        vuosiSyote.setMaxWidth(120);
        kohdeSyote.setId("scenes-textbox");
        kohdeSyote.setMaxWidth(80);
        varastotunnusSyote.setId("scenes-textbox");
        varastotunnusSyote.setMaxWidth(80);
        kohdevarastoSyote.setId("scenes-textbox");
        kohdevarastoSyote.setMaxWidth(80);

    // Muotoillaan napit
        siirraTuoteNappi.setId("button-style");
        siirraTuoteNappi.setMinSize(100, 50);
        siirraVarastoNappi.setId("button-style");
        siirraVarastoNappi.setMinSize(100, 50);

    // Asetetaan onActionit
        siirraTuoteNappi.setOnAction((event) -> {
            tuoteDoAction();
        });
        siirraVarastoNappi.setOnAction((event) -> {
            varastoDoAction();
        });

    // Asettelun rakentaminen ja muotoilut
        asettelu.addColumn(0, varastoText, tuoteText, vuosiText, kohdeText);
        asettelu.addColumn(1, varastoSyote, tuoteSyote, vuosiSyote, kohdeSyote, siirraTuoteNappi);
        asettelu.addColumn(2, varastotunnusText, kohdevarastoText);
        asettelu.addColumn(3, varastotunnusSyote, kohdevarastoSyote);
        asettelu.add(siirraVarastoNappi, 3, 4);

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
        kohdeSyote.setId("scenes-textbox");

        String v = varastoSyote.getText();
        String t = tuoteSyote.getText();
        String y = vuosiSyote.getText();
        String k = kohdeSyote.getText();

        if (syotteentarkistaja.tarkistaSyote(v, "A-Zx2+1-2numeroa")) {
            if (syotteentarkistaja.tarkistaSyote(t, "kirjaimia")) {
                if (syotteentarkistaja.tarkistaSyote(y, "numero")) {
                    int vuosiLukuna = Integer.parseInt(y);
                    if (syotteentarkistaja.tarkistaSyote(k, "A-Zx2+1-2numeroa")) {
                        boolean tuoteSiirretty = false;

                        if (tuoteSiirretty) {
                        // tyhjennetään tekstikentät
                            varastoSyote.clear();
                            tuoteSyote.clear();
                            vuosiSyote.clear();
                            kohdeSyote.clear();

                            paaikkuna.asetaIlmoitusteksti("Tuote '" + t + "' siirretty varastoon " + k + ".");
                        } else {
                            paaikkuna.asetaIlmoitusteksti("Tuotetta ei löytynyt annetusta varastosta");
                        }
                    } else {
                        paaikkuna.asetaIlmoitusteksti("Tarkista kohdevaraston tunnus!");
                        kohdeSyote.setId("text-field-error");
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
        kohdevarastoSyote.setId("scenes-textbox");

        String v = varastotunnusSyote.getText();
        String k = kohdevarastoSyote.getText();

        if (syotteentarkistaja.tarkistaSyote(v, "A-Zx2+1-2numeroa")) {
            if (syotteentarkistaja.tarkistaSyote(k, "A-Zx2+1-2numeroa")) {
                boolean varastoSiirretty = false;

                if (varastoSiirretty) {
                    varastotunnusSyote.clear();
                    kohdevarastoSyote.clear();

                    paaikkuna.asetaIlmoitusteksti("Varasto '" + v + "' siirretty varastoon '" + k + "'.");
                } else {
                    paaikkuna.asetaIlmoitusteksti("Siirrettävää varastoa '" + v + "' ei löytynyt.");
                }
            } else {
                paaikkuna.asetaIlmoitusteksti("Tunnus oltava muotoa 'AB123'.\n Alussa "
                        + "kaksi isoa kirjainta väliltä A-Z jonka jälkeen 1-3 numeroa.\n "
                        + "Max pituus 5 merkkiä.");
                kohdevarastoSyote.setId("text-field-error");
            }
        } else {
            paaikkuna.asetaIlmoitusteksti("Tunnus oltava muotoa 'AB123'.\n Alussa "
                        + "kaksi isoa kirjainta väliltä A-Z jonka jälkeen 1-3 numeroa.\n "
                        + "Max pituus 5 merkkiä.");
            varastotunnusSyote.setId("text-field-error");
        }
    }
}
