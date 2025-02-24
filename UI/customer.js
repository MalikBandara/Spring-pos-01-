
    $("#btnSave").click(function () {
        let customerData = {
            id: $("#userId").val(),
            name: $("#userName").val(),
            address: $("#userAddress").val(),

        };

        $.ajax({
            url: "http://localhost:8080/api/v1/customer/save",
            type: "POST",
            contentType: "application/json",  // Ensure JSON format
            data: JSON.stringify(customerData),  // Convert to JSON
            dataType: "json",  // Expect JSON response
            success: function (response) {
                alert("Customer saved successfully!");
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
            id: $("#userId").val(),
            name: $("#userName").val(),
            address: $("#userAddress").val(),

        };

        $.ajax({
            url: "http://localhost:8080/api/v1/customer/update", // Corrected URL
            type: "PUT",
            contentType: "application/json",  // Ensure JSON format
            data: JSON.stringify(customerData),  // Convert to JSON
            dataType: "json",  // Expect JSON response
            success: function (response) {
                alert("Customer updated successfully!");
                console.log("Updated Customer:", response);
            },
            error: function (xhr) {
                alert("Error updating customer: " + xhr.responseText);
                console.error(xhr.responseText);
            }
        });
    });


    //delete done

    $("#btnDelete").click(function () {
        let userId = $("#userId").val(); // Get user ID from input field

        if (!userId) {
            alert("Please enter a User ID to delete.");
            return;
        }

        $.ajax({
            url: `http://localhost:8080/api/v1/customer/delete/${userId}`, // Correct URL
            type: "DELETE",
            success: function (response) {
                if (response) {
                    alert("Customer deleted successfully!");
                    console.log("Deleted Customer ID:", userId);
                } else {
                    alert("Customer not found!");
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
            url: "http://localhost:8080/api/v1/customer/getAll", // API endpoint
            type: "GET",
            dataType: "json", // Expect JSON response
            success: function (response) {
                let tableBody = $("#userTableBody");
                tableBody.empty(); // Clear previous data

                response.data.forEach(customer => {  // Use response.data instead of customers
                    let row = `<tr>
            <td>${customer.id}</td>
            <td>${customer.name}</td>
            <td>${customer.address}</td>
            </tr>`;
                    tableBody.append(row); // Append new row
                });

                alert("Customer data loaded successfully!");
            },
            error: function (xhr) {
                alert("Error loading customer data: " + xhr.responseText);
                console.error(xhr.responseText);
            }
        });
    });



