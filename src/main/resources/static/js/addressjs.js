(function(){
    "use strict";

    $(document).ready(function(){
        $("#shipBillAddressCheck").click(function(){
            if($(this).is(":checked")){
                $(".shipping-info").removeAttr('required');
                $("#shippingInfo").css("display", "none");
                $("#shipInfoHeader").css("display", "none");
            }
            else if($(this).is(":not(:checked)")){
                $("#shippingInfo").css("display", "block");
                $("#shipInfoHeader").css("display", "block");
                $(".shipping-info").prop('required', true);
            }
        });
    });





})();