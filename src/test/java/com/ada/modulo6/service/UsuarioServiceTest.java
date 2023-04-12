package com.ada.modulo6.service;


import com.ada.modulo6.exceptions.CamposSemPreenchimentoException;
import com.ada.modulo6.exceptions.UsuarioNaoEncontradoException;
import com.ada.modulo6.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService = new UsuarioService();
    private static List<Usuario> usuarios = new ArrayList<>();
    @BeforeAll
    public static void populandoListaUsuariosAntesDosTestes() {

        usuarios.add(new Usuario("user0", "user0", "pwd0"));
        usuarios.add(new Usuario("user1", "user1", "pwd1"));
        usuarios.add(new Usuario("user2", "user2", "pwd2"));
        usuarios.add(new Usuario("user3", "user3", "pwd3"));
        usuarios.add(new Usuario("user4", "user4", "pwd4"));

    }

    @Test
    public void popularUsuariosTesteLimiteArray() {

        List<Usuario> usuarios = usuarioService.getUsuarios();
        assertEquals(5, usuarios.size());

    }

    @Test
    public void popularUsuariosTesteConteudoArray() {

        List<Usuario> usuarios = usuarioService.getUsuarios();
        assertEquals(usuarios, usuarios);

    }

    @Test
    public void popularUsuariosTesteVerificarConteudoAcimeDoLimite() {
        List<Usuario> usuarios = usuarioService.getUsuarios();
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> usuarios.get(5)
        );

    }

    @Test
    public void popularUsuariosTesteConferirDadosDeUmUsuario() {
        List<Usuario> usuarios = usuarioService.getUsuarios();
        Usuario usuario = usuarios.get(4);
        assertEquals("user4", usuario.getLogin());
        assertEquals("user4", usuario.getNome());
        assertEquals("pwd4", usuario.getSenha());

    }

    @Test
    public void fazerLoginTeste() {
        assertThrows(
                UsuarioNaoEncontradoException.class,
                () -> usuarioService.fazerLogin("user5", "pwd5")
        );

    }

    @Test
    public void fazerLoginTesteSucesso() {
        Usuario usuario = usuarioService.getUsuarios().get(0);
        try {
            Usuario usuarioLogado = usuarioService.fazerLogin(usuario.getLogin(), usuario.getSenha());
            assertEquals(usuario, usuarioLogado);
        } catch (UsuarioNaoEncontradoException e) {
            e.printStackTrace();
        } catch (CamposSemPreenchimentoException e) {
           e.printStackTrace();
        }

    }

    @Test
    public void fazerLoginTesteSenhaIncorreta() {
        Usuario usuario = usuarioService.getUsuarios().get(0);
        assertThrows(
                UsuarioNaoEncontradoException.class,
                () -> usuarioService.fazerLogin(usuario.getLogin(), "pwd1")
        );
    }

    @Test
    public void fazerLoginTesteUsuarioInexistente() {
        Usuario usuario = usuarioService.getUsuarios().get(0);
        assertThrows(
                UsuarioNaoEncontradoException.class,
                () -> usuarioService.fazerLogin("user15", usuario.getSenha())
        );
    }

    @Test
    public void fazerLoginTesteUsuarioIncorreto() {
        Usuario usuario = usuarioService.getUsuarios().get(3);
        assertThrows(
                UsuarioNaoEncontradoException.class,
                () -> usuarioService.fazerLogin("usr3", usuario.getSenha())
        );
    }

    @Test
    public void fazerLoginSenhaVazia() {
        Usuario usuario = usuarioService.getUsuarios().get(3);
        assertThrows(
                CamposSemPreenchimentoException.class,
                () -> usuarioService.fazerLogin(usuario.getLogin(), "")
        );
    }

    @Test
    public void fazerLoginUsuarioVazio() {
        Usuario usuario = usuarioService.getUsuarios().get(3);
        assertThrows(
                CamposSemPreenchimentoException.class,
                () -> usuarioService.fazerLogin("", usuario.getSenha())
        );
    }

}
