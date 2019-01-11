
package logiikka;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Objects;


public class Statistics implements Comparable<Statistics> {

    private final DataAnalyzer dataAnalyzer;
    private final ArrayList<Player> players;
    private final ArrayList<BoardGame> games;

    public Statistics(DataAnalyzer dataAnalyzer) {
        this.dataAnalyzer = dataAnalyzer;
        this.players = new ArrayList<>();
        this.games = new ArrayList<>();

    }
    
    public void readData() {
        readPlayerDataFromTextFile();
        readGameDataFromTextFile();
    }
    
    public void readPlayerDataFromTextFile() {
        dataAnalyzer.readFile("src/textfiles/PlayerData.txt").stream()
                .forEach(row -> {
                String[] data = row.split("/");
                
                String name = data[0];
                int activeYears = Integer.parseInt(data[1]);
                int totalPlayTime = Integer.parseInt(data[2]);
                Duration playTime = Duration.ofMinutes(totalPlayTime);
                
                Player player = new Player(name, activeYears, playTime);
                
                });
   
    }
    
    public void readGameDataFromTextFile() {
        dataAnalyzer.readFile("src/textfiles/GameData.txt").stream()
                .forEach(row -> {
                String[] data = row.split("/");
                
                String gamename = data[0];
                int playersMin = Integer.parseInt(data[1]);
                int playersMax = Integer.parseInt(data[2]);
                int duration = Integer.parseInt(data[3]);
                int level = Integer.parseInt(data[4]);
                
                Duration playTime = Duration.ofMinutes(duration);
                
                BoardGame boardGame = new BoardGame(
                        gamename, playTime, playersMin, playersMax, level);
                
                this.games.add(boardGame);
                
                });
   
    }
    
    public void addPlayer(Player player) {
        this.players.add(player);
    }
    
    public void addGame(BoardGame game) {
        this.games.add(game);
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }
    
    public ArrayList<BoardGame> getGames() {
        return this.games;
    }
    
    public ArrayList<BoardGame> searchGames(String search) {
        ArrayList<BoardGame> findings = new ArrayList<>();
        this.games.stream().forEach(game -> {
            if (game.getName().contains(search)) {
                findings.add(game);
            }
        });
        
        return findings;
    }
    
    
    @Override
    public int compareTo(Statistics verrattava) {      
        return 0;
    }  
    
    @Override
    public boolean equals(Object olio) {
        if (olio == null) {
            return false;
        }
        if (getClass() != olio.getClass()) {
            return false;
        }
        Statistics comparable = (Statistics) olio;
        
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(1);
        return hash;
    }
}
