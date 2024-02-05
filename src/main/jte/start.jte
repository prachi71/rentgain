@param com.rentgain.model.ui.Header header
@param boolean isRegistered
@param String mobile
@param String sid
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

    @for(com.rentgain.model.ui.Link l : header.getLinks())
        @if(l.getType() != null)
            <link href="${l.getHref()}" rel="${l.getRel()}" type="${l.getType()}"/>
        @elseif(l.getCrossOrigin() != null)
            <link crossorigin href="${l.getHref()}" rel="${l.getRel()}"/>
        @elseif(l.getLinkClass() != null)
            <link class="${l.getLinkClass()}" href="${l.getHref()}" rel="${l.getRel()}"/>
        @else
            <link href="${l.getHref()}" rel="${l.getRel()}"/>
        @endif
    @endfor

</head>

<body>
@if(!isRegistered)
    @template.signup(mobile = mobile,sid = sid)
@else
    @template.next()
@endif


<!-- Footer -->
<footer class="content-footer footer bg-footer-theme">
    <div class="container-xxl">
        <div class="footer-container d-flex align-items-center justify-content-between py-2 flex-md-row flex-column">
            <div>
                ©
                <script>
                    document.write(new Date().getFullYear());
                </script>
                <a class="fw-semibold" href="https://rentgain.com" target="_blank">RentGain</a>
            </div>
            <div>
                <a class="footer-link me-4" href="https://rentgain.com/tc.html" target="_blank">Terms & Conditions
                </a>


                <a class="footer-link d-none d-sm-inline-block" href="https://rentgain.com/support.html"
                   target="_blank">Support</a>
            </div>
        </div>
    </div>
</footer>
<!-- / Footer -->

<div class="content-backdrop fade"></div>

@for(com.rentgain.model.ui.Script s : header.getScripts())
    <script src="${s.getSrc()}"></script>
@endfor
</body>
</html>