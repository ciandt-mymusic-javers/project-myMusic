package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.service.MusicService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/music")
public class MusicController {

    @Autowired
    private final MusicService musicService;

    @Operation(summary = "Search method for music", description = "endpoint to find music by name or artist")
    @GetMapping
    public ResponseEntity<List<Music>> findMusicsByNameOrArtists(
            @RequestParam String filter,
            @RequestParam String pageNumber,
            @RequestParam String pageSize) {

        log.info("Endpoint to music search inicialized.");

        return new ResponseEntity<>(musicService.findMusicsByNameOrArtists(String.valueOf(filter),
                Integer.parseInt(pageNumber), Integer.parseInt(pageSize)).getContent(), HttpStatus.OK);
    }
}
