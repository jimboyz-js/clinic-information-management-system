package com.jimboyz.cims.view.tree;

public enum TreeNodeModel {

    ALL_RECORDS("All Records", ""),
    College("College", ""),
    BSIT("College", "BSIT"),
    BSBA("College", "BSBA"),
    BSCRIM("College", "BSCRIM"),
    ACT("College", "ACT"),
    HIGHS_SCHOOL("HIGH SCHOOL", "High School"),
    JUNIOR_HIGH("HIGH SCHOOL", "JUNIOR High"),
    SENIOR_HIGH("HIGH SCHOOL", "SENIOR High"),
    TRS("TRS", "TRS"),
    DHMT("TRS", "DHMT"),
    HRM("TRS", "HRM"),
    OTHER_COURSE("Other Course", "");

    private final String department;
    private final String course;

    TreeNodeModel(String department, String course) {
        this.department = department;
        this.course = course;
    }

    public String getDepartment() {
        return department;
    }

    public String getCourse() {
        return course;
    }
}
