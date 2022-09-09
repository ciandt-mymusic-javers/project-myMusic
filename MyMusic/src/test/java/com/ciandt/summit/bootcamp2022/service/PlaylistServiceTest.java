package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.Artist;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.exception.MusicAlreadyInsidePlaylistException;
import com.ciandt.summit.bootcamp2022.exception.MusicNotFoundInsidePlaylistException;
import com.ciandt.summit.bootcamp2022.exception.MusicOrPlaylistNotFoundException;
import com.ciandt.summit.bootcamp2022.repository.MusicRepository;
import com.ciandt.summit.bootcamp2022.repository.PlaylistRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaylistServiceTest {

    @InjectMocks
    private PlaylistService playlistService;

    @Mock
    private PlaylistRepository playlistRepository;

    @Mock
    private MusicRepository musicRepository;

    private static Set<Music> musics;

    private static Music m;

    @Mock
    private static Playlist playlist;

    private static final String PLAYLISTID = "playlist01";

    @BeforeAll
    public static void init() {
        Artist artist = new Artist("30ab1678-c616-4314-adcc-918aff5a7a13", "M1");
        m = new Music("4ffb5d4f-8b7f-4996-b84b-ecf751f52eea", "Leave the Door Open", artist);

        musics = new HashSet<>();
        musics.add(m);
    }

    @Test
    @DisplayName("Return Music id not found when MusicOrPlaylistNotFoundException")
    void addMusicIntoPlaylistShouldReturnMusicOrPlaylistNotFoundExceptionWhenMusicNotFound() {
        when(musicRepository.findById(m.getId()))
                .thenReturn(Optional.empty());

        Exception exception = assertThrows(MusicOrPlaylistNotFoundException.class,
                () -> playlistService.addMusicIntoPlaylist(m, PLAYLISTID)
        );

        assertEquals(
                "Music with id " + m.getId() + " not found", exception.getMessage());
    }

    @Test
    @DisplayName("Return Playlist id not found when PlaylistNotFoundException")
    void addMusicIntoPlaylistShouldReturnPlaylistNotFoundExceptionWhenPlaylistNotFound() {
        when(musicRepository.findById(m.getId()))
                .thenReturn(Optional.ofNullable(m));

        when(playlistRepository.findById(PLAYLISTID))
                .thenReturn(Optional.empty());

        Exception exception = assertThrows(MusicOrPlaylistNotFoundException.class,
                () -> playlistService.addMusicIntoPlaylist(m, PLAYLISTID)
        );

        assertEquals(
                "Playlist with id " + PLAYLISTID + " not found", exception.getMessage());
    }

    @Test
    @DisplayName("Should add music into playlist")
    void addMusicIntoPlaylistShouldAddMusic() {

        when(musicRepository.findById(m.getId()))
                .thenReturn(Optional.ofNullable(m));

        when(playlistRepository.findById(PLAYLISTID))
                .thenReturn(Optional.ofNullable(playlist));

        assertDoesNotThrow(() -> playlistService.addMusicIntoPlaylist(m, PLAYLISTID));
    }

    @Test
    @DisplayName("Return Music already added into this playlist when MusicAlreadyInsidePlaylistException")
    void addMusicIntoPlaylistShouldReturnMusicAlreadyInsidePlaylistExceptionWhenMusicAlreadyInPlaylist() {

        when(musicRepository.findById(m.getId()))
                .thenReturn(Optional.ofNullable(m));

        when(playlistRepository.findById(PLAYLISTID))
                .thenReturn(Optional.of(playlist));

        when(playlist.getMusics()).thenReturn(musics);

        Exception exception = assertThrows(MusicAlreadyInsidePlaylistException.class,
                () -> playlistService.addMusicIntoPlaylist(m, PLAYLISTID)
        );

        assertEquals(
                "Music already added into this playlist", exception.getMessage());
    }

    @DisplayName("Should Return Exception Music not Found into Playlist")
    void deleteMusicFromPlaylistShouldReturnExceptionMusicNotFoundIntoPlaylist() {
        when(musicRepository.findById(m.getId()))
                .thenReturn(Optional.ofNullable(m));

        when(playlistRepository.findById(PLAYLISTID))
                .thenReturn(Optional.of(playlist));

        when(playlistRepository.findMusicIntoPlaylist(playlist.getId(), m.getId()))
                .thenReturn(null);

        Exception exception = assertThrows(MusicNotFoundInsidePlaylistException.class,
                () -> playlistService.deleteMusicFromPlaylist(m.getId(), PLAYLISTID)
        );
        assertEquals(
                "Music was not found inside playlist", exception.getMessage());
    }

    @Test
    @DisplayName("Return Music Id not found when Delete Music Or Playlist Not Found")
    void deleteMusicIntoPlaylistShouldReturnMusicOrPlaylistNotFoundExceptionWhenMusicNotFound() {
        when(musicRepository.findById(m.getId()))
                .thenReturn(Optional.empty());

        Exception exception = assertThrows(MusicOrPlaylistNotFoundException.class,
                () -> playlistService.deleteMusicFromPlaylist(m.getId(), PLAYLISTID)
        );

        assertEquals(
                "Music with id " + m.getId() + " not found", exception.getMessage());
    }

    @Test
    @DisplayName("Should Delete music from playlist")
    void deleteMusicFromPlaylistWithSuccess() {
        when(musicRepository.findById(m.getId()))
                .thenReturn(Optional.ofNullable(m));

        when(playlistRepository.findById(PLAYLISTID))
                .thenReturn(Optional.ofNullable(playlist));

        when(playlistRepository.findMusicIntoPlaylist(playlist.getId(), m.getId()))
                .thenReturn(m.getId());

        assertDoesNotThrow(() -> playlistService.deleteMusicFromPlaylist(m.getId(), PLAYLISTID));
    }
}
