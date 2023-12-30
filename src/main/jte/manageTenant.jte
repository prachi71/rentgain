@param java.util.Set<String> columns
@param java.util.Map<Integer, java.util.List<String>> rows
    <div class="offcanvas offcanvas-end" id="managetenant_form">
<div class="offcanvas-header border-bottom">
    <h5 class="offcanvas-title" id="exampleModalLabel">"manageTenant"</h5>
    <button
            aria-label="Close"
            class="btn-close text-reset"
            data-bs-dismiss="offcanvas"
            type="button"
    ></button>
</div>
    <div class="offcanvas-body flex-grow-1">
    <form id="managetenant_form">

    <div class="card-datatable table-responsive">

        <table class="table dt-responsive">
            <thead>
            <tr>
                @for(String col : columns)
                    <th scope="col">${col}</th>
                @endfor


            </tr>
            </thead>
            <tbody>
            @for(Integer r : rows.keySet())
                <tr>
                    <th scope="row">${r}</th>
                    @for(String rowValue : rows.get(r))
                        <td>${rowValue}</td>
                    @endfor
                </tr>
            @endfor
            </tbody>
        </table>

    </div>
@template.formFooter()