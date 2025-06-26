package com.jimboyz.cims.model.dao.medical;

import java.util.List;

public interface MedicalDataDAO {
    void addMedicalRecord(MedicalRecord medicalRecord);
    MedicalRecord getMedicalRecord(Long id);
    void updateMedicalRecord(MedicalRecord medicalRecord);
    void deleteMedicalRecord(MedicalRecord medicalRecord);
    List<MedicalRecord> getMedicalRecords();
}
