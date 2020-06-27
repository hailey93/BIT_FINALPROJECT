
var sock = new SockJS("/ws-stomp");
var ws = Stomp.over(sock);
var reconnect = 0;

var sender;

var msg=$('#message').val();
console.log(msg);
var messages=new Array();

$(document).ready(function() {
    $('#message').keydown(function (key) {
        if(key.keyCode==13){
            sendMsg();
        }
    });
});

function sendMsg() {
    ws.send("/pub/message", {}, JSON.stringify({
        type:'TALK',
        chatId: chatId,
        sender: sender,
        msg: $('#message').val()
    }));
    $('#message').val("");
}

function connect() {
    // pub/sub event
    ws.connect({}, function(frame) {
        ws.send("/pub/message", {}, JSON.stringify({
            type:'ENTER',
            chatId:chatId,
            sender:sender,
            enterTime:enterTime,
            /*count:count*/
        }));

        ws.subscribe("/sub/chat/"+chatId, function(msg) {
            var receive = JSON.parse(msg.body);

            if(receive.type=='ENTER'){
                /*$(".content").append("<div class='chat-message-group'><div class='chat-messages'><div class='message'>" + receive.msg + "</div></div></div>");*/
                $(".content").append("<div class='chat-message-group'><div class='chat-messages'><div class='message'>" + receive.msg + "</div></div></div>");
                if(sender!=receive.sender){
                    //상담원 입장했을때 고객이 보는 시점
                    $('.content').append("<div class='chat-message-group'><div class='chat-messages'><div class='message'>상담원이 입장하였습니다.</div><div class='message'>안녕하세요? 무엇 궁금하신가요?</div></div></div>");
                    /*$('.content').append("<div class='message'>"+ receive.sender+": 안녕하세요? 무엇 궁금하신가요?</div></div></div>");*/
                } /*else{
                    $('.outgoing').append("<div class='bubble'>나: 안녕하세요 어떤 점이 궁금하신가요?</div>");
                }*/
            } else if(receive.type=='TALK'){
                if(sender==receive.sender){
                    $('.content').append("<div class='chat-message-group writer-user'><div class='chat-messages'><div class='message'>"+ receive.msg+ "</div></div></div>");
                } else {
                    $(".content").append("<div class='chat-message-group'><div class='chat-messages'><div class='message'>" + receive.msg + "</div></div></div>");
                }
            } else {
                alert("상대방이 채팅방을 나갔습니다. 채팅이 종료됩니다.")
                ws.disconnect();
                window.close();
                opener.location.reload();
            }
        });

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
    window.close();
    opener.location.reload();
}
