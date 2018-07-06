/* util*/
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};


/*공통*/
var common = {};
$.extend( cm,{
    /**
     * 회원 가입 페이지 이동
     */
    goJoinPage: function () {
        location.replace("/join");
    }
});