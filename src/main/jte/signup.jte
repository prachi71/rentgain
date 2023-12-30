@param String mobile
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
                                <div class="col-md-6 form-group">
                                    <label class="form-label" for="ll_fullname"><span
                                                class="bg-white px-1">Legal Name</span></label>
                                    <input class="form-control" id="ll_fullname" name="ll_fullname"
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
                                <div class="col-md-6">
                                    <label class="form-label" for="ll_email"><span
                                                class="bg-white px-1">Email</span></label>

                                    <input class="form-control" id="ll_email" name="ll_email"
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
                                    <label class="form-label" for="ll_address"><span
                                                class="bg-white px-1">Address</span></label>

                                    <input class="form-control" id="ll_address"
                                           name="ll_address" placeholder1="1 MG Road.."
                                           required rows="1">
                                    <div class="valid-feedback">
                                        <span class="fa fa-check"></span>
                                    </div>
                                    <div class="invalid-feedback">
                                        Required
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label" for="ll_unit"><span
                                                class="bg-white px-1">House or Apt Number</span></label>

                                    <input Apr House Nbr class="form-control" id="ll_unit"
                                           name="ll_unit" or placeholder1="123" required type="text">
                                    <div class="valid-feedback">
                                        <span class="fa fa-check"></span>
                                    </div>
                                    <div class="invalid-feedback">
                                        Required
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label" for="ll_bacctn"><span
                                                class="bg-white px-1">Bank Account Number</span></label>

                                    <input class="form-control" id="ll_bacctn" name="ll_bacctn"
                                           placeholder1="123456789" required
                                           type="password" maxlength="16" min="9">
                                    <div class="valid-feedback">
                                        <span class="fa fa-check"></span>
                                    </div>
                                    <div class="invalid-feedback">
                                        Required
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label" for="ll_bacctn_match"><span
                                                class="bg-white px-1">Confirm Bank Account Number</span></label>

                                    <input class="form-control" data-match="ll_bacctn" id="ll_bacctn_match"
                                           name="ll_bacctn_match"
                                           placeholder1="Confirm Bank Account Number" required
                                           type="text">
                                    <div class="valid-feedback">
                                        <span class="fa fa-check"></span>
                                    </div>
                                    <div class="invalid-feedback">
                                        Bank account numbers must match
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label" for="ll_ifsc"><span
                                                class="bg-white px-1">Bank IFSC Code</span></label>

                                    <input class="form-control" id="ll_ifsc" name="ll_ifsc"
                                           placeholder="SBIN0123456" required
                                           type="text" pattern="^[A-Z]{4}0[A-Z0-9]{6}$" minlength="11" maxlength="11">
                                    <div class="valid-feedback">
                                        <span class="fa fa-check"></span>
                                    </div>

                                    <div class="invalid-feedback">
                                        Please provide valid IFSC code.
                                        <i
                                                aria-label="The first four characters (SBIN) represent the bank code.
                                                The 5th character (0) is fixed.
                                                The last six characters (123456) are alphanumeric and uniquely identify the bank branch."
                                                class="ti ti-help text-muted"
                                                data-bs-original-title=
                                                "IFSC code is 11 character alpha numeric code. The first four characters (SBIN) represent the bank code.
                                                The 5th character (0) is fixed.
                                                The last six characters (123456) are alphanumeric and uniquely identify the bank branch."

                                                data-bs-placement="bottom"
                                                data-bs-toggle="tooltip"
                                        data-bs-html="true"></i>
                                    </div>
                                </div>
                                <div class="col-md-6">

                                    <label class="form-label" for="ll_pan"><span
                                                class="bg-white px-1">Pan Card Number</span></label>

                                    <input class="form-control" id="ll_pan" name="ll_pan" placeholder="ABCDE1234F" minlength="10" maxlength="10"
                                           required type="text">
                                    <div class="valid-feedback">
                                        <span class="fa fa-check"></span>
                                    </div>
                                    <div class="invalid-feedback">
                                        Please provide valid PAN number.
                                        <i
                                                aria-label= "The PAN card number is a 10-character alphanumeric code, with the following structure:

The first five characters are letters (A-Z).
The next four characters are numerals (0-9).
The last character is again a letter (A-Z).
For example, a valid PAN card number could look like this: ABCDE1234F."
                                                class="ti ti-help text-muted"
                                                data-bs-original-title=
                                                "The PAN card number is a 10-character alphanumeric code, with the following structure:

The first five characters are letters (A-Z).
The next four characters are numerals (0-9).
The last character is again a letter (A-Z).
For example, a valid PAN card number could look like this: ABCDE1234F."

                                                data-bs-placement="top"
                                                data-bs-toggle="tooltip"
                                                data-bs-html="true"></i>

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
                        <div>
                            <input type="hidden" id="ll_mobile" name="ll_mobile" value="${mobile}">
                        </div>

                    </form>
                </div>
            </div>
        </div>


        <!-- Form Alignment -->

    </div>
    <!-- / Content -->
</div>
