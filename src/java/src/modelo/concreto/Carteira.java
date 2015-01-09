/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation;
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package src.modelo.concreto;

import src.modelo.Sala;
import src.padroes.Decorator;

/**
 *  Classe Carteira
 *          Especifica um determinado objeto dentro da sala.
 *  
 * @author Giovanna Garcia
 * @author Marcos Agnes
 */
public class Carteira extends Decorator {

    private int m_id;
    private Sala m_sala;  
    private String tipo;
    private String id_utfpr;

    public Carteira() {
        this.tipo = "Carteira";
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
