package com.ciandt.summit.bootcamp2022.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "TipoUsuario")
public class UserType implements Serializable {

    @Id
    @Column(name = "Id")
    @NonNull
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "Descricao")
    @NonNull
    private ERole description;
}
