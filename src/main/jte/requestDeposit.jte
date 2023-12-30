@param com.rentgain.model.ui.FormDiv formDiv
@template.formHeader(formDiv = formDiv, selectValues = null )
@template.input(formDiv = formDiv)
<div class="col-md-12 mt-2">
    <label class="form-label">Deposit Type</label>
    <div class="row">
        <div class="col-md-6">
            <div class="form-check mb-2">
                <input name="deposit_type" value="token" type="radio" id="deposit_token" class="form-check-input">
                <label class="form-check-label" for="deposit_token">Token</label>
            </div>

        </div>
        <div class="col-md-6">
            <div class="form-check mb-2">
                <input name="deposit_type" value="full" type="radio" id="deposit_full" class="form-check-input" checked>
                <label class="form-check-label" for="deposit_full">Full</label>
            </div>
        </div>
    </div>
</div>

@template.submit()
@template.formFooter()