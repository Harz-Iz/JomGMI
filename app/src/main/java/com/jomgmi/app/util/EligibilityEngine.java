package com.jomgmi.app.util;

import com.jomgmi.app.model.Course;
import com.jomgmi.app.model.StudentProfile;

import java.util.ArrayList;
import java.util.List;

public class EligibilityEngine {

    public static List<Course> getEligibleCourses(StudentProfile profile, List<Course> allCourses) {
        List<Course> eligible = new ArrayList<>();
        if (profile == null || allCourses == null) {
            return eligible;
        }

        String qual = profile.getQualificationType();
        if (qual == null) qual = "SPM";

        // Normalize qualification string
        String qUpper = qual.toUpperCase();

        if (qUpper.contains("SPM")) {
            for (Course course : allCourses) {
                if ("DEGREE".equalsIgnoreCase(course.getDepartment()) || course.isAdvancedEntry()) {
                    // DEGREE (BELT): always show as advanced entry check
                    eligible.add(course);
                    continue;
                }

                if (course.isGappOnly()) {
                    if (profile.getTotalCredits() >= 5
                            && profile.isHasMathsCredit()
                            && profile.isHasAddMaths()
                            && profile.isHasPhysics()
                            && profile.isHasChemistry()
                            && profile.isHasEnglishPass()) {
                        eligible.add(course);
                    }
                } else if (course.isGufpOnly()) {
                    if (profile.getTotalCredits() >= 6
                            && profile.isHasMathsCredit()
                            && profile.isHasAddMaths()
                            && profile.isHasPhysics()
                            && profile.isHasChemistry()
                            && profile.isHasBMPass()
                            && profile.isHasEnglishPass()) {
                        eligible.add(course);
                    }
                } else {
                    if (profile.getTotalCredits() >= course.getMinSPMCredits()
                            && (!course.isRequiresMathsCredit() || profile.isHasMathsCredit())
                            && (!course.isRequiresScienceCredit() || profile.isHasScienceCredit())
                            && (!course.isRequiresBMPass() || profile.isHasBMPass())
                            && (!course.isRequiresEnglishPass() || profile.isHasEnglishPass())) {
                        eligible.add(course);
                    }
                }
            }
        } else if (qUpper.contains("O_LEVEL") || qUpper.contains("O-LEVEL") || qUpper.contains("O LEVEL")) {
            for (Course course : allCourses) {
                if ("DEGREE".equalsIgnoreCase(course.getDepartment()) || course.isAdvancedEntry()) {
                    continue;
                }
                if (course.isGappOnly()) {
                    if (profile.getTotalCredits() >= 5
                            && profile.isHasMathsCredit()
                            && profile.isHasAddMaths()
                            && profile.isHasPhysics()
                            && profile.isHasChemistry()
                            && profile.isHasEnglishPass()) {
                        eligible.add(course);
                    }
                } else if (course.isGufpOnly()) {
                    if (profile.getTotalCredits() >= 6
                            && profile.isHasMathsCredit()
                            && profile.isHasAddMaths()
                            && profile.isHasPhysics()
                            && profile.isHasChemistry()
                            && profile.isHasEnglishPass()) {
                        eligible.add(course);
                    }
                } else {
                    // O-Level: similar logic but no BM pass requirement; min 3 credits
                    if (profile.getTotalCredits() >= 3
                            && (!course.isRequiresMathsCredit() || profile.isHasMathsCredit())
                            && (!course.isRequiresScienceCredit() || profile.isHasScienceCredit())
                            && (!course.isRequiresEnglishPass() || profile.isHasEnglishPass())) {
                        eligible.add(course);
                    }
                }
            }
        } else if (qUpper.contains("SVM") || qUpper.contains("SKM")) {
            // SVM or SKM: show all diploma programmes with SVM/SKM pathway note
            for (Course course : allCourses) {
                if (!"DEGREE".equalsIgnoreCase(course.getDepartment()) && !course.isGappOnly() && !course.isGufpOnly()) {
                    eligible.add(course);
                }
            }
        } else if (qUpper.contains("STPM") || qUpper.contains("FOUNDATION")) {
            // STPM or FOUNDATION: show all diploma programmes + BELT degree
            for (Course course : allCourses) {
                if (!course.isGappOnly() && !course.isGufpOnly()) {
                    eligible.add(course);
                }
            }
        } else {
            // Default fallback
            for (Course course : allCourses) {
                if (profile.getTotalCredits() >= course.getMinSPMCredits()) {
                    eligible.add(course);
                }
            }
        }

        return eligible;
    }

    public static String getRequirementSummary(Course course) {
        if (course == null) return "";
        if ("DEGREE".equalsIgnoreCase(course.getDepartment()) || course.isAdvancedEntry()) {
            return "Relevant Diploma with min CGPA 2.00 OR STPM / Matriculation / Foundation with min CGPA 2.50.";
        }
        if (course.isGappOnly()) {
            return "SPM with min 5 credits: Grade A (MARA) or Grade C (Private) including Add Maths, Physics, Chemistry, Maths & English pass.";
        }
        if (course.isGufpOnly()) {
            return "SPM with min 6 credits including Add Maths, Physics, Chemistry, Maths, BM pass and English pass.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Min ").append(course.getMinSPMCredits()).append(" SPM/O-Level credits");
        if (course.isRequiresMathsCredit()) {
            sb.append(" including Mathematics");
        }
        if (course.isRequiresScienceCredit()) {
            sb.append(" and Science/Technical subject");
        }
        if (course.isRequiresBMPass() && course.isRequiresEnglishPass()) {
            sb.append(", plus Pass in BM and English");
        } else if (course.isRequiresBMPass()) {
            sb.append(", plus Pass in BM");
        } else if (course.isRequiresEnglishPass()) {
            sb.append(", plus Pass in English");
        }
        sb.append(".");
        return sb.toString();
    }
}
