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

import src.modelo.Aula;
import src.padroes.State;
import src.modelo.Sala;

/**
 * Classe Ocupado
 *
 * @description State de uma sala.
 *
 * @author Giovanna Garcia
 * @author Marcos Agnes
 */
public class Ocupado implements State {

    private Aula m_aula;

    public Ocupado(Aula aula) {
        this.m_aula = aula;
    }

    @Override
    public void reservarAula() {
        // ocupar a sala
    }

    @Override
    public void liberarAula() {
        // Ja esta liberado
    }
}
