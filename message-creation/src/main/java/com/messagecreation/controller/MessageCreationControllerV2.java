package com.messagecreation.controller;

import com.commons.data.multitenancy.context.TenantContext;
import com.messagecreation.dto.Payload;
import com.messagecreation.entity.MessageTableMongo;
import com.messagecreation.service.MessageCreationServiceV2;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/api/message-creation/v2")
public class MessageCreationControllerV2 {

    @Autowired
    private MessageCreationServiceV2 service;

    private static final String TENANT_ID_HEADER = "X-Tenant";
    private static final String DEFAULT_TENANT = "default";

    @PostMapping("/createMessage")
    public MessageTableMongo createMessage(@RequestBody Payload payload, HttpServletRequest request) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IOException, TemplateException {
        final String tenant = request.getHeader(TENANT_ID_HEADER);

        System.out.println(tenant);

        if (StringUtils.hasText(tenant)) {
            TenantContext.setTenantId(tenant);
            payload.setTanentid(tenant);
        } else {
            TenantContext.setTenantId(DEFAULT_TENANT);
            payload.setTanentid(DEFAULT_TENANT);

        }
        return service.createMessage(payload);
    }
}

