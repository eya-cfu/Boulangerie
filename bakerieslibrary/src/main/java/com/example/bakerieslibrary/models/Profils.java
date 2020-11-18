package com.example.bakerieslibrary.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * BoulangerieApi3
 * The Boulangerie finally debugged for use, hosting service might need to be adapted
 * <p>
 * Contact: leafyteam@notaRealCompany.com
 */


public class Profils {


    @SerializedName("matricule")
    private Integer matricule = null;
    @SerializedName("nom")
    private String nom = null;
    @SerializedName("affectation")
    private String affectation = null;
    @SerializedName("login")
    private String login = null;
    @SerializedName("password")
    private String password = null;

    public Profils() {
    }

    public Profils(Integer matricule, String nom, String affectation, String login,
                   String password) {
        this.matricule = matricule;
        this.nom = nom;
        this.affectation = affectation;
        this.login = login;
        this.password = password;
    }

    /**
     * minimum: 0
     * maximum: 9999
     **/
    public Integer getMatricule() {
        return matricule;
    }

    public void setMatricule(Integer matricule) {
        this.matricule = matricule;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getAffectation() {
        return affectation;
    }

    public void setAffectation(String affectation) {
        this.affectation = affectation;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profils profils = (Profils) o;
        return getMatricule().equals(profils.getMatricule());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMatricule());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Profils {\n");

        sb.append("  matricule: ").append(matricule).append("\n");
        sb.append("  nom: ").append(nom).append("\n");
        sb.append("  affectation: ").append(affectation).append("\n");
        sb.append("  login: ").append(login).append("\n");
        sb.append("  password: ").append(password).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}
