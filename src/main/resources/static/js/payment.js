/*
function saveMemberAddr() {
    var zipCode = $('#order_received_zip_code').val();
    var receivedAt = $('#order_received_at').val();
    var receivedAtDetail = $('#order_received_at_detail').val();
    var fullAddr;
    if(zipCode == null || receivedAt == null){
        alert("주소를 입력하세요");
    }else {
        fullAddr = zipCode+receivedAt+receivedAtDetail;
        $.ajax({
            url: '/saveAddr',
            type:"POST",
            data:{
                fullAddr : fullAddr
            },success : function () {
                console.log("성공");
            },error : function () {
                console.log("실패");
            }
        })
    }
}*/
