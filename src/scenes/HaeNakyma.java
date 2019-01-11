
package scenes;

import JFrames.TableDisplay;
import java.util.ArrayList;
import java.util.Collections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import logiikka.BoardGame;
import logiikka.Player;
import logiikka.Statistics;


public class HaeNakyma {
    
    // Näkymän komponentit
    private final GridPane asettelu;
    private final Text hakusana;
    private final TextField syotekentta;
    private final Button haeNappi;
    
    // Löydettyjen listakomponentti
    private final TextArea loydetyt;
    private TableDisplay taulukkonaytto;
    
    private Statistics data;
    
    public HaeNakyma(Statistics datacontent) {
        
        this.data = datacontent;
        
        this.asettelu = new GridPane();
        this.hakusana = new Text("Keyword: ");
        this.syotekentta = new TextField();
        this.haeNappi = new Button("Search");   
        
        // Alaosan löydettyjen ilmaisinalue
        this.loydetyt = new TextArea();
        loydetyt.setMinHeight(200);
        loydetyt.setMaxHeight(400);
        loydetyt.setPadding(new Insets(30,140,30,140));
        loydetyt.setEditable(false);
        loydetyt.setId("search-results-box");
        
        // Muotoillaan komponentit
        hakusana.setId("scenes-text");
        syotekentta.setId("scenes-textbox");
        haeNappi.setId("button-style");
        haeNappi.setMinSize(100, 50);
        haeNappi.setOnAction((event) -> {
            doAction();
        });
        
        // Asettelun rakentaminen ja muotoilut
        asettelu.addColumn(0, hakusana);
        asettelu.addColumn(1, syotekentta, haeNappi);

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
        String haettava = this.syotekentta.getText();
        this.syotekentta.clear();
        this.loydetyt.clear();
        
        ArrayList<BoardGame> results = this.data.searchGames(haettava);
        
        
        listaaLoydetyt(results, haettava);
        
    }
    
    private void listaaLoydetyt(ArrayList<BoardGame> lista, String haettava) {
        Collections.sort(lista);
        if (lista.isEmpty()) {
            loydetyt.setText("No results for '" + haettava + "'.");
        } else {
            lista.stream().forEach(tuote -> {
                loydetyt.appendText(tuote.toString() + "\n");
            });
        }
        if (taulukkonaytto == null) {
            taulukkonaytto = new TableDisplay();
            taulukkonaytto.updateContent(lista);
        } else {
            taulukkonaytto.updateContent(lista);
        }
        
    }
    
    public TextArea getLoydetyt() {
        return this.loydetyt;
    }
}
