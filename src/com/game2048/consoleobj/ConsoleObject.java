package com.game2048.consoleobj;

import java.util.Iterator;

public class ConsoleObject implements Iterator<String> {
    protected String header;
    protected String body;
    protected String footer;
    private int iterator;

    public ConsoleObject() {
        this.header = "";
        this.footer = "";
        this.body = "";
    }

    public ConsoleObject(String header, String body, String footer) {
        this.header = header;
        this.body = body;
        this.footer = footer;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public String next() {
        var res = switch (iterator%3) {
            case 0 -> header;
            case 1 -> body;
            case 2 -> footer;
            default -> throw new IllegalStateException("Unexpected value: " + iterator%3);
        };
        iterator++;
        return res;
    }
}
