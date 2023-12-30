package com.rentgain.cb.decentro.callouts.filters;

import com.rentgain.cb.decentro.DecentroConfiguration;
import com.rentgain.cb.decentro.callouts.config.DecentroConfig;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpHeaders;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.ClientFilterChain;
import io.micronaut.http.filter.HttpClientFilter;
import jakarta.inject.Inject;
import org.reactivestreams.Publisher;

@Filter("/v2/banking/account/virtual")
public class DecentroAccountHeaderFilter implements HttpClientFilter {
    public DecentroAccountHeaderFilter() {

    }

    @Inject
    DecentroConfig decentroConfig;

    @Override
    public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
        final MutableHttpHeaders headers = request.getHeaders();
        headers.add(DecentroConfiguration.CLIENT_ID, decentroConfig.getClient_id())
                .add(DecentroConfiguration.CLIENT_SECRET, decentroConfig.getClient_secret())
                .add(DecentroConfiguration.ACCOUNTS_MODULE_SECRET, decentroConfig.getAccounts_module_secret());
        return chain.proceed(request);
    }


}
