(function(){
    "use strict";


    $(document).ready(function(){
        $("#billSubmit").click(function(){
            let wrong = false;
            $("#Bstate").css('border', 'none');
            $("#Bzip").css('border', 'none');
            $("#Bcity").css('border', 'none');
            if(!/^[a-zA-Z][a-zA-Z]*$/.test($("#Bstate").val())){
                $("#Bstate").css('border', '3px solid #FFB6C1');
                wrong = true;
            }
            if(/\D/.test($("#Bzip").val())){
                $("#Bzip").css('border', '3px solid #FFB6C1');
                wrong = true;
            }
            if(!(/^[a-zA-Z][a-zA-Z\s\-]*$/.test($("#Bcity").val().trim()))){
                $("#Bcity").css('border', '3px solid #FFB6C1');
                wrong = true;
            }
            if(wrong === true){
                $("#errorB").text("Please check highlighted boxes for errors! Cities and States can only contain" +
                    " letters and Zip Codes numbers!");
                return false;
            } else {
                return true;
            }

        })
    });

    $(document).ready(function(){
        $("#shipSubmit").click(function(){
            let wrong = false;
            $("#Sstate").css('border', 'none');
            $("#Szip").css('border', 'none');
            $("#Scity").css('border', 'none');
                if(!/^[a-zA-Z][a-zA-Z]*$/.test($("#Sstate").val())){
                    $("#Sstate").css('border', '3px solid #FFB6C1');
                    wrong = true;
                }
                if(/\D/.test($("#Szip").val())){
                    $("#Szip").css('border', '3px solid #FFB6C1');
                    wrong = true;
                }
                if(!/[a-zA-Z][a-zA-Z\s\-]*/.test($("#Scity").val())){
                    $("#Scity").css('border', '3px solid #FFB6C1');
                    wrong = true;
                }
            if(wrong === true){
                $("#errorS").text("Please check highlighted boxes for errors! Cities and States can only contain" +
                    " letters and Zip Codes numbers!");
                return false;
            } else {
                return true;
            }

        })
    })




})();