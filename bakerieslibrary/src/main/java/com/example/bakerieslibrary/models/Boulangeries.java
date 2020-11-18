package com.example.bakerieslibrary.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

/**
 * BoulangerieApi3
 * The Boulangerie finally debugged for use, hosting service might need to be adapted
 * <p>
 * Contact: leafyteam@notaRealCompany.com
 */

public class Boulangeries implements Serializable {


    @SerializedName("id_Boulangerie")
    private Integer idBoulangerie = null;
    @SerializedName("nomBL")
    private String nomBL = null;
    @SerializedName("adresse")
    private String adresse = null;
    @SerializedName("telephone")
    private Long telephone = null;
    @SerializedName("matricule")
    private Integer matricule = null;
    @SerializedName("nbOperateurs")
    private Integer nbOperateurs = null;

    public Boulangeries() {
    }

    public Boulangeries(Integer idBoulangerie, String nomBL, String adresse, Long telephone,
                        Integer matricule, Integer nbOperateurs) {
        this.idBoulangerie = idBoulangerie;
        this.nomBL = nomBL;
        this.adresse = adresse;
        this.telephone = telephone;
        this.matricule = matricule;
        this.nbOperateurs = nbOperateurs;
    }

    public Integer getIdBoulangerie() {
        return idBoulangerie;
    }

    public void setIdBoulangerie(Integer idBoulangerie) {
        this.idBoulangerie = idBoulangerie;
    }


    public String getNomBL() {
        return nomBL;
    }

    public void setNomBL(String nomBL) {
        this.nomBL = nomBL;
    }


    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }


    public Integer getMatricule() {
        return matricule;
    }

    public void setMatricule(Integer matricule) {
        this.matricule = matricule;
    }


    public Integer getNbOperateurs() {
        return nbOperateurs;
    }

    public void setNbOperateurs(Integer nbOperateurs) {
        this.nbOperateurs = nbOperateurs;
    }


    public Boulangeries getUser(File file) throws IOException, ClassNotFoundException {

        Boulangeries boulangerie = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        fis = new FileInputStream(file);
        ois = new ObjectInputStream(fis);
        boulangerie = (Boulangeries) ois.readObject();

        return boulangerie;
    }

    public void saveUser(File file, Boulangeries boulangerie) throws IOException{

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        fos = new FileOutputStream(file);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(boulangerie);

        oos.close();
        fos.close();
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boulangeries that = (Boulangeries) o;
        return getIdBoulangerie().equals(that.getIdBoulangerie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdBoulangerie());
    }

    @Override
    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Boulangerie: ");

        sb.append("idBoulangerie: ").append(idBoulangerie).append(",");
        sb.append("  nomBL: ").append(nomBL).append(",");
        sb.append("  adresse: ").append(adresse).append(",");
        sb.append("  telephone: ").append(telephone);

        return sb.toString();
    }


}
