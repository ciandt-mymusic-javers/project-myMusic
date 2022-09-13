package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.entity.User;
import com.ciandt.summit.bootcamp2022.exception.MusicAlreadyInsidePlaylistException;
import com.ciandt.summit.bootcamp2022.exception.MusicNotFoundInsidePlaylistException;
import com.ciandt.summit.bootcamp2022.exception.MusicOrPlaylistNotFoundException;
import com.ciandt.summit.bootcamp2022.exception.UserFreeMusicLimitExpection;
import com.ciandt.summit.bootcamp2022.repository.MusicRepository;
import com.ciandt.summit.bootcamp2022.repository.PlaylistRepository;
import com.ciandt.summit.bootcamp2022.repository.UserRepository;
import com.ciandt.summit.bootcamp2022.repository.UserTypeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Log4j2
@Service
public class PlaylistService implements IPlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private MusicRepository musicRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTypeRepository userTypeRepository;

    @Override
    public Playlist addMusicIntoPlaylist(Music music, String playlistId, String userId){

        verifyMusic(music.getId());

        Playlist playlist = isPlaylistExists(playlistId);

        String musicIdFound = playlistRepository.findMusicIntoPlaylist(playlistId, music.getId());
        if (musicIdFound != null) {
            throw new MusicAlreadyInsidePlaylistException("Music already added into this playlist");
        }

        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent())
            if(user.get().getUserTypeId().getDescricao().equalsIgnoreCase("comum"))
                if(playlist.getMusics().size() >= 5)
                    throw new UserFreeMusicLimitExpection("You have reached the maximum number of songs in your playlist." +
                        " To add more songs, purchase the premium plan");

        playlist.getMusics().add(music);

        log.info("Music added into the playlist.");

        return playlistRepository.save(playlist);
    }

    @Transactional
    public void deleteMusicFromPlaylist(String musicId, String playlistId) {
        verifyMusic(musicId);

        isPlaylistExists(playlistId);

        String musicIdFound = playlistRepository.findMusicIntoPlaylist(playlistId, musicId);
        if (musicIdFound == null) {
            throw new MusicNotFoundInsidePlaylistException("Music was not found inside playlist");
        }

        playlistRepository.deleteMusicFromPlaylist(playlistId, musicId);
    }

    private Playlist isPlaylistExists(String playlistId) {
        Optional<Playlist> playlistFound = playlistRepository.findById(playlistId);
        if(!playlistFound.isPresent()) {
            log.error("Playlist was not found.");
            throw new MusicOrPlaylistNotFoundException("Playlist with id " + playlistId + " not found");
        }
        return playlistFound.get();
    }

    private void verifyMusic(String musicId) {
        Optional<Music> musicFound = musicRepository.findById(musicId);
        if(!musicFound.isPresent()) {
            log.error("Music was not found.");
            throw new MusicOrPlaylistNotFoundException("Music with id " + musicId + " not found");
        }
    }
}
