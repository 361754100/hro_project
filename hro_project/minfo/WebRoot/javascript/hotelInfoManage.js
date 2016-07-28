/**
 * 酒店管理页面js
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
        sAjaxSource: "/mhotel/hotel/queryHotelInfo.do",  
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
    		$('#tips-modal-dialog .modal-body').html('<span>请选择要删除的数据！</span>');
    		$('#tips-modal-dialog').modal('show');
    	}else{
    		$('#del-modal-dialog').modal('show');
    	}
    });
    
    $('#editBtn').on( 'click', function(){
    	var rows = $('#hotelInfoTable').getSelectRows();
    	if( rows == null || rows == undefined || rows.length == 0 || rows.length > 1){
    		$('#tips-modal-dialog .modal-body').html('<span>请选择一条记录！</span>');
    		$('#tips-modal-dialog').modal('show');
    		return;
    	}
    	$('#editHotelInfo #in_hotelId').val(rows[0].id);
    	$('#editHotelInfo #in_hotelName').val(rows[0].hotelName);
    	$('#editHotelInfo #in_hotelAddress').val(rows[0].address);
    	$('#editHotelInfo #in_hotelIntroduction').val(rows[0].introduction);
    	
    	$('#edit-modal-dialog').modal('show');
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
	   url: '/mhotel/hotel/saveHotelInfo.do',
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
 * 修改酒店信息
 */
function confirmUpdate(){
	var bean = formToBean(document.getElementById("editHotelInfo"));
	var jsonStr = JSON.stringify(bean);
	
	$.ajax({
	   type: 'POST',
	   url: '/mhotel/hotel/updateHotelInfo.do',
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
 * 删除选中的数据
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
	   url: '/mhotel/hotel/deleteHotelInfo.do',
	   data: jsonStr,
	   contentType: 'application/json',
	   dataType: 'json',
	   success: function(rtObj){
		  $('#del-modal-dialog').modal('hide');
	      table.ajax.reload();
	   }
	});
}