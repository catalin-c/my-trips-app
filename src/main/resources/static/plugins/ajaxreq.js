$("#tripSelect :selected").text();


$('#tripSelect').on('change', function() {
    // alert( this.value );
    sParameter = this.value;
    sParameter = encodeURIComponent(sParameter.trim());
    // console.log(sParameter);
    $.ajax({url: "http://localhost:7070/trip?tripName=" + sParameter, success: function(result){
        $("#impressionsText").text(result['impression']);
        $("#datesText").text("From: " + result['dateFrom'] + " to " + result['dateTo']);

    }});
});