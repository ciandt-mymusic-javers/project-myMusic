package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.entity.Music;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.exception.MusicNotFoundInsidePlaylistException;
import com.ciandt.summit.bootcamp2022.exception.MusicOrPlaylistNotFoundException;
import com.ciandt.summit.bootcamp2022.interceptor.TokenInterceptor;
import com.ciandt.summit.bootcamp2022.service.PlaylistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    private PlaylistController playlistController;

    private Playlist playlist;

    private Music music;

    private static final String PLAYLISTID = "playlist00";

    @BeforeEach
    void initTest() {
        given(tokenInterceptor.preHandle(any(), any(), any())).willReturn(true);
    }

    @Test
    @DisplayName("Playlist not found should return HTTP.StatusCode.BAD_REQUEST")
    void addMusicIntoPlaylistNotFound() throws Exception {

        given(playlistService.addMusicIntoPlaylist(Mockito.any(Music.class), anyString()))
                .willThrow(new MusicOrPlaylistNotFoundException("Not found"));

        String url = "/api/v1/playlist/1/musics";

        String body = String.valueOf(music);

        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    @DisplayName("Add music into playlist should return HTTP.StatusCode.OK")
    void addMusicIntoPlaylistSuccess() throws Exception {

        String url = "/api/v1/playlist/1/musics";

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
                .andExpect(status().isCreated())
                .andExpect(content()
                        .string("Music successfully inserted into playlist")).andReturn();
    }

    @Test
    @DisplayName("Delete music inside playlist should return HTTP.StatusCode.NOCONTENT")
    void deleteMusicofPlaylist() throws Exception {

        String url = "/api/v1/playlist/a39926f4-6acb-4497-884f-d4e5296ef652/musics/5101bd14-32f3-4e65-8503-dea6464af059";

        given(playlistService.deleteMusicFromPlaylist(anyString(), anyString())).willReturn(1);

        mvc.perform(MockMvcRequestBuilders
                        .delete(url))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Music not found inside the playlist")
    void deleteMusicFromPlaylistShouldReturnMusicNotFoundInsidePlaylist() throws Exception {

        String url = "/api/v1/playlist/a39926f4-6acb-4497-884f-d4e5296ef652/musics/5101bd14-32f3-4e65-8503-dea6464af059";

        given(playlistService.deleteMusicFromPlaylist(anyString(), anyString()))
                .willThrow(new MusicNotFoundInsidePlaylistException("Music was not found inside playlist"));

        mvc.perform(MockMvcRequestBuilders
                        .delete(url))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PLaylist not found")
    void deleteMusicFromPlaylistShouldReturnPlaylistNotFound() throws Exception {

        String url = "/api/v1/playlist/a39926f4-6acb-4497-884f-d4e5296ef652/musics/5101bd14-32f3-4e65-8503-dea6464af059";

        given(playlistService.deleteMusicFromPlaylist(anyString(), anyString()))
                .willThrow(new MusicOrPlaylistNotFoundException("Not found"));

        mvc.perform(MockMvcRequestBuilders
                        .delete(url))
                .andExpect(status().isBadRequest());
    }
}