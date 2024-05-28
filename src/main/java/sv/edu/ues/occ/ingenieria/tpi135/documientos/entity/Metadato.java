/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi135.documientos.entity;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author alexo
 */
@Entity
@Table(name = "metadato", catalog = "documentostpi135", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Metadato.findAll", query = "SELECT m FROM Metadato m"),
    @NamedQuery(name = "Metadato.findByIdMetadata", query = "SELECT m FROM Metadato m WHERE m.idMetadata = :idMetadata"),
    @NamedQuery(name = "Metadato.findByValor", query = "SELECT m FROM Metadato m WHERE m.valor = :valor"),
    @NamedQuery(name = "Metadato.findByFechaCreacion", query = "SELECT m FROM Metadato m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Metadato.findByComentarios", query = "SELECT m FROM Metadato m WHERE m.comentarios = :comentarios")})
public class Metadato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_metadata", nullable = false)
    private Long idMetadata;
    @Column(name = "valor", length = 2147483647)
    private String valor;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "comentarios", length = 2147483647)
    private String comentarios;
    @JoinColumn(name = "id_atributo", referencedColumnName = "id_atributo")
    @ManyToOne
    private Atributo idAtributo;
    @JoinColumn(name = "id_documento", referencedColumnName = "id_documento")
    @ManyToOne
    private Documento idDocumento;

    public Metadato() {
    }

    public Metadato(Long idMetadata) {
        this.idMetadata = idMetadata;
    }

    public Long getIdMetadata() {
        return idMetadata;
    }

    public void setIdMetadata(Long idMetadata) {
        this.idMetadata = idMetadata;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Atributo getIdAtributo() {
        return idAtributo;
    }

    public void setIdAtributo(Atributo idAtributo) {
        this.idAtributo = idAtributo;
    }

    public Documento getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Documento idDocumento) {
        this.idDocumento = idDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMetadata != null ? idMetadata.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Metadato)) {
            return false;
        }
        Metadato other = (Metadato) object;
        if ((this.idMetadata == null && other.idMetadata != null) || (this.idMetadata != null && !this.idMetadata.equals(other.idMetadata))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi135.mapeodocumentos.entity.Metadato[ idMetadata=" + idMetadata + " ]";
    }
    
}
