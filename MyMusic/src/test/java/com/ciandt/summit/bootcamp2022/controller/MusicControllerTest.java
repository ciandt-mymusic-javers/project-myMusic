package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.entity.Artist;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.exception.InvalidFilterSizeException;
import com.ciandt.summit.bootcamp2022.exception.MusicNotFoundException;
import com.ciandt.summit.bootcamp2022.service.MusicService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MusicController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MusicControllerTest {

    @MockBean
    private MusicService musicService;

    @Autowired
    private MockMvc mvc;

    private Music music;

    @BeforeAll
    public void init()  {
        Artist artist = new Artist("30ab1678-c616-4314-adcc-918aff5a7a13", "Nickelback");
        music = new Music("4ffb5d4f-8b7f-4996-b84b-ecf751f52eea", "Photograph", artist);
    }

    @Test
    @DisplayName("Search with filter longer than 2 characters should return HTTP.StatusCode.OK")
    void findMusicsByNameOrArtistsSuccess() throws Exception {
        given(musicService.findMusicsByNameOrArtists("Nick", 0, 10))
                .willReturn(new PageImpl<>(Collections.singletonList(music)));

        RequestBuilder request = get("/api/v1/music?filter=Nick&pageNumber=0&pageSize=10");
        String body = "[{\"id\":\"4ffb5d4f-8b7f-4996-b84b-ecf751f52eea\"," +
                "\"name\":\"Photograph\"," +
                "\"artist\":{\"id\":\"30ab1678-c616-4314-adcc-918aff5a7a13\"," +
                "\"name\":\"Nickelback\"}}]";
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content()
                        .string(body)).andReturn();
    }

    @Test
    @DisplayName("Not existing search with filter longer than 2 characters should return HTTP.StatusCode.NOTFOUND")
    void findMusicsByNameOrArtistsNotFound() throws Exception {

        given(musicService.findMusicsByNameOrArtists("Sandy", 0, 10))
                .willThrow(new MusicNotFoundException());

        RequestBuilder request = get("/api/v1/music?filter=Sandy&pageNumber=0&pageSize=10");
        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isNoContent()).andReturn();
    }

    @Test
    @DisplayName("Search with filter shorter than 2 characters should return HTTP.StatusCode.BADREQUEST")
    void findMusicsByNameOrArtistsInvalidFilter() throws Exception {

        given(musicService.findMusicsByNameOrArtists("s", 0, 10))
                .willThrow(new InvalidFilterSizeException(
                        "It is not possible to filter musics with word shorter than "
                                + MusicService.MINIMUM_LENGTH + " characters"));

        RequestBuilder request = get("/api/v1/music?filter=s&pageNumber=0&pageSize=10");
        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
    }
}
