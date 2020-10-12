package com.example.diabetescarelink.Models;

public class PatientsModel {
    String id,fullnames,age,sex,contact,village,district,maritalstatus,occupation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullnames() {
        return fullnames;
    }

    public void setFullnames(String fullnames) {
        this.fullnames = fullnames;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public PatientsModel(String id, String fullnames, String age, String sex, String contact, String village, String district, String maritalstatus, String occupation) {
        this.id = id;
        this.fullnames = fullnames;
        this.age = age;
        this.sex = sex;
        this.contact = contact;
        this.village = village;
        this.district = district;
        this.maritalstatus = maritalstatus;
        this.occupation = occupation;
    }
}
