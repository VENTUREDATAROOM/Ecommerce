package com.sellerapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="profile_details")
public class ProfileEntity {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="profile_id")
	private Long id;
	@Column(name="date_of_birth")
	private String dateofbirth;
	@Column(name="profile_pic")
	private String[] profilePic;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String[] getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String[] profilePic) {
		this.profilePic = profilePic;
	}
	
	

}
