package com.csye6220.equipshare.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;

@Entity
@Table(name = "hardwareRequests")
public class HardwareRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Hardware hardware;

    private String store;
    
    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;
    
    @Column(nullable = false)
    private LocalDateTime requestDate;


    public HardwareRequest() {
    	super();
    }
    
    
    public HardwareRequest(Long requestId, User user, Hardware hardware, String store, RequestStatus status,
			LocalDateTime requestDate) {
		super();
		this.requestId = requestId;
		this.user = user;
		this.hardware = hardware;
		this.store = store;
		this.status = status;
		this.requestDate = requestDate;
	}


	@PrePersist
    protected void onCreate() {
        requestDate = LocalDateTime.now();
    }


    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }



	public RequestStatus getStatus() {
		return status;
	}



	public void setStatus(RequestStatus status) {
		this.status = status;
	}


    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Hardware getHardware() {
        return hardware;
    }

    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}



