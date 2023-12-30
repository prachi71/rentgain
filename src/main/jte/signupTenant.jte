@param com.rentgain.model.ui.Header header
@param String tenantId

<!DOCTYPE html>

<html
        class="light-style layout-menu-fixed"
        data-assets-path="/assets/"
        data-template="horizontal-menu-template"
        data-theme="theme-default"
        dir="ltr"
        lang="en"
>
<head>
    <title>${header.getTitle()}</title>

    @for(com.rentgain.model.ui.Metum l : header.getMeta())
        @if(l.getContent() != null)
            <meta content="${l.getContent()}" name="${l.getName()}"/>
        @elseif(l.getCharset() != null)
            <meta charset="${l.getCharset()}"/>
        @endif
    @endfor

    @for(com.rentgain.model.ui.Link l : header.getLinks())
        @if(l.getType() != null)
            <link href="${l.getHref()}" rel="${l.getRel()}" type="${l.getType()}"/>
        @elseif(l.getCrossOrigin() != null)
            <link crossorigin href="${l.getHref()}" rel="${l.getRel()}"/>
        @elseif(l.getLinkClass() != null)
            <link class="${l.getLinkClass()}" href="${l.getHref()}" rel="${l.getRel()}"/>
        @else
            <link href="${l.getHref()}" rel="${l.getRel()}"/>
        @endif
    @endfor

</head>

<body>
<div class="content-wrapper">
    <!-- Content -->

    <div class="container-xxl flex-grow-1 container-p-y">


        <!-- Basic Layout -->


        <!-- Multi Column with Form Separator -->

        <!-- Form with Tabs -->
        <div class="col-12">
            <small class="text-light text-center fw-bold">Lets Get Started</small>
            <div class="bs-stepper vertical wizard-modern wizard-modern-vertical-icons-example mt-2">
                <div class="bs-stepper-header">
                    <div class="step active" data-target="#account-details-vertical-modern">
                        <button aria-selected="true" class="step-trigger" type="button">
                          <span class="bs-stepper-circle">
                            <i class="ti ti-file-description"></i>
                          </span>
                            <span class="bs-stepper-label">
                            <span class="bs-stepper-title">Tenant</span>
                            <span class="bs-stepper-subtitle">Your Details</span>
                          </span>
                        </button>
                    </div>
                    <div class="line"></div>

                </div>
                <div class="bs-stepper-content">
                    <form action="/rentgain/saveTenantProfile" id="tenant_signup_form" method="post" novalidate
                          onsubmit="return false">
                        <!-- Account Details -->
                        <div class="content active dstepper-block" id="account-details-vertical-modern">
                            <div class="row-cols-1 g-3">
                                <div class="col-md-6 mt-3 form-group">
                                    <label class="form-label " for="llp_tenantfullname"><span
                                                class="bg-white px-1">Legal Name</span></label>
                                    <input class="form-control mt-2" id="llp_tenantfullname" name="llp_tenantfullname"
                                           placeholder1="First and Last Name"
                                           required type="text">
                                    <div class="valid-feedback">
                                        <span class="fa fa-check"></span>
                                    </div>
                                    <div class="invalid-feedback">
                                        Required
                                    </div>
                                    <!-- <span class="alert-danger text-center">Is profile and legal name same ?</span> -->
                                </div>

                                <div class="col-md-6 mt-3">
                                    <div class="card mb-6">
                                        <label class="card-header alert-warning">Please provide your <b class="alert-warning">permanent address</b>. <span class="alert-danger">Not to be confused with the address of the place you are renting.</span></label>
                                        <div>

                                            <label class="form-label" for="llp_tenantaddress"><span
                                                        class="bg-white px-1">Permanent Address</span></label>

                                            <textarea class="form-control" id="llp_tenantaddress"
                                                      name="llp_tenantaddress" placeholder1="1 MG Road.."
                                                      required rows="3"></textarea>
                                            <div class="valid-feedback">
                                                <span class="fa fa-check"></span>
                                            </div>
                                            <div class="invalid-feedback">
                                                Required
                                            </div>

                                        </div>
                                        <!--
                                        <div>
                                            <label class="form-label " for="llp_tenantunit"><span
                                                        class="bg-white px-1">House or Apt Number</span></label>

                                            <input Apr House Nbr class="form-control" id="llp_tenantunit"
                                                   name="llp_tenantunit" or placeholder1="123" required type="text">
                                            <div class="valid-feedback">
                                                <span class="fa fa-check"></span>
                                            </div>
                                            <div class="invalid-feedback">
                                                Required
                                            </div>
                                        </div>
                                        -->
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label" for="llp_tenantemail"><span
                                                class="bg-white px-1">Email</span></label>

                                    <input class="form-control" id="llp_tenantemail" name="llp_tenantemail"
                                           placeholder1="any@example.com" required
                                           type="email">
                                    <div class="valid-feedback">
                                        <span class="fa fa-check"></span>
                                    </div>
                                    <div class="invalid-feedback">
                                        Email is required and with the <span class="fa-solid fa-at"></span>
                                    </div>
                                </div>
                                <div class="col-md-6">

                                    <label class="form-label" for="llp_tenantpan"><span
                                                class="bg-white px-1">Pan Card Number</span></label>

                                    <input class="form-control" id="llp_tenantpan" name="llp_tenantpan" placeholder1="PAN Number"
                                           required type="text">
                                    <div class="valid-feedback">
                                        <span class="fa fa-check"></span>
                                    </div>
                                    <div class="invalid-feedback">
                                        Required
                                    </div>


                                </div>

                            </div>
                            <div class="col-sm-12 mt-2 text-center">
                                <button type="reset" class="btn btn-label-secondary mt-2">

                                    <span class="align-left">Clear</span>
                                </button>

                                <button class="btn btn-primary mt-2 "
                                        id="ll_submit">
                                    <span aria-hidden="true" class="spinner-border spinner-border-sm me-2 d-none"
                                          role="status"></span>
                                    <span class="align-right">Submit</span>

                                </button>

                            </div>
                        </div>
                        <input type="hidden" id="tenantId" name="tenantId" value="${tenantId}">
                    </form>
                </div>
            </div>
        </div>

        <!-- Form Alignment -->

    </div>
    <!-- / Content -->


</div>


<!-- Footer -->
<footer class="content-footer footer bg-footer-theme">
    <div class="card mb-6 text-center">
    <p class="alert-success">
    <p class="text-center alert-danger">Caveat Emptor</p>
    <span>By proceeding, you are opting in to receive messages from RentGain's certified WhatsApp Business Account and accepting our <a
                href="https://www.rentgain.com/assets/resources/tc.txt">
                        <span>Terms & Conditions.</span>
                    </a> </span>

    </p>
    <p class="alert-success">
        To ensure the best possible experience with our service, please save us as a contact on <strong>WhatsApp</strong>
        with the name <strong>'RentGain'</strong>.
        Rest assured you will only receive messages pertaining to the service.

    </p>
    </div>
    <div class="container-xxl">
        <div class="footer-container d-flex align-items-center justify-content-between py-2 flex-md-row flex-column">
            <div>
                ©
                <script>
                    document.write(new Date().getFullYear());
                </script>
                2023
                <a class="fw-semibold" href="https://rentgain.com" target="_blank">RentGain</a>
            </div>
            <div>

                <a class="footer-link me-4" href="https://www.rentgain.com/assets/resources/tc.txt" target="_blank">Terms & Conditions
                </a>


                <a class="footer-link d-none d-sm-inline-block" href="https://rentgain.com/support.html"
                   target="_blank">Support</a>
            </div>
        </div>
    </div>
</footer>
<!-- / Footer -->

<div class="content-backdrop fade"></div>

@for(com.rentgain.model.ui.Script s : header.getScripts())
    <script src="${s.getSrc()}"></script>
@endfor
</body>
</html>