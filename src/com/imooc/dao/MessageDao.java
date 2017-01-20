package com.imooc.dao;

import com.imooc.bean.Message;
import com.imooc.db.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/19/019.
 * 和message表相关的数据库操作
 */
public class MessageDao {
    /**
     * 根据查询条件查询消息列表
     */
//    public List<Message> queryMessageList(String command,String description){
//        List<Message> messageList = new ArrayList<Message>();
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/micro_message?characterEncoding=utf8","root","");
//            /**
//             * 不能写成select * ,这样的话，数据库引擎还得解析一遍，解析成对应的列。
//             */
//            StringBuilder sql = new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from MESSAGE where 1=1");
////            List<String> paramList = new ArrayList<>();
//            if(command != null && !"".equals(command.trim())){
//                sql.append(" and command='"+command+"' ");
//
////                paramList.add(command);
//            }
//            if(description != null && !"".equals(description.trim())){
//                sql.append(" and description like '%' '"+description+"' '%' ");
////                paramList.add(description);
//            }
//            PreparedStatement statement = conn.prepareStatement(sql.toString());
////            for(int i=0; i<paramList.size(); i++){
////                statement.setString(i+1,paramList.get(i));
////            }
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()){
//                Message message = new Message();
//                messageList.add(message);
//                message.setId(rs.getString("ID"));
//                message.setCommand(rs.getString("COMMAND"));
//                message.setDescription(rs.getString("DESCRIPTION"));
//                message.setContent(rs.getString("CONTENT"));
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return messageList;
//    }
    /**
     * 根据查询条件查询消息列表
     */
    public List<Message> queryMessageList(Map<String,Object> parameter) {
        DBAccess dbAccess = new DBAccess();
        List<Message> messageList = new ArrayList<Message>();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            // 通过sqlSession执行SQL语句
            IMessage imessage = sqlSession.getMapper(IMessage.class);
            messageList = imessage.queryMessageList(parameter);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sqlSession != null) {
                sqlSession.close();
            }
        }
        return messageList;
    }

    /**
     * 根据查询条件查询消息列表的条数
     */
    public int count(Message message){
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        int result = 0;
        try {
            sqlSession = dbAccess.getSqlSession();
            //通过sqlSession执行sql语句
            IMessage iMessage = sqlSession.getMapper(IMessage.class);
            result = iMessage.count(message);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }

        return result;
    }

    /**
     * 根据查询条件分页查询消息列表
     */
    public List<Message> queryMessageListByPage(Map<String,Object> parameter) {
        DBAccess dbAccess = new DBAccess();
        List<Message> messageList = new ArrayList<Message>();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            // 通过sqlSession执行SQL语句
            IMessage imessage = sqlSession.getMapper(IMessage.class);
            messageList = imessage.queryMessageListByPage(parameter);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sqlSession != null) {
                sqlSession.close();
            }
        }
        return messageList;
    }

    /**
     * 单条删除
     * @param id
     */
    public void deleteOne(int id){
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            //通过sqlSession执行SQL语句
            sqlSession.delete("Message.deleteOne",id);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
            //通过sqlSession执行SQL语句
        } finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }

    public void deleteBatch(List<Integer> ids){
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            //通过sqlSession执行SQL语句
            sqlSession.delete("Message.deleteBatch",ids);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }

}
