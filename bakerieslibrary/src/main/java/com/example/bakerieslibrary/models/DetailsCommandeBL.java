package com.example.bakerieslibrary.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * BoulangerieApi3
 * The Boulangerie finally debugged for use, hosting service might need to be adapted
 * <p>
 * Contact: leafyteam@notaRealCompany.com
 */


public class DetailsCommandeBL {


    @SerializedName("idDetail")
    private Integer idDetail = null;
    @SerializedName("codeProduit")
    private Integer codeProduit = null;
    @SerializedName("idCommandeBL")
    private String idCommandeBL = null;
    @SerializedName("quantite")
    private Integer quantiteProd = null;

    public DetailsCommandeBL() {
    }

    public DetailsCommandeBL(Integer idDetail, Integer codeProduit, String idCommandeBL,
                             Integer quantiteProd) {
        this.idDetail = idDetail;
        this.codeProduit = codeProduit;
        this.idCommandeBL = idCommandeBL;
        this.quantiteProd = quantiteProd;
    }

    public Integer getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(Integer idDetail) {
        this.idDetail = idDetail;
    }


    public Integer getCodeProduit() {
        return codeProduit;
    }

    public void setCodeProduit(Integer codeProduit) {
        this.codeProduit = codeProduit;
    }


    public String getIdCommandeBL() {
        return idCommandeBL;
    }

    public void setIdCommandeBL(String idCommandeBL) {
        this.idCommandeBL = idCommandeBL;
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
        DetailsCommandeBL that = (DetailsCommandeBL) o;
        return Objects.equals(getIdDetail(), that.getIdDetail()) &&
                getCodeProduit().equals(that.getCodeProduit()) &&
                getIdCommandeBL().equals(that.getIdCommandeBL());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdDetail(), getCodeProduit(), getIdCommandeBL());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DetailsCommandesBL {\n");

        sb.append("  idDetail: ").append(idDetail).append("\n");
        sb.append("  codeProduit: ").append(codeProduit).append("\n");
        sb.append("  idCommandeBL: ").append(idCommandeBL).append("\n");
        sb.append("  quantiteProd: ").append(quantiteProd).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}
