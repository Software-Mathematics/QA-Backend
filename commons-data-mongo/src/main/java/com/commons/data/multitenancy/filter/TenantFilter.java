package com.commons.data.multitenancy.filter;

import com.commons.data.multitenancy.context.TenantContext;
import com.commons.data.multitenancy.exception.TenantNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: Gaurav Sharma
 */
@AllArgsConstructor
@Slf4j
@Component
public class TenantFilter extends OncePerRequestFilter {

    private static final String TENANT_ID_HEADER = "X-Tenant";
    private static final String DEFAULT_TENANT = "default";

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
            throws IOException, ServletException,TenantNotFoundException {
        final String tenant = request.getHeader(TENANT_ID_HEADER);

        if (StringUtils.hasText(tenant)) {
            TenantContext.setTenantId(tenant);
        } else {
            TenantContext.setTenantId(DEFAULT_TENANT);
        }
        filterChain.doFilter(request, response);
    }
}
