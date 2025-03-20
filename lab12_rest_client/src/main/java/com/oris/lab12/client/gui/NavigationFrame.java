package com.oris.lab12.client.gui;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NavigationFrame extends Frame implements ActionListener {

    Button saveButton;
    Button deleteButton;
    Button findButton;
    Button findAllButton;

    NavigationFrame() {
        this.setLayout(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        saveButton = new Button("save");
        deleteButton = new Button("delete");
        findButton = new Button("find by id");
        findAllButton = new Button("find all");
        addButton(100, 100, 200, 100, saveButton);
        addButton(100, 200, 200, 100, deleteButton);
        addButton(300, 100, 200, 100, findButton);
        addButton(300, 200, 200, 100, findAllButton);
    }

    public void addButton(int x, int y, int width, int height, Button btn) {

        btn.setFont(new Font("Agency FB", Font.BOLD, 32));
        btn.setBounds(x, y, width, height);
        btn.addActionListener(this);
        this.add(btn);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource().equals(saveButton)) {
            SaveFrame g = new SaveFrame();
            g.setTitle("SaveFrame");
            g.setSize(500, 500);
            g.setVisible(true);
        } else if (ae.getSource().equals(findAllButton)) {
            MainFrame g = new MainFrame();
            g.setTitle("FindAllFrame");
            g.setSize(500, 500);
            g.setVisible(true);
        }else if (ae.getSource().equals(deleteButton)) {
            DeleteFrame g = new DeleteFrame();
            g.setTitle("delete frame");
            g.setSize(500, 500);
            g.setVisible(true);
        }else if (ae.getSource().equals(findButton)) {
            FindByIdFrame g = new FindByIdFrame();
            g.setTitle("find by id");
            g.setSize(500, 500);
            g.setVisible(true);
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {

        NavigationFrame gfg = new NavigationFrame();
        gfg.setTitle("NavigationFrame");
        gfg.setSize(600, 600);
        gfg.setVisible(true);
    }
}