package com.schoolmeta.notes.infrastructure.db.repo;

import com.schoolmeta.notes.domain.dayplan.entity.IItemDetailRepo;
import com.schoolmeta.notes.domain.dayplan.entity.ItemDetailEntity;
import com.schoolmeta.notes.infrastructure.db.SQLiteUtil;

public class ItemDetailRepo implements IItemDetailRepo {
    private String tableName = "t_item_detail";

    @Override
    public void save(ItemDetailEntity itemDetailEntity) {
        // 插入数据
        String itemDetailId = itemDetailEntity.getItemDetailId();
        String planItemId = itemDetailEntity.getPlanItemId();
        String status = itemDetailEntity.getStatus();
        String sqlInsert = "INSERT INTO " + tableName + " (item_detail_id, plan_item_id, value, status, is_pin) " +
                "VALUES ('" + itemDetailId + "', '" + planItemId + "','" + itemDetailEntity.getValue() + "', '" + status + "', '" + itemDetailEntity.getIsPin() + "')";
        SQLiteUtil.insert(tableName, sqlInsert);
    }
}
