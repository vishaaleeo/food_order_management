var stompClient = null;

/*function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#userinfo").html("");
}
*/

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
  //  setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/user", {}, JSON.stringify({'userName': $("#userName").val(),'userPass': $("#userPass").val()}));
}

function showGreeting(message) {
    $("#userinfo").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });

     var socket = new SockJS('/swiggy');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
         //   setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/user', function (greeting) {
                showGreeting(JSON.parse(greeting.body).content);
            });
        });

    $( "#send" ).click(function() { sendName(); });
});