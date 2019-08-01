package cn.cityworks.cms.utils;

import cn.cityworks.cms.domain.Node;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 基础处理类
 */
public class Utils {

    /**
     * 获取不带"-"的UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 将数据转为树
     * 无其他属性
     */
    public static List<Node> getTree(List<Map<String, Object>> list, String fid){
        List<Node> result = new LinkedList<>();
        Node node;
        String id, fatherId;        //作为节点判断

        for(Map<String, Object> map : list){
            id = (String) map.get("id");
            fatherId = (String)map.get("father_id");

            if(fatherId.equals(fid)){
                node = new Node();
                node.setId(id);
                node.setFather_id(fatherId);
                node.setName((String)map.get("name"));
                node.setChild(Utils.getTree(list, id));

                result.add(node);
            }
        }

        return result;
    }

    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str){
        return null == str || "".equals(str);
    }

    /**
     * 判断对象是否为空
     */
    public static boolean isNull(Object obj){
        return null == obj;
    }

    /**
     * 将字符转为java.sql.Date
     */
    public static java.sql.Date strToSqlDate(String str){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;

        if(Utils.isEmpty(str)){
            return null;
        }

        try{
            date = format.parse(str);

            return new java.sql.Date(date.getTime());
        }catch (Exception e){
            e.printStackTrace();

            return null;
        }
    }

    /**
     * 分页
     */
    public static List<Map<String, Object>> pagingForApp(List<Map<String, Object>> list, int page, int rows){
        int size = list.size();
        int pageStart = page * rows;       //截取开始位置
        int pageEnd = size < (page + 1) * rows ? size : (page + 1) * rows;

        if(pageStart > size) {
            return new ArrayList<Map<String, Object>>();
        }else {
            return list.subList(pageStart, pageEnd);
        }
    }
}
