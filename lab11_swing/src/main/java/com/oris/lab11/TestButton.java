package com.oris.lab11;

import javax.swing.*;

public class TestButton extends JFrame {
    private JButton button1;
    private JButton button2;
    private JTextField textField;
    private JLabel label1;
    private JLabel label2;

    public TestButton(){
        super("example two buttons");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setLayout(null);
        textField = new JTextField();
        textField.setBounds(20,20,200,30);
        button1 = new JButton("button 1");
        button1.setBounds(20,50,200,30);
        button2 = new JButton("<html><font color=blue>button 12</html>");
        button2.setToolTipText("");
        button2.setBounds(250,50,200,30);
        button2.addActionListener(new ButtonActionListener());

    }
}
