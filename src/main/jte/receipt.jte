@param com.rentgain.model.ui.Header header
@param com.rentgain.model.PaymentLink paymentLink
@param String rentPaid
<!DOCTYPE html>

<html
        class="light-style layout-menu-fixed"
        data-assets-path="/assets/"
        data-template="horizontal-menu-template"
        data-theme="theme-default"
        dir="ltr"
        lang="en"
>
<head>
    <title>${header.getTitle()}</title>

    @for(com.rentgain.model.ui.Metum l : header.getMeta())
        @if(l.getContent() != null)
            <meta content="${l.getContent()}" name="${l.getName()}"/>
        @elseif(l.getCharset() != null)
            <meta charset="${l.getCharset()}"/>
        @endif
    @endfor



</head>

<body>
<h1 style="text-align: center">Rent Receipt</h1>
<hr/>

<div id="receipt" class="invoice-print p-5">
    <div class="d-flex justify-content-between flex-row">
        <div class="mb-4">

            <h4 class="fw-bold">PROPERTY</h4>

            <p class="mb-1">${paymentLink.getPropertyAddress()}</p>


        </div>
        <div>
            <h4 class="fw-bold">RENT INVOICE #${paymentLink.getInvoiceNumber()}</h4>

            <table>
                <tbody>
                <tr>
                    <td class="pe-3">Date Issued:</td>
                    <td><strong>${paymentLink.getDateIssued()}</strong></td>
                </tr>
                <tr>
                    <td class="pe-3">Date Due:</td>
                    <td><strong>${paymentLink.getDateDue()}</strong></td>
                </tr>
                <tr>
                    <td class="pe-3">Date Paid:</td>
                    <td><strong>${paymentLink.getTransactionCallbackRequest().getTimestamp()}</strong></td>
                </tr>


                </tbody>
            </table>
        </div>
    </div>

    <hr/>
    <div class="row">
        <div class="col">
            <h6>To Tenant:</h6>
            <table>
                <tbody>
                <tr>
                    <td class="pe-3">Name:</td>
                    <td><strong>${paymentLink.getTenantName()}</strong></td>
                </tr>
                <tr>
                    <td class="pe-3">PAN:</td>
                    <td>${paymentLink.getTenantPan()}</td>
                </tr>
                <tr>
                    <td class="pe-3">Mobile:</td>
                    <td>${paymentLink.getTenantMobile()}</td>
                </tr>
                <tr>
                    <td class="pe-3">Email:</td>
                    <td>${paymentLink.getTenantEmail()}</td>
                </tr>

                </tbody>
            </table>
        </div>

        <div class="col">
            <h6>From Landlord:</h6>
            <table>
                <tbody>
                <tr>
                    <td class="pe-3">Name:</td>
                    <td><strong>${paymentLink.getLandlordName()}</strong></td>
                </tr>
                <tr>
                    <td class="pe-3">PAN:</td>
                    <td>${paymentLink.getLandlordPan()}</td>
                </tr>
                <tr>
                    <td class="pe-3">Mobile:</td>
                    <td>${paymentLink.getLandlordMobile()}</td>
                </tr>
                <tr>
                    <td class="pe-3">Email:</td>
                    <td>${paymentLink.getLandlordEmail()}</td>
                </tr>

                </tbody>
            </table>
        </div>
        <div class="col mb-5">
            <h6>Payment:</h6>
            <table>
                <tbody>
                <tr>
                    <td class="pe-3">Rent For:</td>
                    <td><strong>${paymentLink.getRentFor()}</strong></td>
                </tr>
                <tr>
                    <td class="pe-3">Rent Due:</td>

                    <td><strong> ${paymentLink.getRent()}</strong></td>
                </tr>
                <tr>
                    <td class="pe-3">Rent Paid:</td>
                    <td><strong>${rentPaid}</strong></td>
                </tr>
                <tr>
                    <td class="pe-3">Payment mode:</td>
                    <td><strong>UPI</strong></td>
                </tr>
                <tr>
                    <td class="pe-3">npciTxnId:</td>
                    <td><strong>${paymentLink.getTransactionCallbackRequest().getNpciTxnId()}</strong></td>
                </tr>
                <tr>
                    <td class="pe-3">Bank ref:</td>
                    <td><strong>${paymentLink.getTransactionCallbackRequest().getBankReferenceNumber()}</strong></td>
                </tr>
                <img src="/assets/images/pifc.png" width="100px">

                </tbody>
            </table>
        </div>
    </div>

    <hr/>
    <br>
    <div class="row">
        <div class="col-12">
            <span class="fw-bold"><strong>Note:</strong></span>
            <span
            >Use the bank reference number to identify corresponding transactions in your banking records.</span
            >
        </div>
    </div>
</div>
<hr/>
<br/>
<br/>
<center><a href="https://rentgain.com"><img src="/assets/images/dark_logo_white_background.jpg" style="width:50%;"></a></center>

</body>
</html>