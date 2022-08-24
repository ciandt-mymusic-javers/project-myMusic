package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.Music;

import java.util.Set;

public interface IMusicService {

    Set<Music> findMusicsByNameOrArtists(String filter);
}
