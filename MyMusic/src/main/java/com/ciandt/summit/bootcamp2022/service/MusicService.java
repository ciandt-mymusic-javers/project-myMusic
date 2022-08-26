package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.exception.InvalidFilterSizeException;
import com.ciandt.summit.bootcamp2022.exception.MusicNotFoundException;
import com.ciandt.summit.bootcamp2022.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MusicService implements IMusicService{

    public static final int MINIMUM_LENGTH = 2;
    @Autowired
    private MusicRepository musicRepository;

    @Override
    public Set<Music> findMusicsByNameOrArtists(String filter){
        if(isNotEnoughNameLength(filter))
            throw new InvalidFilterSizeException(
                    "It is not possible to filter musics with word shorter than "
                            + MINIMUM_LENGTH + " characters");

        Set<Music> musics = musicRepository.findMusicsByNameOrArtists(filter);

        if(musics.isEmpty())
            throw new MusicNotFoundException();

        return musics;
    }

    public static boolean isNotEnoughNameLength(String filter) {
        return filter.length() < MINIMUM_LENGTH;
    }
}
