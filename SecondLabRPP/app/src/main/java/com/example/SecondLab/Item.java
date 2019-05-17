package com.example.SecondLab;

import java.io.Serializable;

public class Item implements Serializable {
    private String flags;
    private String graphic;
    private String graphic_alt;
    private String name;
    private String help_text = " ";
    private String req1;
    private String reg2;

    public String getHelpText() {
        return help_text;
    }

    public String getFlags() {
        return flags;
    }

    public String getGraphic() {
        return graphic;
    }

    public String getGraphicAlt() {
        return graphic_alt;
    }

    public String getName() {
        return name;
    }

    public String getReq1() {
        return req1;
    }

    public String getReg2() {
        return reg2;
    }
}
