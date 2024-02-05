package com.rentgain.cb.decentro.callouts.filters;

import com.rentgain.cb.decentro.DecentroConfiguration;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpHeaders;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.ClientFilterChain;
import io.micronaut.http.filter.HttpClientFilter;
import org.reactivestreams.Publisher;

@Filter("/v2/banking/account/settlement_account/**")
public class DecentroSettlementAccountFilter implements HttpClientFilter {

    public DecentroSettlementAccountFilter() {
        super();
    }

    @Override
    public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
        final MutableHttpHeaders headers = request.getHeaders();

        String Client_ID = "rentgain_prod";
        String Client_Secret = "KkgP4ep6yeDcUwQsGwZlwpEO8CgmyZ0y";
        String Accounts_Module_Secret = "gESpZMDPAFGUTrTjJHxEy1A9Iq14ZDIn";//"NfufCwyGFSPb91GmuF0nS6kwr6lqkfNM";
        String provider_Secret = "JyeJTAPBFlx0JFGGtoLYdrMQ30y6vPaX"; // DBSS

        System.out.println("Core Banking filter : " + request);
        headers.add(DecentroConfiguration.CLIENT_ID, Client_ID)
                .add(DecentroConfiguration.CLIENT_SECRET, Client_Secret)
                .add(DecentroConfiguration.ACCOUNTS_MODULE_SECRET, Accounts_Module_Secret)
                .add("provider_secret", provider_Secret);
        return chain.proceed(request);
    }
}
