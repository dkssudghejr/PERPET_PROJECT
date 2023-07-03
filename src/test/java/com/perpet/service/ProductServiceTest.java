package com.perpet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.perpet.contstant.ProductSellStatus;
import com.perpet.dto.ProductFormDto;
import com.perpet.entity.Product;
import com.perpet.entity.ProductImg;
import com.perpet.repository.ProductImgRepository;
import com.perpet.repository.ProductRepository;

@SpringBootTest // 테스트 클래스
//테스트에서 트랜잭션을 이용하면 테스트 실행 후 롤백처리됨
@Transactional // 트랜잭션
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProductServiceTest {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductImgRepository productImgRepository;
	
	List<MultipartFile> createMultipartFiles() throws Exception {

		List<MultipartFile> multipartFileList = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			String path = "D://shop/item/";
			String imageName = "image" + i + ".jpg";
			MockMultipartFile multipartFile = new MockMultipartFile(path, imageName, "image/jpg",
					new byte[] { 1, 2, 3, 4 });
			multipartFileList.add(multipartFile);
		}

		return multipartFileList;
	}

	@Test
	@DisplayName("상품등록테스트")
	void saveProduct() throws Exception{
		ProductFormDto productFormDto = new ProductFormDto();
		productFormDto.setCate(10);
		productFormDto.setName("테스트 상품");
		productFormDto.setOnlineBuy("Y");
		productFormDto.setPrice(1500);
		productFormDto.setMadeBy("제조사");
		productFormDto.setStockNumber(100);
		productFormDto.setIngredient("대표성분");		
		productFormDto.setDetail("상세설명");		
		productFormDto.setProductSellStatus(ProductSellStatus.SELL);				
		
		List<MultipartFile> multipartFileList = createMultipartFiles();
		
		Long id = productService.saveProduct(productFormDto, multipartFileList);
		
		List<ProductImg> productImgList = productImgRepository.findByProductIdOrderByIdAsc(id);
		Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		
		assertEquals(productFormDto.getCate(), product.getCate());
		assertEquals(productFormDto.getName(), product.getName());
		assertEquals(productFormDto.getOnlineBuy(), product.getOnlineBuy());
		assertEquals(productFormDto.getPrice(), product.getPrice());
		assertEquals(productFormDto.getMadeBy(), product.getMadeBy());
		assertEquals(productFormDto.getStockNumber(), product.getStockNumber());
		assertEquals(productFormDto.getIngredient(), product.getIngredient());
		assertEquals(productFormDto.getDetail(), product.getDetail());
		assertEquals(productFormDto.getProductSellStatus(), product.getProductSellStatus());
		assertEquals(multipartFileList.get(0).getOriginalFilename(), productImgList.get(0).getOriImgName());
		
		
		
	}
}
