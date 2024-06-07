package org.drp09.backend.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.drp09.backend.utils.JwtUtil;
import org.drp09.backend.utils.ThreadLocalUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
      return true;
    }

    String token = request.getHeader("Authorization");

    try {
      Map<String, Object> claims = JwtUtil.parseToken(token);
      ThreadLocalUtil.set(claims);
      return true;
    } catch (Exception e) {
      response.setStatus(401);
      return false;
    }
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    ThreadLocalUtil.remove();
  }
}
