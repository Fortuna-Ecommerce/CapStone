(function(){
    var URL_PRODUCTAPI_BASE="/productapi";
    var URL_GET_PRODUCT="/getproduct";
    var URL_SAVE_PRODUCT="/productsaveedit";
    $(document).ready(function(){
        initPage();
    });
    var initPage=function(){
        $("#editSaveButton").prop("hidden",false);
        $("#editSaveButtonSpinner").prop("hidden",true);
        $('.editButton').on('click', function(){
            let id=$(this).data('id');
            editProductProperties(id);
        });
        $(".alert").prop("hidden",true);

    };

    var showAlert=function(scope, criticality, alertText){
        // let selector="#editModalAlertContent"
        // if(scope==="edit"){
        //     selector="#editModalAlert";
        // }
        // else if(scope==="global"){
        //     selector="#globalAlert";
        // }
        // else{}
        // $(selector).prop("hidden",true);
        // if(criticality==="error"){
        //     $(selector).addClass("alert-danger");
        // }
        // else if(criticality==="warning"){
        //     $(selector).addClass("alert-warning");
        // }
        // else if(criticality==="success"){
        //     $(selector).addClass("alert-success");
        // }
        // else{
        //     $(selector).addClass("alert-primary");
        // }
        // $(selector+"Content").text(alertText);
        // $(selector).prop("hidden",false);
        // $(selector).fadeTo(2000, 500).slideUp(500, function(){
        //     $(selector).slideUp(500);
        // });
        console.log(alertText);

    };

    var setProductProperties=function(product){
        try{
            let targetProduct=product.id;
            $("#productListID"+targetProduct).text(targetProduct);
            $("#productListName"+targetProduct).text(product.name);
            $("#productListColor"+targetProduct).text(product.color);
            $("#productListSize"+targetProduct).text(product.size);
            $("#productListType"+targetProduct).text(product.type);
            $("#productListPrice"+targetProduct).text(product.price);
            $("#productListQuantity"+targetProduct).text(product.quantity);
            $("#productListImage"+targetProduct).text(product.image);
        }
        catch(e){
            showAlert("edit","error", "Failed to update product properties.");
        }
    };

    var editProductProperties=function(id){
        $("#editSaveButton").prop("hidden",false);
        $("#editSaveButtonSpinner").prop("hidden",true);
        if(id!='' && (Number.isSafeInteger(Number(id)))){
            $.get(URL_PRODUCTAPI_BASE+URL_GET_PRODUCT+"/"+id)
                .done(function(data) {
                    let targetProduct={};
                    try{
                        if(data.length==1){
                            targetProduct=data[0];
                        }
                        else{
                            throw "Product has duplicates or not found!!"
                        }
                        try {
                            $("#editName").val(targetProduct.name);
                            $("#editName").prop('disabled',true);
                            $("#editColor").val(targetProduct.color);
                            $("#editColor").prop('disabled',true);
                            $("#editSize").val(targetProduct.size);
                            $("#editSize").prop('disabled',true);
                            $("#editType").val(targetProduct.type);
                            $("#editType").prop('disabled',true);
                            $("#editPrice").val(targetProduct.price);
                            $("#editQuantity").val(targetProduct.quantity);
                            $("#editDescription").val(targetProduct.description);
                            $("#editID").val(targetProduct.id);
                            if((targetProduct.special==undefined)||(targetProduct.special==="null")||(targetProduct.special===false)){
                                $("#editOnSpecial").prop('checked',false);
                            }
                            else{
                                $("#editOnSpecial").prop('checked',true);
                            }

                        }
                        catch(e){
                            showAlert("edit","error", "Failed to set editor values.");
                        }
                        $("#editModal").modal('show');
                        $("#editSaveButton").on('click',function(){
                            saveProductPropertyEdits(targetProduct.id);
                        })

                    }
                    catch(e){
                        showAlert("global","error","Failed to retrieve product information.")
                    }

                })
                .fail(function(data){
                    showAlert("edit","error", JSON.stringify(data,null,2))
                });


        }
        console.log(id)
    };

    var saveProductPropertyEdits=function(productID){
        let product={};
        $("#editSaveButton").prop("hidden",true);
        $("#editSaveButtonSpinner").prop("hidden",false);
        try{
            product['id']=$("#editID").val();
            product['name']=$("#editName").val();
            product['color']=$("#editColor").val();
            product['size']=$("#editSize").val();
            product['type']=$("#editType").val();
            product['price']=$("#editPrice").val();
            product['quantity']=$("#editQuantity").val();
            product['description']=$("#editDescription").val();
            product['special']=$("#editOnSpecial").is(":checked");
        }
        catch(e){
            showAlert("edit","error", "Failed to Setup Product Objects");
        }
        $.ajax({
            url:URL_PRODUCTAPI_BASE+URL_SAVE_PRODUCT,
            data:JSON.stringify(product),
            type:"POST",
            contentType:"application/json",
        })
        .done(function(data){
            console.log("edit","Finished: "+JSON.stringify(data,null,2));
            setProductProperties(data);
            showAlert("global","success","Successfully Edited Product "+data.name);
            $("#editModalClose").click();
            $("#editSaveButton").prop("hidden",true);
            $("#editSaveButtonSpinner").prop("hidden",false);
        })
        .fail(function(data){
            showAlert("edit","error", "Failed to Edit Product.");
            $("#editSaveButton").prop("hidden",true);
            $("#editSaveButtonSpinner").prop("hidden",false);
        });
    }

})();