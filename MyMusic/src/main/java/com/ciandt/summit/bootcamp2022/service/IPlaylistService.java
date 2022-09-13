package com.ciandt.summit.bootcamp2022.service;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.exception.MusicOrPlaylistNotFoundException;
public interface IPlaylistService {
    Playlist addMusicIntoPlaylist(Music musics, String playlistId, String userId) throws MusicOrPlaylistNotFoundException;
}


