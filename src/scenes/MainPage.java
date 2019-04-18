package scenes;

import JFrames.MultiTimer;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import logiikka.Syotteentarkistaja;
import logiikka.DataAnalyzer;
import logiikka.Statistics;

public final class MainPage {

// Aloitusnäkymän peruskomponentit
    private final Stage ikkuna;
    private final MenuBar menuBar;
    private final BorderPane asettelu;
    private Menu varastoMenu, muokkaaMenu, haeMenu, timerMenu;
    private final Label toimintoteksti, ohjeteksti;

// Näkymä oliot ja syotteentarkistaja
    private final Syotteentarkistaja syotteentarkistaja;
    private final LisaaNakyma lisaaNakyma;
    private final OtaNakyma otaNakyma;
    private final HaeNakyma haeNakyma;
    private final RaporttiNakyma raporttiNakyma;
    private final TyhjennaNakyma tyhjentaja;
    private final InfoNakyma infoNakyma;
    private final PoistaNakyma poistaNakyma;
    private final SiirraNakyma siirraNakyma;
    
// Datasisältö ohjelmalle
    //private final Statistics statistics;

    public MainPage(Stage primaryStage, DataAnalyzer kasittelija, Statistics statistics) {
        this.ikkuna = primaryStage;
        ikkuna.getIcons().add(new Image("/images/jamasoft.logo.pisarajatausta.png"));
        this.syotteentarkistaja = new Syotteentarkistaja();
        this.toimintoteksti = new Label("Select function from menu.");
        this.ohjeteksti = new Label("");
        
        //this.statistics = statistics;

    // Näkymien luonnit
        this.lisaaNakyma = new LisaaNakyma(syotteentarkistaja, this);
        this.otaNakyma = new OtaNakyma(syotteentarkistaja, this);
        this.haeNakyma = new HaeNakyma(statistics);
        this.menuBar = new MenuBar();
        this.raporttiNakyma = new RaporttiNakyma();
        this.tyhjentaja = new TyhjennaNakyma(this);
        this.infoNakyma = new InfoNakyma(kasittelija);
        this.poistaNakyma = new PoistaNakyma(syotteentarkistaja, this);
        this.siirraNakyma = new SiirraNakyma(syotteentarkistaja, this);

        this.asettelu = new BorderPane();

        alustaMenuvalikko();
    }

    public void setScene() {
    // Luodaan näkymän komponentit
        Scene nakyma = new Scene(new VBox(), 750, 800);

        asettelu.getStylesheets().addAll(this.getClass().getResource("/styles/styles.css").toExternalForm());
        asettelu.setBottom(ohjeteksti);
        asettelu.setTop(toimintoteksti);
        toimintoteksti.setId("action-text");
        ohjeteksti.setTextAlignment(TextAlignment.CENTER);
        BorderPane.setAlignment(ohjeteksti, Pos.CENTER);
        BorderPane.setAlignment(toimintoteksti, Pos.CENTER);
        BorderPane.setMargin(toimintoteksti, new Insets(10, 10, 10, 10));
        BorderPane.setMargin(ohjeteksti, new Insets(300, 20, 20, 20));
        ohjeteksti.setText("Welcome!\nBoard Game Controller");
        ohjeteksti.setId("info-text");
        ohjeteksti.setPadding(new Insets(5, 5, 5, 5));

    // Asetetaan asettelu näkymään ja tehdään säädöt
        ((VBox) nakyma.getRoot()).getStylesheets().addAll(this.getClass()
                .getResource("/styles/styles.css").toExternalForm());
        ((VBox) nakyma.getRoot()).getChildren().addAll(menuBar, asettelu);
        ((VBox) nakyma.getRoot()).setId("default-background-image");
        
        this.ikkuna.setResizable(true);
        this.ikkuna.setScene(nakyma);
        this.ikkuna.show();
    }

    public void alustaMenuvalikko() {
    // Building Main Menu
        varastoMenu = new Menu("Menu");
        MenuItem lopeta = new MenuItem("Lopeta");
        lopeta.setOnAction((ActionEvent event) -> {
            
        });
        
        MenuItem raportti = new MenuItem("Raportti");
        raportti.setOnAction((event) -> {
            alustaNakyma("Varastotilanne", "");
            asettelu.setCenter(raporttiNakyma.getPane());
        });
        
        MenuItem tallenna = new MenuItem("Tallenna");
        tallenna.setAccelerator(KeyCombination.keyCombination("Ctrl+T"));
        tallenna.setOnAction((ActionEvent event) -> {
            asetaIlmoitusteksti("Muutokset tallennettu!");
        });
        
        varastoMenu.getItems().addAll(tallenna, raportti, lopeta);

    // Building Game menu
        muokkaaMenu = new Menu("Game");
        MenuItem lisaa = new MenuItem("New Game");
        lisaa.setOnAction((ActionEvent event) -> {
            alustaNakyma("-Set New Game-", "");
            asettelu.setCenter(lisaaNakyma.getPane());
        });
        MenuItem ota = new MenuItem("Add Player");
        ota.setOnAction((ActionEvent event) -> {
            alustaNakyma("-Add Player-", "");
            asettelu.setCenter(otaNakyma.getPane());
        });
        MenuItem tyhjenna = new MenuItem("-empty-");
        tyhjenna.setOnAction((event) -> {
            alustaNakyma("-empty-", "");
            asettelu.setCenter(tyhjentaja.getPane());
        });
        MenuItem poista = new MenuItem("-empty-");
        poista.setOnAction((ActionEvent event) -> {
            alustaNakyma("-empty-", "");
            asettelu.setCenter(poistaNakyma.getPane());
        });
        MenuItem siirra = new MenuItem("-empty-");
        siirra.setOnAction((ActionEvent event) -> {
            alustaNakyma("-empty-", "");
            asettelu.setCenter(siirraNakyma.getPane());
        });

        muokkaaMenu.getItems().addAll(lisaa, ota, siirra, poista, tyhjenna);
        
    // Building StopWatch menu
    timerMenu = new Menu("Timer");
    MenuItem setTimer = new MenuItem("Set new timer");
    setTimer.setOnAction((event) -> {
            asetaToimintoteksti("-Set New Timer-");
            asetaIlmoitusteksti("Starting timer in new window...");
            MultiTimer multitimer = new MultiTimer();
            multitimer.setVisible(true);
        });
    timerMenu.getItems().addAll(setTimer);

    // Building Hae menu
        haeMenu = new Menu("Search");
        MenuItem pelaajaHaku = new MenuItem("Player");
        pelaajaHaku.setOnAction((event) -> {
            asettelu.setCenter(haeNakyma.getPane());
            asettelu.setBottom(haeNakyma.getLoydetyt());
            asetaToimintoteksti("-Search Players-");
            asetaIlmoitusteksti("");
        });
        MenuItem pelihaku = new MenuItem("Game");
        pelihaku.setOnAction((event) -> {
            asettelu.setCenter(haeNakyma.getPane());
            asettelu.setBottom(haeNakyma.getLoydetyt());
            asetaToimintoteksti("-Search Games-");
            asetaIlmoitusteksti("");
        });
        MenuItem info = new MenuItem("info");
        info.setOnAction((event) -> {
            alustaNakyma("-Instructions-", "");
            asettelu.setCenter(infoNakyma.getPane());
        });
        haeMenu.getItems().addAll(pelihaku, pelaajaHaku, info);

    // Adding menus to MenuBar
        menuBar.setId("menubar-style");
        menuBar.getMenus().addAll(varastoMenu, muokkaaMenu, timerMenu, haeMenu);
    }

    public void asetaIlmoitusteksti(String teksti) {
        BorderPane.setMargin(ohjeteksti, new Insets(20, 20, 20, 20));
        ohjeteksti.setText(teksti);
        ohjeteksti.setStyle("-fx-background-color: white;");
    }

    public void asetaToimintoteksti(String toiminto) {
        this.toimintoteksti.setText(toiminto);
        this.toimintoteksti.setId("action-text");
    }

    public void asetaPerusnakyma(String ilmoitusteksti) {
        asettelu.getChildren().clear();
        this.setScene();
        asetaIlmoitusteksti(ilmoitusteksti);
        asetaToimintoteksti("Select function from menu.");
    }

    public void alustaNakyma(String toimintoteksti, String ilmoitusteksti) {
        asetaToimintoteksti(toimintoteksti);
        asetaIlmoitusteksti(ilmoitusteksti);
        asettelu.setBottom(ohjeteksti);
        ohjeteksti.setStyle("-fx-background-color: transparent;");
    }
    
    public String randomBackgroundImage() {
        String[] kuvat = new String[]{"BGC_official-logo.jpg"};
        Random random = new Random();

        return kuvat[random.nextInt(1)];
    }
}
