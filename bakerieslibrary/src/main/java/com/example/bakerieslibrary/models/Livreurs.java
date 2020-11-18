package com.example.bakerieslibrary.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * BoulangerieApi3
 * The Boulangerie finally debugged for use, hosting service might need to be adapted
 * <p>
 * Contact: leafyteam@notaRealCompany.com
 */

public class Livreurs {

    @SerializedName("matricule")
    private Integer matricule = null;
    @SerializedName("teleLivreur")
    private Long teleLivreur = null;
    @SerializedName("numVehicule")
    private String numVehicule = null;

    public Livreurs() {
    }

    public Livreurs(Integer matricule, Long teleLivreur, String numVehicule) {
        this.matricule = matricule;
        this.teleLivreur = teleLivreur;
        this.numVehicule = numVehicule;
    }

    public Integer getMatricule() {
        return matricule;
    }

    public void setMatricule(Integer matricule) {
        this.matricule = matricule;
    }


    public Long getTeleLivreur() {
        return teleLivreur;
    }

    public void setTeleLivreur(Long teleLivreur) {
        this.teleLivreur = teleLivreur;
    }


    public String getNumVehicule() {
        return numVehicule;
    }

    public void setNumVehicule(String numVehicule) {
        this.numVehicule = numVehicule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livreurs livreurs = (Livreurs) o;
        return getMatricule().equals(livreurs.getMatricule());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMatricule());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Livreurs {\n");

        sb.append("  matricule: ").append(matricule).append("\n");
        sb.append("  teleLivreur: ").append(teleLivreur).append("\n");
        sb.append("  numVehicule: ").append(numVehicule).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}
