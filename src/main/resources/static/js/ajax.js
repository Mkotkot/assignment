$(document).ready(function () {
    (function worker() {

        let dataPram = {}
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/refresh",
            data: JSON.stringify(dataPram),
            cache: false,
            timeout: 600000,
            success: function (data) {
                let json = JSON.stringify(data, null, 4);
                $('#feedback').html(createTable(json));

            },
            "complete": function () {
                setTimeout(worker, 10000);
            }
        });
    })();

    $("#customerId").change(function () {
        let dataPram = {}
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/refresh",
            data: JSON.stringify(dataPram),
            cache: false,
            timeout: 600000,
            success: function (data) {
                let json = JSON.stringify(data, null, 4);
                $('#feedback').html(createTable(json));
            },
            "complete": function () {
                setTimeout(worker, 10000);
            }
        });
    });

    $("#customerId2").change(function () {
        let dataPram = {}
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/refresh",
            data: JSON.stringify(dataPram),
            cache: false,
            timeout: 600000,
            success: function (data) {
                let json = JSON.stringify(data, null, 4);
                $('#feedback').html(createTable(json));
            },
            "complete": function () {
                setTimeout(worker, 10000);
            }
        });
    });

    $("#formData").submit(function (event) {

        event.preventDefault();

        let dataPram = {}
        dataPram["employeeId"] = $("#employeeId").val();
        dataPram["customerId"] = $("#customerId").val();

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/send",
            data: JSON.stringify(dataPram),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            "success": function (data) {
                let json = JSON.stringify(data, null, 4);
                $('#feedback').html(createTable(json));
            },
            "error": function (e) {
                let json = "Ajax Error" + e.responseText;
                $('#feedback').html(json);

            }
        });

    });

    $("#replayButtonDefault").click(function (event) {

        event.preventDefault();

        let dataPram = {}
        dataPram["employeeId"] = $("#employeeId").val();
        dataPram["layerType"] = "DEFAULT";
        dataPram["customerId"] = $("#customerId2").val();

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/reply",
            data: JSON.stringify(dataPram),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            "success": function (data) {
                let json = JSON.stringify(data, null, 4);
                $('#feedback').html(createTable(json));
            },
            "error": function (e) {
                let json = "Ajax Error" + e.responseText;
                $('#feedback').html(json);

            }
        });

    });

    $("#replayButtonSupport").click(function (event) {

        event.preventDefault();

        let dataPram = {}
        dataPram["employeeId"] = $("#employeeId").val();
        dataPram["layerType"] = "SUPPORT";
        dataPram["customerId"] = $("#customerId2").val();

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/reply",
            data: JSON.stringify(dataPram),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            "success": function (data) {
                let json = JSON.stringify(data, null, 4);
                $('#feedback').html(createTable(json));
            },
            "error": function (e) {
                let json = "Ajax Error" + e.responseText;
                $('#feedback').html(json);

            }
        });

    });

    $("#replayButtonService").click(function (event) {

        event.preventDefault();

        let dataPram = {}
        dataPram["employeeId"] = $("#employeeId").val();
        dataPram["layerType"] = "SERVICE";
        dataPram["customerId"] = $("#customerId2").val();

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/reply",
            data: JSON.stringify(dataPram),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            "success": function (data) {
                let json = JSON.stringify(data, null, 4);
                $('#feedback').html(createTable(json));
            },
            "error": function (e) {
                let json = "Ajax Error" + e.responseText;
                $('#feedback').html(json);

            }
        });

    });

    function createTable(jsonList) {
        jsonList = JSON.parse(jsonList);
        if (jsonList.notificationDTOS.length > 0) {
            let table = '<table><thead><tr><th>From</th><th>TO</th> <th>Body</th><th> Date/Time</th></tr></thead><tbody>';
            jsonList.notificationDTOS.forEach(function (subJson) {
                if (subJson.customerId == $("#customerId").val()) {
                    table += '<td>' + subJson.from + '</td>';
                    table += '<td>' + subJson.to + '</td>';
                    table += '<td>' + subJson.body + '</td>';
                    table += '<td>' + subJson.localDateTime + '</td></tr>';
                }
            });
            table += '</tbody> </table>';
            return table;
        }
    }
});
