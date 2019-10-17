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
    // var editor;
    // KindEditor.ready(function(K) {
    //     $.messager.alert("系统信息","111");
    //     editor = K.create('textarea[name="dept_edit_content"]', {
    //         allowFileManager : true
    //     });
    //     $.messager.alert("系统信息","222");
    // });
    function kedit(kedit){
        var editor = KindEditor.create(kedit,{
            width: '780px',
            height: '700px',
            resizeMode: 0,
            allowPreviewEmoticons: false

        });
    }

    $(function(){
        kedit('textarea[name="dept_edit_content"]');
    })
</script>
<div  style="padding:10px;">
    系统设置 >> 部门管理
    <hr/>

    <form action="" method="post" id="role_edit_form">
        <input type="hidden" name="id"/>
        <label>
            <textarea name="dept_edit_content" style="width:800px;height:400px;visibility:hidden;"  cols="2" rows="3">KindEditor</textarea>
        </label>
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
