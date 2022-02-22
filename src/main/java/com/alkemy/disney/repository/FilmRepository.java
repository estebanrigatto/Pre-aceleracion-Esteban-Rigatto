package com.alkemy.disney.repository;

import com.alkemy.disney.entity.FilmEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, String> {

    List<FilmEntity> findAll(Specification<FilmEntity> spec);

}
