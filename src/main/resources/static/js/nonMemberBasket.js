    $('#getTP').text( getTP());
$('#paymentTP').text(getTP());

function checkBoxIsChecked() {
    var len = JSON.parse(localStorage.getItem("saveL")).length;
    console.log("체크박스 함수 실행"+len+ JSON.parse(localStorage.getItem("saveL"))[0][0]);
    var delProductNo = new Array();
    console.log("Arr : " + delProductNo.length);
    var checkBox = new Array(len);
    var checkBoxResult = new Array(len);
    var a=0;
    var trueCount = 0;
    var isTrue;

    for(var i=0;i<len;i++){
        checkBox[i] = document.getElementById('innerCheckbox'+i);

        checkBoxResult[i] = $(checkBox[i]).prop("checked");
        console.log("체크박스 저장 : " + checkBox[i]+ "result : " + checkBoxResult[i]);
        if(checkBoxResult[i] == false){
            q = JSON.parse(localStorage.getItem("saveL"))[i][0];
            w = JSON.parse(localStorage.getItem("saveL"))[i][1];
            e = JSON.parse(localStorage.getItem("saveL"))[i][2];
            delProductNo[a] = [q,w,e];
            a++;
        }else{
            trueCount++;
        }
    }

    console.log("checkBox : " +checkBox + "checkBoxResult : " + checkBoxResult + "delProductNo : " + delProductNo);

    var b = JSON.parse(localStorage.getItem("saveL")).length;
    console.log("길이 측정 : " +a+JSON.parse(localStorage.getItem("saveL")).length + "a : " +a + b + "trueCount" + trueCount);
    if(trueCount == b){
        delProductNo = null;
    }
    console.log(delProductNo);
    localStorage.removeItem("saveL");
    if(delProductNo != null){

        localStorage.setItem("saveL",JSON.stringify(delProductNo));
        var fir = null;
        var hohoSession = JSON.parse(localStorage.getItem("saveL"));
        console.log("hohoSession : "+hohoSession);
        if(hohoSession.length==1){
            fir = "첫번째";
        }
        $.ajax({
            url : '/basketLocal',
            type : 'POST',
            traditional : true,
            data : {
                first : fir,
                hoho : hohoSession
            },
            success : function (e) {
                console.log("suc");
                window.location.reload();
            },
            error : function (e) {
                console.log("err");
            }
        });
    }else{
        console.log("delProN is Null");
        $.ajax({
            url : '/delNonmemberBasket',
            type : 'POST',
            data : {
                bool : 'real',
            },
            success : function (e) {
                console.log("suc");
                window.location.reload();
            },
            error : function (e) {
                console.log("err");
            }
        })
    }
    /*  console.log("delProductNo = "+delProductNo);
      console.log("Arr : " + delProductNo.length + delProductNo);
  if(delProductNo == null){
      console.log("checkBoxResult : "+checkBoxResult);
      alert("선택값이 없습니다");
      //window.location.reload();
  }*/



    /* memberBasket
    $.ajax({
         url : '/delCheckBox',
         type : 'POST',

         data : {
             delProcutNoArray : delProductNo
         },
         success : function () {
             alert("삭제 되었습니다");

         },
         error : function () {
             alert("삭제 실패");
         }
     })*/
}


function  getTP() {
    console.log("getTP실행");
    var tPrice=new Array();
    var TP = 0;
    $('.selling-option-item__price__number').each(function(){
        tPrice.push($(this).attr('price-total'));
    });
    //console.log("tPrice :: "+tPrice);

    for(var m = 0; m<tPrice.length; m++){
        console.log(Number(tPrice[m]));
        TP += Number(tPrice[m]);
    }
    console.log("결과 : "+ TP);
    return TP;

}

function checkAll(){
    if( $("#th_checkAll").is(':checked') ){
        $("input[name=innerCheckbox]").prop("checked", true);
    }else{
        $("input[name=innerCheckbox]").prop("checked", false);
    }
}
$("input[name=innerCheckbox]").click(function(){
    $("#th_checkAll").prop("checked", false);
});
/*
                function deleteBtn(){
                    $("ul").remove("#one, #two");

                }*/
