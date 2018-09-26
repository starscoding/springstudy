<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="top.jsp"></jsp:include>
<form action="/save" method="post" class="form-horizontal">
    <input type="hidden" name="scheduleJobId" value="${scheduleJobVo.scheduleJobId}">
    <input type="hidden" name="keywords" value="${scheduleJobVo.keywords}">

    <div class="form-group">
        <label for="jobName" class="col-sm-2 control-label">任务名称</label>
        <div class="col-sm-8">
            <input type="text" name="jobName" value="${scheduleJobVo.jobName}" class="form-control">
        </div>
    </div>

    <div class="form-group">
        <label for="jobGroup" class="col-sm-2 control-label">任务分组</label>
        <div class="col-sm-8">
            <input type="text" name="jobGroup" value="${scheduleJobVo.jobGroup}" class="form-control">
        </div>
    </div>

    <div class="form-group">
        <label for="aliasName" class="col-sm-2 control-label">任务别名</label>
        <div class="col-sm-8">
            <input type="text" name="aliasName" value="${scheduleJobVo.aliasName}" class="form-control">
        </div>
    </div>

    <div class="form-group">
        <label for="cronExpression" class="col-sm-2 control-label">时间表达式</label>
        <div class="col-sm-8">
            <input type="text" name="cronExpression" value="${scheduleJobVo.cronExpression}" class="form-control">
        </div>
    </div>

    <div class="form-group">
        <label for="isSync" class="col-sm-2 control-label">是否异步</label>
        <div class="col-sm-8">
            <input type="radio" name="isSync" value="false" #if (${scheduleJobVo.isSync}=="false") checked #end/>同步
            <input type="radio" name="isSync" value="true" #if (${scheduleJobVo.isSync}=="true") checked #end/>异步
        </div>
    </div>

    <div class="form-group">
        <label for="url" class="col-sm-2 control-label">任务执行url</label>
        <div class="col-sm-8">
            <input type="text" name="url" value="${scheduleJobVo.url}" class="form-control">
        </div>
    </div>

    <div class="form-group">
        <label for="description" class="col-sm-2 control-label">任务描述</label>
        <div class="col-sm-8">
            <input type="text" name="description" value="${scheduleJobVo.description}" class="form-control">
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary">保存</button>
            <a href="listJob.jsp" class="btn btn-default" role="button">返回</a>
        </div>
    </div>

</form>