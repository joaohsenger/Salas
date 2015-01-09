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
package src.modelo;

import java.util.Date;

/**
 *  Classe Pessoa
 *  
 * @author Giovanna Garcia
 * @author Marcos Agnes
 */
public abstract class Pessoa {
    protected int id;
    protected String m_nome;
    protected Date m_inicio;

    public String getM_nome() {
        return m_nome;
    }

    public void setM_nome(String m_nome) {
        this.m_nome = m_nome;
    }

    public abstract String getCategoria ();
    

    public Date getM_inicio() {
        return m_inicio;
    }

    public void setM_inicio(Date m_inicio) {
        this.m_inicio = m_inicio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
    public abstract String getDescricao ();
}
