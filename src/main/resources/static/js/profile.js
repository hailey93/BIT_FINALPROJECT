//쪽지 체크박스 전체 선택
$(function(){
    $("#allcheck").click(function(){
        var chk = $(this).is(":checked");//.attr('checked');
        if(chk) $(".select_note input").prop('checked', true);
        else $(".select_note input").prop('checked', false);
    });
});

//팔로우버튼
$(function () {
    $("#follow").click(function(){

        var follow = {
            followId: $("#fol").val(),
        };
        console.log(follow);


        $.ajaxSettings.traditional=true;
        $.ajax({
            type : "post",
            url : "/member/follow",
            data : follow,
            success : function(data){

                location.reload();
            },
        });
    });
});

//팔로우취소버튼
$(function(){
    $("#followcancel").click(function(){

        var follow = {
            followId: $("#fol").val(),
        };
        console.log(follow);

        $.ajaxSettings.traditional=true;
        $.ajax({
            type : "post",
            url : "/member/cancelFollow",
            data : follow,
            success : function(data){

                location.reload();
            },
        });
    });
});


//프로필페이지에서 쪽지보내기 버튼
$(function(){
    $("#notesend").click(function(){
        var receive = $("#fol").val();
        document.sending.receiveId.value = receive;
        console.log(receive);
        alert('console');

        $("#sending").submit();
    });
});

//프로필페이지 사진 전체보기
$(function(){
    $("#allphoto").click(function(){
        var member = $("#fol").val();
        document.photo.memberId.value = member;
        console.log(member);
        alert('console');

        $("#photo").submit();
    });
});


//프로필페이지 스크랩 전체보기
$(function(){
    $("#allscrap").click(function(){
        var member = $("#fol").val();
        document.scrap.memberId.value = member;
        console.log(member);
        alert('console');

        $("#scrap").submit();
    });
});



//쪽지페이지에서 보내기버튼
$(function() {
    $("#send").click(function () {
        var count = $(".select_note input:checked").length;
        var chk;
        $("input[name=check]:checked").each(function () {
            chk = ($(this).attr("data-sendId"));
        });
        document.sending.receiveId.value = chk;
        console.log(chk);
        if (count == 0) {
            alert("선택된 쪽지가 없습니다.")
        } else {
            if (count > 1) {
                alert("하나만 선택해주세요.")
            } else {
                alert("쪽지보내러 ㄱㄱ");

                $("#sending").submit();

            }
        }
    });
});

//쪽지 삭제 버튼
$(function() {
    $("#delete").click(function(){
        var dcount = $(".select_note input:checked").length;
        var dchk=new Array();
        $("input[name=check]:checked").each(function(){
            dchk.push($(this).attr("data-No"));
        });
        console.log(dchk);
        if(dcount == 0){
            alert("선택된 쪽지가 없습니다.")
        }else{
            confirm(dcount+"개의 쪽지를 삭제하시겠습니까?")

            $.ajaxSettings.traditional=true;
            $.ajax({
                url : "/member/deleteNote",
                type : "post",
                data : {msgNum : dchk},
                success : function(data){
                    location.reload(true);
                },
            });
        }
    });
});


//profileInfo 사진 업로드
$(document).ready(function (e){
    $("input[type='file']").change(function(e){

        //div 내용 비워주기
        $('#preview').empty();

        var files = e.target.files;
        var arr =Array.prototype.slice.call(files);

        //업로드 가능 파일인지 체크
        for(var i=0;i<files.length;i++){
            if(!checkExtension(files[i].name,files[i].size)){
                return false;
            }
        }

        preview(arr);


    });//file change

    function checkExtension(fileName,fileSize){

        var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
        var maxSize = 10485760;  //10MB

        if(fileSize >= maxSize){
            alert('파일 사이즈 초과');
            $("input[type='file']").val("");  //파일 초기화
            return false;
        }

        if(regex.test(fileName)){
            alert('업로드 불가능한 파일이 있습니다.');
            $("input[type='file']").val("");  //파일 초기화
            return false;
        }
        return true;
    }

    function preview(arr){
        arr.forEach(function(f){

            //파일명이 길면 파일명...으로 처리
            var fileName = f.name;
            if(fileName.length > 10){
                fileName = fileName.substring(0,7)+"...";
            }

            //div에 이미지 추가
            var str = '<div style="display: inline-flex; ">';
            // str += '<span>'+fileName+'</span><br>';

            //이미지 파일 미리보기
            if(f.type.match('image.*')){
                var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
                reader.onload = function (e) { //파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
                    //str += '<button type="button" class="delBtn" value="'+f.name+'" style="background: red">x</button><br>';
                    str += '<img src="'+e.target.result+'" title="'+f.name+'" width=300 height=300 />';
                    str += '</li></div>';
                    $(str).appendTo('#preview');
                }
                reader.readAsDataURL(f);
            }else{
                str += '<img src="/resources/img/fileImg.png" title="'+f.name+'" width=300 height=300 />';
                $(str).appendTo('#preview');
            }
        });//arr.forEach
    }
});

