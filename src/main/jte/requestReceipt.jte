@param com.rentgain.model.ui.FormDiv formDiv

@template.formHeader(formDiv = formDiv, selectValues = null)

<div class="row g-3">
    @template.input(formDiv = formDiv)
    @template.months(monthId = "receipt_rent_month")
    @template.years(yearId = "receipt_rent_year")
    <div class="col-sm-12">
        <div class="btn-group">
            <button aria-expanded="false"
                    class="btn btn-primary dropdown-toggle waves-effect waves-light"
                    data-bs-toggle="dropdown" type="button">
                Receipt Via
            </button>
            <ul class="dropdown-menu" name="dm">
                <li title="Send to tenant via WhatsApp"><input class="dropdown-item" name="receipt_deliver_method"
                                                               type="submit"
                                                               value="Whatsapp"></li>
                <li title="Send to tenant via Email"><input class="dropdown-item" name="receipt_deliver_method"
                                                            type="submit"
                                                            value="Email"></li>
                <li title="Download and save"><input class="dropdown-item" name="receipt_deliver_method"
                                                     type="submit" value="Download">
                </li>
            </ul>
        </div>
        <button class="btn btn-outline-secondary" data-bs-dismiss="offcanvas" type="reset">Cancel
        </button>
    </div>
</div>



@template.formFooter()