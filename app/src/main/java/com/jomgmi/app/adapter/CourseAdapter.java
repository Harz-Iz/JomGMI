package com.jomgmi.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.jomgmi.app.CourseDetailActivity;
import com.jomgmi.app.R;
import com.jomgmi.app.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private final Context context;
    private List<Course> courseList;

    public CourseAdapter(Context context, List<Course> courseList) {
        this.context = context;
        this.courseList = courseList != null ? courseList : new ArrayList<>();
    }

    public void updateList(List<Course> newList) {
        this.courseList = newList != null ? newList : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_card, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);

        holder.tvCourseName.setText(course.getName());
        holder.tvCourseBrief.setText(course.getBrief());
        holder.tvCourseCode.setText(course.getId() + " • " + course.getCode());
        holder.tvMqaCode.setText("MQA: " + (course.getMqaCode() != null ? course.getMqaCode() : "N/A"));

        // Format department name and styling
        String dept = course.getDepartment();
        if (dept == null) dept = "GENERAL";

        int colorPrimary = ContextCompat.getColor(context, R.color.primary);
        int colorBg = ContextCompat.getColor(context, R.color.primary_container);

        String deptDisplay = dept;
        if ("ELECTRICAL".equalsIgnoreCase(dept)) {
            deptDisplay = "ELECTRICAL";
            colorPrimary = ContextCompat.getColor(context, R.color.dept_electrical);
            colorBg = ContextCompat.getColor(context, R.color.dept_electrical_bg);
        } else if ("MECHANICAL".equalsIgnoreCase(dept)) {
            deptDisplay = "MECHANICAL";
            colorPrimary = ContextCompat.getColor(context, R.color.dept_mechanical);
            colorBg = ContextCompat.getColor(context, R.color.dept_mechanical_bg);
        } else if ("IT".equalsIgnoreCase(dept)) {
            deptDisplay = "IT & MULTIMEDIA";
            colorPrimary = ContextCompat.getColor(context, R.color.dept_it);
            colorBg = ContextCompat.getColor(context, R.color.dept_it_bg);
        } else if ("PRE_UNIVERSITY".equalsIgnoreCase(dept)) {
            deptDisplay = "PRE-UNIVERSITY";
            colorPrimary = ContextCompat.getColor(context, R.color.dept_pre_u);
            colorBg = ContextCompat.getColor(context, R.color.dept_pre_u_bg);
        } else if ("DEGREE".equalsIgnoreCase(dept)) {
            deptDisplay = "DEGREE";
            colorPrimary = ContextCompat.getColor(context, R.color.dept_degree);
            colorBg = ContextCompat.getColor(context, R.color.dept_degree_bg);
        }

        holder.tvDepartmentBadge.setText(deptDisplay);
        holder.tvDepartmentBadge.setTextColor(colorPrimary);
        holder.tvDepartmentBadge.setBackgroundTintList(ColorStateList.valueOf(colorBg));
        holder.viewDeptIndicator.setBackgroundColor(colorPrimary);

        // Advanced Entry / Special Notice
        if ("DEGREE".equalsIgnoreCase(dept) || course.isAdvancedEntry()) {
            holder.tvCourseNote.setVisibility(View.VISIBLE);
            holder.tvCourseNote.setText("Advanced Entry: Diploma / STPM / Foundation required");
            holder.tvCourseNote.setBackgroundColor(Color.parseColor("#FEF3C7"));
            holder.tvCourseNote.setTextColor(Color.parseColor("#B45309"));
        } else if (course.isGappOnly()) {
            holder.tvCourseNote.setVisibility(View.VISIBLE);
            holder.tvCourseNote.setText("Germany Pathway: Min 5 credits incl. Add Maths, Phys, Chem");
            holder.tvCourseNote.setBackgroundColor(Color.parseColor("#EDE9FE"));
            holder.tvCourseNote.setTextColor(Color.parseColor("#6D28D9"));
        } else if (course.isGufpOnly()) {
            holder.tvCourseNote.setVisibility(View.VISIBLE);
            holder.tvCourseNote.setText("UTP Foundation: Min 6 credits incl. Add Maths, Phys, Chem");
            holder.tvCourseNote.setBackgroundColor(Color.parseColor("#E0F2FE"));
            holder.tvCourseNote.setTextColor(Color.parseColor("#0369A1"));
        } else {
            holder.tvCourseNote.setVisibility(View.GONE);
        }

        // Click listeners to open CourseDetailActivity
        View.OnClickListener openDetails = v -> {
            Intent intent = new Intent(context, CourseDetailActivity.class);
            intent.putExtra(CourseDetailActivity.EXTRA_COURSE_ID, course.getId());
            context.startActivity(intent);
        };

        holder.itemView.setOnClickListener(openDetails);
        holder.btnViewDetails.setOnClickListener(openDetails);
    }

    @Override
    public int getItemCount() {
        return courseList != null ? courseList.size() : 0;
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        View viewDeptIndicator;
        TextView tvDepartmentBadge;
        TextView tvCourseCode;
        TextView tvCourseName;
        TextView tvCourseBrief;
        TextView tvCourseNote;
        TextView tvMqaCode;
        MaterialButton btnViewDetails;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            viewDeptIndicator = itemView.findViewById(R.id.viewDeptIndicator);
            tvDepartmentBadge = itemView.findViewById(R.id.tvDepartmentBadge);
            tvCourseCode = itemView.findViewById(R.id.tvCourseCode);
            tvCourseName = itemView.findViewById(R.id.tvCourseName);
            tvCourseBrief = itemView.findViewById(R.id.tvCourseBrief);
            tvCourseNote = itemView.findViewById(R.id.tvCourseNote);
            tvMqaCode = itemView.findViewById(R.id.tvMqaCode);
            btnViewDetails = itemView.findViewById(R.id.btnViewDetails);
        }
    }
}
