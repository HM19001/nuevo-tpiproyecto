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
@Table(name = "taxonomia", catalog = "documentostpi135", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Taxonomia.findTipoDocumentoByDocumento", query = "SELECT t.idTipoDocumento.idTipoDocumento FROM Taxonomia t WHERE t.idDocumento = :idDocumento"),
    @NamedQuery(name = "Taxonomia.findAll", query = "SELECT t FROM Taxonomia t"),
    @NamedQuery(name = "Taxonomia.findByIdTaxonomia", query = "SELECT t FROM Taxonomia t WHERE t.idTaxonomia = :idTaxonomia"),
    @NamedQuery(name = "Taxonomia.findByFechaCreacion", query = "SELECT t FROM Taxonomia t WHERE t.fechaCreacion = :fechaCreacion")})
public class Taxonomia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_taxonomia", nullable = false)
    private Long idTaxonomia;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @JoinColumn(name = "id_documento", referencedColumnName = "id_documento")
    @ManyToOne
    private Documento idDocumento;
    @JoinColumn(name = "id_tipo_documento", referencedColumnName = "id_tipo_documento")
    @ManyToOne
    private TipoDocumento idTipoDocumento;

    public Taxonomia() {
    }

    public Taxonomia(Long idTaxonomia) {
        this.idTaxonomia = idTaxonomia;
    }

    public Long getIdTaxonomia() {
        return idTaxonomia;
    }

    public void setIdTaxonomia(Long idTaxonomia) {
        this.idTaxonomia = idTaxonomia;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Documento getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Documento idDocumento) {
        this.idDocumento = idDocumento;
    }

    public TipoDocumento getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(TipoDocumento idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTaxonomia != null ? idTaxonomia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Taxonomia)) {
            return false;
        }
        Taxonomia other = (Taxonomia) object;
        if ((this.idTaxonomia == null && other.idTaxonomia != null) || (this.idTaxonomia != null && !this.idTaxonomia.equals(other.idTaxonomia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi135.mapeodocumentos.entity.Taxonomia[ idTaxonomia=" + idTaxonomia + " ]";
    }

}
