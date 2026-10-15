package com.jomgmi.app;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
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

public class EligibilityActivity extends AppCompatActivity {

    private MaterialToolbar toolbarEligibility;
    private Spinner spEligQualification;
    private TextInputEditText etEligCredits;
    private MaterialButton btnEligMinus;
    private MaterialButton btnEligPlus;
    private MaterialCheckBox cbEligMaths;
    private MaterialCheckBox cbEligScience;
    private MaterialCheckBox cbEligBm;
    private MaterialCheckBox cbEligEnglish;
    private LinearLayout layoutEligStem;
    private MaterialCheckBox cbEligAddMaths;
    private MaterialCheckBox cbEligPhysics;
    private MaterialCheckBox cbEligChemistry;
    private MaterialButton btnEligReset;
    private MaterialButton btnEligCheck;

    private TextView tvEligCount;
    private RecyclerView rvEligResults;
    private LinearLayout layoutEligEmpty;
    private BottomNavigationView bottomNavigation;

    private CourseAdapter courseAdapter;
    private final String[] qualifications = {"SPM", "O-Level", "SVM", "SKM", "STPM", "Foundation"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eligibility);

        initViews();
        setupToolbar();
        setupSpinner();
        setupRecyclerView();
        setupListeners();
        setupNavigation();

        evaluateEligibility();
    }

    private void initViews() {
        toolbarEligibility = findViewById(R.id.toolbarEligibility);
        spEligQualification = findViewById(R.id.spEligQualification);
        etEligCredits = findViewById(R.id.etEligCredits);
        btnEligMinus = findViewById(R.id.btnEligMinus);
        btnEligPlus = findViewById(R.id.btnEligPlus);
        cbEligMaths = findViewById(R.id.cbEligMaths);
        cbEligScience = findViewById(R.id.cbEligScience);
        cbEligBm = findViewById(R.id.cbEligBm);
        cbEligEnglish = findViewById(R.id.cbEligEnglish);
        layoutEligStem = findViewById(R.id.layoutEligStem);
        cbEligAddMaths = findViewById(R.id.cbEligAddMaths);
        cbEligPhysics = findViewById(R.id.cbEligPhysics);
        cbEligChemistry = findViewById(R.id.cbEligChemistry);
        btnEligReset = findViewById(R.id.btnEligReset);
        btnEligCheck = findViewById(R.id.btnEligCheck);

        tvEligCount = findViewById(R.id.tvEligCount);
        rvEligResults = findViewById(R.id.rvEligResults);
        layoutEligEmpty = findViewById(R.id.layoutEligEmpty);
        bottomNavigation = findViewById(R.id.bottomNavigation);
    }

    private void setupToolbar() {
        toolbarEligibility.setNavigationOnClickListener(v -> finish());
    }

    private void setupSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                qualifications
        );
        spEligQualification.setAdapter(adapter);
    }

    private void setupRecyclerView() {
        rvEligResults.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter = new CourseAdapter(this, new ArrayList<>());
        rvEligResults.setAdapter(courseAdapter);
    }

    private void setupNavigation() {
        NavigationHelper.setupBottomNavigation(this, bottomNavigation, R.id.nav_eligibility);
    }

    private void setupListeners() {
        spEligQualification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateStemVisibility();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnEligMinus.setOnClickListener(v -> {
            int current = parseCredits();
            if (current > 0) {
                etEligCredits.setText(String.valueOf(current - 1));
            }
        });

        btnEligPlus.setOnClickListener(v -> {
            int current = parseCredits();
            if (current < 12) {
                etEligCredits.setText(String.valueOf(current + 1));
            }
        });

        etEligCredits.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateStemVisibility();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        btnEligCheck.setOnClickListener(v -> evaluateEligibility());

        btnEligReset.setOnClickListener(v -> {
            spEligQualification.setSelection(0);
            etEligCredits.setText("5");
            cbEligMaths.setChecked(true);
            cbEligScience.setChecked(true);
            cbEligBm.setChecked(true);
            cbEligEnglish.setChecked(true);
            cbEligAddMaths.setChecked(false);
            cbEligPhysics.setChecked(false);
            cbEligChemistry.setChecked(false);
            updateStemVisibility();
            evaluateEligibility();
        });
    }

    private int parseCredits() {
        String text = etEligCredits.getText() != null ? etEligCredits.getText().toString().trim() : "";
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void updateStemVisibility() {
        String selected = (String) spEligQualification.getSelectedItem();
        int credits = parseCredits();

        if ("SPM".equalsIgnoreCase(selected) && credits >= 5) {
            layoutEligStem.setVisibility(View.VISIBLE);
        } else {
            layoutEligStem.setVisibility(View.GONE);
        }
    }

    private void evaluateEligibility() {
        int credits = parseCredits();
        String qualification = (String) spEligQualification.getSelectedItem();
        if (qualification == null) qualification = "SPM";

        StudentProfile profile = new StudentProfile();
        profile.setQualificationType(qualification);
        profile.setTotalCredits(credits);
        profile.setHasMathsCredit(cbEligMaths.isChecked());
        profile.setHasScienceCredit(cbEligScience.isChecked());
        profile.setHasBMPass(cbEligBm.isChecked());
        profile.setHasEnglishPass(cbEligEnglish.isChecked());

        if (layoutEligStem.getVisibility() == View.VISIBLE) {
            profile.setHasAddMaths(cbEligAddMaths.isChecked());
            profile.setHasPhysics(cbEligPhysics.isChecked());
            profile.setHasChemistry(cbEligChemistry.isChecked());
        } else {
            profile.setHasAddMaths(false);
            profile.setHasPhysics(false);
            profile.setHasChemistry(false);
        }

        List<Course> allCourses = CourseRepository.getCourses();
        List<Course> eligibleCourses = EligibilityEngine.getEligibleCourses(profile, allCourses);

        courseAdapter.updateList(eligibleCourses);

        int count = eligibleCourses.size();
        tvEligCount.setText(count + (count == 1 ? " Programme" : " Programmes"));

        if (count == 0) {
            layoutEligEmpty.setVisibility(View.VISIBLE);
            rvEligResults.setVisibility(View.GONE);
        } else {
            layoutEligEmpty.setVisibility(View.GONE);
            rvEligResults.setVisibility(View.VISIBLE);
        }
    }
}
