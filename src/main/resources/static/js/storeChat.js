
var sock = new SockJS("/ws-stomp");
var ws = Stomp.over(sock);
var reconnect = 0;

var sender;
var msg=$('#message').val();
console.log(msg);
var messages=new Array();

function sendMsg() {
    ws.send("/pub/chat/message", {}, JSON.stringify({
        type:'TALK',
        chatId: chatId,
        sender: sender,
        msg: $('#message').val(),
        sessionId:/\/([^\/]+)\/websocket/.exec(sock._transport.url)[1]
    }));
    $('#message').val("");
}
function recvMessage(recv){
    messages.unshift({
        "type" : recv.type,
        "sender" : recv.type=='ENTER'?'[알림]':recv.sender,
        "msg" : recv.msg,
        "sessionId": recv.sessionId
    });
    console.log(recv.sessionId);

    //if(sessionId)
    $('#chatting').append("<p class='me'>"+ recv.sender+ ":" +recv.msg+"</p>");
    //$("#chating").append("<p class='others'>" + d.userName + " :" + d.msg + "</p>");
}
function connect() {
    // pub/sub event
    ws.connect({}, function(frame) {
        ws.subscribe("/sub/chat/"+chatId, function(msg) {
            var recv = JSON.parse(msg.body);
            recvMessage(recv);
        });
        ws.send("/pub/chat/message", {}, JSON.stringify({
            type:'ENTER',
            chatId:chatId,
            sender:sender,
            sessionId: /\/([^\/]+)\/websocket/.exec(sock._transport.url)[1]
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
