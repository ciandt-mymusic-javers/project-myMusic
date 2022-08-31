package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.SummitBootcampApplication;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.service.MusicService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/music")
public class MusicController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MusicController.class);

    @Autowired
    private final MusicService musicService;

    @Operation(summary = "Search method for music", description = "endpoint to find music by name or artist")
    @GetMapping
    public ResponseEntity<List<Music>> findMusicsByNameOrArtists(
            @RequestParam String filter,
            @RequestParam String pageNumber,
            @RequestParam String pageSize) {
        try {
            LOGGER.info("Music found.");
            return new ResponseEntity<>(musicService.findMusicsByNameOrArtists(String.valueOf(filter),
                    Integer.parseInt(pageNumber), Integer.parseInt(pageSize)).getContent(), HttpStatus.OK);
        } catch (RuntimeException e){
            LOGGER.error("Error to find music.");
            throw new RuntimeException(e.getMessage());
        }
    }
}
