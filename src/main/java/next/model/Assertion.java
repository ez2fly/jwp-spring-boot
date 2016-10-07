package next.model;

public class Assertion {
	private String credId;
	private String clientData;
	private String authnrData;
	private String signature;
	private String challenge;
	
	public Assertion() {
	}
	public Assertion(String credId, String clientData, String authnrData, String signature, String challenge) {
		this.credId = credId;
		this.clientData = clientData;
		this.authnrData = authnrData;
		this.signature = signature;
		this.challenge = challenge;
	}
	
	
	public String getCredId() {
		return credId;
	}
	public void setCredId(String credId) {
		this.credId = credId;
	}
	public String getClientData() {
		return clientData;
	}
	public void setClientData(String clientData) {
		this.clientData = clientData;
	}
	public String getAuthnrData() {
		return authnrData;
	}
	public void setAuthnrData(String authnrData) {
		this.authnrData = authnrData;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getChallenge() {
		return challenge;
	}
	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}	
}
