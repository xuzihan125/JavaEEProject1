package com.javaee.ebook1.mybatis.dao;

import com.javaee.ebook1.mybatis.entity.Log;
import com.javaee.ebook1.mybatis.entity.LogExample;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LogMapperTest {

    @Resource
    LogMapper logmapper;

    @Test
    void logmapperTest1() {
        System.out.println("Test Log insert");
        Log log = new Log();
        log.setDescr("abcdefghil");
        log.setTime(Date.valueOf("1111-11-11"));
        log.setUid(101);
        int lines = logmapper.insert(log);
        System.out.println("After insert 1 log, the lines change : " + lines);
        assertEquals(lines, 1);
    }

    @Test
    void logmapperTest2(){
        System.out.println("Test Log update");
        LogExample example = new LogExample();
        example.createCriteria().andUidEqualTo(101);
        List<Log> logs = logmapper.selectByExample(example);
        Log log = logs.get(0);
        System.out.println("Before update : " + log.toString());
        if(log.getDescr().equals("abcdefghil")){
            log.setDescr("qwertyuiop");
        }else{
            log.setDescr("abcdefghil");
        }
        logmapper.updateByExample(log, example);
        System.out.println("After update : " + log.toString());
    }

    @Test
    void logmapperTest3(){
        System.out.println("Test Log update");
        LogExample example = new LogExample();
        example.createCriteria().andUidEqualTo(101);
        List<Log> logs = logmapper.selectByExample(example);
        Log log = logs.get(0);
        System.out.println("Before update : " + log.toString());
    }

    @Test
    void logmapperTest4(){
        System.out.println("Test Log delete");
        LogExample example = new LogExample();
        example.createCriteria().andUidEqualTo(101);
        int lines = logmapper.deleteByExample(example);
        System.out.println("The number of delete record is : " + lines);
    }
}