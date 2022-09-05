package com.ciandt.summit.bootcamp2022.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "Playlists")
public class Playlist implements Serializable {

    @Id
    @Column(name = "Id")
    @NonNull
    private String id;

    @ManyToMany
    @JoinTable(
            name = "PlaylistMusicas",
            joinColumns = @JoinColumn(name = "PlaylistId"),
            inverseJoinColumns = @JoinColumn(name = "MusicaId")
    )
    private Set<Music> musics;
}
