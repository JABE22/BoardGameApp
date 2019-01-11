/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import java.time.Duration;
import java.util.Objects;

/**
 *
 * @author Jarno Matarmaa ( University of Tampere )
 */
public class BoardGame implements Comparable<BoardGame> {
    private final String name;
    private final Duration duration;
    private final int playersMin;
    private final int playersMax;
    private final int level;
    
    public BoardGame(String name, Duration duration, int playersMin, int playersMax, int level) {
        this.name = name;
        this.duration = duration;
        this.playersMin = playersMin;
        this.playersMax = playersMax;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public Duration getDuration() {
        return duration;
    }

    public int getPlayersMax() {
        return playersMax;
    }

    public int getPlayersMin() {
        return playersMin;
    }

    public int getLevel() {
        return level;
    }
    
    public String[] getDetailsAsTable() {
    // Type convertions
        String dur = this.duration.toString();
        String plrMin = Integer.toString(this.playersMin);
        String plrMax = Integer.toString(this.playersMax);  
        String lvl = Integer.toString(this.level);
        
        String[] details = {name, dur, plrMin, plrMax, lvl};
        
        return details;
    } 

    @Override
    public String toString() {
        return "BoardGame{" + "name=" + name + ", duration=" + duration + ", playersMax=" + playersMax + ", playersMin=" + playersMin + ", level=" + level + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.duration);
        hash = 97 * hash + this.playersMax;
        hash = 97 * hash + this.playersMin;
        hash = 97 * hash + this.level;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BoardGame other = (BoardGame) obj;
        if (this.playersMax != other.playersMax) {
            return false;
        }
        if (this.playersMin != other.playersMin) {
            return false;
        }
        if (this.level != other.level) {
            return false;
        }
        if (!Objects.equals(this.duration, other.duration)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(BoardGame comparable) {
         if (comparable != null) {
            return this.name.compareTo(comparable.getName());
        }
        return 0;
    }

    
    
}
