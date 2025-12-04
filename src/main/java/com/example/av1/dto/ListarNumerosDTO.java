package com.example.av1.dto;

import java.util.List;
import java.io.Serializable;

public class ListarNumerosDTO implements Serializable {

    private List<Integer> numeros;

    public List<Integer> getNumeros() {
        return numeros;
    }

    public void setNumeros(List<Integer> numeros) {
        this.numeros = numeros;
    }
}