package com.oris.lab12.client.gui;

import com.oris.lab12.client.service.MonthService;
import com.oris.lab12.model.Month;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

public class SaveFrame extends JFrame implements ActionListener {

    private JLabel nameLabel;
    private JTextField nameText;
    private JLabel seasonLabel;
    private JTextField seasonText;
    private JButton save;
    private JButton reset;
    private JButton inquiry;
    private MonthService service = new MonthService();

    public SaveFrame() {
        super("save");
        this.setSize(600, 600);
        this.setLayout(null);

        nameLabel = new JLabel("name");
        nameLabel.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        nameLabel.setSize(100, 20);
        nameLabel.setLocation(100, 100);
        this.add(nameLabel);
        nameText = new JTextField();
        nameText.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        nameText.setSize(190, 20);
        nameText.setLocation(200, 100);
        this.add(nameText);

        seasonLabel = new JLabel("season");
        seasonLabel.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        seasonLabel.setSize(100, 20);
        seasonLabel.setLocation(100, 150);
        this.add(seasonLabel);
        seasonText = new JTextField();
        seasonText.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        seasonText.setSize(190, 20);
        seasonText.setLocation(200, 150);
        this.add(seasonText);

        save = new JButton("save");
        save.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        save.setSize(100, 20);
        save.setLocation(100, 300);
        save.addActionListener(this);
        this.add(save);

        reset = new JButton("reset");
        reset.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        reset.setSize(100, 20);
        reset.setLocation(220, 300);
        reset.addActionListener(this);
        this.add(reset);

        inquiry = new JButton("inquiry");
        inquiry.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        inquiry.setSize(100, 20);
        inquiry.setLocation(340, 300);
        inquiry.addActionListener(this);
        this.add(inquiry);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
            if (nameText.getText().isEmpty() || seasonLabel.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this.getComponent(0), "the fields must be not empty!!!");
            } else {
                Month month = new Month(0L, nameText.getText(), seasonText.getText());
                try {
                    service.save(month);
                } catch (URISyntaxException | IOException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if (e.getSource() == reset) {
            String empty = "";
            nameText.setText(empty);
            seasonText.setText(empty);
        } else if (e.getSource() == inquiry) {
            JOptionPane.showMessageDialog(this.getComponent(0), "hi add new month");
        }
    }
}

