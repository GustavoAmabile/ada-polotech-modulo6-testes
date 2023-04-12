package com.ada.modulo6.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class UsuarioNaoEncontradoException extends Exception{

        public UsuarioNaoEncontradoException(){
            super("Usuário não encontrado!");
        }
        public UsuarioNaoEncontradoException(String message){
            super(message);
        }

}
