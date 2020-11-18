package com.example.bakerieslibrary.models.customresponses;

import androidx.annotation.NonNull;

import com.example.bakerieslibrary.models.Boulangeries;
import com.google.gson.annotations.SerializedName;

/**
 * BoulangerieApi3
 * The Boulangerie finally debugged for use, hosting service might need to be adapted
 * <p>
 * Contact: leafyteam@notaRealCompany.com
 * <p>
 * This class represents a Boulangerie and the name of its Responsable
 * It was referred to as InlineResponse2001
 */

public class BoulangerieAndResp {


    @SerializedName("Boulangerie")
    private Boulangeries boulangerie = null;
    @SerializedName("nom")
    private String nom = null;  //nomResponsable

    public BoulangerieAndResp() {
    }

    public BoulangerieAndResp(Boulangeries boulangerie, String nom) {
        this.boulangerie = boulangerie;
        this.nom = nom;
    }

    public Boulangeries getBoulangerie() {
        return boulangerie;
    }

    public void setBoulangerie(Boulangeries boulangerie) {
        this.boulangerie = boulangerie;
    }

    /**
     * nom responsable
     **/
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    @NonNull
    public String toString() {
        return  boulangerie.toString() +
                ", responsable=" + nom ;
    }
}
