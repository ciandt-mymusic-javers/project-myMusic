package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.service.MusicService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/music")
public class MusicController {

    @Autowired
    private final MusicService musicService;

    @Operation(summary = "Search method for music", description = "endpoint to find music by name or artist")
    @GetMapping
    public ResponseEntity<Set<Music>> findMusicsByNameOrArtists(@RequestParam String filter) {
        return new ResponseEntity<>(musicService.findMusicsByNameOrArtists(String.valueOf(filter)), HttpStatus.OK);
    }
}
