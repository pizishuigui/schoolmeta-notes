package com.schoolmeta.notes.domain.dayplan.model;

public class ItemDetailModel {
    private int itemDetailId;
    private int planItemId;
    private String value;
    private int status;
    private int order;
    private String details;

    // 构造函数
    public ItemDetailModel(int itemDetailId, int planItemId, String value, int status, int order, String details) {
        this.itemDetailId = itemDetailId;
        this.planItemId = planItemId;
        this.value = value;
        this.status = status;
        this.order = order;
        this.details = details;
    }

    // Getter 和 Setter 方法
    public int getItemDetailId() {
        return itemDetailId;
    }

    public void setItemDetailId(int itemDetailId) {
        this.itemDetailId = itemDetailId;
    }

    public int getPlanItemId() {
        return planItemId;
    }

    public void setPlanItemId(int planItemId) {
        this.planItemId = planItemId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ItemDetailModel{" +
                "itemDetailId=" + itemDetailId +
                ", planItemId=" + planItemId +
                ", value='" + value + '\'' +
                ", status=" + status +
                ", order=" + order +
                ", details='" + details + '\'' +
                '}';
    }
}