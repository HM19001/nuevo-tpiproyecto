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
@Table(name = "tipo_atributo", catalog = "documentostpi135", schema = "public")
@NamedQueries({
    @NamedQuery(name = "TipoAtributo.findAll", query = "SELECT t FROM TipoAtributo t"),
    @NamedQuery(name = "TipoAtributo.findByIdTipoAtributo", query = "SELECT t FROM TipoAtributo t WHERE t.idTipoAtributo = :idTipoAtributo"),
    @NamedQuery(name = "TipoAtributo.findByNombre", query = "SELECT t FROM TipoAtributo t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoAtributo.findByObservaciones", query = "SELECT t FROM TipoAtributo t WHERE t.observaciones = :observaciones"),
    @NamedQuery(name = "TipoAtributo.findByExpresionRegular", query = "SELECT t FROM TipoAtributo t WHERE t.expresionRegular = :expresionRegular"),
    @NamedQuery(name = "TipoAtributo.findByNombreScreen", query = "SELECT t FROM TipoAtributo t WHERE t.nombreScreen = :nombreScreen"),
    @NamedQuery(name = "TipoAtributo.findByIndicacionesScreen", query = "SELECT t FROM TipoAtributo t WHERE t.indicacionesScreen = :indicacionesScreen")})
public class TipoAtributo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_atributo", nullable = false)
    private Integer idTipoAtributo;
    @Column(name = "nombre", length = 155)
    private String nombre;
    @Column(name = "observaciones", length = 2147483647)
    private String observaciones;
    @Column(name = "expresion_regular", length = 2147483647)
    private String expresionRegular;
    @Column(name = "nombre_screen", length = 155)
    private String nombreScreen;
    @Column(name = "indicaciones_screen", length = 2147483647)
    private String indicacionesScreen;
    @OneToMany(mappedBy = "idTipoAtributo")
    private List<Atributo> atributoList;

    public TipoAtributo() {
    }

    public TipoAtributo(Integer idTipoAtributo) {
        this.idTipoAtributo = idTipoAtributo;
    }

    public Integer getIdTipoAtributo() {
        return idTipoAtributo;
    }

    public void setIdTipoAtributo(Integer idTipoAtributo) {
        this.idTipoAtributo = idTipoAtributo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getExpresionRegular() {
        return expresionRegular;
    }

    public void setExpresionRegular(String expresionRegular) {
        this.expresionRegular = expresionRegular;
    }

    public String getNombreScreen() {
        return nombreScreen;
    }

    public void setNombreScreen(String nombreScreen) {
        this.nombreScreen = nombreScreen;
    }

    public String getIndicacionesScreen() {
        return indicacionesScreen;
    }

    public void setIndicacionesScreen(String indicacionesScreen) {
        this.indicacionesScreen = indicacionesScreen;
    }

    @JsonbTransient
    public List<Atributo> getAtributoList() {
        return atributoList;
    }

    public void setAtributoList(List<Atributo> atributoList) {
        this.atributoList = atributoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoAtributo != null ? idTipoAtributo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAtributo)) {
            return false;
        }
        TipoAtributo other = (TipoAtributo) object;
        if ((this.idTipoAtributo == null && other.idTipoAtributo != null) || (this.idTipoAtributo != null && !this.idTipoAtributo.equals(other.idTipoAtributo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi135.mapeodocumentos.entity.TipoAtributo[ idTipoAtributo=" + idTipoAtributo + " ]";
    }

}
