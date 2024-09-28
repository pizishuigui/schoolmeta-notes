package com.schoolmeta.notes.domain.dayplan.repo;

import com.schoolmeta.notes.domain.dayplan.model.DayPlanModel;
import com.schoolmeta.notes.infrastructure.db.SQLiteHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayPlanRepo {

    private SQLiteHelper dbHelper;

    public DayPlanRepo(SQLiteHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    // 插入一条记录
    public void insert(DayPlanModel dayPlan) {
        List<String> columns = List.of("plan_id", "date_str", "progress", "labels");
        List<Object> values = List.of(dayPlan.getPlanId(), dayPlan.getDateStr(), dayPlan.getProgress(), dayPlan.getLabels());
        dbHelper.insertData("t_day_plan", columns, values);
    }

    // 更新一条记录
    public void update(DayPlanModel dayPlan) {
        Map<String, Object> setValues = new HashMap<>();
        setValues.put("date_str", dayPlan.getDateStr());
        setValues.put("progress", dayPlan.getProgress());
        setValues.put("labels", dayPlan.getLabels());
        String whereClause = "plan_id = " + dayPlan.getPlanId();
        dbHelper.updateData("t_day_plan", setValues, whereClause);
    }

    // 删除一条记录
    public void delete(int planId) {
        String whereClause = "plan_id = " + planId;
        dbHelper.deleteData("t_day_plan", whereClause);
    }

    // 查询所有记录
    public List<DayPlanModel> findAll() {
        List<Map<String, Object>> results = dbHelper.queryData("t_day_plan", null, null);
        return mapToModels(results);
    }

    // 根据条件查询记录
    public List<DayPlanModel> findByCondition(String condition) {
        List<Map<String, Object>> results = dbHelper.queryData("t_day_plan", null, condition);
        return mapToModels(results);
    }

    // 分页查询
    public List<DayPlanModel> findPaged(int offset, int limit, String orderBy) {
        List<Map<String, Object>> results = dbHelper.pageQuery("t_day_plan", null, offset, limit, null, orderBy);
        return mapToModels(results);
    }

    // 将查询结果转换为 DayPlanModel 对象列表
    private List<DayPlanModel> mapToModels(List<Map<String, Object>> results) {
        List<DayPlanModel> models = new ArrayList<>();
        for (Map<String, Object> row : results) {
            DayPlanModel model = new DayPlanModel(
                (int) row.get("plan_id"),
                (String) row.get("date_str"),
                (double) row.get("progress"),
                (String) row.get("labels")
            );
            models.add(model);
        }
        return models;
    }
}