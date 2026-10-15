# JomGMI — GMI Course & Eligibility Finder

An Android application developed for **SPG 0562 Android Programming (PBL Assignment 2)** designed for prospective students (SPM, O-Level, SVM, SKM, STPM, and Foundation leavers) to discover academic programmes at the **German-Malaysian Institute (GMI)** they qualify for based on their grades.

---

## Key Features

- **Automated Eligibility Engine (`EligibilityEngine`)**:
  - Evaluates and matches programmes based on qualification type, total credits/passes, and core subjects (Mathematics, Science/Technical, Bahasa Melayu, and English).
  - **Dynamic STEM Evaluation**: Automatically reveals Additional Mathematics, Physics, and Chemistry checkboxes when evaluating SPM with 5+ credits (required for the German University Preparatory Programme — GAPP and GMI–UTP Foundation Programme — GUFP).
  - **Strict Qualification Filtering**: Prevents false-positive matches (e.g., returns 0 matches when credits are `0` or subjects are unchecked, and restricts the Bachelor of Electrical Engineering Technology (BELT) Degree programme strictly to STPM/Foundation/Diploma pathways rather than SPM/O-Level/SVM/SKM).
- **Home Dashboard & Persistent Recommendations (`MainActivity`)**:
  - Defaults to `3` credits with all core subject checkboxes unchecked on initial launch or reset.
  - Computes and renders matched programmes as interactive cards and saves the student's profile locally using `SharedPreferences` (`JomGMIPrefs`) so personalized suggestions remain visible across sessions.
- **Unfiltered Programme Exploration (`CoursesActivity`)**:
  - Browse all 18 GMI programmes without grade restrictions.
  - Real-time keyword search by programme name, brief, course code, MQA code, or career paths.
  - Filterable by department chips: All, Electrical, Mechanical, IT & Multimedia, Pre-University, Degree, and GDVT.
- **Detailed Programme Profiles & Admission Redirection (`CourseDetailActivity`)**:
  - Displays department badges, Registration Codes, MQA Accreditation numbers, programme overview briefs, entry requirements summary, and career opportunities.
  - Includes a sticky **"Apply Now"** button that redirects eligible prospects directly to the official GMI online admission portal via an implicit browser `Intent`.
- **Standalone Eligibility Checker (`EligibilityActivity`)**:
  - Dedicated sandbox calculator starting at `0` credits and all subjects unchecked for testing different hypothetical qualification and grade scenarios without overwriting saved home dashboard preferences.
- **About & Contact GMI (`AboutContactActivity`)**:
  - Displays GMI's institutional background and official Kajang campus address (*Jalan Ilmiah, Taman Universiti, 43000, Kajang, Selangor, Malaysia*).
  - Interactive one-tap Implicit `Intents` for calling GMI (`ACTION_DIAL`), sending an email enquiry (`ACTION_SENDTO`), visiting the official website (`ACTION_VIEW`), and launching Google Maps directions to the Kajang campus.

---

## Technical Specifications

- **Language**: Java 11
- **Android Gradle Plugin (AGP)**: 9.2.1
- **Minimum SDK**: API 24 (Android 7.0 Nougat)
- **Target SDK**: API 34 (Android 14)
- **Architecture**: Multi-Activity Android Architecture + Material Design 3 (`Theme.Material3.Light.NoActionBar` enforced across day/night configurations for high-contrast readability)
- **Color Theme & Branding**:
  - Primary: `#003087` (GMI Corporate Navy Blue)
  - Accent: `#FFB800` (GMI Gold / Yellow)
  - Background & Surface: Clean Light/White (`#F7F9FC` & `#FFFFFF`) with high-contrast typography (`#101828` primary, `#344054` secondary)

---

## Project Structure

```text
JomGMI/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/jomgmi/app/
│   │   │   │   ├── adapter/
│   │   │   │   │   └── CourseAdapter.java
│   │   │   │   ├── data/
│   │   │   │   │   └── CourseRepository.java
│   │   │   │   ├── model/
│   │   │   │   │   ├── Course.java
│   │   │   │   │   └── StudentProfile.java
│   │   │   │   ├── util/
│   │   │   │   │   ├── EligibilityEngine.java
│   │   │   │   │   └── NavigationHelper.java
│   │   │   │   ├── AboutContactActivity.java
│   │   │   │   ├── CourseDetailActivity.java
│   │   │   │   ├── CoursesActivity.java
│   │   │   │   ├── EligibilityActivity.java
│   │   │   │   ├── MainActivity.java
│   │   │   │   └── SplashActivity.java
│   │   │   ├── res/
│   │   │   │   ├── drawable/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_about_contact.xml
│   │   │   │   │   ├── activity_course_detail.xml
│   │   │   │   │   ├── activity_courses.xml
│   │   │   │   │   ├── activity_eligibility.xml
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   ├── activity_splash.xml
│   │   │   │   │   └── item_course_card.xml
│   │   │   │   ├── menu/
│   │   │   │   │   └── bottom_nav_menu.xml
│   │   │   │   ├── mipmap/
│   │   │   │   └── values/
│   │   │   │       ├── colors.xml
│   │   │   │       ├── strings.xml
│   │   │   │       └── themes.xml
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   │       └── java/com/jomgmi/app/
│   │           └── EligibilityEngineTest.java
│   └── build.gradle.kts
├── gradle/
├── build.gradle.kts
├── gradle.properties
├── settings.gradle.kts
├── .gitignore
├── LICENSE
└── README.md