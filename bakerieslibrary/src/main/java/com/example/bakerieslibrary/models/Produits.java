package com.example.bakerieslibrary.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * BoulangerieApi3
 * The Boulangerie finally debugged for use, hosting service might need to be adapted
 * <p>
 * Contact: leafyteam@notaRealCompany.com
 */


public class Produits {

    @SerializedName("codeProduit")
    private Integer codeProduit = null;
    @SerializedName("libelle")
    private String libelle = null;
    @SerializedName("prixHA")
    private Double prixHA = null;
    @SerializedName("TVA")
    private Double TVA = null;
    @SerializedName("prixTTC")
    private Double prixTTC = null;

    public Produits() {
    }

    public Produits(Integer codeProduit, String libelle, Double prixHA, Double TVA,
                    Double prixTTC) {
        this.codeProduit = codeProduit;
        this.libelle = libelle;
        this.prixHA = prixHA;
        this.TVA = TVA;
        this.prixTTC = prixTTC;
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


    public Double getPrixHA() {
        return prixHA;
    }

    public void setPrixHA(Double prixHA) {
        this.prixHA = prixHA;
    }


    public Double getTVA() {
        return TVA;
    }

    public void setTVA(Double TVA) {
        this.TVA = TVA;
    }


    public Double getPrixTTC() {
        return prixTTC;
    }

    public void setPrixTTC(Double prixTTC) {
        this.prixTTC = prixTTC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produits produits = (Produits) o;
        return getCodeProduit().equals(produits.getCodeProduit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodeProduit());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Produits {\n");

        sb.append("  codeProduit: ").append(codeProduit).append("\n");
        sb.append("  libelle: ").append(libelle).append("\n");
        sb.append("  prixHA: ").append(prixHA).append("\n");
        sb.append("  TVA: ").append(TVA).append("\n");
        sb.append("  prixTTC: ").append(prixTTC).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}
