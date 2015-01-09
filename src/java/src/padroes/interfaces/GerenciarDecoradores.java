/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.padroes.interfaces;

import dao.entity.Decorador;
import java.util.List;
import src.padroes.Decorator;

/**
 *
 * @author giovanna
 */
public interface GerenciarDecoradores {
    public boolean redirecionarDecoradores();
    public List<Decorator> listarDecoradores(List<Decorador> d);
    public List<Decorator> listarDecoradoresSala(List<Decorador> d);
    public List<Decorator> listarDecoradoresSalaReserva(List<Decorador> d);
    public boolean redirecionarDecorador ();
    public Decorador criarDecorador (String idUtfpr, String tipo, dao.entity.Sala s);
    public boolean deletarDecorador ();
    public boolean editarDecorador ();
}
