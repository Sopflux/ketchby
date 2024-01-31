## 🖥️ ketchby 소개

취미 아마추어들의 정식 클래스 오픈 및 성장을 돕는 웹사이트

## 🕛 개발 기간

✔️ 개발 기간 : 2023.07. ~ 2023.08(4주)


## 🧑‍🤝‍🧑멤버 구성

![image](https://github.com/gr033/Ketchby/assets/128104387/378b1b8a-2c24-4d4b-9ff8-66d3aecece5a)


## 👩‍💻개발 환경

### BackEnd
---
   ![image](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)  ![image](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white) ![image](https://img.shields.io/badge/Oracle_Cloud-F80000?style=for-the-badge&logo=oracle&logoColor=black) ![image](https://img.shields.io/badge/Apache_Tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=black) ![image](https://img.shields.io/badge/Maven-D22128?style=for-the-badge&logo=apachemaven&logoColor=white) ![image](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)

### Tools
---
![image](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white) ![image](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white) ![image](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white) ![image](https://img.shields.io/badge/Visual_Studio_Code-0078D4?style=for-the-badge&logo=visual%20studio%20code&logoColor=white)

### FrontEnd
---
![image](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white) ![image](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white) ![image](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white) ![image](https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white) ![image](https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white)



## 📖ERD 다이어그램
![image](https://github.com/gr033/Ketchby/assets/94173709/3a97ed5f-c2ee-4f95-8251-bfdee0c4985c)




## 🖥️화면 구성 및 주요 기능
### 로그인 & 회원가입
---
![image](https://github.com/gr033/Ketchby/assets/94173709/c2fbb78d-ad1b-4afa-9ef4-fce5a8b4d3c3)

* 
* Oauth, spring security를 이용하여 카카오 계정과 연동
* Mail API를 이용한 이메일 인증
* Ajax를 이용해 사용자가 입력한 아이디의 유무 확인


### 메인 페이지
---
![image](https://github.com/gr033/Ketchby/assets/94173709/9b84637b-e063-4659-84d6-56bfd92f96bb)


* % 연산 사용, 마지막 슬라이드에서 다시 처음 순환 가능
* autoSlide 함수로 일정 시간마다 자동으로 슬라이드 변경
* setInterval를 통해 3초마다 autoSlide 함수가 호출
* 추천 클래스 : SQL 쿼리를 이용해 자신과 비슷한 취향의 회원 + 새로 가입시에는 인기 클래스 추천


### 클래스
---
1. 클래스 메인

![image](https://github.com/gr033/Ketchby/assets/94173709/4628014f-5898-4dcf-ab84-9223da70d669)


2. 클래스 상세

![image](https://github.com/gr033/Ketchby/assets/94173709/552ebc5b-3afa-452a-a5d3-9fc634c9901f)


3. 클래스 개설

![image](https://github.com/gr033/Ketchby/assets/94173709/ab733e76-b800-4ee8-9e3d-e12ad600860c)

* 검색, 카테고리별, 필터별로 조건에 맞는 카드 추출(updateQueryString)
* 카카오 API를 사용하여 위치를 지도에 표시
* Ajax를 이용하여 카테고리 대 분류 선택 시 대 분류에 속하는 소분류 카테고리 출력


### 예약/결제
---
![image](https://github.com/Sopflux/ketchby/assets/94173709/ec077f5e-436e-4861-bef5-a05c17ba1241)

* JS를 통해 신청 가능한 날짜만 활성화
* Import 모듈을 이용하여 결제 기능 구현



### 마이페이지
---
![image](https://github.com/Sopflux/ketchby/assets/94173709/e8893156-c443-430d-97c8-d47cb811b9f7)

* 로그인 세션을 불러와 게시물, 수강내역, 소모임 등 Ketchby에서의 활동 확인


### 공지사항
---
![image](https://github.com/Sopflux/ketchby/assets/94173709/3eab53cb-06e4-437c-b474-4728f401e5ed)

* 관리자가 관리자 페이지에 작성한 공지사항만 출력 (Thymeleaf  for each문 사용)


### 커뮤니티
---
![image](https://github.com/Sopflux/ketchby/assets/94173709/bf1ac72d-a814-4433-bb0f-4e3feb9c7736)

* HOT 인기글을 rownum을 활용해 상위 5개 출력
* select문을 사용하여 검색어에 맞는 쿼리 작동

### 커뮤니티 상세/등록
---
![image](https://github.com/Sopflux/ketchby/assets/94173709/42fbbb0a-d4e8-4c5e-b425-7a998c3a429f)

* Thymeleaf를 사용해 본문 출력
* 조건문을 활용하여 작성자와 로그인 세션 저장, 작성자 본인일 떄에만 수정, 삭제 가능
* Ajax을 이용해 댓글이 등록될 때마다 함수 호출 - 실시간 업데이트 가능
* form 태그를 활용하여 등록 시 controller로 이동하여 insert 작업 수행

### 소모임
---
![image](https://github.com/Sopflux/ketchby/assets/94173709/a5b92415-7113-4639-a4cc-a6f86f301772)

* select문을 사용하여 신청 가능한 소모임만 표시, controller 조건식으로 동일 소모임 중복 신청 불가능

### Q&A
---
![image](https://github.com/Sopflux/ketchby/assets/94173709/a21cd9a7-f102-4fc7-9d58-fedbaa8e13cf)

* Ajax을 이용해 자주 묻는 질문을 아코디언 형식으로 생성
* 자바스크립트를 사용하여 이메일 복사 버튼 생성
* MailSender를 이용하여 제출 시 이메일 발송

### 회원정보 수정 및 탈퇴
---
![image 016](https://github.com/gr033/Ketchby/assets/128104387/7904b8c4-d868-4b4b-9a24-f0af6fd585db)

### 대시보드
---
![image 017](https://github.com/gr033/Ketchby/assets/128104387/54d76ca6-206c-445b-b85a-64cc4a04343f)

* 당일 및 전일의 신규 가입, 개설 클래스, 탈퇴 수 SQL쿼리를 작성해 전일 대비 당일 증감률 수치화
* Chart.js 및 Ajax 할용
* DB에서 추출한 데이터를 JSON 객체에 담아 view에 넘긴 뒤, 받은 데이터를 가공해 Chart.js가 제공하는 양식에 넣어 시각화

### 회원 관리
---
![image 018](https://github.com/gr033/Ketchby/assets/128104387/c38d6778-0e74-4110-a5ab-01c457ee6541)

* 아이디, 이메일, 이름 각각의 칼럼과 전체 칼럼으로 선택하여 검색 가능
* 모달과 FormData, Ajax를 활용해 회원의 상세 정보 확인 후 수정, 삭제

### 클래스 관리
---
![image 019](https://github.com/gr033/Ketchby/assets/128104387/5836b59b-33c9-43a6-9dda-72a4c8d3ea64)

* 클래스 개설 시, 자동으로 승인 요청 클래스에 업로드
* 승인된 클래스만을 메인 및 클래스 메인에 제시

### 공지사항 관리
---
![image 020](https://github.com/gr033/Ketchby/assets/128104387/64ef2391-cc80-405a-9436-086af9928c00)

* 모달과 Ajax을 활용해 공지사항의 제목과 작성일, 내용을 확인 후 수정, 삭제 가능

### 문의사항 관리
---
![image 021](https://github.com/gr033/Ketchby/assets/128104387/8b5d0764-2228-4b17-9898-b904aabe5103)

* 답장 여부, 각 칼럼 별로 검색 가능
* 모달과 Ajax을 활용해 문의사항의 종류, 번호, 이름, 이메일, 제목, 문의 내용, 문의 일시 확인
* 답장 버튼을 눌러 MailAPI를 활용해 답장, Ajax를 활용해 답장 여부를 '보냄'으로 변환
