package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.Music;
import org.springframework.data.domain.Page;


public interface IMusicService {

    Page<Music> findMusicsByNameOrArtists(String filter, int pageNumber, int pageSize);

}
