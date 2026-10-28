package com.jomgmi.app;

import android.app.Dialog;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.jomgmi.app.adapter.GalleryFeedAdapter;
import com.jomgmi.app.adapter.HeroSliderAdapter;
import com.jomgmi.app.model.GalleryItem;
import com.jomgmi.app.util.NavigationHelper;

import java.util.ArrayList;
import java.util.List;

public class EligibilityActivity extends AppCompatActivity {

    private MaterialToolbar toolbarEligibility;
    private ViewPager2 viewPagerHero;
    private MaterialCardView cardHeroPreview;
    private TextView tvHeroTag;
    private TextView tvHeroBadge;
    private TextView tvHeroTitle;
    private TextView tvHeroSubtitle;
    private RecyclerView rvGalleryFeed;
    private BottomNavigationView bottomNavigation;

    private List<GalleryItem> galleryItems;
    private HeroSliderAdapter heroAdapter;
    private GalleryFeedAdapter feedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eligibility);

        initViews();
        setupToolbar();
        prepareGalleryData();
        setupHeroSlider();
        setupGalleryFeed();
        setupNavigation();
    }

    private void initViews() {
        toolbarEligibility = findViewById(R.id.toolbarEligibility);
        viewPagerHero = findViewById(R.id.viewPagerHero);
        cardHeroPreview = findViewById(R.id.cardHeroPreview);
        tvHeroTag = findViewById(R.id.tvHeroTag);
        tvHeroBadge = findViewById(R.id.tvHeroBadge);
        tvHeroTitle = findViewById(R.id.tvHeroTitle);
        tvHeroSubtitle = findViewById(R.id.tvHeroSubtitle);
        rvGalleryFeed = findViewById(R.id.rvGalleryFeed);
        bottomNavigation = findViewById(R.id.bottomNavigation);
    }

    private void setupToolbar() {
        toolbarEligibility.setNavigationOnClickListener(v -> finish());
    }

    private void prepareGalleryData() {
        galleryItems = new ArrayList<>();

        galleryItems.add(new GalleryItem(
                1,
                "jomgmi_1",
                "GMI Main Campus & Administration",
                "Sprawling green campus in Bangi, Selangor built to German industrial education standards.",
                "Campus Overview"
        ));

        galleryItems.add(new GalleryItem(
                2,
                "jomgmi_2",
                "Advanced Industrial Automation & Robotics Lab",
                "Equipped with state-of-the-art robotic workcells, PLC controllers, and SCADA systems.",
                "Industrial Labs"
        ));

        galleryItems.add(new GalleryItem(
                3,
                "jomgmi_3",
                "Precision CNC & Engineering Workshop",
                "High-precision CNC turning, 5-axis milling machinery, and additive manufacturing workshops.",
                "Engineering Workshops"
        ));

        galleryItems.add(new GalleryItem(
                4,
                "jomgmi_4",
                "Cyber Security & Software Development Labs",
                "High-performance networked computing infrastructure dedicated to AI, cloud, and security.",
                "IT & Software"
        ));

        galleryItems.add(new GalleryItem(
                5,
                "jomgmi_5",
                "Mechatronics & Electro-Pneumatics Training Center",
                "Comprehensive hands-on mechatronics prototyping stations and industrial automation sensors.",
                "Mechatronics"
        ));

        galleryItems.add(new GalleryItem(
                6,
                "jomgmi_6",
                "Student Life & Sports Complex",
                "Full-sized sports arena, gymnasiums, Olympic-sized swimming amenities, and student activity spaces.",
                "Student Life"
        ));

        galleryItems.add(new GalleryItem(
                7,
                "jomgmi_7",
                "GMI Resource Centre & Collaborative Learning Hub",
                "Rich repository of engineering literature, research terminals, and modern group discussion pods.",
                "Learning Spaces"
        ));

        // Resolve drawable resource IDs dynamically with graceful fallback
        for (GalleryItem item : galleryItems) {
            int resId = getResources().getIdentifier(item.getDrawableName(), "drawable", getPackageName());
            if (resId == 0) {
                resId = R.drawable.ic_school; // fallback placeholder
            }
            item.setResId(resId);
        }
    }

    private void setupHeroSlider() {
        heroAdapter = new HeroSliderAdapter(this, galleryItems, (position, item) -> showLightboxDialog(position));
        viewPagerHero.setAdapter(heroAdapter);

        viewPagerHero.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateHeroCardInfo(position);
            }
        });

        if (!galleryItems.isEmpty()) {
            updateHeroCardInfo(0);
        }

        cardHeroPreview.setOnClickListener(v -> showLightboxDialog(viewPagerHero.getCurrentItem()));
    }

    private void updateHeroCardInfo(int position) {
        if (position >= 0 && position < galleryItems.size()) {
            GalleryItem item = galleryItems.get(position);
            tvHeroBadge.setText((position + 1) + " / " + galleryItems.size());
            tvHeroTag.setText(item.getCategory());
            tvHeroTitle.setText(item.getTitle());
            tvHeroSubtitle.setText(item.getDescription());
        }
    }

    private void setupGalleryFeed() {
        rvGalleryFeed.setLayoutManager(new LinearLayoutManager(this));
        feedAdapter = new GalleryFeedAdapter(this, galleryItems, (position, item) -> {
            viewPagerHero.setCurrentItem(position, true);
            showLightboxDialog(position);
        });
        rvGalleryFeed.setAdapter(feedAdapter);
    }

    private void setupNavigation() {
        NavigationHelper.setupBottomNavigation(this, bottomNavigation, R.id.nav_eligibility);
    }

    private void showLightboxDialog(int initialPosition) {
        if (galleryItems == null || galleryItems.isEmpty()) return;

        Dialog dialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_lightbox);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }

        ImageView ivLightboxMain = dialog.findViewById(R.id.ivLightboxMain);
        ImageButton btnLightboxClose = dialog.findViewById(R.id.btnLightboxClose);
        ImageButton btnLightboxPrev = dialog.findViewById(R.id.btnLightboxPrev);
        ImageButton btnLightboxNext = dialog.findViewById(R.id.btnLightboxNext);
        TextView tvLightboxCount = dialog.findViewById(R.id.tvLightboxCount);
        TextView tvLightboxTag = dialog.findViewById(R.id.tvLightboxTag);
        TextView tvLightboxTitle = dialog.findViewById(R.id.tvLightboxTitle);
        TextView tvLightboxDesc = dialog.findViewById(R.id.tvLightboxDesc);

        final int[] currentIndex = {Math.max(0, Math.min(initialPosition, galleryItems.size() - 1))};

        Runnable updateDialogContent = () -> {
            GalleryItem currentItem = galleryItems.get(currentIndex[0]);
            if (currentItem.getResId() != 0) {
                ivLightboxMain.setImageResource(currentItem.getResId());
            } else {
                ivLightboxMain.setImageResource(R.drawable.ic_school);
            }
            tvLightboxCount.setText("Photo " + (currentIndex[0] + 1) + " of " + galleryItems.size());
            tvLightboxTag.setText(currentItem.getCategory());
            tvLightboxTitle.setText(currentItem.getTitle());
            tvLightboxDesc.setText(currentItem.getDescription());
        };

        updateDialogContent.run();

        btnLightboxClose.setOnClickListener(v -> dialog.dismiss());

        btnLightboxPrev.setOnClickListener(v -> {
            if (currentIndex[0] > 0) {
                currentIndex[0]--;
            } else {
                currentIndex[0] = galleryItems.size() - 1;
            }
            updateDialogContent.run();
            viewPagerHero.setCurrentItem(currentIndex[0], true);
        });

        btnLightboxNext.setOnClickListener(v -> {
            if (currentIndex[0] < galleryItems.size() - 1) {
                currentIndex[0]++;
            } else {
                currentIndex[0] = 0;
            }
            updateDialogContent.run();
            viewPagerHero.setCurrentItem(currentIndex[0], true);
        });

        dialog.show();
    }
}
