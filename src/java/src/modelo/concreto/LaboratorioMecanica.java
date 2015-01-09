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

/**
 *  Laboratório Mecânica
 *  
 * @author Giovanna Garcia
 * @author Marcos Agnes
 */
public class LaboratorioMecanica extends Sala {
    private int m_id;

    public LaboratorioMecanica(String id_utfpr) {
        super.m_identificacao = "Laboratório de Mecânica";
        super.idUtfpr = id_utfpr;
    }    

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        super.id = m_id;
        this.m_id = m_id;
    }
}
