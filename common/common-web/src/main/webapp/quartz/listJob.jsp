<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="top.jsp"></jsp:include>
<a href="input-schedule-job.shtml" class="btn btn-success" role="button">添加任务</a>
<table class="table table-bordered table-margin-top">
    <tr>
        <td>任务名称</td>
        <td>任务别名</td>
        <td>任务分组</td>
        <td>触发器</td>
        <td>任务状态</td>
        <td>时间表达式</td>
        <td>是否异步</td>
        <td>任务执行url</td>
        <td>任务描述</td>
        <td>操作</td>
    </tr>
    <tr>
        <td>${item.jobName}</td>
        <td>${item.aliasName}</td>
        <td>${item.jobGroup}</td>
        <td>${item.jobTrigger}</td>
        <td class="status-color">${item.status}</td>
        <td>${item.cronExpression}</td>
        <td>
            ${item.isSync}

        </td>
        <td>${item.url}</td>
        <td>${item.description}</td>
        <td>
            <!--<a href="input-schedule-job.shtml?scheduleJobId=$!{item.scheduleJobId}">修改</a>-->
            <a class="btn btn-danger" role="button"
               href="pause-schedule-job.shtml?scheduleJobId=$!{item.scheduleJobId}"><i class="fa fa-pause-circle-o"
                                                                                       aria-hidden="true"></i>暂停</a>
            <a class="btn btn-danger" role="button"
               href="resume-schedule-job.shtml?scheduleJobId=$!{item.scheduleJobId}"><i class="fa fa-reply"
                                                                                        aria-hidden="true"></i>恢复</a>
            <a class="btn btn-danger" role="button"
               href="run-once-schedule-job.shtml?scheduleJobId=$!{item.scheduleJobId}"><i class="fa fa-play"
                                                                                          aria-hidden="true"></i>运行一次</a>
            <a class="btn btn-danger" role="button"
               href="input-schedule-job.shtml?scheduleJobId=$!{item.scheduleJobId}&keywords=delUpdate"><i
                    class="fa fa-pencil" aria-hidden="true"></i>修改</a>
            <a class="btn btn-danger" role="button"
               href="delete-schedule-job.shtml?scheduleJobId=$!{item.scheduleJobId}"><i class="fa fa-trash"
                                                                                        aria-hidden="true"></i>删除</a>
        </td>
    </tr>
</table>
<p class="lead">运行中的任务</p>
<table class="table table-bordered">
    <tr>
        <td>任务名称</td>
        <td>任务别名</td>
        <td>任务分组</td>
        <td>触发器</td>
        <td>任务状态</td>
        <td>时间表达式</td>
        <td>是否异步</td>
        <td>任务执行url</td>
        <td>任务描述</td>
    </tr>
    #foreach($item in $executingJobList)
    <tr>
        <td>${item.jobName}</td>
        <td>${item.aliasName}</td>
        <td>${item.jobGroup}</td>
        <td>${item.jobTrigger}</td>
        <td>${item.status}</td>
        <td>${item.cronExpression}</td>
        <td>${item.isSync}</td>
        <td>${item.url}</td>
        <td>${item.description}</td>
    </tr>
    #end
</table>