package com.jomgmi.app;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.ChipGroup;
import com.jomgmi.app.adapter.CourseAdapter;
import com.jomgmi.app.data.CourseRepository;
import com.jomgmi.app.model.Course;
import com.jomgmi.app.util.NavigationHelper;

import java.util.ArrayList;
import java.util.List;

public class CoursesActivity extends AppCompatActivity {

    private MaterialToolbar toolbarCourses;
    private EditText etSearchQuery;
    private ImageView ivClearSearch;
    private ChipGroup chipGroupDepartment;
    private TextView tvExploreCount;
    private RecyclerView rvExploreCourses;
    private LinearLayout layoutNoResults;
    private BottomNavigationView bottomNavigation;

    private CourseAdapter courseAdapter;
    private List<Course> allCourses;
    private String selectedDepartment = "ALL";
    private String currentSearchQuery = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        initViews();
        setupToolbar();
        setupRecyclerView();
        setupFilterChips();
        setupSearch();
        setupNavigation();

        allCourses = CourseRepository.getCourses();
        filterCourses();
    }

    private void initViews() {
        toolbarCourses = findViewById(R.id.toolbarCourses);
        etSearchQuery = findViewById(R.id.etSearchQuery);
        ivClearSearch = findViewById(R.id.ivClearSearch);
        chipGroupDepartment = findViewById(R.id.chipGroupDepartment);
        tvExploreCount = findViewById(R.id.tvExploreCount);
        rvExploreCourses = findViewById(R.id.rvExploreCourses);
        layoutNoResults = findViewById(R.id.layoutNoResults);
        bottomNavigation = findViewById(R.id.bottomNavigation);
    }

    private void setupToolbar() {
        toolbarCourses.setNavigationOnClickListener(v -> finish());
    }

    private void setupRecyclerView() {
        rvExploreCourses.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter = new CourseAdapter(this, new ArrayList<>());
        rvExploreCourses.setAdapter(courseAdapter);
    }

    private void setupNavigation() {
        NavigationHelper.setupBottomNavigation(this, bottomNavigation, R.id.nav_explore);
    }

    private void setupFilterChips() {
        chipGroupDepartment.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.isEmpty()) {
                selectedDepartment = "ALL";
            } else {
                int id = checkedIds.get(0);
                if (id == R.id.chipElectrical) {
                    selectedDepartment = "ELECTRICAL";
                } else if (id == R.id.chipMechanical) {
                    selectedDepartment = "MECHANICAL";
                } else if (id == R.id.chipIt) {
                    selectedDepartment = "IT";
                } else if (id == R.id.chipPreU) {
                    selectedDepartment = "PRE_UNIVERSITY";
                } else if (id == R.id.chipDegree) {
                    selectedDepartment = "DEGREE";
                } else if (id == R.id.chipGdvt) {
                    selectedDepartment = "GDVT";
                } else {
                    selectedDepartment = "ALL";
                }
            }
            filterCourses();
        });
    }

    private void setupSearch() {
        etSearchQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentSearchQuery = s.toString().trim();
                ivClearSearch.setVisibility(currentSearchQuery.isEmpty() ? View.GONE : View.VISIBLE);
                filterCourses();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        ivClearSearch.setOnClickListener(v -> {
            etSearchQuery.setText("");
        });
    }

    private void filterCourses() {
        if (allCourses == null) return;

        List<Course> filtered = new ArrayList<>();
        String queryLower = currentSearchQuery.toLowerCase();

        for (Course course : allCourses) {
            boolean matchesDept = "ALL".equalsIgnoreCase(selectedDepartment)
                    || (course.getDepartment() != null && course.getDepartment().equalsIgnoreCase(selectedDepartment));

            boolean matchesSearch = queryLower.isEmpty()
                    || (course.getName() != null && course.getName().toLowerCase().contains(queryLower))
                    || (course.getBrief() != null && course.getBrief().toLowerCase().contains(queryLower))
                    || (course.getCareerPaths() != null && course.getCareerPaths().toLowerCase().contains(queryLower))
                    || (course.getId() != null && course.getId().toLowerCase().contains(queryLower))
                    || (course.getCode() != null && course.getCode().toLowerCase().contains(queryLower));

            if (matchesDept && matchesSearch) {
                filtered.add(course);
            }
        }

        courseAdapter.updateList(filtered);

        int count = filtered.size();
        tvExploreCount.setText("Showing " + count + (count == 1 ? " programme" : " programmes"));

        if (count == 0) {
            layoutNoResults.setVisibility(View.VISIBLE);
            rvExploreCourses.setVisibility(View.GONE);
        } else {
            layoutNoResults.setVisibility(View.GONE);
            rvExploreCourses.setVisibility(View.VISIBLE);
        }
    }
}
