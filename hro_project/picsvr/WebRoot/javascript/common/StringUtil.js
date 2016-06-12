	var S = {};
	
	//ʵ��String��trim()����,�÷���str = "hello ".trim();
	String.prototype.trim = function(){
		return this.replace(/(^\s*)|(\s*$)/g,"");
	}
	
	//ʵ��String��endsWith()����,�÷���str = "hello ".endsWith(".xls");
	String.prototype.endsWith = function(s){
		if(s==null){
			return false;
		}
		var len = s.length;
		if(len>this.length){
			return false;
		}
		var sub = this.substring(this.length-len,this.length);
		return sub==s;
	}
	
	//ʵ��String��startsWith()����,�÷���str = "hello ".startsWith(".xls");
	String.prototype.startsWith = function(s){
		if(s==null){
			return false;
		}
		var len = s.length;
		if(len>this.length){
			return false;
		}
		var sub = this.substring(0,len);
		return sub==s;
	}
	
	// �滻���е��ַ���
	/***
	String.prototype.replaceAll = function (oldStr, newStr) {
		return this.replace(new RegExp(oldStr, "g"), newStr);
	}
	***/
	
	// ��� str==null ���� s, ���򷵻�str.trim()
	S.trim = function(str, s){
		if( str==null ){
			if( s==null ){
				return "";
			} else {
				return s;
			}
		} else {
			return str.trim();
		}
	}
	
	// ��� str==null ���� str=="" ���� s, ���򷵻�str.trim()
	S.trim2 = function(str, s){
		if( str==null || str.trim()=="" ){
			if( s==null ){
				return "";
			} else {
				return s;
			}
		} else {
			return str.trim();
		}
	}
	
	// 
	S.split = function( str, sep ){
		if( str==null || str=="" ){
			return [];
		} else {
			return str.split(sep);
		}
	}
	
	/**
	 * ������java��StringBuffer��
	 */
	function StringBuffer() {
		var arr=new Array();
		/**
		 * ����Ԫ��
		 */
		this.append=function(str) {
			arr.push(str);
		}
		/**
		 * ������Ԫ����������
		 */
		this.toString =function() {
			return arr.join("");
		}
		/**
		 * s�ǽ�����Ԫ�����������ķָ���
		 */
		this.join =function(s) {
			return arr.join(s);
		}
	}
	
	/**
	 * ���һ���ı��ڵ��������ı�
	 * �Է��ı��ڵ��벻Ҫʹ���������
	 * ͨ������һ��ajax���ص�����Ľڵ�,��Ϊ������ʹ��innerText��InnerHTML
	 */
	function getInnerText(obj) {
		if(obj.firstChild!=null) {
			return obj.firstChild.nodeValue;
		} else {
			return "";
		}
	}
	
	/**
	 *
	 */
	function showLimitedString(str, len) {
		if(str.length > len) {
			return str.substring(0, len-1)+"...";
		} else {
			return str;
		}
	}
	function isValidate(value) 
	{ 
	   var vkeyWords=/^[^`~!@#$%^&*()+=|\\\][\]\{\}:;'\,.<>?]{1}[^`~!@$%^&+=\\\][\{\}:;'\,<>?]{0,}$/; 
	   return vkeyWords.test(value); 
	}

	/**
	 *
	 */
	function isEmpty(str) {
		if( str==null ){
			return true;
		}
		var er = /^\s*$/;
		return er.test(str);
	}
	
	/**
	 *
	 */
	function isNumber(str) {
		var er = /^\d+$/;
		return er.test(str);
	}
	
	/**
	 *
	 */
	function isInt(str) {
		if(str.length>=10){
			return false;
		}
		var er = /^\d+$/;
		return er.test(str);
	}
	function isPort(obj)
	{
	    if(!isInt(obj))
	        return false;
	    if(obj< 65536)
	        return true;
	    return false; 
	}
	
	/**
	 * �ж�һ�����ǲ���С��,����Ҳ����С����һ��
	 */
	function isFloat(str) {
    	if(str.length>1 && str.charAt(0)=="-") {
    		str = str.substring(1, str.length);
    	}
    	var arr = str.split(".")
    	if(arr.length>2) {
    		return false;
    	}
    	for(var i=0;i<arr.length;i++) {
    		if(!isInt(arr[i])) {
    			return false;
    		}
    	}
    	return true;
    }
	
	/**
	 * �ж�һ���ַ����ǲ���IP��ַ�ĸ�ʽ
	 */
	function isIP(str) {
		var arr = str.split(".");
		if(arr.length!=4) {
			return false;
		}
		for(var i=0;i<arr.length;i++) {
			if(isInt(arr[i])==false || arr[i].length>3) {
				return false;
			}
			var num = parseInt(arr[i]);
			if(num<0||num>255) {
				return false;
			}
		}
		return true;
	}
	/**
	 *	�ж�һ���ַ����Ƿ�Ϊ��ȷ��MAC��ʽ
	 */
	
	function isMAC(str){
 		var reg = /^[0-9,a-f,A-F]{2}[-][0-9,a-f,A-F]{2}[-][0-9,a-f,A-F]{2}[-][0-9,a-f,A-F]{2}[-][0-9,a-f,A-F]{2}[-][0-9,a-f,A-F]{2}$/;
 		return reg.test(str);
	}
	
	/**
	 * 
	 */
	function isEmail(str){
		if(str.length>0) {
			var pattern = /^\w+@\w+(\.\w+)*$/;
			return pattern.test(str);
		}
		return false;
	}
	//�ж��Ƿ���ϵ绰�����ʽ	
	function isTel(str){ 
		var reg=/^([0-9]|[\-])+$/g ; 
		if(str.length<7 || str.length>18){ 
			return false; 
		} 
		else{ 
			return reg.test(str); 
		} 
	}
	//20λ����
    function isNode(str){ 
		var er = /^\+?[0-9][0-9]*$/;//������ 
		if(str.length==20){
			return er.test(str); 
		}
		return false;
	}
    /**
     * �ж�һ���ַ����ǲ��� "2000-01-01 01:01:01" �����ĸ�ʽ
     */
    function isDateTimeString(str){
		var pattern = /^(\d{4})-(\d{2})-(\d{2}) (\d{2}):(\d{2}):(\d{2})$/;
		if( pattern.test(str)==false ){
			return false;
		}
		if( RegExp.$2 > 12 ){
			return false;
		}
		if( RegExp.$3 > 31 ){
			return false;
		}
		if( RegExp.$4 > 23 ){
			return false;
		}
		if( RegExp.$5 > 59 ){
			return false;
		}
		if( RegExp.$6 > 59 ){
			return false;
		}
		return true;
	}
	
	/**
     * �ж�һ���ַ����ǲ��� "2000-01-01" �����ĸ�ʽ
     */
    function isDateString(str){
		var pattern = /^(\d{4})-(\d{2})-(\d{2})$/;
		if( pattern.test(str)==false ){
			return false;
		}
		if( RegExp.$2 > 12 ){
			return false;
		}
		if( RegExp.$3 > 31 ){
			return false;
		}
		return true;
	}
	
	/**
     * �ж�һ���ַ����ǲ��� "01:01:01" �����ĸ�ʽ
     */
    function isTimeString(str){
		var pattern = /^(\d{2}):(\d{2}):(\d{2})$/;
		if( pattern.test(str)==false ){
			return false;
		}
		if( RegExp.$1 > 23 ){
			return false;
		}
		if( RegExp.$2 > 59 ){
			return false;
		}
		if( RegExp.$3 > 59 ){
			return false;
		}
		return true;
	}
	
	/**
	*�Ƿ������ֻ���Ӣ����ĸ
	*/
	function isDigitOrLetter( str ) {
		if(!str) return false;
		return str.match(/^[A-Za-z0-9]*$/)!=null;
	}
	
	/**
	 * ����url����
	 */
	function doURLEncode( str ){
		if( str==null ){
			return null;
		}
		return encodeURIComponent(str).replaceAll("-", "%2D").replaceAll("%", "-");
	}
	
	/**
	 * ����url����
	 */
	function doURLDecode( str ){
		if( str==null ){
			return null;
		}
		return decodeURIComponent( str.replaceAll("-", "%") );
	}
    
	/**
	 * ȡ��һ���ַ������Ӵ�
	 */
	function substring(str, start, end)
	{
		if( str==null ){
			return "";
		}
		if( start>str.length ){
			return "";
		}
		if( end>str.length ){
			return str.substring(start);
		}else{
			return str.substring(start, end);
		}
	}
	
	/**
	 * ˵��:
	 * 		�� numToString( 123456789, 4 )
	 * 		�� 125369 ת��Ϊ 5369
	 * 
	 * 		�� numToString( 89, 4 )
	 * 		�� 89 ת��Ϊ 0089
	 * 
	 * 		������ұߵ�4λ,�������0����.
	 * 
	 * @param num
	 * 		Ҫ����ת������
	 * @param len 
	 * 		Ҫ���������λ��
	 * @return
	 */
	function numToString( num, len ){
		var s = ""+num;
		var slen = s.length;
		if( slen>len ){
			return s.substring(s.length-len);
		}
		if( slen<len ){
			var count = len-slen;
			var arr = [];
			for( var i=0; i<count; i++ ){
				arr.push("0");
			}
			return arr.join("")+s;
		}
		return s;
	}
	
	/**
	 * ���ַ������ݸ�ʽ,��"yyyy-MM-dd hh:mm:ss.SSS"ת��Ϊ����
	 */
	function strToDate(str, format){		//
		format = (format==null) ? "yyyy-MM-dd hh:mm:ss.SSS" : format;
		//
		var y_idx = format.indexOf("yyyy");
		var m_idx = format.indexOf("MM");
		var d_idx = format.indexOf("dd");
		var h_idx = format.indexOf("hh");
		var mi_idx = format.indexOf("mm");
		var s_idx = format.indexOf("ss");
		var ms_idx = format.indexOf("SSS");
		//
		var y=0;
		var m=0;
		var d=0;
		var h=0;
		var mi=0
		var s=0;
		var ms=0;
		//
		if( y_idx==-1 ){	//formatû��yyyy���꣩����ô������hh��Сʱ��
			if( h_idx==-1 ){
				throw "ת��ʧ��str["+str+"],format["+format+"]";
			}
			var date = new Date();
			y = date.getYear();
			m = date.getMonth()+1;
			d = date.getDate(); 
			h = new Number(str.substring(h_idx,h_idx+2));
			if( mi_idx==-1 ){
				return new Date(y,m,d,h);
			}
			mi = new Number(str.substring(mi_idx,mi_idx+2));
			if( s_idx==-1 ){
				return new Date(y,m,d,h,mi);
			}
			s = new Number(str.substring(s_idx,s_idx+2));
			if( ms_idx==-1 ){
				return new Date(y,m,d,h,mi,s);
			}
			ms = new Number(str.substring(ms_idx,ms_idx+3));
			return new Date(y,m,d,h,mi,s,ms);
		}else{				//format��yyyy���꣩
			y = new Number(str.substring(y_idx,y_idx+4));
			if( m_idx==-1 ){
				return new Date(y);
			}
			m = new Number(str.substring(m_idx,m_idx+2))-1;
			if( d_idx==-1 ){
				return new Date(y,m);
			}
			d = new Number(str.substring(d_idx,d_idx+2));
			if( h_idx==-1 ){
				return new Date(y,m,d);
			}
			h = new Number(str.substring(h_idx,h_idx+2));
			if( mi_idx==-1 ){
				return new Date(y,m,d,h);
			}
			mi = new Number(str.substring(mi_idx,mi_idx+2));
			if( s_idx==-1 ){
				return new Date(y,m,d,h,mi);
			}
			s = new Number(str.substring(s_idx,s_idx+2));
			if( ms_idx==-1 ){
				return new Date(y,m,d,h,mi,s);
			}
			ms = new Number(str.substring(ms_idx,ms_idx+3));
			return new Date(y,m,d,h,mi,s,ms);
		}
	}
	
	/**
	 * ��һDate���͵Ķ���ת��Ϊһ�� "yyyy-MM-dd hh:mm:ss.SSS" �������ַ���
	 */
	function dateToString( date, format ){
		format = (format==null) ? "yyyy-MM-dd hh:mm:ss" : format;
		//
		var y = date.getYear();
		y = (y<100) ? 1900+y : y;		//��Ϊ1970�ᱻ��ʾ��70
		var m = date.getMonth()+1;
		var d = date.getDate();
		var h = date.getHours();
		var mi = date.getMinutes();
		var s = date.getSeconds();
		var ms = date.getMilliseconds();
		//
		format = format.replace("yyyy",numToString(y,4));
		format = format.replace("MM",numToString(m,2));
		format = format.replace("dd",numToString(d,2));
		format = format.replace("hh",numToString(h,2));
		format = format.replace("mm",numToString(mi,2));
		format = format.replace("ss",numToString(s,2));
		format = format.replace("SSS",numToString(ms,3));
		return format;
	}
	/**
	* ��yyyyMMddhhmmssתΪyyyy-MM-dd hh:mm:ss
	*/
	function StrToTime(timeStr){
		if(timeStr==null){
			return;
		}
		var newTime = timeStr.substring(0, 4)+"-"+timeStr.substring(4, 6)+"-"+
						timeStr.substring(6, 8)+" "+timeStr.substring(8, 10)+":"+
						timeStr.substring(10, 12)+":"+timeStr.substring(12, 14);
		return newTime;
	}
	/**
	 * ��������Ƚ���,��֪��û�п�һ��ķ���
	 */
	function jsonEscape(str){
		var result = [];
		for( var i=0; i<str.length; i++ ){
			var c = str.charAt(i);
			if( c=="\"" ){
				result.push("\\\"");
			} else if( c=="\\" ){
				result.push("\\\\");
			} else if( c=="/" ){
				result.push("\\/");
			} else if( c=="\r" ){
				result.push("\\r");
			} else if( c=="\n" ){
				result.push("\\n");
			} else if( c=="\t" ){
				result.push("\\t");
			} else if( c=="\b" ){
				result.push("\\b");
			} else if( c=="\f" ){
				result.push("\\f");
			} else {
				result.push(c);
			}
		}
		return result.join("");
	}
	/**
	 * ��һ������ת��Ϊһ��json�ַ���
	 */
	function toJSONString(obj){
		if( obj==null ){
			return "null";
		}
		if( typeof(obj)=="boolean" ){
			return obj;
		}
		if( typeof(obj)=="string" ){
			return '"'+jsonEscape(""+obj)+'"';
		}
		if( typeof(obj)=="number" ){
			return ""+obj;
		}
		if( obj instanceof Array ){
			var result = [];
			for( var i=0; i<obj.length; i++ ){
				result.push( toJSONString(obj[i]) );
			}
			return "["+result.join(",")+"]";
		}
		if( typeof(obj)=="object" ){
			var result = [];
			for( var p in obj ){
				result.push('"'+p+'":'+toJSONString(obj[p]));
			}
			return "{"+result.join(",")+"}";
		}
		return "\"^_^�Ǻ�,jsonת��������^_^\"";
	}
	
	/**
	 * ���㿪ʼʱ�䵽����ʱ���Ӧ�ٷֱȵ�ʱ��
	 * @param percent	С�� : percent >0 && percent < 100
	 */
	function getDateFromPercent(beginTime, endTime, percent){
		var dBeginTime = strToDate(beginTime);
		var dEndTime = strToDate(endTime);
		var timeOffset = (dEndTime.getTime()-dBeginTime.getTime()) * percent / 100;
		return new Date( dBeginTime.getTime() + timeOffset );
	}
	
	/*
	* ���sessionʧЧ
	*/
	function checkSession(result){
		if( result!='' && result.indexOf('/security/login?module=')>-1){					    
		    alert("����ʱ��û�в������Ự�ѳ�ʱ�������µ�¼��");
		    top.location.href ="/security/login";
		    return;
		}
	}
	
	/*
	* ���sessionʧЧ
	*/
	function checkSessionInDialog(result){
		if( result!='' && result.indexOf('/security/login?module=')>-1){					    
		    alert("����ʱ��û�в������Ự�ѳ�ʱ�������µ�¼��");
		    top.location.href ="/security/login";
		    window.close();
		    return;
		}
	}
	
	/**************************
	* �ַ���ת����xml��ʽ��
	*  strXml : �ַ���xml
	***************************/
	function FormatToXml(strXml){     
		 var isIE = function(){     
		  	var IE = /msie/i.test(navigator.userAgent);     
		  	return IE;     
		 }     
		 var Exc = function(){     
		 var XmlDoc = null;     
		 if (isIE()){     
		   	XmlDoc = new ActiveXObject("Microsoft.XMLDOM");      
		   	XmlDoc.loadXML(strXml);     
		 }else{          
		   XmlDoc = (new DOMParser()).parseFromString(strXml, "text/xml");     
		 }     
		  return XmlDoc;     
		}     
		return Exc();     
	}   
	
	
	
	/**
	 * �ض��ַ���
	 */
	function getSkipSubstring(str, len) {
		if (str.length < len) {
			return str;
		} else {
			str = str.substring(0, len) + "...";
		}
		
		return str;		
	}
	//��ȡ�ַ�����
	function len(s) { 
		var l = 0; 
		var a = s.split(""); 
		for (var i=0;i<a.length;i++) { 
			if (a[i].charCodeAt(0)<299) { 
				l++; 
			} else { 
				l+=2; 
			} 
		} 
		return l; 
	}
	
	
	
	
	