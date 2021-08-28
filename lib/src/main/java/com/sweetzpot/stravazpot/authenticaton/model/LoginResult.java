package com.sweetzpot.stravazpot.authenticaton.model;

import com.google.gson.annotations.SerializedName;
import com.sweetzpot.stravazpot.athlete.model.Athlete;
import com.sweetzpot.stravazpot.authenticaton.model.Token;

import java.util.Date;

public class LoginResult {
    @SerializedName("access_token") private String token;
    @SerializedName("refresh_token") private String refreshToken;
    @SerializedName("athlete") private Athlete athlete;
    @SerializedName("expires_at") private long expiresAt;
    @SerializedName("expires_in") private long expiresIn;

    public Token getToken() {
        return new Token(token);
    }

    public String getTokenValue() {
	    return token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public long getExpiresAt() {
	    return expiresAt;
    }

    public long getExpiresIn() {
	    return expiresIn;
    }

    public Athlete getAthlete() {
        return athlete;
    }
}
