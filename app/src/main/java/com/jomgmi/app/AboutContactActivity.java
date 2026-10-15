package com.jomgmi.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.jomgmi.app.util.NavigationHelper;

public class AboutContactActivity extends AppCompatActivity {

    private MaterialToolbar toolbarAbout;
    private LinearLayout rowAddress;
    private LinearLayout rowPhone;
    private LinearLayout rowEmail;
    private LinearLayout rowWebsite;
    private MaterialButton btnGetDirections;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_contact);

        initViews();
        setupToolbar();
        setupContactActions();
        setupNavigation();
    }

    private void initViews() {
        toolbarAbout = findViewById(R.id.toolbarAbout);
        rowAddress = findViewById(R.id.rowAddress);
        rowPhone = findViewById(R.id.rowPhone);
        rowEmail = findViewById(R.id.rowEmail);
        rowWebsite = findViewById(R.id.rowWebsite);
        btnGetDirections = findViewById(R.id.btnGetDirections);
        bottomNavigation = findViewById(R.id.bottomNavigation);
    }

    private void setupToolbar() {
        toolbarAbout.setNavigationOnClickListener(v -> finish());
    }

    private void setupNavigation() {
        NavigationHelper.setupBottomNavigation(this, bottomNavigation, R.id.nav_about);
    }

    private void setupContactActions() {
        // Phone: tap -> Intent ACTION_DIAL
        View.OnClickListener dialAction = v -> {
            try {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+60355692000"));
                startActivity(dialIntent);
            } catch (Exception e) {
                Toast.makeText(this, "Could not open dialer: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        rowPhone.setOnClickListener(dialAction);

        // Email: tap -> Intent ACTION_SENDTO
        View.OnClickListener emailAction = v -> {
            try {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:info@gmi.edu.my"));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Enquiry regarding GMI Programmes");
                startActivity(emailIntent);
            } catch (Exception e) {
                Toast.makeText(this, "Could not open email client: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        rowEmail.setOnClickListener(emailAction);

        // Website: tap -> Intent ACTION_VIEW
        View.OnClickListener websiteAction = v -> {
            try {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gmi.edu.my"));
                startActivity(webIntent);
            } catch (Exception e) {
                Toast.makeText(this, "Could not open website: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        rowWebsite.setOnClickListener(websiteAction);

        // Get Directions button & Address row -> Intent ACTION_VIEW geo URI
        View.OnClickListener mapAction = v -> {
            try {
                Uri mapUri = Uri.parse("geo:2.9348,101.7946?q=German-Malaysian+Institute,+Jalan+Ilmiah,+Taman+Universiti,+43000+Kajang,+Selangor");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                startActivity(mapIntent);
            } catch (Exception e) {
                // Fallback to web maps URL
                try {
                    Intent webMap = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://maps.google.com/?q=German-Malaysian+Institute,+Jalan+Ilmiah,+Taman+Universiti,+43000+Kajang,+Selangor"));
                    startActivity(webMap);
                } catch (Exception ex) {
                    Toast.makeText(this, "Could not open map: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        btnGetDirections.setOnClickListener(mapAction);
        rowAddress.setOnClickListener(mapAction);
    }
}
