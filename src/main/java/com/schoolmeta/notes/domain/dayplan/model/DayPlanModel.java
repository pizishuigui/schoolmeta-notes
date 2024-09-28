package com.schoolmeta.notes.domain.dayplan.model;

public class DayPlanModel {
    private int planId;
    private String dateStr;
    private double progress;
    private String labels;

    // 构造函数
    public DayPlanModel(int planId, String dateStr, double progress, String labels) {
        this.planId = planId;
        this.dateStr = dateStr;
        this.progress = progress;
        this.labels = labels;
    }

    // Getter 和 Setter 方法
    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }
}