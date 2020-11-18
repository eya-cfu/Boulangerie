package com.example.bakerieslibrary.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * BoulangerieApi3
 * The Boulangerie finally debugged for use, hosting service might need to be adapted
 * <p>
 * Contact: leafyteam@notaRealCompany.com
 */

public class CompositionsProduit {

    @SerializedName("idComposition")
    private Integer idComposition = null;
    @SerializedName("codeProduit")
    private Integer codeProduit = null;
    @SerializedName("idComposant")
    private String idComposant = null;
    @SerializedName("quantiteComp")
    private Integer quantiteComp = null;

    public CompositionsProduit() {
    }

    public CompositionsProduit(Integer idComposition, Integer codeProduit, String idComposant,
                               Integer quantiteComp) {
        this.idComposition = idComposition;
        this.codeProduit = codeProduit;
        this.idComposant = idComposant;
        this.quantiteComp = quantiteComp;
    }

    public Integer getIdComposition() {
        return idComposition;
    }

    public void setIdComposition(Integer idComposition) {
        this.idComposition = idComposition;
    }


    public Integer getCodeProduit() {
        return codeProduit;
    }

    public void setCodeProduit(Integer codeProduit) {
        this.codeProduit = codeProduit;
    }


    public String getIdComposant() {
        return idComposant;
    }

    public void setIdComposant(String idComposant) {
        this.idComposant = idComposant;
    }


    public Integer getQuantiteComp() {
        return quantiteComp;
    }

    public void setQuantiteComp(Integer quantiteComp) {
        this.quantiteComp = quantiteComp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositionsProduit that = (CompositionsProduit) o;
        return getIdComposition().equals(that.getIdComposition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdComposition());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CompositionsProduit {\n");

        sb.append("  idComposition: ").append(idComposition).append("\n");
        sb.append("  codeProduit: ").append(codeProduit).append("\n");
        sb.append("  idComposant: ").append(idComposant).append("\n");
        sb.append("  quantiteComp: ").append(quantiteComp).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}
