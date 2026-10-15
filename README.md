# JomGMI — GMI Course & Eligibility Finder

An Android application designed for prospective students (SPM, O-Level, SVM, SKM, STPM, and Foundation leavers) to discover academic programmes at the **German-Malaysian Institute (GMI)** they qualify for based on their grades.

---

## Key Features

- **Automated Eligibility Engine**:
  - Automatically matches programmes based on qualification type, total credits, and core subjects (Mathematics, Science, BM, and English).
  - Dynamic STEM evaluation: reveals Additional Mathematics, Physics, and Chemistry fields when evaluating SPM with 5+ credits (required for German Pre-U GAPP and UTP Foundation GUFP).
- **Instant Home Discovery**:
  - Automatically computes and renders matched programmes as cards upon grade selection without manual searching.
  - Automatically persists user profile with `SharedPreferences` across sessions.
- **Programme Exploration**:
  - Searchable by name, brief, course codes, and career outcomes.
  - Filterable by department: Electrical, Mechanical, IT & Multimedia, Pre-University, and Degree.
- **Detailed Programme Profiles**:
  - Displays MQA accreditation, registration codes, detailed syllabus briefs, entry requirements, and career paths.
  - Direct link to official admission portals.
- **Dedicated Standalone Eligibility Checker**:
  - Standalone calculator for testing different hypothetical grade combinations.
- **About & Contact GMI**:
  - Institute profile and interactive contact actions (one-tap dialing, email enquiry, website navigation, and Google Maps directions).

---

## Technical Specifications

- **Language**: Java 11
- **Minimum SDK**: API 24 (Android 7.0 Nougat)
- **Target SDK**: API 34 (Android 14)
- **Architecture**: Native Android Activities + Material Design 3 Components
- **Color Theme**:
  - Primary: `#003087` (GMI Dark Blue)
  - Accent: `#FFB800` (Gold / Yellow)
  - Background: Clean White / Light (`#F7F9FC` & `#FFFFFF`)

---

## Project Structure

```
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
```

---

## Building and Running

1. Clone or open the project folder in **Android Studio** (Hedgehog or newer).
2. Allow Gradle sync to complete.
3. To run unit tests:
   ```bash
   ./gradlew test
   ```
4. To build the debug APK:
   ```bash
   ./gradlew assembleDebug
   ```

---

## License

This project is licensed under the [MIT License](LICENSE).
