package com.example.bakerieslibrary.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * BoulangerieApi3
 * The Boulangerie finally debugged for use, hosting service might need to be adapted
 * <p>
 * Contact: leafyteam@notaRealCompany.com
 */


public class Administrateurs {


    @SerializedName("loginAdmin")
    private String loginAdmin = null;
    @SerializedName("passwordAdmin")
    private String passwordAdmin = null;
    @SerializedName("nomAdmin")
    private String nomAdmin = null;
    @SerializedName("teleAdmin")
    private Integer teleAdmin = null;

    public Administrateurs() {
    }

    public Administrateurs(String loginAdmin, String passwordAdmin,
                           String nomAdmin, Integer teleAdmin) {
        this.loginAdmin = loginAdmin;
        this.passwordAdmin = passwordAdmin;
        this.nomAdmin = nomAdmin;
        this.teleAdmin = teleAdmin;
    }

    public String getLoginAdmin() {
        return loginAdmin;
    }

    public void setLoginAdmin(String loginAdmin) {
        this.loginAdmin = loginAdmin;
    }


    public String getPasswordAdmin() {
        return passwordAdmin;
    }

    public void setPasswordAdmin(String passwordAdmin) {
        this.passwordAdmin = passwordAdmin;
    }


    public String getNomAdmin() {
        return nomAdmin;
    }

    public void setNomAdmin(String nomAdmin) {
        this.nomAdmin = nomAdmin;
    }


    public Integer getTeleAdmin() {
        return teleAdmin;
    }

    public void setTeleAdmin(Integer teleAdmin) {
        this.teleAdmin = teleAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrateurs that = (Administrateurs) o;
        return getLoginAdmin().equals(that.getLoginAdmin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLoginAdmin());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Administrateurs {\n");

        sb.append("  loginAdmin: ").append(loginAdmin).append("\n");
        sb.append("  passwordAdmin: ").append(passwordAdmin).append("\n");
        sb.append("  nomAdmin: ").append(nomAdmin).append("\n");
        sb.append("  teleAdmin: ").append(teleAdmin).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}
