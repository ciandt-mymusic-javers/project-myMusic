package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.Artist;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.exception.InvalidFilterSizeException;
import com.ciandt.summit.bootcamp2022.exception.MusicNotFoundException;
import com.ciandt.summit.bootcamp2022.repository.MusicRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MusicServiceTest {

    @InjectMocks
    private MusicService musicService;
    @Mock
    private MusicRepository musicRepository;

    private static List<Music> musics;

    private static Music m;

    @BeforeAll
    public static void init() {
        Artist artist = new Artist("30ab1678-c616-4314-adcc-918aff5a7a13", "M1");
        m = new Music("4ffb5d4f-8b7f-4996-b84b-ecf751f52eea", "Leave the Door Open", artist);

        musics = new ArrayList<>();
        musics.add(m);
    }

    @ParameterizedTest(name = "{index} - \"{0}\" does not have " + MusicService.MINIMUM_LENGTH + " or more characters")
    @ValueSource(strings = {"A", "a", " ", ""})
    void findMusicsByNameOrArtistsShouldReturnInvalidFilterSizeExceptionWhenFilterSizeIsInvalid(String filter) {
        Exception exception = assertThrows(InvalidFilterSizeException.class,
                () -> musicService.findMusicsByNameOrArtists(filter, 0, 10)
        );

        assertEquals(
                "It is not possible to filter musics with word shorter than "
                        + MusicService.MINIMUM_LENGTH + " characters", exception.getMessage());
    }

    @ParameterizedTest(name = "{index} - \"{0}\" have " + MusicService.MINIMUM_LENGTH + " or more characters")
    @ValueSource(strings = {"bea", "U2", "Bruno Mars"})
    void findMusicsByNameOrArtistsShouldReturnMusicsWhenFilterSizeIsValid(String filter) {
        when(musicRepository.findMusicsByNameOrArtists(filter, PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(musics));

         assertDoesNotThrow(() -> musicService.findMusicsByNameOrArtists(filter, 0 ,10));
    }

    @Test
    void findMusicsByNameOrArtistsShouldReturnMusicNotFoundExceptionWhenMusicOrArtistNotFound() {
        when(musicRepository.findMusicsByNameOrArtists(m.getName(), PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));

        assertThrows(MusicNotFoundException.class, () ->
                musicService.findMusicsByNameOrArtists(m.getName(), 0,10));
    }
}