package com.example.artgallery.utils;

public class removeFormatting {

    private removeFormatting() {
        // Impede a instanciação da classe utilitária.
    }

    public static String removeCpfFormatting(String cpf) {
        if (cpf == null) {
            return null;
        }

        return cpf.replaceAll("\\D", "");
    }

}
