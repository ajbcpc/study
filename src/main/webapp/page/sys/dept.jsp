<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/16
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
    var operate = 'update';
    function abc(obj){
        $('#role_dialog').dialog({
            title: '分配权限',
            width: 400,
            height: 200,
            closed: false,
            cache: false,
            href: 'sys/privilege.jsp',
            modal: true,
            onLoad:function(){
                /* $("#role_edit_form :text:eq(0)").val($("#role_table").datagrid("getSelected").name);
                $("#role_edit_form :text:eq(1)").val($("#role_table").datagrid("getSelected").sort);
                $("#role_edit_form :text:eq(2)").val($("#role_table").datagrid("getSelected").remark);
                $("#role_edit_form :hidden:eq(0)").val($("#role_table").datagrid("getSelected").id); */
                $("#privilege_form :hidden:eq(0)").val($(obj).parent().parent().siblings("[field='id']").children().eq(0).html());
                $('#privilege_tree').tree({
                    url:'showPrivilege?id='+$(obj).parent().parent().siblings("[field='id']").children().eq(0).html(),
                    checkbox:true
                });
            }
        });
    }

    $(function(){
        $('#role_table').datagrid({
            url:'showPost',
            columns:[[
                {field:'id',title:'编号',width:100,hidden:true},
                {field:'title',title:'标题',width:100},
                {field:'content',title:'详细内容',width:100,hidden:true},
                {field:'content_abb',title:'内容',width:100,align:'right'},
                {field:'likes',title:'赞',width:100,align:'right'},
                {field:'qwe',title:'备注',width:100,align:'right',
                    formatter: function(value,row,index){
                        return "<a href='javascript:void(0)' onclick='javascript:abc(this);return false;'>分配权限</a>";
                    }
                }
            ]],
            pagination:true,
            pageList:[2,4,6],
            fitColumns:true,
            striped:true,
            rownumbers:true,
            singleSelect:true,
            onDblClickRow: abc,
            toolbar: [{
                iconCls: 'icon-add',
                text:'增加',
                handler: function(){
                    operate = 'save';
                    $('#role_dialog').dialog({
                        title: '新建角色',
                        width: 400,
                        height: 200,
                        closed: false,
                        cache: false,
                        href: 'sys/dept_edit.jsp',
                        modal: true,
                        minimizable: true,
                        maximizable: true,
                        resizable: true,
                        onLoad:function(){
                            $("#role_edit_form :text:eq(0)").val('');
                            $("#role_edit_form :text:eq(1)").val('');
                            $("#role_edit_form :text:eq(2)").val('');
                            $("#role_edit_form :hidden:eq(0)").val('0');
                        }
                    });
                }
            },'-',{
                iconCls: 'icon-remove',
                text:'删除',
                handler: function(){alert('正在建设中')}
            },'-',{
                iconCls: 'icon-edit',
                text:'修改',
                handler: function(){
                    operate = 'update';
                    if($("#role_table").datagrid("getSelections").length==1){
                        //弹出窗口
                        $('#role_dialog').dialog({
                            title: '修改角色',
                            width: 400,
                            height: 200,
                            closed: false,
                            cache: false,
                            href: 'sys/dept_edit.jsp',
                            modal: true,
                            onLoad:function(){
                                $("#role_edit_form :text:eq(0)").val($("#role_table").datagrid("getSelected").name);
                                $("#role_edit_form :text:eq(1)").val($("#role_table").datagrid("getSelected").sort);
                                $("#role_edit_form :text:eq(2)").val($("#role_table").datagrid("getSelected").remark);
                                $("#role_edit_form :hidden:eq(0)").val($("#role_table").datagrid("getSelected").id);
                            }
                        });
                    }else{
                        $.messager.alert("系统信息","请选择<b style='color:red;'>一行</b>")
                    }
                }
            }]
        });
    })
</script>
<table id="role_table"></table>
<div id="role_dialog">Dialog Content.</div>
</body>
</html>
