package com.jimboyz.cims.model.dao.user;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="users", indexes = {
        @Index(name = "idx_firstname", columnList = "firstname"),
        @Index(name = "idx_lastname", columnList = "lastname"),
        @Index(name = "idx_username", columnList = "username"),
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstname;
    private String lastname;
    @Column(unique=true)
    private String username;
    private String password;
    @Column(unique=true)
    private String terminal_key;
    private String mac_add;
    private boolean isAdmin;
    private boolean allowAddStudent;
    private boolean allowAddMedicalRecord;
    private boolean allowAddUser;
    private boolean allowUpdateStudent;
    private boolean allowUpdateMedicalRecord;
    private boolean allowUpdateUser;
    private boolean allowDeleteStudent;
    private boolean allowDeleteMedicalRecord;
    private boolean allowDeleteUser;
    private boolean disable;
    @Temporal(TemporalType.DATE)
    private Date fromDateSuspended;
    @Temporal(TemporalType.DATE)
    private Date toDateSuspended;
    private String salt;



    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTerminal_key() {
        return terminal_key;
    }

    public void setTerminal_key(String terminal_key) {
        this.terminal_key = terminal_key;
    }

    public String getMac_add() {
        return mac_add;
    }

    public void setMac_add(String mac_add) {
        this.mac_add = mac_add;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isAllowAddStudent() {
        return allowAddStudent;
    }

    public void setAllowAddStudent(boolean allowAddStudent) {
        this.allowAddStudent = allowAddStudent;
    }

    public boolean isAllowDeleteStudent() {
        return allowDeleteStudent;
    }

    public void setAllowDeleteStudent(boolean allowDeleteStudent) {
        this.allowDeleteStudent = allowDeleteStudent;
    }

    public boolean isAllowAddMedicalRecord() {
        return allowAddMedicalRecord;
    }

    public void setAllowAddMedicalRecord(boolean allowAddMedicalRecord) {
        this.allowAddMedicalRecord = allowAddMedicalRecord;
    }

    public boolean isAllowAddUser() {
        return allowAddUser;
    }

    public void setAllowAddUser(boolean allowAddUser) {
        this.allowAddUser = allowAddUser;
    }

    public boolean isAllowUpdateStudent() {
        return allowUpdateStudent;
    }

    public void setAllowUpdateStudent(boolean allowUpdateStudent) {
        this.allowUpdateStudent = allowUpdateStudent;
    }

    public boolean isAllowUpdateMedicalRecord() {
        return allowUpdateMedicalRecord;
    }

    public void setAllowUpdateMedicalRecord(boolean allowUpdateMedicalRecord) {
        this.allowUpdateMedicalRecord = allowUpdateMedicalRecord;
    }

    public boolean isAllowUpdateUser() {
        return allowUpdateUser;
    }

    public void setAllowUpdateUser(boolean allowUpdateUser) {
        this.allowUpdateUser = allowUpdateUser;
    }

    public boolean isAllowDeleteMedicalRecord() {
        return allowDeleteMedicalRecord;
    }

    public void setAllowDeleteMedicalRecord(boolean allowDeleteMedicalRecord) {
        this.allowDeleteMedicalRecord = allowDeleteMedicalRecord;
    }

    public boolean isAllowDeleteUser() {
        return allowDeleteUser;
    }

    public void setAllowDeleteUser(boolean allowDeleteUser) {
        this.allowDeleteUser = allowDeleteUser;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getFromDateSuspended() {
        return fromDateSuspended;
    }

    public void setFromDateSuspended(Date fromDateSuspended) {
        this.fromDateSuspended = fromDateSuspended;
    }

    public Date getToDateSuspended() {
        return toDateSuspended;
    }

    public void setToDateSuspended(Date toDateSuspended) {
        this.toDateSuspended = toDateSuspended;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
