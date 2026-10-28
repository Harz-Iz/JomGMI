package com.jomgmi.app.model;

import java.io.Serializable;

public class GalleryItem implements Serializable {
    private final int id;
    private final String drawableName;
    private final String title;
    private final String description;
    private final String category;
    private int resId;

    public GalleryItem(int id, String drawableName, String title, String description, String category) {
        this.id = id;
        this.drawableName = drawableName;
        this.title = title;
        this.description = description;
        this.category = category;
        this.resId = 0;
    }

    public int getId() {
        return id;
    }

    public String getDrawableName() {
        return drawableName;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
