package com.jomgmi.app;

import com.jomgmi.app.data.CourseRepository;
import com.jomgmi.app.model.Course;
import com.jomgmi.app.model.StudentProfile;
import com.jomgmi.app.util.EligibilityEngine;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EligibilityEngineTest {

    @Test
    public void testCourseRepositoryCount() {
        List<Course> courses = CourseRepository.getCourses();
        assertNotNull(courses);
        assertEquals(18, courses.size());
    }

    @Test
    public void testSPMStandardDiplomaEligibility() {
        StudentProfile profile = new StudentProfile();
        profile.setQualificationType("SPM");
        profile.setTotalCredits(3);
        profile.setHasMathsCredit(true);
        profile.setHasScienceCredit(true);
        profile.setHasBMPass(true);
        profile.setHasEnglishPass(true);

        List<Course> eligible = EligibilityEngine.getEligibleCourses(profile, CourseRepository.getCourses());
        assertNotNull(eligible);
        // Should include all standard diplomas + BELT (degree advanced entry flag)
        // Should NOT include GAPP (min 5 credits + STEM) or GUFP (min 6 credits + STEM)
        boolean hasGapp = false;
        boolean hasGufp = false;
        boolean hasMechatronics = false;
        for (Course c : eligible) {
            if ("PU01".equals(c.getId())) hasGapp = true;
            if ("PU02".equals(c.getId())) hasGufp = true;
            if ("EE01".equals(c.getId())) hasMechatronics = true;
        }
        assertFalse("GAPP should not be eligible with 3 credits", hasGapp);
        assertFalse("GUFP should not be eligible with 3 credits", hasGufp);
        assertTrue("Mechatronics should be eligible with 3 credits + Maths & Science", hasMechatronics);
    }

    @Test
    public void testGappEligibility() {
        StudentProfile profile = new StudentProfile();
        profile.setQualificationType("SPM");
        profile.setTotalCredits(5);
        profile.setHasMathsCredit(true);
        profile.setHasScienceCredit(true);
        profile.setHasBMPass(false); // GAPP does not require BM pass
        profile.setHasEnglishPass(true);
        profile.setHasAddMaths(true);
        profile.setHasPhysics(true);
        profile.setHasChemistry(true);

        List<Course> eligible = EligibilityEngine.getEligibleCourses(profile, CourseRepository.getCourses());
        boolean hasGapp = false;
        for (Course c : eligible) {
            if ("PU01".equals(c.getId())) hasGapp = true;
        }
        assertTrue("GAPP should be eligible with 5 credits and STEM subjects", hasGapp);
    }

    @Test
    public void testGufpEligibility() {
        StudentProfile profile = new StudentProfile();
        profile.setQualificationType("SPM");
        profile.setTotalCredits(6);
        profile.setHasMathsCredit(true);
        profile.setHasScienceCredit(true);
        profile.setHasBMPass(true);
        profile.setHasEnglishPass(true);
        profile.setHasAddMaths(true);
        profile.setHasPhysics(true);
        profile.setHasChemistry(true);

        List<Course> eligible = EligibilityEngine.getEligibleCourses(profile, CourseRepository.getCourses());
        boolean hasGufp = false;
        for (Course c : eligible) {
            if ("PU02".equals(c.getId())) hasGufp = true;
        }
        assertTrue("GUFP should be eligible with 6 credits, BM pass and STEM subjects", hasGufp);
    }

    @Test
    public void testCreativeMultimediaNoMathsCredit() {
        StudentProfile profile = new StudentProfile();
        profile.setQualificationType("SPM");
        profile.setTotalCredits(3);
        profile.setHasMathsCredit(false);
        profile.setHasScienceCredit(false);
        profile.setHasBMPass(true);
        profile.setHasEnglishPass(true);

        List<Course> eligible = EligibilityEngine.getEligibleCourses(profile, CourseRepository.getCourses());
        boolean hasMultimedia = false;
        boolean hasMechatronics = false;
        for (Course c : eligible) {
            if ("IT02".equals(c.getId())) hasMultimedia = true;
            if ("EE01".equals(c.getId())) hasMechatronics = true;
        }
        assertTrue("Creative Multimedia should be eligible without Maths/Science credit", hasMultimedia);
        assertFalse("Mechatronics should not be eligible without Maths/Science credit", hasMechatronics);
    }
}
