@param String mobile
@param String sid
<div class="content-wrapper">
    <!-- Content -->

    <div class="container-xxl flex-grow-1 container-p-y">


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
                            <span class="bs-stepper-title">Landlord</span>
                            <span class="bs-stepper-subtitle">Your Details</span>
                          </span>
                        </button>
                    </div>
                    <div class="line"></div>

                </div>
                <div class="bs-stepper-content">
                    <form action="/landlord-next.html" id="signup_form" method="post" novalidate
                          onsubmit="return false">
                        <!-- Account Details -->
                        <div class="content active dstepper-block" id="account-details-vertical-modern">
                            <div class="row g-3">
                                @template.legalName()
                                <!-- Email -->
                                @template.email()
                                <!-- Address Details -->
                                @template.address()

                                <!-- Bank Account Details && Pan Details -->
                                @template.bankDetails(mobile = mobile, panIncluded = true)


                            </div>
                            @template.formSubmit()

                        </div>
                        f
                    </form>
                </div>
            </div>
        </div>


        <!-- Form Alignment -->

    </div>
    <!-- / Content -->
</div>
