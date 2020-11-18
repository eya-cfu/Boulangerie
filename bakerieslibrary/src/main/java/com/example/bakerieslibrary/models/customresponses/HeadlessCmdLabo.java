package com.example.bakerieslibrary.models.customresponses;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;

/**
 * BoulangerieApi
 * The Boulangerie finally debugged for use, hosting service might need to be adapted
 * <p>
 * Contact: leafyteam@notaRealCompany.com
 * <p>
 * This class is a Commande Labo without an ID.
 * RESPONSE TO getCmdForLab request under CommandesBLController.
 */

public class HeadlessCmdLabo {

    @SerializedName("codeProduit")
    private Integer codeProduit = null;
    @SerializedName("libelle")
    private String libelle = null;
    @SerializedName("dueDate")
    private Date dueDate = null;
    @SerializedName("quantiteTotal")
    private Integer quantiteTotal = null;


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

    public Integer getQuantiteTotal() {
        return quantiteTotal;
    }

    public void setQuantiteTotal(Integer quantiteTotal) {
        this.quantiteTotal = quantiteTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeadlessCmdLabo that = (HeadlessCmdLabo) o;
        return getCodeProduit().equals(that.getCodeProduit()) &&
                getDueDate().equals(that.getDueDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodeProduit(), getDueDate());
    }

    @Override
    @NonNull
    public String toString() {
        return "codeProduit=" + codeProduit +
                ", libelle='" + libelle + '\'' +
                ", dueDate=" + dueDate +
                ", quantiteTotal=" + quantiteTotal +
                '}';
    }
}
