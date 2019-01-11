package JFrames;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import logiikka.BoardGame;
import logiikka.Player;

public class TableDisplay {
    private final JFrame ikkuna;
    private final JScrollPane kehys;
    private int rivi;
    
    public TableDisplay() {
        ikkuna = new JFrame();
        ikkuna.setTitle("Inventory Management");
        ikkuna.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ikkuna.setSize(800, 400);
        ikkuna.setLocationRelativeTo(null); 
        kehys = new JScrollPane();
        ikkuna.add(kehys);
        
        rivi = 0;
    }

    public void nayta() {
        ikkuna.setVisible(true);
    }

    public void updateContent(ArrayList<BoardGame> list) {
        rivi = 0;
        String[] columns = new String[]{
            "Game", "Playtime", "Players min", "Players max", "level"
        };

        String[][] data = new String[list.size()][5];

        list.stream().map(game -> game.getDetailsAsTable())
                .forEach(taulukko -> {
                    for (int i = 0; i < 5; i++) {
                        data[rivi][i] = taulukko[i];
                    }
                    rivi++;
                });

        JTable table = new JTable(data, columns);
        table.setFont(new Font("Tahoma", Font.PLAIN, 20));
        table.getTableHeader().setFont(new Font("Calibri", Font.ROMAN_BASELINE, 16));
        table.getTableHeader().setBackground(Color.ORANGE);
        table.setRowHeight(30);
        table.setAutoCreateRowSorter(true);
        
        kehys.getViewport().removeAll();
        kehys.getViewport().add(table);
        nayta();
    }
}
