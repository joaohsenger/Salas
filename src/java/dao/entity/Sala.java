/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Marcos
 */
@Entity
@Table(schema = "utfpr", name = "sala",
        uniqueConstraints=@UniqueConstraint(columnNames={"id_utfpr"}))
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sala.findAll", query = "SELECT s FROM Sala s"),
    @NamedQuery(name = "Sala.findByCod", query = "SELECT s FROM Sala s WHERE s.cod = :cod"),
    @NamedQuery(name = "Sala.findByIdSala", query = "SELECT s FROM Sala s WHERE s.idSala = :idSala"),
    @NamedQuery(name = "Sala.findByTipo", query = "SELECT s FROM Sala s WHERE s.tipo = :tipo")})
public class Sala implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod")
    private int cod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "id_sala")
    private String idSala;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "id_utfpr")
    private String idUtfpr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "categoria")
    private String categoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salaCod")
    private List<Decorador> decoradorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salaCod")
    private List<Reserva> reservaList;

    public Sala() {
    }

    public Sala(int cod) {
        this.cod = cod;
    }

    public Sala(int cod, String idSala, String idUtfpr, String tipo, List<Decorador> decoradorList, List<Reserva> reservaList, String categoria) {
        this.cod = cod;
        this.idSala = idSala;
        this.idUtfpr = idUtfpr;
        this.tipo = tipo;
        this.decoradorList = decoradorList;
        this.reservaList = reservaList;
        this.categoria = categoria;
    }  

    public String getIdUtfpr() {
        return idUtfpr;
    }

    public void setIdUtfpr(String idUtfpr) {
        this.idUtfpr = idUtfpr;
    }        

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getIdSala() {
        return idSala;
    }

    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<Decorador> getDecoradorList() {
        return decoradorList;
    }

    public void setDecoradorList(List<Decorador> decoradorList) {
        this.decoradorList = decoradorList;
    }

    @XmlTransient
    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        this.reservaList = reservaList;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }   
    
}
