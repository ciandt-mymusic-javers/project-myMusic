package com.ciandt.summit.bootcamp2022.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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

    @Column(name = "Descricao")
    @NonNull
    private String descricao;
}
