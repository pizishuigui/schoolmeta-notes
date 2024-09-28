package com.schoolmeta.notes.domain.dayplan.repo;

import com.schoolmeta.notes.domain.dayplan.model.ItemDetailModel;
import com.schoolmeta.notes.infrastructure.db.SQLiteHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemDetailRepo {

    private SQLiteHelper dbHelper;

    public ItemDetailRepo(SQLiteHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    // 插入一条记录
    public void insert(ItemDetailModel itemDetail) {
        List<String> columns = List.of("item_detail_id", "plan_item_id", "value", "status", "order", "details");
        List<Object> values = List.of(itemDetail.getItemDetailId(), itemDetail.getPlanItemId(), itemDetail.getValue(),
                                      itemDetail.getStatus(), itemDetail.getOrder(), itemDetail.getDetails());
        dbHelper.insertData("t_item_detail", columns, values);
    }

    // 更新一条记录
    public void update(ItemDetailModel itemDetail) {
        Map<String, Object> setValues = new HashMap<>();
        setValues.put("plan_item_id", itemDetail.getPlanItemId());
        setValues.put("value", itemDetail.getValue());
        setValues.put("status", itemDetail.getStatus());
        setValues.put("order", itemDetail.getOrder());
        setValues.put("details", itemDetail.getDetails());

        String whereClause = "item_detail_id = " + itemDetail.getItemDetailId();
        dbHelper.updateData("t_item_detail", setValues, whereClause);
    }

    // 删除一条记录
    public void delete(int itemDetailId) {
        String whereClause = "item_detail_id = " + itemDetailId;
        dbHelper.deleteData("t_item_detail", whereClause);
    }

    // 查询所有记录
    public List<ItemDetailModel> findAll() {
        List<Map<String, Object>> results = dbHelper.queryData("t_item_detail", null, null);
        return mapToModels(results);
    }

    // 根据条件查询记录
    public List<ItemDetailModel> findByCondition(String condition) {
        List<Map<String, Object>> results = dbHelper.queryData("t_item_detail", null, condition);
        return mapToModels(results);
    }

    // 分页查询
    public List<ItemDetailModel> findPaged(int offset, int limit, String orderBy) {
        List<Map<String, Object>> results = dbHelper.pageQuery("t_item_detail", null, offset, limit, null, orderBy);
        return mapToModels(results);
    }

    // 将查询结果转换为 ItemDetailModel 对象列表
    private List<ItemDetailModel> mapToModels(List<Map<String, Object>> results) {
        List<ItemDetailModel> models = new ArrayList<>();
        for (Map<String, Object> row : results) {
            ItemDetailModel model = new ItemDetailModel(
                (int) row.get("item_detail_id"),
                (int) row.get("plan_item_id"),
                (String) row.get("value"),
                (int) row.get("status"),
                (int) row.get("order"),
                (String) row.get("details")
            );
            models.add(model);
        }
        return models;
    }
}