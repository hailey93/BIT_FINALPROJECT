$(document).ready(function() {

    $('#memberId').keydown(function (key) {
        if(key.keyCode==13){
            searchId();
        }
    });

});

function searchId() {
    var id = {memberId: $('#memberId').val()};

    $.ajax({
        url: "/chatHistoryById",
        type: 'GET',
        data: id,
        dataType: 'json',

        success: function (data) {
            var msgList = data.msgList;
            var list;

            $.each(msgList, function (index, value) {
                var msg=value.msg.split(",");
                console.log(msg);

                list = list
                    + '<tr class="list-group list-group-horizontal"><td style="width:10%">' + value.sender + '</td>'
                    + '<td style="width:70%">' + value.msg + '</td>'
                    + '<td style="width:10%">' + value.time + '</td></tr>'
            });
            $('#tableList').empty();
            $('#tableList').append(list);
        },
    })
}