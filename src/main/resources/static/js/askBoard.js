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


$(function () {
    var obj = [];

    nhn.husky.EZCreator.createInIFrame({
        oAppRef: obj,
        elPlaceHolder: "askContent",
        sSkinURI: "/editor/SmartEditor2Skin.html",
        htParams: {
            bUseToolbar: true,
            bUseVerticalResizer: true,
            bUseModeChanger: true,
        }
    });

    $("#updateBoard").click(function () {
        obj.getById["askContent"].exec("UPDATE_CONTENTS_FIELD", []);
        $("#updateBoardFrm").submit();
    });
});
