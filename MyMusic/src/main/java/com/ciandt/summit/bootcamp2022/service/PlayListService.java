package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.exception.MusicOrPlaylistNotFoundException;
import com.ciandt.summit.bootcamp2022.repository.MusicRepository;
import com.ciandt.summit.bootcamp2022.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

public class PlayListService implements IPlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private MusicRepository musicRepository;

    @Override
    public void addMusicIntoPlaylist(Music music, String playlistId) throws MusicOrPlaylistNotFoundException {
        Optional<Music> musicFound = musicRepository.findById(music.getId());
        if(!musicFound.isPresent())
            throw new MusicOrPlaylistNotFoundException("Music with id " + music.getId() + " not found");

        Optional<Playlist> playlistFound = playlistRepository.findById(playlistId);
        if(!playlistFound.isPresent()) {
            throw new MusicOrPlaylistNotFoundException("Playlist with id " + playlistId + " not found");
        }
        else{
            Playlist playlist =  playlistFound.get();
            playlist.getMusics().add(music);
            playlistRepository.save(playlist);
        }
    }
}
