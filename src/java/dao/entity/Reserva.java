/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marcos
 */
@Entity
@Table(schema = "utfpr", name = "reserva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r"),
    @NamedQuery(name = "Reserva.findByCod", query = "SELECT r FROM Reserva r WHERE r.cod = :cod"),
    @NamedQuery(name = "Reserva.findByData", query = "SELECT r FROM Reserva r WHERE r.data = :data"),
    @NamedQuery(name = "Reserva.findByAula", query = "SELECT r FROM Reserva r WHERE r.aula = :aula")})
public class Reserva  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod")
    private Integer cod;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aula")
    private int aula;
    @JoinColumn(name = "sala_cod", referencedColumnName = "cod")
    @ManyToOne(optional = false)
    private Sala salaCod;
    @JoinColumn(name = "pessoa_cod", referencedColumnName = "cod")
    @ManyToOne(optional = false)
    private Pessoa pessoaCod;

    public Reserva() {
    }

    public Reserva(Integer cod) {
        this.cod = cod;
    }

    public Reserva(Integer cod, Date data, int aula) {
        this.cod = cod;
        this.data = data;
        this.aula = aula;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getAula() {
        return aula;
    }

    public void setAula(int aula) {
        this.aula = aula;
    }

    public Sala getSalaCod() {
        return salaCod;
    }

    public void setSalaCod(Sala salaCod) {
        this.salaCod = salaCod;
    }

    public Pessoa getPessoaCod() {
        return pessoaCod;
    }

    public void setPessoaCod(Pessoa pessoaCod) {
        this.pessoaCod = pessoaCod;
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
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.cod == null && other.cod != null) || (this.cod != null && !this.cod.equals(other.cod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.entity.Reserva[ cod=" + cod + " ]";
    }
    
}
