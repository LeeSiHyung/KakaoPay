# 카카오페이 사전과제

```
백엔트
  ㅇ JAVA8
  ㅇ 스프링 MVC
  ㅇ JPA
  ㅇ Tomcat8.5
  ㅇ Maven
  
프론트
  ㅇ Bootstrap
  ㅇ JSP
  ㅇ JQUERY

```

```
빌드
  ㅇ mvn package
```

```
Page
  ㅇ http://localhost:8080/index.do
```

```
API 정보
  ㅇ 기본정보
  application/json
  
  ㅇ 리스트
  URL : /api/v1/list.do
  Method : get
  Parameter : int page
  
  ㅇ 추가
  URL : /api/v1/insert.do
  Method : post
  Parameter : String todo
  
  ㅇ 업데이트
  URL : /api/v1/update.do
  Method : put
  Parameter : long id, String todo
  
  ㅇ 완료
  URL : /api/v1/complete.do
  Method : put
  Parameter : long id
  
```
