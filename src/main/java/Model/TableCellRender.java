/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author gabri
 */
public class TableCellRender implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setForeground(Color.BLACK);
        p.setOpaque(true);

        JLabel label = new JLabel((value).toString());
        label.setFocusable(true);
        label.setOpaque(true);
        label.setForeground(Color.black);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        JCheckBox check = new JCheckBox();
        check.setHorizontalAlignment(JLabel.CENTER);
        check.setVerticalAlignment(JLabel.CENTER);
        check.setOpaque(true);

        if (row % 2 == 0) {
            p.setBackground(Color.lightGray);
        }

        if (isSelected) {
            p.setBackground(table.getSelectionBackground());
        }

        if (value.getClass().equals(Integer.class)) {
            if (Integer.parseInt(value.toString()) < 0) {
                label.setForeground(Color.red);
            }
            label.setBackground(p.getBackground());
            p.add(label);
        }

        if (value.getClass().equals(Double.class)) {
            if (Double.parseDouble(value.toString()) < 0) {
                label.setForeground(Color.red);
            }
            label.setBackground(p.getBackground());
            p.add(label);
        }

        if (value.getClass().equals(Boolean.class)) {
            if (value.equals(true)) {
                check.setSelected(true);
            }
            check.setBackground(p.getBackground());
            p.add(check);
        }

        if (value.getClass().equals(String.class)) {
            label.setBackground(p.getBackground());
            p.add(label);
        }


        return p;
    }

}
