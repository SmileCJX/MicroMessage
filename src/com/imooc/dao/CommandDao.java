package com.imooc.dao;

import com.imooc.bean.Command;
import com.imooc.db.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/25/025.
 */
public class CommandDao {
    /**
     * 根据查询条件查询指令列表
     */
    public List<Command> queryCommandList(String name, String description){
        DBAccess dbAccess = new DBAccess();
        List<Command> commandList = new ArrayList<>();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            Command command = new Command();
            command.setName(name);
            command.setDescription(description);
            //通过sqlSession执行SQL语句
            commandList = sqlSession.selectList("Command.queryCommandList",command);
        } catch (IOException e) {
            e.printStackTrace();
            //通过sqlSession执行SQL语句
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

        return commandList;
    }
}
