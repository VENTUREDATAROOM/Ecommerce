package com.sellerapp.model;

public class AvailableProductResponse {

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AvailableProductResponse(String status) {
		super();
		this.status = status;
	}

	public AvailableProductResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AvailableProductResponse [status=" + status + "]";
	}

}
