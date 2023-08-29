package com.ctrip.demo.entity;

public class CollationInstance {

    private String Variable_name;
    private String Value;

    public String getVariable_name() {
        return Variable_name;
    }

    public void setVariable_name(String variable_name) {
        Variable_name = variable_name;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    @Override
    public String toString() {
        return "CollationInstance{" +
                "Variable_name='" + Variable_name + '\'' +
                ", Value='" + Value + '\'' +
                '}';
    }
}
