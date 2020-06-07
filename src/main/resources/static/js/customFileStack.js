// Call to API Key
const client = filestack.init("A2kZypz53QySbQzGXOkTOz");

// MODAL OPTIONS
    const options = {
        "url": "https://cdn.filestackcontent.com/api/file/s7tdGfE5RRKFUxwsZoYv",
        "size": 8331,
        "type": "image/png",
        "filename": "watermark.png",
        "key": "a1RyBxiglW92bS2SRmqM_watermark.png"
        };

    // client.transform("g8fLXNCRT4K3TJSUFrRW", options);


    const picker = client.picker();

    // BUTTON TO UPLOAD
    const btn = document.getElementById('fs-upload');
        btn.addEventListener('click', function (e) {
            e.preventDefault();
            picker.open(options);
        });

        // function updateForm(result) {
    //     document.getElementById("image-url").value = result.filesUploaded[0].url;
    // }
    // updateForm();

    function pickMark() {
        //Opening the file picker here
        client.picker("g8fLXNCRT4K3TJSUFrRW", options).then(function(result) {
            document.getElementById("image-url").value = result.filesUploaded[0].url;
        });
    }
    pickMark();