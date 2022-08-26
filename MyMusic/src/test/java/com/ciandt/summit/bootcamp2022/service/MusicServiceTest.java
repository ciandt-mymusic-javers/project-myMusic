package com.ciandt.summit.bootcamp2022.service;

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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MusicServiceTest {

    @InjectMocks
    private MusicService musicService;
    @Mock
    private MusicRepository musicRepository;

    private static Set<Music> musics;

    private static Music m;

    @BeforeAll
    public static void init() {
        m = new Music("M1", "Leave the Door Open");

        musics = new HashSet<>();
        musics.add(m);
    }

    @ParameterizedTest(name = "{index} - \"{0}\" does not have " + MusicService.MINIMUM_LENGTH + " or more characters")
    @ValueSource(strings = {"A", "a", " ", ""})
    void NotEnoughNameLengthTest(String filter) {
        Exception exception = assertThrows(
                InvalidFilterSizeException.class, () -> musicService.findMusicsByNameOrArtists(filter)
        );

        assertEquals(
                "It is not possible to filter musics with word shorter than "
                        + MusicService.MINIMUM_LENGTH + " characters", exception.getMessage());
    }

    @ParameterizedTest(name = "{index} - \"{0}\" have " + MusicService.MINIMUM_LENGTH + " or more characters")
    @ValueSource(strings = {"bea", "U2", "Bruno Mars"})
    void EnoughNameLengthTest(String filter) {
        when(musicRepository.findMusicsByNameOrArtists(filter)).thenReturn(musics);

         assertDoesNotThrow(() -> musicService.findMusicsByNameOrArtists(filter));
    }

    @Test
    void MusicNotFoundTest() {
        when(musicRepository.findMusicsByNameOrArtists(anyString())).thenReturn(Collections.emptySet());

        Exception exception = assertThrows(
                MusicNotFoundException.class, () -> musicService.findMusicsByNameOrArtists(m.getName())
        );

        assertEquals("MusicNotFoundException", exception.getClass().getSimpleName());
    }
}