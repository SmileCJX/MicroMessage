package com.imooc.service;

import com.imooc.dao.MessageDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 维护相关的业务功能
 * Created by Administrator on 2016/12/21/021.
 */
public class MaintainService {
    /*
    单条删除
     */
    public void deleteOne(String id){
        if(id != null && !"".equals(id.trim())){
            MessageDao messageDao = new MessageDao();
            messageDao.deleteOne(Integer.valueOf(id));
        }
    }

    /*
    批量删除
     */
    public void deleteBatch(String[] ids){
        MessageDao messageDao = new MessageDao();
        List<Integer> idsList = new ArrayList<>();
        for(String id:ids){
            idsList.add(Integer.valueOf(id));
        }
        messageDao.deleteBatch(idsList);
    }
}
