/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.beans;

import dao.entity.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import src.enumeracoes.TipoPessoa;
import src.modelo.Pessoa;
import src.padroes.Proxy;
import src.padroes.interfaces.concreto.GeralProxy;
import src.padroes.interfaces.concreto.ProfessorProxy;
import src.padroes.interfaces.concreto.SetorProxy;
import web.beans.utils.Mensagens;
import web.beans.utils.Redirecionador;

/**
 *
 * @author Marcos
 */
@ManagedBean(name = "usuarioControlador")
@SessionScoped
public class UsuarioControlador {

    private String usuario;
    private String senha;
    private String descricao;
    private String nome;
    private Date data;
    private int codigo;
    private int codUsu;
    private String categoria = "semCategoria";

    private dao.entity.Usuario u;
    private Proxy p;
    private Redirecionador r;
    private List<Pessoa> lPessoas;
    private List<dao.entity.Pessoa> lPessoasDAO;
    private dao.entity.Pessoa pessoa;
    @EJB
    private dao.teste.UsuarioFacade ejbFacadeUsuario;
    @EJB
    private dao.teste.PessoaFacade ejbFacadePessoa;

    public UsuarioControlador() {
        r = new Redirecionador();
        p = new Proxy();
        pessoa = new dao.entity.Pessoa();
        lPessoas = new ArrayList<>();
    }

    public void deletarPessoa(Pessoa pe) {
        p = getProxy();
        if (p.deletarPessoa(pe)) {
            dao.entity.Pessoa pDao = new dao.entity.Pessoa();
            pDao.setCod(pe.getId());
            pDao.setDataCadastro(pe.getM_inicio());
            pDao.setNome(pe.getM_nome());
            pDao.setTipo(TipoPessoa.gambi2(pe.getDescricao()));
            dao.entity.Usuario u = ejbFacadeUsuario.getUsuarioP(pe.getId());
            ejbFacadeUsuario.remove(u);
            ejbFacadePessoa.remove(pDao);
        } else {
            switch (p.getUsuario().getTipo()) {
                case 1:
                    new Mensagens().addErrorMessage("Você não tem permissão para deletar pessoas.");
                    break;
                case 2:
                    new Mensagens().addErrorMessage("Você não tem permissão para deletar pessoas.");
                    break;
                case 3:
                    new Mensagens().addErrorMessage("Pessoa: " + pe.getM_nome() + " erro ao deletar");
            }
        }
    }

    private void colocaPessoaNaSessao(Usuario uu) {
        try {
            pessoa = ejbFacadePessoa.find(uu.getUsuarioCod());
            pessoa.setCod(uu.getCod());
            pessoa.setTipo(pessoa.getTipo().toLowerCase());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            HttpSession session = (HttpSession) ec.getSession(false);
            session.setAttribute("pessoa", pessoa);
        } catch (Exception e) {
//            r.logar();
        }

    }

    public void logar() {
        try {
            u = ejbFacadeUsuario.login(usuario, senha);
        } catch (Exception e) {
            u = null;
        }
        try {
            if (u != null) {
                p.setUsuario(u);
                colocaPessoaNaSessao(u);
                switch (u.getTipo()) {
                    case 1:
                        ProfessorProxy px = new ProfessorProxy();
                        p.setpProxy(px);
                        break;
                    case 2:
                        SetorProxy sp = new SetorProxy();
                        p.setsProxy(sp);
                        break;
                    case 3:
                        GeralProxy gp = new GeralProxy();
                        p.setgProxy(gp);
                        break;
                }
                FacesContext fc = FacesContext.getCurrentInstance();
                ExternalContext ec = fc.getExternalContext();
                HttpSession session = (HttpSession) ec.getSession(false);
                session.setAttribute("proxy", p);
                usuario = "";
                senha = "";
                descricao = "";
                nome = "";
                r.logado(p);
            } else {
                r.logar();
            }
        } catch (Exception e) {
            r.logar();
        }

    }

    public void deslogar() {
        //Contexto da Aplicação  
        FacesContext conext = FacesContext.getCurrentInstance();
        //Verifica a sessao e a grava na variavel  
        HttpSession session = (HttpSession) conext.getExternalContext().getSession(false);
        //Fecha/Destroi sessao  
        session.invalidate();
        r.logar();
    }

    public boolean verificalogado() {

        Object o = ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("proxy");

        if (o == null) {
            return false;
        } else {
            return true;
        }

    }

    private Proxy getProxy() {
        if (verificalogado()) {
            return (Proxy) ((HttpSession) FacesContext
                    .getCurrentInstance().getExternalContext()
                    .getSession(true)).getAttribute("proxy");
        } else {
            r.logar();
            return null;
        }
    }

    private List<Pessoa> lPessoasBanco() {
        lPessoasDAO = ejbFacadePessoa.findAll();
        if (lPessoasDAO != null) {
            return getProxy().listarPessoas(lPessoasDAO);
        } else {
            return new ArrayList<>();
        }
    }

    public void novoCadastro() {
        dao.entity.Pessoa pDao;
        if (!"RESPONSAVEL_SETOR".equals(this.descricao)) {
            this.categoria = "semCategoria";
        }
        if (!"semCategoria".equals(this.categoria)) {
            pDao = getProxy().criarPessoa(nome, descricao, categoria);
        } else {
            pDao = getProxy().criarPessoa(nome, descricao);
        }
        pDao.setCategoria(categoria);
        Usuario u = new Usuario();
        try {
            ejbFacadePessoa.create(pDao);
            u.setLogin(usuario);
            u.setSenha(senha);
            u.setUsuarioCod(pDao.getCod());
            u.setTipo(TipoPessoa.gambi(pDao.getTipo()));
            ejbFacadeUsuario.create(u);
            new Redirecionador().verUsuarios();
        } catch (Exception e) {
            new Redirecionador().erro("Erro no cadastro da pessoa.");
        }
    }

    public void editarPessoa(Pessoa pe) {
        p = getProxy();
        if (p.editarPessoa()) {
            this.descricao = TipoPessoa.gambi2(pe.getDescricao());
            this.nome = pe.getM_nome();
            if (!"semCategoria".equals(pe.getCategoria())) {
                this.categoria = pe.getCategoria();
            }
            Usuario u = ejbFacadeUsuario.getUsuarioP(pe.getId());
            this.senha = u.getSenha();
            this.usuario = u.getLogin();
            this.data = pe.getM_inicio();
            this.codigo = pe.getId();
            this.codUsu = u.getCod();
            new Redirecionador().editarPessoa();
        } else {
            new Mensagens().addErrorMessage("Você não tem permissão para editar pessoas.");
        }
    }

    public void editarCadastro() {
        dao.entity.Pessoa pDao;
         if (!"semCategoria".equals(this.categoria)) {
            pDao = getProxy().criarPessoa(nome, descricao, categoria);
        } else {
            pDao = getProxy().criarPessoa(nome, descricao);
        }
        pDao.setDataCadastro(data);
        pDao.setCod(this.codigo);
        Usuario u = new Usuario();
        try {
            ejbFacadePessoa.edit(pDao);
            u.setLogin(usuario);
            u.setSenha(senha);
            u.setCod(this.codUsu);
            u.setUsuarioCod(pDao.getCod());
            u.setTipo(TipoPessoa.gambi(pDao.getTipo()));
            ejbFacadeUsuario.edit(u);
            new Redirecionador().verUsuarios();
        } catch (Exception e) {
            new Redirecionador().erro("Erro na edição da pessoa.\n");
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Pessoa> getlPessoas() {
        return lPessoasBanco();
    }

    public void setlPessoas(List<Pessoa> lPessoas) {
        this.lPessoas = lPessoas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
