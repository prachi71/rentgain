package com.rentgain.cb.decentro.callouts.filters;

import com.rentgain.cb.decentro.DecentroConfiguration;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpHeaders;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.ClientFilterChain;
import io.micronaut.http.filter.HttpClientFilter;
import org.reactivestreams.Publisher;

@Filter("/core_banking/**")
public class DecentroBankAccountFilter implements HttpClientFilter {

    public DecentroBankAccountFilter() {
        super();
    }

    @Override
    public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
        final MutableHttpHeaders headers = request.getHeaders();

        String Client_ID = "rentgain_prod";
        String Client_Secret = "KkgP4ep6yeDcUwQsGwZlwpEO8CgmyZ0y";
        String Accounts_Module_Secret = "B4bLZzuVEIE4KNYRPAXw6x45VIjI2qqe";//"NfufCwyGFSPb91GmuF0nS6kwr6lqkfNM";
        String YBL_Provider_Secret = "QQrp78iywB4geWYv4E3o9fNQMvyIGFWv";

        System.out.println("Core Banking filter : " + request);
        headers.add(DecentroConfiguration.CLIENT_ID, Client_ID)
                .add(DecentroConfiguration.CLIENT_SECRET, Client_Secret)
                .add(DecentroConfiguration.ACCOUNTS_MODULE_SECRET, Accounts_Module_Secret)
                .add("provider_secret", YBL_Provider_Secret);
        return chain.proceed(request);
    }
}
