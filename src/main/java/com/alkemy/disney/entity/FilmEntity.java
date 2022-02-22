package com.alkemy.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "films")
@Getter
@Setter
@SQLDelete(sql = "UPDATE films SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class FilmEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private boolean deleted = Boolean.FALSE;

    private String picture;

    private String title;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "release_date")
    private LocalDate release;

    private int rating;

    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinColumn(name = "genre_id")
    private GenreEntity genre;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "film_character",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "character_id")})
    private List<CharacterEntity> characters = new ArrayList<>();

}
