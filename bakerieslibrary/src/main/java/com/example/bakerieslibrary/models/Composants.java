package com.example.bakerieslibrary.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * BoulangerieApi3
 * The Boulangerie finally debugged for use, hosting service might need to be adapted
 * <p>
 * Contact: leafyteam@notaRealCompany.com
 */

public class Composants {

    @SerializedName("idComposant")
    private String idComposant = null;
    @SerializedName("nomComp")
    private String nomComp = null;
    @SerializedName("unite")

    private String unite = null;

    public Composants() {
    }

    public Composants(String idComposant, String nomComp, String unite) {
        this.idComposant = idComposant;
        this.nomComp = nomComp;
        this.unite = unite;
    }

    public String getIdComposant() {
        return idComposant;
    }

    public void setIdComposant(String idComposant) {
        this.idComposant = idComposant;
    }


    public String getNomComp() {
        return nomComp;
    }

    public void setNomComp(String nomComp) {
        this.nomComp = nomComp;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Composants that = (Composants) o;
        return getIdComposant().equals(that.getIdComposant());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdComposant());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Composants {\n");

        sb.append("  idComposant: ").append(idComposant).append("\n");
        sb.append("  nomComp: ").append(nomComp).append("\n");
        sb.append("  unite: ").append(unite).append("\n");
        sb.append("}\n");
        return sb.toString();
    }


}
