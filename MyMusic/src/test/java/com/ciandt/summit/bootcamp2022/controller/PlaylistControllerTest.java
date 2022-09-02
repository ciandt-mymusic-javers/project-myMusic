package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.entity.Artist;
import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.exception.MusicOrPlaylistNotFoundException;
import com.ciandt.summit.bootcamp2022.interceptor.TokenInterceptor;
import com.ciandt.summit.bootcamp2022.service.PlaylistService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PlaylistController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlaylistControllerTest {

    @MockBean
    private PlaylistService playlistService;

    @MockBean
    private TokenInterceptor tokenInterceptor;

    @Autowired
    private MockMvc mvc;

    private Playlist playlist;

    private Music music;

    private static final String PLAYLISTID = "playlist00";

    @BeforeAll
    public void init()  {
        Artist artist = new Artist("30ab1678-c616-4314-adcc-918aff5a7a13", "Nickelback");
        music = new Music("4ffb5d4f-8b7f-4996-b84b-ecf751f52eea", "Photograph", artist);

        playlist = new Playlist("4ffb5d4f-8b7f-4996-b84b-ecf751f52eee");
    }

    @BeforeEach
    void initTest() {
        given(tokenInterceptor.preHandle(any(), any(), any())).willReturn(true);
    }

    @Test
    @DisplayName("Playlist not found should return HTTP.StatusCode.BAD_REQUEST")
    void addMusicIntoPlaylistWhenPlaylistIdNotFound() throws Exception {

        given(playlistService.addMusicIntoPlaylist(music, PLAYLISTID))
                .willThrow(new MusicOrPlaylistNotFoundException("Playlist with id" + PLAYLISTID + " not found"));

        String url = "/api/playlists/" + PLAYLISTID + "/musics";

        String body = String.valueOf(music);

        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
    }

    @Test
    @DisplayName("Music not found should return HTTP.StatusCode.BAD_REQUEST")
    void addMusicIntoPlaylistWhenMusicNotFound() throws Exception {

        given(playlistService.addMusicIntoPlaylist(music , playlist.getId()))
                .willThrow(new MusicOrPlaylistNotFoundException("Music with id" + playlist.getId() + " not found"));

        String url = "/api/playlists/" + playlist.getId() + "/musics";

        String body = String.valueOf(music);

        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
    }

    @Test
    @DisplayName("Add music into playlist should return HTTP.StatusCode.OK")
    void addMusicIntoPlaylistSuccess() throws Exception {

        given(playlistService.addMusicIntoPlaylist(music , playlist.getId()))
                .willReturn(new Playlist());

        String url = "/api/playlists/" + playlist.getId() + "/musics";

        String body = "{\n" +
                "     \"id\": \"4ffb5d4f-8b7f-4996-b84b-ecf75.1f52eea\",\n" +
                "     \"name\": \"Photograph\",\n" +
                "     \"artist\": {\n" +
                "       \"id\": \"30ab1678-c616-4314-adcc-918aff5a7a13\",\n" +
                "       \"name\": \"Nickelback\" \n" +
                "      } \n" +
                "  }";

        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(content()
                        .string("Music successfully inserted into playlist")).andReturn();
    }
}