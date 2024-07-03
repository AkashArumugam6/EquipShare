package com.csye6220.equipshare.model;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Component
@Entity
@Table(name = "hardware")
public class Hardware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specifications;
    private String description;
    private int count;

    @Column(nullable = false)
    private boolean isDeleted = false; 

    
    @Transient
    private MultipartFile image;
    
    @Lob
    @Column(name = "image",columnDefinition = "MEDIUMBLOB")
    private byte[] imageData;
    

    public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	
	public String generateBase64Image()
	{
	    return Base64.encodeBase64String(this.getImageData());
	}

	public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    
    public Hardware() {
    	super();
    }

	public Hardware(Long id, String name, String specifications, String description, int count) {
		super();
		this.id = id;
		this.name = name;
		this.specifications = specifications;
		this.description = description;
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
    

    


}
