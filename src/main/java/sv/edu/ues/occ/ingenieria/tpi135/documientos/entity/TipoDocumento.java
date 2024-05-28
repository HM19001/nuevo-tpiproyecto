/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi135.documientos.entity;

import jakarta.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author alexo
 */
@Entity
@Table(name = "tipo_documento", catalog = "documentostpi135", schema = "public")
@NamedQueries({
    @NamedQuery(name = "TipoDocumento.findAll", query = "SELECT t FROM TipoDocumento t"),
    @NamedQuery(name = "TipoDocumento.findByIdTipoDocumento", query = "SELECT t FROM TipoDocumento t WHERE t.idTipoDocumento = :idTipoDocumento"),
    @NamedQuery(name = "TipoDocumento.findByNombre", query = "SELECT t FROM TipoDocumento t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoDocumento.findByActivo", query = "SELECT t FROM TipoDocumento t WHERE t.activo = :activo"),
    @NamedQuery(name = "TipoDocumento.findByObservaciones", query = "SELECT t FROM TipoDocumento t WHERE t.observaciones = :observaciones")})
public class TipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_documento", nullable = false)
    private Integer idTipoDocumento;
    @Column(name = "nombre", length = 2147483647)
    private String nombre;
    @Column(name = "activo")
    private Boolean activo;
    @Column(name = "observaciones", length = 2147483647)
    private String observaciones;
    @OneToMany(mappedBy = "idTipoDocumento")
    private List<Atributo> atributoList;
    @OneToMany(mappedBy = "idTipoDocumento")
    private List<Taxonomia> taxonomiaList;

    public TipoDocumento() {
    }

    public TipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @JsonbTransient
    public List<Atributo> getAtributoList() {
        return atributoList;
    }

    public void setAtributoList(List<Atributo> atributoList) {
        this.atributoList = atributoList;
    }

    @JsonbTransient
    public List<Taxonomia> getTaxonomiaList() {
        return taxonomiaList;
    }

    public void setTaxonomiaList(List<Taxonomia> taxonomiaList) {
        this.taxonomiaList = taxonomiaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDocumento != null ? idTipoDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) object;
        if ((this.idTipoDocumento == null && other.idTipoDocumento != null) || (this.idTipoDocumento != null && !this.idTipoDocumento.equals(other.idTipoDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi135.mapeodocumentos.entity.TipoDocumento[ idTipoDocumento=" + idTipoDocumento + " ]";
    }

}
