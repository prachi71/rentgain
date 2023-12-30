@param com.rentgain.model.ui.FormDiv formDiv

@for(com.rentgain.model.ui.InputDiv id : formDiv.inputs)

    <div class="${id.getCol()} mt-2">
        <label class="form-label" for="${id.getInputId()}"><span
                    class="bg-white px-1">${id.getLabel()}</span></label>

        @if(id.getInputType().equals("select"))
            <select class="form-select" id="${id.getInputId()}" name="${id.getInputId()}" required>
            </select>
        @else
            <input class="form-control" id="${id.getInputId()}" name="${id.getInputId()}"
                   placeholder="${id.getPlaceHolder()}"
                   type="${id.getInputType()}" required>
        @endif
        <div class="valid-feedback">
            <span class="fa fa-check"></span>
        </div>
        <div class="invalid-feedback">
            ${id.getInvalidFeedBack()}
        </div>
    </div>
@endfor



