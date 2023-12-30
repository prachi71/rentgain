package com.rentgain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentgain.annotations.SendWA;
import com.rentgain.model.*;
import com.rentgain.model.ui.FormDiv;
import com.rentgain.model.ui.Header;
import com.rentgain.model.ui.factory.FormFactory;
import com.rentgain.service.CrudService;
import com.rentgain.utils.Utils;
import gg.jte.TemplateOutput;
import gg.jte.output.StringOutput;
import io.micronaut.context.annotation.Value;
import io.micronaut.core.io.Readable;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.netty.cookies.NettyCookie;
import io.micronaut.views.View;
import io.micronaut.views.jte.HtmlJteViewsRenderer;
import jakarta.inject.Inject;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Controller("/rentgain")
public class RentGainViewController {
    private static final DateFormatSymbols dfs = new DateFormatSymbols();

    @Inject
    HtmlJteViewsRenderer htmlJteViewsRenderer;
    @Inject
    FormFactory formFactory;

    @Inject
    ObjectMapper objectMapper;

    @Value("classpath:ui/head.json")
    private Readable head;

    @Get(uri = "/start")
    @View("start")
    public HttpResponse<?> start(@QueryValue String mobile, @QueryValue String sid) throws Exception {
        Header headers = objectMapper.readValue(head.asInputStream(), Header.class);
        boolean validSession = CrudService.validateSession(sid);
        boolean registered = CrudService.isLandlordRegistered(mobile);

        MutableHttpResponse<Map> ok = HttpResponse.ok(CollectionUtils.mapOf("header", headers, "isRegistered", registered, "mobile", mobile));
        ok.cookie(new NettyCookie("ll_mobile", mobile));
        return ok;
    }

    @Get(uri = "/next")
    @View("start")
    public HttpResponse<?> next(@CookieValue("ll_mobile") String mobile) throws Exception {
        Header headers = objectMapper.readValue(head.asInputStream(), Header.class);
        // isRegistered will be true as cookie value will be set..maybe we still need to verify
        MutableHttpResponse<Map> ok = HttpResponse.ok(CollectionUtils.mapOf("header", headers, "isRegistered", Boolean.TRUE, "mobile", mobile));
        ok.cookie(new NettyCookie("ll_mobile", mobile));
        return ok;
    }

    @Get(uri = "/tenant-next")
    @View("tenant-next")
    public HttpResponse<?> tenantNext(@CookieValue String llt_mobile) throws Exception {
        Header headers = objectMapper.readValue(head.asInputStream(), Header.class);
        MutableHttpResponse<Map> ok = HttpResponse.ok(CollectionUtils.mapOf("header", headers, "isRegistered", true, "mobile", llt_mobile));
        return ok;
    }

    @Get(uri = "/receipt")
    public HttpResponse<?> rentReceipt(@QueryValue String tid) throws Exception {
        Header headers = objectMapper.readValue(head.asInputStream(), Header.class);

        PaymentLink paymentLink = CrudService.findPaymentLink(tid);
        final NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        String rentDue = currencyInstance.format(Double.valueOf(paymentLink.getRent()));
        rentDue = rentDue.substring(0, 1) + " " + rentDue.substring(1);
        paymentLink.setRent(rentDue);
        String rentPaid = currencyInstance.format(Double.valueOf(paymentLink.getTransactionCallbackRequest().getTransactionAmount()));
        rentPaid = rentPaid.substring(0, 1) + " " + rentPaid.substring(1);

        paymentLink.setTenantMobile(Utils.telephoneFormatter(paymentLink.getTenantMobile()));
        paymentLink.setLandlordMobile(Utils.telephoneFormatter(paymentLink.getLandlordMobile()));

        TemplateOutput st = new StringOutput();
        htmlJteViewsRenderer.render("receipt", CollectionUtils.mapOf("header", headers, "paymentLink", paymentLink, "rentPaid", rentPaid), null).writeTo(st.getWriter());
        DownloadReceipt downloadReceipt = new DownloadReceipt();
        downloadReceipt.setData(st.toString());
        downloadReceipt.setFileName("rent_" + System.currentTimeMillis());
        return HttpResponse.ok(downloadReceipt);
    }

    @Get(uri = "/receiptHtml")
    @View("receipt")
    public HttpResponse<?> rentReceiptHtml(@QueryValue String tid) throws Exception {
        PaymentLink paymentLink = CrudService.findPaymentLink(tid);
        Header headers = objectMapper.readValue(head.asInputStream(), Header.class);
        return HttpResponse.ok(CollectionUtils.mapOf("header", headers, "paymentLink", paymentLink));
    }

    @Get(uri = "/download_receipt")
    @View("download_receipt")
    public HttpResponse<?> downLoadRentReceipt(@QueryValue String tid) throws Exception {
        MutableHttpResponse<Map> ok = HttpResponse.ok(CollectionUtils.mapOf("tid", tid));
        return ok;
    }

    @Get(uri = "/signupTenant")
    @View("signupTenant")
    public HttpResponse<?> signupTenant(@QueryValue String tid) throws Exception {

        Header headers = objectMapper.readValue(head.asInputStream(), Header.class);
        MutableHttpResponse<Map> ok = HttpResponse.ok(CollectionUtils.mapOf("header", headers, "isRegistered", false, "tenantId", tid));
        return ok;
    }

    @Get("/saveProperty")
    @View("request")
    public HttpResponse<?> getAddPropertyView() {
        FormDiv formDiv = null;
        try {
            formDiv = formFactory.getPropertyFormDiv();
        } catch (IOException e) {
            e.printStackTrace();
            return HttpResponse.serverError();
        }
        return HttpResponse.ok(CollectionUtils.mapOf("formDiv", formDiv));
    }

    @Get("/requestVerification")
    @View("request")
    public HttpResponse<?> getVerificationView() {
        FormDiv formDiv = null;
        try {
            formDiv = formFactory.getVerificationFormDiv();
        } catch (IOException e) {
            e.printStackTrace();
            return HttpResponse.serverError();
        }
        return HttpResponse.ok(CollectionUtils.mapOf("formDiv", formDiv));
    }

    @Get("/requestRent")
    @View("requestRent")
    public HttpResponse<?> getRequestRentView() {

        try {
            FormDiv formDiv = formFactory.getRequestRentView();
            return HttpResponse.ok(CollectionUtils.mapOf("formDiv", formDiv));
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.serverError();
        }

    }

    @Get("/saveTenant")
    @View("saveTenant")
    @SendWA
    public HttpResponse<?> getSaveTenantView() {
        try {
            FormDiv formDiv = formFactory.getSaveTenant();
            FormDiv formDivLease = formFactory.getLease();
            return HttpResponse.ok(CollectionUtils.mapOf("formDiv", formDiv, "formDivLease", formDivLease));
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.serverError();
        }
    }

    @Get("/requestReminder")
    @View("requestReminder")
    public HttpResponse<?> getRequestReminderView() {
        try {
            FormDiv formDiv = formFactory.getRequestReminder();
            return HttpResponse.ok(CollectionUtils.mapOf("formDiv", formDiv));
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.serverError();
        }
    }

    @Get("/requestReceipt")
    @View("requestReceipt")
    public HttpResponse<?> getRequestReceiptView() {
        try {
            FormDiv formDiv = formFactory.getRequestReceipt();
            return HttpResponse.ok(CollectionUtils.mapOf("formDiv", formDiv));
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.serverError();
        }
    }

    @Get("/requestDeposit")
    @View("requestDeposit")
    public HttpResponse<?> getRequestDepositView() {
        try {
            FormDiv formDiv = formFactory.getRequestDeposit();
            return HttpResponse.ok(CollectionUtils.mapOf("formDiv", formDiv));
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.serverError();
        }
    }

    @Get("/manageTenant")
    @View("modal")
    public HttpResponse<?> getManageTenantView() {
        try {
            Set<String> cols = CollectionUtils.setOf("Tenant Name", "Mobile", "Property", "Rent", "LeaseStart", "LeaseEnd");
            List<Tenant> tenants = CrudService.getTenants("1234567");
            Map<Integer, List<String>> rows = new HashMap<Integer, List<String>>();
            AtomicInteger rowNumber = new AtomicInteger(1);
            tenants.forEach(t -> {
                List<String> rowData = new ArrayList<String>();
                rowData.add(t.getName());
                rowData.add(t.getLlp_tenantnumber());
                rowData.add(t.getLlr_existing_property());
                rows.put(rowNumber.getAndIncrement(), rowData);
            });

            Set<String> colsProperty = CollectionUtils.setOf("Nick Name", "Address");
            List<Property> properties = CrudService.getProperties("1234567");
            Map<Integer, List<String>> rowsProperty = new HashMap<Integer, List<String>>();
            AtomicInteger rowNumber2 = new AtomicInteger(1);
            properties.forEach(p -> {
                List<String> rowData = new ArrayList<String>();
                rowData.add(p.getLlp_nickname());
                rowData.add(p.getLlp_address());
                rowsProperty.put(rowNumber2.getAndIncrement(), rowData);
            });
            return HttpResponse.ok(CollectionUtils.mapOf("cols", cols, "rows", rows, "colsProperty", colsProperty, "rowsProperty", rowsProperty));
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.serverError();
        }
    }


}
