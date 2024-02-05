package com.rentgain.controller;

import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.views.jte.HtmlJteViewsRenderer;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Collections;

@Controller
public class ErrorController {

    private HtmlJteViewsRenderer viewsRenderer;

    public ErrorController(HtmlJteViewsRenderer viewsRenderer) {
        this.viewsRenderer = viewsRenderer;
    }

    @Error(status = HttpStatus.BAD_REQUEST, global = true)
    public HttpResponse notFound(HttpRequest request) {
        String link = URLEncoder.encode("https://wa.me/919663927368?text=Welcome back to RentGain" +
                        "to continue please send this message.",
                Charset.defaultCharset());
        return HttpResponse.ok(viewsRenderer.render("was", CollectionUtils.mapOf("link", link), request))
                .contentType(MediaType.TEXT_HTML);
    }


}
