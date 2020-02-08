package com.blackfriday.api.DTOs;

import com.blackfriday.api.data.models.CampaignModel;
import com.blackfriday.api.data.models.UserModel;

public class CampaignDTO {
	private String name;
	private String description;
	private boolean isActive;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public static CampaignDTO processDataModel(CampaignModel campaign) {
		CampaignDTO campaignDto = new CampaignDTO();
		
		campaignDto.setActive(campaign.isActive());
		campaignDto.setDescription(campaign.getDescription());
		campaignDto.setName(campaign.getName());
		
		return campaignDto;
	}
	
}
