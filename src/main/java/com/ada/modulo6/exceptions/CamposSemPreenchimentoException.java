package com.ada.modulo6.exceptions;

public class CamposSemPreenchimentoException extends Exception{

        public CamposSemPreenchimentoException(){
            super("Credenciais inválidas!");
        }
        public CamposSemPreenchimentoException(String message){
            super(message);
        }
}
