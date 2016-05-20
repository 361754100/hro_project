var dataTable_language = {
    "decimal":        "",
    "emptyTable":     "没找到相关的查询结果",
    "info":           "第_PAGE_页&nbsp;共_PAGES_页&nbsp; 显示_START_到_END_,共_TOTAL_记录 ",
    "infoEmpty":      "共0页",
    //"infoFiltered":   "(filtered from _MAX_ total entries)",
    "infoFiltered":   "",
    "infoPostFix":    "",
    "thousands":      ",",
    "lengthMenu":     "每页 _MENU_ 行",
    "loadingRecords": "加载中...",
    "processing":     "进行中...",
    "search":         "Search:",
    "zeroRecords":    "没找到相关的查询结果",
    "paginate": {
        "first":      "首页",
        "last":       "尾页",
        "next":       "下一页",
        "previous":   "上一页"
    },
    "aria": {
        "sortAscending":  ": 升序排列",
        "sortDescending": ": 降序排列"
    }
};


var resizeTable_scrollBody = function(){
	//如果右边面板包含 dataTables_scrollBody 则要重新计算 dataTables_scrollBody的最大高度
	var right_content_div_H = $('#right_content_div').height();
	
	var select_panel_H = 0;
	var select_panels = $('#right_content_div .select_panel');
	if(select_panels != null ){
		select_panel_H = $(select_panels[0]).height();
	}
	
	var btn_list_panel_H = 0;
	var btn_list_panels = $('#right_content_div .btn_list_panel');
	if(btn_list_panels != null ){
		btn_list_panel_H = $(btn_list_panels[0]).height();
	}

	var dataTables_scrollHead_H = 0;
	var dataTables_scrollHeads = $('#right_content_div .dataTables_scrollHead');
	if(dataTables_scrollHeads != null ){
		dataTables_scrollHead_H = $(dataTables_scrollHeads[0]).height();
	}
	
	var pageButton_H = 30;
	var dataTables_scrollBody_H = right_content_div_H - select_panel_H - btn_list_panel_H - dataTables_scrollHead_H - pageButton_H - 33;
	
	$('#right_content_div').find('.dataTables_scrollBody').css({'max-height': dataTables_scrollBody_H+'px'});
};