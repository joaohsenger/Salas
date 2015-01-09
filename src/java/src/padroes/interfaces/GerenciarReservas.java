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
package src.padroes.interfaces;

import java.util.List;
import src.modelo.Pessoa;
import src.modelo.Sala;
import src.modelo.concreto.Reserva;

/**
 *  Classe Gerenciar Reservas
 *  
 * @author Giovanna Garcia
 * @author Marcos Agnes
 */
public interface GerenciarReservas {
    public Reserva criarReserva (Pessoa p, Sala s);
    public void liberarReserva (int id);
    public Reserva buscarReserva (int id);
    public List<Reserva> listarReserva(int codPessoa, List<dao.entity.Reserva> res);
    public List<Reserva> listarTodasReserva(List<dao.entity.Reserva> lis);
    public List<Reserva> listarReservaSetor(List<dao.entity.Reserva> lis);
    public List<Reserva> listarMinhasReserva(int codPessoa, List<dao.entity.Reserva> lis);
    public boolean redirecionarReserva();
    public boolean deletarReserva (Reserva r);
    public boolean redirecionarMinhasReservas ();
}
