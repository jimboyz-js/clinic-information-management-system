package com.jimboyz.cims.model;

import com.jimboyz.cims.err.ErrorDialog;
import com.jimboyz.cims.model.dao.Student;
import com.jimboyz.cims.model.dao.StudentDAO;
import com.jimboyz.cims.model.dao.StudentDaoImpl;
import com.jimboyz.cims.model.dao.medical.MedicalDaoImpl;
import com.jimboyz.cims.model.dao.medical.MedicalDataDAO;
import com.jimboyz.cims.model.dao.medical.MedicalRecord;
import com.jimboyz.cims.model.dao.user.User;
import com.jimboyz.cims.model.dao.user.UserDAO;
import com.jimboyz.cims.model.dao.user.UserDaoImpl;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class DataModel {

    private Student student;
    private final StudentDAO studentDAO;
    private MedicalRecord medicalRecord;
    private final MedicalDataDAO medicalDataDAO;
    private final StudentInfoModel studentInfoModel;
    private final EditPatientsModel editPatientsModel;
    private final WebcamModel webcamModel;
    private User user;
    private UserDAO userDao;
    private long totalResults = 0;
    private int currentPage = 0;
    private final int pageSize = 7;
    private Long studentId;

    public DataModel() {
        studentDAO = new StudentDaoImpl();
        medicalDataDAO = new MedicalDaoImpl();
        studentInfoModel = new StudentInfoModel();
        editPatientsModel = new EditPatientsModel();
        webcamModel = new WebcamModel();
        userDao = new UserDaoImpl();
//        medicalRecord = new MedicalRecord();
    }

    public void fetchData(JTable table) {
        for(Student student : studentDAO.getAllStudents()){
            ((DefaultTableModel) table.getModel()).addRow(new Object[]{student.getId(), student.getFirstname(), student.getLastname(), student.getGender(), student.getBirthdate(), student.getAge(), student.getCourse(), student.getGrade()});
        }
    }

    public void fetchData(JTable table, String firstName, String lastName, String gender, String yearLevel, String idNo, String depCourse, int page, int pageSize) {

//        for(Student student : studentDAO.getPaginatedStudents(firstName, lastName, gender, yearLevel, idNo)) {
//            ((DefaultTableModel) table.getModel()).addRow(new Object[]{student.getId(), student.getFirstname(), student.getLastname(), student.getGender(), student.getBirthdate(), student.getAge(), student.getCourse(), student.getGrade()});
//        }

        var result = studentDAO.searchPerform(firstName, lastName, gender, yearLevel, idNo, depCourse, page, pageSize);
        totalResults = result.total().hitCount();
        List<Student> student = result.hits();

        ((DefaultTableModel) table.getModel()).setRowCount(0);
        for(Student students : student) {
//            medicalRecord = students.getMedical_data();
            ((DefaultTableModel) table.getModel()).addRow(new Object[]{students.getId(), students.getFirstname(), students.getLastname(), students.getGender(), students.getBirthdate(), students.getAge(), students.getCourse(), students.getGrade()});
        }

        currentPage = page;
    }

    public void tableData(JTable table) {
        int selectedRow = table.getSelectedRow();
        if(selectedRow != -1) {

            String s = table.getValueAt(selectedRow, 0).toString();
            fetchingStudentData(Long.parseLong(s));
//                    studentInfoModel.setBloodType(student.set);

        }
    }

    public void fetchingStudentData(Long id) {

        student = studentDAO.findStudentById(id);
        medicalRecord = student.getMedical_data();

        studentInfoModel.setName(student.getFirstname() + " " + student.getLastname());
        studentInfoModel.setGender(student.getGender());
        studentInfoModel.setStatus(student.getCivilStatus());
        studentInfoModel.setBirthdate(student.getBirthdate());
        studentInfoModel.setAge(student.getAge());
        studentInfoModel.setAddress(student.getBarangay() + ", " + student.getMunicipality() + ", " + student.getProvince());
        studentInfoModel.setEmailAdd(student.getEmail());
        studentInfoModel.setCourse(student.getCourse());
        studentInfoModel.setYearLevel(student.getGrade());

        if(medicalRecord != null) {
            studentInfoModel.setBloodType(medicalRecord.getBloodType());
            studentInfoModel.setPulseRate(medicalRecord.getPulseRate());
            studentInfoModel.setHeight(medicalRecord.getHeight());
            studentInfoModel.setWeight(medicalRecord.getWeight());
            studentInfoModel.setBp(medicalRecord.getBp());
        } else {
            studentInfoModel.setBloodType("");
            studentInfoModel.setPulseRate(null);
            studentInfoModel.setHeight(null);
            studentInfoModel.setWeight(null);
            studentInfoModel.setBp(null);
        }

        studentInfoModel.setStudentImage(student.getStudentImage());
    }

    public List<User> fetchingAllUserData() {
        return userDao.getAllUser();
    }

    public boolean deleteUser(long id) {
        return userDao.deleteUser(id);
    }

    public String getUserTerminalKey(int id) {
        user = userDao.findUserById(id);
        return user.getTerminal_key();
    }

    public boolean getUserIsAllowUpdateUser() {
        return user.isAllowUpdateUser();
    }

    public boolean getUserIsAllowUpdateMedRec() {
        return user.isAllowUpdateMedicalRecord();
    }

    public boolean getUserIsAllowUpdateStudent() {
        return user.isAllowUpdateStudent();
    }

    public boolean getUserIsAllowAddUser() {
        return user.isAllowAddUser();
    }

    public boolean getUserIsAllowAddMedRec() {
        return user.isAllowAddMedicalRecord();
    }

    public boolean getUserIsAllowAddStudent() {
        return user.isAllowAddStudent();
    }

    public boolean getUserIsAllowDeleteStudent() {
        return user.isAllowDeleteStudent();
    }

    public boolean getUserIsAllowDeleteMedRec() {
        return user.isAllowDeleteMedicalRecord();
    }

    public boolean getUserIsAllowDeleteUser() {
        return user.isAllowDeleteUser();
    }

    public boolean getUserIsAdmin() {
        return user.isAdmin();
    }

    public JRBeanCollectionDataSource initReport(long id) {
//        List<Student> studentsRecord = studentDAO.getAllStudents();
//        List<MedicalRecord> medicalRecords = medicalDataDAO.getMedicalRecords();

//        Session session = sessionFactory.openSession();
//        List<Employee> employees = session.createQuery("FROM Employee", Employee.class).list();
//        session.close();

//        Session session = HibernateUtil.getSessionFactory().openSession();

//        List<Student> studentsRecord = session.createQuery("SELECT s FROM Student s JOIN FETCH s.medical_data").list();
//        Student studentsRecord = session.createQuery("SELECT s FROM Student s JOIN FETCH s.medical_data WHERE s.id = :id", Student.class).setParameter("id", sid).getSingleResult();
//        MedicalRecord medicalRecord1 = session.createQuery("SELECT m FROM MedicalRecord m LEFT JOIN FETCH m.student WHERE m.student.id = :id", MedicalRecord.class).setParameter("id", sid).getSingleResult();
//        System.out.println("Student Records: " + studentsRecord.getFirstname() + " " + studentsRecord.getLastname() + " : ");

        Student studentsRecord = studentDAO.findStudentWithMedicalRecord(id);

        if(studentsRecord != null) {
            List<Student> data = Collections.singletonList(studentsRecord);

            return new JRBeanCollectionDataSource(data);
        }

        return null;
//        SELECT s FROM Student s JOIN FETCH s.medical_data WHERE s.id = $P{id}
    }

    public void sample() {////////////////////
        StudentDaoImpl studentDaoImpl = new StudentDaoImpl();
        studentDaoImpl.session().doWork(connection -> {

        });
    }

//    public void addMedicalRecord(boolean isHypertension, boolean isDiabetes, boolean isBronchial, boolean isHeartDisease,
//                                boolean isThyroid, boolean isCopd, boolean isPud, String height, String weight, String pulseRate,
//                                 String bp, String bloodType, String remarks, String familyHistory, String developmentNutritionNormal,
//                                 String generalAppearanceNormal, String earNoseThroatNormal) {
//        medicalRecord = new MedicalRecord();
////        medicalRecord.setHypertension();
//    }

    // Create Account Model
    public boolean createAccount(String firstname, String lastname, String username, String macAddr, String terminalKey, String password, String salt) {
        try {
            user = userDao.findUserByTerminalKey(terminalKey);
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setUsername(username);
            user.setMac_add(macAddr);
            user.setPassword(password);
            user.setDisable(false);
            user.setSalt(salt);

            return userDao.updateUser(user);
        } catch (NullPointerException e) {
            ErrorDialog.show("Invalid key.");
        } catch (Exception e) {
            ErrorDialog.error(e.getMessage());
        }
        return false;
    }

    // Generate Terminal Key
    public boolean createTerminalKey(String key, boolean admin, boolean allowAddUser,
                                     boolean allowAddStudent, boolean allowAddMedicalRec,
                                     boolean allowUpdateStudent, boolean allowUpdateMedicalRec,
                                     boolean allowUpdateUser, boolean allowDeleteStudent,
                                     boolean allowDeleteMedicalRec, boolean allowDeleteUser) {
        user = new User();
        user.setTerminal_key(key);
        userCred(admin, allowAddUser, allowAddStudent, allowAddMedicalRec, allowUpdateStudent, allowUpdateMedicalRec, allowUpdateUser, allowDeleteStudent, allowDeleteMedicalRec, allowDeleteUser);
        return userDao.saveUser(user);
    }

    // Update User Privileges
    public boolean updateUser(boolean admin, boolean allowAddUser,
                              boolean allowAddStudent, boolean allowAddMedicalRec,
                              boolean allowUpdateStudent, boolean allowUpdateMedicalRec,
                              boolean allowUpdateUser, boolean allowDeleteStudent,
                              boolean allowDeleteMedicalRec, boolean allowDeleteUser) {

        userCred(admin, allowAddUser, allowAddStudent, allowAddMedicalRec, allowUpdateStudent, allowUpdateMedicalRec, allowUpdateUser, allowDeleteStudent, allowDeleteMedicalRec, allowDeleteUser);

        return userDao.updateUser(user);
    }

    // Suspend user
    public boolean suspendUser(int id, Date suspendFrom, Date suspendTo) {
        user = userDao.findUserById(id);
        user.setFromDateSuspended(suspendFrom);
        user.setToDateSuspended(suspendTo);
        return userDao.updateUser(user);
    }

    // Disable user
    public boolean disableUser(int id, boolean disable) {
        user = userDao.findUserById(id);
        user.setDisable(disable);
        return userDao.updateUser(user);
    }

    private void userCred(boolean admin, boolean allowAddUser, boolean allowAddStudent, boolean allowAddMedicalRec, boolean allowUpdateStudent, boolean allowUpdateMedicalRec, boolean allowUpdateUser, boolean allowDeleteStudent, boolean allowDeleteMedicalRec, boolean allowDeleteUser) {
        user.setAdmin(admin);
        user.setAllowAddUser(allowAddUser);
        user.setAllowAddStudent(allowAddStudent);
        user.setAllowAddMedicalRecord(allowAddMedicalRec);
        user.setAllowUpdateStudent(allowUpdateStudent);
        user.setAllowUpdateMedicalRecord(allowUpdateMedicalRec);
        user.setAllowUpdateUser(allowUpdateUser);
        user.setAllowDeleteStudent(allowDeleteStudent);
        user.setAllowDeleteMedicalRecord(allowDeleteMedicalRec);
        user.setAllowDeleteUser(allowDeleteUser);
    }

    public String generateTerminalKey() {
        UUID uuid = UUID.randomUUID();
        String key = uuid.toString().replace("-", "").toUpperCase();

        return key.substring(0, 10);
    }

    // Change Password
    public boolean changePassword(String username, String oldPassword, String newPassword, String salt) {
        try {

            User user = userDao.findUserByUsername(username);
            user.setPassword(newPassword);
            user.setSalt(salt);

            return userDao.updateUser(user);
        } catch (NullPointerException e) {
            ErrorDialog.show("Invalid old password.\n" + e.getMessage());
        } catch (Exception e) {
            ErrorDialog.show(e);
        }

        return false;
    }

    public void show() {

        editPatientsModel.setFirstname(student.getFirstname());
        editPatientsModel.setLastname(student.getLastname());
        editPatientsModel.setGender(student.getGender());
        editPatientsModel.setStatus(student.getCivilStatus());
        editPatientsModel.setBirthdate(student.getBirthdate());
        editPatientsModel.setPhone(student.getMobile());
        editPatientsModel.setAge(student.getAge());
        editPatientsModel.setBarangay(student.getBarangay());
        editPatientsModel.setMunicipality(student.getMunicipality());
        editPatientsModel.setProvince(student.getProvince());
        editPatientsModel.setEmailAdd(student.getEmail());
        editPatientsModel.setCourse(student.getCourse());
        editPatientsModel.setYearLevel(student.getGrade());
        editPatientsModel.setIdNum(student.getIdNumber());
        editPatientsModel.setStudentImage(student.getStudentImage());
    }

    public void saveData (String firstname, String lastname, String age,
                          String gender, String course, String civilStatus,
                          String yearLevel, String dob, String mobile,
                          String email, String idNo, String brgy,
                          String mun, String province, byte[] image) {
        student = new Student();
        studentCred(firstname, lastname, age, gender, course, civilStatus, yearLevel, dob, mobile, email, idNo, brgy, mun, province, image);

        studentId = studentDAO.addStudent(student);
    }

    public void updateData (String firstname, String lastname, String age,
                          String gender, String course, String civilStatus,
                          String yearLevel, String dob, String mobile,
                          String email, String idNo, String brgy,
                          String mun, String province, byte[] image) {
//        student = new Student();
        studentCred(firstname, lastname, age, gender, course, civilStatus, yearLevel, dob, mobile, email, idNo, brgy, mun, province, image);

        studentDAO.updateStudent(student);
    }

    public boolean deleteData() {
        studentDAO.deleteStudent(student.getId());
        return Boolean.parseBoolean(System.getProperty("app.data.delete"));
    }

    public void addMedicalRecord(boolean isHypertension, boolean isDiabetes, boolean isBronchial, boolean isHeartDisease,
                                 boolean isThyroid, boolean isCopd, boolean isPud, String height, String weight, String pulseRate,
                                 String bp, String bloodType, String remarks, String familyHistory, String developmentNutritionNormal,
                                 String developmentNutritionPositive, String developmentNutritionRemarks, String generalAppearanceNormal,
                                 String generalAppearancePositive, String generalAppearanceRemarks, String earNoseThroatNormal,
                                 String earNoseThroatPositive, String earNoseThroatRemarks, String headEyesNormal, String headEyesPositive,
                                 String headEyesRemarks, String chestLungsNormal, String chestLungsPositive, String chestLungsRemarks,
                                 String heartNormal, String heartPositive, String heartRemarks, String abdomenNormal, String abdomenPositive,
                                 String abdomenRemarks, String genitalsNormal, String genitalsPositive, String genitalsRemarks,
                                 String extremitiesNormal, String extremitiesPositive, String extremitiesRemarks, String neuralgicNormal,
                                 String neuralgicPositive, String neuralgicRemarks, String diagnosis, String recommendation, String physician,
                                 String licenseNo, String medicalExaminer, String date, String time) {

        student = studentDAO.findStudentById(studentId);
        medicalRecordExtracted(isHypertension, isDiabetes, isBronchial, isHeartDisease, isThyroid, isCopd, isPud, height, weight, pulseRate, bp, bloodType, remarks, familyHistory, developmentNutritionNormal, developmentNutritionPositive, developmentNutritionRemarks, generalAppearanceNormal, generalAppearancePositive, generalAppearanceRemarks, earNoseThroatNormal, earNoseThroatPositive, earNoseThroatRemarks, headEyesNormal, headEyesPositive, headEyesRemarks, chestLungsNormal, chestLungsPositive, chestLungsRemarks, heartNormal, heartPositive, heartRemarks, abdomenNormal, abdomenPositive, abdomenRemarks, genitalsNormal, genitalsPositive, genitalsRemarks, extremitiesNormal, extremitiesPositive, extremitiesRemarks, neuralgicNormal, neuralgicPositive, neuralgicRemarks, diagnosis, recommendation, physician, licenseNo, medicalExaminer, date, time);

        medicalDataDAO.addMedicalRecord(medicalRecord);
    }

    public void addMedicalRecordOfExistingPatient(boolean isHypertension, boolean isDiabetes, boolean isBronchial, boolean isHeartDisease,
                                 boolean isThyroid, boolean isCopd, boolean isPud, String height, String weight, String pulseRate,
                                 String bp, String bloodType, String remarks, String familyHistory, String developmentNutritionNormal,
                                 String developmentNutritionPositive, String developmentNutritionRemarks, String generalAppearanceNormal,
                                 String generalAppearancePositive, String generalAppearanceRemarks, String earNoseThroatNormal,
                                 String earNoseThroatPositive, String earNoseThroatRemarks, String headEyesNormal, String headEyesPositive,
                                 String headEyesRemarks, String chestLungsNormal, String chestLungsPositive, String chestLungsRemarks,
                                 String heartNormal, String heartPositive, String heartRemarks, String abdomenNormal, String abdomenPositive,
                                 String abdomenRemarks, String genitalsNormal, String genitalsPositive, String genitalsRemarks,
                                 String extremitiesNormal, String extremitiesPositive, String extremitiesRemarks, String neuralgicNormal,
                                 String neuralgicPositive, String neuralgicRemarks, String diagnosis, String recommendation, String physician,
                                 String licenseNo, String medicalExaminer, String date, String time) {


        medicalRecordExtracted(isHypertension, isDiabetes, isBronchial, isHeartDisease, isThyroid, isCopd, isPud, height, weight, pulseRate, bp, bloodType, remarks, familyHistory, developmentNutritionNormal, developmentNutritionPositive, developmentNutritionRemarks, generalAppearanceNormal, generalAppearancePositive, generalAppearanceRemarks, earNoseThroatNormal, earNoseThroatPositive, earNoseThroatRemarks, headEyesNormal, headEyesPositive, headEyesRemarks, chestLungsNormal, chestLungsPositive, chestLungsRemarks, heartNormal, heartPositive, heartRemarks, abdomenNormal, abdomenPositive, abdomenRemarks, genitalsNormal, genitalsPositive, genitalsRemarks, extremitiesNormal, extremitiesPositive, extremitiesRemarks, neuralgicNormal, neuralgicPositive, neuralgicRemarks, diagnosis, recommendation, physician, licenseNo, medicalExaminer, date, time);

        medicalDataDAO.addMedicalRecord(medicalRecord);
    }

    private void medicalRecordExtracted(boolean isHypertension, boolean isDiabetes, boolean isBronchial, boolean isHeartDisease, boolean isThyroid, boolean isCopd, boolean isPud, String height, String weight, String pulseRate, String bp, String bloodType, String remarks, String familyHistory, String developmentNutritionNormal, String developmentNutritionPositive, String developmentNutritionRemarks, String generalAppearanceNormal, String generalAppearancePositive, String generalAppearanceRemarks, String earNoseThroatNormal, String earNoseThroatPositive, String earNoseThroatRemarks, String headEyesNormal, String headEyesPositive, String headEyesRemarks, String chestLungsNormal, String chestLungsPositive, String chestLungsRemarks, String heartNormal, String heartPositive, String heartRemarks, String abdomenNormal, String abdomenPositive, String abdomenRemarks, String genitalsNormal, String genitalsPositive, String genitalsRemarks, String extremitiesNormal, String extremitiesPositive, String extremitiesRemarks, String neuralgicNormal, String neuralgicPositive, String neuralgicRemarks, String diagnosis, String recommendation, String physician, String licenseNo, String medicalExaminer, String date, String time) {
        medicalRecord = new MedicalRecord();
        medicalRecord.setHypertension(isHypertension);
        medicalRecord.setDiabetes(isDiabetes);
        medicalRecord.setBronchial(isBronchial);
        medicalRecord.setHeartDisease(isHeartDisease);
        medicalRecord.setThyroid(isThyroid);
        medicalRecord.setCopd(isCopd);
        medicalRecord.setPud(isPud);
        medicalRecord.setHeight(height);
        medicalRecord.setWeight(weight);
        medicalRecord.setPulseRate(pulseRate);
        medicalRecord.setBp(bp);
        medicalRecord.setBloodType(bloodType);
        medicalRecord.setRemarks(remarks);
        medicalRecord.setFamilyHistory(familyHistory);
        medicalRecord.setDevelopmentNutritionNormal(developmentNutritionNormal);
        medicalRecord.setDevelopmentNutritionPositive(developmentNutritionPositive);
        medicalRecord.setDevelopmentNutritionRemarks(developmentNutritionRemarks);
        medicalRecord.setGeneralAppearanceNormal(generalAppearanceNormal);
        medicalRecord.setGeneralAppearancePositive(generalAppearancePositive);
        medicalRecord.setGeneralAppearanceRemarks(generalAppearanceRemarks);
        medicalRecord.setEarNoseThroatNormal(earNoseThroatNormal);
        medicalRecord.setEarNoseThroatPositive(earNoseThroatPositive);
        medicalRecord.setEarNoseThroatRemarks(earNoseThroatRemarks);
        medicalRecord.setHeadEyesNormal(headEyesNormal);
        medicalRecord.setHeadEyesPositive(headEyesPositive);
        medicalRecord.setHeadEyesRemarks(headEyesRemarks);
        medicalRecord.setChestLungsNormal(chestLungsNormal);
        medicalRecord.setChestLungsPositive(chestLungsPositive);
        medicalRecord.setChestLungsRemarks(chestLungsRemarks);
        medicalRecord.setHeartNormal(heartNormal);
        medicalRecord.setHeartPositive(heartPositive);
        medicalRecord.setHeartRemarks(heartRemarks);
        medicalRecord.setAbdomenNormal(abdomenNormal);
        medicalRecord.setAbdomenPositive(abdomenPositive);
        medicalRecord.setAbdomenRemarks(abdomenRemarks);
        medicalRecord.setGenitalsNormal(genitalsNormal);
        medicalRecord.setGenitalsPositive(genitalsPositive);
        medicalRecord.setGenitalsRemarks(genitalsRemarks);
        medicalRecord.setExtremitiesNormal(extremitiesNormal);
        medicalRecord.setExtremitiesPositive(extremitiesPositive);
        medicalRecord.setExtremitiesRemarks(extremitiesRemarks);
        medicalRecord.setNeuralgicNormal(neuralgicNormal);
        medicalRecord.setNeuralgicPositive(neuralgicPositive);
        medicalRecord.setNeuralgicRemarks(neuralgicRemarks);
        medicalRecord.setDiagnosis(diagnosis);
        medicalRecord.setRecommendation(recommendation);
        medicalRecord.setPhysician(physician);
        medicalRecord.setLicenseNo(licenseNo);
        medicalRecord.setMedicalExaminer(medicalExaminer);
        medicalRecord.setDate(date);
        medicalRecord.setTime(time);

        medicalRecord.setStudent(student);
    }

    private void studentCred(String firstname, String lastname, String age, String gender, String course, String civilStatus, String yearLevel, String dob, String mobile, String email, String idNo, String brgy, String mun, String province, byte[] image) {
        student.setFirstname(firstname);
        student.setLastname(lastname);
        student.setAge(Objects.equals(age, "") ? 0 : Integer.parseInt(age));
        student.setGender(gender);
        student.setCourse(course);
        student.setCivilStatus(civilStatus);
        student.setGrade(yearLevel);
        student.setBirthdate(dob);
        student.setMobile(mobile);
        student.setEmail(email);
        student.setIdNumber(idNo);
        student.setBarangay(brgy);
        student.setMunicipality(mun);
        student.setProvince(province);
        student.setStudentImage(image);
        student.setDepartment(setStudentDepartment(course));
    }

    public String setStudentDepartment(String course) {

        return switch (course) {
            case "ACT", "BSIT", "BSBA", "BSCRIM" -> "College";
            case "DHMT", "HRM" -> "TRS";
            case "JUNIOR High", "SENIOR High" -> "High School";
            default -> "Other Course";
        };
    }

    public EditPatientsModel getEditPatientsModel() {
        return editPatientsModel;
    }

    public StudentInfoModel getStudentInfoModel() {
        return studentInfoModel;
    }

    public WebcamModel getWebcamModel() {
        return webcamModel;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }
}
