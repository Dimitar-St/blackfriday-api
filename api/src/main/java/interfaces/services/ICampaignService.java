package services;

import java.util.List;

import com.blackfriday.api.data.models.CampaignModel;

public interface ICampaignService {
	List<CampaignModel> getAll();
	
	boolean addCampaign(CampaignModel campaign);
	
	CampaignModel getCampaign(int id);
}
