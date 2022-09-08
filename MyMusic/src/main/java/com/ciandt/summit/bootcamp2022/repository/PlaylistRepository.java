package com.ciandt.summit.bootcamp2022.repository;

import com.ciandt.summit.bootcamp2022.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, String> {
    @Query(value = "SELECT MusicaId as musicId FROM PlaylistMusicas WHERE PlaylistId =:playlistId AND MusicaId =:musicId", nativeQuery = true)
    String findMusicIntoPlaylist(@Param("playlistId") String playlistId, @Param("musicId") String musicId);

    @Modifying
    @Query(value = "DELETE FROM PlaylistMusicas WHERE PlaylistId =:playlistId AND MusicaId =:musicId", nativeQuery = true)
    void deleteMusicFromPlaylist(@Param("playlistId") String playlistId, @Param("musicId") String musicId);
}
