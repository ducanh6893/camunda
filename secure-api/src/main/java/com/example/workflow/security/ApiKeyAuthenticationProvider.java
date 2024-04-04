package com.example.workflow.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.rest.security.auth.AuthenticationProvider;
import org.camunda.bpm.engine.rest.security.auth.AuthenticationResult;

public class ApiKeyAuthenticationProvider implements AuthenticationProvider {
    @Override
    public AuthenticationResult extractAuthenticatedUser(HttpServletRequest httpServletRequest, ProcessEngine processEngine) {
        if(httpServletRequest.getHeader("X-API-KEY")!=null){
            if(httpServletRequest.getHeader("X-API-KEY").equals("secret")){
                return AuthenticationResult.successful("LENDING");

            }
        } else {
            return AuthenticationResult.unsuccessful();
        }
        return null;
    }

    @Override
    public void augmentResponseByAuthenticationChallenge(HttpServletResponse httpServletResponse, ProcessEngine processEngine) {
        httpServletResponse.setHeader("WWW-Authenticate", "Basic realm=\"" + processEngine.getName() + "\"");
    }
}
