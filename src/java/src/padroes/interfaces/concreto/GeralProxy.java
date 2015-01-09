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
package src.padroes.interfaces.concreto;

import dao.entity.Decorador;
import dao.entity.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import src.modelo.Aula;
import src.modelo.Pessoa;
import src.modelo.Sala;
import src.modelo.concreto.Carteira;
import src.modelo.concreto.Computador;
import src.modelo.concreto.Disponivel;
import src.modelo.concreto.LaboratorioInformatica;
import src.modelo.concreto.LaboratorioMecanica;
import src.modelo.concreto.LaboratorioQuimica;
import src.modelo.concreto.Monitor;
import src.modelo.concreto.Mouse;
import src.modelo.concreto.Professor;
import src.modelo.concreto.Projetor;
import src.modelo.concreto.Reserva;
import src.modelo.concreto.ResponsavelGeral;
import src.modelo.concreto.ResponsavelSetor;
import src.modelo.concreto.Teorica;
import src.padroes.Decorator;
import src.padroes.interfaces.GerenciarDecoradores;
import src.padroes.interfaces.GerenciarPessoa;
import src.padroes.interfaces.GerenciarReservas;
import src.padroes.interfaces.GerenciarSala;

/**
 * Classe Geral Proxy
 *
 * @author Giovanna Garcia
 * @author Marcos Agnes
 */
public class GeralProxy implements GerenciarPessoa, GerenciarReservas, GerenciarSala, GerenciarDecoradores {

    @Override
    public Decorador criarDecorador(String idUtfpr, String tipo, dao.entity.Sala s) {
        Decorador d = new Decorador();
        d.setIdUtfpr(idUtfpr);
        d.setSalaCod(s);
        d.setTipo(tipo);
        return d;
    }

    @Override
    public boolean deletarPessoa(Pessoa pe) {
        return true;
    }

    @Override
    public boolean deletarReserva(Reserva r) {
        return false;
    }

    @Override
    public List<Decorator> listarDecoradores(List<Decorador> decorator) {
        List<Decorator> ld = new ArrayList<>();
        for (Decorador d : decorator) {
            Sala s = null;
            switch (d.getSalaCod().getTipo()) {
                case "LAB_MECANICA":
                    LaboratorioMecanica lm = new LaboratorioMecanica(d.getSalaCod().getIdUtfpr());
                    lm.setM_id(d.getSalaCod().getCod());
                    lm.setIdUtfpr(d.getSalaCod().getIdUtfpr());
                    s = lm;
                    break;
                case "TEORICA":
                    Teorica t = new Teorica(d.getSalaCod().getIdUtfpr());
                    t.setM_Id(d.getSalaCod().getCod());
                    t.setIdUtfpr(d.getSalaCod().getIdUtfpr());
                    s = t;
                    break;
                case "LAB_INFORMATICA":
                    LaboratorioInformatica lr = new LaboratorioInformatica(d.getSalaCod().getIdUtfpr());
                    lr.setM_id(d.getSalaCod().getCod());
                    lr.setIdUtfpr(d.getSalaCod().getIdUtfpr());
                    s = lr;
                    break;
                case "LAB_QUIMICA":
                    LaboratorioQuimica lq = new LaboratorioQuimica(d.getSalaCod().getIdUtfpr());
                    lq.setM_id(d.getSalaCod().getCod());
                    lq.setIdUtfpr(d.getSalaCod().getIdUtfpr());
                    s = lq;
                    break;
            }
            switch (d.getTipo()) {
                case "CARTEIRA":
                    Carteira c = new Carteira();
                    c.setM_id(d.getCod());
                    c.setM_sala(s);
                    c.setId_utfpr(d.getIdUtfpr());
                    ld.add(c);
                    break;
                case "MONITOR":
                    Monitor m = new Monitor();
                    m.setM_id(d.getCod());
                    m.setM_sala(s);
                    m.setId_utfpr(d.getIdUtfpr());
                    ld.add(m);
                    break;
                case "MOUSE":
                    Mouse mo = new Mouse();
                    mo.setM_id(d.getCod());
                    mo.setM_sala(s);
                    mo.setId_utfpr(d.getIdUtfpr());
                    ld.add(mo);
                    break;
                case "PROJETOR":
                    Projetor p = new Projetor();
                    p.setM_id(d.getCod());
                    p.setM_sala(s);
                    p.setId_utfpr(d.getIdUtfpr());
                    ld.add(p);
                    break;
                case "COMPUTADOR":
                    Computador co = new Computador();
                    co.setM_id(d.getCod());
                    co.setM_sala(s);
                    co.setId_utfpr(d.getIdUtfpr());
                    ld.add(co);
                    break;
            }
        }
        return ld;
    }

    @Override
    public List<Decorator> listarDecoradoresSala(List<Decorador> decorator) {
        List<Decorator> ld = new ArrayList<>();
        for (Decorador d : decorator) {
            Sala s = null;
            switch (d.getSalaCod().getTipo()) {
                case "LAB_MECANICA":
                    LaboratorioMecanica lm = new LaboratorioMecanica(d.getSalaCod().getIdUtfpr());
                    lm.setM_id(d.getSalaCod().getCod());
                    lm.setIdUtfpr(d.getSalaCod().getIdUtfpr());
                    s = lm;
                    break;
                case "TEORICA":
                    Teorica t = new Teorica(d.getSalaCod().getIdUtfpr());
                    t.setM_Id(d.getSalaCod().getCod());
                    t.setIdUtfpr(d.getSalaCod().getIdUtfpr());
                    s = t;
                    break;
                case "LAB_INFORMATICA":
                    LaboratorioInformatica lr = new LaboratorioInformatica(d.getSalaCod().getIdUtfpr());
                    lr.setM_id(d.getSalaCod().getCod());
                    lr.setIdUtfpr(d.getSalaCod().getIdUtfpr());
                    s = lr;
                    break;
                case "LAB_QUIMICA":
                    LaboratorioQuimica lq = new LaboratorioQuimica(d.getSalaCod().getIdUtfpr());
                    lq.setM_id(d.getSalaCod().getCod());
                    lq.setIdUtfpr(d.getSalaCod().getIdUtfpr());
                    s = lq;
                    break;
            }
            switch (d.getTipo()) {
                case "CARTEIRA":
                    Carteira c = new Carteira();
                    c.setM_id(d.getCod());
                    c.setM_sala(s);
                    c.setId_utfpr(d.getIdUtfpr());
                    ld.add(c);
                    break;
                case "MONITOR":
                    Monitor m = new Monitor();
                    m.setM_id(d.getCod());
                    m.setM_sala(s);
                    m.setId_utfpr(d.getIdUtfpr());
                    ld.add(m);
                    break;
                case "MOUSE":
                    Mouse mo = new Mouse();
                    mo.setM_id(d.getCod());
                    mo.setM_sala(s);
                    mo.setId_utfpr(d.getIdUtfpr());
                    ld.add(mo);
                    break;
                case "PROJETOR":
                    Projetor p = new Projetor();
                    p.setM_id(d.getCod());
                    p.setM_sala(s);
                    p.setId_utfpr(d.getIdUtfpr());
                    ld.add(p);
                    break;
                case "COMPUTADOR":
                    Computador co = new Computador();
                    co.setM_id(d.getCod());
                    co.setM_sala(s);
                    co.setId_utfpr(d.getIdUtfpr());
                    ld.add(co);
                    break;
            }
        }
        return ld;
    }

    @Override
    public List<Decorator> listarDecoradoresSalaReserva(List<Decorador> decorator) {
        List<Decorator> ld = new ArrayList<>();
        for (Decorador d : decorator) {
            Sala s = null;
            switch (d.getSalaCod().getTipo()) {
                case "LAB_MECANICA":
                    LaboratorioMecanica lm = new LaboratorioMecanica(d.getSalaCod().getIdUtfpr());
                    lm.setM_id(d.getSalaCod().getCod());
                    lm.setIdUtfpr(d.getSalaCod().getIdUtfpr());
                    s = lm;
                    break;
                case "TEORICA":
                    Teorica t = new Teorica(d.getSalaCod().getIdUtfpr());
                    t.setM_Id(d.getSalaCod().getCod());
                    t.setIdUtfpr(d.getSalaCod().getIdUtfpr());
                    s = t;
                    break;
                case "LAB_INFORMATICA":
                    LaboratorioInformatica lr = new LaboratorioInformatica(d.getSalaCod().getIdUtfpr());
                    lr.setM_id(d.getSalaCod().getCod());
                    lr.setIdUtfpr(d.getSalaCod().getIdUtfpr());
                    s = lr;
                    break;
                case "LAB_QUIMICA":
                    LaboratorioQuimica lq = new LaboratorioQuimica(d.getSalaCod().getIdUtfpr());
                    lq.setM_id(d.getSalaCod().getCod());
                    lq.setIdUtfpr(d.getSalaCod().getIdUtfpr());
                    s = lq;
                    break;
            }
            switch (d.getTipo()) {
                case "CARTEIRA":
                    Carteira c = new Carteira();
                    c.setM_id(d.getCod());
                    c.setM_sala(s);
                    c.setId_utfpr(d.getIdUtfpr());
                    ld.add(c);
                    break;
                case "MONITOR":
                    Monitor m = new Monitor();
                    m.setM_id(d.getCod());
                    m.setM_sala(s);
                    m.setId_utfpr(d.getIdUtfpr());
                    ld.add(m);
                    break;
                case "MOUSE":
                    Mouse mo = new Mouse();
                    mo.setM_id(d.getCod());
                    mo.setM_sala(s);
                    mo.setId_utfpr(d.getIdUtfpr());
                    ld.add(mo);
                    break;
                case "PROJETOR":
                    Projetor p = new Projetor();
                    p.setM_id(d.getCod());
                    p.setM_sala(s);
                    p.setId_utfpr(d.getIdUtfpr());
                    ld.add(p);
                    break;
                case "COMPUTADOR":
                    Computador co = new Computador();
                    co.setM_id(d.getCod());
                    co.setM_sala(s);
                    co.setId_utfpr(d.getIdUtfpr());
                    ld.add(co);
                    break;
            }
        }
        return ld;
    }

    @Override
    public List<Reserva> listarMinhasReserva(int codPessoa, List<dao.entity.Reserva> lis) {
        List<Reserva> reservasConcretas = new ArrayList<>();
        try {
            for (dao.entity.Reserva r : lis) {
                Reserva reservaC = new Reserva();
                // aula
                Aula a = new Aula();
                a.setM_id(this.convertAula(r.getAula()));
                a.setEstado(new Disponivel(a));

                reservaC.setM_aula(a);
                reservaC.setData(r.getData());
                // pessoa
                switch (r.getPessoaCod().getTipo()) {
                    case "RESPONSAVEL_GERAL":
                        ResponsavelGeral rg = new ResponsavelGeral();
                        rg.setM_nome(r.getPessoaCod().getNome());
                        reservaC.setPessoa(rg);
                        break;
                    case "RESPONSAVEL_SETOR":
                        ResponsavelSetor rs = new ResponsavelSetor();
                        rs.setM_nome(r.getPessoaCod().getNome());
                        reservaC.setPessoa(rs);
                        break;
                    case "PROFESSOR":
                        Professor pro = new Professor();
                        pro.setM_nome(r.getPessoaCod().getNome());
                        reservaC.setPessoa(pro);
                        break;
                }
                // sala
                switch (r.getSalaCod().getTipo()) {
                    case "LAB_MECANICA":
                        LaboratorioMecanica lm = new LaboratorioMecanica(r.getSalaCod().getIdUtfpr());
                        lm.setM_id(r.getSalaCod().getCod());
                        a.setM_Sala(lm);
                        break;
                    case "TEORICA":
                        Teorica t = new Teorica(r.getSalaCod().getIdUtfpr());
                        t.setM_Id(r.getSalaCod().getCod());
                        a.setM_Sala(t);
                        break;
                    case "LAB_INFORMATICA":
                        LaboratorioInformatica lr = new LaboratorioInformatica(r.getSalaCod().getIdUtfpr());
                        lr.setM_id(r.getSalaCod().getCod());
                        a.setM_Sala(lr);
                        break;
                    case "LAB_QUIMICA":
                        LaboratorioQuimica lq = new LaboratorioQuimica(r.getSalaCod().getIdUtfpr());
                        lq.setM_id(r.getSalaCod().getCod());
                        a.setM_Sala(lq);
                        break;
                }
                reservaC.setSala(a.getM_Sala()); // recebe a sala da aula                
                reservasConcretas.add(reservaC);
            }
        } catch (Exception e) {
        }
        return reservasConcretas;
    }

    @Override
    public List<Reserva> listarTodasReserva(List<dao.entity.Reserva> lis) {
        List<Reserva> reservasConcretas = new ArrayList<>();
        for (dao.entity.Reserva r : lis) {
            Reserva reservaC = new Reserva();
            // aula
            Aula a = new Aula();
            a.setM_id(this.convertAula(r.getAula()));
            a.setEstado(new Disponivel(a));

            reservaC.setM_aula(a);
            reservaC.setData(r.getData());
            // pessoa
            switch (r.getPessoaCod().getTipo()) {
                case "RESPONSAVEL_GERAL":
                    ResponsavelGeral rg = new ResponsavelGeral();
                    rg.setM_nome(r.getPessoaCod().getNome());
                    reservaC.setPessoa(rg);
                    break;
                case "RESPONSAVEL_SETOR":
                    ResponsavelSetor rs = new ResponsavelSetor();
                    rs.setM_nome(r.getPessoaCod().getNome());
                    reservaC.setPessoa(rs);
                    break;
                case "PROFESSOR":
                    Professor pro = new Professor();
                    pro.setM_nome(r.getPessoaCod().getNome());
                    reservaC.setPessoa(pro);
                    break;
            }
            // sala
            switch (r.getSalaCod().getTipo()) {
                case "LAB_MECANICA":
                    LaboratorioMecanica lm = new LaboratorioMecanica(r.getSalaCod().getIdUtfpr());
                    lm.setM_id(r.getSalaCod().getCod());
                    a.setM_Sala(lm);
                    break;
                case "TEORICA":
                    Teorica t = new Teorica(r.getSalaCod().getIdUtfpr());
                    t.setM_Id(r.getSalaCod().getCod());
                    a.setM_Sala(t);
                    break;
                case "LAB_INFORMATICA":
                    LaboratorioInformatica lr = new LaboratorioInformatica(r.getSalaCod().getIdUtfpr());
                    lr.setM_id(r.getSalaCod().getCod());
                    a.setM_Sala(lr);
                    break;
                case "LAB_QUIMICA":
                    LaboratorioQuimica lq = new LaboratorioQuimica(r.getSalaCod().getIdUtfpr());
                    lq.setM_id(r.getSalaCod().getCod());
                    a.setM_Sala(lq);
                    break;
            }
            reservaC.setSala(a.getM_Sala()); // recebe a sala da aula                
            reservasConcretas.add(reservaC);
        }
        return reservasConcretas;
    }
    
    @Override
    public List<Reserva> listarReservaSetor(List<dao.entity.Reserva> lis) {
        List<Reserva> reservasConcretas = new ArrayList<>();
        for (dao.entity.Reserva r : lis) {
            Reserva reservaC = new Reserva();
            // aula
            Aula a = new Aula();
            a.setM_id(this.convertAula(r.getAula()));
            a.setEstado(new Disponivel(a));

            reservaC.setM_aula(a);
            reservaC.setData(r.getData());
            // pessoa
            switch (r.getPessoaCod().getTipo()) {
                case "RESPONSAVEL_GERAL":
                    ResponsavelGeral rg = new ResponsavelGeral();
                    rg.setM_nome(r.getPessoaCod().getNome());
                    reservaC.setPessoa(rg);
                    break;
                case "RESPONSAVEL_SETOR":
                    ResponsavelSetor rs = new ResponsavelSetor();
                    rs.setM_nome(r.getPessoaCod().getNome());
                    reservaC.setPessoa(rs);
                    break;
                case "PROFESSOR":
                    Professor pro = new Professor();
                    pro.setM_nome(r.getPessoaCod().getNome());
                    reservaC.setPessoa(pro);
                    break;
            }
            // sala
            switch (r.getSalaCod().getTipo()) {
                case "LAB_MECANICA":
                    LaboratorioMecanica lm = new LaboratorioMecanica(r.getSalaCod().getIdUtfpr());
                    lm.setM_id(r.getSalaCod().getCod());
                    a.setM_Sala(lm);
                    break;
                case "TEORICA":
                    Teorica t = new Teorica(r.getSalaCod().getIdUtfpr());
                    t.setM_Id(r.getSalaCod().getCod());
                    a.setM_Sala(t);
                    break;
                case "LAB_INFORMATICA":
                    LaboratorioInformatica lr = new LaboratorioInformatica(r.getSalaCod().getIdUtfpr());
                    lr.setM_id(r.getSalaCod().getCod());
                    a.setM_Sala(lr);
                    break;
                case "LAB_QUIMICA":
                    LaboratorioQuimica lq = new LaboratorioQuimica(r.getSalaCod().getIdUtfpr());
                    lq.setM_id(r.getSalaCod().getCod());
                    a.setM_Sala(lq);
                    break;
            }
            reservaC.setSala(a.getM_Sala()); // recebe a sala da aula                
            reservasConcretas.add(reservaC);
        }
        return reservasConcretas;
    }

    @Override
    public List<Reserva> listarReserva(int codPessoa, List<dao.entity.Reserva> lis) {
        List<Reserva> reservasConcretas = new ArrayList<>();
        for (dao.entity.Reserva r : lis) {
            Reserva reservaC = new Reserva();
            // aula
            Aula a = new Aula();
            a.setM_id(this.convertAula(r.getAula()));
            a.setEstado(new Disponivel(a));

            reservaC.setM_aula(a);
            reservaC.setData(r.getData());
            // pessoa
            switch (r.getPessoaCod().getTipo()) {
                case "RESPONSAVEL_GERAL":
                    ResponsavelGeral rg = new ResponsavelGeral();
                    rg.setM_nome(r.getPessoaCod().getNome());
                    reservaC.setPessoa(rg);
                    break;
                case "RESPONSAVEL_SETOR":
                    ResponsavelSetor rs = new ResponsavelSetor();
                    rs.setM_nome(r.getPessoaCod().getNome());
                    reservaC.setPessoa(rs);
                    break;
                case "PROFESSOR":
                    Professor pro = new Professor();
                    pro.setM_nome(r.getPessoaCod().getNome());
                    reservaC.setPessoa(pro);
                    break;
            }
            // sala
            switch (r.getSalaCod().getTipo()) {
                case "LAB_MECANICA":
                    LaboratorioMecanica lm = new LaboratorioMecanica(r.getSalaCod().getIdUtfpr());
                    lm.setM_id(r.getSalaCod().getCod());
                    a.setM_Sala(lm);
                    break;
                case "TEORICA":
                    Teorica t = new Teorica(r.getSalaCod().getIdUtfpr());
                    t.setM_Id(r.getSalaCod().getCod());
                    a.setM_Sala(t);
                    break;
                case "LAB_INFORMATICA":
                    LaboratorioInformatica lr = new LaboratorioInformatica(r.getSalaCod().getIdUtfpr());
                    lr.setM_id(r.getSalaCod().getCod());
                    a.setM_Sala(lr);
                    break;
                case "LAB_QUIMICA":
                    LaboratorioQuimica lq = new LaboratorioQuimica(r.getSalaCod().getIdUtfpr());
                    lq.setM_id(r.getSalaCod().getCod());
                    a.setM_Sala(lq);
                    break;
            }
            reservaC.setSala(a.getM_Sala()); // recebe a sala da aula                
            reservasConcretas.add(reservaC);
        }
        return reservasConcretas;
    }

    @Override
    public dao.entity.Pessoa criarPessoa(String nome, String tp) {
        dao.entity.Pessoa pDao = new dao.entity.Pessoa();
        switch (tp) {
            case "RESPONSAVEL_GERAL": {
                ResponsavelGeral rg = new ResponsavelGeral();
                rg.setM_inicio(new Date());
                rg.setM_nome(nome);
                pDao.setDataCadastro(rg.getM_inicio());
                pDao.setNome(rg.getM_nome());
                pDao.setTipo("RESPONSAVEL_GERAL");
            }
            break;
            case "RESPONSAVEL_SETOR": {
                ResponsavelSetor rs = new ResponsavelSetor();
                rs.setM_inicio(new Date());
                rs.setM_nome(nome);
                pDao.setDataCadastro(rs.getM_inicio());
                pDao.setNome(rs.getM_nome());
                pDao.setTipo("RESPONSAVEL_SETOR");
            }
            break;
            case "PROFESSOR": {
                Professor pr = new Professor();
                pr.setM_inicio(new Date());
                pr.setM_nome(nome);
                pDao.setDataCadastro(pr.getM_inicio());
                pDao.setNome(pr.getM_nome());
                pDao.setTipo("PROFESSOR");
            }
            break;
        }
        pDao.setCategoria("semCategoria");
        return pDao;
    }

    @Override
    public dao.entity.Pessoa criarPessoa(String nome, String tp, String cat) {
        dao.entity.Pessoa pDao = new dao.entity.Pessoa();
        ResponsavelSetor rs = new ResponsavelSetor(cat);
        rs.setM_inicio(new Date());
        rs.setM_nome(nome);
        pDao.setDataCadastro(rs.getM_inicio());
        pDao.setNome(rs.getM_nome());
        pDao.setTipo("RESPONSAVEL_SETOR");
        pDao.setCategoria(rs.getCategoria());
        return pDao;
    }

    @Override
    public boolean editarPessoa() {
        return true;
    }

    @Override
    public Pessoa buscarPessoa(String nome) {
        // Buscar pessoa por nome
        Pessoa p = null;

        return p;
    }

    @Override
    public Pessoa buscarPessoa(int id) {
        // Buscar pessoa por id
        Pessoa p = null;

        return p;
    }

    @Override
    public Reserva criarReserva(Pessoa p, Sala s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void liberarReserva(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reserva buscarReserva(int id) {
        // Buscar reserva por id
        Reserva r = null;

        return r;
    }

    @Override
    public dao.entity.Sala criarSala(String ident, String tp, String categoria) {
        dao.entity.Sala s = new dao.entity.Sala();        
        switch (tp) {
            case "LAB_INFORMATICA": {
                LaboratorioInformatica li = new LaboratorioInformatica(ident);
                li.setCategoria(categoria);
                s.setCategoria(li.getCategoria());
                s.setIdSala(li.getIdentificacao());
                s.setIdUtfpr(li.getIdUtfpr());
                s.setTipo("LAB_INFORMATICA");
            }
            break;
            case "LAB_MECANICA": {
                LaboratorioMecanica lm = new LaboratorioMecanica(ident);
                lm.setCategoria(categoria);
                s.setCategoria(lm.getCategoria());
                s.setIdSala(lm.getIdentificacao());
                s.setIdUtfpr(lm.getIdUtfpr());
                s.setTipo("LAB_MECANICA");
            }
            break;
            case "TEORICA": {
                Teorica t = new Teorica(ident);
                t.setCategoria(categoria);
                s.setCategoria(t.getCategoria());
                s.setIdSala(t.getIdentificacao());
                s.setIdUtfpr(t.getIdUtfpr());
                s.setTipo("TEORICA");
            }
            break;
            case "LAB_QUIMICA": {
                LaboratorioQuimica lq = new LaboratorioQuimica(ident);
                lq.setCategoria(categoria);
                s.setCategoria(lq.getCategoria());
                s.setIdSala(lq.getIdentificacao());
                s.setIdUtfpr(lq.getIdUtfpr());
                s.setTipo("LAB_QUIMICA");
            }
            break;
        }
        return s;
    }

    @Override
    public boolean deletarSala(Sala s) {
        return true;
    }

    @Override
    public boolean editarSala(String categoria, String categoriaPessoa) {
        return true;
    }

    @Override
    public Sala buscarSala(int id) {
        // Buscar sala por id
        Sala s = null;

        return s;
    }

    public String convertAula(int aula) {
        String name = "";
        switch (aula) {
            case 0:
                name = "M1";
                break;
            case 1:
                name = "M2";
                break;
            case 2:
                name = "M3";
                break;
            case 3:
                name = "M4";
                break;
            case 4:
                name = "M5";
                break;
            case 5:
                name = "T1";
                break;
            case 6:
                name = "T2";
                break;
            case 7:
                name = "T3";
                break;
            case 8:
                name = "T4";
                break;
            case 9:
                name = "T5";
                break;
            case 10:
                name = "N1";
                break;
            case 11:
                name = "N2";
                break;
            case 12:
                name = "N3";
                break;
            case 13:
                name = "N4";
                break;
            case 14:
                name = "N5";
                break;
            default:
                name = "";
                break;
        }
        return name;
    }

    @Override
    public List<Sala> buscarListaSalas(List<dao.entity.Sala> lis) {
        List<Sala> a = new ArrayList<>();
        for (dao.entity.Sala s : lis) {
            switch (s.getTipo()) {
                case "LAB_MECANICA":
                    LaboratorioMecanica lm = new LaboratorioMecanica(s.getIdUtfpr());
                    lm.setCategoria(s.getCategoria());
                    lm.setM_id(s.getCod());
                    lm.setId(s.getCod());
                    a.add(lm);
                    break;
                case "TEORICA":
                    Teorica t = new Teorica(s.getIdUtfpr());
                    t.setCategoria(s.getCategoria());
                    t.setM_Id(s.getCod());
                    t.setId(s.getCod());
                    a.add(t);
                    break;
                case "LAB_INFORMATICA":
                    LaboratorioInformatica lr = new LaboratorioInformatica(s.getIdUtfpr());
                    lr.setCategoria(s.getCategoria());
                    lr.setM_id(s.getCod());
                    lr.setId(s.getCod());
                    a.add(lr);
                    break;
                case "LAB_QUIMICA":
                    LaboratorioQuimica lq = new LaboratorioQuimica(s.getIdUtfpr());
                    lq.setCategoria(s.getCategoria());
                    lq.setM_id(s.getCod());
                    lq.setId(s.getCod());
                    a.add(lq);
                    break;
            }
        }
        return a;
    }

    @Override
    public List<Pessoa> listarPessoas(List<dao.entity.Pessoa> lis) {
        List<Pessoa> lp = new ArrayList<>();
        for (dao.entity.Pessoa d : lis) {
            switch (d.getTipo()) {
                case "RESPONSAVEL_GERAL":
                    ResponsavelGeral rg = new ResponsavelGeral();
                    rg.setId(d.getCod());
                    rg.setM_nome(d.getNome());
                    rg.setM_inicio(d.getDataCadastro());
                    lp.add(rg);
                    break;
                case "RESPONSAVEL_SETOR":
                    ResponsavelSetor rs = new ResponsavelSetor(d.getCategoria());
                    rs.setId(d.getCod());
                    rs.setM_nome(d.getNome());
                    rs.setM_inicio(d.getDataCadastro());
                    lp.add(rs);
                    break;
                case "PROFESSOR":
                    Professor p = new Professor();
                    p.setId(d.getCod());
                    p.setM_nome(d.getNome());
                    p.setM_inicio(d.getDataCadastro());
                    lp.add(p);
                    break;
            }
        }
        return lp;
    }

    @Override
    public boolean redirecionarSala() {
        return true;
    }

    @Override
    public boolean redirecionarReserva() {
        return false;
    }

    @Override
    public boolean redirecionarPessoa() {
        return true;
    }

    @Override
    public boolean redirecionarDecoradores() {
        return true;
    }

    @Override
    public boolean redirecionarDecorador() {
        return true;
    }

    @Override
    public boolean deletarDecorador() {
        return true;
    }

    @Override
    public boolean editarDecorador() {
        return true;
    }

    @Override
    public boolean redirecionarMinhasReservas() {
        return false;
    }

    @Override
    public boolean gravarEdicao() {
        return true;
    }
}
