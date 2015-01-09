/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.enumeracoes;

/**
 *
 * @author giovanna
 */
public enum TipoDecorador {

    CARTEIRA,
    COMPUTADOR,
    MONITOR,
    MOUSE,
    PROJETOR;

    public static String gambi2(String s) {
        switch (s) {
            case "Monitor":
                return "MONITOR";
            case "Mouse":
                return "MOUSE";
            case "Projetor":
                return "PROJETOR";
            case "Carteira":
                return "CARTEIRA";
            case "Computador":
                return "COMPUTADOR";
        }
        return "";
    }
}
