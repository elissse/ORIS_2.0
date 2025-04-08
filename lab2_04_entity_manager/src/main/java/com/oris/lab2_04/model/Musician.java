package com.oris.lab2_04.model;
import com.oris.lab2_04.annotation.Entity;
import com.oris.lab2_04.annotation.Id;
import com.oris.lab2_04.annotation.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Musician {
    @Id
    private Integer id;
    private String name;
    @ManyToOne
    private Country country;
}