package com.oris.lab2_05.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reader_seq")
    @SequenceGenerator(name = "reader_seq", sequenceName = "reader_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String email;

    private String phone;
}
