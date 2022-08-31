package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.SummitBootcampApplication;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.exception.InvalidFilterSizeException;
import com.ciandt.summit.bootcamp2022.exception.MusicNotFoundException;
import com.ciandt.summit.bootcamp2022.repository.MusicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MusicService implements IMusicService{

    private static final Logger LOGGER = LoggerFactory.getLogger(MusicService.class);

    public static final int MINIMUM_LENGTH = 2;
    @Autowired
    private MusicRepository musicRepository;

    @Override
    public Page<Music> findMusicsByNameOrArtists(String filter, int pageNumber, int pageSize){
        LOGGER.info("Music found.");

        if(isNotEnoughNameLength(filter))
            throw new InvalidFilterSizeException(
                    "It is not possible to filter musics with word shorter than "
                            + MINIMUM_LENGTH + " characters");

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Music> musics = musicRepository.findMusicsByNameOrArtists(filter, pageable);

        if(musics.isEmpty())
            throw new MusicNotFoundException();

        return musics;
    }

    private boolean isNotEnoughNameLength(String filter) {
        return filter.length() < MINIMUM_LENGTH;
    }
}
