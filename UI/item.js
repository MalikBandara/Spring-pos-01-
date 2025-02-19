
$("#btnSave").click(function () {
    let customerData = {
        id: $("#itemId").val(),
        name: $("#itemName").val(),
        price: $("#price").val(),
        qty: $("#quantity").val(),

    };

    $.ajax({
        url: "http://localhost:8080/api/v1/item/save",
        type: "POST",
        contentType: "application/json",  // Ensure JSON format
        data: JSON.stringify(customerData),  // Convert to JSON
        dataType: "json",  // Expect JSON response
        success: function (response) {
            alert("item saved successfully!");
            console.log("Response:", response);
        },
        error: function (xhr) {
            alert("Error: " + xhr.responseText);
            console.error(xhr.responseText);
        }
    });
});


$("#btnUpdate").click(function () {
    let customerData = {
        id: $("#itemId").val(),
        name: $("#itemName").val(),
        price: $("#price").val(),
        qty: $("#quantity").val(),

    };

    $.ajax({
        url: "http://localhost:8080/api/v1/item/update", // Corrected URL
        type: "PUT",
        contentType: "application/json",  // Ensure JSON format
        data: JSON.stringify(customerData),  // Convert to JSON
        dataType: "json",  // Expect JSON response
        success: function (response) {
            alert("item updated successfully!");
            console.log("Updated item:", response);
        },
        error: function (xhr) {
            alert("Error updating customer: " + xhr.responseText);
            console.error(xhr.responseText);
        }
    });
});


//delete done

$("#btnDelete").click(function () {
    let itemId = $("#itemId").val(); // Get user ID from input field

    if (!itemId) {
        alert("Please enter a User ID to delete.");
        return;
    }

    $.ajax({
        url: `http://localhost:8080/api/v1/item/delete/${itemId}`, // Correct URL
        type: "DELETE",
        success: function (response) {
            if (response) {
                alert("item deleted successfully!");
                console.log("Deleted item ID:", itemId);
            } else {
                alert("itemId not found!");
            }
        },
        error: function (xhr) {
            alert("Error deleting customer: " + xhr.responseText);
            console.error(xhr.responseText);
        }
    });
});


$("#btnViewAll").click(function () {
    $.ajax({
        url: "http://localhost:8080/api/v1/item/getAll", // API endpoint
        type: "GET",
        dataType: "json", // Expect JSON response
        success: function (customers) {
            let tableBody = $("#userTableBody");
            tableBody.empty(); // Clear previous data

            customers.forEach(customer => {
                let row = `<tr>
                    <td>${customer.id}</td>
                    <td>${customer.name}</td>
                    <td>${customer.price}</td>
                    <td>${customer.qty}</td>
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



