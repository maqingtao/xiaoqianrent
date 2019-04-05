function logout() {
    $.cookie('username',"欢迎 请登陆");
    $.cookie('userid',"");
    location.href="/"
}