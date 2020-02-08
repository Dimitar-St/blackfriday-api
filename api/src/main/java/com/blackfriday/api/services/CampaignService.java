package com.blackfriday.api.services;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.blackfriday.api.data.models.CampaignModel;

import database.IDatabase;
import services.ICampaignService;

public class CampaignService implements ICampaignService {
	private EntityManager entityManager;

	@Inject
	public CampaignService(IDatabase database) {
		this.entityManager = database.createEntityManager();
	}
	
	public List<CampaignModel> getAll() {
		entityManager.getTransaction().begin();
		
		List<CampaignModel> campaigns = entityManager.createQuery("SELECT c FROM CampaignModel c WHERE c.isActive = true").getResultList();
		
//		for(int i = 0; i < campaigns.size(); i++) {
//			System.out.println(campaigns.get(i).isActive());
//			
//			if(campaigns.get(i).isActive() == false) {
//				campaigns.remove(i);
//			}
//		}
		
		entityManager.getTransaction().commit();
		
		return campaigns;
	}
	
	public boolean addCampaign(CampaignModel campaign) {
		boolean addedSuccessfully = true;
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(campaign);
		
		entityManager.getTransaction().commit();
		
		return addedSuccessfully;
	}

	public CampaignModel getCampaign(int id) {
		CampaignModel campaign = null;
		
		entityManager.getTransaction().begin();
		
		campaign = (CampaignModel) entityManager.createQuery("SELECT c FROM CampaignModel c WHERE c.id LIKE :id")
					 .setParameter("id", id)
					 .getSingleResult();
		
		entityManager.getTransaction().commit();
		
		return campaign;
	}
	
}
