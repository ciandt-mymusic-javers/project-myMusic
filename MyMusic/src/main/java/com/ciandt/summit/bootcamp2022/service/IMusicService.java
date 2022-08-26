package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.Music;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface IMusicService {

    //    Set<Music> findMusicsByNameOrArtists(String filter);
    Page<Music> findMusicsByNameOrArtists(String filter, int pageNumber, int pageSize);

}
