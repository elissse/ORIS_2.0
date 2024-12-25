package com.oris.lab11.controller;

import com.oris.lab11.model.Animal;
import com.oris.lab11.service.AnimalService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimalFrame extends JFrame implements ActionListener {

    private AnimalService animalService;
    private JLabel nameLabel;
    private JTextField nameText;
    private JLabel soundLabel;
    private JTextField soundText;
    private JButton save;
    private JButton reset;
    private JButton inquiry;

    public AnimalFrame() {
        super("animalssssssssss");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setLayout(null);
        animalService = new AnimalService();

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

        soundLabel = new JLabel("sound");
        soundLabel.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        soundLabel.setSize(100, 20);
        soundLabel.setLocation(100, 150);
        this.add(soundLabel);
        soundText = new JTextField();
        soundText.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        soundText.setSize(190, 20);
        soundText.setLocation(200, 150);
        this.add(soundText);

        save = new JButton("save");
        save.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        save.setSize(100, 20);
        save.setLocation(150, 450);
        save.addActionListener(this);
        this.add(save);

        reset = new JButton("reset");
        reset.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        reset.setSize(100, 20);
        reset.setLocation(270, 450);
        reset.addActionListener(this);
        this.add(reset);

        inquiry = new JButton("inquiry");
        inquiry.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        inquiry.setSize(100, 20);
        inquiry.setLocation(390, 450);
        inquiry.addActionListener(this);
        this.add(inquiry);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
            if (nameText.getText().isEmpty() || soundLabel.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this.getComponent(0), "the fields must be not empty!!!");
            } else {
                Animal animal = new Animal(0L, nameText.getText(), soundText.getText());
                animalService.save(animal);
            }
        } else if (e.getSource() == reset) {
            String empty = "";
            nameText.setText(empty);
            soundText.setText(empty);
        } else if (e.getSource() == inquiry) {
            JOptionPane.showMessageDialog(this.getComponent(0), "here you can add any animal you would like and the sound they make");
        }
    }
}

class Animals {

    public static void main(String[] args) throws Exception {
        AnimalFrame f = new AnimalFrame();
    }
}
