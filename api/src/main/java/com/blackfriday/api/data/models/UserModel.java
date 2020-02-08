package com.blackfriday.api.data.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.blackfriday.api.DTOs.CampaignDTO;
import com.blackfriday.api.DTOs.UserDTO;

@Entity
@DynamicUpdate
@Table(name = "users")
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;
	private String email;
	private String password;
	private String image;
	private String role;
	
	@Column(length = 1000)
	private String token;
	private int card;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	private List<BoughtProducts> orders;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<BoughtProducts> getOrders() {
		return orders;
	}

	public void setOrders(List<BoughtProducts> orders) {
		this.orders = orders;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCard() {
		return card;
	}

	public void setCard(int card) {
		this.card = card;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        UserModel model = (UserModel) o;
        return Objects.equals(username, model.username);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

	public String getToken() {
		return token;
	}

	
	public void setToken(String token) {
		this.token = token;
	}
	
	public static UserModel processObject(UserDTO userDto) {
		UserModel user = new UserModel();

		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setUsername(userDto.getUsername());
		user.setCard(userDto.getCard());
		user.setImage(userDto.getImage());
		user.setRole(userDto.getRole());

		return user;
	}
}
