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

/**
 *  Classe Sala
 *      Esta classe representa as salas que existem na UTFPr e estão disponíveis 
 *  para o uso.
 * 
 * @author Giovanna Garcia
 * @author Marcos Agnes
 */
public abstract class Sala {
    protected String m_identificacao = "Sala sem identificação"; 
    protected int id;
    protected String idUtfpr; // identificacao como L18 L19 etc
    protected String categoria;

    public int getId() {
        return id;
    }

    public String getIdUtfpr() {
        return idUtfpr;
    }

    public void setIdUtfpr(String idUtfpr) {
        this.idUtfpr = idUtfpr;
    }        

    public void setId(int id) {
        this.id = id;
    }        
  
    public String getIdentificacao () {
        return m_identificacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }        
    
}
