package com.jomgmi.app.model;

import java.io.Serializable;

public class Course implements Serializable {
    private String id;
    private String name;
    private String code;
    private String department;
    private String brief;
    private String careerPaths;
    private String mqaCode;
    private String applyUrl;
    private int imageRes;
    private boolean requiresMathsCredit;
    private boolean requiresScienceCredit;
    private boolean requiresBMPass;
    private boolean requiresEnglishPass;
    private int minSPMCredits;
    private boolean requiresAddMaths;
    private boolean requiresPhysics;
    private boolean requiresChemistry;
    private boolean gappOnly;
    private boolean gufpOnly;
    private boolean advancedEntry;

    public Course() {
        this.applyUrl = "https://www.gmi.edu.my/diploma-programme-admission/";
    }

    public Course(String id, String name, String code, String department, String brief,
                  String careerPaths, String mqaCode, int minSPMCredits,
                  boolean requiresMathsCredit, boolean requiresScienceCredit,
                  boolean requiresBMPass, boolean requiresEnglishPass) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.department = department;
        this.brief = brief;
        this.careerPaths = careerPaths;
        this.mqaCode = mqaCode;
        this.minSPMCredits = minSPMCredits;
        this.requiresMathsCredit = requiresMathsCredit;
        this.requiresScienceCredit = requiresScienceCredit;
        this.requiresBMPass = requiresBMPass;
        this.requiresEnglishPass = requiresEnglishPass;
        this.applyUrl = "https://www.gmi.edu.my/diploma-programme-admission/";
        this.imageRes = android.R.drawable.ic_menu_gallery;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getCareerPaths() {
        return careerPaths;
    }

    public void setCareerPaths(String careerPaths) {
        this.careerPaths = careerPaths;
    }

    public String getMqaCode() {
        return mqaCode;
    }

    public void setMqaCode(String mqaCode) {
        this.mqaCode = mqaCode;
    }

    public String getApplyUrl() {
        return applyUrl;
    }

    public void setApplyUrl(String applyUrl) {
        this.applyUrl = applyUrl;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public boolean isRequiresMathsCredit() {
        return requiresMathsCredit;
    }

    public void setRequiresMathsCredit(boolean requiresMathsCredit) {
        this.requiresMathsCredit = requiresMathsCredit;
    }

    public boolean isRequiresScienceCredit() {
        return requiresScienceCredit;
    }

    public void setRequiresScienceCredit(boolean requiresScienceCredit) {
        this.requiresScienceCredit = requiresScienceCredit;
    }

    public boolean isRequiresBMPass() {
        return requiresBMPass;
    }

    public void setRequiresBMPass(boolean requiresBMPass) {
        this.requiresBMPass = requiresBMPass;
    }

    public boolean isRequiresEnglishPass() {
        return requiresEnglishPass;
    }

    public void setRequiresEnglishPass(boolean requiresEnglishPass) {
        this.requiresEnglishPass = requiresEnglishPass;
    }

    public int getMinSPMCredits() {
        return minSPMCredits;
    }

    public void setMinSPMCredits(int minSPMCredits) {
        this.minSPMCredits = minSPMCredits;
    }

    public boolean isRequiresAddMaths() {
        return requiresAddMaths;
    }

    public void setRequiresAddMaths(boolean requiresAddMaths) {
        this.requiresAddMaths = requiresAddMaths;
    }

    public boolean isRequiresPhysics() {
        return requiresPhysics;
    }

    public void setRequiresPhysics(boolean requiresPhysics) {
        this.requiresPhysics = requiresPhysics;
    }

    public boolean isRequiresChemistry() {
        return requiresChemistry;
    }

    public void setRequiresChemistry(boolean requiresChemistry) {
        this.requiresChemistry = requiresChemistry;
    }

    public boolean isGappOnly() {
        return gappOnly;
    }

    public void setGappOnly(boolean gappOnly) {
        this.gappOnly = gappOnly;
    }

    public boolean isGufpOnly() {
        return gufpOnly;
    }

    public void setGufpOnly(boolean gufpOnly) {
        this.gufpOnly = gufpOnly;
    }

    public boolean isAdvancedEntry() {
        return advancedEntry;
    }

    public void setAdvancedEntry(boolean advancedEntry) {
        this.advancedEntry = advancedEntry;
    }
}
