/**
 * 根据判断结果为校验数据框添加样式
 * @param flag 表达式
 * @param $obj 操作的input框
 * @returns {boolean}
 */
function setClassForHint(flag, $obj) {
    if (flag) {
        //符合要求显示绿色
        $obj.parent().removeClass("has-error").addClass("has-success");
        return true;
    } else {
        //不符合要求红色提示
        $obj.parent().removeClass("has-success").addClass("has-error");
        return false;
    }
}


/**
 * 提取URL中的指定名称的参数
 * @param variable
 * @returns {string|boolean}
 */
function getQueryVariable(variable)
{
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return(false);
}