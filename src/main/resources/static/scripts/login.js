signUpButton

$(document).ready(function () {
    $("#signUpButton").click(function () {
        $(location).attr("href", "/register");
    });
});