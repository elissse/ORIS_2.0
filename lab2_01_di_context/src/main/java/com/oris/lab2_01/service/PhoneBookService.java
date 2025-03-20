package com.oris.lab2_01.service;

import com.oris.lab2_01.annotation.Component;
import com.oris.lab2_01.model.Abonent;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class PhoneBookService {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Abonent> phoneBook = new ArrayList<>();

    public void addAbonent() {
        String name = scanner.nextLine();
        String countryCode = scanner.nextLine();
        String number = scanner.nextLine();
        Abonent abonent = new Abonent(name, countryCode, number);
        phoneBook.add(abonent);
    }

    public Abonent getAbonentByPhoneNumber(String number) {
        for (Abonent abonent: phoneBook) {
            if (abonent.getPhoneNumbers().contains(number))
                return abonent;
        }
        System.out.println("there's no one with this number");
        return null;
    }

}
