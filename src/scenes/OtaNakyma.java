package scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import logiikka.Syotteentarkistaja;

public class OtaNakyma {

    private final MainPage aloitusnakyma;
    private final Syotteentarkistaja syotteentarkistaja;
    private final GridPane asettelu;
    // Näkymän tekstikomponentit
    private final Text tuote, vuosikerta, maaraL;
    // Näkymän tekstikentät
    private final TextField tuotenimi, vuosi, maara;

    public OtaNakyma(Syotteentarkistaja syotteentarkistaja, MainPage aloitusnakyma) {
        this.aloitusnakyma = aloitusnakyma;
        this.syotteentarkistaja = syotteentarkistaja;
        this.asettelu = new GridPane();

        this.tuote = new Text("Tuotenimi: ");
        this.vuosikerta = new Text("Vuosi: ");
        this.maaraL = new Text("Maara (litraa): ");

        this.tuotenimi = new TextField();
        this.vuosi = new TextField();
        this.maara = new TextField();

        // Asetetaan tekstien fontit
        tuote.setId("scenes-text");
        vuosikerta.setId("scenes-text");
        maaraL.setId("scenes-text");

        // Muotoillaan tekstikentät
        tuotenimi.setId("scenes-textbox");
        vuosi.setId("scenes-textbox");
        vuosi.setMaxWidth(120);
        maara.setId("scenes-textbox");
        maara.setMaxWidth(80);

        // Luodaan ja muotoillaan "lisää" nappi
        Button otaNappi = new Button("Ota varastosta");
        otaNappi.setId("button-style");
        otaNappi.setMinSize(100, 50);
        otaNappi.setOnAction((event) -> {
            doAction();
        });
        otaNappi.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                doAction();
            }
        });

        // Asettelun rakentaminen ja muotoilut
        asettelu.addColumn(0, tuote, vuosikerta, maaraL);
        asettelu.addColumn(1, tuotenimi, vuosi, maara, otaNappi);

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
        tuotenimi.setId("scenes-textbox");
        vuosi.setId("scenes-textbox");
        maara.setId("scenes-textbox");

        String t = tuotenimi.getText();
        String y = vuosi.getText();
        String m = maara.getText();

        if (syotteentarkistaja.tarkistaSyote(t, "kirjaimia")) {
            if (syotteentarkistaja.tarkistaSyote(y, "numero")) {
                int vuosiLukuna = Integer.parseInt(y);
                if (syotteentarkistaja.tarkistaSyote(m, "liukuluku")) {
                    double maaraLukuna = Double.parseDouble(m);
                    
                } else {
                    maara.setId("text-field-error");
                    aloitusnakyma.asetaIlmoitusteksti("Tarkista määrä!");
                }
            } else {
                vuosi.setId("text-field-error");
                aloitusnakyma.asetaIlmoitusteksti("Tarkista vuosi!");
            }
        } else {
            tuotenimi.setId("text-field-error");
            aloitusnakyma.asetaIlmoitusteksti("Tarkista ettei tuotenimi sisällä erikoismerkkejä tai numeroita!");
        }

    }
}
