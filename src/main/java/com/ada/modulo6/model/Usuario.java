package com.ada.modulo6.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Autowired
    private String nome;
    private String login;
    private String senha;

}
