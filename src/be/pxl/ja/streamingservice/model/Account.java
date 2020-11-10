package be.pxl.ja.streamingservice.model;

import be.pxl.ja.streamingservice.exception.TooManyProfilesException;
import be.pxl.ja.streamingservice.util.PasswordUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Account {
	private String email;
	private String password;
	private PaymentInfo paymentInfo;
	private StreamingPlan streamingPlan;
	private HashMap<String,Profile> profiles = new HashMap<String,Profile>();

	public Account(String email, String password) {
		this.email = email;
		setPassword(password);
		profiles.put("Profile1", new Profile("Profile1"));
	}

	public void setStreamingPlan(StreamingPlan streamingPlan) {
		this.streamingPlan = streamingPlan;
	}

	public void addProfile(Profile profile) {
		if(profiles.size() < streamingPlan.getNumberOfScreens()) {
			profiles.put(profile.getName(), profile);
		}
		else {
			throw new TooManyProfilesException("Maximum number of profiles in use, cannot create new profile");
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean verifyPassword(String password) {
		return PasswordUtil.isValid(password, this.password);
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public void setPassword(String password) {
		this.password = PasswordUtil.encodePassword(password);
	}

	public Profile getProfile(Profile profile) {
		return profiles.get(profile.getName());
	}
}
