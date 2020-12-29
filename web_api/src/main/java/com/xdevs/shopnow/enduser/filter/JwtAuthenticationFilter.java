package com.xdevs.shopnow.enduser.filter;

import com.google.gson.Gson;
import com.xdevs.shopnow.enduser.common.BusinessResponse;
import com.xdevs.shopnow.enduser.common.ServiceError;
import com.xdevs.shopnow.enduser.sys.entities.Customer;
import com.xdevs.shopnow.enduser.sys.repository.UserCmsRepo;
import com.xdevs.shopnow.enduser.utils.JwtTokenProvider;
import com.xdevs.shopnow.enduser.utils.LogFormatUtils;
import com.xdevs.shopnow.enduser.common.GlobalValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Resource
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserCmsRepo userCmsRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestId = (String) httpServletRequest.getAttribute(GlobalValue.REQUEST_PREFIX);
        try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                String username = tokenProvider.getUserNameFromJWT(jwt);
                Customer userDetails = userCmsRepo.findByUserName(username);
                if (userDetails == null) {
                    throw new UsernameNotFoundException(username);
                }
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, Collections.emptyList()));
            }
        }
        catch (Exception e){
            LogFormatUtils.info(requestId, "Validate token fail !", e);
            unsuccessfulToken(requestId, request, response, e);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private void unsuccessfulToken(String requestId, HttpServletRequest request, HttpServletResponse response, Exception ex) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers,LdapType");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(new Gson().toJson(new BusinessResponse(requestId, ServiceError.Types.TOKEN_INVALID, ex.getMessage())));
    }
}
