package com.rentgain.model;

import io.micronaut.core.annotation.Introspected;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Introspected
@Serdeable
public class TenantInvite {
    private String tenantId;
    private String tenantMobile;
    private Boolean sent;
    private String landLordMobile;
    private String landLordName;
    private String inviteId;
    private String propertyNickName;
}
