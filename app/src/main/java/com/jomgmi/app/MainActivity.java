package com.jomgmi.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.jomgmi.app.adapter.CourseAdapter;
import com.jomgmi.app.data.CourseRepository;
import com.jomgmi.app.model.Course;
import com.jomgmi.app.model.StudentProfile;
import com.jomgmi.app.util.EligibilityEngine;
import com.jomgmi.app.util.NavigationHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "JomGMIPrefs";
    public static final String KEY_HAS_SAVED = "has_saved";
    public static final String KEY_QUALIFICATION = "qualification";
    public static final String KEY_CREDITS = "credits";
    public static final String KEY_MATHS = "maths";
    public static final String KEY_SCIENCE = "science";
    public static final String KEY_BM = "bm";
    public static final String KEY_ENGLISH = "english";
    public static final String KEY_ADD_MATHS = "add_maths";
    public static final String KEY_PHYSICS = "physics";
    public static final String KEY_CHEMISTRY = "chemistry";

    private Spinner spQualification;
    private TextInputEditText etCredits;
    private MaterialButton btnCreditMinus;
    private MaterialButton btnCreditPlus;
    private MaterialCheckBox cbMaths;
    private MaterialCheckBox cbScience;
    private MaterialCheckBox cbBm;
    private MaterialCheckBox cbEnglish;
    private LinearLayout layoutStemSubjects;
    private MaterialCheckBox cbAddMaths;
    private MaterialCheckBox cbPhysics;
    private MaterialCheckBox cbChemistry;
    private MaterialButton btnFindCourses;
    private MaterialButton btnClearGrades;

    private TextView tvMatchedCount;
    private RecyclerView rvEligibleCourses;
    private LinearLayout layoutEmptyState;
    private MaterialButton btnExploreAll;
    private BottomNavigationView bottomNavigation;

    private CourseAdapter courseAdapter;
    private final String[] qualifications = {"SPM", "O-Level", "SVM", "SKM", "STPM", "Foundation"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupSpinner();
        setupRecyclerView();
        setupListeners();
        setupNavigation();

        // Check if there are previously saved grades
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (prefs.getBoolean(KEY_HAS_SAVED, false)) {
            loadSavedProfile(prefs);
            runEligibilityCheck(false);
        } else {
            // Default initial check
            runEligibilityCheck(false);
        }
    }

    private void initViews() {
        spQualification = findViewById(R.id.spQualification);
        etCredits = findViewById(R.id.etCredits);
        btnCreditMinus = findViewById(R.id.btnCreditMinus);
        btnCreditPlus = findViewById(R.id.btnCreditPlus);
        cbMaths = findViewById(R.id.cbMaths);
        cbScience = findViewById(R.id.cbScience);
        cbBm = findViewById(R.id.cbBm);
        cbEnglish = findViewById(R.id.cbEnglish);
        layoutStemSubjects = findViewById(R.id.layoutStemSubjects);
        cbAddMaths = findViewById(R.id.cbAddMaths);
        cbPhysics = findViewById(R.id.cbPhysics);
        cbChemistry = findViewById(R.id.cbChemistry);
        btnFindCourses = findViewById(R.id.btnFindCourses);
        btnClearGrades = findViewById(R.id.btnClearGrades);

        tvMatchedCount = findViewById(R.id.tvMatchedCount);
        rvEligibleCourses = findViewById(R.id.rvEligibleCourses);
        layoutEmptyState = findViewById(R.id.layoutEmptyState);
        btnExploreAll = findViewById(R.id.btnExploreAll);
        bottomNavigation = findViewById(R.id.bottomNavigation);
    }

    private void setupSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                qualifications
        );
        spQualification.setAdapter(adapter);
    }

    private void setupRecyclerView() {
        rvEligibleCourses.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter = new CourseAdapter(this, new ArrayList<>());
        rvEligibleCourses.setAdapter(courseAdapter);
    }

    private void setupNavigation() {
        NavigationHelper.setupBottomNavigation(this, bottomNavigation, R.id.nav_home);
    }

    private void setupListeners() {
        // Spinner selection listener
        spQualification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateStemVisibility();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Credit +/- buttons
        btnCreditMinus.setOnClickListener(v -> {
            int current = parseCredits();
            if (current > 0) {
                etCredits.setText(String.valueOf(current - 1));
            }
        });

        btnCreditPlus.setOnClickListener(v -> {
            int current = parseCredits();
            if (current < 12) {
                etCredits.setText(String.valueOf(current + 1));
            }
        });

        // Watch credits changes for dynamic STEM visibility
        etCredits.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateStemVisibility();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Find My Courses button
        btnFindCourses.setOnClickListener(v -> {
            runEligibilityCheck(true);
        });

        // Clear Grades button
        btnClearGrades.setOnClickListener(v -> {
            clearGrades();
        });

        // Empty state explore all button
        btnExploreAll.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CoursesActivity.class);
            startActivity(intent);
        });
    }

    private int parseCredits() {
        String text = etCredits.getText() != null ? etCredits.getText().toString().trim() : "";
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void updateStemVisibility() {
        String selected = (String) spQualification.getSelectedItem();
        int credits = parseCredits();

        // Show/hide Add Maths, Physics, Chemistry checkboxes only when qualification type is SPM and user has 5+ credits
        if ("SPM".equalsIgnoreCase(selected) && credits >= 5) {
            layoutStemSubjects.setVisibility(View.VISIBLE);
        } else {
            layoutStemSubjects.setVisibility(View.GONE);
        }
    }

    private void runEligibilityCheck(boolean saveToPrefs) {
        int credits = parseCredits();
        String qualification = (String) spQualification.getSelectedItem();
        if (qualification == null) qualification = "SPM";

        StudentProfile profile = new StudentProfile();
        profile.setQualificationType(qualification);
        profile.setTotalCredits(credits);
        profile.setHasMathsCredit(cbMaths.isChecked());
        profile.setHasScienceCredit(cbScience.isChecked());
        profile.setHasBMPass(cbBm.isChecked());
        profile.setHasEnglishPass(cbEnglish.isChecked());

        if (layoutStemSubjects.getVisibility() == View.VISIBLE) {
            profile.setHasAddMaths(cbAddMaths.isChecked());
            profile.setHasPhysics(cbPhysics.isChecked());
            profile.setHasChemistry(cbChemistry.isChecked());
        } else {
            profile.setHasAddMaths(false);
            profile.setHasPhysics(false);
            profile.setHasChemistry(false);
        }

        List<Course> allCourses = CourseRepository.getCourses();
        List<Course> eligibleCourses = EligibilityEngine.getEligibleCourses(profile, allCourses);

        courseAdapter.updateList(eligibleCourses);

        int count = eligibleCourses.size();
        tvMatchedCount.setText(count + (count == 1 ? " Programme" : " Programmes"));

        if (count == 0) {
            layoutEmptyState.setVisibility(View.VISIBLE);
            rvEligibleCourses.setVisibility(View.GONE);
        } else {
            layoutEmptyState.setVisibility(View.GONE);
            rvEligibleCourses.setVisibility(View.VISIBLE);
        }

        if (saveToPrefs) {
            saveProfile(profile);
            Toast.makeText(this, "Grades evaluated and saved!", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveProfile(StudentProfile profile) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit()
                .putBoolean(KEY_HAS_SAVED, true)
                .putString(KEY_QUALIFICATION, profile.getQualificationType())
                .putInt(KEY_CREDITS, profile.getTotalCredits())
                .putBoolean(KEY_MATHS, profile.isHasMathsCredit())
                .putBoolean(KEY_SCIENCE, profile.isHasScienceCredit())
                .putBoolean(KEY_BM, profile.isHasBMPass())
                .putBoolean(KEY_ENGLISH, profile.isHasEnglishPass())
                .putBoolean(KEY_ADD_MATHS, profile.isHasAddMaths())
                .putBoolean(KEY_PHYSICS, profile.isHasPhysics())
                .putBoolean(KEY_CHEMISTRY, profile.isHasChemistry())
                .apply();
    }

    private void loadSavedProfile(SharedPreferences prefs) {
        String qual = prefs.getString(KEY_QUALIFICATION, "SPM");
        for (int i = 0; i < qualifications.length; i++) {
            if (qualifications[i].equalsIgnoreCase(qual)) {
                spQualification.setSelection(i);
                break;
            }
        }

        int credits = prefs.getInt(KEY_CREDITS, 5);
        etCredits.setText(String.valueOf(credits));

        cbMaths.setChecked(prefs.getBoolean(KEY_MATHS, true));
        cbScience.setChecked(prefs.getBoolean(KEY_SCIENCE, true));
        cbBm.setChecked(prefs.getBoolean(KEY_BM, true));
        cbEnglish.setChecked(prefs.getBoolean(KEY_ENGLISH, true));

        updateStemVisibility();

        cbAddMaths.setChecked(prefs.getBoolean(KEY_ADD_MATHS, false));
        cbPhysics.setChecked(prefs.getBoolean(KEY_PHYSICS, false));
        cbChemistry.setChecked(prefs.getBoolean(KEY_CHEMISTRY, false));
    }

    private void clearGrades() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().clear().apply();

        // Reset form
        spQualification.setSelection(0);
        etCredits.setText("5");
        cbMaths.setChecked(true);
        cbScience.setChecked(true);
        cbBm.setChecked(true);
        cbEnglish.setChecked(true);
        cbAddMaths.setChecked(false);
        cbPhysics.setChecked(false);
        cbChemistry.setChecked(false);

        updateStemVisibility();
        runEligibilityCheck(false);

        Toast.makeText(this, "Grades cleared", Toast.LENGTH_SHORT).show();
    }
}
