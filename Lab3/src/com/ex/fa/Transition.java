package com.ex.fa;

public class Transition {
    private String from;
    private String to;
    private String value;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getValue() {
        return value;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Transition(String from, String to, String value) {
        this.from = from;
        this.to = to;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("[%s,%s,%s]",from,to,value);
    }
}
