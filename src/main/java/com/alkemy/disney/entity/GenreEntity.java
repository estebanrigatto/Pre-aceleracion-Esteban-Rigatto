package com.alkemy.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "genre")
@Getter
@Setter
@SQLDelete(sql = "UPDATE genre SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class GenreEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private boolean deleted = Boolean.FALSE;

    private String name;

    private String picture;

}
