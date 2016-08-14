/**
 * 酒店信息管理js
 */
var table = null;

$(document).ready(function() {
    table = $('#hotelInfoTable').DataTable( {
        pagingType : 'full',
        scrollY : '300px',
        scrollCollapse : true,
        language : dataTable_language,
        processing: false,
        serverSide: true,
        ajax: {
            "url": "/minfo/hotel/queryHotelInfo.do",
            "type": "POST",
            "dataType": "json",
            "data": function(aoData){
            	dataTableFormToBean(document.getElementById("queryForm"), null, aoData);
            }
        },
        /***
        sServerMethod: "POST",
        sAjaxSource: "/minfo/hotel/queryHotelInfo.do",  
        fnServerParams: function ( aoData ) {
        	//dataTableFormToBean(document.getElementById("queryForm"), null, aoData);
        	formToBean(document.getElementById("queryForm"));
        },
        ***/
        aLengthMenu : [10, 20, 30, 50, 100],
        bDestroy : true,
        //bSort: false,
        columns: [
               { data: 'id', name: 'ID' },
               { data: 'hotelName', name: 'HOTEL_NAME' },
               { data: 'address', name: 'ADDRESS' },
               { data: 'introduction', name: 'INTRODUCTION' },
               { data: 'bdX', name: 'BD_X' },
               { data: 'bdY', name: 'BD_Y' },
               { data: 'picInfos[0].picName', name: 'PIC_NAME' }
           ],
        columnDefs: [
               {
                 "targets": [ 0 ],
                 "visible": false,
                 "searchable": false
               } 
           ]
    } );
    
    table.on( 'xhr', function () {
        var data = table.ajax.params();
        
        resizeTable_scrollBody();
    } );
    
    $('#hotelInfoTable tbody').on( 'click', 'tr', function () {
        $(this).toggleClass('selected');
    } );
    
    $('#searchBtn').on( 'click', function(){
    	table.ajax.reload();
    });
    
    $('#deleteBtn').on( 'click', function(){
    	var rows = $('#hotelInfoTable').getSelectRows();
    	var len = rows.length;
    	if(len == 0){
    		$('#tips-modal-dialog .modal-body').html('<span>请选择一行记录</span>');
    		$('#tips-modal-dialog').modal('show');
    	}else{
    		$('#del-modal-dialog').modal('show');
    	}
    });
    
    $('#editBtn').on( 'click', function(){
    	var rows = $('#hotelInfoTable').getSelectRows();
    	if( rows == null || rows == undefined || rows.length == 0 || rows.length > 1){
    		$('#tips-modal-dialog .modal-body').html('<span>请选择一行记录</span>');
    		$('#tips-modal-dialog').modal('show');
    		return;
    	}
    	$('#editHotelInfo #in_hotelId').val(rows[0].id);
    	$('#editHotelInfo #in_hotelName').val(rows[0].hotelName);
    	$('#editHotelInfo #in_hotelAddress').val(rows[0].address);
    	$('#editHotelInfo #in_hotelIntroduction').val(rows[0].introduction);
    	
    	var tmpObj = new Object();
    	tmpObj.groupId = rows[0].id;
    	tmpObj.picType = 'HOTEL_TYPE';
    	
    	var jsonStr = JSON.stringify(tmpObj);
    	$.ajax({
		   type: 'POST',
		   url: '/minfo/hotel/getHotelPicInfo.do',
		   data: jsonStr,
		   contentType: 'application/json',
		   dataType: 'json',
		   async: true,
		   success: function(rtObj){
			  if(rtObj != null && rtObj != undefined){
				  var picInfoList = rtObj.picInfoList;
				  var tmpHtml = '';
				  for( var i = 0; i< picInfoList.length; i++){
					  tmpHtml += '<img src="/picsvr'+picInfoList[i].virtualPicPath+'/'+picInfoList[i].picName+'" width="100" height="100" />';
				  }
				  
				  alert(' tmpHtml --->'+ tmpHtml );
				  
				  $('#tab_base_info').html(tmpHtml);
			  }
		   }
		});
    	
    	$('#edit-modal-dialog').modal('show');
    	
    	var iframe = $("#fileUploadFrame")[0];
    	var params = new Object();
    	params.filePath = '/files';
    	params.picType = 'HOTEL_TYPE';
    	params.groupId = rows[0].id;
    	
    	iframe.contentWindow.initSwfUpload(params);
        
    });
    
    $('#resetBtn').on( 'click', function(){
    	$('#queryForm')[0].reset();
    });
    
} );

/**
 * 保存酒店信息
 **/
function saveInfo(){
	var bean = formToBean(document.getElementById("addHotelInfo"));
	var jsonStr = JSON.stringify(bean);
	
	$.ajax({
	   type: 'POST',
	   url: '/minfo/hotel/saveHotelInfo.do',
	   data: jsonStr,
	   contentType: 'application/json',
	   dataType: 'json',
	   success: function(rtObj){
		  $('#addHotelInfo')[0].reset();
	      $('#add-modal-dialog').modal('hide');
	      table.ajax.reload();
	   }
	});
}

/**
 * 更新酒店基本信息
 */
function confirmUpdate(){
	var bean = formToBean(document.getElementById("editHotelInfo"));
	var jsonStr = JSON.stringify(bean);
	
	$.ajax({
	   type: 'POST',
	   url: '/minfo/hotel/updateHotelInfo.do',
	   data: jsonStr,
	   contentType: 'application/json',
	   dataType: 'json',
	   success: function(rtObj){
		  $('#editHotelInfo')[0].reset();
	      $('#edit-modal-dialog').modal('hide');
	      table.ajax.reload();
	   }
	});
}

/**
 * 删除酒店信息
 **/
function confirmDelete(){
	var rows = $('#hotelInfoTable').getSelectRows();
	var ids = [];
	var len = rows.length;
	for(var i = 0; i< len; i++){
		ids.push(rows[i].id);
	}
	var idStr = ids.join(',').toString();
	var obj = new Object();
	obj.ids = idStr;
	
	var jsonStr = JSON.stringify(obj);
	
	$.ajax({
	   type: 'POST',
	   url: '/minfo/hotel/deleteHotelInfo.do',
	   data: jsonStr,
	   contentType: 'application/json',
	   dataType: 'json',
	   success: function(rtObj){
		  $('#del-modal-dialog').modal('hide');
	      table.ajax.reload();
	   }
	});
}

/**
 * 打印功能
 * @param gridcontrol
 * @param title
 */
function printPage(gridcontrol,title) {
	
	var titleHtml = '<OBJECT id="printBrowser" height=0 width=0 classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 name=wb></OBJECT><span>打印预览</span>';
	
	var newwin = window.open("printer.html", "", "");
	newwin.document.write(titleHtml);
	newwin.document.location.reload();
	
	try{      
       var Wsh=new ActiveXObject("WScript.Shell");      
       Wsh.RegWrite("HKEY_CURRENT_USER\\Software\\Microsoft\\Internet Explorer\\PageSetup\\header","");  //通过修改注册表的方式 页眉和页脚也可以改成你想要的内容  
       Wsh.RegWrite("HKEY_CURRENT_USER\\Software\\Microsoft\\Internet Explorer\\PageSetup\\footer","");     
    }catch(e){  
       //alert(e.name+e.message);  
    }
	
	newwin.document.getElementById('printBrowser').execWB(7,1);
	//newwin.print();
	newwin.close();
}
