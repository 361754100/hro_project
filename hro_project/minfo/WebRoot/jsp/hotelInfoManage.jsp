<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>酒店信息管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<!-- <link rel="stylesheet" href="/msvr/css/bootstrap/bootstrap.css"> -->
	<!-- 可选的Bootstrap主题文件（一般不用引入） -->
	<!-- <link rel="stylesheet" href="/msvr/css/bootstrap-theme.css"> -->
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<!-- <script src="/msvr/javascript/jquery/jquery-1.11.3.js"></script> -->
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<!-- <script src="/msvr/javascript/bootstrap/bootstrap.js"></script> -->
	
	<link rel="stylesheet" href="/msvr/css/uikit/uikit.css">
	<link rel="stylesheet" href="/msvr/css/datatable/dataTables.uikit.css">
	<style type="text/css">

	</style>
	
	<script type="text/javascript" src="/msvr/javascript/jquery/jquery-1.11.3.js"></script>
	<script type="text/javascript" src="/msvr/javascript/datatable/jquery.dataTables.js"></script>
	<script type="text/javascript" src="/msvr/javascript/datatable/dataTables.uikit.js"></script>
	<script type="text/javascript" src="/msvr/javascript/datatable/dataTables.language.zh_cn.js"></script>
	
	<script type="text/javascript" >
	$(document).ready(function() {
	    $('#hotelInfoTable').DataTable( {
	        pagingType : 'full',
	        scrollY : '85%',
	        scrollCollapse : true,
	        //ordering : false,
	        language : dataTable_language,
	        ajax : '/mhotel/hotel/queryHotelInfo.do',
	        columns: [
                { data: 'id' },
                { data: 'hotelName' },
                { data: 'address' },
                { data: 'introduction' },
                { data: 'bdX' },
                { data: 'bdY' },
                { data: 'picInfos[0].picName' }
            ]
	    } );
	    
	    $('#hotelInfoTable tbody').on( 'click', 'tr', function () {
	        $(this).toggleClass('selected');
	    } );
	} );
	
	</script>
  </head>
  
  <body>
    <table id="hotelInfoTable" class="uk-table uk-table-hover uk-table-striped" cellspacing="0" width="100%">
    	<thead>
            <tr>
                <th width="14%">主键</th>
                <th>酒店名称</th>
                <th>地址</th>
                <th>简介</th>
                <th>经度</th>
                <th>纬度</th>
                <th>图片名称</th>
            </tr>
        </thead>
    </table>
  </body>
</html>
