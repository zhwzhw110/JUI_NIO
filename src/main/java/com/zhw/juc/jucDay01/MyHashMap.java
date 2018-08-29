package com.zhw.juc.jucDay01;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/8 0008
 * @desc 自定义的HashMap
 **/
public class MyHashMap implements HashMap {

    private Node[] tables;
    private int iniSize;

    public MyHashMap(){
        iniSize = 16;
        this.tables = new Node[iniSize];// 创建对象时初始化大小
    }

    public MyHashMap(int iniSize){
        this.iniSize = iniSize;
        this.tables = new Node[iniSize];// 创建对象时初始化大小
    }

    @Override
    public Object get(Object key) {
        int i = coordinate(key);
        if(tables[i] != null){
            //当前的Node
            Node thisNode = tables[i];
            //下一个Node
            Node nextNode = thisNode.getNextNode();
            while (thisNode.getKey()!=key){
                nextNode = thisNode;
                thisNode = nextNode.getNextNode();
            }
            Object value = thisNode.getValue();
            return value;
        }
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        if(null==key){
            throw new RuntimeException("key不能为null");
        }
        int i = coordinate(key);
        Node newNode = new Node(key,value);
        if(tables[i] != null){
            //当前的Node
            Node thisNode = tables[i];
            //下一个Node
            Node nextNode = thisNode.getNextNode();
            while (thisNode.getNextNode()!=null){
                nextNode = thisNode;
                thisNode = nextNode.getNextNode();
            }
            thisNode.setNextNode(newNode);
        }else{
            tables[i] = newNode;
        }

    }

    public int coordinate(Object key){
        if(null!=key){
            return key.hashCode() % iniSize; // hascode值除map大小取余
        }
        return  0;
    }

    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1,"asd");
        myHashMap.put(2,"asda");
        System.out.println(myHashMap.get(2));
    }


}
class Node{
    private Object key;
    private Object value;
    private Node nextNode;

    public Node(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }
}