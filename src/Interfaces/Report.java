/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author User
 */
public class Report {

    public ArrayList<String> report(ArrayList<String> arrlist) {

        String data[][] = new String[100][100];
        int x = 0;
        int y = 0;
        for (int i = 0; i < arrlist.size(); i++) {
            if (x == 4) {
                x = 0;
                y++;
            }
            data[y][x] = arrlist.get(i);
            x++;
        }
        String col[] = {"Project", "NPV", "ROI", "Payback Period (Year)"};

        final JTable table = new JTable(data, col) {
            public boolean getScrollableTracksViewportWidth() {
                return getPreferredSize().width < getParent().getWidth();
            }
        };
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        final JScrollPane scrollPane = new JScrollPane(table);

        JFrame frame = new JFrame("Report");
        //JPanel panel = new JPanel();
        frame.add(scrollPane);
        frame.pack();
        frame.setSize(600, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return arrlist;

    }
}
