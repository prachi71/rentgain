/**
 * Main
 */

'use strict';
var autotype = document.getElementById('autotype');

var typewriter = new Typewriter(autotype, {
  loop: true
});

typewriter.typeString('Tenant <strong class="at">Verification</strong> !')
    .deleteAll().
typeString('Rent <strong class="at">Payments</strong> !')
    .pauseFor(2500)
    .deleteChars(10)
    .typeString('<strong class="at">Receipts</strong> !')
    .pauseFor(2500)
    .deleteChars(10)
    .typeString('<strong class="at">Reminders</strong> !')
    .pauseFor(2500)
    .deleteChars(11)
    .typeString('<strong class="at">Tracking</strong> !')
    .pauseFor(2500)
    .start();

