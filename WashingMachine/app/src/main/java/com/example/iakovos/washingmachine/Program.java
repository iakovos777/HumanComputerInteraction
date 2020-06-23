package com.example.iakovos.washingmachine;



public class Program {
    private int id;
    private String programName;
    private String start;
    private String duration;
    private String spins;
    private String temp;
    private static int iD=1;

    public Program(){
        this.id=iD;
        iD++;
    }

    public Program(String programName, String start, String temp, String spins, String duration ){
        this.id=iD;
        iD++;
        this.programName = programName;
        this.start = start;
        this.temp = temp;
        this.spins = spins;
        this.duration = duration;


    }

    public void setId(int id){
        this.id = id;
    }

    public void setProgramName(String programName){
        this.programName = programName;
    }

    public void setStart(String start){
        this.start = start;
    }
    public void setTemperature(String temp){
        this.temp =temp;
    }
    public void setDuration(String duration){
       this.duration = duration;
    }

    public void setSpins(String spins){
        this.spins = spins;
    }

    public int getId(){
        return this.id;
    }

    public String getProgramName(){
        return this.programName;
    }

    public String getStart(){
        return this.start ;
    }
    public String getTemperature(){
        return this.temp ;
    }
    public String getDuration(){
        return this.duration;
    }

    public String getSpins(){
        return this.spins ;
    }



}
