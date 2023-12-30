@param com.rentgain.model.ui.FormDiv formDiv
@param com.rentgain.model.ui.FormDiv formDivLease

@template.formHeader(formDiv = formDiv, selectValues = null)
<div class="row g-3">
    @template.input(formDiv = formDiv)

    <div class="col-md-12">
        <select aria-label="Default select example" class="form-select llr_existing_property"
                id="llr_existing_property" name="llr_existing_property" required>

        </select>
        <div class="valid-feedback">
            <span class="fa fa-check"></span>
        </div>
        <div class="invalid-feedback">
            Property is required
        </div>
    </div>
    <div class="col-md-12">
        <div class="divider divider-dashed">
            <div class="divider-text">Or</div>
        </div>
    </div>
    <div class="col-md-12 mb-3">
        <button class="form-control btn btn-secondary" onclick="gotoAddProperty()"
                type="button"><span><i
                        class="ti ti-plus me-sm-1"></i> <span>Add New Property</span></span>
        </button>
    </div>
    <div class="mb-3">
        <div class="form-check form-check-inline">
            <input checked="" class="form-check-input" id="llr_prepay" name="llr_prepost" type="radio"
                   value="pre" checked>
            <label class="form-check-label" for="llr_prepay">
                Rent Prepaid
            </label>
            <i
                    aria-label="With Pre Pay rent for the current month is due in the same month."
                    class="ti ti-help text-muted"
                    data-bs-original-title="With Pre Pay rent for the current month is due in the same month."
                    data-bs-placement="top"
                    data-bs-toggle="tooltip"></i>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" id="llr_postpay" name="llr_prepost" type="radio"
                   value="post">
            <label class="form-check-label" for="llr_postpay">
                Rent Postpaid

            </label>
            <i aria-label="With Post Pay rent for the current month is due in the next month."
               class="ti ti-help text-muted"
               data-bs-original-title="With Post Pay rent for the current month is due in the next month."
               data-bs-placement="top"
               data-bs-toggle="tooltip"></i>
        </div>
    </div>

    @template.input(formDiv = formDivLease)
</div>
@template.submit()
</div>
</form>
</div>
</div>
