var pageCurr;
var form;
$(function() {
    layui.use(['table','element'], function(){
        var table = layui.table;
        form = layui.form;
        var element = layui.element;

        tableIns=table.render({
            elem: '#toggleList',
            url:'/toggle/getSwSwitchList',
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
                ,{field:'appId', title:'appId',align:'center'}
                ,{field:'name', title:'开关名字',align:'center'}
                ,{field:'nameKey', title:'开关唯一键',align:'center'}
                ,{field:'nameDesc', title: '开关描述',align:'center'}
                ,{field:'ownerEmail', title: '负责人邮箱',align:'center'}
                ,{field:'status', title: '开关状态',align:'center',
                templet: function(d){
                           return d.status=='-1' ? "已删除":"正常";}}
                ,{field:'type', title: '开关类型',align:'center'}
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
        table.on('tool(toggleTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                //删除
                del(data,data.id,data.name);
            } else if(obj.event === 'edit'){
                //编辑,根据type生成不同的开关规则表格
                //渲染开关规则页信息
                //var switchSearchId = data.id;
                //每次编辑重载规则表格
                //loadrules(data.id);
                if(data.type==1||data.type==3){
                tableIns2=table.render({
                                          elem: '#rulesList',
                                          url:'/toggle/getSwSwitchRulesList',
                                          method: 'post', //默认：get请求
                                          cellMinWidth: 50,
                                          page: true,
                                          where:{
                                              switchSearchId: data.id
                                          },
                                          request: {
                                               pageName: 'pageNum', //页码的参数名称，默认：pageNum
                                               limitName: 'pageSize' ,//每页数据量的参数名，默认：pageSize
                                          },
                                          response:{
                                               statusName: 'code', //数据状态的字段名称，默认：code
                                               statusCode: 200, //成功的状态码，默认：0
                                               countName: 'totals', //数据总数的字段名称，默认：count
                                                dataName: 'list' //数据列表的字段名称，默认：data
                                          },
                                          cols: [[
                                                  {field:'id', title:'id',align:'center',width : 50}
                                                  ,{field:'rule', title: '开关规则',align:'center'}
                                           ]],
                                 });
                }else{
                tableIns3=table.render({
                                          elem: '#rulesList',
                                          url:'/toggle/getSwSwitchRulesList',
                                          method: 'post', //默认：get请求
                                          cellMinWidth: 50,
                                          page: true,
                                          where:{
                                              switchSearchId: data.id
                                          },
                                          request: {
                                               pageName: 'pageNum', //页码的参数名称，默认：pageNum
                                               limitName: 'pageSize' ,//每页数据量的参数名，默认：pageSize
                                          },
                                          response:{
                                               statusName: 'code', //数据状态的字段名称，默认：code
                                               statusCode: 200, //成功的状态码，默认：0
                                               countName: 'totals', //数据总数的字段名称，默认：count
                                                dataName: 'list' //数据列表的字段名称，默认：data
                                          },
                                          cols: [[
                                                  {field:'id', title:'id',align:'center',width : 50}
                                                  ,{field:'rule', title: '开关规则',align:'center'}
                                                  ,{field:'sort', title: 'sort',align:'center',sort:true}
                                           ]],
                                 });
                }

                openSwSwitch(data,"编辑");
            }
        });

        //监听提交
        form.on('submit(SwSwitchSubmit)', function(data){
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
            form.on('submit(toggleSearchSubmit)', function(data){
                //重新加载table
                //data数据 参数名字 尽量和 后台 请求的参数 名字一致

                load(data);
                return false;
            });
        });

});

//添加开关
function addSwSwitch(){
    openSwSwitch(null,"添加开关");
}

//添加和编辑操作
function openSwSwitch(data,title){
     //没有数据，说明是要新建操作
    if(data==null || data==""){
        $("#id").val("");
    }else{
    //有数据，说明想编辑。
    //此处应该是要填充到layer.open（）中，但是为何不成功啊
    //发现问题，下面的$()参数需要  和  setSwSwitch表单 中的属性id 保持一致
    //要传的参数
        $("#id").val(data.id);
        $("#swSwitchappId").val(data.appId);
        $("#swSwitchName").val(data.name);
        $("#swSwitchNameKey").val(data.nameKey);
        $("#swSwitchNameDesc").val(data.nameDesc);
        $("#swSwitchownerEmail").val(data.ownerEmail);
        $("#swSwitchstatus").val(data.status);
        $("#swSwitchtype").val(data.type);
        $("#swSwitchcreatetime").val(data.createtime);
        $("#swSwitchupdatetime").val(data.updatetime);

     //获取到根据开关id查询的开关规则列表数据
     /* $.ajax({
          type:"POST",
          data: {
          pageNum:pageNum,
          pageSize:10,
          switchSearchId:switchSearchId
          },
          url: "/toggle/getSwSwitchRulesList",
          success:function(res){
          $("#rules").val(res.rule);
          var rule = res.list.rule;
          //不用render(),怎样渲染表格
          loadrules(res);
          }
      });*/
    }
    var pageNum = $(".layui-laypage-skip").find("input").val();
        $("#pageNum").val(pageNum);
    //弹出添加开关的页面（与编辑的页面相同）
    layer.open({
          type:1,//Layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
          title: title,
          fixed:false,//固定在可视区域
          resize :false,//是否允许拉伸
          shadeClose: true,//点击遮罩关闭
          area: ['550px','400px'],
          content:$('#demo'),//可以传入普通的html内容，还可以指定DOM.这里content是一个DOM，是一个html界面接口
          //clear操作,新建后，再次点击新建，表格内容清空
          end:function(){
               cleanForm();
          }
    });

}
//提交开关信息表单
function formSubmit(obj){
    $.ajax({
        type: "POST",
        //往后台传输数据（对应实体类参数）时，表单中各项的name属性尽量与实体类中变量名相同
        data: $("#SwSwitchForm").serialize(),//发送到服务器的数据
        url: "/toggle/setSwSwitch",//向后端发送请求的地址
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
                      $.post("/toggle/del",{"id":id,"name":name },function(data){
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
    //重新加载开关列表table
   //再次强调obj.field属性名字要和后台参数一样，否则后台方法中的参数需要添加注解
    tableIns.reload({
        where: obj.field
        , page: {
            curr: pageCurr //从当前页码开始
        }
    });
}

function loadrules(id){
    //重新加载开关规则列表table
   //再次强调obj.field属性名字要和后台参数一样，否则后台方法中的参数需要添加注解
    tableIns2.reload({
        where: {
        switchSearchId: id
        }
        , page: {
            curr: pageCurr //从当前页码开始
        }
    });
}

//清空表单，文明注册，不留垃圾
function cleanForm(){
     $("#id").val("");
     $("#swSwitchappId").val("");
     $("#swSwitchName").val("");
     $("#swSwitchNameKey").val("");
     $("#swSwitchNameDesc").val("");
     $("#swSwitchownerEmail").val("");
     $("#swSwitchstatus").val("");
     $("#swSwitchtype").val("");
     $("#swSwitchcreatetime").val("");
     $("#swSwitchupdatetime").val("");
}



















