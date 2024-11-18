package CNPM.G14.ems.service;

import CNPM.G14.ems.dto.request.AuthenticationRequest;
import CNPM.G14.ems.dto.request.IntrospectRequest;

import CNPM.G14.ems.dto.request.LogOutRequest;
import CNPM.G14.ems.dto.request.RefreshRequest;
import CNPM.G14.ems.dto.response.AuthenticationResponse;
import CNPM.G14.ems.dto.response.IntrospectResponse;

import com.nimbusds.jose.JOSEException;
import java.text.ParseException;

public interface AuthenticationService {

     IntrospectResponse introspect(IntrospectRequest request)
        throws JOSEException, ParseException;

     AuthenticationResponse authenticate(AuthenticationRequest request);

     AuthenticationResponse refreshToken(RefreshRequest request)
        throws JOSEException, ParseException;

     void logOut(LogOutRequest request) throws ParseException, JOSEException;


}
