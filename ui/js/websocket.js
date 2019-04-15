var soket=null;
if(typeof (WebSocket)=='undefined'){
    console.log("您的浏览器不支持WebSocket");

}else{
    console.log("您的浏览器支持WebSocket");
    var socket= new WebSocket("ws:localhost:8080/websocket/20");
    socket.onopen=function () {
        console.log("socket opent");
    }
    socket.onmessage=function (message) {
        console.log("收到消息"+message.data);
    }
    socket.onclose=function () {
        console.log("关闭了socket连接");
    }
    socket.onerror=function () {
        console.error("连接错误");
    }
}