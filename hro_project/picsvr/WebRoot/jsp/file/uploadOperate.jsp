<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="../../css/swfupload/default.css" rel="stylesheet" type="text/css" />
<script src="../../javascript/swfupload/swfupload.js"></script>
<script src="../../javascript/swfupload/swfupload.queue.js"></script>
<script src="../../javascript/swfupload/fileprogress.js"></script>
<script src="../../javascript/swfupload/handlers.js"></script>
<script type="text/javascript">
<!--
var swfUpload;

window.onload = function () {

    var settings = {

           flash_url : "swf/swfupload.swf" ,

           upload_url: "FileBatchUploadAction!batchUpload" ,

           post_params: { "param" : "uploadParams" } ,

           file_size_limit : "200MB" ,

           file_types : "*.*" ,

           file_post_name: "uploadFile" ,

           file_types_description : "All Files" ,

           file_upload_limit : 50,

           file_queue_limit : 0,

           custom_settings : {

              progressTarget : "fsUploadProgress" ,

              cancelButtonId : "btnCancel"

           } ,

           debug: false ,// 是否显示调试的 textarea

          

           // Button settings

           button_image_url: "images/swfupload/TestImageNoText_65x29.png" ,

           button_width: "65" ,

           button_height: "29" ,

           button_placeholder_id: "spanButtonPlaceHolder" ,

           button_text: '<span class="theFont"> 浏   览 </span>' ,

           button_text_style: ".theFont { font-size: 12; }" ,

           button_text_left_padding: 12,

           button_text_top_padding: 3,



           // The event handler functions are defined in handlers.js

           file_queued_handler : fileQueued,

           file_queue_error_handler : fileQueueError,

           file_dialog_complete_handler : fileDialogComplete,

           upload_start_handler : uploadStart,

           upload_progress_handler : uploadProgress,

           upload_error_handler : uploadError,

           upload_success_handler : uploadSuccess,

           upload_complete_handler : uploadComplete,

           queue_complete_handler : queueComplete

    } ;

    // 自定义属性，是否停止上传

    swfUpload = new SWFUpload(settings);

    swfUpload.stopped = false ;

  } ;



  function fileDialogComplete(numberselected, numberqueued) {

     if (swfUpload.getStats().files_queued > 0) {

      document.getElementById( "btnCancel" ).disabled = false ;

     }

  }





  function queueComplete(numberselected, numberqueued) {

    //alert(numberselected + "-" + numberqueued);

  }



  function upload() {

   if (swfUpload.getStats().files_queued > 0) {

     //document.getElementById("btnCancel").disabled = true;

      swfUpload.startUpload();

   } else {

       alert( " 请选择要上传的文件 !" );

   }

  }

 

  function stop() {

     if (swfUpload) {

       swfUpload.stopUpload();

    }

  }
//-->
</script>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>SWFUpload 多文件上传示例</title>
    
	<meta http-equiv = "content-type" content = "text/html; charset=UTF-8" >
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div id = "content">

           <h2></h2>

           <form id = "form" action = "FileBatchUploadAction!batchUpload" method = "post" enctype = "multipart/form-data" >

              <p></p>

              <div class = "fieldset flash" id = "fsUploadProgress" >
                  <span class = "legend" > 上传文件列表 </span>
              </div>

              <div id = "divMovieContainer" >
                  <input id = "filenamelist" type = "hidden" name = "filenamelist" />
                  
                  <span id = "spanButtonPlaceHolder" ></span>
                  
                  <input type = "button" value = " 上   传 " onclick = "upload();" style = "margin-left: 2px; font-size: 8pt; height: 29px;" />

                  <input type = "button" value = " 停   止 " onclick = "stop();" style = "display: none; margin-left: 2px; font-size: 8pt; height: 29px;" />

                  <input id = "btnCancel" type = "button" value = " 取消所有 " onclick = "swfUpload.cancelQueue();" disabled = "disabled" style = "margin-left: 2px; font-size: 8pt; height: 29px;" />

                  <input type = "button" onclick = "javaScript:window.close();" name = "bt" style = "margin-left: 2px; font-size: 8pt; height: 29px;" value = " 关   闭 " />

              </div>

           </form>

       </div>
  </body>
</html>
