/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Jarno Matarmaa ( University of Tampere )
 */
public class GameDay {
    
    private final BoardGame boardgame;
    private final Player homePlayer;
    private Date gameDate;
    private final ArrayList<Player> players;
    
    public GameDay(BoardGame boardgame, Player homePlayer) {
        this.boardgame = boardgame;
        this.homePlayer = homePlayer;   
        this.players = new ArrayList<>();
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }
    
    public void addPlayer(Player player) {
        if (!this.players.contains(player)) {
            this.players.add(player);
        }
    }

    public BoardGame getBoardgame() {
        return boardgame;
    }

    public Player getHomePlayer() {
        return homePlayer;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "GameDay{" + "Boardgame=" + boardgame + ", Home Player=" 
                + homePlayer + ", Game Date=" + gameDate + ", Players=" 
                + players + '}';
    }
}
