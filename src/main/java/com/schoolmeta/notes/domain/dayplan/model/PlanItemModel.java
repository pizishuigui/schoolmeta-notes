package com.schoolmeta.notes.domain.dayplan.model;

public class PlanItemModel {
    private int planItemId;
    private int planId;
    private String name;
    private int status;
    private boolean isPin;
    private int order;

    // 构造函数
    public PlanItemModel(int planItemId, int planId, String name, int status, boolean isPin, int order) {
        this.planItemId = planItemId;
        this.planId = planId;
        this.name = name;
        this.status = status;
        this.isPin = isPin;
        this.order = order;
    }

    // Getter 和 Setter 方法
    public int getPlanItemId() {
        return planItemId;
    }

    public void setPlanItemId(int planItemId) {
        this.planItemId = planItemId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isPin() {
        return isPin;
    }

    public void setPin(boolean pin) {
        isPin = pin;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "PlanItemModel{" +
                "planItemId=" + planItemId +
                ", planId=" + planId +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", isPin=" + isPin +
                ", order=" + order +
                '}';
    }
}