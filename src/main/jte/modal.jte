@param java.util.Set<String> cols
@param java.util.Map<Integer, java.util.List<String>> rows
@param java.util.Set<String> colsProperty
@param java.util.Map<Integer, java.util.List<String>> rowsProperty
<div class="card-body">
    <div class="row gy-3">


        <!-- Vertically Centered Modal -->
        <div class="col-lg-4 col-md-6">
            <small class="text-light fw-semibold">Vertically centered</small>
            <div class="mt-3">
                <!-- Modal -->
                <div class="modal fade" id="managetenant_form" tabindex="-1" aria-hidden="true" style="display: none;">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="modalCenterTitle">Modal title</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form id="manage">
                                    <div class="nav-align-top mb-4">
                                        <ul class="nav nav-pills mb-3 nav-fill" role="tablist">
                                            <li class="nav-item" role="presentation">
                                                <button type="button" class="nav-link active" role="tab"
                                                        data-bs-toggle="tab" data-bs-target="#navs-pills-justified-home"
                                                        aria-controls="navs-pills-justified-home" aria-selected="true">
                                                    <i class="tf-icons ti ti-home ti-xs me-1"></i> Tenants
                                                    <span class="badge rounded-pill badge-center h-px-20 w-px-20 bg-danger ms-1">3</span>
                                                </button>
                                            </li>
                                            <li class="nav-item" role="presentation">
                                                <button type="button" class="nav-link" role="tab" data-bs-toggle="tab"
                                                        data-bs-target="#navs-pills-justified-profile"
                                                        aria-controls="navs-pills-justified-profile"
                                                        aria-selected="false" tabindex="-1">
                                                    <i class="tf-icons ti ti-user ti-xs me-1"></i> Properties
                                                </button>
                                            </li>
                                        </ul>
                                        <div class="tab-content">
                                            <div class="tab-pane fade show active" id="navs-pills-justified-home"
                                                 role="tabpanel">
                                                <div class="dt-responsive">

                                                    <table id="tenantTable" class="table dt-responsive nowrap" style="width:100%">
                                                        <thead>
                                                        <tr>
                                                            @for(String col : cols)
                                                                <th>${col}</th>
                                                            @endfor
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        @for(Integer r : rows.keySet())
                                                            <tr>
                                                                @for(String rowValue : rows.get(r))
                                                                    <td>${rowValue}</td>
                                                                @endfor
                                                            </tr>
                                                        @endfor
                                                        </tbody>
                                                    </table>

                                                </div>
                                            </div>
                                            <div class="tab-pane fade show active" id="navs-pills-justified-home2"
                                                 role="tabpanel">
                                                <div class="dt-responsive">

                                                    <table id="tenantTable2" class="table dt-responsive nowrap" style="width:100%">
                                                        <thead>
                                                        <tr>
                                                            @for(String col : colsProperty)
                                                                <th>${col}</th>
                                                            @endfor
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        @for(Integer r : rowsProperty.keySet())
                                                            <tr>
                                                                @for(String rowValue : rowsProperty.get(r))
                                                                    <td>${rowValue}</td>
                                                                @endfor
                                                            </tr>
                                                        @endfor
                                                        </tbody>
                                                    </table>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-label-secondary waves-effect"
                                        data-bs-dismiss="modal">
                                    Close
                                </button>
                                <button type="button" class="btn btn-primary waves-effect waves-light">Save changes
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


    </div>
</div>