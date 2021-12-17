package com.missionexpress.utilidades;

import java.util.Collection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dohko
 */
public class Validator {

    /**
     * Valida si es númerico
     *
     * @param numero
     * @return retorna null si no es númerico, de lo contrario retonar el valor
     */
    public static String validarNumero(final String numero) {
        if (numero.matches("[0-9]+")) {
            return numero;
        } else {
            return null;
        }

    }

    /**
     * Valida si es vacio el vector
     *
     * @param objeto
     * @return retorna true si esta vacio de lo contratio falso
     */
    public static Boolean isArrayNull(final Object[] objeto) {
        return objeto == null || objeto.length == 0;
    }
    
    /**
     * 
     * @param lista
     * @return 
     */
    public static Boolean isListNull(final Collection lista) {
        return lista == null || lista.isEmpty();
    }
    

}
