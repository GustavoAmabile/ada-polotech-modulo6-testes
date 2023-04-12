package com.ada.modulo6.service;

import com.ada.modulo6.exceptions.CamposSemPreenchimentoException;
import com.ada.modulo6.exceptions.UsuarioNaoEncontradoException;
import com.ada.modulo6.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
@AllArgsConstructor
public class UsuarioService {
    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioService() {
        this.popularUsuarios();
    }


    public void popularUsuarios(){

        for (int i=0; i < 5; i++){

            usuarios.add(new Usuario("user" + i, "user" + i, "pwd" + i));

        }

    }


    public Usuario fazerLogin(String login, String senha) throws UsuarioNaoEncontradoException, CamposSemPreenchimentoException {

        if (login == null || senha == null) {
            throw new CamposSemPreenchimentoException();
        } else if (login.isEmpty() || senha.isEmpty()) {
            throw new CamposSemPreenchimentoException();
        } else if (login.isBlank() || senha.isBlank()) {
            throw new CamposSemPreenchimentoException();
        } else {
            for (Usuario usuario : usuarios) {
                if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                    return usuario;
                }
            }
            throw new UsuarioNaoEncontradoException();
        }

        }




}
