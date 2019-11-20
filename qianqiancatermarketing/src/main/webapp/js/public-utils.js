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