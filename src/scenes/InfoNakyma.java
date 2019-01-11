
package scenes;

import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import logiikka.DataAnalyzer;


public final class InfoNakyma {
    private final DataAnalyzer tiedostonkasittelija;
    private final BorderPane asettelu;
    private final TextArea tekstialue;
    
    public InfoNakyma(DataAnalyzer tiedostonkasittelija) {
        this.tiedostonkasittelija = tiedostonkasittelija;
        this.asettelu = new BorderPane();
        this.tekstialue = new TextArea();
        tekstialue.setEditable(false);
        tekstialue.setId("infoscene-text");
        tekstialue.setScrollTop(Double.MAX_VALUE);
        
        asettelu.setCenter(tekstialue);
        asettelu.setPadding(new Insets(20,60,20,60));
        asettelu.setMinHeight(700);
    }
    
    public void asetaInfoteksti() {
        tiedostonkasittelija.lueIsoTiedosto("ohjeita.txt").stream()
                .forEach(rivi -> tekstialue.appendText(rivi + "\n"));
    }
    
    public BorderPane getPane() {
        asetaInfoteksti();
        return this.asettelu;
    }
}
