package com.zy.designpattern.factory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowsButton implements Button{
    JPanel jPanel = new  JPanel();
    JFrame jFrame = new JFrame();
    JButton jButton ;
    @Override
    public void render() {
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Windows98");
        label.setOpaque(true);
        label.setBackground(new Color(235,233,126));
        label.setFont(new Font("Dialog", Font.PLAIN, 12));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setLayout(new FlowLayout(FlowLayout.CENTER));
        jFrame.getContentPane().add(label);
        jPanel.add(label);
        onClick();
        jPanel.add(jButton);

        jFrame.setSize(320,200);
        jFrame.setVisible(true);
        onClick();
    }

    @Override
    public void onClick() {
        jButton = new JButton("Exit");
        jButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
                System.exit(0);
            }
        });

    }
}
