package com.hro.core.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hro.core.cache.PictureCacheManager;
import com.hro.core.common.log.PicSvrLog;
import com.hro.core.common.util.StringUtil;
import com.hro.core.model.PicInfo;
import com.hro.core.mongodb.MongoJdbc;
import com.hro.core.service.picsvr.PicInfoService;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@Controller
@RequestMapping("/fileOperate")
public class FileOperateController {
	
	//解码器
	private BASE64Decoder decoder = new BASE64Decoder();
	//编码器
	private BASE64Encoder encoder = new BASE64Encoder();
	
	@Autowired
	@Qualifier("m_picInfoService") 
	private PicInfoService picInfoService;

	@RequestMapping(value="/fileUpload", method=RequestMethod.POST  )
	@ResponseBody
	public String fileUpload(@RequestParam("uploadFile") MultipartFile uploadFile,@RequestParam("filePath") String filePath, HttpServletRequest request ){

		String rtMsg = "error";
		//如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中  
        String realPath = request.getSession().getServletContext().getRealPath("/uploaded"+filePath);  
        //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
        try {
			FileUtils.copyInputStreamToFile(uploadFile.getInputStream(), new File(realPath, uploadFile.getOriginalFilename()));
	        
			PicInfo picInfo = new PicInfo();
			picInfo.setId(UUID.randomUUID().toString());
			
			int cnt = picInfoService.savePicInfo(picInfo);
			System.out.println(" cnt --->"+ cnt);
			
			
//			MongoOperations mongoDatabase = MongoJdbc.getMongoTemplate("hero_mongodb");
//			DBCollection collection = mongoDatabase.getCollection("hero_picsvr");
////	        List<Document> documents = new ArrayList<Document>();  
//	        
//	        Document fileDoc = new Document("realPath", realPath+"/"+uploadFile.getOriginalFilename())
//				.append("virtualPath", filePath+"/"+uploadFile.getOriginalFilename());
//	        
//	        mongoDatabase.save(fileDoc);
//	        collection.insertMany(documents);
			
			rtMsg = "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
			
        return rtMsg;
	}
	
	@RequestMapping(value="/imagePreview", method=RequestMethod.POST  )
	public void imagePreview(@RequestParam("uploadFile") MultipartFile uploadFile, @RequestParam("filePath") String filePath, HttpServletRequest request, HttpServletResponse response ){

		String rtMsg = "error";
		//如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中  
        String realPath = request.getSession().getServletContext().getRealPath("/uploaded"+filePath);
        //图片输出对象
        OutputStream out = null;
        //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
        try {
        	
        	byte[] data = uploadFile.getBytes();
        	out = response.getOutputStream();
        	out.write(data);
        	out.flush();
        	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if( out != null){
					out.close();
					out = null;
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
			
	}
	
	
	/***
	 * 按照文件名称删除文件
	 * @param params
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/deleteFiles", method=RequestMethod.POST  )
	@ResponseBody
	public Map<String, Object> deleteFiles(@RequestBody Map<String, Object> params, HttpServletRequest request){
		Map<String, Object> rtMap = new HashMap<String, Object>();
		String tmpFileNames = StringUtil.toString(params.get("fileNames"));
		String tmpFilePath = StringUtil.toString(params.get("filePath"));
		if(StringUtil.isEmpty(tmpFileNames) || StringUtil.isEmpty(tmpFilePath)){
			rtMap.put("status", "0");
			rtMap.put("message", "error");
			return rtMap;
		}
		
		//如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中  
        String realPath = request.getSession().getServletContext().getRealPath(tmpFilePath);  
		
		String[] fileNames = tmpFileNames.split(",");
		File tmpFile = null;
		for(String fileName: fileNames){
			tmpFile = new File(realPath+"/"+fileName);
			
			try {
				if(tmpFile.isDirectory()){
					FileUtils.deleteDirectory(tmpFile);
				}else {
					FileUtils.deleteQuietly(tmpFile);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		rtMap.put("status", "1");
		rtMap.put("message", "success");
		
		return rtMap;
	}
	
	/***It is ok
	@RequestMapping(value="/multiFileUpload", method=RequestMethod.POST  )
	@ResponseBody
	public String multiFileUpload(@RequestParam("Filedata") MultipartFile[] uploadFiles, HttpServletRequest request ){
		String rtMsg = "error";
		
		//如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中  
		String realPath = request.getSession().getServletContext().getRealPath("/uploaded/files");  
		//这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
		try {
			//如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解  
	        //如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解  
	        //并且上传多个文件时，前台表单中的所有<input type="file"/>的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件  
	        for(MultipartFile uploadFile : uploadFiles){  
	            if(uploadFile.isEmpty()){  
	                System.out.println("文件未上传");  
	            }else{  
	                System.out.println("文件长度: " + uploadFile.getSize());  
	                System.out.println("文件类型: " + uploadFile.getContentType());  
	                System.out.println("文件名称: " + uploadFile.getName());  
	                System.out.println("文件原名: " + uploadFile.getOriginalFilename());  
	                System.out.println("========================================");  
	                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
	                FileUtils.copyInputStreamToFile(uploadFile.getInputStream(), new File(realPath, uploadFile.getOriginalFilename()));  
	            }  
	        }
	        
	        rtMsg = "success";
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return rtMsg;
	}
	***/
	
	@RequestMapping(value="/queryFile/{filePath}/{fileName:.*}", method=RequestMethod.GET)
	public void queryFile(@PathVariable String filePath, @PathVariable String fileName, HttpServletRequest request, HttpServletResponse response){
		
		long timeBegin = System.currentTimeMillis();
		
		InputStream in = null;
		OutputStream out = null;
		
		String fileKey = filePath + "/"+ fileName;
		try {
			
			//如果图片缓存已经存在，则直接读取缓存数据
			if( PictureCacheManager.getInstance().isDataExist(fileKey) ){
				byte[] bytefer = PictureCacheManager.getInstance().getData(fileKey);
				out = response.getOutputStream();
				out.write(bytefer, 0, bytefer.length);
				out.flush();
				
				PicSvrLog.debug("[FileOperateController.queryFile] -> read data from cache");
				long timeEnd = System.currentTimeMillis();
				PicSvrLog.debug("[FileOperateController.queryFile] -> read from cache times:"+ (timeEnd - timeBegin) );
			}else {
				//如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中  
				String realPath = request.getSession().getServletContext().getRealPath("/uploaded/"+ fileKey);  
				File file = new File(realPath);
				
				if(file != null){
					int length = 0;
					
					in = new FileInputStream(realPath); 
					out = response.getOutputStream();
					
					byte[] bytefer = new byte[in.available()];
					in.read(bytefer);
					
					if( bytefer != null && bytefer.length > 0 ){
						PictureCacheManager.getInstance().addData(fileKey, bytefer);
						PicSvrLog.debug("[FileOperateController.queryFile] -> put data to cache");
					}
					
					out.write(bytefer, 0, bytefer.length);  
					out.flush();
					
					long timeEnd = System.currentTimeMillis();
					PicSvrLog.debug("[FileOperateController.queryFile] -> read from file times:"+ (timeEnd - timeBegin) );
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if( in != null){
					in.close();
					in = null;
				}
				if( out != null){
					out.close();
					out = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@RequestMapping(value="test", method=RequestMethod.GET)
	public void test(){
		System.out.println("test ------>");
		MongoTemplate mongoDatabase = MongoJdbc.getMongoTemplate("hero_mongodb");
        DBCollection collection = mongoDatabase.getCollection("hero_picsvr");
        //检索所有文档  
        /** 
        * 1. 获取迭代器FindIterable<Document> 
        * 2. 获取游标MongoCursor<Document> 
        * 3. 通过游标遍历检索出的文档集合 
        * */  
        
        DBCursor dbCursor = collection.find();
        
        Iterator<DBObject> mongoCursor = dbCursor.iterator();
        while( mongoCursor.hasNext() ){  
        	DBObject doc = mongoCursor.next();  
            PicSvrLog.info(" doc --->"+ doc+" realPath --->"+ StringUtil.toString(doc.get("realPath")) +" virtualPath --->"+ StringUtil.toString(doc.get("virtualPath")) );
        }
        
	}

	public PicInfoService getPicInfoService() {
		return picInfoService;
	}

	public void setPicInfoService(PicInfoService picInfoService) {
		this.picInfoService = picInfoService;
	}
	
}
