package com.oris.lab2_04.model;
import com.oris.lab2_04.annotation.Entity;
import com.oris.lab2_04.annotation.Id;
import com.oris.lab2_04.annotation.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Entity
public class MusicTrack {
    @Id
    private Integer id;
    private String name;
    private Integer length;
    @ManyToOne
    private Musician musician;
    @ManyToOne
    private Author author;
    private String date;
    @ManyToOne
    private Genre genre;
}