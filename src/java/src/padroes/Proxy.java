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
package src.padroes;

import dao.entity.Decorador;
import dao.entity.Usuario;
import src.modelo.Pessoa;
import src.modelo.Sala;
import src.modelo.concreto.Reserva;
import src.padroes.interfaces.GerenciarPessoa;
import src.padroes.interfaces.GerenciarReservas;
import src.padroes.interfaces.GerenciarSala;
import src.padroes.interfaces.concreto.GeralProxy;
import src.padroes.interfaces.concreto.ProfessorProxy;
import src.padroes.interfaces.concreto.SetorProxy;

import java.util.List;
import src.padroes.interfaces.GerenciarDecoradores;
import web.beans.utils.Redirecionador;

/**
 * Class Proxy Define o padrão proxy. O proxy deverá ser único para cada pessoa
 * logada no sistema. ** Juliano
 *
 * @author Giovanna Garcia
 * @author Marcos Agnes
 */
public class Proxy implements GerenciarPessoa, GerenciarReservas, GerenciarSala, GerenciarDecoradores {

    private Usuario usuario = null;
    private ProfessorProxy pProxy;
    private GeralProxy gProxy;
    private SetorProxy sProxy;

    public Proxy() {
    }

    public void setpProxy(ProfessorProxy p) {
        pProxy = p;
    }

    public void setgProxy(GeralProxy gProxy) {
        this.gProxy = gProxy;
    }

    public void setsProxy(SetorProxy sProxy) {
        this.sProxy = sProxy;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public ProfessorProxy getpProxy() {
        return pProxy;
    }

    public GeralProxy getgProxy() {
        return gProxy;
    }

    public SetorProxy getsProxy() {
        return sProxy;
    }

    @Override
    public dao.entity.Pessoa criarPessoa(String nome, String tp) {  // TP - é o tipo de pessoa a ser criada
        dao.entity.Pessoa p = null;

        switch (usuario.getTipo()) {
            case 1:           // Um professor nao pode criar uma pessoa
                new Redirecionador().erro("Sem permissão!!");
                break;
            case 2:       // Um responsavel por setor nao pode criar uma pessoa
                new Redirecionador().erro("Sem permissão!!");
                break;
            case 3:      // Um responsavel geral pode criar uma pessoa
                p = gProxy.criarPessoa(nome, tp);
                break;
        }

        return p; // Se retornar null, informar erro de permissao.
    }

    @Override
    public dao.entity.Pessoa criarPessoa(String nome, String tp, String categoria) {  // TP - é o tipo de pessoa a ser criada
        dao.entity.Pessoa p = null;

        switch (usuario.getTipo()) {
            case 1:           // Um professor nao pode criar uma pessoa
                new Redirecionador().erro("Você não tem permissão para criar usuários.");
                break;
            case 2:       // Um responsavel por setor nao pode criar uma pessoa
                new Redirecionador().erro("Você não tem permissão para criar usuários.");
                break;
            case 3:      // Um responsavel geral pode criar uma pessoa
                p = gProxy.criarPessoa(nome, tp, categoria);
                break;
        }

        return p; // Se retornar null, informar erro de permissao.
    }

    @Override
    public boolean editarPessoa() {
        switch (usuario.getTipo()) {
            case 1:           // Um professor nao pode alterar uma pessoa
                return pProxy.editarPessoa();
            case 2:       // Um responsavel por setor pode alterar uma pessoa
                return sProxy.editarPessoa();
            case 3:      // Um responsavel geral pode alterar uma pessoa
                return gProxy.editarPessoa();
        }
        return false;
    }

    @Override
    public Pessoa buscarPessoa(String nome) {
        // Buscar pessa por nome
        Pessoa p = null;
        switch (usuario.getTipo()) {
            case 1:
                p = pProxy.buscarPessoa(nome);
                break;
            case 2:
                p = sProxy.buscarPessoa(nome);
                break;
            case 3:
                p = gProxy.buscarPessoa(nome);
                break;
        }
        return p;
    }

    @Override
    public Pessoa buscarPessoa(int id) {
        // Buscar pessoa por id
        Pessoa p = null;
        switch (usuario.getTipo()) {
            case 1:
                p = pProxy.buscarPessoa(id);
                break;
            case 2:
                p = sProxy.buscarPessoa(id);
                break;
            case 3:
                p = gProxy.buscarPessoa(id);
                break;
        }
        return p;
    }

    @Override
    public Reserva criarReserva(Pessoa p, Sala s) {
        Reserva r = null;
        switch (usuario.getTipo()) {
            case 1:
                r = pProxy.criarReserva(p, s);
                break;
            case 2:
                r = sProxy.criarReserva(p, s);
                break;
            case 3:
                System.out.println("Responsavel geral nao pode criar uma reserva.");
                new Redirecionador().erro("Responsavel geral nao pode criar uma reserva.");
                break;
        }

        return r;
    }

    @Override
    public void liberarReserva(int id) {
        switch (usuario.getTipo()) {
            case 1:
                pProxy.liberarReserva(id);
                break;
            case 2:
                sProxy.liberarReserva(id);
                break;
            case 3:
                System.out.println("Responsavel geral nao pode liberar uma reserva.");
                new Redirecionador().erro("Responsavel geral nao pode liberar uma reserva.");
                break;
        }
    }

    @Override
    public Reserva buscarReserva(int id) {
        // Buscar reserva por id
        Reserva r = null;
        switch (usuario.getTipo()) {
            case 1:
                r = pProxy.buscarReserva(id);
                break;
            case 2:
                r = sProxy.buscarReserva(id);
                break;
            case 3:
                r = gProxy.buscarReserva(id);
                break;
        }
        return r;
    }

    @Override
    public dao.entity.Sala criarSala(String ident, String descricao, String categoria) {
        dao.entity.Sala s = null;
        switch (usuario.getTipo()) {
            case 1:
                //System.out.println("Professor nao pode criar uma sala");
                new Redirecionador().erro("Professor nao pode criar uma sala");
                break;
            case 2:
                //System.out.println("Setor nao pode criar uma sala");
                new Redirecionador().erro("Setor nao pode criar uma sala");
                break;
            case 3:
                s = gProxy.criarSala(ident, descricao, categoria);
                break;
        }
        return s;
    }

    @Override
    public boolean deletarSala(Sala s) {
        switch (usuario.getTipo()) {
            case 1:
                return pProxy.deletarSala(s);
            case 2:
                return sProxy.deletarSala(s);
            case 3:
                return gProxy.deletarSala(s);
        }
        return false;
    }

    @Override
    public boolean editarSala(String categoria, String categoriaPessoa) {
        switch (usuario.getTipo()) {
            case 1:
                return pProxy.editarSala(categoria, categoriaPessoa);
            case 2:
                return sProxy.editarSala(categoria, categoriaPessoa);
            case 3:
                return gProxy.editarSala(categoria, categoriaPessoa);
        }
        return false;
    }

    @Override
    public Sala buscarSala(int id) {
        // Buscar sala por id
        Sala s = null;

        switch (usuario.getTipo()) {
            case 1:
                s = pProxy.buscarSala(id);
                break;
            case 2:
                s = sProxy.buscarSala(id);
                break;
            case 3:
                s = gProxy.buscarSala(id);
                break;
        }

        return s;
    }

    @Override
    public List<Reserva> listarReserva(int codPessoa, List<dao.entity.Reserva> lis) {
        switch (usuario.getTipo()) {
            case 1:
                return pProxy.listarReserva(codPessoa, lis);
            case 2:
                return sProxy.listarReserva(codPessoa, lis);
            case 3:
                return gProxy.listarReserva(codPessoa, lis);
        }
        return null;
    }

    @Override
    public List<Reserva> listarTodasReserva(List<dao.entity.Reserva> lis) {
        switch (usuario.getTipo()) {
            case 1:
                return pProxy.listarTodasReserva(lis);
            case 2:
                return sProxy.listarTodasReserva(lis);
            case 3:
                return gProxy.listarTodasReserva(lis);
        }
        return null;
    }

    @Override
    public List<Reserva> listarReservaSetor(List<dao.entity.Reserva> lis) {
        switch (usuario.getTipo()) {
            case 1:
                return pProxy.listarReservaSetor(lis);
            case 2:
                return sProxy.listarReservaSetor(lis);
            case 3:
                return gProxy.listarReservaSetor(lis);
        }
        return null;
    }

    @Override
    public List<Reserva> listarMinhasReserva(int codPessoa, List<dao.entity.Reserva> lis) {
        switch (usuario.getTipo()) {
            case 1:
                return pProxy.listarMinhasReserva(codPessoa, lis);
            case 2:
                return sProxy.listarMinhasReserva(codPessoa, lis);
            case 3:
                return gProxy.listarMinhasReserva(codPessoa, lis);
        }
        return null;
    }

    @Override
    public List<Sala> buscarListaSalas(List<dao.entity.Sala> lis) {
        switch (usuario.getTipo()) {
            case 1:
                return pProxy.buscarListaSalas(lis);
            case 2:
                return sProxy.buscarListaSalas(lis);
            case 3:
                return gProxy.buscarListaSalas(lis);
        }
        return null;
    }

    @Override
    public List<Pessoa> listarPessoas(List<dao.entity.Pessoa> lis) {
        switch (usuario.getTipo()) {
            case 1:
                return pProxy.listarPessoas(lis);
            case 2:
                return sProxy.listarPessoas(lis);
            case 3:
                return gProxy.listarPessoas(lis);
        }
        return null;
    }

    @Override
    public boolean redirecionarSala() {
        switch (usuario.getTipo()) {
            case 1:
                return pProxy.redirecionarSala();
            case 2:
                return sProxy.redirecionarSala();
            case 3:
                return gProxy.redirecionarSala();
        }
        return false;
    }

    @Override
    public boolean redirecionarReserva() {
        switch (usuario.getTipo()) {
            case 1:
                return pProxy.redirecionarReserva();
            case 2:
                return sProxy.redirecionarReserva();
            case 3:
                return gProxy.redirecionarReserva();
        }
        return false;
    }

    @Override
    public boolean redirecionarPessoa() {
        switch (usuario.getTipo()) {
            case 1:
                return pProxy.redirecionarPessoa();
            case 2:
                return sProxy.redirecionarPessoa();
            case 3:
                return gProxy.redirecionarPessoa();
        }
        return false;
    }

    @Override
    public boolean redirecionarDecoradores() {
        switch (usuario.getTipo()) {
            case 1:
                return pProxy.redirecionarDecoradores();
            case 2:
                return sProxy.redirecionarDecoradores();
            case 3:
                return gProxy.redirecionarDecoradores();
        }
        return false;
    }

    @Override
    public List<Decorator> listarDecoradores(List<Decorador> d) {
        switch (usuario.getTipo()) {
            case 1:
                return pProxy.listarDecoradores(d);
            case 2:
                return sProxy.listarDecoradores(d);
            case 3:
                return gProxy.listarDecoradores(d);
        }
        return null;
    }

    @Override
    public boolean deletarPessoa(Pessoa d) {
        switch (usuario.getTipo()) {
            case 1:
                return pProxy.deletarPessoa(d);
            case 2:
                return sProxy.deletarPessoa(d);
            case 3:
                return gProxy.deletarPessoa(d);
        }
        return false;
    }

    @Override
    public boolean deletarReserva(Reserva r) {
        switch (usuario.getTipo()) {
            case 1:
                return pProxy.deletarReserva(r);
            case 2:
                return sProxy.deletarReserva(r);
            case 3:
                return gProxy.deletarReserva(r);
        }
        return false;
    }

    @Override
    public List<Decorator> listarDecoradoresSala(List<Decorador> d) {
        switch (usuario.getTipo()) {
            case 1:
                return pProxy.listarDecoradoresSala(d);
            case 2:
                return sProxy.listarDecoradoresSala(d);
            case 3:
                return gProxy.listarDecoradoresSala(d);
        }
        return null;
    }

    @Override
    public List<Decorator> listarDecoradoresSalaReserva(List<Decorador> d) {
        switch (usuario.getTipo()) {
            case 1:
                return pProxy.listarDecoradoresSalaReserva(d);
            case 2:
                return sProxy.listarDecoradoresSalaReserva(d);
            case 3:
                return gProxy.listarDecoradoresSalaReserva(d);
        }
        return null;
    }

    @Override
    public boolean redirecionarDecorador() {
        switch (usuario.getTipo()) {
            case 1:
                return pProxy.redirecionarDecorador();
            case 2:
                return sProxy.redirecionarDecorador();
            case 3:
                return gProxy.redirecionarDecorador();
        }
        return false;
    }

    @Override
    public Decorador criarDecorador(String idUtfpr, String tipo, dao.entity.Sala s) {
        switch (usuario.getTipo()) {
            case 1:
                return pProxy.criarDecorador(idUtfpr, tipo, s);
            case 2:
                return sProxy.criarDecorador(idUtfpr, tipo, s);
            case 3:
                return gProxy.criarDecorador(idUtfpr, tipo, s);
        }
        return null;
    }

    @Override
    public boolean deletarDecorador() {
        switch (usuario.getTipo()) {
            case 1:
                return pProxy.deletarDecorador();
            case 2:
                return sProxy.deletarDecorador();
            case 3:
                return gProxy.deletarDecorador();
        }
        return false;
    }

    @Override
    public boolean editarDecorador() {
        switch (usuario.getTipo()) {
            case 1:
                return pProxy.editarDecorador();
            case 2:
                return sProxy.editarDecorador();
            case 3:
                return gProxy.editarDecorador();
        }
        return false;
    }

    @Override
    public boolean redirecionarMinhasReservas() {
        switch (usuario.getTipo()) {
            case 1:
                return pProxy.redirecionarMinhasReservas();
            case 2:
                return sProxy.redirecionarMinhasReservas();
            case 3:
                return gProxy.redirecionarMinhasReservas();
        }
        return false;
    }

    @Override
    public boolean gravarEdicao() {
        switch (usuario.getTipo()) {
            case 1:
                return pProxy.gravarEdicao();
            case 2:
                return sProxy.gravarEdicao();
            case 3:
                return gProxy.gravarEdicao();
        }
        return false;
    }

}
