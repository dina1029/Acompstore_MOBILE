package com.example.acompstore.pModel;

public class ModelPembeli {

    int idPembeli;
    String NamaPembeli;
    String NoHPPembeli;
    String EmailPembeli;
    String PasswordPembeli;

    public int getIdPembeli() {
        return idPembeli;
    }

    public void setIdPembeli(int idPembeli) {
        this.idPembeli = idPembeli;
    }

    public String getNamaPembeli() {
        return NamaPembeli;
    }

    public void setNamaPembeli(String namaPembeli) {
        NamaPembeli = namaPembeli;
    }

    public String getNoHPPembeli() {
        return NoHPPembeli;
    }

    public void setNoHPPembeli(String noHPPembeli) {
        NoHPPembeli = noHPPembeli;
    }

    public String getEmailPembeli() {
        return EmailPembeli;
    }

    public void setEmailPembeli(String emailPembeli) {
        EmailPembeli = emailPembeli;
    }

    public String getPasswordPembeli() {
        return PasswordPembeli;
    }

    public void setPasswordPembeli(String passwordPembeli) {
        PasswordPembeli = passwordPembeli;
    }

    public ModelPembeli() {
    }

    public ModelPembeli(int idPembeli, String namaPembeli, String noHPPembeli, String emailPembeli, String passwordPembeli) {
        this.idPembeli = idPembeli;
        NamaPembeli = namaPembeli;
        NoHPPembeli = noHPPembeli;
        EmailPembeli = emailPembeli;
        PasswordPembeli = passwordPembeli;
    }
}
