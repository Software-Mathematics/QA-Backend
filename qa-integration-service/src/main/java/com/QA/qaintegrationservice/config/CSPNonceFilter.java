package com.QA.qaintegrationservice.config;

import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component
public class CSPNonceFilter implements Filter {

    public static final String NONCE_ATTRIBUTE = "cspNonce";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String nonce = Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes());

        String cspHeader = "default-src 'self'; " +
                "script-src 'self' https://cdn.rawgit.com https://cdnjs.cloudflare.com https://cdn.jsdelivr.net 'nonce-" + nonce + "' 'unsafe-eval'; " +
                "style-src 'self' https://cdn.rawgit.com https://fonts.googleapis.com https://cdnjs.cloudflare.com https://cdn.jsdelivr.net 'nonce-" + nonce + "'; " +
                "font-src 'self' https://fonts.gstatic.com data:; " +
                "img-src 'self' data:; " +
                "connect-src 'self'; " +
                "frame-src 'self'; " +
                "object-src 'none';";

        httpServletResponse.setHeader("Content-Security-Policy", cspHeader);

        request.setAttribute(NONCE_ATTRIBUTE, nonce);
        chain.doFilter(request, response);
    }

}
