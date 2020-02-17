package com.blackfriday.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.blackfriday.api.DTOs.CampaignDTO;
import com.blackfriday.api.DTOs.CampaignsWithProductsDTO;
import com.blackfriday.api.data.models.CampaignModel;
import com.blackfriday.api.data.models.CampaignsWithProducts;

import services.ICampaignProductsService;
import services.ICampaignService;

@Path("campaigns")
public class CampaignResources {
	
	private ICampaignService campaignService;

	private ICampaignProductsService campaignProductsService;
	
	private static final String EMPTY_DESCRIPTION_OR_NAME = "Please enter name and descripiton!";
	private static final String CAMPAIGN_ADDED_SUCCESSFULLY = "Campaign added successfully!";
	private static final String ERROR_OCCURED_WHEN_CREATING_CAMPAIGN = "Error occured when creating a campaign!";
	private static final String PRODUCT_CAN_NOT_BE_ADDED = "The product cannot be added!";
	private static final String PRODUCT_ADDED_TO_A_CAMPAIGN = "Product added successfully to a campaign!";
	private static final String ENTER_VALID_ID = "Please enter valid id";
	
	@Inject
	public CampaignResources(ICampaignService campaignService, ICampaignProductsService campaignProductsService) {
		this.campaignService = campaignService;
		this.campaignProductsService = campaignProductsService;
	}
	
	@GET
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<CampaignDTO> campaigns = new ArrayList<CampaignDTO>();
		
		List<CampaignModel> activeCampaigns = this.campaignService.getAll();
		
		for(int i =0; i < activeCampaigns.size(); i++) {
			CampaignDTO campaignDto = CampaignDTO.processDataModel(activeCampaigns.get(i));
			
			campaigns.add(campaignDto);
		}
	
		if(campaigns.size() == 0) {
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		
		return Response.ok().entity(campaigns).build();
	}
	
	@POST 
	@RolesAllowed("employee") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCampaign(CampaignDTO campaignDto) {
		if(campaignDto.getDescription().isEmpty() || campaignDto.getName().isEmpty()) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(EMPTY_DESCRIPTION_OR_NAME).build();
		}
		
		CampaignModel campaign = CampaignModel.processObject(campaignDto);
		
		campaign.setActive(true);
		
		boolean addedSuccessfully = this.campaignService.addCampaign(campaign);
		
		Response response = addedSuccessfully ? Response.status(Response.Status.CREATED).entity(CAMPAIGN_ADDED_SUCCESSFULLY).build()
											    : Response.status(Response.Status.CONFLICT).entity(ERROR_OCCURED_WHEN_CREATING_CAMPAIGN).build();
		
		
		return response;
		
	}
	
	@GET
	@Path("/{id}")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCampaign(@PathParam("id") int id) {
		if(id < 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity(ENTER_VALID_ID).build();
		}
		
		CampaignModel dataModel = this.campaignService.getCampaign(id);
		
		CampaignDTO campaign = CampaignDTO.processDataModel(dataModel);
		
		return Response.status(Response.Status.OK).entity(campaign).build();
	}
	
	
	@POST
	@Path("/product")
	@RolesAllowed("employee")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProductToACampaign(CampaignsWithProductsDTO data) {
		
		try {
			this.campaignProductsService.addCampaignToAProducts(data.getCampaignId(), data.getProductId(), data.getDiscountPercentage());
		} catch(Exception e) {
			return Response.status(Response.Status.CONFLICT).entity(PRODUCT_CAN_NOT_BE_ADDED).build();
		}
		
		return Response.status(Response.Status.OK).entity(PRODUCT_ADDED_TO_A_CAMPAIGN).build();
	}
	
}
