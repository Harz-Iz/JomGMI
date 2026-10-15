package com.jomgmi.app.data;

import com.jomgmi.app.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository {

    public static List<Course> getCourses() {
        List<Course> list = new ArrayList<>();

        // 1. EE01
        Course ee01 = new Course("EE01",
                "Diploma of Mechatronics Engineering Technology",
                "R3/0713/4/0041",
                "ELECTRICAL",
                "Focuses on PLCs, industrial robotics, HMI, CIM, and IIoT systems. Bridges mechanical, electrical, and computer engineering.",
                "Automation Technical Specialist, ROV Supervisor, Automation System Designer",
                "MQA/FA 14611",
                3, true, true, true, true);
        list.add(ee01);

        // 2. EE02
        Course ee02 = new Course("EE02",
                "Diploma of Engineering Technology (Instrumentation & Control)",
                "R3/0741/4/0012",
                "ELECTRICAL",
                "Covers distributed control systems (DCS), SCADA, process variable measurement, calibration, and industrial process instrumentation.",
                "Control & Instrumentation Technologist, Plant Commissioning Specialist",
                "MQA/FA 14612",
                3, true, true, true, true);
        list.add(ee02);

        // 3. EE03
        Course ee03 = new Course("EE03",
                "Diploma of Electronics Engineering Technology (Computer)",
                "R3/0741/4/0013",
                "ELECTRICAL",
                "Covers embedded controllers, microprocessors, FPGA, network administration, RF & broadband systems.",
                "Embedded Systems Technologist, Telecommunication Technologist, System Programmer",
                "MQA/FA 14613",
                3, true, true, true, true);
        list.add(ee03);

        // 4. EE04
        Course ee04 = new Course("EE04",
                "Diploma in Autotronics Engineering Technology",
                "R2/0741/4/0009",
                "ELECTRICAL",
                "Specialises in ECU, CAN/LIN bus, hybrid & electric vehicle powertrains, and electronic transmission systems.",
                "Autotronics Specialist, EV Diagnostics Assistant Engineer, Automotive R&D Assistant",
                "MQA/FA 4936",
                3, true, true, true, true);
        list.add(ee04);

        // 5. EE05
        Course ee05 = new Course("EE05",
                "Diploma in Engineering Technology (Sustainable Energy & Power Distribution)",
                "R2/522/4/0017",
                "ELECTRICAL",
                "Covers solar PV engineering, wind & hydro turbines, biomass conversion, energy auditing, and power distribution.",
                "Sustainable Energy Technologist, Solar PV Project Engineer, Energy Management Consultant",
                "MQA/FA 1410",
                3, true, true, true, true);
        list.add(ee05);

        // 6. ME01
        Course me01 = new Course("ME01",
                "Diploma of Mechanical Engineering Technology (CNC Precision)",
                "R3/0714/4/0025",
                "MECHANICAL",
                "Trains students in multi-axis CNC milling (3-axis & 5-axis), CNC turning, CATIA, PowerMill, and EDM machining.",
                "CNC Programmer/Machinist, CAD/CAM Specialist, Tooling Application Engineer",
                "MQA/FA 14870",
                3, true, true, true, true);
        list.add(me01);

        // 7. ME02
        Course me02 = new Course("ME02",
                "Diploma of Mechanical Engineering Technology (Manufacturing)",
                "R2/0714/4/0011",
                "MECHANICAL",
                "Covers flexible manufacturing systems (FMS), CIMS, automated assembly, pneumatics/hydraulics, and production planning.",
                "Manufacturing Assistant Engineer, Industrial Production Supervisor, Automated Production Technologist",
                "MQA/FA 14871",
                3, true, true, true, true);
        list.add(me02);

        // 8. ME03
        Course me03 = new Course("ME03",
                "Diploma in Precision Tooling Engineering Technology",
                "R2/0714/4/0013",
                "MECHANICAL",
                "Specialises in press die design, sheet metal forming, plastic injection mold tooling, and advanced CAD/CAM.",
                "Tool & Die Specialist, Tooling Design Engineer, Mold Fabrication Specialist",
                "A 5005",
                3, true, true, true, true);
        list.add(me03);

        // 9. ME04
        Course me04 = new Course("ME04",
                "Diploma in Industrial Quality Engineering Technology",
                "R/0741/4/0001",
                "MECHANICAL",
                "Covers GD&T, coordinate measuring machines (CMM), TQM, Lean Six Sigma Green Belt, and quality auditing.",
                "QA Assistant Engineer, QC Supervisor, Metrology Lab Technician, Continuous Improvement Lead",
                "MQA/FA 10032",
                3, true, true, true, true);
        list.add(me04);

        // 10. ME05
        Course me05 = new Course("ME05",
                "Diploma in Engineering Technology (Industrial Design)",
                "R2/0741/4/0010",
                "MECHANICAL",
                "Focuses on conceptual ideation, 3D digital styling & visualization, transportation design, furniture systems, and rapid prototyping.",
                "Industrial Product Designer, Transportation Design Technologist, 3D Visualizer",
                "MQA/FA 4785",
                3, true, false, true, true);
        list.add(me05);

        // 11. ME06
        Course me06 = new Course("ME06",
                "Diploma in Innovative Product Design Engineering Technology",
                "R2/0741/4/0006",
                "MECHANICAL",
                "Covers design for manufacturing & assembly (DFMA), industrial ergonomics, reverse engineering, rapid tooling, and plastic product design.",
                "Product Development Assistant Engineer, CAD/CAE Application Specialist, Quality Assurance Executive",
                "A 7165",
                3, true, true, true, true);
        list.add(me06);

        // 12. ME07
        Course me07 = new Course("ME07",
                "Diploma in Engineering Technology (Machine Tools Maintenance)",
                "R2/0741/4/0008",
                "MECHANICAL",
                "Covers preventive machine maintenance, electromechanical fault diagnostics, machine tool calibration, retrofitting & overhaul, and TPM.",
                "Plant Maintenance Assistant Engineer, Machine Retrofitting Specialist, Precision Field Service Engineer",
                "MQA/FA 4786",
                3, true, true, true, true);
        list.add(me07);

        // 13. IT01
        Course it01 = new Course("IT01",
                "Diploma in Software Engineering",
                "R/0612/4/0007",
                "IT",
                "Covers software verification & testing, secure OOP, mobile (Android) computing, and applied AI development.",
                "Software Quality Assurance Engineer, Test Automation Engineer, Application Developer, Systems Analyst",
                "MQA/FA 9541",
                3, true, false, true, true);
        list.add(it01);

        // 14. IT02
        Course it02 = new Course("IT02",
                "Diploma in Creative Multimedia",
                "R2/0211/4/0171",
                "IT",
                "Covers 2D/3D animation, digital video/audio post-production, UI/UX design, game asset pipelines, and dynamic web programming.",
                "2D/3D Digital Animator, Interactive UI/UX Designer, Web Content Technologist, Digital Video Editor",
                "MQA/FA 6035",
                3, false, false, true, true);
        list.add(it02);

        // 15. IT03
        Course it03 = new Course("IT03",
                "Diploma in Cyber Security Technology",
                "R3/0613/4/0043",
                "IT",
                "Covers network defense architecture, penetration testing, ethical hacking, digital forensics, cryptographic standards, and cloud security.",
                "Cyber Security Specialist, SOC Analyst, Network Security Administrator, Vulnerability Assessment Associate",
                "MQA/FA15748-01",
                3, true, false, true, true);
        list.add(it03);

        // 16. PU01
        Course pu01 = new Course("PU01",
                "German University Preparatory Programme (GAPP)",
                "R3/010/3/0447",
                "PRE_UNIVERSITY",
                "A 2-year programme at GMI + 6 months in Germany. Prepares students for German Fachhochschule (Universities of Applied Sciences) via Cambridge A-Levels and TestDaF German language qualifications.",
                "Bachelor of Engineering at German Universities of Applied Sciences",
                "A 7643",
                5, true, true, false, true);
        pu01.setRequiresAddMaths(true);
        pu01.setRequiresPhysics(true);
        pu01.setRequiresChemistry(true);
        pu01.setGappOnly(true);
        list.add(pu01);

        // 17. PU02
        Course pu02 = new Course("PU02",
                "GMI–UTP Foundation Programme (GUFP)",
                "N/010/3/0537",
                "PRE_UNIVERSITY",
                "A 1-year foundation programme in partnership with UTP. Covers engineering physics, calculus, chemistry, and algorithmic thinking. Guarantees articulation into UTP engineering degree programmes.",
                "Bachelor of Engineering at Universiti Teknologi PETRONAS (UTP)",
                "MQA/FA 13871",
                6, true, true, true, true);
        pu02.setRequiresAddMaths(true);
        pu02.setRequiresPhysics(true);
        pu02.setRequiresChemistry(true);
        pu02.setGufpOnly(true);
        list.add(pu02);

        // 18. DEG01
        Course deg01 = new Course("DEG01",
                "Bachelor of Electrical Engineering Technology with Honours (BELT)",
                "MQA/FA 9760",
                "DEGREE",
                "A degree programme in partnership with UTeM. Covers industrial automation, robotics, embedded computing, and electromagnetic field theory. Entry requires a relevant Diploma with min CGPA 2.00 or STPM/Matriculation with CGPA 2.50.",
                "Electrical Engineer, Automation Engineer, Robotics Engineer, Embedded Systems Developer",
                "MQA/FA 9760",
                0, false, false, false, false);
        deg01.setApplyUrl("https://www.gmi.edu.my/degree-programme-admission/");
        deg01.setAdvancedEntry(true);
        list.add(deg01);

        return list;
    }

    public static Course getCourseById(String id) {
        if (id == null) return null;
        for (Course course : getCourses()) {
            if (id.equalsIgnoreCase(course.getId())) {
                return course;
            }
        }
        return null;
    }
}
