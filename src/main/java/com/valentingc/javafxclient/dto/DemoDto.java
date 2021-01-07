package com.valentingc.javafxclient.dto;

/**
 * Demo DTO that is shown in the table of the search results.
 * 
 * @author Valentin Goronjic
 */
public class DemoDto {
    private String title;
    private String description;

    public DemoDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
