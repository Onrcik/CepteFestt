package com.example.ceptefestt;

public class Kullanici {
    private String ad,eposta;
    private int tel;

    public Kullanici(){}

    public Kullanici(String ad, String eposta, int tel) {
        this.ad = ad;
        this.eposta = eposta;
        this.tel = tel;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String soyad) {
        this.eposta = eposta;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int yas) {
        this.tel = tel;
    }
}



