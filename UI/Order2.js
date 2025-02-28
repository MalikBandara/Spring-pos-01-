let cartItems = []; // Array to store all items in the cart

$('#addToCartBtn').click(function () {
    const orderDate = $('#orderDate').val();
    const customerId = parseInt($('#customerId').val());
    const itemId = $('#itemId').val();
    const itemPrice = parseFloat($('#itemPrice').val());
    const orderQuantity = parseInt($('#orderQuantity').val());
    const total = orderQuantity * itemPrice;

    // Validate inputs (optional but recommended)
    if (!orderDate || isNaN(customerId) || !itemId || isNaN(itemPrice) || isNaN(orderQuantity)) {
        alert('Please fill in all fields with valid data.');
        return;
    }

    // Create an object for the item
    const item = {
        orderDate,
        customerId,
        itemId,
        itemPrice,
        orderQuantity,
        total
    };

    // Add the item to the cart
    cartItems.push(item);

    // Update the table to display all items in the cart
    updateCartTable();

    // Clear the form fields (optional)

});

function updateCartTable() {
    // Clear the table before re-rendering
    $('#orderTableBody').empty();

    // Loop through the cartItems array and append each item to the table
    cartItems.forEach((item, index) => {
        $('#orderTableBody').append(`
            <tr>
                <td>${item.orderDate}</td>
                <td>${item.customerId}</td>
                <td>${item.itemId}</td>
                <td>${item.itemPrice}</td>
                <td>${item.orderQuantity}</td>
                <td>${item.total}</td>
                <td><button class="removeItemBtn" data-index="${index}">Remove</button></td>
            </tr>
        `);
    });

    // Add event listeners for remove buttons
    $('.removeItemBtn').click(function () {
        const index = $(this).data('index');
        cartItems.splice(index, 1); // Remove the item from the cart
        updateCartTable(); // Update the table
    });
}


$('#btnSave').click(function () {
    if (cartItems.length === 0) {
        alert('Your cart is empty!');
        return;
    }

    const orderDate = $('#orderDate').val();
    console.log("Order Date:", orderDate);
    const customerId = parseInt($('#customerId').val());

    // Calculate the total order amount
    const total = cartItems.reduce((sum, item) => sum + item.total, 0);

    // Prepare the order data
    const orderData = {
        date: orderDate,
        total: total,
        customerId: customerId,
        orderDetailsList: cartItems.map(item => ({
            item: item.itemId, // Make sure this is a string if your DB uses VARCHAR for item ID
            qty: item.orderQuantity,
            unitPrice: item.itemPrice
        }))
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

            // Clear the cart and table after successful order placement
            cartItems = [];
            updateCartTable();
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