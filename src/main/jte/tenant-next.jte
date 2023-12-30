<div class="content-wrapper px-4 mt-5">
    <!-- Content -->
    <div class="row mb-5">

        <div class="col-md-6 col-lg-3 mb-3">
            <div class="card text-center">
                <div class="card">
                    <div class="card-body">
                        <p class="card-text">Tenant Profile</p>
                        <button id="signupTenant" data-form="signupTenant_form" data-page="signupTenant" aria-controls="DataTables_Table_0" class="btn btn-secondary next-action btn-primary"
                                tabindex="0" type="button"><span><i
                                        class="ti ti-user me-sm-1"></i> <span
                                >Manage Profile</span></span>
                        </button>

                    </div>
                </div>
            </div>
        </div>


        <div class="col-md-6 col-lg-3 mb-3">
            <div class="card text-center">
                <div class="card-body">
                    <p class="card-text">Request Rent Receipt</p>
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
                    <p class="card-text">Rent Paid Proof</p>
                    <button data-form="rentproof_form" data-page="rentProof" aria-controls="DataTables_Table_0" class="btn btn-secondary next-action btn-primary"
                            tabindex="0" type="button"><span><i
                                    class="ti ti-upload"></i> <span>Upload</span></span>
                    </button>
                </div>
            </div>
        </div>

    </div>
    <div id="offForm"></div>


    <div class="content-backdrop fade"></div>
</div><!-- Core JS -->
