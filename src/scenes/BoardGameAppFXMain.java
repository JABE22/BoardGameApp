package scenes;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import logiikka.BoardGame;
import logiikka.DataAnalyzer;
import logiikka.Statistics;

public class BoardGameAppFXMain extends Application {

    @Override
    public void start(Stage primaryStage) {
    // Luodaan tiedostonlukija ja luetaan datatiedostot ohjelmaan
        DataAnalyzer dataAnalyzer = new DataAnalyzer();
        Statistics statistics = new Statistics(dataAnalyzer);
        statistics.readData();
        
        // Test printing
        ArrayList<BoardGame> games = statistics.getGames();
        for (BoardGame game : games) {
            System.out.println(game);
        }
        
        
        
        MainPage startScene = new MainPage(primaryStage, dataAnalyzer, statistics);

    // luodaan sopiva kehys komponenteille
        StackPane kehys = new StackPane();
        kehys.setId("pane");

    // Luodaan komponentit ja muotoillaan ne
        Text repliikki = new Text("Control Your Game");
        repliikki.setId("fancytext");

    // Asetetaan komponentit kehykseen
        kehys.getChildren().add(repliikki);
        StackPane.setAlignment(repliikki, Pos.BOTTOM_CENTER);
        kehys.setPadding(new Insets(10, 50, 40, 50));
        kehys.getStylesheets().addAll(this.getClass().getResource("/styles/styles.css").toExternalForm());

    // Asetetaan kehys näkymään ja tehdään näkymän (scene) säädöt
        Scene scene = new Scene(kehys, 600, 600, Color.DARKGREY);

        primaryStage.setTitle("BoardGameApp");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e -> { System.exit(0); });
        primaryStage.show();

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                startScene.setScene();
            }
        });
    }

    public static void main(String[] args) {
        launch(BoardGameAppFXMain.class);
    }
}
