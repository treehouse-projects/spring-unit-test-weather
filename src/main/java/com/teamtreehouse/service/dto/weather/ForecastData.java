package com.teamtreehouse.service.dto.weather;

import com.teamtreehouse.service.dto.Dto;

import java.util.List;

public class ForecastData extends Dto {
    private String summary;
    private String icon;
    private List<Condition> data;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Condition> getData() {
        return data;
    }

    public void setData(List<Condition> data) {
        this.data = data;
    }
}
