package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.service.IMusicService;
import com.ciandt.summit.bootcamp2022.service.MusicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/music")
public class MusicController {

    @Autowired
    MusicService musicService;

    @Operation(summary = "test", description = "endpoint only for test")
    @GetMapping
    public ResponseEntity<Set<Music>> get() {
        return ResponseEntity.ok(musicService.findMusicsByNameOrArtists("th"));
    }
}
