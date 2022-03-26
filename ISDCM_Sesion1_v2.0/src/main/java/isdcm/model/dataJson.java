package isdcm.model;

import java.io.Serializable;

public class dataJson implements Serializable{
    private static final long serialVersionUID = 1L;
    private String parameter;
    private String value;

    public dataJson() {
    }

    public dataJson(String parameter, String value) {
        this.parameter = parameter;
        this.value = value;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    
}
