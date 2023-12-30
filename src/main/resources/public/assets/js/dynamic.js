
'use strict';

(function () {
    var waName = document.getElementById('waName');
    var llName = document.getElementById('ll-fullname');
    var waNameFromParam = null;
    var urlString = document.location.href;
    let paramString = urlString.split('?')[1];
    let params_arr = paramString.split('&');
    for(let i = 0; i < params_arr.length; i++) {
        let pair = params_arr[i].split('=');
        if('name' === pair[0]) {
            waName.value = pair[1];
            waNameFromParam = waName;
            llName.value = pair[1]
        }

    }
    if(waNameFromParam === null) {
        waName.textContent  = 'Please enter your details.'
    }else{
        waName.innerHTML = '<strong>' + waNameFromParam.value + '</strong> please enter your details.'
    }
})();



