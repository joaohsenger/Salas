/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.modelo.concreto;

import src.modelo.Sala;
import src.padroes.Decorator;

/**
 *
 * @author giovanna
 */
public class Computador extends Decorator {

    private int m_id;
    private Sala m_sala;  
    private String tipo;
    private String id_utfpr;

    public Computador() {
        this.tipo = "Computador";
    }

    public void setM_sala(Sala m_sala) {
        this.m_sala = m_sala;
    }

    public String getId_utfpr() {
        return id_utfpr;
    }

    public void setId_utfpr(String id_utfpr) {
        super.idUtfpr = id_utfpr;
        this.id_utfpr = id_utfpr;
    }        

    public Sala getM_sala() {
        return m_sala;
    }    

    @Override
    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    @Override
    public String getIdentificacao() {
        return this.tipo;
    }

    @Override
    public String getSalaIdUtfpr() {
        return this.m_sala.getIdUtfpr();
    }

    @Override
    public String getDecoradorIdUtfpr() {
        return this.id_utfpr;
    }    
    
}