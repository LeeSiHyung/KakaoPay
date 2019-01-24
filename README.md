# 카카오페이 사전과제

```
1) 백엔트
  ㅇ JAVA8
  ㅇ 스프링 MVC
  ㅇ JPA
  ㅇ Tomcat8.5
  ㅇ Maven
  
2) 프론트
  ㅇ Bootstrap
  ㅇ JSP
  ㅇ JQUERY

```

```
빌드
  mvn package
```

```
Page
  http://localhost:8080/index.do
```

```
API 정보
  1) 기본정보
  application/json
  
  2) API 목록
    가. 목록
    URL : /api/v1/list.do
    Method : get
    Parameter : int page
  
    나. 추가
    URL : /api/v1/insert.do
    Method : post
    Parameter : String todo
  
    다. 업데이트
    URL : /api/v1/update.do
    Method : put
    Parameter : long id, String todo
  
    라. 완료
    URL : /api/v1/complete.do
    Method : put
    Parameter : long id
  
```
