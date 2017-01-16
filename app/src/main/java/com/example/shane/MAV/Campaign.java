package com.example.shane.MAV;

import java.util.Date;

/**
 * Created by Shane on 22/12/2016.
 */

public class Campaign {
    private int campaignID;
    private String campaignName;
    private int countryID;
    private int clientID;
    private int estimatedEntryCount;
    private int currentEntryCount;
    private Date startDate;
    private Date endDate;

    public Campaign(){}

    public Campaign(int campaignID, String campaignName, int countryID, int clientID, int estimatedEntryCount, int currentEntryCount, Date startDate, Date endDate){
        this.campaignID = campaignID;
        this.campaignName = campaignName;
        this.countryID = countryID;
        this.clientID = clientID;
        this.estimatedEntryCount = estimatedEntryCount;
        this.currentEntryCount = currentEntryCount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String[] campaignInfo(){
        String details[] = new String[8];
        details[0] = "" + campaignID;
        details[1] = campaignName;
        details[2] = "" + countryID;
        details[3] = "" + clientID;
        details[4] = "" + estimatedEntryCount;
        details[5] = "" + currentEntryCount;
        details[6] = startDate.toString();
        details[7] = endDate.toString();

        return details;
    }

    public int entriesLeftToEstimate(){
        return estimatedEntryCount - currentEntryCount;
    }

    /*public int howLongLeft(){
        return endDate.compareTo(startDate);
    }*/

    public Date howLongLeft(){
        int endY, endMon, endD, endH, endMin, endS, startY, startMon, startD, startH, startMin, startS, rY, rMon, rD, rH, rMin, rS;

        endY = endDate.getYear();
        endMon = endDate.getMonth();
        endD = endDate.getDay();
        endH = endDate.getHours();
        endMin = endDate.getMinutes();
        endS = endDate.getSeconds();

        startY = startDate.getYear();
        startMon = startDate.getMonth();
        startD = startDate.getDay();
        startH = startDate.getHours();
        startMin = startDate.getMinutes();
        startS = startDate.getSeconds();

        rY = endY - startY;
        rMon = endMon - startMon;
        rD = endD - startD;
        rH = endH - startH;
        rMin = endMin - startMin;
        rD = endD - startD;
        rS = endS - startS;

        Date date = new Date();
        date.setYear(rY);
        date.setMonth(rMon);
        date.setDate(rD);
        date.setHours(rH);
        date.setMinutes(rMin);
        date.setSeconds(rS);

        return date;
    }
}
