package com.jimboyz.cims.model;

import java.io.File;
import java.util.ArrayList;

public class StudentImageFile extends ArrayList<File> {

    private static StudentImageFile instance = null;

    private StudentImageFile() {}

    public static StudentImageFile getInstance() {
        if (instance == null) {
            instance = new StudentImageFile();
        }

        return instance;
    }

    public void setImageFile(File filename) {
        this.removeAll(this);
        this.add(filename);
    }

    public File getImageFile() {

        int index = this.size() -1;
        if(index >= 0) {
            return this.get(index);
        }

        return null;
    }

    public void removeImageFile() {

        int index = this.size() -1;
        if(index >= 0) {
            this.remove(index);
        }
    }
}
