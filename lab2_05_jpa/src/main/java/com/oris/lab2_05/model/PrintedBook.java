package com.oris.lab2_05.model;

import lombok.Data;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class PrintedBook extends LibraryItem {

    private int pages;

    private String publisher;
}
