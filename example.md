## 🖥️ ketchby 소개

취미 아마추어들의 정식 클래스 오픈 및 성장을 돕는 웹사이트

## 🕛 개발 기간

✔️ 개발 기간 : 2023.07. ~ 2023.08(4주)


## 🧑‍🤝‍🧑멤버 구성

![image](./image/image.022.png)


## 👩‍💻개발 환경

### BackEnd
---


![image](https://github.com/hyunseo3/ketchby/assets/94173709/b8847e8a-9b7a-4572-92a6-fd4a2f99ddfa)


## 📖ERD 다이어그램
![image](./image/image.001.png)


## 🖥️화면 구성 및 주요 기능
### 로그인 & 회원가입
---
![image](./image/image.002.png) 
* 
* Oauth, spring security를 이용하여 카카오 계정과 연동
* Mail API를 이용한 이메일 인증
* Ajax를 이용해 사용자가 입력한 아이디의 유무 확인


### 메인 페이지
---
![image](./image/image.003.png)

* % 연산 사용, 마지막 슬라이드에서 다시 처음 순환 가능
* autoSlide 함수로 일정 시간마다 자동으로 슬라이드 변경
* setInterval를 통해 3초마다 autoSlide 함수가 호출
* 추천 클래스 : SQL 쿼리를 이용해 자신과 비슷한 취향의 회원 + 새로 가입시에는 인기 클래스 추천


### 클래스
---
1. 클래스 메인

![image](./image/image.004.png)

2. 클래스 상세

![image](./image/image.005.png)

3. 클래스 개설

![image](./image/image.006.png)

* 검색, 카테고리별, 필터별로 조건에 맞는 카드 추출(updateQueryString)
* 카카오 API를 사용하여 위치를 지도에 표시
* Ajax를 이용하여 카테고리 대 분류 선택 시 대 분류에 속하는 소분류 카테고리 출력


### 예약/결제
---
![image](./image/image.007.png)

* JS를 통해 신청 가능한 날짜만 활성화
* Import 모듈을 이용하여 결제 기능 구현



### 마이페이지
---
![image](./image/image.009.png)

* 로그인 세션을 불러와 게시물, 수강내역, 소모임 등 Ketchby에서의 활동 확인


### 공지사항
---
![image](./image/image.011.png)

* 관리자가 관리자 페이지에 작성한 공지사항만 출력 (Thymeleaf  for each문 사용)


### 커뮤니티
---
![image](./image/image.012.png)

* HOT 인기글을 rownum을 활용해 상위 5개 출력
* select문을 사용하여 검색어에 맞는 쿼리 작동

### 커뮤니티 상세/등록
---
![image](./image/image.013.png)

* Thymeleaf를 사용해 본문 출력
* 조건문을 활용하여 작성자와 로그인 세션 저장, 작성자 본인일 떄에만 수정, 삭제 가능
* Ajax을 이용해 댓글이 등록될 때마다 함수 호출 - 실시간 업데이트 가능
* form 태그를 활용하여 등록 시 controller로 이동하여 insert 작업 수행

### 소모임
---
![image](./image/image.014.png)

* select문을 사용하여 신청 가능한 소모임만 표시, controller 조건식으로 동일 소모임 중복 신청 불가능

### Q&A
---
![image](./image/image.015.png)

* Ajax을 이용해 자주 묻는 질문을 아코디언 형식으로 생성
* 자바스크립트를 사용하여 이메일 복사 버튼 생성
* MailSender를 이용하여 제출 시 이메일 발송

### 회원정보 수정 및 탈퇴
---
![image](./image/image.016.png)

### 대시보드
---
![image](./image/image.017.png)

* 당일 및 전일의 신규 가입, 개설 클래스, 탈퇴 수 SQL쿼리를 작성해 전일 대비 당일 증감률 수치화
* Chart.js 및 Ajax 할용
* DB에서 추출한 데이터를 JSON 객체에 담아 view에 넘긴 뒤, 받은 데이터를 가공해 Chart.js가 제공하는 양식에 넣어 시각화

### 회원 관리
---
![image](./image/image.018.png)

* 아이디, 이메일, 이름 각각의 칼럼과 전체 칼럼으로 선택하여 검색 가능
* 모달과 FormData, Ajax를 활용해 회원의 상세 정보 확인 후 수정, 삭제

### 클래스 관리
---
![image](./image/image.019.png)

* 클래스 개설 시, 자동으로 승인 요청 클래스에 업로드
* 승인된 클래스만을 메인 및 클래스 메인에 제시

### 공지사항 관리
---
![image](./image/image.020.png)

* 모달과 Ajax을 활용해 공지사항의 제목과 작성일, 내용을 확인 후 수정, 삭제 가능

### 문의사항 관리
---
![image](./image/image.021.png)

* 답장 여부, 각 칼럼 별로 검색 가능
* 모달과 Ajax을 활용해 문의사항의 종류, 번호, 이름, 이메일, 제목, 문의 내용, 문의 일시 확인
* 답장 버튼을 눌러 MailAPI를 활용해 답장, Ajax를 활용해 답장 여부를 '보냄'으로 변환
