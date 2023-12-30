/**
 *  Form Wizard
 */

'use strict';

window.addEventListener("load", (event) => {
    $('[data-toggle="tooltip"]').tooltip();
    let addressField = document.getElementById("ll_address");
    let addressFocus = document.getElementById("ll_unit");
    initAutocomplete(addressField, addressFocus);
});

function openModal(modalId) {
    var myModal = new bootstrap.Modal(document.getElementById(modalId), {
        keyboard: true,
        focus: true
    })
}

function validate(wizardValidationFormStep1, event, form, verticalModernIconsStepper) {
    let inputElements = wizardValidationFormStep1.querySelectorAll("input, select, checkbox, textarea");
    let validateSuccess = true;


    for (let i = 0; i < inputElements.length; i++) {
        if (!inputElements[i].checkValidity()) {
            if (event.target.validity.patternMismatch) {
                event.target.setCustomValidity('Please tell us how we should address you.');
            }
            event.preventDefault()
            event.stopPropagation()
            validateSuccess = false;
        }
    }
    if (validateSuccess) {
        if (form.classList.contains('was-validated')) {
            form.classList.remove('was-validated');
        }
        verticalModernIconsStepper.next();
    } else {
        form.classList.add('was-validated');
    }

}

const tp = [];

function getTenantPropertyFromForm() {
    let lease = {
        llr_prepay: document.getElementById("llr_prepay").checked ? true : false,
        llr_postpay: document.getElementById("llr_postpay").checked ? true : false,
        llr_amount: document.getElementById("llr_amount").value,
        llr_rentdueby: document.getElementById("llr_rentdueby").value,
        llr_leaseenddate: document.getElementById("llr_leaseenddate").value
    }
    let tenants = [];
    let tenant = {
        llp_tenantnumber: document.getElementById("llp_tenantnumber").value,
        lease: lease
    }
    tenants.push(tenant);

    let property = {
        llp_nickname: document.getElementById("llp_nickname").value,
        llp_address: document.getElementById("llp_address").value,
        llp_unit: document.getElementById("llp_unit").value,
        tenants: tenants
    };

    return property;
}

function addMoreButton(cardDiv, row) {
    var addedButton = document.createElement("button");
    addedButton.type = "button";
    addedButton.classList.add("btn");
    addedButton.classList.add("btn-primary");
    addedButton.classList.add("me-1");
    addedButton.innerText = document.getElementById("llp_nickname").value;
    cardDiv.appendChild(addedButton);
    let tenantAndProperty = getTenantPropertyFromForm();
    tp.push(tenantAndProperty)
    row.append(cardDiv);
}

function buildLandLordProfile() {
    let profile = {
        ll_fullname: document.getElementById("ll_fullname").value,
        ll_email: document.getElementById("ll_email").value,
        ll_address: document.getElementById("ll_address").value,
        ll_unit: document.getElementById("ll_unit").value,
        ll_pan: document.getElementById("ll_pan").value,
        ll_mobile: document.getElementById("ll_mobile").value,
        ll_pan_verification: null
    }
    return profile;
}

function buildTenantProfile() {
    let tenant_profile = {
        llp_tenantid: document.getElementById("tenantId").value,
        llp_tenantfullname: document.getElementById("llp_tenantfullname").value,
        llp_tenantemail: document.getElementById("llp_tenantemail").value,
        llp_tenantaddress: document.getElementById("llp_tenantaddress").value,
        //llp_tenantunit: document.getElementById("llp_tenantunit").value,
        llp_tenantpan: document.getElementById("llp_tenantpan").value
    }
    return tenant_profile;
}

function buildLandLordBankAccount() {
    let bankAccount = {
        ll_bacctn: document.getElementById("ll_bacctn").value,
        ll_ifsc: document.getElementById("ll_ifsc").value,
        ll_vacctn: 'NEW'
    }
    return bankAccount;
}

function buildLandLord(llProfile, llBankAccount) {
    let landLord = {
        profile: llProfile,
        bankAccount: llBankAccount,
        properties: tp
    }
    return landLord;
}

function initTenants() {
    if (tp.length === 0) {
        let tenantAndProperty = getTenantPropertyFromForm();
        tp.push(tenantAndProperty)
    }
}

async function fetchHtmlAsText(url) {
    let responsePromise = await fetch(url);
    if(responsePromise.ok) {
        return await (responsePromise).text();
    }else{
        alert("Error : " + responsePromise.statusText)
    }
}

// this is your `load_home() function`
async function loadHome(contentDiv, url, func) {
    contentDiv.innerHTML = await fetchHtmlAsText(url);
    if (func != undefined) {
        func();
    }
}

var DatePicker = {
    hideOldDays: function () { // hide days for previous month
        var x = $('.datepicker .datepicker-days tr td.old');
        if (x.length > 0) {
            x.css('visibility', 'hidden');
            if (x.length === 7) {
                x.parent().hide();
            }
        }
    },
    hideNewDays: function () { // hide days for next month
        var x = $('.datepicker .datepicker-days tr td.new');
        if (x.length > 0) {
            x.hide();
        }
    },
    hideOtherMonthDays: function () { // hide days not for current month
        DatePicker.hideOldDays();
        DatePicker.hideNewDays();
        var x = $('.datepicker .datepicker-dd');
        if (x.length > 0) {
            x.hide();
        }
    }
};

function initDatePickers() {
    // Bootstrap Datepicker
    // --------------------------------------------------------------------
    var llrRentdueby = $('#llr_rentdueby')
    var llrLeasestartdate = $('#llr_leasestartdate')
    var llrLeaseenddate = $('#llr_leaseenddate')

    // Lease end date picker
    if (llrLeaseenddate.length) {
        llrLeaseenddate.datepicker({
            autoclose: true
        })
    }
    // Lease start date picker
    if (llrLeasestartdate.length) {
        llrLeasestartdate.datepicker({
            autoclose: true
        })
    }
}

let offCanvasEl;

function deliveryMethod(type) {
    alert(type)
}

const Error500 = "Houston, we have a problem ! Please try again.";
const url = "/rentgain/";
const url_dcb = "/rentgain-dcb/";

function showSwalToast(responseData) {
    Swal.fire({
        toast: true,
        icon: 'success',
        title: responseData.onSuccess,
        text: responseData.statusTxt,
        position: 'top',
        showConfirmButton: false,
        timer: 2000,
        timerProgressBar: true,
        didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
    })
}
function showSwalConfirm(title, text) {
    Swal.fire({
        title: title,
        text: text,
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes"
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire({
                toast: true,
                icon: 'success',
                title: "Saving your profile.",
                text: "Almost there...",
                position: 'top',
                showConfirmButton: false,
                timer: 1000,
                timerProgressBar: true,
                didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                }
            })
        }
    });
}
function showSwalDialog(responseData) {
    Swal.fire({
        icon: 'success',
        title: responseData.onSuccess,
        showCloseButton: true,
        confirmButtonText: responseData.confirmButtonText, // !== null ? responseData.confirmButtonText : 'Goto WhatsApp',
        footer: '<h6 class=\"text-center\">' + responseData.footerMsg1 + ' <span class=\"text-success\">' + responseData.footerMsg2 + ' </span>' + responseData.footerMsg3 + '</h6>',
        customClass: {
            actions: 'my-actions',
            cancelButton: 'order-1 right-gap',
            confirmButton: 'order-2',
            denyButton: 'order-3',
        }
    }).then((result) => {
        if (result.isConfirmed) {
            window.open(responseData.link)
        } else if (result.isDenied) {
            Swal.fire('Changes are not saved', '', 'info')
        }
    })
}

async function saveNextAction(event, nextAction, back) {
    const data = new FormData(event.target);
    if (event.submitter.classList.contains("dropdown-item")) {
        data.append(event.submitter.getAttribute("name"), event.submitter.getAttribute("value"));
    }
    const formJSON = Object.fromEntries(data.entries());
    formJSON.action = nextAction.dataset.page;
    console.log(formJSON);
    let response = await postData(url + nextAction.dataset.page, formJSON);
    if (response.ok) {
        var responseData = await response.json();
        if (back !== undefined) {
            offCanvasEl.hide();
            showSwalToast(responseData)
            document.getElementById(back).click();
        } else {
            offCanvasEl.hide();
            if (responseData.toast) {
                showSwalToast(responseData);
            } else {
                showSwalDialog(responseData);
            }
        }
    } else {
        var responseData = await response.json();
        Swal.fire({
            title: responseData.onError !== null ? responseData.onError : Error500,
            customClass: {
                actions: 'next-actions',
                cancelButton: 'order-1 right-gap',
                confirmButton: 'order-2',
                denyButton: 'order-3',
            }
        })
    }

}

function loadYears(selectId) {
    let props = document.getElementById(selectId);
    var years = ["2023", "2022"];
    let m;
    for (m in years) {
        var option = document.createElement("option");
        option.text = m;
        props.add(option);
    }

}

function loadMonths(selectId) {
    let props = document.getElementById(selectId);
    var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
    let m;
    for (m in months) {
        var option = document.createElement("option");
        option.text = m;
        props.add(option);
    }

}

function loadTenants(selectId) {
    selectItems("getTenants", selectId, "llr_existing_property", "Tenant");
}

function loadProperties(selectId) {
    selectItems("getProperties", selectId, "llp_nickname", "Property");
}

function selectItems(path, selectId, selectField, selectString) {
    fetch(url + path)
        .then((response) => response.json())
        .then((data) => {
            let props = document.getElementById(selectId);
            var option1 = document.createElement("option");
            option1.value = "";
            option1.text = "Loading..";
            props.add(option1);
            for (let i in data) {
                var option = document.createElement("option");
                option.text = data[i][selectField];
                props.add(option);
            }
            option1.text = "Select " + selectString;
        });
}


function gotoAddProperty() {
    const elementById = document.getElementById('addProperty');
    elementById.setAttribute("data-back", "addTenant");
    elementById.click();
}

function nextAction() {
    const nextActions = document.querySelectorAll('.next-action');
    nextActions.forEach((nextAction) => {
        nextAction.addEventListener('click', function () {
            let formDiv = document.getElementById("offForm");
            loadHome(formDiv, url + nextAction.dataset.page).then(r => {
                    if (nextAction.dataset.postload != undefined) {
                        let fnName = nextAction.dataset.postload.split("-")[0];
                        let fnArg = nextAction.dataset.postload.split("-")[1];
                        window[fnName](fnArg);
                    }

                    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
                    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
                        return new bootstrap.Tooltip(tooltipTriggerEl, {
                            container: formDiv
                        })
                    })
                    let formId = document.getElementById(nextAction.dataset.form);
                    if (nextAction.dataset.offconvas === "modal") {
                        offCanvasEl = new bootstrap.Modal(formId);
                    } else {
                        offCanvasEl = new bootstrap.Offcanvas(formId);
                    }
                    formId.addEventListener('submit', event => {
                        if (validateForm(event) == true) {
                            saveNextAction(event, nextAction, nextAction.dataset.back);
                        }
                    })
                    offCanvasEl.show(formDiv);
                    initDatePickers();
                    initDatatables();
                }
            );
        });
    });
}

// Example POST method implementation:
async function postData(url = "", data = {}) {
    try {
        const response = await fetch(url, {
            method: "POST", // *GET, POST, PUT, DELETE, etc.
            mode: "cors", // no-cors, *cors, same-origin
            cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
            credentials: "same-origin", // include, *same-origin, omit
            headers: {
                "Content-Type": "application/json",
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
            redirect: "follow", // manual, *follow, error
            referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
            body: JSON.stringify(data), // body data type must match "Content-Type" header
        });
        return response;
    } catch (e) {
        let errorReponse = new Response();
        errorReponse.statusText = Error500;
        errorReponse.status = 500;
        return errorReponse;
    }

}
async function saveTenantProfile(event) {
    showBusy(event)
    let lltProfile = buildTenantProfile();
    let response = await postData(url + "updateTenant", lltProfile);
    if (response.ok) {

        let onTenantProfileSuccess = {
                onSuccess : "Welcome to Rentgain, profile saved successfully !",
                confirmButtonText: "Learn more at RentGain", // !== null ? responseData.confirmButtonText : 'Goto WhatsApp',
                footerMsg1 : "Rent invoices including UPI links will be sent via ",
                footerMsg2 : "WhatsApp.",
                footerMsg3 : "Please save us in your contacts.",
                link: "https://www.rentgain.com"
        }
        showSwalDialog(onTenantProfileSuccess);
        // let contentDiv = document.getElementsByClassName("content-wrapper")[0];
        //loadHome(contentDiv, url + "tenant-next", nextAction);
    } else {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Something went wrong!',
            footer: '<a href="">Please try again.</a>'
        })
    }
    removeBusy(event)
}

async function saveLandLord(event) {

    showBusy(event)
    let llProfile = buildLandLordProfile();
    // Make a fetch request
    await fetch(url_dcb + "PANDetailed?pan=" + llProfile.ll_pan)
        .then(response => {
            // Check if the request was successful (status code 200-299)
            if (response.ok) {
                // Parse the JSON response
                return response.json();
            }else{
                return null;
            }
        })
        .then(panVerification => {
            if(panVerification !== null) {
                if(panVerification.verified === true) {
                    let name = panVerification.kycResponse.kycResult.fullName;
                    const modifiedPanName = name.replace(/\s/g, '').toLowerCase();
                    const modifiedEnteredName = llProfile.ll_fullname.replace(/\s/g, '').toLowerCase();
                    if(modifiedPanName === modifiedEnteredName) {
                        let rd = {
                            onSuccess: "PAN verified successfully !",
                            statusTxt: "Verified name on PAN matches entered legal name."
                        }

                        showSwalToast(rd)
                        llProfile.panVerification = panVerification;
                    }else{
                        let title = "The name on your PAN does not match the entered legal name."
                        let text = "Are you ok to use name from PAN ?"

                        showSwalConfirm(title,text);
                    }
                }
            }
        })
        .catch(error => {
            // Handle errors
            console.error('There was a problem with the fetch operation:', error);
        });

    let llBankAccount = buildLandLordBankAccount();
    let landLord = buildLandLord(llProfile, llBankAccount);
    let response = await postData(url + "saveLandLord", landLord);
    if (response.ok) {
        loadHome(document.getElementsByClassName("content-wrapper")[0], url + "next", nextAction);
    }
    removeBusy(event)
}

function showBusy(event) {
    const spinner = event.submitter.querySelector('.spinner-border');
    spinner.classList.remove('d-none');
    event.submitter.disabled = true;
    return spinner;
}

function removeBusy(event) {
    const spinner = event.submitter.querySelector('.spinner-border');
    spinner.classList.add('d-none');
    event.submitter.disabled = false;
}

(function () {
    nextAction();
    const form = document.getElementById("signup_form")
    if (form !== null && form !== undefined) {
        form.addEventListener('submit', event => {

            if (validateForm(event) == true) {
                saveLandLord(event);
            }

        })
    }
    const tenantSignupForm = document.getElementById("tenant_signup_form")

    if (tenantSignupForm !== null && tenantSignupForm !== undefined) {
        tenantSignupForm.addEventListener('submit', event => {

            if (validateForm(event) == true) {
                saveTenantProfile(event);
            }

        })
    }
})();
