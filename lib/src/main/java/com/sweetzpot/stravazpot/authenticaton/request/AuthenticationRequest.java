package com.sweetzpot.stravazpot.authenticaton.request;

import com.sweetzpot.stravazpot.authenticaton.api.AuthenticationAPI;
import com.sweetzpot.stravazpot.authenticaton.model.LoginResult;
import com.sweetzpot.stravazpot.authenticaton.rest.AuthenticationRest;
import com.sweetzpot.stravazpot.authenticaton.model.AppCredentials;

import retrofit2.Call;

public class AuthenticationRequest {

    private final AppCredentials appCredentials;
    private final AuthenticationRest restService;
    private final AuthenticationAPI api;
    private String code;
    private String refreshToken;

    public AuthenticationRequest(AppCredentials appCredentials, AuthenticationRest restService, AuthenticationAPI api) {
        this.appCredentials = appCredentials;
        this.restService = restService;
        this.api = api;
    }

    public AuthenticationRequest withCode(String code) {
        this.code = code;
        return this;
    }

    public AuthenticationRequest withRefreshToken(String token) {
	    this.refreshToken = token;
        return this;
    }

    public LoginResult execute() {
        Call<LoginResult> call;
	if (code != null) {
	    call = restService.token(appCredentials.getClientID(),
				     appCredentials.getClientSecret(),
				     code,
				     "authorization_code");
	}
	else {
	    call = restService.refreshToken(appCredentials.getClientID(),
					    appCredentials.getClientSecret(),
					    refreshToken,
					    "refresh_token");
	}
        return api.execute(call);
    }
}
