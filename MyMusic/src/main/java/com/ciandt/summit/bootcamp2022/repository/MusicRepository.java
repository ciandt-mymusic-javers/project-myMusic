package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MusicRepository extends JpaRepository<Music, String> {
    @Query(value = "SELECT m FROM Music m WHERE m.name " +
            "like %:name% OR m.artist.name like %:name% " +
            "ORDER BY LOWER(m.name), LOWER(m.artist.name) ASC")
    Set<Music> findMusicsByNameOrArtists(@Param("name") String name);

}
