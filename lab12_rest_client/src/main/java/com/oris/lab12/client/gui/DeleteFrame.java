package com.oris.lab12.client.gui;

import com.oris.lab12.client.service.MonthService;
import com.oris.lab12.model.Month;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

public class DeleteFrame extends JFrame implements ActionListener {

    private JLabel idLabel;
    private JTextField idText;
    private JButton delete;
    private MonthService service = new MonthService();

    public DeleteFrame() {
        super("delete");

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

        delete = new JButton("delete");
        delete.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        delete.setSize(100, 20);
        delete.setLocation(150, 200);
        delete.addActionListener(this);
        this.add(delete);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delete) {
            if (idText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this.getComponent(0), "the fields must be not empty!!!");
            } else {
                try {
                    service.delete(Long.valueOf(idText.getText()));
                    JOptionPane.showMessageDialog(this.getComponent(0), "the month was deleted");
                } catch (URISyntaxException | IOException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}

