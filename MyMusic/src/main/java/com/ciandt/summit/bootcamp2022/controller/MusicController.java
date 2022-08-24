package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.service.MusicService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/music")
public class MusicController {

    @Autowired
    private final MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @Operation(summary = "test", description = "endpoint only for test")
    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("67f5976c-eb1e-404e-8220-2c2a8a23be47");
    }

    @Operation(summary = "Search method for music", description = "endpoint to find music by name or artist")
    @GetMapping(value = "/filter={name}")
    public ResponseEntity<Set<Music>> findMusicsByNameOrArtists(@PathVariable String name) {
        return new ResponseEntity<>(musicService.findMusicsByNameOrArtists(String.valueOf(name)), HttpStatus.OK);
    }
}
