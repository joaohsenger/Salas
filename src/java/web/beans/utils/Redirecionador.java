/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.beans.utils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import src.padroes.Proxy;

/**
 *
 * @author Marcos
 */
@ManagedBean(name = "redirecionador")
@RequestScoped
public class Redirecionador {

    private String caminho = "/salateste/utfpr/";
    private String erro;

    public void homeResponsavelGeral() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "inicioGeral.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(Redirecionador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void escolherAulas() {
        if (verificalogado()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "escolherAulas.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(Redirecionador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            logar();
        }
    }

    public void editarPessoa() {
        if (verificalogado()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "editarUsuario.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(Redirecionador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            logar();
        }
    }

    public void editarDecorador() {
        if (verificalogado()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "editarDecorador.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(Redirecionador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            logar();
        }
    }

    public void verReservasSetor() {
        if (verificalogado()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "reservasSetor.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(Redirecionador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            logar();
        }
    }

    public void editarSala() {
        if (verificalogado()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "editarSala.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(Redirecionador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            logar();
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

    public void home() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "home.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(Redirecionador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void logar() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(Redirecionador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void logado(Proxy p) {
        if (verificalogado()) {
            try {
                switch(p.getUsuario().getTipo()) {
                    case 1:
                        FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "inicio.xhtml");
                        break;
                    case 2:
                        FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "inicioSetor.xhtml");
                        break;
                    case 3:
                        FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "inicioGeral.xhtml");
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(Redirecionador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            logar();
        }

    }

    public void logado() {
        Proxy p = (Proxy) ((HttpSession) FacesContext
                .getCurrentInstance().getExternalContext()
                .getSession(true)).getAttribute("proxy");
        logado(p);
    }

    public void novaSala() {
        if (verificalogado()) {
            Proxy p = (Proxy) ((HttpSession) FacesContext
                    .getCurrentInstance().getExternalContext()
                    .getSession(true)).getAttribute("proxy");
            if (p.redirecionarSala()) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "novaSala.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(Redirecionador.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                new Redirecionador().erro("Você não tem permissão para adicionar uma sala.");
            }
        } else {
            logar();
        }
    }

    public void novoDecorador() {
        if (verificalogado()) {
            Proxy p = (Proxy) ((HttpSession) FacesContext
                    .getCurrentInstance().getExternalContext()
                    .getSession(true)).getAttribute("proxy");
            if (p.redirecionarDecorador()) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "novoDecorador.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(Redirecionador.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                new Redirecionador().erro("Voce nao tem permissao para cadastrar decoradores.");
            }
        } else {
            logar();
        }
    }

    public void novaReserva() {
//        Proxy p = (Proxy) ((HttpSession) FacesContext
//                .getCurrentInstance().getExternalContext()
//                .getSession(true)).getAttribute("proxy");
//        if (p.redirecionarSala()) {
//            if (verificalogado()) {
//                try {
//                    FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "novaSala.xhtml");
//                } catch (IOException ex) {
//                    Logger.getLogger(Redirecionador.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else {
//                logar();
//            }
//        } else {
//            new Redirecionador().erro("Voce nao tem permissao para cadastrar salas.");
//        }
    }

    public void novoUsuario() {
        if (verificalogado()) {
            Proxy p = (Proxy) ((HttpSession) FacesContext
                    .getCurrentInstance().getExternalContext()
                    .getSession(true)).getAttribute("proxy");
            if (p.redirecionarPessoa()) {

                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "novoUsuario.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(Redirecionador.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                new Mensagens().addErrorMessage("Você não tem permissão para adicionar uma pessoa.");
            }
        } else {
            logar();
        }
    }

    public void verSalas() {
        if (verificalogado()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "salas.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(Redirecionador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            logar();
        }
    }

    public void verListHorariosSalas() {
        if (verificalogado()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "escolherAulas.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(Redirecionador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            logar();
        }
    }

    public void verUsuarios() {
        if (verificalogado()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "usuarios.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(Redirecionador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            logar();
        }
    }

    public void verMinhasReservas() {
        if (verificalogado()) {
            Proxy p = (Proxy) ((HttpSession) FacesContext
                    .getCurrentInstance().getExternalContext()
                    .getSession(true)).getAttribute("proxy");
            if (p.redirecionarMinhasReservas()) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "minhasReservas.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(Redirecionador.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                new Redirecionador().erro("Você não possui reservas.");
            }
        } else {
            logar();
        }
    }

    public void verReservas() {
        if (verificalogado()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "reservas.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(Redirecionador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            logar();
        }
    }

    public void verDecoradores() {
        if (verificalogado()) {
            Proxy p = (Proxy) ((HttpSession) FacesContext
                    .getCurrentInstance().getExternalContext()
                    .getSession(true)).getAttribute("proxy");
            if (p.redirecionarDecoradores()) {

                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "decoradores.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(Redirecionador.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                new Mensagens().addErrorMessage("Você não pode acessar os decoradores.");
            }
        } else {
            logar();
        }
    }

    public void erro(String erro) {
        new Mensagens().addErrorMessage(erro);
    }
    
    public void sucesso(String erro) {
        new Mensagens().addSuccessMessage(erro);
    }

    public String getError() {
        return erro;
    }

    public void setError(String erro) {
        this.erro = erro;
    }

    public void paginaSalas() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "salas.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(Redirecionador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
