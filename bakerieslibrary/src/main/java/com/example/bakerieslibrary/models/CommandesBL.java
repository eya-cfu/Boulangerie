package com.example.bakerieslibrary.models;


import androidx.annotation.NonNull;

import java.util.Date;
import java.util.Objects;

import com.example.bakerieslibrary.ApiInvoker;
import com.google.gson.annotations.SerializedName;

/**
 * BoulangerieApi3
 * The Boulangerie finally debugged for use, hosting service might need to be adapted
 * <p>
 * Contact: leafyteam@notaRealCompany.com
 */


public class CommandesBL  {

    @SerializedName("idCommandeBL")
    private String idCommandeBL = null;
    @SerializedName("dueDate")
    private String dueDate = null;
    @SerializedName("creationDate")
    private Date creationDate = null;
    @SerializedName("etat")
    private EtatEnum etat = null;
    @SerializedName("idBoulangerie")
    private Integer idBoulangerie = null;
    @SerializedName("matricule")
    private Integer matricule = 0;

    public CommandesBL() {
    }


    public CommandesBL(String idCommandeBL, Date dueDate, Date creationDate, EtatEnum etat,
                       Integer idBoulangerie, Integer matricule) {
        this.idCommandeBL = idCommandeBL;
        this.dueDate = ApiInvoker.formatDate(dueDate);
        this.creationDate = creationDate;
        this.etat = etat;
        this.idBoulangerie = idBoulangerie;
        this.matricule = matricule;
    }


    public String getIdCommandeBL() {
        return idCommandeBL;
    }

    public void setIdCommandeBL(String idCommandeBL) {
        this.idCommandeBL = idCommandeBL;
    }

    public Date getDueDate() {
        return ApiInvoker.parseDate(dueDate);
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = ApiInvoker.formatDate(dueDate);
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public EtatEnum getEtat() {
        return etat;
    }

    public void setEtat(EtatEnum etat) {
        this.etat = etat;
    }

    public Integer getIdBoulangerie() {
        return idBoulangerie;
    }

    public void setIdBoulangerie(Integer idBoulangerie) {
        this.idBoulangerie = idBoulangerie;
    }

    public Integer getMatricule() {
        return matricule;
    }

    public void setMatricule(Integer matricule) {
        this.matricule = matricule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandesBL that = (CommandesBL) o;
        return getIdCommandeBL().equals(that.getIdCommandeBL());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdCommandeBL());
    }

    @Override
    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CommandesBL {\n");

        sb.append("  idCommandeBL: ").append(idCommandeBL).append("\n");
        sb.append("  dueDate: ").append(dueDate).append("\n");
        sb.append("  creationDate: ").append(creationDate).append("\n");
        sb.append("  etat: ").append(etat).append("\n");
        sb.append("  idBoulangerie: ").append(idBoulangerie).append("\n");
        sb.append("  matricule: ").append(matricule).append("\n");
        sb.append("}\n");
        return sb.toString();
    }


    public enum EtatEnum {
        nouvelle, enCours, honoree,
    }


}
