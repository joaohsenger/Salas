/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marcos
 */
@Entity
@Table( schema = "utfpr", name = "decorador",
        uniqueConstraints=@UniqueConstraint(columnNames={"id_utfpr"}))
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Decorador.findAll", query = "SELECT d FROM Decorador d"),
    @NamedQuery(name = "Decorador.findByCod", query = "SELECT d FROM Decorador d WHERE d.cod = :cod"),
    @NamedQuery(name = "Decorador.findByTipo", query = "SELECT d FROM Decorador d WHERE d.tipo = :tipo")})
public class Decorador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod")
    private Integer cod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "id_utfpr")
    private String idUtfpr;
    @JoinColumn(name = "sala_cod", referencedColumnName = "cod")
    @ManyToOne(optional = false)
    private Sala salaCod;

    public Decorador() {
    }

    public Decorador(Integer cod) {
        this.cod = cod;
    }

    public String getIdUtfpr() {
        return idUtfpr;
    }

    public void setIdUtfpr(String idUtfpr) {
        this.idUtfpr = idUtfpr;
    }        

    public Decorador(Integer cod, String tipo) {
        this.cod = cod;
        this.tipo = tipo;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Sala getSalaCod() {
        return salaCod;
    }

    public void setSalaCod(Sala salaCod) {
        this.salaCod = salaCod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cod != null ? cod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Decorador)) {
            return false;
        }
        Decorador other = (Decorador) object;
        if ((this.cod == null && other.cod != null) || (this.cod != null && !this.cod.equals(other.cod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.entity.Decorador[ cod=" + cod + " ]";
    }
    
}
