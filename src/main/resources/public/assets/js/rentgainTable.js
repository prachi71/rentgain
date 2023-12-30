/**
 * DataTables Advanced (jquery)
 */

'use strict';


// Responsive Table
// --------------------------------------------------------------------
function initDatatables() {
    let dt_responsive_table = document.getElementById("tenantTable")
    if (dt_responsive_table !== undefined) {


        var dt_responsive = dt_responsive_table.DataTable({

            columnDefs: [
                {
                    className: 'control',
                    orderable: false,
                    targets: 0,
                    searchable: false,
                    render: function (data, type, full, meta) {
                        return '';
                    }
                },
                {
                    // Label
                    targets: -1,
                    render: function (data, type, full, meta) {
                        var $status_number = full['status'];
                        var $status = {
                            1: { title: 'Current', class: 'bg-label-primary' },
                            2: { title: 'Professional', class: ' bg-label-success' },
                            3: { title: 'Rejected', class: ' bg-label-danger' },
                            4: { title: 'Resigned', class: ' bg-label-warning' },
                            5: { title: 'Applied', class: ' bg-label-info' }
                        };
                        if (typeof $status[$status_number] === 'undefined') {
                            return data;
                        }
                        return (
                            '<span class="badge ' + $status[$status_number].class + '">' + $status[$status_number].title + '</span>'
                        );
                    }
                }
            ],
            // scrollX: true,
            destroy: true,
            dom: '<"row"<"col-sm-12 col-md-6"l><"col-sm-12 col-md-6 d-flex justify-content-center justify-content-md-end"f>>t<"row"<"col-sm-12 col-md-6"i><"col-sm-12 col-md-6"p>>',
            responsive: {
                details: {
                    display: $.fn.dataTable.Responsive.display.modal({
                        header: function (row) {
                            var data = row.data();
                            return 'Details of ' + data['full_name'];
                        }
                    }),
                    type: 'column',
                    renderer: function (api, rowIdx, columns) {
                        var data = $.map(columns, function (col, i) {
                            return col.title !== '' // ? Do not show row in modal popup if title is blank (for check box)
                                ? '<tr data-dt-row="' +
                                col.rowIndex +
                                '" data-dt-column="' +
                                col.columnIndex +
                                '">' +
                                '<td>' +
                                col.title +
                                ':' +
                                '</td> ' +
                                '<td>' +
                                col.data +
                                '</td>' +
                                '</tr>'
                                : '';
                        }).join('');

                        return data ? $('<table class="table"/><tbody />').append(data) : false;
                    }
                }
            }
        });


    }
}


