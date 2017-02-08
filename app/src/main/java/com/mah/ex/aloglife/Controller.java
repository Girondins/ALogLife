package com.mah.ex.aloglife;

/**
 * Created by Girondins on 2017-01-30.
 */

public class Controller {
    private String authCode;
    private ApiConnector api;

    public Controller(){
    }

    public void setAuthCode(String authCode){
        this.authCode = authCode;
        api = new ApiConnector(authCode);
        getUserToken();
    }

    public void getUserToken(){
        api.authorize(authCode);
    }

}
