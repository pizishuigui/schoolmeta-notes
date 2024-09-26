package com.schoolmeta.notes.domain.dayplan.entity;

import com.schoolmeta.notes.infrastructure.db.repo.ItemDetailRepo;

/**
 * 待办详情
 */
public class ItemDetailEntity {
    // todo 使用beanMap管理
    private IItemDetailRepo itemDetailRepo = new ItemDetailRepo();

    private String itemDetailId;

    public String getItemDetailId() {
        return itemDetailId;
    }

    public void setItemDetailId(String itemDetailId) {
        this.itemDetailId = itemDetailId;
    }

    // 详情项id
    private String planItemId;

    // 详情内容
    private String value;

    // 状态
    private String status;

    // 是否置顶到桌面 0 -> 否 1->是
    private Integer isPin;


    public String getPlanItemId() {
        return planItemId;
    }

    public void setPlanItemId(String planItemId) {
        this.planItemId = planItemId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIsPin() {
        return isPin;
    }

    public void setIsPin(Integer isPin) {
        this.isPin = isPin;
    }

    public void save() {
        itemDetailRepo.save(this);
    }
}
