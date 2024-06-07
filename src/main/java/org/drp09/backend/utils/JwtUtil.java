package org.drp09.backend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {
  private static final String KEY = "on-demand-driver-solution";
  private static final int VALID_FOR = 1000 * 60 * 60 * 12;

  public static String genToken(Map<String, Object> claims) {
    return JWT.create()
        .withClaim("claims", claims)
        .withExpiresAt(new Date(System.currentTimeMillis() + VALID_FOR))
        .sign(Algorithm.HMAC256(KEY));
  }

  public static Map<String, Object> parseToken(String token) {
    return JWT.require(Algorithm.HMAC256(KEY)).build().verify(token).getClaim("claims").asMap();
  }
}
