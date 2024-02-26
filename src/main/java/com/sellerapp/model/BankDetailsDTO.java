package com.sellerapp.model;



public class BankDetailsDTO {

	
	private String accountNumber;
	private String confirmAccountNumber;
	private String bankName;
	private String ifscCode;
	private String accountType;
	private String[] bankDocument;
	
	
   
	public String[] getBankDocument() {
		return bankDocument;
	}
	public void setBankDocument(String[] bankDocument) {
		this.bankDocument = bankDocument;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getConfirmAccountNumber() {
		return confirmAccountNumber;
	}
	public void setConfirmAccountNumber(String confirmAccountNumber) {
		this.confirmAccountNumber = confirmAccountNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	
}
