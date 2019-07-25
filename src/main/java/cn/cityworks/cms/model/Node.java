package cn.cityworks.cms.model;

import java.util.List;

/**
 * 树
 */
public class Node {

    private String id;     //节点id
    private String name;        //节点名称
    private String father_id;       //父节点id
    private List<Node> child;       //子节点信息
    private String info;        //节点额外信息，使用json字符串

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFather_id() {
        return father_id;
    }

    public void setFather_id(String father_id) {
        this.father_id = father_id;
    }

    public List<Node> getChild() {
        return child;
    }

    public void setChild(List<Node> child) {
        this.child = child;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
