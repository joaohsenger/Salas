/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.modelo;

import src.enumeracoes.IdAula;
import src.modelo.concreto.Disponivel;
import src.padroes.State;

/**
 * 
 * @author Marcos
 */
public class Aula {
    // State da sala
    protected State m_estadoOcupado;
    protected State m_estadoDisponivel;
    protected State m_estado = new Disponivel(this);
    private Sala m_Sala;
    private String m_id;
    
    private boolean m_disponivel;

    public boolean isM_disponivel() {
        return m_disponivel;
    }

    public void setM_disponivel(boolean m_disponivel) {
        this.m_disponivel = m_disponivel;
    }        

    public Aula() {
    }       
    
    // Gets
    public State getEstadoOcupado () {
        return this.m_estadoOcupado;
    }
    
    public State getEstadoDisponivel () {
        return this.m_estadoDisponivel;
    }  
    
    // Sets
    public void setEstado (State estado) {
        this.m_estado = estado;
    }

    public void setM_Sala(Sala m_Sala) {
        this.m_Sala = m_Sala;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public Sala getM_Sala() {
        return m_Sala;
    }

    public String getM_id() {
        return m_id;
    }        
    
    /*
     *  Reservar Sala
     *      Método para reserva de sala.
     *  
     *  @public
     *  @return void
     */
    public void reservarAula () {
       this.m_estado.reservarAula();
    }
    
    /*
     *  Reservar Sala
     *      Método para liberação de sala.
     *  
     *  @public
     *  @return void
     */
    public void liberarAula() {
        this.m_estado.liberarAula();
    }
    
    
    public String convertAulaString(int aula) {
        String name = "";
        switch (aula) {
            case 0:
                name = "M1";
                break;
            case 1:
                name = "M2";
                break;
            case 2:
                name = "M3";
                break;
            case 3:
                name = "M4";
                break;
            case 4:
                name = "M5";
                break;
            case 5:
                name = "T1";
                break;
            case 6:
                name = "T2";
                break;
            case 7:
                name = "T3";
                break;
            case 8:
                name = "T4";
                break;
            case 9:
                name = "T5";
                break;
            case 10:
                name = "N1";
                break;
            case 11:
                name = "N2";
                break;
            case 12:
                name = "N3";
                break;
            case 13:
                name = "N4";
                break;
            case 14:
                name = "N5";
                break;
            default:
                name = "";
                break;
        }
        return name;
    }
    
    public int convertAulaInteiro(String aula) {        
        switch (aula) {
            case "M1":
                return 0;
            case "M2":
                return 1;
            case "M3":
                return 2;
            case "M4":
                return 3;
            case "M5":
                return 4;
            case "T1":
                return 5;
            case "T2":
                return 6;
            case "T3":
                return 7;
            case "T4":
                return 8;
            case "T5":
                return 9;
            case "N1":
               return 10;
            case "N2":
                return 11;
            case "N3":
                return 12;
            case "N4":
                return 13;
            case "N5":
                return 14;
        }
        return -1;
    }
}
