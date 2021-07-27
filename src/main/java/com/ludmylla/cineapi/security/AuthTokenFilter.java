package com.ludmylla.cineapi.security;

import com.ludmylla.cineapi.services.CustomDetailsServiceImpl;
import com.ludmylla.cineapi.utils.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private CustomDetailsServiceImpl customDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt = parseJwt(request);
            if(jwt != null){
                String username = tokenProvider.getUsernameFromToken(jwt);
                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    UserDetails userDetails = customDetailsService.loadUserByUsername(username);
                    if(tokenProvider.validateToken(jwt,userDetails)){
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request){
        String headerAuth = request.getHeader(CommonConstant.AUTHORIZATION);
        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith(CommonConstant.TOKEN_SPLIT)){
            return headerAuth.substring(7, headerAuth.length());
        }
        return null;
    }
}
