package com.oris.lab12.client.gui;

import com.oris.lab12.client.service.MonthService;
import com.oris.lab12.model.Month;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

public class FindByIdFrame  extends JFrame implements ActionListener {

    private JLabel idLabel;
    private JTextField idText;
    private JButton find;
    private MonthService service = new MonthService();

    public FindByIdFrame() {
        super("find by id");

        this.setSize(600, 600);
        this.setLayout(null);

        idLabel = new JLabel("id");
        idLabel.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        idLabel.setSize(100, 20);
        idLabel.setLocation(100, 100);
        this.add(idLabel);
        idText = new JTextField();
        idText.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        idText.setSize(190, 20);
        idText.setLocation(200, 100);
        this.add(idText);

        find = new JButton("find");
        find.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        find.setSize(100, 20);
        find.setLocation(150, 200);
        find.addActionListener(this);
        this.add(find);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == find) {
            if (idText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this.getComponent(0), "the fields must be not empty!!!");
            } else {
                try {
                    Month month = service.findById(Long.valueOf(idText.getText()));
                    JOptionPane.showMessageDialog(this.getComponent(0), month);
                } catch (URISyntaxException | IOException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}