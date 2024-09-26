package com.schoolmeta.notes.application;

import com.schoolmeta.notes.domain.dayplan.IItemDetailDomainService;
import com.schoolmeta.notes.domain.dayplan.service.ItemDetailDomainService;

import java.util.UUID;

public class ItemDetailHandler {
    static IItemDetailDomainService itemDetailDomainService = new ItemDetailDomainService();

    public static void main(String[] args) {
        // todo 测试
        itemDetailDomainService.createItemDetail("待办详情11", UUID.randomUUID().toString());
    }
}
