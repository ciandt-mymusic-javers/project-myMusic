package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.service.MusicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @GetMapping
    @Operation(summary = "Search method for music", description = "Endpoint to find music by name or artist")
    @ApiResponse(responseCode = "200", description = "Returns a list of musics that was found by name or artist")
    @ApiResponse(responseCode = "400", description = "Returns nothing when music was not found or returns 'It is not " +
            "possible to filter musics with word shorter than 2 characters' " +
            "when music is been searched with word shorter than 2 characters")
    public ResponseEntity<List<Music>> findMusicsByNameOrArtists(
            @RequestParam String filter,
            @RequestParam String pageNumber,
            @RequestParam String pageSize) {

        log.info("Endpoint to music search initialized.");

        return new ResponseEntity<>(musicService.findMusicsByNameOrArtists(String.valueOf(filter),
                Integer.parseInt(pageNumber), Integer.parseInt(pageSize)).getContent(), HttpStatus.OK);
    }
}
