package com.perpet.service;

import javax.persistence.EntityNotFoundException;

import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.perpet.entity.ProductImg;
import com.perpet.repository.ProductImgRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductImgService {

	@Value("${productImgLocation}")
	private String productImgLocation;
	
	private final ProductImgRepository productImgRepository;
	
	private final FileService fileService;
	
	public void saveProductImg(ProductImg productImg, MultipartFile productImgFile) throws Exception{
		String oriImgName = productImgFile.getOriginalFilename();
		String imgName = "";
		String imgUrl = "";
		
		//파일 업로드
		if(!StringUtils.isEmpty(oriImgName)) {
			//사용자가 상품 이미지를 등록했다면 파일을 저장할 경로, 원본파일명, 파일의 바이트 배열을 
			//파일 업로드 파라미터로 uploadFile메서드를 호출
			imgName = fileService.uploadFile(productImgLocation, oriImgName, productImgFile.getBytes());
			//저장한 상품 이미지를 불러올 경로를 설정
			imgUrl = "/images/product/" + imgName;
		}
		//상품 이미지 정보를 저장
		productImg.updateProductImg(oriImgName, imgName, imgUrl);
		productImgRepository.save(productImg);
		
	}
	
	public void updateProductImg(Long productImgId, MultipartFile productImgFile) throws Exception{
		//이미지 파일이 비어있지 않으면
		if(!productImgFile.isEmpty()) {
			//이미지 아이디를 이용하여 기존에 저장했던 상품 이미지 엔티티를 조회
			ProductImg savedProductImg = productImgRepository.findById(productImgId).orElseThrow(EntityNotFoundException::new);
			 
			//기존에 등록된 상품 이미지 파일이 있는 경우 해당 파일을 삭제
			if(!StringUtils.isEmpty(savedProductImg.getImgName())) {
				fileService.deleteFile(productImgLocation + "/" + savedProductImg.getImgName());
			}
			
			//변경된 상품 이미지 저장
			String oriImgName = productImgFile.getOriginalFilename();
			String imgName = fileService.uploadFile(productImgLocation, oriImgName, productImgFile.getBytes());
			String imgUrl = "/images/product" + imgName;
			savedProductImg.updateProductImg(oriImgName, imgName, imgUrl);
		}
	}
}
