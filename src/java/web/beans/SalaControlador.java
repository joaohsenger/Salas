/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.beans;

import dao.entity.Pessoa;
import dao.entity.Sala;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import src.enumeracoes.TipoSala;
import src.padroes.Proxy;
import web.beans.utils.Mensagens;
import web.beans.utils.Redirecionador;

/**
 *
 * @author Marcos
 */
@ManagedBean(name = "salaControlador")
@SessionScoped
public class SalaControlador {

    @EJB
    private dao.teste.SalaFacade ejbFacadeSala;

    private List<Sala> listSalaDAO;
    private List<src.modelo.Sala> listSalaConc;
    private Proxy p;
    private String salaIdentificacao;
    private String descricao;
    private String identificacao; // utfpr
    private String categoria; // categoria da sala
    private int codigoSala;

    public SalaControlador() {
        try {
            listSalaDAO = ejbFacadeSala.findAll();
        } catch (Exception e) {
            listSalaDAO = null;
        }
        listSalaConc = new ArrayList<>();
        p = (Proxy) ((HttpSession) FacesContext
                .getCurrentInstance().getExternalContext()
                .getSession(true)).getAttribute("proxy");
    }

    public void criarSala() {
        Sala s = getProxy().criarSala(identificacao, descricao, categoria);
        try {
            ejbFacadeSala.create(s);
            new Redirecionador().verSalas();
        } catch (Exception e) {
            new Redirecionador().erro("Erro no cadastro da sala.");
        }
    }

    private Proxy getProxy() {
        return (Proxy) ((HttpSession) FacesContext
                .getCurrentInstance().getExternalContext()
                .getSession(true)).getAttribute("proxy");
    }

    public void visualizarSala(src.modelo.Sala s) {
        if (getProxy().redirecionarReserva()) {
            colocaSalaSessao(s);
            new Redirecionador().verListHorariosSalas();
        } else {
            new Redirecionador().erro("Você não pode cadastrar reservas.");
        }
        
    }

    public void colocaSalaSessao(src.modelo.Sala s) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(false);
        session.setAttribute("salatemp", s);
    }

    public void colocaSalaDAOSessao(Sala s) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(false);
        session.setAttribute("salaDecorador", s);
    }

    public void editarSala(src.modelo.Sala s) {
        p = getProxy();
        if (p.editarSala(s.getCategoria(), ((Pessoa) ((HttpSession) FacesContext
                .getCurrentInstance().getExternalContext()
                .getSession(true)).getAttribute("pessoa")).getCategoria())) {
            this.codigoSala = s.getId();
            this.descricao = TipoSala.gambi2(s.getIdentificacao());
            this.identificacao = s.getIdUtfpr();
            this.categoria = s.getCategoria();
            Sala sDao = new Sala();
            sDao.setCod(codigoSala);
            sDao.setIdUtfpr(identificacao);
            sDao.setTipo(descricao);
            sDao.setIdSala(s.getIdentificacao());
            sDao.setCategoria(categoria);
            colocaSalaDAOSessao(sDao);
            new Redirecionador().editarSala();
        } else {
            switch (p.getUsuario().getTipo()) {
                case 1: // professor
                    new Redirecionador().erro("Você não tem permissão para editar salas.");
                    break;
                case 2: // setor
                    new Redirecionador().erro("Esta sala não pertence ao teu setor.");
                    break;
            }

        }
    }

    public void gravarEdicao() {
        p = getProxy();
        if (p.gravarEdicao()) {
            Sala sDao = (Sala) ((HttpSession) FacesContext
                    .getCurrentInstance().getExternalContext()
                    .getSession(true)).getAttribute("salaDecorador");
            sDao.setTipo(this.descricao);
            sDao.setIdUtfpr(this.identificacao);
            sDao.setIdSala(TipoSala.gambi1(this.descricao));
            if (p.getUsuario().getTipo() != 2) { // nao permite ao responsável do setor alterar a categoria da sala
                sDao.setCategoria(this.categoria);
            }
            ejbFacadeSala.edit(sDao);
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            HttpSession session = (HttpSession) ec.getSession(false);
            session.setAttribute("salaDecorador", null);
            new Redirecionador().verSalas();
        } else {
            new Redirecionador().erro("Erro para editar sala.");
        }
    }

    public void deletarSala(src.modelo.Sala s) {
        p = getProxy();
        if (p.deletarSala(s)) {
            dao.entity.Sala sa = new dao.entity.Sala();
            sa.setCod(s.getId());
            sa.setIdSala(s.getIdentificacao());
            sa.setIdUtfpr(s.getIdUtfpr());
            switch (s.getIdentificacao()) {
                case "Laboratório de Informática":
                    sa.setTipo("LAB_INFORMATICA");
                    break;
                case "Laboratório de Química":
                    sa.setTipo("LAB_QUIMICA");
                    break;
                case "Teórica":
                    sa.setTipo("TEORICA");
                    break;
                case "Laboratório de Mecânica":
                    sa.setTipo("LAB_MECANICA");
                    break;
            }
            ejbFacadeSala.remove(sa);
        } else {
            switch (p.getUsuario().getTipo()) {
                case 1:
                    new Mensagens().addErrorMessage("Você não tem permissão para deletar salas.");
                    break;
                case 2:
                    new Mensagens().addErrorMessage("Você não tem permissão para deletar salas.");
                    break;
                case 3:
                    new Mensagens().addErrorMessage("Sala: " + s.getIdUtfpr() + " erro ao deletar");
            }
        }
    }

    public boolean verificalogado() {

        Object o = ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("proxy");

        if (o == null) {
            return false;
        } else {
            return true;
        }

    }

    public List<src.modelo.Sala> getListSalaConc() {
        try {
            return p.buscarListaSalas(ejbFacadeSala.findAll());
        } catch (Exception e) {
            return null;
        }

    }

    public void setListSalaConc(List<src.modelo.Sala> listSalaConc) {
        this.listSalaConc = listSalaConc;
    }

    public String getSalaIdentificacao() {
        salaIdentificacao = "Sala ";
        salaIdentificacao += ((src.modelo.Sala) ((HttpSession) FacesContext
                .getCurrentInstance().getExternalContext()
                .getSession(true)).getAttribute("salatemp")).getIdentificacao();
        salaIdentificacao += " (" + ((src.modelo.Sala) ((HttpSession) FacesContext
                .getCurrentInstance().getExternalContext()
                .getSession(true)).getAttribute("salatemp")).getIdUtfpr() + ") ";
        return salaIdentificacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public int getCodigoSala() {
        return codigoSala;
    }

    public void setCodigoSala(int codigoSala) {
        this.codigoSala = codigoSala;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
