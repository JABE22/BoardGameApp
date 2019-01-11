
package logiikka;

import java.time.Duration;
import java.util.Objects;
import java.util.Timer;


public class Player implements Comparable<Player> {
    private final String name;
    private int activeYears;
    private final Timer timer;
    private Duration totalPlayingTime;
    
    public Player(String nimi, int activeYears, Duration totalPlayingTime ) {
        this.name = nimi;
        this.activeYears = activeYears;
        this.totalPlayingTime = Duration.ofMinutes(20);
        this.timer = new Timer();
        
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getYears() {
        return this.activeYears;
    }
    
    public String[] getTiedotTaulukkona() {
        String[] tiedot = new String[4];
        tiedot[0] = name;
        tiedot[1] = "" + activeYears;
        
        return tiedot;
    }
    
    @Override
    public String toString() {
        return this.name + " " + this.activeYears;
    }

    @Override
    public int compareTo(Player comparable) {
        if (comparable != null) {
            return this.name.compareTo(comparable.getName());
        }
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
        Player verrattava = (Player) olio;
        if (this.activeYears != verrattava.getYears()) {
            return false;
        }
        return this.name.equals(verrattava.getName());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + this.activeYears;
        return hash;
    }
}
