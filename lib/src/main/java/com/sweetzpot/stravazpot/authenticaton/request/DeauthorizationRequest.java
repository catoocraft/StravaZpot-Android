package com.sweetzpot.stravazpot.authenticaton.request;

import com.sweetzpot.stravazpot.authenticaton.api.AuthenticationAPI;
import com.sweetzpot.stravazpot.authenticaton.rest.AuthenticationRest;

import retrofit2.Call;

public class DeauthorizationRequest {

    private final AuthenticationRest restService;
    private final AuthenticationAPI api;
    private String token;

    public DeauthorizationRequest(AuthenticationRest restService, AuthenticationAPI api) {
        this.restService = restService;
        this.api = api;
    }

    public DeauthorizationRequest withAccessToken(String token) {
	this.token = token;
	return this;
    }
    
    public Void execute() {
        Call<Void> call = restService.deauthorize(token);
        return api.execute(call);
    }
}
