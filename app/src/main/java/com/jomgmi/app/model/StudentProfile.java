package com.jomgmi.app.model;

import java.io.Serializable;

public class StudentProfile implements Serializable {
    private String qualificationType; // SPM, O_LEVEL, SVM, SKM, STPM, FOUNDATION
    private int totalCredits;
    private boolean hasMathsCredit;
    private boolean hasScienceCredit;
    private boolean hasBMPass;
    private boolean hasEnglishPass;
    private boolean hasAddMaths;
    private boolean hasPhysics;
    private boolean hasChemistry;
    private String addMathsGrade;
    private String physicsGrade;
    private String chemistryGrade;
    private String englishGrade;
    private String mathsGrade;

    public StudentProfile() {
        this.qualificationType = "SPM";
        this.totalCredits = 0;
        this.addMathsGrade = "";
        this.physicsGrade = "";
        this.chemistryGrade = "";
        this.englishGrade = "";
        this.mathsGrade = "";
    }

    public String getQualificationType() {
        return qualificationType;
    }

    public void setQualificationType(String qualificationType) {
        this.qualificationType = qualificationType;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public boolean isHasMathsCredit() {
        return hasMathsCredit;
    }

    public void setHasMathsCredit(boolean hasMathsCredit) {
        this.hasMathsCredit = hasMathsCredit;
    }

    public boolean isHasScienceCredit() {
        return hasScienceCredit;
    }

    public void setHasScienceCredit(boolean hasScienceCredit) {
        this.hasScienceCredit = hasScienceCredit;
    }

    public boolean isHasBMPass() {
        return hasBMPass;
    }

    public void setHasBMPass(boolean hasBMPass) {
        this.hasBMPass = hasBMPass;
    }

    public boolean isHasEnglishPass() {
        return hasEnglishPass;
    }

    public void setHasEnglishPass(boolean hasEnglishPass) {
        this.hasEnglishPass = hasEnglishPass;
    }

    public boolean isHasAddMaths() {
        return hasAddMaths;
    }

    public void setHasAddMaths(boolean hasAddMaths) {
        this.hasAddMaths = hasAddMaths;
    }

    public boolean isHasPhysics() {
        return hasPhysics;
    }

    public void setHasPhysics(boolean hasPhysics) {
        this.hasPhysics = hasPhysics;
    }

    public boolean isHasChemistry() {
        return hasChemistry;
    }

    public void setHasChemistry(boolean hasChemistry) {
        this.hasChemistry = hasChemistry;
    }

    public String getAddMathsGrade() {
        return addMathsGrade;
    }

    public void setAddMathsGrade(String addMathsGrade) {
        this.addMathsGrade = addMathsGrade;
    }

    public String getPhysicsGrade() {
        return physicsGrade;
    }

    public void setPhysicsGrade(String physicsGrade) {
        this.physicsGrade = physicsGrade;
    }

    public String getChemistryGrade() {
        return chemistryGrade;
    }

    public void setChemistryGrade(String chemistryGrade) {
        this.chemistryGrade = chemistryGrade;
    }

    public String getEnglishGrade() {
        return englishGrade;
    }

    public void setEnglishGrade(String englishGrade) {
        this.englishGrade = englishGrade;
    }

    public String getMathsGrade() {
        return mathsGrade;
    }

    public void setMathsGrade(String mathsGrade) {
        this.mathsGrade = mathsGrade;
    }
}
