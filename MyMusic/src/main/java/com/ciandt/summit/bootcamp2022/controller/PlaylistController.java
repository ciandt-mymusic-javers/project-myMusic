package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.service.PlaylistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @PostMapping("/{playlistId}/musics")
    @Operation(summary = "Add music to playlist", description = "Endpoint to add a music to a playlist by id")
    @ApiResponse(responseCode = "201", description = "Returns 'Music successfully inserted into playlist'")
    @ApiResponse(responseCode = "400", description = "Returns 'Music with id {} not found'")
    public ResponseEntity<String> addMusicIntoPlaylist(@PathVariable String playlistId,
                                                       @RequestBody @Valid Music music){
        playlistService.addMusicIntoPlaylist(music, playlistId);

        log.info("Endpoint to add music into playlist initialized.");

        return new ResponseEntity<>("Music successfully inserted into playlist", HttpStatus.CREATED);
    }

    @Operation(summary = "Remove music from playlist", description = "Remove music from a playlist")
    @ApiResponse(responseCode = "204", description = "Music successfully removed from the playlist")
    @ApiResponse(responseCode = "400", description = "Returns 'Music was not found inside playlist' or 'Music with id {} not found' or 'Playlist with id 2 not found'")
    @DeleteMapping("/{playlistId}/musics/{musicId}")
    public ResponseEntity<?> deleteMusicFromPlaylist(@PathVariable String playlistId, @PathVariable String musicId){
        playlistService.deleteMusicFromPlaylist(musicId, playlistId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
