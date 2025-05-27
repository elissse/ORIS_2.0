package com.oris.lab2_05.model;

import lombok.Data;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class DigitalMedia extends LibraryItem {

    private String format;

    private double sizeInMB;
}

