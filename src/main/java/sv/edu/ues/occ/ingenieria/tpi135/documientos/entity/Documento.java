/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi135.documientos.entity;

import jakarta.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import java.util.Date;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author alexo
 */
@Entity
@Table(name = "documento", catalog = "documentostpi135", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d"),
    @NamedQuery(name = "Documento.findByIdDocumento", query = "SELECT d FROM Documento d WHERE d.idDocumento = :idDocumento"),
    @NamedQuery(name = "Documento.findByNombre", query = "SELECT d FROM Documento d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "Documento.findByReferenciaExterna", query = "SELECT d FROM Documento d WHERE d.referenciaExterna = :referenciaExterna"),
    @NamedQuery(name = "Documento.findByUbicacionFisica", query = "SELECT d FROM Documento d WHERE d.ubicacionFisica = :ubicacionFisica"),
    @NamedQuery(name = "Documento.findByUrl", query = "SELECT d FROM Documento d WHERE d.url = :url"),
    @NamedQuery(name = "Documento.findByFechaCreacion", query = "SELECT d FROM Documento d WHERE d.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Documento.findByCreadoPor", query = "SELECT d FROM Documento d WHERE d.creadoPor = :creadoPor"),
    @NamedQuery(name = "Documento.findByComentarios", query = "SELECT d FROM Documento d WHERE d.comentarios = :comentarios")})
public class Documento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_documento", nullable = false)
    private Long idDocumento;
    @Column(name = "nombre", length = 2147483647)
    private String nombre;
    @Column(name = "referencia_externa", length = 2147483647)
    private String referenciaExterna;
    @Column(name = "ubicacion_fisica", length = 2147483647)
    private String ubicacionFisica;
    @Column(name = "url", length = 2147483647)
    private String url;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "creado_por", length = 155)
    private String creadoPor;
    @Column(name = "comentarios", length = 2147483647)
    private String comentarios;
    @OneToMany(mappedBy = "idDocumento")
    private List<Metadato> metadatoList;
    @OneToMany(mappedBy = "idDocumento")
    private List<Taxonomia> taxonomiaList;

    public Documento() {
    }

    public Documento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getReferenciaExterna() {
        return referenciaExterna;
    }

    public void setReferenciaExterna(String referenciaExterna) {
        this.referenciaExterna = referenciaExterna;
    }

    public String getUbicacionFisica() {
        return ubicacionFisica;
    }

    public void setUbicacionFisica(String ubicacionFisica) {
        this.ubicacionFisica = ubicacionFisica;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
@JsonbTransient
    public List<Metadato> getMetadatoList() {
        return metadatoList;
    }

    public void setMetadatoList(List<Metadato> metadatoList) {
        this.metadatoList = metadatoList;
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
        hash += (idDocumento != null ? idDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) object;
        if ((this.idDocumento == null && other.idDocumento != null) || (this.idDocumento != null && !this.idDocumento.equals(other.idDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi135.mapeodocumentos.entity.Documento[ idDocumento=" + idDocumento + " ]";
    }
    
}
