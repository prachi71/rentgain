@param String tid
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body onload="saveReceipt()">
<span id="dl">Downloading</span>
<progress id="pb" value="35" max="100"></progress>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.2/html2pdf.bundle.js">
</script>
<script>
    async function saveReceipt() {
        var createpdf = await fetch("receipt?tid=" + "${tid}").then((response) => {
            return response.json();
        });
        var opt = {
            margin: 10,
            filename: createpdf.fileName,
            image: {type: 'jpeg', quality: 0.98},
            html2canvas: {scale: 3, width: 1200, dpi: 300},
            jsPDF: {unit: 'mm', format: 'A4', orientation: 'p'},
            // Set pagebreaks if you like. It didn't work out well for me.
            // pagebreak: { mode: ['avoid-all'] }
        };

        html2pdf().set(opt).from(createpdf.data).save();
        document.getElementById("dl").textContent = "Download complete";
        document.getElementById("pb").value = 100;
    }
    //# sourceURL=dr.js
</script>
</body>
</html>