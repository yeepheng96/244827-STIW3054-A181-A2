package com.Asg2;

import java.lang.*;

public class ChessList {
    private String no;
    private String name;
    private String fideID;
    private String fed;
    private String rtg;
    private String cc;

    public ChessList(){
        super();
    }

    public ChessList(String no, String name, String fideID, String fed, String rtg, String cc){
        super();
        this.no = no;
        this.name = name;
        this.fideID = fideID;
        this.fed = fed;
        this.rtg = rtg;
        this.cc = cc;
    }

    public String getNo(){
        return no;
    }

    public void setNo(String no){
        this.no = no;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getFideID(){
        return fideID;
    }

    public void setFideID(String fideID){
        this.fideID = fideID;
    }

    public String getFed(){
        return fed;
    }

    public void setFed(String fed){
        this.fed = fed;
    }

    public String getRtg(){
        return rtg;
    }

    public void setRtg(String rtg){
        this.rtg = rtg;
    }

    public String getCc(){
        return cc;
    }

    public void setCc(String cc){
        this.cc = cc;
    }
}
