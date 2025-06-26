package com.jimboyz.cims.view;

import java.util.ArrayList;
import java.util.List;

public class ConstraintsList extends ArrayList<String> {

    private static ConstraintsList cl;

    private ConstraintsList() {}

    public static ConstraintsList getInstance() {
        if(cl == null) {
            cl = new ConstraintsList();
        }

        return cl;
    }

    public List<String> getAllConstrainstList() {
        return this;
    }

    public void addConstraintsNameToList(String constraintsName) {
        this.add(constraintsName);
    }

    public String getConstraintsNameByLastIndex() {
        return this.get(super.size() - 1);
    }
}
