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

    $(document).ready(function(){
        $("#AddressSubmit").click(function(){
            let wrong = false;
            $("#Bstate").css('border', 'none');
            $("#Bzip").css('border', 'none');
            $("#Sstate").css('border', 'none');
            $("#Szip").css('border', 'none');
            $("#Bcity").css('border', 'none');
            $("#Scity").css('border', 'none');
            if(!$("#Bzip").val() || !$("#Bcity").val() || !$("#Bstate").val() || !$("#Baddress1").val() || !$("#Bfirst_name").val() || !$("#Blast_name").val() || !$("#Szip").val() || !$("#Scity").val() || !$("#Sstate").val() || !$("#Saddress1").val() || !$("#Sfirst_name").val() || !$("#Slast_name").val()){
                $("#error").text("Some fields may be missing data - please double check and try again!");
                $("html, body").scrollTop(0);
                return false;
            }
            if(!/^[a-zA-Z][a-zA-Z]*$/.test($("#Bstate").val())){
                $("#Bstate").css('border', '3px solid #FFB6C1');
                wrong = true;
            }
            if(/\D/.test($("#Bzip").val())){
                $("#Bzip").css('border', '3px solid #FFB6C1');
                wrong = true;
            }
            if(!/^[a-zA-Z][a-zA-Z\s\-]*$/.test($("#Bcity").val())){
                $("#Bcity").css('border', '3px solid #FFB6C1');
                wrong = true;
            }
            if($("#shipBillAddressCheck").is(":not(:checked)")){
                if(!/^[a-zA-Z][a-zA-Z]*$/.test($("#Sstate").val())){
                    $("#Sstate").css('border', '3px solid #FFB6C1');
                    wrong = true;
                }
                if(/\D/.test($("#Szip").val())){
                    $("#Szip").css('border', '3px solid #FFB6C1');
                    wrong = true;
                }
                if(!/^[a-zA-Z][a-zA-Z\s\-]*$/.test($("#Scity").val())){
                    $("#Scity").css('border', '3px solid #FFB6C1');
                    wrong = true;
                }
            }
            if(wrong === true){
                $("#error").text("Please check highlighted boxes for errors! Cities and States can only contain" +
                    " letters and Zip Codes numbers!");
                $("html, body").scrollTop(0);
                return false;
            } else {
                return true;
            }

        })
    })





})();