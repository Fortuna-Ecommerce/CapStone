(function(){
    "use strict";
    $(document).ready(function() {
        $("#amountOrdered").on('input', function () {
            let quantity = $("#amountOrdered").val();
            let price = $("#priceGet1").val();
            let price2 = $("#priceGet3").val();
            if (quantity > 0) {
                let total = quantity * price;
                let total2 = quantity * price2;
                let finalTotal = Math.round((total + Number.EPSILON) * 100) / 100;
                let finalTotal2 = Math.round((total2 + Number.EPSILON) * 100) / 100;
                $("#priceInput1").text(finalTotal.toLocaleString('en'));
                $("#priceInput3").text(finalTotal2.toLocaleString('en'));
            } else if (quantity === ""){
                $("#priceInput1").text(parseFloat(price).toLocaleString('en'));
                $("#priceInput3").text(parseFloat(price2).toLocaleString('en'));
            }
        })
    });
    $(document).ready(function() {
        $("#amountOrdered").on('input', function () {
            let quantity = $("#amountOrdered").val();
            let price = $("#priceGet2").val();
            console.log(quantity);
            if (quantity > 0) {
                let total = quantity * price;
                let finalTotal = Math.round((total + Number.EPSILON) * 100) / 100;
                $("#priceInput2").text(finalTotal.toLocaleString('en'));
            } else if (quantity === ""){
                $("#priceInput2").text(parseFloat(price).toLocaleString('en'));
            }
        })
    });
    $(document).ready(function () {
        $("#white1").on('click', function(){
            $("#originalImage").css("display", "none");
            $("#000000").css("display", "none");
            $("#808080").css("display", "none");
            $("#FFFFFF").css("display", "inline");
        })
    });
    $(document).ready(function () {
        $("#black1").on('click', function(){
            $("#originalImage").css("display", "none");
            $("#808080").css("display", "none");
            $("#FFFFFF").css("display", "none");
            $("#000000").css("display", "inline");
        })
    });
    $(document).ready(function () {
        $("#gray1").on('click', function(){
            $("#originalImage").css("display", "none");
            $("#000000").css("display", "none");
            $("#FFFFFF").css("display", "none");
            $("#808080").css("display", "inline");
        })
    });
    $(document).ready(function () {
        $("#white2").on('click', function(){
            $("#originalImage").css("display", "none");
            $("#000000").css("display", "none");
            $("#808080").css("display", "none");
            $("#FFFFFF").css("display", "inline");
        })
    });
    $(document).ready(function () {
        $("#black2").on('click', function(){
            $("#originalImage").css("display", "none");
            $("#808080").css("display", "none");
            $("#FFFFFF").css("display", "none");
            $("#000000").css("display", "inline");
        })
    });
    $(document).ready(function () {
        $("#gray2").on('click', function(){
            $("#originalImage").css("display", "none");
            $("#000000").css("display", "none");
            $("#FFFFFF").css("display", "none");
            $("#808080").css("display", "inline");
        })
    });
    $(document).ready(function () {
        $("#white3").on('click', function(){
            $("#originalImage").css("display", "none");
            $("#000000").css("display", "none");
            $("#808080").css("display", "none");
            $("#FFFFFF").css("display", "inline");
        })
    });
    $(document).ready(function () {
        $("#black3").on('click', function(){
            $("#originalImage").css("display", "none");
            $("#808080").css("display", "none");
            $("#FFFFFF").css("display", "none");
            $("#000000").css("display", "inline");
        })
    });
    $(document).ready(function () {
        $("#gray3").on('click', function(){
            $("#originalImage").css("display", "none");
            $("#000000").css("display", "none");
            $("#FFFFFF").css("display", "none");
            $("#808080").css("display", "inline");
        })
    });
})();