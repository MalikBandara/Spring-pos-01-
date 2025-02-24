$('#btnSave').click(function () {
    const orderDate = $('#orderDate').val();
    const customerId = parseInt($('#customerId').val());
    const itemId = $('#itemId').val();
    const itemPrice = parseFloat($('#itemPrice').val());
    const orderQuantity = parseInt($('#orderQuantity').val());
    const total = orderQuantity * itemPrice;

    const orderData = {
        date: orderDate,
        total: total,
        customerId: customerId,
        orderDetailsList: [
            {
                item: itemId, // Make sure this is a string if your DB uses VARCHAR for item ID
                qty: orderQuantity,
                unitPrice: itemPrice
            }
        ]
    };

    console.log("Sending data:", JSON.stringify(orderData)); // Debugging

    $.ajax({
        url: "http://localhost:8080/api/v1/Order/save",
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(orderData),

        success: function (resp) {
            alert(resp.msg);  // Show success message
            console.log("Success:", resp);
        },

        error: function (xhr) {
            try {
                let response = JSON.parse(xhr.responseText);
                alert("Error: " + response.message); // Show error message from backend
                console.log("Error:", response);
            } catch (e) {
                alert("Unexpected error occurred!");
                console.log("Parsing error:", e);
            }
        }
    });
});
