package com.jomgmi.app;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.jomgmi.app.data.CourseRepository;
import com.jomgmi.app.model.Course;
import com.jomgmi.app.util.EligibilityEngine;

public class CourseDetailActivity extends AppCompatActivity {

    public static final String EXTRA_COURSE_ID = "EXTRA_COURSE_ID";

    private MaterialToolbar toolbarDetail;
    private TextView tvDetailDeptBadge;
    private TextView tvDetailTitle;
    private TextView tvDetailRegCode;
    private TextView tvDetailMqaCode;
    private TextView tvDetailBrief;
    private TextView tvDetailRequirements;
    private TextView tvDetailCareerPaths;
    private MaterialButton btnApplyNow;

    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        initViews();

        String courseId = getIntent().getStringExtra(EXTRA_COURSE_ID);
        course = CourseRepository.getCourseById(courseId);

        if (course == null) {
            Toast.makeText(this, "Course not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        bindCourseDetails();
    }

    private void initViews() {
        toolbarDetail = findViewById(R.id.toolbarDetail);
        tvDetailDeptBadge = findViewById(R.id.tvDetailDeptBadge);
        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailRegCode = findViewById(R.id.tvDetailRegCode);
        tvDetailMqaCode = findViewById(R.id.tvDetailMqaCode);
        tvDetailBrief = findViewById(R.id.tvDetailBrief);
        tvDetailRequirements = findViewById(R.id.tvDetailRequirements);
        tvDetailCareerPaths = findViewById(R.id.tvDetailCareerPaths);
        btnApplyNow = findViewById(R.id.btnApplyNow);

        toolbarDetail.setNavigationOnClickListener(v -> finish());
    }

    private void bindCourseDetails() {
        toolbarDetail.setTitle(course.getName());
        tvDetailTitle.setText(course.getName());
        tvDetailBrief.setText(course.getBrief());
        tvDetailRegCode.setText(course.getCode() != null ? course.getCode() : "N/A");
        tvDetailMqaCode.setText(course.getMqaCode() != null ? course.getMqaCode() : "N/A");
        tvDetailCareerPaths.setText(course.getCareerPaths() != null ? course.getCareerPaths() : "N/A");

        // Format Department Badge
        String dept = course.getDepartment();
        if (dept == null) dept = "GENERAL";
        tvDetailDeptBadge.setText(dept.replace("_", " "));

        int badgeBgColor = ContextCompat.getColor(this, R.color.accent);
        int badgeTextColor = ContextCompat.getColor(this, R.color.black);

        if ("ELECTRICAL".equalsIgnoreCase(dept)) {
            badgeBgColor = ContextCompat.getColor(this, R.color.accent);
            badgeTextColor = ContextCompat.getColor(this, R.color.black);
        } else if ("MECHANICAL".equalsIgnoreCase(dept)) {
            badgeBgColor = ContextCompat.getColor(this, R.color.accent_light);
            badgeTextColor = ContextCompat.getColor(this, R.color.on_accent_container);
        } else if ("IT".equalsIgnoreCase(dept)) {
            badgeBgColor = ContextCompat.getColor(this, R.color.dept_it_bg);
            badgeTextColor = ContextCompat.getColor(this, R.color.dept_it);
        } else if ("PRE_UNIVERSITY".equalsIgnoreCase(dept)) {
            badgeBgColor = ContextCompat.getColor(this, R.color.dept_pre_u_bg);
            badgeTextColor = ContextCompat.getColor(this, R.color.dept_pre_u);
        } else if ("DEGREE".equalsIgnoreCase(dept)) {
            badgeBgColor = ContextCompat.getColor(this, R.color.dept_degree_bg);
            badgeTextColor = ContextCompat.getColor(this, R.color.dept_degree);
        }
        tvDetailDeptBadge.setBackgroundTintList(ColorStateList.valueOf(badgeBgColor));
        tvDetailDeptBadge.setTextColor(badgeTextColor);

        // Requirements Summary
        String reqs = EligibilityEngine.getRequirementSummary(course);
        tvDetailRequirements.setText(reqs);

        // Apply Now Action
        btnApplyNow.setOnClickListener(v -> {
            String url = course.getApplyUrl();
            if (url == null || url.isEmpty()) {
                url = "https://www.gmi.edu.my/diploma-programme-admission/";
            }
            try {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            } catch (Exception e) {
                Toast.makeText(this, "Could not open browser: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
