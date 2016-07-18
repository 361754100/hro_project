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
	
	//������
	private BASE64Decoder decoder = new BASE64Decoder();
	//������
	private BASE64Encoder encoder = new BASE64Encoder();
	
	@Autowired
	@Qualifier("m_picInfoService") 
	private PicInfoService picInfoService;

	@RequestMapping(value="/fileUpload", method=RequestMethod.POST  )
	@ResponseBody
	public String fileUpload(@RequestParam("uploadFile") MultipartFile uploadFile,@RequestParam("filePath") String filePath, HttpServletRequest request ){

		String rtMsg = "error";
		//����õ���Tomcat�����������ļ����ϴ���\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\�ļ�����  
        String realPath = request.getSession().getServletContext().getRealPath("/uploaded"+filePath);  
        //���ﲻ�ش���IO���رյ����⣬��ΪFileUtils.copyInputStreamToFile()�����ڲ����Զ����õ���IO���ص������ǿ�����Դ���֪����  
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
		//����õ���Tomcat�����������ļ����ϴ���\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\�ļ�����  
        String realPath = request.getSession().getServletContext().getRealPath("/uploaded"+filePath);
        //ͼƬ�������
        OutputStream out = null;
        //���ﲻ�ش���IO���رյ����⣬��ΪFileUtils.copyInputStreamToFile()�����ڲ����Զ����õ���IO���ص������ǿ�����Դ���֪����  
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
	 * �����ļ�����ɾ���ļ�
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
		
		//����õ���Tomcat�����������ļ����ϴ���\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\�ļ�����  
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
		
		//����õ���Tomcat�����������ļ����ϴ���\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\�ļ�����  
		String realPath = request.getSession().getServletContext().getRealPath("/uploaded/files");  
		//���ﲻ�ش���IO���رյ����⣬��ΪFileUtils.copyInputStreamToFile()�����ڲ����Զ����õ���IO���ص������ǿ�����Դ���֪����  
		try {
			//���ֻ���ϴ�һ���ļ�����ֻ��ҪMultipartFile���ͽ����ļ����ɣ�����������ʽָ��@RequestParamע��  
	        //������ϴ�����ļ�����ô�����Ҫ��MultipartFile[]�����������ļ������һ�Ҫָ��@RequestParamע��  
	        //�����ϴ�����ļ�ʱ��ǰ̨���е�����<input type="file"/>��name��Ӧ����myfiles������������myfiles�޷���ȡ�������ϴ����ļ�  
	        for(MultipartFile uploadFile : uploadFiles){  
	            if(uploadFile.isEmpty()){  
	                System.out.println("�ļ�δ�ϴ�");  
	            }else{  
	                System.out.println("�ļ�����: " + uploadFile.getSize());  
	                System.out.println("�ļ�����: " + uploadFile.getContentType());  
	                System.out.println("�ļ�����: " + uploadFile.getName());  
	                System.out.println("�ļ�ԭ��: " + uploadFile.getOriginalFilename());  
	                System.out.println("========================================");  
	                //���ﲻ�ش���IO���رյ����⣬��ΪFileUtils.copyInputStreamToFile()�����ڲ����Զ����õ���IO���ص������ǿ�����Դ���֪����  
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
			
			//���ͼƬ�����Ѿ����ڣ���ֱ�Ӷ�ȡ��������
			if( PictureCacheManager.getInstance().isDataExist(fileKey) ){
				byte[] bytefer = PictureCacheManager.getInstance().getData(fileKey);
				out = response.getOutputStream();
				out.write(bytefer, 0, bytefer.length);
				out.flush();
				
				PicSvrLog.debug("[FileOperateController.queryFile] -> read data from cache");
				long timeEnd = System.currentTimeMillis();
				PicSvrLog.debug("[FileOperateController.queryFile] -> read from cache times:"+ (timeEnd - timeBegin) );
			}else {
				//����õ���Tomcat�����������ļ����ϴ���\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\�ļ�����  
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
        //���������ĵ�  
        /** 
        * 1. ��ȡ������FindIterable<Document> 
        * 2. ��ȡ�α�MongoCursor<Document> 
        * 3. ͨ���α�������������ĵ����� 
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
