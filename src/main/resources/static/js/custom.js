(function() {
    "use strict";


        try {

            // Create a Stripe client.
            var stripe = Stripe('pk_test_B1PgkqwpndJUHJCdlY0I9leL00c395TbE5');

            // Create an instance of Elements.
            var elements = stripe.elements();

            // Create an instance of the card Element.
            var card = elements.create('card');

            // Add an instance of the card Element into the `card-element` <div>.
            card.mount('#card-element');

            // Handle real-time validation errors from the card Element.
            card.addEventListener('change', function (event) {
                var displayError = document.getElementById('card-errors');
                if (event.error) {
                    displayError.textContent = event.error.message;
                } else {
                    displayError.textContent = '';
                }
            });

            // Handle form submission.
            var form = document.getElementById('payment-form');
            form.addEventListener('submit', function (event) {
                event.preventDefault();
                // handle payment
                handlePayments();
            });


            //handle card submission
            function handlePayments() {
                stripe.createToken(card).then(function (result) {
                    if (result.error) {
                        // Inform the user if there was an error.
                        var errorElement = document.getElementById('card-errors');
                        errorElement.textContent = result.error.message;
                    } else {
                        // Send the token to your server.
                        var token = result.token.id;
                        var email = $('#email').val();
                        var total = $('#total').val();
                        $.post(
                            "/charge",
                            {email: email, token: token, total: total},
                            function (data) {
                            }, 'json')
                            .done(function (data) {
                                let displayError = document.getElementById('card-errors');
                                let message = "";
                                let isError = false;
                                let url = "localhost:8080";
                                try {
                                    isError = data.error.isError;
                                    message = data.error.message;
                                    url = data.url;
                                    console.log(JSON.stringify(message));
                                } catch (e) {
                                    isError = "true";
                                    displayError.textContent = "Unknown Error Occurred." + e;
                                }
                                if (isError === "true") {
                                    displayError.textContent = message;
                                } else {
                                    $("#close-payment-modal").click();
                                    window.location.assign(url);
                                }
                            })
                            .fail(function (data) {
                                console.log(JSON.stringify(data));
                            })
                    }
                });
            }
        }
        catch(e){}
    // var singleUploadForm = document.querySelector('#singleUploadForm');
    // var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
    // var singleFileUploadError = document.querySelector('#singleFileUploadError');
    // var singleFileUploadSuccess = document.querySelector('#singleFileUploadSuccess');
    //
    //
    // function uploadSingleFile(file) {
    //     var formData = new FormData();
    //     formData.append("file", file);
    //     console.log(formData);
    //     var xhr = new XMLHttpRequest();
    //     xhr.open("POST", "/uploadFile");
    //     console.log(xhr);
    //     xhr.onload = function() {
    //         console.log(xhr.responseText);
    //         var response = JSON.parse(xhr.responseText);
    //         if(xhr.status == 200) {
    //             singleFileUploadError.style.display = "none";
    //             singleFileUploadSuccess.innerHTML = "<p>File Uploaded Successfully.</p><p>DownloadUrl : <a href='" + response.fileDownloadUri + "' target='_blank'>" + response.fileDownloadUri + "</a></p>";
    //             singleFileUploadSuccess.style.display = "block";
    //         } else {
    //             singleFileUploadSuccess.style.display = "none";
    //             singleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
    //         }
    //     };
    //
    //     xhr.send(formData);
    // }
    //
    //
    // singleUploadForm.addEventListener('submit', function(event){
    //     var files = singleFileUploadInput.files;
    //     console.log("test");
    //     console.log(files);
    //     if(files.length === 0) {
    //         singleFileUploadError.innerHTML = "Please select a file";
    //         singleFileUploadError.style.display = "block";
    //     }
    //     uploadSingleFile(files[0]);
    //     event.preventDefault();
    // }, true);

    // $('#buttonSubmit').on('click', function(event) {
    //     console.log(test);
    //     var formElement = this;
    //     // You can directly create form data from the form element
    //     // (Or you could get the files from input element and append them to FormData as we did in vanilla javascript)
    //     var formData = new FormData(formElement);
    //
    //     $.ajax({
    //         type: "POST",
    //         enctype: 'multipart/form-data',
    //         url: "/uploadFile",
    //         data: formData,
    //         processData: false,
    //         contentType: false,
    //         success: function (response) {
    //             console.log(response);
    //             // process response
    //         },
    //         error: function (error) {
    //             console.log(error);
    //             // process error
    //         }
    //     });
    //
    //     event.preventDefault();
    // });
    // $(document).onload(function() {
    //     console("Adding event listener");
    //     $("#amountOrdered").on('input', function () {
    //         let quantity = $("#amountOrdered").val();
    //         let price = $("#priceInput").val();
    //         console.log("a");
    //         console.log(quantity);
    //         console.log(price);
    //         if (quantity > 0) {
    //             let total = quantity * price;
    //             $("#total").text(total);
    //             $("#priceInput").val(total);
    //         }
    //     })
    // });
    // document.getElementById('amount').addEventListener('change', function(event) {
    //     var quantity = $("#amountOrdered").val();
    //     if (quantity !== 0) {
    //         $(#price).html($(#price).val() * quantity);
    //     }


})();