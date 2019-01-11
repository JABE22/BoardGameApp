package scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import logiikka.Syotteentarkistaja;

public class LisaaNakyma {

    private final MainPage aloitusnakyma;
    private final Syotteentarkistaja syotteentarkistaja;
    private final GridPane asettelu;
    // Näkymän tekstikomponentit
    private final Text varastoT, tuote, vuosikerta, maaraL;
    // Näkymän tekstikentät
    private final TextField varastotunnus, tuotenimi, vuosi, maara;

    public LisaaNakyma(Syotteentarkistaja syotteentarkistaja, MainPage aloitusnakyma) {
        this.aloitusnakyma = aloitusnakyma;
        this.syotteentarkistaja = syotteentarkistaja;
        this.asettelu = new GridPane();

        this.varastoT = new Text("Varastotunnus: ");
        this.tuote = new Text("Tuotenimi: ");
        this.vuosikerta = new Text("Vuosi: ");
        this.maaraL = new Text("Maara (litraa): ");

        this.varastotunnus = new TextField();
        this.tuotenimi = new TextField();
        this.vuosi = new TextField();
        this.maara = new TextField();

        // Asetetaan tekstien fontit
        varastoT.setId("scenes-text");
        tuote.setId("scenes-text");
        vuosikerta.setId("scenes-text");
        maaraL.setId("scenes-text");

        // Muotoillaan tekstikentät
        varastotunnus.setId("scenes-textbox");
        varastotunnus.setMaxWidth(80);
        tuotenimi.setId("scenes-textbox");
        vuosi.setId("scenes-textbox");
        vuosi.setMaxWidth(120);
        maara.setId("scenes-textbox");
        maara.setMaxWidth(80);

        // Luodaan ja muotoillaan "lisää" nappi
        Button lisaaNappi = new Button("Lisää");
        lisaaNappi.setId("button-style");
        lisaaNappi.setMinSize(100, 50);
        lisaaNappi.setOnAction((event) -> {
            doAction();
        });
        lisaaNappi.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                doAction();
            }
        });
        
        // Asettelun rakentaminen ja muotoilut
        asettelu.addColumn(0, varastoT, tuote, vuosikerta, maaraL);
        asettelu.addColumn(1, varastotunnus, tuotenimi, vuosi, maara, lisaaNappi);

        asettelu.setAlignment(Pos.CENTER);
        asettelu.setVgap(10);
        asettelu.setHgap(10);
        asettelu.setPadding(new Insets(50, 50, 50, 20));
        asettelu.setId("pane-background");
        asettelu.getStylesheets().addAll(this.getClass().getResource("/styles/styles.css").toExternalForm());
        asettelu.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                doAction();
            }
        });
    }

    public GridPane getPane() {
        return this.asettelu;
    }

    public void doAction() {
        varastotunnus.setId("scenes-textbox");
        tuotenimi.setId("scenes-textbox");
        vuosi.setId("scenes-textbox");
        maara.setId("scenes-textbox");

        String v = varastotunnus.getText();
        String t = tuotenimi.getText();
        String y = vuosi.getText();
        String m = maara.getText();

        if (syotteentarkistaja.tarkistaSyote(v, "A-Zx2+1-2numeroa")) {
            if (syotteentarkistaja.tarkistaSyote(t, "kirjaimia")) {
                if (syotteentarkistaja.tarkistaSyote(y, "numero")) {
                    int vuosiLukuna = Integer.parseInt(y);
                    if (syotteentarkistaja.tarkistaSyote(m, "liukuluku")) {
                        double maaraLukuna = Double.parseDouble(m);

                        varastotunnus.clear();
                        tuotenimi.clear();
                        vuosi.clear();
                        maara.clear();

                        aloitusnakyma.asetaIlmoitusteksti("Tuote " + t + " lisättiin.");

                    } else {
                        aloitusnakyma.asetaIlmoitusteksti("Tarkista määrä!");
                        maara.setId("text-field-error");
                    }
                } else {
                    aloitusnakyma.asetaIlmoitusteksti("Tarkista vuosiluku!");
                    vuosi.setId("text-field-error");

                }
            } else {
                aloitusnakyma.asetaIlmoitusteksti("Tuotenimi voi sisältää vain kirjaimia ja välilyöntejä.\n"
                        + "Vähintään kaksi, enintään 25 merkkiä.");
                tuotenimi.setId("text-field-error");
            }
        } else {
            aloitusnakyma.asetaIlmoitusteksti("Tunnus oltava muotoa 'AB123'.\n Alussa "
                        + "kaksi isoa kirjainta väliltä A-Z jonka jälkeen 1-3 numeroa.\n "
                        + "Max pituus 5 merkkiä.");
            varastotunnus.setId("text-field-error");
        }
    }
}
