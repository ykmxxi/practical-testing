# 요구사항

- [x] 주문 목록 음료 추가
- [x] 주문 목록 음료 삭제
- [x] 주문 목록 전체 삭제
- [x] 주문 목록 총 금액 계산
- [x] 주문 생성
- [x] 한 종류의 음료 여러 잔을 한 번에 추가
- [x] 운영 시간(10:00 ~ 22:00) 외에는 주문 생성 불가능
- [x] 키오스크 주문을 위한 상품 후보 리스트 조회
- [x] 상품의 판매 상태 지정
  - 판매중, 판매보류: 화면에 노출
  - 판매중지: 화면에 노출 X
- [x] 상품 엔티티
  - id, 상품 번호, 상품 타입, 판매 상태, 상품 이름, 가격
- [x] 상품 번호 리스트를 받아 주문 생성
  - 상품 번호가 중복된 경우 동일 상품에 대한 중복 주문으로 처리
    - ex) ["001", "001"] -> "001" 상품 2개 주문
- [x] 주문에 주문 상태, 주문 등록 시간을 추가
- [x] 주문의 총 금액 계산
- [x] 주문 생성 시 재고 확인 및 개수 차감 후 생성
- [x] 재고는 상품번호를 가진다
- [x] 재고와 관련 있는 상품 타입은 병 음료, 베이커리
