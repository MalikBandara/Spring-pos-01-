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


$("#btnViewAll").click(function () {
    $.ajax({
        url: "http://localhost:8080/api/v1/Order/getAll", // API endpoint
        type: "GET",
        dataType: "json", // Expect JSON response
        success: function (order) {
            let tableBody = $("#orderTableBody");
            tableBody.empty(); // Clear previous data

            order.data.forEach(order => {
                let row = `<tr>
                    <td>${order.orderId}</td>
                    <td>${order.date}</td>
                    <td>${order.total}</td>
                    <td>${order.customerId}</td>
                </tr>`;
                tableBody.append(row); // Append new row
            });

            alert("item data loaded successfully!");
        },
        error: function (xhr) {
            alert("Error loading customer data: " + xhr.responseText);
            console.error(xhr.responseText);
        }
    });
});

const  loadALL =  () => {
    $.ajax({
        url: "http://localhost:8080/api/v1/OrderDetail/getAll", // API endpoint
        type: "GET",
        dataType: "json", // Expect JSON response
        success: function (order) {
            let tableBody = $("#orderDetailsTableBody");
            tableBody.empty(); // Clear previous data

            order.data.forEach(order => {
                let row = `<tr>
                    <td>${order.id}</td>
                    <td>${order.qty}</td>
                    <td>${order.unitPrice}</td>
                    <td>${order.item}</td>
                    <td>${order.orders}</td>
                    
                </tr>`;
                tableBody.append(row); // Append new row
            });


        },
        error: function (xhr) {
            alert("Error loading customer data: " + xhr.responseText);
            console.error(xhr.responseText);
        }
    });
}
loadALL();


$('#addToCartBtn').click(function () {

    const orderDate = $('#orderDate').val();
    const customerId = parseInt($('#customerId').val());
    const itemId = $('#itemId').val();
    const itemPrice = parseFloat($('#itemPrice').val());
    const orderQuantity = parseInt($('#orderQuantity').val());
    const total = orderQuantity * itemPrice;

    $('#orderTableBody').append(`<tr>

         <td>${orderDate}</td>
         <td>${customerId}</td>
         <td>${itemId}</td>
         <td>${itemPrice}</td>
         <td>${orderQuantity}</td>
         <td>${total}</td>

   </tr>`)

})