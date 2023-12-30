function validateForm(event) {

    const customValidation = new Map();
    let form = event.submitter.form;
    let inputElements = form.elements; //querySelectorAll("input, select, checkbox, textarea");
    let validateSuccess = true;
    const fieldValidationFailure = new Map();
    for (let i = 0; i < inputElements.length; i++) {
        let inputElement = inputElements[i];
        if ((inputElement.nodeName !== "BUTTON")) {
            event.preventDefault()
            event.stopPropagation()
            let match = inputElement.dataset.match;
            if (match !== undefined) {
                let matchField = match;
                if (document.getElementById(matchField).value !== inputElement.value) {
                    customValidation.set(inputElement, "Account Numbers Must Match");
                } else {
                    if (customValidation.has(inputElement)) {
                        customValidation.delete(inputElement);
                    }
                }
            } else {
                validateSuccess = inputElement.checkValidity();
                if(validateSuccess === false) {
                    fieldValidationFailure.set(inputElement, validateSuccess)
                }
            }
        }
    }

    if (customValidation.size > 0) {
        validateSuccess = false;
        for (let [key, value] of customValidation) {
            key.setCustomValidity(value);
        }
    }


    if (fieldValidationFailure.size === 0) {
        if (form.classList.contains('was-validated')) {
            form.classList.remove('was-validated');
        }
    } else {
        validateSuccess = false;
        form.classList.add('was-validated');
    }
    return validateSuccess;

}
