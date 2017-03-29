package com.example.shane.MAV.Objects;

import com.example.shane.MAV.Interfaces.simpleCampaignInterface;

import java.util.Date;

/**
 * Created by Shane on 22/12/2016.
 */

public class Campaign implements simpleCampaignInterface {
    private int campaignID;
    private String campaignName;
    private String description;
    private int countryID;
    private int clientID;
    private int currentEntryCount;
    private int estimatedEntryCount;
    private int failedEntries;
    private int positiveResponses;
    private int negativeResponses;
    private Date startDate;
    private Date endDate;

    public Campaign(){}

    public Campaign(int campaignID, String campaignName, String description, int countryID, int clientID, int currentEntryCount, int estimatedEntryCount, int failedEntries, int positiveResponses, int negativeResponses, Date startDate, Date endDate){
        this.campaignID = campaignID;
        this.campaignName = campaignName;
        this.description = description;
        this.countryID = countryID;
        this.clientID = clientID;
        this.estimatedEntryCount = estimatedEntryCount;
        this.currentEntryCount = currentEntryCount;
        this.failedEntries = failedEntries;
        this.positiveResponses = positiveResponses;
        this.negativeResponses = negativeResponses;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getCampaignID(){return campaignID;}
    public String getCampaignName(){return campaignName;}
    public String getDescription(){return description;}
    public int getCountryID(){return countryID;}
    public int getClientID(){return clientID;}
    public int getCurrentEntryCount(){return currentEntryCount;}
    public int getEstimatedEntryCount(){return estimatedEntryCount;}
    public int getFailedEntries(){return failedEntries;}
    public int getPositiveResponses(){return positiveResponses;}
    public int getNegativeResponses(){return negativeResponses;}
    public Date getStartDate(){return startDate;}
    public Date getEndDate(){return endDate;}


    public String[] campaignInfo(){
        String details[] = new String[8];
        details[0] = "" + campaignID;
        details[1] = campaignName;
        details[2] = description;
        details[3] = "" + countryID;
        details[4] = "" + clientID;
        details[5] = "" + currentEntryCount;
        details[6] = "" + estimatedEntryCount;
        details[7] = "" + failedEntries;
        details[8] = "" + positiveResponses;
        details[9] = "" + negativeResponses;
        details[10] = startDate.toString();
        details[11] = endDate.toString();

        return details;
    }

    public int entriesLeftToEstimate(){
        return estimatedEntryCount - currentEntryCount;
    }



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
