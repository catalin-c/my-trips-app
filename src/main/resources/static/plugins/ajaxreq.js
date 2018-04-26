alert($("#tripSelect :selected").text());


$('#tripSelect').on('change', function() {
    alert( this.value );
});