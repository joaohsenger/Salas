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

import dao.entity.Usuario;
import java.util.List;
import src.modelo.Sala;

/**
 *  Classe Gerenciar Sala
 *  
 * @author Giovanna Garcia
 * @author Marcos Agnes
 */
public interface GerenciarSala {
    public dao.entity.Sala criarSala (String ident, String descricao, String categoria);
    public boolean deletarSala (Sala s);
    public boolean editarSala (String categoria, String categoriaPessoa);
    public Sala buscarSala (int id);
    public List<Sala> buscarListaSalas(List<dao.entity.Sala> lis);
    public boolean redirecionarSala ();   
    public boolean gravarEdicao ();  
}
