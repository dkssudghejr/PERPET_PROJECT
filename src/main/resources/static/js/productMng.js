$(document).ready(function() {
	//검색버튼을 클릭하면 
	$("#searchBtn").on("click", function(e) {
		e.preventDefault(); //기본동작을 중지 (전송을 막아줌)
		page(0); //페이지 변호로 0번째 페이지를 조회하는 page() 함수를 호출 
	});
});

//페이지 처리를 위한 함수
//이동할 페이지 값을 받아서 조회 조건으로 설정된
//상품 등록 기간, 판매 상태, 조회유형, 검색어 등을 파라미터로 설정한 후
//상품을 조회함
function page(page) {
	var searchDateType = $("#searchDateType").val(); //검색 날짜 유형
	var searchSellStatus = $("#searchSellStatus").val(); //판매상태
	var searchBy = $("#searchBy").val(); //검색기준
	var searchQuery = $("#searchQuery").val(); //검색쿼리

	//쿼리스트링을 생성하여 페이지를 이동
	location.href = "/admin/items/" + page + "?searchDateType=" + searchDateType
		+ "&searchSellStatus=" + searchSellStatus
		+ "&searchBy=" + searchBy
		+ "&searchQuery=" + searchQuery;
}