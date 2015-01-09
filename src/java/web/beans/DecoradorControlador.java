/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.beans;

import dao.entity.Decorador;
import dao.entity.Sala;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import src.enumeracoes.TipoDecorador;
import src.enumeracoes.TipoSala;
import src.modelo.concreto.Carteira;
import src.modelo.concreto.Computador;
import src.modelo.concreto.Monitor;
import src.modelo.concreto.Mouse;
import src.modelo.concreto.Projetor;
import src.padroes.Decorator;
import src.padroes.Proxy;
import web.beans.utils.Mensagens;
import web.beans.utils.Redirecionador;

/**
 *
 * @author giovanna
 */
@ManagedBean(name = "decoradorControlador")
@SessionScoped
public class DecoradorControlador {

    @EJB
    private dao.teste.DecoradorFacade ejbFacadeDecorador;
    private List<Decorator> listaDeDecoradores;
    private List<Decorador> listaDAODeDecoradores;
    private List<Decorator> listaDeDecoradoresSala;
    private List<Decorador> listaDAODeDecoradoresSala;
    private String idUtfpr;
    private String tipo;
    private dao.entity.Sala sala;
    private int codigo;
    private Decorador decorador;
    private Proxy p;

    public void editarDecorador() {
        p = getProxy();
        if (p.editarDecorador()) {
            decorador.setTipo(this.tipo);
            decorador.setIdUtfpr(this.idUtfpr);
            ejbFacadeDecorador.edit(this.decorador);
            new Redirecionador().editarSala();
        } else {
            new Redirecionador().erro("Você nao tem esse controle.");
        }
    }

    public void editarDecorador(Decorator d) {
        p = getProxy();
        if (p.editarDecorador()) {
            this.decorador = new Decorador();
            decorador.setCod(d.getM_id());
            decorador.setIdUtfpr(d.getIdUtfpr());
            src.modelo.Sala salaModel = null;
            switch (TipoDecorador.gambi2(d.getIdentificacao())) {
                case "CARTEIRA":
                    Carteira c = (Carteira) d;
                    salaModel = c.getM_sala();
                    break;
                case "MONITOR":
                    Monitor m = (Monitor) d;
                    salaModel = m.getM_sala();
                    break;
                case "MOUSE":
                    Mouse mo = (Mouse) d;
                    salaModel = mo.getM_sala();
                    break;
                case "PROJETOR":
                    Projetor p = (Projetor) d;
                    salaModel = p.getM_sala();
                    break;
                case "COMPUTADOR":
                    Computador co = (Computador) d;
                    salaModel = co.getM_sala();
                    break;
            }
            Sala sDao = new Sala();
            sDao.setCod(salaModel.getId());
            sDao.setIdUtfpr(salaModel.getIdUtfpr());
            sDao.setTipo(TipoSala.gambi2(salaModel.getIdentificacao()));
            sDao.setIdSala(d.getIdentificacao());
            decorador.setTipo(TipoDecorador.gambi2(d.getIdentificacao()));
            decorador.setSalaCod(sDao);
            this.tipo = TipoDecorador.gambi2(d.getIdentificacao());
            this.idUtfpr = d.getIdUtfpr();
            new Redirecionador().editarDecorador();
        } else {
            new Redirecionador().erro("Você não pode editar decoradores.");
        }
    }

    public List<Decorator> getListaDeDecoradores() {
        listarDecoradores();
        return listaDeDecoradores;
    }

    public List<Decorator> getListaDeDecoradoresSala(int codSala) {
        listarDecoradoresSala(codSala);
        return listaDeDecoradoresSala;
    }

    public void criarDecorador() {
        this.sala = (Sala) ((HttpSession) FacesContext
                .getCurrentInstance().getExternalContext()
                .getSession(true)).getAttribute("salaDecorador");
        Decorador d = getProxy().criarDecorador(idUtfpr, tipo, sala);
        try {
            ejbFacadeDecorador.create(d);
            new Redirecionador().editarSala();
        } catch (Exception e) {
            new Redirecionador().erro("Erro no cadastro de decoradores.");
        }
    }

    public void deletarDecorador(Decorator d) {
        p = getProxy();
        if (p.deletarDecorador()) {
            Decorador dDao = new Decorador();
            dDao.setCod(d.getM_id());
            src.modelo.Sala salaModel = null;
            switch (TipoDecorador.gambi2(d.getIdentificacao())) {
                case "CARTEIRA":
                    Carteira c = (Carteira) d;
                    salaModel = c.getM_sala();
                    break;
                case "MONITOR":
                    Monitor m = (Monitor) d;
                    salaModel = m.getM_sala();
                    break;
                case "MOUSE":
                    Mouse mo = (Mouse) d;
                    salaModel = mo.getM_sala();
                    break;
                case "PROJETOR":
                    Projetor p = (Projetor) d;
                    salaModel = p.getM_sala();
                    break;
                case "COMPUTADOR":
                    Computador co = (Computador) d;
                    salaModel = co.getM_sala();
                    break;
            }
            Sala sDao = new Sala();
            sDao.setCod(salaModel.getId());
            sDao.setIdUtfpr(salaModel.getIdUtfpr());
            sDao.setTipo(TipoSala.gambi2(salaModel.getIdentificacao()));
            sDao.setIdSala(d.getIdentificacao());
            dDao.setIdUtfpr(d.getIdUtfpr());
            dDao.setTipo(TipoDecorador.gambi2(d.getIdentificacao()));
            dDao.setSalaCod(sDao);
            ejbFacadeDecorador.remove(dDao);
        } else {
            new Redirecionador().erro("Você não pode deletar um decorador.");
        }
    }

    public void listarDecoradoresSala(int codSala) {
        p = getProxy();
        switch (p.getUsuario().getTipo()) {
            case 1:
                new Mensagens().addErrorMessage("Você não possui este controle.");
                break;
            case 2:                
            case 3:
                listaDAODeDecoradoresSala = ejbFacadeDecorador.decoradorSala(codSala);
                if (listaDAODeDecoradoresSala == null) {
                    listaDeDecoradoresSala = null;
                } else {
                    listaDeDecoradoresSala = p.listarDecoradoresSala(listaDAODeDecoradoresSala);
                }
                break;
        }
    }

    public List<Decorator> listarDecoradoresSalaReserva() {
        p = getProxy();
        return p.listarDecoradoresSalaReserva(ejbFacadeDecorador.decoradorSala(((src.modelo.Sala) ((HttpSession) FacesContext
                .getCurrentInstance().getExternalContext()
                .getSession(true)).getAttribute("salatemp")).getId()));
    }

    private Proxy getProxy() {
        return (Proxy) ((HttpSession) FacesContext
                .getCurrentInstance().getExternalContext()
                .getSession(true)).getAttribute("proxy");
    }

    public void listarDecoradores() {
        p = getProxy();
        switch (p.getUsuario().getTipo()) {
            case 1:
            case 2:
                new Mensagens().addErrorMessage("Você não possui este controle.");
                break;
            case 3:
                listaDAODeDecoradores = ejbFacadeDecorador.findAll();
                if (listaDAODeDecoradores == null) {
                    listaDeDecoradores = null;
                } else {
                    listaDeDecoradores = p.listarDecoradores(listaDAODeDecoradores);
                }
                break;
        }

    }

    public String getIdUtfpr() {
        return idUtfpr;
    }

    public String getTipo() {
        return tipo;
    }

    public dao.entity.Sala getSala() {
        return sala;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setIdUtfpr(String idUtfpr) {
        this.idUtfpr = idUtfpr;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setSala(dao.entity.Sala sala) {
        this.sala = sala;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

}
