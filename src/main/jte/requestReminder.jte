@param com.rentgain.model.ui.FormDiv formDiv
@param String monthId = "reminder_rent_month"
@param String yearId = "reminder_rent_year"
@template.formHeader(formDiv = formDiv, selectValues = null)

<div class="row g-3">

    @template.input(formDiv = formDiv)
    @template.months(monthId = monthId)
    @template.years(yearId = yearId)

</div>

@template.submit()

@template.formFooter()