<div class="content-wrapper px-4 mt-5">
    <!-- Content -->
    <div class="row mb-5">

        <div class="col-md-6 col-lg-3 mb-3">
            <div class="card text-center">
                <div class="card">
                    <div class="card-body">
                        <p class="card-text">Tenant Signup</p>
                        <button id="signupTenant" data-form="signupTenant_form" data-page="signupTenant" aria-controls="DataTables_Table_0" class="btn btn-secondary next-action btn-primary"
                                tabindex="0" type="button"><span><i
                                        class="ti ti-user me-sm-1"></i> <span
                                >Tenant Signup</span></span>
                        </button>

                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-6 col-lg-3 mb-3">
            <div class="card text-center">
                <div class="card">
                    <div class="card-body">
                        <p class="card-text">Add Tenant And Property</p>
                        <button id="addTenant" data-form="addtandp_form" data-page="saveTenant" data-postload="loadProperties-llr_existing_property" aria-controls="DataTables_Table_0" class="btn btn-secondary next-action btn-primary"
                                tabindex="0" type="button"><span><i
                                        class="ti ti-user me-sm-1"></i> <span
                                >Add</span></span>
                        </button>

                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3 mb-3">
            <div class="card text-center">
                <div class="card-body">
                    <p class="card-text">Verify Tenant</p>
                    <button data-form="verify_form" data-page="requestVerification" aria-controls="DataTables_Table_0" class="btn btn-secondary next-action btn-primary"
                            tabindex="0" type="button"><span><i
                                    class="ti ti-id-badge me-sm-1"></i> <span>Verify</span></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3 mb-3">
            <div class="card text-center">
                <div class="card">
                    <div class="card-body">
                        <p class="card-text">Add Property</p>
                        <button id="addProperty" data-form="addp_form" data-page="saveProperty" aria-controls="DataTables_Table_0" class="btn btn-secondary next-action btn-primary"
                                tabindex="0" type="button"><span><i
                                        class="ti ti-home me-sm-1"></i> <span
                                >Add</span></span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3 mb-3">
            <div class="card text-center">
                <div class="card-body">
                    <p class="card-text">Send Reminder</p>
                    <button data-form="rentreminder_form" data-page="requestReminder" data-postload="loadTenants-reminder_tenant" aria-controls="DataTables_Table_0" class="btn btn-secondary next-action btn-primary"
                            tabindex="0" type="button"><span><i
                                    class="ti ti-calendar me-sm-1"></i> <span>Send</span></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3 mb-3">
            <div class="card text-center">
                <div class="card-body">
                    <p class="card-text">Rent Receipt</p>
                    <button data-form="getreceipt_form" data-page="requestReceipt" data-postload="loadTenants-receipt_tenant" aria-controls="DataTables_Table_0" class="btn btn-secondary next-action btn-primary"
                            tabindex="0" type="button"><span><i
                                    class="ti ti-download me-sm-1"></i> <span>Get</span></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3 mb-3">
            <div class="card text-center">
                <div class="card-body">
                    <p class="card-text">Request Deposit</p>
                    <button data-form="deposit_form" data-page="requestDeposit" aria-controls="DataTables_Table_0" class="btn btn-secondary next-action btn-primary"
                            tabindex="0" type="button"><span><i
                                    class="ti ti-check"></i> <span>Send</span></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3 mb-3">
            <div class="card text-center">
                <div class="card-body">
                    <p class="card-text">Request Rent</p>
                    <button data-form="requestrent_form" data-page="requestRent" data-postload="loadTenants-request_rent_tenant" aria-controls="DataTables_Table_0" class="btn btn-secondary next-action btn-primary"
                            tabindex="0" type="button"><span><i
                                    class="ti ti-check"></i> <span>Request</span></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3 mb-3">
            <div class="card text-center">
                <div class="card-body">
                    <p class="card-text">Rent Paid Proof</p>
                    <button data-form="rentproof_form" data-page="rentProof" aria-controls="DataTables_Table_0" class="btn btn-secondary next-action btn-primary"
                            tabindex="0" type="button"><span><i
                                    class="ti ti-upload"></i> <span>Upload</span></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-lg-3 mb-3">
            <div class="card text-center">
                <div class="card-body">
                    <p class="card-text">Manage Tenants</p>
                    <button data-form="managetenant_form" data-page="manageTenant" data-offconvas="modal" aria-controls="DataTables_Table_0" class="btn btn-secondary next-action btn-primary"
                            tabindex="0" type="button"><span><i
                                    class="ti ti-upload"></i> <span>Manage Tenants</span></span>
                    </button>
                </div>
            </div>
        </div>

    </div>
    <div id="offForm"></div>


    <div class="content-backdrop fade"></div>
</div><!-- Core JS -->
