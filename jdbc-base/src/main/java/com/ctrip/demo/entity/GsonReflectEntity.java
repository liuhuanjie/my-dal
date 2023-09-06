package com.ctrip.demo.entity;

public class GsonReflectEntity {

    private long id;
    private Long ID=10L;
    private String name;
    private boolean flag;
    private Boolean FLAG=true;
    private char charset;
    private Character CHARSET='c';

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Boolean getFLAG() {
        return FLAG;
    }

    public void setFLAG(Boolean FLAG) {
        this.FLAG = FLAG;
    }

    public char getCharset() {
        return charset;
    }

    public void setCharset(char charset) {
        this.charset = charset;
    }

    public Character getCHARSET() {
        return CHARSET;
    }

    public void setCHARSET(Character CHARSET) {
        this.CHARSET = CHARSET;
    }

    @Override
    public String toString() {
        return "GsonReflectEntity{" +
                "id=" + id +
                ", ID=" + ID +
                ", name='" + name + '\'' +
                ", flag=" + flag +
                ", FLAG=" + FLAG +
                ", charset=" + charset +
                ", CHARSET=" + CHARSET +
                '}';
    }
}
