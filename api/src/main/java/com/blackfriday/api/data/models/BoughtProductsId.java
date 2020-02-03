package com.blackfriday.api.data.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BoughtProductsId implements Serializable {

	@Column(name = "product_id")
    private int productId;


	@Column(name = "user_id")
    private int userId;
    
    public BoughtProductsId() {}

    public BoughtProductsId(int productId, int userId) {
    	this.productId = productId;
    	this.userId = userId;
    }
    
    public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        BoughtProductsId that = (BoughtProductsId) o;
        return Objects.equals(productId, that.productId) &&
               Objects.equals(userId, that.userId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(productId, userId);
    }
    
}
