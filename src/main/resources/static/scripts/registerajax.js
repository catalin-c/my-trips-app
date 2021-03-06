$(document).ready(function () {

    $.validate({
        modules: 'security',
        // modules : 'html5',
        onError: function () {
            // alert('Validation of form failed!');
        },
        onSuccess: function () {
            var firstName = $("#firstName").val();
            var lastName = $("#lastName").val();
            var email = $("#email").val();
            var city = $("#city").val();
            var address = $("#address").val();
            var phone = $("#phone").val();
            var password = $("#password").val();

            var userDetails = {};

            userDetails['firstName'] = firstName;
            userDetails['lastName'] = lastName;
            userDetails['email'] = email;
            userDetails['city'] = city;
            userDetails['phone'] = phone;
            userDetails['address'] = address;
            userDetails['passwordHash'] = password;
            userDetails['role'] = "USER";

            $.ajax({
                type: "POST",
                url: "http://localhost:7070/register/newUser",
                // The key needs to match your method's input parameter (case-sensitive).
                data: JSON.stringify(userDetails),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    $(location).attr("href", "/login");
                    // alert("Request Success!");
                    // location.reload(true);
                },
                failure: function (errMsg) {
                    alert("Error creating new account!");
                }
            });
            return false; // Will stop the submission of the form
        }
    });
});