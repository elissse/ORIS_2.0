package com.oris.lab2_04.model;
import com.oris.lab2_04.annotation.Entity;
import com.oris.lab2_04.annotation.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Country {
    @Id
    private Integer id;
    private String name;
}