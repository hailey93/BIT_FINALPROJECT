//좋아요버튼
$(function(){
    $("#like").click(function(){

        var BoardNo = {
            photoBoardNo : $("#user").val(),
        };
        console.log(BoardNo);

        $.ajaxSettings.traditional = true;
        $.ajax({
            type: "post",
            url : "/like",
            data : BoardNo,
            success : function(data){

                location.reload();
            },
        });
    });
});

//좋아요취소버튼
$(function(){
    $("#cancellike").click(function(){
        var BoardNo = {
            photoBoardNo: $("#user").val(),
        };
        console.log(BoardNo);

        $.ajaxSettings.traditional = true;
        $.ajax({
            type: "post",
            url: "/nonlike",
            data: BoardNo,
            success : function(data){

                location.reload();
            },
        });
    });
});

//스크랩버튼
$(function(){
    $("#scrap").click(function(){
        var BoardNo = {
            photoBoardNo : $("#user").val(),
        };
        console.log(BoardNo);

        $.ajaxSettings.traditional = true;
        $.ajax({
            type: "post",
            url: "/scrap",
            data: BoardNo,
            success : function(data){

                location.reload();
            },
        });
    });
});

$(function(){
    $("#cancelscrap").click(function(){
        var BoardNo = {
            photoBoardNo : $("#user").val(),
        };
        console.log(BoardNo);

        $.ajaxSettings.traditional = true;
        $.ajax({
            type: "post",
            url: "/nonscrap",
            data: BoardNo,
            success : function(data){

                location.reload();
            },
        });
    });
});

$(function(){
    $("#notesend").click(function(){
        var receive = $("#memberId").val();
        console.log(receive);

        document.sending.receiveId.value=receive;

        $("#sending").submit();
    });

});

$(function(){
    $("#photoinsert").click(function(){
        var area = $("#areaSelect").val();
        var house = $("#houseSelect").val();
        var style = $("#styleSelect").val();
        var place = $("#placeSelect").val();

        console.log(area);
        console.log(house);
        console.log(style);
        console.log(place);

        document.insertphoto.area.value=area;
        document.insertphoto.style.value=style;
        document.insertphoto.house.value=house;
        document.insertphoto.place.value=place;

        $("#insertphoto").submit();
    });
});

function update(idx) {
    var photoBoardNo = idx;
    location.href = "/photoupdate/" + photoBoardNo;
};

function del(idx){
    var photoBoardNo = idx;
    location.href = "/photodelete/" + photoBoardNo;
};

$(function(){
    $("#photoupdate").click(function(){
        var area = $("#areaSelect").val();
        var house = $("#houseSelect").val();
        var style = $("#styleSelect").val();
        var place = $("#placeSelect").val();

        console.log(area);
        console.log(house);
        console.log(style);
        console.log(place);


                    document.updatephoto.area.value=area;
                    document.updatephoto.style.value=style;
                    document.updatephoto.house.value=house;
                    document.updatephoto.place.value=place;

                    $("#updatephoto").submit();



    });
});

$(function(){
    $("#pCommentInsert").click(function(){
        /*var BoardNo = {
            photoBoardNo : $("#user").val(),
        };*/

        var Content = $("#comment").serialize();

        //console.log(BoardNo);
        console.log(Content);

        $.ajaxSettings.traditional = true;
        $.ajax({
            type: "post",
            url: "/insertPhotoComment",
            data: Content,
            success : function(data){
                console.log(data);
                location.reload();
            },
        });
    });
});

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
