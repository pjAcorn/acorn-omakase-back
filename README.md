# acorn-omakase-back 1

## Description
7명의 멤버가 프론트, 백에 한정되지 않고 기능에 맞춰 유연하게 진행한 프로젝트입니다.

원하는 지역을 선택해 상권 분석, 유동인구를 파악해 유망한 업종을 추천해 주는 서비스입니다.


## 프로젝트 기간
2023.05.15. ~ 2023.06.17.

## Back-End Contributors
|이름|github|
|---|---|
|조건희|[조건희 github](https://github.com/gunhee93)|
|노현경|[노현경 github](https://github.com/nhk1657)|
|전대웅|[전대웅 github](https://github.com/jundanny)|
|정종호|[정종호 github](https://github.com/DreamJJW)|
|정지우|[정지우 github](https://github.com/youhobin)|
|김혜진|[유호빈 github](https://github.com/rla77gpwls)|         

## 기술스택 
>Environment
<div>
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
</div>

>Development
back-end
<div>
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
</div>

>Infra
<div>
  <img src="https://img.shields.io/badge/amazonec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white">
  <img src="https://img.shields.io/badge/amazonrds-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white">
  <img src="https://img.shields.io/badge/amazons3-569A31?style=for-the-badge&logo=amazons3&logoColor=white">
</div>

>Communication
<div>
  <img src="https://img.shields.io/badge/slack-4A154B?style=for-the-badge&logo=slack&logoColor=white">
</div>

## 배포주소 
실제 배포 : http://

테스트 서버 : http://

---

## 주요기능 및 요구사항

#### 내 가게 찾기 (메인 기능) : 고객이 선택한 상권, 업종 정보를 이용하여 데이터 분석 후 화면에 지도, 데이터 출력 (현경 건희)

- 주요 기능 : 지도api, 지역 유동인구, 업종별 포화/분포도
-  요구사항 : 
   1) 내 가게 찾기로 이동 시 화면에는 카카오 지도가 표시되며 업종과 지역(시.도) 입력을 하면 해당 상권의 유동인구, 점포 수, 포화도가 화면에 표시된다.
	 2) 로그인을 한 회원을 검색한 데이터와 회원 데이터를 기반으로 해당 조건에 맞는 업종을 추천 받을  수 있다.

#### 회원 - 로그인 이후에 할 수 있는 활동 ex) 게시글 작성, 댓글 작성, 업종 추천 받기 (종호 대웅)

- 주요 기능 : 회원가입, 로그인, 아이디 찾기, 비밀번호 변경, 로그아웃, 회원정보 수정, 회원 탈퇴, 로그인 이후 활동 인증/인가
- 요구사항 :  
  1) 사용자는 개인 필수 정보를 입력해 가입할 수 있다. 
	2) 가입이 완료되면 로그인이 가능하며 로그인 시 제한된 모든 기능을 사용할 수 있다(게시글 작성, 댓글 작성, 업종 추천받기).	
	3) 로그인을 하지 않아도 게시글을 읽을 수 있고 상권을 검색 할 수 있다.
	4) 가입한 사용자는 아이디 / 비밀번호 분실 시 이메일 정보를 이용하여 아이디를 찾고 비밀번호를 변경 할 수 있으며
	마이 페이지 화면에 입장해 개인 정보를 수정할 수 있다.

#### 커뮤니티(게시판)  - 창업주들이 소통 할 수 있는 게시판 (혜진 지우)

- 주요 기능 : 게시글 작성, 게시글 목록(조회순, 최신순), 게시글 단건 조회, 댓글, 좋아요
- 요구사항 : 
  1) 게시글 목록을 조회순, 최신순으로 나누어 조회 가능하고 페이지당 10개의 게시글 목록을 볼 수 있다.
	2) 게시글 목록에는 작성일, 조회수, 작성자 닉네임, 제목이 표시된다.
  3) 게시글을 클릭하면 게시글 전체(제목, 내용 작성일, 닉네임, 댓글)를 볼 수 있으며 로그인 한 사용자는 댓글 작성이 가능하다.

## 기능 추가

#### 기능
- 

#### 기능
- 

---

## 구조 및 설계
### DB 설계
>erd (Entity Relationship Diagram)

![erd 초안](https://github.com/pjAcorn/acorn-omakase-back/assets/123151812/8989d217-bf31-4a6a-922e-abe6ea205bc4)

---

### CI/CD 구축 ()
![image](https://user-images.githubusercontent.com/111469930/229506681-aa8ec884-ce90-43f4-b8e4-c418db1842da.png)

1. main branch에 push 혹은 pull request 시 Github Actions 작동
2. deploy.yml에 따라 빌드된 jar는 AWS의 S3에 저장
3. AWS Code Deploy에게 S3에 저장된 jar 파일을 EC2 배포 명령
4. AWS EC2 서버에 배포


## Git 
### Git Convention
- commit 메시지
  - feat : 새로운 기능의 추가
  - fix : 버그 수정
  - docs : 문서 수정
  - style : 스타일 변경 (포매팅 수정, 들여쓰기 추가, 코드 자체의 변경이 없는 경우)
  - refactor : 코드 리팩토링
  - test : 테스트 관련 코드
  - chore : 그 외 자잘한 수정
  
- pull request 
  - 한글로 작성
  
### Git Branch 전략
- release : 배포를 위한 branch
- develop(dev) : 개발 branch
  - dev의 상태에서 feature branch 생성
- feature : 개인 작업 branch
