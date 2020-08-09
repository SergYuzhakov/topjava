$(function () {
    makeEditable({
            ajaxUrl: "profile/meals/",
            datatableApi: $("#datatable").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "dateTime"
                    },
                    {
                        "data": "description"
                    },
                    {
                        "data": "calories"
                    },

                    {
                        "defaultContent": "Edit",
                        "orderable": false
                    },
                    {
                        "defaultContent": "Delete",
                        "orderable": false
                    }
                ],
                "order": [
                    [
                        0,
                        "desc"
                    ]
                ]
            }),
            update: filter
        });
});

function filter(){
    $.ajax({
        type: "GET",
        url: "profile/meals/between",
        data: $("#filterForm").serialize()
        }).done(updateTableByData);
}
function clearFilter() {
    $("#filterForm")[0].reset();
    $.get("profile/meals/", updateTableByData)
}