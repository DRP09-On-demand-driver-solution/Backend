package org.drp09.backend;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.drp09.backend.utils.JwtUtil;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

public class JwtTest {

  @Test
  public void testGenAndParse() {
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", 1);
    claims.put("email", "alice@abc.com");
    String token = JwtUtil.genToken(claims);

    Map<String, Object> decodedClaims = JwtUtil.parseToken(token);
    assertThat(decodedClaims.get("id"), is(1));
  }

  @Test
  public void testParseWillFailIfTokenExpired() {
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", 1);
    claims.put("email", "alice@abc.com");
    String token = JWT.create()
            .withClaim("claims", claims)
            .withExpiresAt(new Date(System.currentTimeMillis()))
            .sign(Algorithm.HMAC256("123"));

    try {
      JWT.require(Algorithm.HMAC256("123")).build().verify(token);
      fail();
    } catch (JWTVerificationException ignored) {
    }
  }
}
