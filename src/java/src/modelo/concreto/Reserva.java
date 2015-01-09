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

import java.util.Date;
import src.modelo.Aula;
import src.modelo.Pessoa;
import src.modelo.Sala;

/**
 *  Classe Reserva
 *  
 * @author Giovanna Garcia
 * @author Marcos Agnes
 */
public class Reserva {
    private Date data;
    private Sala sala;
    private Pessoa pessoa;
    private Aula m_aula;
    private int codigo;
    
    public Reserva() {
    }

    public Aula getM_aula() {
        return m_aula;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }        

    public void setM_aula(Aula m_aula) {
        this.m_aula = m_aula;
    }
    
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String toString() {
        return "Reserva{" + "data=" + data + ", sala=" + sala + ", pessoa=" + pessoa + ", m_aula=" + m_aula + '}';
    }      
    
    
}
