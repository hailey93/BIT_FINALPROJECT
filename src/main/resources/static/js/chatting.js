
var sock = new SockJS("/ws-stomp");
var ws = Stomp.over(sock);
var reconnect = 0;

var sender;
var msg=$('#message').val();
console.log(msg);
var messages=new Array();

function sendMsg() {
    ws.send("/pub/message", {}, JSON.stringify({
        type:'TALK',
        chatId: chatId,
        sender: sender,
        msg: $('#message').val()
    }));
    $('#message').val("");
}
/*function recvMessage(recv){
   /!* messages.unshift({
        "type" : recv.type,
        "sender" : recv.type=="ENTER"?'[알림]':recv.sender,
        "msg" : recv.msg
    });
*!/
    if(sender==recv.sender){
        $('#chatting').append("<p class='me'>나: "+ recv.msg+ "</p>");
    } else {
        $("#chatting").append("<p class='others'>" + recv.sender + " :" + recv.msg + "</p>");
    }

}*/
function connect() {
    // pub/sub event
    ws.connect({}, function(frame) {
        ws.subscribe("/sub/chat/"+chatId, function(msg) {
            var receive = JSON.parse(msg.body);
            /*recvMessage(recv);*/
            if(receive.type=='TALK'){
                if(sender==receive.sender){
                    $('#chatting').append("<p class='me'>나: "+ receive.msg+ "</p>");
                } else {
                    $("#chatting").append("<p class='others'>" + receive.sender + " :" + receive.msg + "</p>");
                }
            } else if(receive.type=='ENTER'){
                $("#chatting").append("<p class='others'>" + receive.msg + "</p>");
            } else {
                $("#chatting").append("<p class='others'>" + receive.msg + "</p>");
            }

        });
        ws.send("/pub/message", {}, JSON.stringify({
            type:'ENTER',
            chatId:chatId,
            sender:sender,
        }));

    }, function(error) {
        if(reconnect++ <= 5) {
            setTimeout(function() {
                console.log("connection reconnect");
                sock = new SockJS("/ws-stomp");
                ws = Stomp.over(sock);
                connect();
            },10*1000);
        }
    });
}
connect();
function disconnect(){
    ws.send("/pub/message", {}, JSON.stringify({
            type:'LEAVE',
            chatId:chatId,
            sender:sender
    }));
    ws.disconnect();
}
