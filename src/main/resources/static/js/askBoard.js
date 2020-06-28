$(function(){
    $("#aCommentInsert").click(function(){
        var Content = $("#comment").serialize();

        console.log(Content);

        $.ajaxSettings.traditaional = true;
        $.ajax({
            type: "post",
            url: "/insertAskComment",
            data: Content,
            success : function(data){
                console.log(data);
                location.reload();
            },
        });
    });
});

function reply(idx) {
    var askBoardNo = idx;
    location.href = "/askreply/" + askBoardNo;
}

function del(idx) {
    var askBoardNo = idx;

    var result = confirm("정말 삭제하시겠습니까?");

    if (result) {
        location.href = "/askdelete/" + askBoardNo;

    }
}

function update(idx) {
    var askBoardNo = idx;
    location.href = "/askupdate/" + askBoardNo;
}