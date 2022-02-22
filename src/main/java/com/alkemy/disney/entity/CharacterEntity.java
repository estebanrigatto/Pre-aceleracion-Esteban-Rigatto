package com.alkemy.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characters")
@Getter
@Setter
@SQLDelete(sql = "UPDATE characters SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class CharacterEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private boolean deleted = Boolean.FALSE;

    private String picture;

    @Column(name = "character_name")
    private String name;

    private int age;

    private int weight;

    private String story;

    @ManyToMany(
            mappedBy = "characters",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    private List<FilmEntity> films = new ArrayList<>();

}
