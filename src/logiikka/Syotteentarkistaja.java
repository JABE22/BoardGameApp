
package logiikka;


public class Syotteentarkistaja {
    
    public boolean tarkistaSyote(String syote, String ehto) {
        if (ehto.equals("numero")) {
            if (syote.matches("[0-9]+")) {
                return true;
            }
        } else if (ehto.equals("A-Zx2+1-2numeroa")) {
            if (syote.matches("[A-Z]{2}[0-9]{1,2}")) {
                return true;
            }
        } else if (ehto.equals("kirjaimia")) {
            if (syote.matches("[a-ö| |A-Ö]{2,25}")) {
                return true;
            }
        } else if (ehto.equals("liukuluku")) {
            if (syote.matches("([0-9]+(\\.){0,1}[0-9]{1})|[1-9]{1}[0-9]{0,2}")) {
                return true;
            }
        }
        return false;
    }
}
