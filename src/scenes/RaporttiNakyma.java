
package scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;


class RaporttiNakyma {
    private final TextArea textArea;
    
    private final FlowPane asettelu;
    
    public RaporttiNakyma() {
        this.textArea = new TextArea();
        this.asettelu = new FlowPane();
        
        textArea.setId("report-text");
        textArea.setPadding(new Insets(10,10,10,10));
        textArea.setEditable(false);
        textArea.autosize();
        textArea.setMinHeight(600);
        
        asettelu.getChildren().add(textArea);
        asettelu.setPadding(new Insets(30,20,30,20));
        asettelu.setAlignment(Pos.CENTER);
        asettelu.setId("pane-background");
        asettelu.getStylesheets().addAll(this.getClass().getResource("/styles/styles.css").toExternalForm());
          
    }
    
    public FlowPane getPane() {
        updateLabelText();
        return asettelu;
    }
    
    public void updateLabelText() {
        textArea.clear();
        
    }
}
