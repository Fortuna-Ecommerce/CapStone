(function(){
    "use strict";
    $(document).ready(function() {
        $("#amountOrdered").on('input', function () {
            let quantity = $("#amountOrdered").val();
            let price = $("#priceGet1").val();
            if (quantity > 0) {
                let total = quantity * price;
                let finalTotal = Math.round((total + Number.EPSILON) * 100) / 100;
                $("#priceInput1").text(finalTotal);
            } else if (quantity === " "){
                $("#priceInput1").text(price);
            }
        })
    });
    $(document).ready(function() {
        $("#amountOrdered").on('input', function () {
            let quantity = $("#amountOrdered").val();
            let price = $("#priceGet2").val();
            if (quantity > 0) {
                let total = quantity * price;
                let finalTotal = Math.round((total + Number.EPSILON) * 100) / 100;
                $("#priceInput2").text(finalTotal);
            } else if (quantity === " "){
                $("#priceInput2").text(price);
            }
        })
    });
})();