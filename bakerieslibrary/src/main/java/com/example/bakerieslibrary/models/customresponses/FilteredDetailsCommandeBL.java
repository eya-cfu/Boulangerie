package com.example.bakerieslibrary.models.customresponses;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * BoulangerieApi
 * The Boulangerie finally debugged for use, hosting service might need to be adapted
 * <p>
 * Contact: leafyteam@notaRealCompany.com
 * <p>
 * RESPONSE FOR getDetailsByCommandeBL under CommandesBLController
 * It was referred to as InlineResponse2004
 */

public class FilteredDetailsCommandeBL {



    @SerializedName("codeProduit")
    private Integer codeProduit = null;
    @SerializedName("libelle")
    private String libelle = null;
    @SerializedName("quantite")
    private Integer quantiteProd = null;


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


    public Integer getQuantiteProd() {
        return quantiteProd;
    }

    public void setQuantiteProd(Integer quantiteProd) {
        this.quantiteProd = quantiteProd;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilteredDetailsCommandeBL that = (FilteredDetailsCommandeBL) o;
        return getCodeProduit().equals(that.getCodeProduit()) &&
                getQuantiteProd().equals(that.getQuantiteProd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodeProduit(), getQuantiteProd());
    }

    @Override
    @NonNull
    public String toString() {
        return  "PD"+codeProduit + "-" + libelle +" " + quantiteProd ;
    }
}
