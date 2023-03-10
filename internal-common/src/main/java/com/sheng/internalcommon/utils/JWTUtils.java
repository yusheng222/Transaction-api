package com.sheng.internalcommon.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sheng.internalcommon.dto.TokenResult;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JWTUtils {
    private static final String SIGN = "synpulsechallenge";

    private static final String JWT_IDENTIFIER = "identifier";

    public static TokenResult parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String identifier = verify.getClaim(JWT_IDENTIFIER).asString();

        TokenResult tokenResult = new TokenResult();
        tokenResult.setIdentifier(identifier);

        return tokenResult;
    }

    public static String generateToken(Map<String,String> map){
        // token expiration
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,15);
        Date date = calendar.getTime();

        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);
        // setup the expiration
        builder.withExpiresAt(date);

        // generate token
        return builder.sign(Algorithm.HMAC256(SIGN));
    }

    /**
     * check if token is right
     * @param token
     * @return
     */
    public static TokenResult checkToken(String token) {
        TokenResult tokenResult = null;

        try {
            tokenResult = JWTUtils.parseToken(token);
        } catch (Exception e) {
            System.out.println("Token expired");
        }
        return tokenResult;
    }
}
