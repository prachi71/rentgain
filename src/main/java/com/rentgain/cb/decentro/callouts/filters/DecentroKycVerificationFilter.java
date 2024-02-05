package com.rentgain.cb.decentro.callouts.filters;

import com.rentgain.cb.decentro.DecentroConfiguration;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpHeaders;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.ClientFilterChain;
import io.micronaut.http.filter.HttpClientFilter;
import org.reactivestreams.Publisher;

@Filter("/kyc/**")
public class DecentroKycVerificationFilter implements HttpClientFilter {

    public DecentroKycVerificationFilter() {
        super();
    }

    @Override
    public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
        final MutableHttpHeaders headers = request.getHeaders();

        String Client_ID = "rentgain_prod";
        String Client_Secret = "KkgP4ep6yeDcUwQsGwZlwpEO8CgmyZ0y";
        String KYC_Module_Secret = "UiPwdSsVcginyibCZYCir8LWPHPb4G2H";


        headers.add(DecentroConfiguration.CLIENT_ID, Client_ID)
                .add(DecentroConfiguration.CLIENT_SECRET, Client_Secret)
                .add(DecentroConfiguration.ACCOUNTS_MODULE_SECRET, KYC_Module_Secret);
        return chain.proceed(request);
    }
}
