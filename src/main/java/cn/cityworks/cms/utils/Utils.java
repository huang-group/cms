package cn.cityworks.cms.utils;

import cn.cityworks.cms.domain.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
}
