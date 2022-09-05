package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.service.PlaylistService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/playlist")
public class PlaylistController {

    @Autowired
    private final PlaylistService playlistService;

    @Operation(summary = "Add music to playlist", description = "endpoint to add a music to a playlist by id")
    @PostMapping("/{playlistId}/musics")
    public ResponseEntity<String> addMusicIntoPlaylist(@PathVariable String playlistId,
                                                       @RequestBody @Valid Music music){
        playlistService.addMusicIntoPlaylist(music, playlistId);

        log.info("Endpoint to add music into playlist initialized.");

        return new ResponseEntity<>("Music successfully inserted into playlist", HttpStatus.CREATED);
    }

    @Operation(summary = "Remove music from playlist", description = "Remove music from a playlist")
    @DeleteMapping("/{playlistId}/musics/{musicId}")
    public ResponseEntity<Playlist> deleteMusicFromPlaylist(@PathVariable String playlistId, @PathVariable String musicId){
        playlistService.deleteMusicOfPlaylist(playlistId,musicId);

        return new ResponseEntity<Playlist>(HttpStatus.OK);
    }
}
