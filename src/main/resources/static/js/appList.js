var pageCurr;
var form;
$(function() {
    layui.use('table', function(){
        var table = layui.table;
        form = layui.form;

        tableIns=table.render({
            elem: '#appList',
            url:'/getSwAppList',
            method: 'post', //默认：get请求
            cellMinWidth: 50,
            page: true,
            request: {
                pageName: 'pageNum', //页码的参数名称，默认：pageNum
                limitName: 'pageSize' //每页数据量的参数名，默认：pageSize
            },
            response:{
                statusName: 'code', //数据状态的字段名称，默认：code
                statusCode: 200, //成功的状态码，默认：0
                countName: 'totals', //数据总数的字段名称，默认：count
                dataName: 'list' //数据列表的字段名称，默认：data
            },
            cols: [[
                 {field:'id', title:'id',align:'center',width : 50}
                ,{field:'appName', title:'应用名',align:'center'}
                ,{field:'appNameSpace', title:'应用名字空间',align:'center'}
                ,{field:'appDesc', title:'应用描述',align:'center'}
                ,{field:'ownerName', title:'所有者名字',align:'center'}
                ,{field:'ownerEmail', title:'邮箱',align:'center'}
                ,{field:'status', title: '开关状态',align:'center',
                templet: function(d){
                           return d.status=='-1' ? "已删除":"正常";}}
                ,{field:'createtime', title: '创建时间',align:'center'}
                ,{field:'updatetime', title: '更新时间',align:'center'}
                ,{title:'操作',align:'center', toolbar:'#optBar',width : 150}
            ]],
            done: function(res, curr, count){
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                //console.log(res);
                //得到当前页码
                console.log(curr);
               /* $("[data-field='userStatus']").children().each(function(){
                    if($(this).text()=='1'){
                        $(this).text("有效")
                    }else if($(this).text()=='0'){
                        $(this).text("失效")
                    }
                });*/
                //得到数据总量
                //console.log(count);
                pageCurr=curr;
            }
        });

        //监听工具条
        table.on('tool(appTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                //删除
                del(data,data.id,data.appName);
            } else if(obj.event === 'edit'){
                //编辑
                edit(data,"编辑");
            }
        });

        //监听提交
        form.on('submit(appSubmit)', function(data){
            // TODO 校验
            formSubmit(data);
            return false;
        });
    });

    //搜索框
    layui.use(['form','laydate'], function(){
            var form = layui.form ,layer = layui.layer
                ,laydate = layui.laydate;
            //日期
            /*laydate.render({
                elem: '#startTime'
            });
            laydate.render({
                elem: '#endTime'
            });*/
            //TODO 数据校验
            //监听搜索框
            form.on('submit(appSearchSubmit)', function(data){
                load(data);
                return false;
            });
        });

});

//添加开关
function addApp(){
    edit(null,"添加新应用");
}

//添加和编辑操作
function edit(data,title){
     //没有数据，说明是要新建操作
    if(data==null || data==""){
        $("#id").val("");
    }else{
    //有数据，说明想编辑。
    //此处应该是要填充到layer.open（）中，但是为何不成功啊
    //发现问题，下面的$()参数需要  和  setSwSwitch表单 中的属性id 保持一致
    //要传的参数
        $("#id").val(data.id);
        $("#appName").val(data.appName);
        $("#appNameSpace").val(data.appNameSpace);
        $("#appDesc").val(data.appDesc);
        $("#ownerName").val(data.ownerName);
        $("#ownerEmail").val(data.ownerEmail);
        $("#status").val(data.status);
        $("#createtime").val(data.createtime);
        $("#updatetime").val(data.updatetime);
    }
    var pageNum = $(".layui-laypage-skip").find("input").val();
    $("#pageNum").val(pageNum);
    layer.open({
        type:1,
        title: title,
        fixed:false,//固定在可视区域
        resize :false,//是否允许拉伸
        shadeClose: true,//点击遮罩关闭
        area: ['550px','400px'],
        content:$('#setSwApp'),
        end:function(){
            cleanEditForm();
        }
    });
}
//提交开关信息表单
function formSubmit(obj){
    $.ajax({
        type: "POST",
        data: $("#SwAppForm").serialize(),//发送到服务器的数据
        url: "/setSwApp",//向后端发送请求的地址
        success: function (data) {
            if (data.code == 1) {
                layer.alert(data.msg,function(){
                    layer.closeAll();
                    load(obj);
                });
            } else {
                layer.alert(data.msg);
            }
        },
        error: function () {
            layer.alert("操作请求错误，请您稍后再试",function(){
                layer.closeAll();
                //加载load方法
                load(obj);//自定义
            });
        }
    });
}

function del(obj,id,name) {
    if(null!=id){
      layer.confirm('您确定要删除'+name+'吗？', {
                      btn: ['确认','返回'] //按钮
                  }, function(){
                      $.post("/delApp",{"id":id,"appName":name},function(data){
                          if (data.code == 1) {
                              layer.alert(data.msg,function(){
                                  layer.closeAll();
                                  load(obj);
                              });
                          } else {
                              layer.alert(data.msg);
                          }
                      });
                  }, function(){
                      layer.closeAll();
                  });
    }
}

function load(obj){
    tableIns.reload({
        where: obj.field
        , page: {
            curr: 1 //从当前页码开始
        }
    });
}

//清空表单，文明注册，不留垃圾
function cleanRuleForm(){
     $("#id").val("");
     $("#appName").val("");
     $("#appNameSpace").val("");
     $("#appDesc").val("");
     $("#ownerName").val("");
      $("#ownerEmail").val("");
     $("#status").val("");
     $("#createtime").val("");
     $("#updatetime").val("");
}



















