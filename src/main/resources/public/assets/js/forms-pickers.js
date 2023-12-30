/**
 * Form Picker
 */

'use strict';

// * Pickers with jQuery dependency (jquery)
$(function () {
    // Bootstrap Datepicker
    // --------------------------------------------------------------------
    var bsDatepickerBasic = $('#rentDueByDayOfMonth')
    var leaseEndDate = $('#leaseEndDate')

    // Lease end date picker
    if (leaseEndDate.length) {
        $('#leaseEndDate').datepicker({
            autoclose: true
        })
    }

    // Rent Due By Date Picker
    if (bsDatepickerBasic.length) {
        $('#rentDueByDayOfMonth').datepicker({
            autoclose: true
        }).on('show', function (e) {
            document.getElementsByClassName("datepicker-switch")[0].style.display = 'none';
            document.querySelector("body > div.datepicker.datepicker-dropdown.dropdown-menu.datepicker-orient-left.datepicker-orient-top > div.datepicker-days > table > thead > tr:nth-child(2) > th.next").style.display = 'none';
            document.querySelector("body > div.datepicker.datepicker-dropdown.dropdown-menu.datepicker-orient-left.datepicker-orient-top > div.datepicker-days > table > thead > tr:nth-child(2) > th.prev").style.display = 'none';
            document.querySelector("body > div.datepicker.datepicker-dropdown.dropdown-menu.datepicker-orient-left.datepicker-orient-top > div.datepicker-days > table > thead > tr:nth-child(3)").style.display = 'none';

        });
        $('#rentDueByDayOfMonth').on('changeDate', function (e) {
            var dom = $('#rentDueByDayOfMonth').val();
            if (dom == '01') {
                $('#rentDueByDayOfMonth').text = '1st of the month';


            }


        });

        bsDatepickerBasic.datepicker({
            todayHighlight: true,
            orientation: isRtl ? 'auto right' : 'auto left'

        })


    }


});
