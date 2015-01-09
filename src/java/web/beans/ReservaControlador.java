/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.beans;

import dao.entity.Pessoa;
import dao.entity.Reserva;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import src.enumeracoes.IdAula;
import src.enumeracoes.TipoPessoa;
import src.modelo.Aula;
import src.modelo.Sala;
import src.modelo.concreto.Disponivel;
import src.modelo.concreto.LaboratorioInformatica;
import src.modelo.concreto.LaboratorioMecanica;
import src.modelo.concreto.LaboratorioQuimica;
import src.modelo.concreto.Ocupado;
import src.modelo.concreto.Professor;
import src.modelo.concreto.ResponsavelGeral;
import src.modelo.concreto.ResponsavelSetor;
import src.modelo.concreto.Teorica;
import src.padroes.Proxy;
import web.beans.utils.Mensagens;
import web.beans.utils.Redirecionador;

/**
 *
 * @author Marcos
 */
@ManagedBean(name = "reservaControlador")
@SessionScoped
public class ReservaControlador {

    private List<src.modelo.concreto.Reserva> listaDeReservas;
    private List<src.modelo.concreto.Reserva> listaTodasReservas;
    private List<src.modelo.concreto.Reserva> listaMinhasReservas;
    private List<src.modelo.concreto.Reserva> listaReservasSetor;
    private Proxy p;
    @EJB
    private dao.teste.ReservaFacade ejbFacadeReserva;
    @EJB
    private dao.teste.PessoaFacade ejbFacadePessoa;
    @EJB
    private dao.teste.SalaFacade ejbFacadeSala;
    private List<dao.entity.Reserva> listReservasDAO;
    private Reserva reDao;
    private int aula = 0;
    private List<Aula> aulas;
    private Date dataReserva = new Date();

    public ReservaControlador() {
        listaDeReservas = new ArrayList<>();
        p = new Proxy();
    }

    public void buscarData() {
        new Redirecionador().escolherAulas();
    }

    public void setDataReserva(Date dataReserva) {
        if (dataReserva.after(new Date())) {
            this.dataReserva = dataReserva;
        } else {
            this.dataReserva = new Date();
        }
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    private Proxy getProxy() {
        return (Proxy) ((HttpSession) FacesContext
                .getCurrentInstance().getExternalContext()
                .getSession(true)).getAttribute("proxy");
    }

    public void deletarReserva(src.modelo.concreto.Reserva r) {
        p = getProxy();
        if (p.deletarReserva(r)) {
            if (IdAula.dataAula(new Date(), r.getM_aula().getM_id(), r.getData())) {
                Reserva rDao = new Reserva();
                rDao.setAula(IdAula.converterInt(r.getM_aula().getM_id()));
                rDao.setCod(r.getCodigo());
                rDao.setData(r.getData());
                
                Pessoa p = new Pessoa();
                p.setCod(r.getPessoa().getId());
                p.setDataCadastro(r.getPessoa().getM_inicio());
                p.setNome(r.getPessoa().getM_nome());
                p.setTipo(TipoPessoa.gambi2(r.getPessoa().getDescricao()));
                rDao.setPessoaCod(p);
                
                dao.entity.Sala sa = new dao.entity.Sala();
                sa.setCod(r.getSala().getId());
                sa.setIdSala(r.getSala().getIdentificacao());
                sa.setIdUtfpr(r.getSala().getIdUtfpr());
                sa.setCategoria(r.getSala().getCategoria());
                switch (r.getSala().getIdentificacao()) {
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
                rDao.setSalaCod(sa);
                
                ejbFacadeReserva.remove(rDao);
            } else {
                new Redirecionador().erro("Esta aula não pode mais ser removida.");
            }
        } else {
            switch (p.getUsuario().getTipo()) {
                case 1:
                case 2:
                    new Redirecionador().erro("Reserva: " + r.getCodigo() + " erro ao deletar");
                    break;
                case 3:
                    new Redirecionador().erro("Você não tem permissão para deletar resservas.");
                    break;
            }
        }
    }

    public void listaReservas() {
        p = getProxy();
        switch (p.getUsuario().getTipo()) {
            case 1:
            case 2:
                listReservasDAO = ejbFacadeReserva.getReservas(p.getUsuario().getUsuarioCod(), new Date());
                if (listReservasDAO == null) {
                    listaDeReservas = null;
                } else {
                    listaDeReservas = p.listarReserva(p.getUsuario().getUsuarioCod(), listReservasDAO);
                }
                break;
            case 3:
                new Redirecionador().erro("Listar Reservas");
                break;
        }

    }

    public void listarTodasReservas() {
        p = getProxy();
        listReservasDAO = ejbFacadeReserva.getTodasReservas();
        if (listReservasDAO == null) {
            listaTodasReservas = null;
        } else {
            listaTodasReservas = p.listarTodasReserva(listReservasDAO);
        }
    }

    public void listarMinhasReservas() {
        p = getProxy();
        switch (p.getUsuario().getTipo()) {
            case 1:
            case 2:
                listReservasDAO = ejbFacadeReserva.getMinhasReservas(p.getUsuario().getUsuarioCod());
                if (listReservasDAO == null) {
                    listaMinhasReservas = null;
                } else {
                    listaMinhasReservas = p.listarMinhasReserva(p.getUsuario().getUsuarioCod(), listReservasDAO);
                }
                break;
            case 3:
                new Redirecionador().erro("Você não possui reservas.");
                break;
        }

    }

    public List<src.modelo.concreto.Reserva> getListaTodasReservas() {
        listarTodasReservas();
        return listaTodasReservas;
    }

    public List<src.modelo.concreto.Reserva> getListaMinhasReservas() {
        listarMinhasReservas();
        return listaMinhasReservas;
    }

    public void gravarNovaReserva(Aula aula) {
        p = getProxy();
        if (IdAula.dataAula(new Date(), aula.getM_id(), dataReserva)) {            
            src.modelo.concreto.Reserva r = new src.modelo.concreto.Reserva();            
            Pessoa pp = (Pessoa) ((HttpSession) FacesContext
                    .getCurrentInstance().getExternalContext()
                    .getSession(true)).getAttribute("pessoa");
            switch (p.getUsuario().getTipo()) {
                case 1:         
                    src.modelo.concreto.Professor prof = new Professor();
                    prof.setId(pp.getCod());
                    prof.setM_inicio(pp.getDataCadastro());
                    prof.setM_nome(pp.getNome());
                    r = p.criarReserva(prof, aula.getM_Sala());
                    break;
                case 2:      
                    src.modelo.concreto.ResponsavelSetor rs = new ResponsavelSetor();
                    rs.setId(pp.getCod());
                    rs.setM_inicio(pp.getDataCadastro());
                    rs.setM_nome(pp.getNome());
                    r = p.criarReserva(rs, aula.getM_Sala());
                    break;
                case 3:      
                    src.modelo.concreto.ResponsavelGeral rg = new ResponsavelGeral();
                    rg.setId(pp.getCod());
                    rg.setM_inicio(pp.getDataCadastro());
                    rg.setM_nome(pp.getNome());
                    r = p.criarReserva(rg, aula.getM_Sala());
                    break;
            }
            reDao = new Reserva();
            switch (r.getSala().getIdentificacao()) {
                case "Laboratório de Mecânica":
                    LaboratorioMecanica lm = (LaboratorioMecanica) r.getSala();
                    reDao.setSalaCod(ejbFacadeSala.getSalaCod(lm.getM_id()));
                    break;
                case "Teórica":
                    Teorica t = (Teorica) r.getSala();
                    reDao.setSalaCod(ejbFacadeSala.getSalaCod(t.getM_Id()));
                    break;
                case "Laboratório de Informática":
                    LaboratorioInformatica lr = (LaboratorioInformatica) r.getSala();
                    reDao.setSalaCod(ejbFacadeSala.getSalaCod(lr.getM_id()));
                    break;
                case "Laboratório de Química":
                    LaboratorioQuimica lq = (LaboratorioQuimica) r.getSala();
                    reDao.setSalaCod(ejbFacadeSala.getSalaCod(lq.getM_id()));
                    break;
            }
            reDao.setPessoaCod(ejbFacadePessoa.getPessoaCod(r.getPessoa().getId()));
            reDao.setAula(aula.convertAulaInteiro(aula.getM_id()));
            reDao.setData(this.dataReserva);
            ejbFacadeReserva.create(reDao);
            new Redirecionador().sucesso("Aula: " + aula.getM_id() + " gravada.");
        } else {
            new Redirecionador().erro("Você não pode cadastrar esta aula.");
        }
    }

    public List<src.modelo.concreto.Reserva> getListaDeReservas() {
        listaReservas();
        return listaDeReservas;
    }

    public void setListaDeReservas(List<src.modelo.concreto.Reserva> listaDeReservas) {
        this.listaDeReservas = listaDeReservas;
    }

    public int getAula() {
        return aula;
    }

    public void setAula(int aula) {
        this.aula = aula;
    }

    public void buscarAulas() {
        List<Reserva> res = new ArrayList<>();
        Sala sal = (Sala) ((HttpSession) FacesContext
                .getCurrentInstance().getExternalContext()
                .getSession(true)).getAttribute("salatemp");
        switch (sal.getIdentificacao()) {
            case "Laboratório de Mecânica":
                LaboratorioMecanica lm = (LaboratorioMecanica) sal;
                sal.setId(lm.getM_id());
                res = ejbFacadeReserva.getReservasAula(lm.getM_id(), this.dataReserva);
                break;
            case "Teórica":
                Teorica t = (Teorica) sal;
                sal.setId(t.getM_Id());
                res = ejbFacadeReserva.getReservasAula(t.getM_Id(), this.dataReserva);
                break;
            case "Laboratório de Informática":
                LaboratorioInformatica lr = (LaboratorioInformatica) sal;
                sal.setId(lr.getM_id());
                res = ejbFacadeReserva.getReservasAula(lr.getM_id(), this.dataReserva);
                break;
            case "Laboratório de Química":
                LaboratorioQuimica lq = (LaboratorioQuimica) sal;
                sal.setId(lq.getM_id());
                res = ejbFacadeReserva.getReservasAula(lq.getM_id(), this.dataReserva);
                break;
        }

//        res = ejbFacadeReserva.getReservasAula(sal.getId(), new Date());
        aulas = new ArrayList<>();
        int i = 0;
        for (Reserva rrr : res) {
            for (int j = i; j < rrr.getAula(); ++j, ++i) {
                Aula a = new Aula();
                a.setEstado(new Disponivel(a));
                a.setM_Sala(sal);
                a.setM_id(a.convertAulaString(j));
                a.setM_disponivel(true);

                aulas.add(a);
            }
            Aula au = new Aula();
            au.setEstado(new Ocupado(au));
            au.setM_Sala(sal);
            au.setM_disponivel(false);
            au.setM_id(au.convertAulaString(rrr.getAula()));
            ++i;
            aulas.add(au);
        }

        for (int j = i; j < 15; ++j, ++i) {
            Aula a = new Aula();
            a.setEstado(new Disponivel(a));
            a.setM_Sala(sal);
            a.setM_id(a.convertAulaString(j));
            a.setM_disponivel(true);
            aulas.add(a);
        }
    }

    public List<Aula> getAulas() {
        buscarAulas();
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    public List<src.modelo.concreto.Reserva> getListaReservasSetor() {
        listarReservasSetor();
        return listaReservasSetor;
    }

    public void setListaReservasSetor(List<src.modelo.concreto.Reserva> listaReservasSetor) {
        this.listaReservasSetor = listaReservasSetor;
    }

    public void listarReservasSetor() {
        p = getProxy();
        listReservasDAO = ejbFacadeReserva.getReservasSetor(((Pessoa) ((HttpSession) FacesContext
                .getCurrentInstance().getExternalContext()
                .getSession(true)).getAttribute("pessoa")).getCategoria(), new Date());
        if (listReservasDAO == null) {
            listaReservasSetor = null;
        } else {
            listaReservasSetor = p.listarReservaSetor(listReservasDAO);
        }
    }

}
