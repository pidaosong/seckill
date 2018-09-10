package com.pi.miaosha.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pi.miaosha.domain.MiaoshaProduct;
import com.pi.miaosha.domain.User;
import com.pi.miaosha.util.MD5Util;
import com.pi.miaosha.vo.ProductVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDaoTest {
    @Autowired
    ProductDao productDao;

    @Autowired
    UserDao userDao;
    @Test
    public void queryProductVoList() {
        List<ProductVo> list = productDao.queryProductVoList();
        System.out.println(list.get(0).toString());
    }

    @Test
    public void updateStock() {
        MiaoshaProduct p = new MiaoshaProduct();
        p.setId(1L);
        p.setCount(1);
        int num = productDao.updateStock(p);
    }

    @Test
    public void test()throws Exception{
        List<User> users = new ArrayList<User>(5000);
        //生成用户
        for(int i=0;i<5000;i++) {
            User user = new User();
            user.setId(13000000000L+i);
            user.setLoginCount(1);
            user.setNickname("user"+i);
            user.setRegisterDate(new Date());
            user.setSalt("root");
            user.setPassword(MD5Util.inputPassToDbPass("root", user.getSalt()));
            users.add(user);
        }
        System.out.println("create user");
        //插入数据库
        /*for(int i=0;i<users.size();i++) {
            User user = users.get(i);
            userDao.insertUser(user);
        }*/

        //System.out.println("insert to db");
        //登录，生成token
        String urlString = "http://localhost:8080/login/enter";
        File file = new File("D:/tokens.txt");
        if(file.exists()) {
            file.delete();
        }
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        file.createNewFile();
        raf.seek(0);
        for(int i=0;i<users.size();i++) {
            User user = users.get(i);
            URL url = new URL(urlString);
            HttpURLConnection co = (HttpURLConnection)url.openConnection();
            co.setRequestMethod("POST");
            co.setDoOutput(true);
            OutputStream out = co.getOutputStream();
            String params = "mobile="+user.getId()+"&password="+MD5Util.inputPassToFormPass("root");
            out.write(params.getBytes());
            out.flush();
            InputStream inputStream = co.getInputStream();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte buff[] = new byte[1024];
            int len = 0;
            while((len = inputStream.read(buff)) >= 0) {
                bout.write(buff, 0 ,len);
            }
            inputStream.close();
            bout.close();
            String response = new String(bout.toByteArray());
            JSONObject jo = JSON.parseObject(response);
            String token = jo.getString("data");
            System.out.println("create token : " + user.getId());

            String row = user.getId()+","+token;
            raf.seek(raf.length());
            raf.write(row.getBytes());
            raf.write("\r\n".getBytes());
            System.out.println("write to file : " + user.getId());
        }
        raf.close();

        System.out.println("over");
    }
}