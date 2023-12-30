/**
 * DataTables Basic
 */

'use strict';

let fv, offCanvasEl;

async function fetchHtmlAsText(url) {
    return await (await fetch(url)).text();
}

// this is your `load_home() function`
async function loadHome(contentDiv, url) {
    contentDiv.innerHTML = await fetchHtmlAsText(url);
}

function nextAction() {
    const nextActions = document.querySelectorAll('.next-action');
    nextActions.forEach((nextAction) => {
        nextAction.addEventListener('click', function () {
            let formDiv = document.getElementById("offForm");
            loadHome(formDiv, nextAction.dataset.page + ".html").then(r => {
                    let formId = document.getElementById("add-new-record");
                    offCanvasEl = new bootstrap.Offcanvas(formId);
                    offCanvasEl.show(formDiv);
                }
            );
        });
    });
}

document.addEventListener('DOMContentLoaded', function (e) {
    (function () {
        setTimeout(() => {
            nextAction();
        }, 200);


        // Form validation for Add new record
        fv = FormValidation.formValidation(formAddNewRecord, {
            fields: {
                basicFullname: {
                    validators: {
                        notEmpty: {
                            message: 'The name is required'
                        }
                    }
                },
                basicPost: {
                    validators: {
                        notEmpty: {
                            message: 'Post field is required'
                        }
                    }
                },
                basicEmail: {
                    validators: {
                        notEmpty: {
                            message: 'The Email is required'
                        },
                        emailAddress: {
                            message: 'The value is not a valid email address'
                        }
                    }
                },
                basicDate: {
                    validators: {
                        notEmpty: {
                            message: 'Joining Date is required'
                        },
                        date: {
                            format: 'MM/DD/YYYY',
                            message: 'The value is not a valid date'
                        }
                    }
                },
                basicSalary: {
                    validators: {
                        notEmpty: {
                            message: 'Basic Salary is required'
                        }
                    }
                }
            },
            plugins: {
                trigger: new FormValidation.plugins.Trigger(),
                bootstrap5: new FormValidation.plugins.Bootstrap5({
                    // Use this for enabling/changing valid/invalid class
                    // eleInvalidClass: '',
                    eleValidClass: '',
                    rowSelector: '.col-sm-12'
                }),
                submitButton: new FormValidation.plugins.SubmitButton(),
                // defaultSubmit: new FormValidation.plugins.DefaultSubmit(),
                autoFocus: new FormValidation.plugins.AutoFocus()
            },
            init: instance => {
                instance.on('plugins.message.placed', function (e) {
                    if (e.element.parentElement.classList.contains('input-group')) {
                        e.element.parentElement.insertAdjacentElement('afterend', e.messageElement);
                    }
                });
            }
        });

        // FlatPickr Initialization & Validation
        flatpickr(formAddNewRecord.querySelector('[name="basicDate"]'), {
            enableTime: false,
            // See https://flatpickr.js.org/formatting/
            dateFormat: 'm/d/Y',
            // After selecting a date, we need to revalidate the field
            onChange: function () {
                fv.revalidateField('basicDate');
            }
        });
    })();
});

