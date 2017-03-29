package com.example.shane.MAV.Interfaces;

import java.util.Date;

/**
 * Created by Shane on 24/03/2017.
 */

public interface questionCampaignInterface {
    public int getCampaignID();
    public String getCampaignName();
    public String getDescription();
    public int getCountryID();
    public int getClientID();
    public int getCurrentEntryCount();
    public int getEstimatedEntryCount();
    public int getFailedEntries();
    public Date getStartDate();
    Date getEndDate();

    public String[] campaignInfo();

    public int entriesLeftToEstimate();

    public Date howLongLeft();
}
