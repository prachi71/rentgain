package com.rentgain.cb.decentro.callouts.filters;

import com.rentgain.cb.decentro.DecentroConfiguration;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpHeaders;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.ClientFilterChain;
import io.micronaut.http.filter.HttpClientFilter;
import org.reactivestreams.Publisher;

@Filter("/v2/payments/upi/link")
public class DecentroUpiGenFilter implements HttpClientFilter {

    public DecentroUpiGenFilter() {
        super();
    }

    @Override
    public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
        final MutableHttpHeaders headers = request.getHeaders();

        String Client_ID = "rentgain_prod";
        String Client_Secret = "KkgP4ep6yeDcUwQsGwZlwpEO8CgmyZ0y";

        String Payments_Module_Secret = "abk6MtgX9HYNwdX8tfAGntNp3xpGZC4M";
        String COSMOS_Provider_Secret = "61rjnHuGJEexUFQcU1LYvnz4lP8Bv8mF";

        System.out.println("response from dec : " + request);
        headers.add(DecentroConfiguration.CLIENT_ID, Client_ID)
                .add(DecentroConfiguration.CLIENT_SECRET, Client_Secret)
                .add(DecentroConfiguration.ACCOUNTS_MODULE_SECRET, Payments_Module_Secret)
                .add("provider_secret", COSMOS_Provider_Secret);
        return chain.proceed(request);
    }
}
