package com.example.acompstore.pModel;

public class ModelPembeli {

    private String idPembeli;
    private String NamaPembeli;
    private String NoHPPembeli;
    private String EmailPembeli;
    private String PasswordPembeli;

    public String getIdPembeli() {
        return idPembeli;
    }

    public void setIdPembeli(String idPembeli) {
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

    public ModelPembeli(String idPembeli, String namaPembeli, String noHPPembeli, String emailPembeli, String passwordPembeli) {
        this.idPembeli = idPembeli;
        this.NamaPembeli = namaPembeli;
        this.NoHPPembeli = noHPPembeli;
        this.EmailPembeli = emailPembeli;
        this.PasswordPembeli = passwordPembeli;
    }
}
