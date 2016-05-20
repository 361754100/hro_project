/**
 * ��js������������StringUtil.js�����⣬��������StringUtil.js
 */
/**
 * ���� : select_setSelected("BASETYPE",{value:2,innerHTML:"�̶�����ǹ"});
 * "BASETYPE"��һ��select��idֵ,
 */
function select_setSelected(id, obj )
{
	var oSelect = document.getElementById(id);
	
	if(oSelect!=null){
		for(var i=0;i<oSelect.options.length;i++)
		{
			var isSelected = true;
			var pName;
			for(pName in obj)
			{
				if(oSelect.options[i][pName]!=obj[pName])
				{
					isSelected = false;
				}
			}
			if(isSelected==true)
			{
				oSelect.options[i].selected = true;
				break;//
			}
		}
	}
	else{
		alert("domUtil.js����,������idΪ'"+id+"',����");
	}
}


//����index���������б�
function select_setSelectedByIndex(id, index) {
	var oSelect = document.getElementById(id);
	if(oSelect!=null){
		for(var i=0;i<oSelect.options.length;i++) {
			if (i == index) {
				//alert("id=" + id + ",i=" + i + ";index=" + index);
				oSelect.options[i].selected = true;
				break;
			}
		}
	}
	else{
		alert("domUtil.js����,������idΪ'"+id+"',����");
	}
}

/*
* ���� : select_indexOf("BASETYPE",{value:2,innerHTML:"�̶�����ǹ"});
* "BASETYPE"��һ��select��idֵ,
* ���ظ������Ժ�obj�ĸ�������һ����option���±�
*/
function select_indexOf(id, obj){
	var oSelect = document.getElementById(id);
	
	if(oSelect!=null){
		for(var i=0;i<oSelect.options.length;i++)
		{
			var isExist = true;
			var pName;
			for(pName in obj)
			{
				if(oSelect.options[i][pName]!=obj[pName])
				{
					isExist = false;
				}
			}
			if(isExist==true)
			{
				return i;
			}
		}
		return -1;
	}
	else{
		alert("domUtil.js����,������idΪ'"+id+"',����");
	}
}


/**
 * ������һ��form����,�����form�����Ԫ��ת��һ��bean
 */
function formToBean(form, ignoreButton){
	if( ignoreButton==null || ignoreButton==true) {
		ignoreButton = true;
	} else {
		ignoreButton = false;
	}
	var object = {};
	var elements = form.elements;
	for (var i = 0; i < elements.length; i++ ) {
		var element = elements[i];
		switch (element.type) {
		case "radio" : 
			if (!object[element.name]) {object[element.name] = new Array()};
			if (element.checked) {object[element.name].push(element.value);}
			break;
		case "checkbox" : 
			if (!object[element.name]) {object[element.name] = new Array()};
			if (element.checked) {object[element.name].push(element.value);}
			break;
		case "select-one" : 
			var value = '', opt, index = element.selectedIndex;
			if (index >= 0) {
				opt = element.options[index];
				value = opt.value;
				if (!value && !('value' in opt)) value = opt.text;
			}
			object[element.name] = value;
			break;
		case "select-multiple" :
			if (!object[element.name]) {object[element.name] = new Array()};
			for (var j = 0; j < element.options.length; j++) {
				var opt = element.options[j];
				if (opt.selected) {
					var optValue = opt.value;
					if (!optValue && !('value' in opt)) optValue = opt.text;
					object[element.name].push(optValue);
				}
		    }
		    break;
		default : 
			if (ignoreButton) {
				if (element.type!="submit" && element.type!="button" && element.type!="reset") {
					object[element.name] = element.value;
				}
			} else {
				object[element.name] = element.value;
			}
			break;
		}
	}
	return object;
}


/**
 * ������һ��form����,�����form�����Ԫ��ת��һ��bean
 */
function dataTableFormToBean(form, ignoreButton, aoData){
	if( ignoreButton==null || ignoreButton==true) {
		ignoreButton = true;
	} else {
		ignoreButton = false;
	}
	
	//var olist = new Array();
	var object = {};
	var elements = form.elements;
	for (var i = 0; i < elements.length; i++ ) {
		var element = elements[i];
		
		switch (element.type) {
		case "radio" : 
			if (!object[element.name]) {aoData[element.name] = new Array(); };
			if (element.checked) {aoData[element.name].push(element.value);}
			break;
		case "checkbox" : 
			if (!object[element.name]) {aoData[element.name] = new Array(); };
			if (element.checked) {aoData[element.name].push(element.value);}
			break;
		case "select-one" : 
			var value = '', opt, index = element.selectedIndex;
			if (index >= 0) {
				opt = element.options[index];
				value = opt.value;
				if (!value && !('value' in opt)) value = opt.text;
			}
			aoData[element.name] = value;
			break;
		case "select-multiple" :
			if (!object[element.name]) {aoData[element.name] = new Array(); };
			for (var j = 0; j < element.options.length; j++) {
				var opt = element.options[j];
				if (opt.selected) {
					var optValue = opt.value;
					if (!optValue && !('value' in opt)) optValue = opt.text;
					aoData[element.name].push(optValue);
				}
		    }
		    break;
		default : 
			if (ignoreButton) {
				if (element.type!="submit" && element.type!="button" && element.type!="reset") {
					aoData[element.name] = element.value;
				}
			} else {
				aoData[element.name] = element.value;
			}
			break;
		}
		
	}
	return aoData;
}
/***
function dataTableFormToBean(form, ignoreButton, aoData){
	if( ignoreButton==null || ignoreButton==true) {
		ignoreButton = true;
	} else {
		ignoreButton = false;
	}
	
	//var olist = new Array();
	var object = {};
	var elements = form.elements;
	for (var i = 0; i < elements.length; i++ ) {
		var data = new Object();
		var element = elements[i];
		
		data.name = element.name;
		
		switch (element.type) {
		case "radio" : 
			if (!object[element.name]) {data.value = new Array(); };
			if (element.checked) {data.value.push(element.value);}
			break;
		case "checkbox" : 
			if (!object[element.name]) {data.value = new Array(); };
			if (element.checked) {data.value.push(element.value);}
			break;
		case "select-one" : 
			var value = '', opt, index = element.selectedIndex;
			if (index >= 0) {
				opt = element.options[index];
				value = opt.value;
				if (!value && !('value' in opt)) value = opt.text;
			}
			data.value = value;
			break;
		case "select-multiple" :
			if (!object[element.name]) {data.value = new Array(); };
			for (var j = 0; j < element.options.length; j++) {
				var opt = element.options[j];
				if (opt.selected) {
					var optValue = opt.value;
					if (!optValue && !('value' in opt)) optValue = opt.text;
					data.value.push(optValue);
				}
		    }
		    break;
		default : 
			if (ignoreButton) {
				if (element.type!="submit" && element.type!="button" && element.type!="reset") {
					data.value = element.value;
				}
			} else {
				data.value = element.value;
			}
			break;
		}
		
		aoData.push(data);
	}
	return aoData;
}
***/

/**
 * ��һ��select�����ݴ�����
 * id��	select��id
 * arr�� select����Ҫ��������
 * str:	������ʱʹ�õķָ���
 * 
 * ���ӣ�var rtStr = stringTogether("ciVideoID",["value","innerHTML"],",")
 */
function stringTogether(id,arr,str){
	var oSelect = document.getElementById(id);
	var rtStr = "";
	if(oSelect!=null && oSelect.tagName=="SELECT"){
		for(var i=0;i<oSelect.length;i++){
			for(var j=0;j<arr.length;j++){
				var s = ""+oSelect.options[i][arr[j]];
				if(s=="undefined")
					s = "";
				rtStr += s + str;
			}
		}
	}else{
		alert("������idΪ'"+id+"'��select����");
		return "";
	}
	return rtStr;
}
/*
 * 
 */
function joinAttr(arr, p, sep){
	var tempArr = [];
	for( var i=0; i<arr.length; i++ ){
		var obj = arr[i];
		tempArr.push(obj[p]);
	}
	if( sep==null ){
		sep = ",";
	}
	return tempArr.join(sep)
}
/**
 * ��checkFun()��������true��ʱ��,��ִ��doFun����,��Ϊfalse,��100ms����,��ѭ��ֱ���ɹ�,
 */
function whenTrueDo( checkFun, doFun, interval ){
	if( interval==null ){
		interval = 100;
	}
	if(checkFun()){
		doFun();
	}else{
		window.setTimeout(function(){
			whenTrueDo( checkFun, doFun );
		},interval);
	}
}

/*
* ���tbody���������tr
* pTbody: ��һ��getElementById�Ժ�Ķ���
*/
function clearTbody(pTbody){
	if( pTbody != null ){
		for( var i=pTbody.childNodes.length-1; i>=0; i-- ){
			pTbody.removeChild(pTbody.childNodes[i]);
		}
	}
}

/*
* ��select����title
* ����pObj������һ��select���󣬲�Ȼ��Ҫ��2������
*�����ӣ�showTitle(this)����showTitle(this, this.value)
*/
function showTitle(pObj, pContent){
	var div = null;
	if(pObj.titleDiv!=null){
		var text = "";
		if(pContent!=null){
			text = pContent;
		}else{
			try{
				text = pObj.options[pObj.selectedIndex].innerHTML
			}catch(e){
				text = "�޷���ȡ����";
			}
		}
		if(text==""){
			text = "&nbsp;"
		}
		div = pObj.titleDiv;
		div.style.display = "";
		document.getElementById("contentTd_"+div.id).innerHTML = text;
		var table = document.getElementById("contentTable_"+div.id);
		var iframe = document.getElementById("contentIframe_"+div.id);
		iframe.style.height = table.offsetHeight;
	}else{
		var text = "";
		if(pContent!=null){
			text = pContent;
		}else{
			try{
				text = pObj.options[pObj.selectedIndex].innerHTML
			}catch(e){
				text = "�޷���ȡ����";
			}
		}
		if(text==""){
			text = "&nbsp;"
		}
		var id = "title_"+new Date().getTime();
		var html = ""
		+ "<div id='"+id+"' style='margin:0px; position:absolute; background-color:#FFFFE1; z-index:10000000;' onmouseover='this.style.display=\"none\"'>"
			+ "<table id='contentTable_"+id+"' border='0' cellpadding='0' cellspacing='0' style=' margin:0px;border:1px solid black;padding:3 10 3 10;'>"
				+ "<tr>"
					+ "<td id='contentTd_"+id+"' style='white-space:nowrap;'>"
						+ text 
					+ "</td>"
				+ "</tr>"
			+ "</table>"
			+ "<iframe id='contentIframe_"+id+"' src=\"javascript:false;\" style=\"position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:20px; z-index:-1; filter='alpha(style=0,opacity=0)';\"></iframe>"
		+ "</div>"
		;
		document.body.insertAdjacentHTML("beforeEnd",html);
		div = document.getElementById(id);
		var table = document.getElementById("contentTable_"+id);
		var iframe = document.getElementById("contentIframe_"+id);
		iframe.style.height = table.offsetHeight;
		pObj.titleDiv = div;
	}
	var x = window.event.clientX + document.body.scrollLeft;
	var y = window.event.clientY + document.body.scrollTop;
	
	if(  x + div.offsetWidth > document.body.scrollLeft + document.body.clientWidth - 30 ){
		x = document.body.scrollLeft + document.body.clientWidth - 30 - div.offsetWidth;
	}
	if(  y + div.offsetHeight > document.body.scrollTop + document.body.clientHeight - 30 ){
		y = document.body.scrollTop + document.body.clientHeight - 30 - div.offsetHeight;
	}
	
	div.style.left = x + 10;
	div.style.top = y + 10;
}

/*
* ��showTitle��������
*/
function clearTitle(pObj){
	var div = pObj.titleDiv;
	if(div!=null){
		div.style.display = "none";
	}
}

/**
 * ���һ���������������
 */
function removeAllProperties(obj){
	for(var p in obj){
		delete(obj[p]);
	}
}

/**
 * ����һ�������������һ��
 */
function cloneObject(obj){
	var newObj = {};
	for(var p in obj){
		newObj[p] = obj[p];
	}
	return newObj;
}

/**
 * ��һ�����������copy����һ������
 */
function copyTo( fromObj,toObj ){
	for(var p in fromObj){
		toObj[p] = fromObj[p];
	}
}

/**
 * ��checkbox�򹴻���ȥ����
 */
function setCheckboxChecked( cbxName, checked ){
	var arr = document.getElementsByName(cbxName);
	if( arr==null ){
		return ;
	}
	for( var i=0; i<arr.length; i++ ){
		var cbx = arr[i];
		cbx.checked = checked;
	}
}

/**
 * ����һ��ͬ����checkbox�еĴ��˹���checkbox
 */
function getCheckedBox( pName ){
	var chkboxArr = document.getElementsByName(pName);
	var result = [];
	if( chkboxArr!=null ){
		for( var i=0; i<chkboxArr.length; i++ ){
			var chkbox = chkboxArr[i];
			if( chkbox.checked==true ){
				result.push(chkbox);
			}
		}
	}
	return result;
}
/**
 * ����һ��radio�д��˹����Ǹ�radio��value
 */
function getRadioValue( radioName, valueColumn ){
	if( valueColumn==null ){
		valueColumn = "value";
	}
	var radios = document.getElementsByName(radioName);
	if( radios==null ){
		return null;
	}
	for( var i=0; i<radios.length; i++ ){
		if( radios[i].checked==true ){
			return radios[i][valueColumn];
		}
	}
	return null;
}
/**
 * ����һ��checkbox�д��˹����Ǹ�checkbox��value
 */
function getCheckboxValue( checkboxName, valueColumn ){
	if( checkboxName==null ){
		checkboxName = "value";
	}
	var checkboxs = document.getElementsByName(checkboxName);
	if( checkboxs==null ){
		return null;
	}
	var arr = [];
	for( var i=0; i<checkboxs.length; i++ ){
		if( checkboxs[i].checked==true ){
			arr.push( checkboxs[i][valueColumn] );
		}
	}
	return arr.join("");
}
/**
 * ��ȡһ������ľ���λ��
 * @return ����pos = { left:0, top:0 };
 */
function getAbsolutePosition(obj){
	var pos = { left:0, top:0 };
	var op = obj;
	while( op.offsetParent!=null ){
		pos.left += op.offsetLeft;
		pos.top += op.offsetTop;
		op = op.offsetParent;
	}
	return pos;
}
 
/**
 * ��ȡһ��iframe�ڸ������е��±�
 * @return ����-1��ʾû���ҵ����������
 */
function getFrameIndexInParentWindow(win){
	var pWin = win.parent;
	var frameIndex = -1;
	var frames = pWin.frames;
	for( var i=0; i<frames.length; i++ ){
		if( frames[i]==win ){
			frameIndex = i;
		}
	}
	return frameIndex;
}

/**
 * ����һ��������������Դ��������ַ���
 */
function getAllProperties(obj){
	var arr = [];
	for( var p in obj ){
		arr.push( p+" : "+obj[p] );
	}
	return arr.join("\n");
}

/**
 * ����һ��������������Լ����������ַ���
 */
function getAllKeys(obj,connStr){
	var arr = [];
	for( var p in obj ){
		arr.push( p );
	}
	return arr.join( (connStr==null) ? "," : connStr );
}

/**
 * ��ȡ�쳣�������Ϣ
 */
function getErrorMessage(e){
	if( e==null ){
		return "null";
	} else if( e instanceof Error ) {
		return e.message;
	} else if( typeof(e)=="string" ) {
		return e;
	} else {
		return getAllProperties(e);
	}
}

/**
 * ��ӡ������Ϣ
 */
function alertError(e){
	alert(getErrorMessage(e));
}

/**
 * 
 */
function getReturnMessage(str){
	if( str.startsWith("fail:") ){
		return str.substring( "fail:".length );
	} else {
		return str;
	}
}

/**
 * 
 */
function alertReturnMessage(str){
	alert( getReturnMessage(str) );
}
/**
 * ѡ���ı��е�һ��С����"123456",ֻ������ѡ��"2345"
 * ʹ�� ��selectRange("id", 1, 5)
 */
function selectRange(id, start, end)
{
	var text = document.getElementById(id);
	var range = text.createTextRange();
    range.moveStart("character", start);
    range.moveEnd("character", end - text.value.length);
    range.select();
    text.focus();
}
/**
 * ��ȡ������ڵ�λ��,������ʼ�ͽ�����λ��
 * @param textBox: һ��DOM����
 */
function getTextCursorPosition(textBox) {
	var start = 0;
	var end = 0;
	var range = document.selection.createRange();
	var range_all=document.body.createTextRange();
	range_all.moveToElementText(textBox);
	for(start=0; range_all.compareEndPoints("StartToStart",range)<0; start++){
		range_all.moveStart('character',1);
	}
	for(var i=0;i<=start;i++) {
		if(textBox.value.charAt(i)=='\n') {
			start++;
		}
	}
	var range_all=document.body.createTextRange();
	range_all.moveToElementText(textBox);
	for(end=0;range_all.compareEndPoints('StartToEnd',range)<0;end++){
		range_all.moveStart('character',1);
	}
	for(var i=0;i<=end;i++) {
		if(textBox.value.charAt(i)=='\n'){
			end++;
		}
	}
	return {"start":start, "end":end}
}

/**
 * ����һ����͸����div����ʾ���ȴ���Ϣ
 */
function showWaitBackground(title){
 	var div = document.getElementById("waitBackground_div");
 	var titlediv = document.getElementById("waitBackground_title_div");
 	if( div==null ){
 		div = document.createElement("div");
 		div.id = "waitBackground_div";
 		div.style.cssText = "position:absolute; z-index:1000000; top:0; left:0; cursor:wait; background-color:black; width:100%; height:100%; filter:alpha(opacity=30);";
 		document.body.appendChild(div);
 		
 		titlediv = document.createElement("div");
 		titlediv.id = "waitBackground_title_div";
 		titlediv.style.cssText = "position:absolute; z-index:1000001; top:0; left:0; width:1px; height:200px; text-align:center;";
 		titlediv.innerHTML = ""
 		+ "<table border='0'>"
 			+ "<tr>"
 				+ "<td align='center'>"
 					+ "<img src='/afResource/i_mages/loading.gif' />"
 				+ "</td>"
 			+ "</tr>"
 			+ "<tr>"
 				+ "<td align='center' nowrap style='font-size:20px; color:green; font-weight:bold;'>"
 					+ title
 				+ "</td>"
 			+ "</tr>"
 		+ "</table>"
 		;
 		document.body.appendChild(titlediv);
 	}
 	div.style.width = div.offsetWidth + 40;
 	div.style.display = "";
 	titlediv.style.display = "";
 	
 	var top = document.body.scrollTop + document.body.scrollHeight/2 - titlediv.offsetHeight/2;
 	var left = document.body.scrollLeft + document.body.scrollWidth/2 - titlediv.offsetWidth/2;
 	titlediv.style.top = top;
 	titlediv.style.left = left;
}

var CaptureUtil = new function(){
	this._obj;
	this.setCapture = function(obj){
		this._obj = obj;
		this._obj.setCapture();
	}
	this.releaseCapture = function(){
		if( this._obj!=null ){
			this._obj.releaseCapture();
		}
		this._obj = null;
	}
}

/**
 * ɾ����͸����div
 */
function hideWaitBackground(){
	var div = document.getElementById("waitBackground_div");
 	var titlediv = document.getElementById("waitBackground_title_div");
 	if( div!=null ){
 		document.body.removeChild(div);
 	}
 	if( titlediv!=null ){
 		document.body.removeChild(titlediv);
 	}
}

/**
 * ��ȡҳ�����ڵ�Ӧ����
 */
function getAppName(pWid){
	var win = (pWid!=null) ? pWid : window;
	var path = win.location.pathname;
	if( path.indexOf("/")==-1 ){
		return path;
	}else{
		if( path.startsWith("/") ){
			var idx = path.indexOf("/",1);
			return path.substring(1,9);
		}else{
			var idx = path.indexOf("/");
			return path.substring(0,9);
		}
	}
}

/**
 * ��ȡҳ�����ڵ�������
 */
function getContextPath(pWid){
	return "/"+getAppName(pWid);
}

/**
 * ��һ���Ի�����ʾָ���İ�ť�����ݣ�����󷵻ذ�ť��value
 * ʹ�����ӣ�new CommonDialog(["��","��","����"],"ȷ��Ҫ���浽���ݿ���?").show()
 */
function CommonDialog( pButtonArr, pMsg, pFeature ){
	var buttonArr = pButtonArr;
	var msg = pMsg;
	var feature = (pFeature!=null) ? pFeature : "dialogWidth:260px;dialogHeight:141px;center:yes;status:no;resizable:yes;scroll:no;";
	//
	this.show = function(){
  		var path = getContextPath()+"/public/dialog.jsp";
  		var param = {};
  		param.buttonArr = buttonArr;
  		param.msg = msg;
  	    return window.showModalDialog(path,param,feature);
	}
}

/**
 * ���ַ���д��һ���ļ�����
 */
function writeToFile(str,path,param){
	var fso = new ActiveXObject("Scripting.FileSystemObject");
	path = (path==null) ? "g:\\testfile.txt" : path;
	param = (param==null) ? true : param;
	var tf = fso.CreateTextFile(path, param);
	tf.WriteLine(str) ;
	tf.Close();
}

/**
 * ����IE�����xml�ǲ����д�
 */
function checkXml(strXml){
	window.showModalDialog("/afManage/test/xml.jsp?ts="+new Date().getTime(), strXml);
}
/**
 * �ж�������ǲ�������, �����, ���˼�, Del��
 */
function isPressNumberKey(keyCode){
	if( (event.keyCode>=48 && event.keyCode<=57) || 	// ���ּ�
		(event.keyCode>=96 && event.keyCode<=105) || 	// С�������ּ�
		(event.keyCode>=35 && event.keyCode<=40) ||		// end, home ���� 
		event.keyCode==8 || 							// ���˼�
		event.keyCode==46 ) {							// Del��
		return true;
	} else {
		return false;
	}
}
/**
 * ���õ������ڵĴ�С�������Ⱥ͸߶ȣ���������ʾ���ڡ���qxb��
 */
function setDialogSize( width, height, isCenter){
	//���ڴ�С����λpx
	var setW=width;
	var setH=height;
	//���ô��ڴ�С
	if( window.dialogWidth != setW+"px" ){
		window.dialogWidth = setW+"px";
	}
	if( window.dialogHeight != setH+"px" ){
		window.dialogHeight = setH+"px";
	}
	//���´����õ������ھ���
	if( isCenter==true ){
		var w = 1024;
	    var h = 768;
	    if (document.all || document.layers)
	    {
			w = screen.availWidth;
			h = screen.availHeight;
	    }
	    var leftPos = (w/2-setW/2);
	    var topPos = (h/2.3-setH/2.3);
		window.dialogLeft = leftPos+"px";
		window.dialogTop = topPos+"px";
	}
}


//���ò�ͬIE�汾�´��ڵĸ߶�
function resetDialogHeight(){  
	//if( document.body.scrollWidth > (window.screen.availWidth-100) ){     
     //   window.dialogWidth = (window.screen.availWidth-100).toString() + "px";     
    //}else{     
     //   window.dialogWidth = (document.body.scrollWidth + 50).toString() + "px";     
    //}         
        
    if( document.body.scrollHeight > (window.screen.availHeight-70) ){     
        window.dialogHeight = (window.screen.availHeight - 50).toString() + "px";     
    }else{     
        window.dialogHeight = (document.body.scrollHeight + 40).toString() + "px";     
    }         

    window.dialogLeft = ((window.screen.availWidth - document.body.clientWidth) / 2).toString() + "px";     
    window.dialogTop = ((window.screen.availHeight - document.body.clientHeight) / 2).toString() + "px"; 
}

//************lc************************

    function resetDialogHightSize(){          
    if( document.body.scrollHeight > (window.screen.availHeight-70) ){  
        window.dialogHeight = (window.screen.availHeight - 50).toString() + "px";     
    }else{     
        window.dialogHeight = (document.body.scrollHeight + 30).toString() + "px";      
    }  
 
      var vision=getIEVision();
    
    if(vision.indexOf("7")>-1){
          window.dialogHeight = (document.body.scrollHeight).toString() + "px";    
    }
    window.dialogLeft = ((window.screen.availWidth - document.body.clientWidth) / 2).toString() + "px";     
    window.dialogTop = ((window.screen.availHeight - document.body.clientHeight) / 2).toString() + "px"; 
}

function appInfo(){
     var browserVision="";
    var browser = {
            msie: false, firefox: false, opera: false, safari: false, 
            chrome: false, netscape: false, appname: 'unknown', version: 0
        },
        userAgent = window.navigator.userAgent.toLowerCase();
    if ( /(msie|firefox|opera|chrome|netscape)\D+(\d[\d.]*)/.test( userAgent ) ){
        browser[RegExp.$1] = true;
        browser.appname = RegExp.$1;
        browser.version = RegExp.$2;
    } else if ( /version\D+(\d[\d.]*).*safari/.test( userAgent ) ){ // safari
        browser.safari = true;
        browser.appname = 'safari';
        browser.version = RegExp.$2;
    }
    return browser;
}


function getIEVision(){
  var vision="";
var myos = appInfo();
if (myos.msie){
    vision=myos.version;
} else {
    vision=myos.appname+myos.version;
}

 return vision;
}

//************lc*********************


/**
 * �Զ����¼�������
 */
var isCancelBubble = false;	// �Ƿ��¼�ð��

function onclickEventFlowHandler(){
	if( isCancelBubble==true ){		// ��ֹ�¼�ð��
		isCancelBubble = false		// �ָ�ΪĬ��ֵ
		return ;
	}
	if( window.parent==null ){
		return ;
	}
	window.parent.document.body.click();
}
/**
 * ����־�õĹ�����
 */
function LoggerUtilClass(pContainer){
	var self = this;
	this.enable = true;				// �����ʹ����־������Ļ�,�ͽ����ֵ��Ϊfalse������
	this.id = ("var"+new Date().getTime()+Math.random()).replace(".","");
	this.dom = null;
	this.container = pContainer;	// һ��window����,
	this.width = 500;
	this.height = 400;
	//
	this._hideBtn_ = null;
	this._smallBtn_ = null;
	this._context_ = null;
	this._contentTr_ = null;
	this._titleTd_ = null;
	//
	this._init_ = function(){
		if( this.enable==false ){
			return;
		}
		this.dom = this.container.document.createElement("<div>");
		this.dom.style.cssText = "position:absolute; left:10; top:10; width:"+this.width+"px; height:"+this.height+"px; z-index:99999999;";
		var html = [];
		html.push(	"<table width='100%' height='100%' border='0' cellpadding='0' cellspacing='0' style='border:2px solid #555555;'>" );
		html.push( 		"<tr id='titleTr_"+this.id+"'>" );
		html.push( 			"<td id='titleTd_"+this.id+"' align='right' style='height:20px; background-color:#956A33; text-align:right;'>" );
		html.push( 				"<span style='float:left; color:white;'>&nbsp;��־����</span>" );
		html.push( 				"<a id='small_"+this.id+"' href='#' style=' color:white; font-family:webdings;'>2</a>&nbsp;" );
		html.push( 				"<a id='hide_"+this.id+"' href='#' style=' color:white; font-family:webdings;'>r</a>&nbsp;" );
		html.push( 			"</td>" );
		html.push( 		"</tr>" );
		html.push( 		"<tr id='contentTr_"+this.id+"'>" );
		html.push( 			"<td><textarea id='logDiv_"+this.id+"' style='width:100%; height:100%; border:0px; background-color:#aaaaaa;'></textarea></td>" );
		html.push( 		"</tr>" );
		html.push(	"</table>" );
		html.push(	"<iframe src='' style='position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1;'></iframe>" );
		this.dom.innerHTML = html.join("");
		this.container.document.body.appendChild(this.dom);
		//
		this._smallBtn_ = this.container.document.getElementById("small_"+this.id);
		this._hideBtn_ = this.container.document.getElementById("hide_"+this.id);
		this._titleTd_ = this.container.document.getElementById("titleTd_"+this.id);
		this._contentTr_ = this.container.document.getElementById("contentTr_"+this.id);
		this._context_ = this.container.document.getElementById("logDiv_"+this.id);
		//
		this._smallBtn_.attachEvent( "onclick", function(){self._small_();} );
		this._hideBtn_.attachEvent( "onclick", function(){self._hide_();} );
		//
		this._setMoveFn_();
	}
	//
	this.info = function(str){
		if( this.enable==false ){
			return;
		}
		this.show();
		this._context_.value += "["+dateToString(new Date())+"] "+str+"\n";
	}
	//
	this._small_ = function(){
		if( this._contentTr_.style.display=="" ){
			this._contentTr_.style.display = "none";
			this.dom.style.height = "1px";
		} else {
			this._contentTr_.style.display = "";
			this.dom.style.height = this.height;
		}
	}
	//
	this._hide_ = function(){
		this.dom.style.display = "none";
	}
	//
	this.show = function(){
		if( this.enable==false ){
			return;
		}
		this.dom.style.display = "";
	}
	//
	this._setMoveFn_ = function(){
		this._titleTd_.attachEvent("onmousedown",function () {
			self._moveMouseDown_(self._titleTd_);
		});
		this._titleTd_.attachEvent("onmouseup",function () {
			self._moveMouseUp_(self._titleTd_);
		});
		this._titleTd_.attachEvent("onmousemove",function(){
			self._moveMouseMove_();
		});
	}
	//
	this.moving;	
	this.ex;
	this.ey;
	this.l;
	this.t;
	//
	this._moveMouseDown_ = function(pObj){
		if( this.container.event.button==1 ) {
			this.moving = true;
			pObj.setCapture();
			this.ex = this.container.event.screenX;
			this.ey = this.container.event.screenY;
			this.l = this.dom.offsetLeft;
			this.t = this.dom.offsetTop;
		}
	}
	//
	this._moveMouseUp_ = function(pObj){
		if( this.container.event.button==1 && this.moving==true ) {
			this.moving=false;
			pObj.releaseCapture();
		}
	}
	//
	this._moveMouseMove_ = function(){
		if( this.moving ) {
			var dx = this.container.event.screenX - this.ex;
			var dy = this.container.event.screenY - this.ey;
			if( this.t + dy < 0 ){
				this.dom.style.top = 0
			} else {
				this.dom.style.top = this.t + dy
			}
			this.dom.style.left = this.l + dx
		}
	}
	//
	this.dispose = function(){
		if( this.dom!=null ){
			this.dom.parentElement.removeChild(this.dom);
		}
	}
	//
	this._init_();
}

















