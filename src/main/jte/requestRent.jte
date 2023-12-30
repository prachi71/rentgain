@import com.rentgain.model.ui.FormDiv
@import com.rentgain.model.ui.InputDiv
@import java.util.List
@import java.util.Map
@param FormDiv formDiv
@param Map<String,List<String>> selectValues
@param String monthId = "request_rent_month"
@param String yearId = "request_rent_year"
@template.formHeader(formDiv = formDiv, selectValues = null)
<div class="row g-3">
    @template.input(formDiv = formDiv)
    @template.months(monthId = monthId)
    @template.years(yearId = yearId)
</div>
@template.submit()
@template.formFooter()