package com.schoolmeta.notes.domain.dayplan.service;

import com.schoolmeta.notes.domain.dayplan.IItemDetailDomainService;
import com.schoolmeta.notes.domain.dayplan.entity.ItemDetailEntity;

import java.util.UUID;

public class ItemDetailDomainService implements IItemDetailDomainService {
    @Override
    public void createItemDetail(String value, String planItemId) {
        ItemDetailEntity itemDetailEntity = new ItemDetailEntity();
        itemDetailEntity.setItemDetailId("i_detail_" + UUID.randomUUID());
        itemDetailEntity.setValue(value);
        itemDetailEntity.setStatus("未开始");
        itemDetailEntity.setPlanItemId(planItemId);
        itemDetailEntity.setIsPin(0);

        itemDetailEntity.save();
    }
}
