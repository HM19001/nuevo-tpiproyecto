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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author alexo
 */
@Entity
@Table(name = "atributo", catalog = "documentostpi135", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Atributo.findTipoDocumentobyId", query = "SELECT a.idTipoDocumento.idTipoDocumento FROM Atributo a WHERE a.idAtributo = :idAtributo"),
    @NamedQuery(name = "Atributo.findAll", query = "SELECT a FROM Atributo a"),
    @NamedQuery(name = "Atributo.findByIdAtributo", query = "SELECT a FROM Atributo a WHERE a.idAtributo = :idAtributo"),
    @NamedQuery(name = "Atributo.findByNombre", query = "SELECT a FROM Atributo a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Atributo.findByNombrePantalla", query = "SELECT a FROM Atributo a WHERE a.nombrePantalla = :nombrePantalla"),
    @NamedQuery(name = "Atributo.findByIndicacionesPantalla", query = "SELECT a FROM Atributo a WHERE a.indicacionesPantalla = :indicacionesPantalla"),
    @NamedQuery(name = "Atributo.findByObligatorio", query = "SELECT a FROM Atributo a WHERE a.obligatorio = :obligatorio")})
public class Atributo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_atributo", nullable = false)
    private Long idAtributo;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 155)
    private String nombre;
    @Column(name = "nombre_pantalla", length = 155)
    private String nombrePantalla;
    @Column(name = "indicaciones_pantalla", length = 2147483647)
    private String indicacionesPantalla;
    @Column(name = "obligatorio")
    private Boolean obligatorio;
    @OneToMany(mappedBy = "idAtributo")
    private List<Metadato> metadatoList;
    @JoinColumn(name = "id_tipo_atributo", referencedColumnName = "id_tipo_atributo")
    @ManyToOne
    private TipoAtributo idTipoAtributo;
    @JoinColumn(name = "id_tipo_documento", referencedColumnName = "id_tipo_documento")
    @ManyToOne
    private TipoDocumento idTipoDocumento;

    public Atributo() {
    }

    public Atributo(Long idAtributo) {
        this.idAtributo = idAtributo;
    }

    public Atributo(Long idAtributo, String nombre) {
        this.idAtributo = idAtributo;
        this.nombre = nombre;
    }

    public Long getIdAtributo() {
        return idAtributo;
    }

    public void setIdAtributo(Long idAtributo) {
        this.idAtributo = idAtributo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombrePantalla() {
        return nombrePantalla;
    }

    public void setNombrePantalla(String nombrePantalla) {
        this.nombrePantalla = nombrePantalla;
    }

    public String getIndicacionesPantalla() {
        return indicacionesPantalla;
    }

    public void setIndicacionesPantalla(String indicacionesPantalla) {
        this.indicacionesPantalla = indicacionesPantalla;
    }

    public Boolean getObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(Boolean obligatorio) {
        this.obligatorio = obligatorio;
    }

    @JsonbTransient
    public List<Metadato> getMetadatoList() {
        return metadatoList;
    }

    public void setMetadatoList(List<Metadato> metadatoList) {
        this.metadatoList = metadatoList;
    }

    public TipoAtributo getIdTipoAtributo() {
        return idTipoAtributo;
    }

    public void setIdTipoAtributo(TipoAtributo idTipoAtributo) {
        this.idTipoAtributo = idTipoAtributo;
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
        hash += (idAtributo != null ? idAtributo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atributo)) {
            return false;
        }
        Atributo other = (Atributo) object;
        if ((this.idAtributo == null && other.idAtributo != null) || (this.idAtributo != null && !this.idAtributo.equals(other.idAtributo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi135.mapeodocumentos.entity.Atributo[ idAtributo=" + idAtributo + " ]";
    }

}
