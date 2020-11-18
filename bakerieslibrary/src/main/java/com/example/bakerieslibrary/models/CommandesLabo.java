package com.example.bakerieslibrary.models;

import java.util.Date;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;

/**
 * BoulangerieApi3
 * The Boulangerie finally debugged for use, hosting service might need to be adapted
 * <p>
 * Contact: leafyteam@notaRealCompany.com
 */

public class CommandesLabo {

    @SerializedName("idCommandesLabo")
    private Integer idCommandesLabo = null;
    @SerializedName("codeProduit")
    private Integer codeProduit = null;
    @SerializedName("libelle")
    private String libelle = null;
    @SerializedName("dueDate")
    private Date dueDate = null;
    @SerializedName("quantiteTotal")
    private String quantiteTotal = null;

    public CommandesLabo() {
    }

    public CommandesLabo(Integer idCommandesLabo, Integer codeProduit, String libelle,
                         Date dueDate, String quantiteTotal) {
        this.idCommandesLabo = idCommandesLabo;
        this.codeProduit = codeProduit;
        this.libelle = libelle;
        this.dueDate = dueDate;
        this.quantiteTotal = quantiteTotal;
    }

    public Integer getIdCommandesLabo() {
        return idCommandesLabo;
    }

    public void setIdCommandesLabo(Integer idCommandesLabo) {
        this.idCommandesLabo = idCommandesLabo;
    }


    public Integer getCodeProduit() {
        return codeProduit;
    }

    public void setCodeProduit(Integer codeProduit) {
        this.codeProduit = codeProduit;
    }


    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }


    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }


    public String getQuantiteTotal() {
        return quantiteTotal;
    }

    public void setQuantiteTotal(String quantiteTotal) {
        this.quantiteTotal = quantiteTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandesLabo that = (CommandesLabo) o;
        return getCodeProduit().equals(that.getCodeProduit()) &&
                getDueDate().equals(that.getDueDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodeProduit(), getDueDate());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CommandesLabo {\n");

        sb.append("  idCommandesLabo: ").append(idCommandesLabo).append("\n");
        sb.append("  codeProduit: ").append(codeProduit).append("\n");
        sb.append("  libelle: ").append(libelle).append("\n");
        sb.append("  dueDate: ").append(dueDate).append("\n");
        sb.append("  quantiteTotal: ").append(quantiteTotal).append("\n");
        sb.append("}\n");
        return sb.toString();
    }


}
