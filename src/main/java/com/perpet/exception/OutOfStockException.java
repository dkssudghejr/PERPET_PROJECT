package com.perpet.exception;

//상품의 재고가 부족하거나 구매할 수 없는 경우에 발생하는 예외
//온라인 상점이나 재고 관리 시스템 등에서 많이 사용
//사용자 정의 예외
public class OutOfStockException extends RuntimeException{
	
	//예외 발생 시 메세지 출력
	public OutOfStockException(String message) {
		super(message);
	}
}