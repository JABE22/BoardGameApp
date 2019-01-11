package scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;

public class TyhjennaNakyma {

    private final MainPage aloitusnakyma;
    private final BorderPane asettelu;
    private final HBox nappiasettelu;
    // Näkymän komponentit
    private final Label varoitusteksti;
    private final Button yesButton, noButton;

    public TyhjennaNakyma(MainPage aloitusnakyma) {
        this.aloitusnakyma = aloitusnakyma;
        this.asettelu = new BorderPane();
        this.nappiasettelu = new HBox();

        varoitusteksti = new Label("VAROITUS!!!\nVaraston kaikki sisältö poistetaan "
                + "pysyvästi!\n\nHaluatko jatkaa?");
        varoitusteksti.setId("warning-text");
        varoitusteksti.setTextAlignment(TextAlignment.CENTER);

        yesButton = new Button("Kyllä");
        noButton = new Button("Ei");
        yesButton.setId("button-style");
        noButton.setId("button-style");
        yesButton.setMinSize(100, 40);
        noButton.setMinSize(100, 40);
        doAction(yesButton.getText());
        doAction(noButton.getText());

        nappiasettelu.getChildren().addAll(yesButton, noButton);
        nappiasettelu.setAlignment(Pos.CENTER);
        nappiasettelu.setSpacing(60);
        nappiasettelu.setPadding(new Insets(20, 20, 20, 20));
        nappiasettelu.setStyle("-fx-background-color: lightgrey");

        asettelu.setCenter(varoitusteksti);
        asettelu.setBottom(nappiasettelu);
        asettelu.setId("pane-background");
        asettelu.getStylesheets().addAll(this.getClass().getResource("/styles/styles.css").toExternalForm());

    }

    public BorderPane getPane() {
        aloitusnakyma.asetaIlmoitusteksti("Huom! Lue tyhjentämisen ohjeet ennen tämän toiminnon käyttöä.");
        return this.asettelu;
    }

    private void doAction(String text) {
        if (text.equals("Kyllä")) {
            yesButton.setOnAction((event) -> {
                aloitusnakyma.asetaPerusnakyma("Varasto tyhjennetty.");
            });
        } else {
            noButton.setOnAction((event) -> {
                aloitusnakyma.asetaPerusnakyma("Varaston tyhjennys peruttu");
            });
        }
    }
}
