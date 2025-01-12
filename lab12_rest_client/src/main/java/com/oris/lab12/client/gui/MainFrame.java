package com.oris.lab12.client.gui;

import com.oris.lab12.client.service.MonthService;
import com.oris.lab12.model.Month;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private MonthService service = new MonthService();

    public MainFrame() {
        super("12 месяцев");

        setLayout(new FlowLayout());
        setSize(400, 400);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Получаем список всех месяцев через сервис, и создаем модель данных для списка
        ListModel monthListModel = null;
        try {
            monthListModel = new MonthElementListModel(service.findAll());
            System.out.println(service.findById(4L));
            System.out.println(service.save(new Month(0L,"sleepember", "falling")));

        } catch (Exception e) {
            e.printStackTrace();
            monthListModel = new DefaultListModel();
            ((DefaultListModel)monthListModel).add(0, "Не удалось загрузить данные");
        }


        JList listMonth = new JList(monthListModel);

        add(new JScrollPane(listMonth));
        setVisible(true);
    }

}