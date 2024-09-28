package com.schoolmeta.notes.domain.dayplan.repo;

import com.schoolmeta.notes.domain.dayplan.model.PlanItemModel;
import com.schoolmeta.notes.infrastructure.db.SQLiteHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlanItemRepo {

    private SQLiteHelper dbHelper;

    public PlanItemRepo(SQLiteHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    // 插入一条记录
    public void insert(PlanItemModel planItem) {
        List<String> columns = List.of("plan_item_id", "plan_id", "name", "status", "is_pin", "order");
        List<Object> values = List.of(planItem.getPlanItemId(), planItem.getPlanId(), planItem.getName(),
                                      planItem.getStatus(), planItem.isPin(), planItem.getOrder());
        dbHelper.insertData("t_plan_item", columns, values);
    }

    // 更新一条记录
    public void update(PlanItemModel planItem) {
        Map<String, Object> setValues = new HashMap<>();
        setValues.put("plan_id", planItem.getPlanId());
        setValues.put("name", planItem.getName());
        setValues.put("status", planItem.getStatus());
        setValues.put("is_pin", planItem.isPin());
        setValues.put("order", planItem.getOrder());

        String whereClause = "plan_item_id = " + planItem.getPlanItemId();
        dbHelper.updateData("t_plan_item", setValues, whereClause);
    }

    // 删除一条记录
    public void delete(int planItemId) {
        String whereClause = "plan_item_id = " + planItemId;
        dbHelper.deleteData("t_plan_item", whereClause);
    }

    // 查询所有记录
    public List<PlanItemModel> findAll() {
        List<Map<String, Object>> results = dbHelper.queryData("t_plan_item", null, null);
        return mapToModels(results);
    }

    // 根据条件查询记录
    public List<PlanItemModel> findByCondition(String condition) {
        List<Map<String, Object>> results = dbHelper.queryData("t_plan_item", null, condition);
        return mapToModels(results);
    }

    // 分页查询
    public List<PlanItemModel> findPaged(int offset, int limit, String orderBy) {
        List<Map<String, Object>> results = dbHelper.pageQuery("t_plan_item", null, offset, limit, null, orderBy);
        return mapToModels(results);
    }

    // 将查询结果转换为 PlanItemModel 对象列表
    private List<PlanItemModel> mapToModels(List<Map<String, Object>> results) {
        List<PlanItemModel> models = new ArrayList<>();
        for (Map<String, Object> row : results) {
            PlanItemModel model = new PlanItemModel(
                (int) row.get("plan_item_id"),
                (int) row.get("plan_id"),
                (String) row.get("name"),
                (int) row.get("status"),
                (boolean) row.get("is_pin"),
                (int) row.get("order")
            );
            models.add(model);
        }
        return models;
    }
}