package com.seu.vCampus.Client.AcademicAffairs.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Confirm extends JFrame {

    public Confirm(){
        setBounds(100, 100, 425, 271);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        JLabel label = new JLabel("\u786E\u5B9A\u9009\u62E9");
        label.setBounds(55, 81, 65, 18);
        getContentPane().add(label);

        JLabel label_1 = new JLabel("\uFF1F");
        label_1.setBounds(368, 81, 72, 18);
        getContentPane().add(label_1);

        JButton yes = new JButton("确定");
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        yes.setBounds(55, 145, 113, 27);
        getContentPane().add(yes);

        JButton cancel = new JButton("取消");
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

            }
        });
        cancel.setBounds(241, 145, 113, 27);
        getContentPane().add(cancel);

        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(134, 81, 204, 18);
        getContentPane().add(lblNewLabel);
    }
}
