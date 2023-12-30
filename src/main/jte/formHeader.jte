@import com.rentgain.model.ui.FormDiv
@import com.rentgain.model.ui.InputDiv
@import java.util.List
@import java.util.Map
@param FormDiv formDiv
@param Map<String,List<String>> selectValues
<div class="offcanvas offcanvas-end" id="${formDiv.formId}">
    <div class="offcanvas-header border-bottom">
        <h5 class="offcanvas-title" id="exampleModalLabel">${formDiv.titles.get(0)}</h5>
        <button
                aria-label="Close"
                class="btn-close text-reset"
                data-bs-dismiss="offcanvas"
                type="button"
        ></button>
    </div>
    <div class="offcanvas-body flex-grow-1">
        <form id="${formDiv.formId}" novalidate onsubmit="return false">

            <div class="content" id="personal-info-vertical-modern">
                <div class="content-header mb-3">
                    <h6 class="mb-0">${formDiv.titles.get(1)}</h6>
                    <small>${formDiv.titles.get(2)}</small>
                </div>