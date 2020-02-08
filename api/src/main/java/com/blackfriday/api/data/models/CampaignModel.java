package com.blackfriday.api.data.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.blackfriday.api.DTOs.CampaignDTO;

@Entity
@DynamicUpdate
@Table(name = "campaigns")
public class CampaignModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private boolean isActive;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "campaign", cascade = CascadeType.ALL)
	private List<CampaignsWithProducts> campaigns;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public static CampaignModel processObject(CampaignDTO campaignDto) {
		CampaignModel campaign = new CampaignModel();

		campaign.setActive(campaignDto.isActive());
		campaign.setDescription(campaignDto.getDescription());
		campaign.setName(campaignDto.getName());

		return campaign;
	}
}
