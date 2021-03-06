package org.mhboard.web.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.mhboard.web.board.vo.BoardVO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component("fileUtils")
public class FileUtils {
	//private static final String filePath = "C:\\mp\\file\\"; // 파일이 저장될 위치(로컬)
	
	private static final String filePath = "//usr//local//tomcat//webapps//ROOT//file//"; //서버 저장위치
	// 서버 저장 경로 "//usr//local//tomcat//webapps//ROOT//tripdiary//board_img//";
	
	public List<Map<String, Object>> parseInsertFileInfo(BoardVO boardVO, 
			MultipartHttpServletRequest mpReq) throws Exception{
		
		/*
			Iterator은 데이터들의 집합체? 에서 컬렉션으로부터 정보를 얻어올 수 있는 인터페이스입니다.
			List나 배열은 순차적으로 데이터의 접근이 가능하지만, Map등의 클래스들은 순차적으로 접근할 수가 없습니다.
			Iterator을 이용하여 Map에 있는 데이터들을 while문을 이용하여 순차적으로 접근합니다.
		*/
		
		Iterator<String> iterator = mpReq.getFileNames();
		
		MultipartFile multipartFile = null;
		String orgFileName = null;
		String orgFileType = null;
		String strFileName = null;
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> listMap = null;
		
		int bid = boardVO.getBid();
		
		File file = new File(filePath);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		while(iterator.hasNext()) {
			multipartFile = mpReq.getFile(iterator.next());
			if(multipartFile.isEmpty() == false) {
				orgFileName = multipartFile.getOriginalFilename();
				orgFileType = orgFileName.substring(orgFileName.lastIndexOf("."));
				strFileName = getRandomString() + orgFileType;
				
				file = new File(filePath + strFileName);
				multipartFile.transferTo(file);
				listMap = new HashMap<String, Object>();
				listMap.put("bid", bid);
				listMap.put("orgFileName", orgFileName);
				listMap.put("strFileName", strFileName);
				listMap.put("fileSize", multipartFile.getSize());
				list.add(listMap);
			}
		}
		return list;
	}
	
	
	
	//다중파일 처리 메서드
	public List<Map<String, Object>> parseUpdateFileInfo(BoardVO boardVO, String[] files, String[] fileNames, MultipartHttpServletRequest mpReq) throws Exception{ 
		Iterator<String> iterator = mpReq.getFileNames();
		MultipartFile multipartFile = null;
		String orgFileName = null;
		String orgFileType = null;
		String strFileName = null;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> listMap = null; 
		int bid = boardVO.getBid();
		while(iterator.hasNext()){ 
			multipartFile = mpReq.getFile(iterator.next()); 
			if(multipartFile.isEmpty() == false){ 
				orgFileName = multipartFile.getOriginalFilename(); 
				orgFileType = orgFileName.substring(orgFileName.lastIndexOf(".")); 
				strFileName = getRandomString() + orgFileType; 
				multipartFile.transferTo(new File(filePath + strFileName)); 
				listMap = new HashMap<String,Object>();
				listMap.put("IS_NEW", "Y");
				listMap.put("bid", bid); 
				listMap.put("orgFileName", orgFileName);
				listMap.put("strFileName", strFileName); 
				listMap.put("fileSize", multipartFile.getSize());
				list.add(listMap); 
				System.out.println("==========이거 필수확인1=========" +listMap);
			} 
		}
		if(files != null && fileNames != null){ 
			for(int i = 0; i<fileNames.length; i++) {
				System.out.println("==========이거 필수확인2=========" +listMap);
					listMap = new HashMap<String,Object>();
                    listMap.put("IS_NEW", "N");
					listMap.put("fid", files[i]); 
					list.add(listMap); 
			}
		}
		return list; 
	}
	
	
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}