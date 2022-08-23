package com.ciandt.summit.bootcamp2022.repositories;

import com.ciandt.summit.bootcamp2022.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, String> {
    List<Artist> findByNomeContaining(String nome);
}
