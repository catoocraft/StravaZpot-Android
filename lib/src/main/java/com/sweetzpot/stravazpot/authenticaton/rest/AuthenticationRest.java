package com.sweetzpot.stravazpot.authenticaton.rest;

import com.sweetzpot.stravazpot.authenticaton.model.LoginResult;
import com.sweetzpot.stravazpot.authenticaton.model.Token;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthenticationRest {

    @POST("/oauth/token")
    @FormUrlEncoded
    Call<LoginResult> token(
            @Field("client_id") int clientID,
            @Field("client_secret") String clientSecret,
            @Field("code") String code,
            @Field("grant_type") String grantType);

    @POST("/oauth/token")
    @FormUrlEncoded
    Call<LoginResult> refreshToken(
            @Field("client_id") int clientID,
            @Field("client_secret") String clientSecret,
            @Field("refresh_token") String token,
            @Field("grant_type") String grantType);
    
    @POST("/oauth/deauthorize")
    Call<Void> deauthorize(
            @Field("access_token") String token);
}
