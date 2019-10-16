<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/16
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
    <style>
        form {
            margin: 0;
        }
        textarea {
            display: block;
        }
    </style>
    <link rel="stylesheet" href="../themes/default/default.css" />
    <script charset="utf-8" src="../kindeditor-min.js"></script>
    <script charset="utf-8" src="../lang/zh_CN.js"></script>
    <script>
        var editor;
        KindEditor.ready(function(K) {
            editor = K.create('textarea[name="content"]', {
                allowFileManager : true
            });
            K('input[name=getHtml]').click(function(e) {
                alert(editor.html());
            });
            K('input[name=isEmpty]').click(function(e) {
                alert(editor.isEmpty());
            });
            K('input[name=getText]').click(function(e) {
                alert(editor.text());
            });
            K('input[name=selectedHtml]').click(function(e) {
                alert(editor.selectedHtml());
            });
            K('input[name=setHtml]').click(function(e) {
                editor.html('<h3>Hello KindEditor</h3>');
            });
            K('input[name=setText]').click(function(e) {
                editor.text('<h3>Hello KindEditor</h3>');
            });
            K('input[name=insertHtml]').click(function(e) {
                editor.insertHtml('<strong>插入HTML</strong>');
            });
            K('input[name=appendHtml]').click(function(e) {
                editor.appendHtml('<strong>添加HTML</strong>');
            });
            K('input[name=clear]').click(function(e) {
                editor.html('');
            });
        });
    </script>
</head>
<body>
<script type="text/javascript">

    $(function(){
        $("#role_edit_submit").click(function(){
            $('#role_edit_form').form('submit', {
                url:operate,
                onSubmit: function(){
                    // do some check
                    // return false to prevent submit;
                },
                success:function(data){

                    if(data==1){
                        $.messager.show({
                            title:'系统消息',
                            msg:'保存成功',
                            timeout:3000,
                            showType:'slide'
                        });
                        //弹出框关闭
                        $("#role_dialog").dialog("close");
                        $('#role_table').datagrid('reload');
                    }else{
                        $.messager.alert("系统信息","保存失败,请重新修改");
                    }
                }
            });
        })
    });
</script>
<div  style="padding:10px;">
    系统设置 >> 角色管理
    <hr/>

    <form action="" method="post" id="role_edit_form">
        <input type="hidden" name="id"/>
        <textarea name="content" style="width:800px;height:400px;visibility:hidden;">KindEditor</textarea>
        <p>
            <input type="button" name="getHtml" value="取得HTML" />
            <input type="button" name="isEmpty" value="判断是否为空" />
            <input type="button" name="getText" value="取得文本(包含img,embed)" />
            <input type="button" name="selectedHtml" value="取得选中HTML" />
            <br />
            <br />
            <input type="button" name="setHtml" value="设置HTML" />
            <input type="button" name="setText" value="设置文本" />
            <input type="button" name="insertHtml" value="插入HTML" />
            <input type="button" name="appendHtml" value="添加HTML" />
            <input type="button" name="clear" value="清空内容" />
            <input type="reset" name="reset" value="Reset" />
        </p>
        <table>
            <tr>
                <td>标题:</td>
                <td><input type="text" name="name"/> </td>
            </tr>
            <tr>
                <td>点赞:</td>
                <td><input type="text" name="sort"/> </td>
            </tr>
            <tr>
                <td>收藏:</td>
                <td><input type="text" name="remark"/> </td>
            </tr>
            <tr>
                <td colspan="2">
                    <a id="role_edit_submit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">保存</a>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
