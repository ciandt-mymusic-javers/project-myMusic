package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.entity.Music;
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
@RequestMapping("/api/playlists")
public class PlaylistController {

    @Autowired
    private final PlaylistService playlistService;

    @Operation(summary = "Add music to playlist", description = "endpoint to add a music to a playlist by id")
    @PostMapping("/{playlistId}/musics")
    public ResponseEntity<String> addMusicIntoPlaylist(@PathVariable String playlistId,
                                                       @RequestBody @Valid Music music){
        playlistService.addMusicIntoPlaylist(music, playlistId);

        log.info("Endpoint to add music into playlist inicialized.");

        return new ResponseEntity<>("Music successfully insert into playlist", HttpStatus.CREATED);
    }
}
