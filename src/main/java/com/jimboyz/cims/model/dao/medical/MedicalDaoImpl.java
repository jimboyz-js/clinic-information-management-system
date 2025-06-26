package com.jimboyz.cims.model.dao.medical;

import com.jimboyz.cims.db.HibernateUtil;
import com.jimboyz.cims.err.ErrorDialog;
import org.hibernate.Session;

import java.util.List;

public class MedicalDaoImpl implements MedicalDataDAO {

    @Override
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(medicalRecord);
            System.setProperty("app.medical.data.save", "true");
            session.getTransaction().commit();

        } catch (Exception e) {
            System.setProperty("app.medical.data.save", "false");
            ErrorDialog.show(e.getMessage());
        }
    }

    @Override
    public MedicalRecord getMedicalRecord(Long id) {
        return null;
    }

    @Override
    public void updateMedicalRecord(MedicalRecord medicalRecord) {

    }

    @Override
    public void deleteMedicalRecord(MedicalRecord medicalRecord) {

    }

    @Override
    public List<MedicalRecord> getMedicalRecords() {

       try( Session session = HibernateUtil.getSessionFactory().openSession()) {
           List<MedicalRecord> medicalRecords = session.createQuery("from MedicalRecord", MedicalRecord.class).list();
           return medicalRecords;
       }

//        return List.of();
    }
}
