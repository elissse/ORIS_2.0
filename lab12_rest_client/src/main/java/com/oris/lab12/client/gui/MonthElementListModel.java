package com.oris.lab12.client.gui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import com.oris.lab12.model.Month;

/**
 * Минимум, необходимый для реализации модели списка - получение размера и получение элемента по индексу
 */
public class MonthElementListModel extends AbstractListModel {

    private List<Month> months = new ArrayList<>();

    public MonthElementListModel(List<Month> months) {
        this.months = months;
    }

    @Override
    public int getSize() {
        return months.size();
    }

    @Override
    public Object getElementAt(int index) {
        return months.get(index);
    }
}