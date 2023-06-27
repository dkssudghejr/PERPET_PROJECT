/*
 * package com.perpet.service;
 * 
 * import static org.junit.Assert.assertTrue; import static
 * org.junit.jupiter.api.Assertions.assertEquals;
 * 
 * import org.junit.jupiter.api.DisplayName; import org.junit.jupiter.api.Test;
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.security.crypto.password.PasswordEncoder; import
 * org.springframework.test.context.TestPropertySource; import
 * org.springframework.transaction.annotation.Transactional;
 * 
 * import com.perpet.dto.MemberFormDto; import com.perpet.entity.Member;
 * 
 * @SpringBootTest //테스트 클래스
 * 
 * //테스트에서 트랜잭션을 이용하면 테스트 실행 후 롤백처리됨
 * 
 * @Transactional //트랜잭션
 * 
 * @TestPropertySource(locations="classpath:application-test.properties") public
 * class MemberServiceTest {
 * 
 * @Autowired MemberService memberService;
 * 
 * @Autowired PasswordEncoder passwordEncoder;
 * 
 * //회원정보를 입력한 member엔티티를 만드는 메서드 작성 public Member createMember() {
 * MemberFormDto memberFormDto = new MemberFormDto();
 * memberFormDto.setMemail("hello"); memberFormDto.setM_pw("12341234");
 * memberFormDto.setM_name("김그린"); memberFormDto.setM_tel("010-1111-1111");
 * memberFormDto.setM_addr("울산 남구");
 * 
 * memberFormDto.setM_terms("Y"); memberFormDto.setM_drop("N");
 * 
 * 
 * return Member.createMember(memberFormDto, passwordEncoder); }
 * 
 * @Test
 * 
 * @DisplayName("회원가입 테스트") public void saveMemberTest() { Member member =
 * createMember(); Member savedMember = memberService.saveMember(member);
 * //저장하려고 요청했던 값과 실제 저장된 데이터를 비교 // 기대값 , 실제 저장된 값
 * assertEquals(member.getMemail(), savedMember.getMemail());
 * assertEquals(member.getM_pw(), savedMember.getM_pw());
 * assertEquals(member.getM_name(), savedMember.getM_name());
 * assertEquals(member.getM_tel(), savedMember.getM_tel());
 * assertEquals(member.getM_addr(), savedMember.getM_addr());
 * assertEquals(member.getM_terms(), savedMember.getM_terms());
 * assertEquals(member.getM_drop(), savedMember.getM_drop());
 * assertEquals(member.getRole(), savedMember.getRole()); }
 * 
 * @Test
 * 
 * @DisplayName("중복 회원 가입 테스트") public void saveDuplicateMemberTest() { Member
 * member1 = createMember(); Member member2 = createMember();
 * memberService.saveMember(member1);
 * 
 * try { memberService.saveMember(member2); } catch(IllegalStateException e) {
 * assertTrue(e instanceof IllegalStateException); assertEquals("이미 가입된 회원입니다.",
 * e.getMessage()); } } }
 */