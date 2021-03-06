package com.chengmboy.orm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import com.chengmboy.datasource.DataBase;
import com.chengmboy.util.common.ThreadUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author cheng_mboy
 */
public class Operation {

    public static void main(String[] args) throws JsonProcessingException {
        /*String url = "jdbc:mysql://localhost:3306/train?useUnicode=true&characterEncoding=utf-8&&useSSL=false";
        String username = "root";
        String password = "123456";
        String driver = "com.mysql.jdbc.Driver";*/

      /*  String url = "jdbc:mysql://192.168.0.3:3306/coin_sub?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf-8";
        String username = "digi";
        String password = "digi123456";
        String driver = "com.mysql.jdbc.Driver";

        url = "jdbc:mysql://localhost:3306/advice?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf-8";
        username = "root";
        password = "123456";
        driver = "com.mysql.jdbc.Driver";*/
        String url = "jdbc:mysql://192.168.0.3:3306/coin_sub?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf-8";
        String username = "digi";
        String password = "digi123456";
        String driver = "com.mysql.jdbc.Driver";
        DataBase dataBase = new DataBase(url, username, password, driver);
        ThreadPoolExecutor pool = ThreadUtils.getThreadPoolExecutor(200, 100);

        for (int i = 0; i < 100; i++) {
            pool.submit(() -> dataBase.selectOne("select count(*) from account_record_history"));
        }

    }
}
