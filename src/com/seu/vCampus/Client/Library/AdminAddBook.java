package com.seu.vCampus.Client.Library;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminAddBook {
    private JTextField BNameText;
    private JTextField ISBNText;
    private JTextField AuthorText;
    private JButton ApplyButton;
    private JLabel ISBNLable;
    private JLabel NameLable;
    private JLabel AuthorLable;
    private JPanel AddMPanel;
    private JTextField TypeText;
    private JLabel TypeLable;

    public AdminAddBook() {
        ApplyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Object[] data={
                        BNameText.getText(),AuthorText.getText(),ISBNText.getText(),TypeText.getText()
                };
                AdminLib.AModel.addRow(data);
            }
        });
    }

    protected static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("AdminAddBook");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //Create and set up the content pane.
        frame.setContentPane(new AdminAddBook().AddMPanel);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}
