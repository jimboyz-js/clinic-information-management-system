package com.jimboyz.cims.model.dao.medical;

import com.jimboyz.cims.model.dao.Student;
import jakarta.persistence.*;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

import java.util.ArrayList;
import java.util.List;

@Entity
@Indexed
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean hypertension;
    private Boolean diabetes;
    private Boolean bronchial;
    private Boolean heartDisease;
    private Boolean thyroid;
    private Boolean copd;
    private Boolean pud;
    private String height;
    private String weight;
    private String pulseRate;
    private String bp;
    private String bloodType;
    private String remarks;
    private String familyHistory;
    private String developmentNutritionNormal;
    private String developmentNutritionPositive;
    private String developmentNutritionRemarks;
    private String generalAppearanceNormal;
    private String generalAppearancePositive;
    private String generalAppearanceRemarks;
    private String earNoseThroatNormal;
    private String earNoseThroatPositive;
    private String earNoseThroatRemarks;
    private String headEyesNormal;
    private String headEyesPositive;
    private String headEyesRemarks;
    private String chestLungsNormal;
    private String chestLungsPositive;
    private String chestLungsRemarks;
    private String heartNormal;
    private String heartPositive;
    private String heartRemarks;
    private String abdomenNormal;
    private String abdomenPositive;
    private String abdomenRemarks;
    private String genitalsNormal;
    private String genitalsPositive;
    private String genitalsRemarks;
    private String extremitiesNormal;
    private String extremitiesPositive;
    private String extremitiesRemarks;
    private String neuralgicNormal;
    private String neuralgicPositive;
    private String neuralgicRemarks;
    private String diagnosis;
    private String recommendation;
    private String physician;
    private String licenseNo;
    private String medicalExaminer;
    private String date;
    private String time;

//    @OneToOne(mappedBy = "medical_data", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn
//    @IndexedEmbedded
    public Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getHypertension() {
        return hypertension;
    }

    public void setHypertension(Boolean hypertension) {
        this.hypertension = hypertension;
    }

    public Boolean getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(Boolean diabetes) {
        this.diabetes = diabetes;
    }

    public Boolean getBronchial() {
        return bronchial;
    }

    public void setBronchial(Boolean bronchial) {
        this.bronchial = bronchial;
    }

    public Boolean getHeartDisease() {
        return heartDisease;
    }

    public void setHeartDisease(Boolean heartDisease) {
        this.heartDisease = heartDisease;
    }

    public Boolean getThyroid() {
        return thyroid;
    }

    public void setThyroid(Boolean thyroid) {
        this.thyroid = thyroid;
    }

    public Boolean getCopd() {
        return copd;
    }

    public void setCopd(Boolean copd) {
        this.copd = copd;
    }

    public Boolean getPud() {
        return pud;
    }

    public void setPud(Boolean pud) {
        this.pud = pud;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPulseRate() {
        return pulseRate;
    }

    public void setPulseRate(String pulseRate) {
        this.pulseRate = pulseRate;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory;
    }

    public String getDevelopmentNutritionNormal() {
        return developmentNutritionNormal;
    }

    public void setDevelopmentNutritionNormal(String developmentNutritionNormal) {
        this.developmentNutritionNormal = developmentNutritionNormal;
    }

    public String getDevelopmentNutritionPositive() {
        return developmentNutritionPositive;
    }

    public void setDevelopmentNutritionPositive(String developmentNutritionPositive) {
        this.developmentNutritionPositive = developmentNutritionPositive;
    }

    public String getDevelopmentNutritionRemarks() {
        return developmentNutritionRemarks;
    }

    public void setDevelopmentNutritionRemarks(String developmentNutritionRemarks) {
        this.developmentNutritionRemarks = developmentNutritionRemarks;
    }

    public String getGeneralAppearanceNormal() {
        return generalAppearanceNormal;
    }

    public void setGeneralAppearanceNormal(String generalAppearanceNormal) {
        this.generalAppearanceNormal = generalAppearanceNormal;
    }

    public String getGeneralAppearancePositive() {
        return generalAppearancePositive;
    }

    public void setGeneralAppearancePositive(String generalAppearancePositive) {
        this.generalAppearancePositive = generalAppearancePositive;
    }

    public String getGeneralAppearanceRemarks() {
        return generalAppearanceRemarks;
    }

    public void setGeneralAppearanceRemarks(String generalAppearanceRemarks) {
        this.generalAppearanceRemarks = generalAppearanceRemarks;
    }

    public String getEarNoseThroatNormal() {
        return earNoseThroatNormal;
    }

    public void setEarNoseThroatNormal(String earNoseThroatNormal) {
        this.earNoseThroatNormal = earNoseThroatNormal;
    }

    public String getEarNoseThroatPositive() {
        return earNoseThroatPositive;
    }

    public void setEarNoseThroatPositive(String earNoseThroatPositive) {
        this.earNoseThroatPositive = earNoseThroatPositive;
    }

    public String getEarNoseThroatRemarks() {
        return earNoseThroatRemarks;
    }

    public void setEarNoseThroatRemarks(String earNoseThroatRemarks) {
        this.earNoseThroatRemarks = earNoseThroatRemarks;
    }

    public String getHeadEyesNormal() {
        return headEyesNormal;
    }

    public void setHeadEyesNormal(String headEyesNormal) {
        this.headEyesNormal = headEyesNormal;
    }

    public String getHeadEyesPositive() {
        return headEyesPositive;
    }

    public void setHeadEyesPositive(String headEyesPositive) {
        this.headEyesPositive = headEyesPositive;
    }

    public String getHeadEyesRemarks() {
        return headEyesRemarks;
    }

    public void setHeadEyesRemarks(String headEyesRemarks) {
        this.headEyesRemarks = headEyesRemarks;
    }

    public String getChestLungsNormal() {
        return chestLungsNormal;
    }

    public void setChestLungsNormal(String chestLungsNormal) {
        this.chestLungsNormal = chestLungsNormal;
    }

    public String getChestLungsPositive() {
        return chestLungsPositive;
    }

    public void setChestLungsPositive(String chestLungsPositive) {
        this.chestLungsPositive = chestLungsPositive;
    }

    public String getChestLungsRemarks() {
        return chestLungsRemarks;
    }

    public void setChestLungsRemarks(String chestLungsRemarks) {
        this.chestLungsRemarks = chestLungsRemarks;
    }

    public String getHeartNormal() {
        return heartNormal;
    }

    public void setHeartNormal(String heartNormal) {
        this.heartNormal = heartNormal;
    }

    public String getHeartPositive() {
        return heartPositive;
    }

    public void setHeartPositive(String heartPositive) {
        this.heartPositive = heartPositive;
    }

    public String getHeartRemarks() {
        return heartRemarks;
    }

    public void setHeartRemarks(String heartRemarks) {
        this.heartRemarks = heartRemarks;
    }

    public String getAbdomenNormal() {
        return abdomenNormal;
    }

    public void setAbdomenNormal(String abdomenNormal) {
        this.abdomenNormal = abdomenNormal;
    }

    public String getAbdomenPositive() {
        return abdomenPositive;
    }

    public void setAbdomenPositive(String abdomenPositive) {
        this.abdomenPositive = abdomenPositive;
    }

    public String getAbdomenRemarks() {
        return abdomenRemarks;
    }

    public void setAbdomenRemarks(String abdomenRemarks) {
        this.abdomenRemarks = abdomenRemarks;
    }

    public String getGenitalsNormal() {
        return genitalsNormal;
    }

    public void setGenitalsNormal(String genitalsNormal) {
        this.genitalsNormal = genitalsNormal;
    }

    public String getGenitalsPositive() {
        return genitalsPositive;
    }

    public void setGenitalsPositive(String genitalsPositive) {
        this.genitalsPositive = genitalsPositive;
    }

    public String getGenitalsRemarks() {
        return genitalsRemarks;
    }

    public void setGenitalsRemarks(String genitalsRemarks) {
        this.genitalsRemarks = genitalsRemarks;
    }

    public String getExtremitiesNormal() {
        return extremitiesNormal;
    }

    public void setExtremitiesNormal(String extremitiesNormal) {
        this.extremitiesNormal = extremitiesNormal;
    }

    public String getExtremitiesPositive() {
        return extremitiesPositive;
    }

    public void setExtremitiesPositive(String extremitiesPositive) {
        this.extremitiesPositive = extremitiesPositive;
    }

    public String getExtremitiesRemarks() {
        return extremitiesRemarks;
    }

    public void setExtremitiesRemarks(String extremitiesRemarks) {
        this.extremitiesRemarks = extremitiesRemarks;
    }

    public String getNeuralgicNormal() {
        return neuralgicNormal;
    }

    public void setNeuralgicNormal(String neuralgicNormal) {
        this.neuralgicNormal = neuralgicNormal;
    }

    public String getNeuralgicPositive() {
        return neuralgicPositive;
    }

    public void setNeuralgicPositive(String neuralgicPositive) {
        this.neuralgicPositive = neuralgicPositive;
    }

    public String getNeuralgicRemarks() {
        return neuralgicRemarks;
    }

    public void setNeuralgicRemarks(String neuralgicRemarks) {
        this.neuralgicRemarks = neuralgicRemarks;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getPhysician() {
        return physician;
    }

    public void setPhysician(String physician) {
        this.physician = physician;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getMedicalExaminer() {
        return medicalExaminer;
    }

    public void setMedicalExaminer(String medicalExaminer) {
        this.medicalExaminer = medicalExaminer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
