package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.exception.MusicNotFoundInsidePlaylistException;
import com.ciandt.summit.bootcamp2022.exception.MusicOrPlaylistNotFoundException;
import com.ciandt.summit.bootcamp2022.repository.MusicRepository;
import com.ciandt.summit.bootcamp2022.repository.PlaylistRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Log4j2
@Service
public class PlaylistService implements IPlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private MusicRepository musicRepository;

    @Override
    public Playlist addMusicIntoPlaylist(Music music, String playlistId){
        isMusicExists(music.getId());

        Playlist playlist = isPlaylistExists(playlistId);
        playlist.getMusics().add(music);

        log.info("Music added into the playlist.");

        return playlistRepository.save(playlist);
    }

    public Integer deleteMusicOfPlaylist(String musicId, String playlistId) {
        isMusicExists(musicId);

        isPlaylistExists(playlistId);

        String musicIdFound = playlistRepository.findMusicIntoPlaylist(playlistId, musicId);
        if (musicIdFound == null) {
            throw new MusicNotFoundInsidePlaylistException("Music was not found inside playlist");
        }
        return playlistRepository.deleteMusicOfPlaylist(playlistId, musicId);
    }

    private Playlist isPlaylistExists(String playlistId) {
        Optional<Playlist> playlistFound = playlistRepository.findById(playlistId);
        if(!playlistFound.isPresent()) {
            log.error("Playlist was not found.");
            throw new MusicOrPlaylistNotFoundException("Playlist with id " + playlistId + " not found");
        }
        return playlistFound.get();
    }

    private void isMusicExists(String musicId) {
        Optional<Music> musicFound = musicRepository.findById(musicId);
        if(!musicFound.isPresent()) {
            log.error("Music was not found.");
            throw new MusicOrPlaylistNotFoundException("Music with id " + musicId + " not found");
        }
    }
}
