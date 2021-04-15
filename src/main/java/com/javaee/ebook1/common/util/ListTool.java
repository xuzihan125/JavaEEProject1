package com.javaee.ebook1.common.util;

import com.github.pagehelper.Page;
import com.javaee.ebook1.common.Enum.ResultCode;
import com.javaee.ebook1.common.exception.OpException;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/4/14
 **/
public class ListTool {

    public static List copyTo(List fromList, Class toClass) throws OpException {
        try {
            List toList = new ArrayList();
            if(null == fromList){
                return toList;
            }
            Object tempObj;
            for (Object aFromList : fromList) {
                tempObj = toClass.newInstance();
                BeanUtils.copyProperties(aFromList, tempObj, toClass);
                toList.add(tempObj);
            }
            if (fromList instanceof Page) {
                fromList.clear();
                fromList.addAll(toList);
                return fromList;
            } else {
                return toList;
            }
        } catch (Exception e) {
            throw new OpException(ResultCode.ERROR_LIST_COPY.getDesc(), ResultCode.ERROR_LIST_COPY.getCode());
        }
    }
    //踢除重复元素
    public static List removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }
}
