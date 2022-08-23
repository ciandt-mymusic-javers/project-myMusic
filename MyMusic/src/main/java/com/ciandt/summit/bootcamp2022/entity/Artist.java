package com.ciandt.summit.bootcamp2022.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "Artistas")

public class Artist implements Serializable {

    @Id
    @Column(name = "Id")
    @NonNull
    private String id;

    @Column(name = "Nome")
    @NonNull
    private String name;


}
