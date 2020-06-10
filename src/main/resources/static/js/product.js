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
                $("#priceInput1").text('$'+finalTotal);
                $("#priceInput3").text('$'+finalTotal2);
            } else if (quantity === ""){
                $("#priceInput1").text('$'+price);
                $("#priceInput3").text('$'+price2);
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
                $("#priceInput2").text('$'+finalTotal);
            } else if (quantity === ""){
                $("#priceInput2").text('$'+price);
            }
        })
    });
})();