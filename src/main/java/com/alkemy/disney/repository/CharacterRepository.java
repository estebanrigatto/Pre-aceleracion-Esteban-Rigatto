package com.alkemy.disney.repository;

import com.alkemy.disney.entity.CharacterEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, String> {

    List<CharacterEntity> findAll(Specification<CharacterEntity> spec);

}
