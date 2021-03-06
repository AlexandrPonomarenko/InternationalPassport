package com.InternationalPassport.security.afterSignIn;

import com.InternationalPassport.security.helper.SecurityUserHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LogManager.getLogger(MyAuthenticationSuccessHandler.class);

    private RedirectStrategy strategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityUserHelper securityUserHelper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        handle(httpServletRequest, httpServletResponse, authentication);
        clearAuthenticationAttributes(httpServletRequest);
        securityUserHelper.show();
        HttpSession session =  httpServletRequest.getSession(false);
        session.setAttribute("BRO", 123123);
    }

    protected void handle(HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        logger.debug("target URL " + targetUrl);
        if (httpServletResponse.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to "
                    + targetUrl);

            return;
        }
        strategy.sendRedirect(httpServletRequest, httpServletResponse, targetUrl);
    }

    protected String determineTargetUrl(final Authentication authentication) {
        Map<String, String> roleTargetUrlMap = new HashMap<>();
        roleTargetUrlMap.put("ROLE_User", "/home");
        roleTargetUrlMap.put("ROLE_Manager", "/control");
        roleTargetUrlMap.put("ROLE_CEO", "/ceo");

        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            String authorityName = grantedAuthority.getAuthority();
            if(roleTargetUrlMap.containsKey(authorityName)) {
                return roleTargetUrlMap.get(authorityName);
            }
        }

        throw new IllegalStateException();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
