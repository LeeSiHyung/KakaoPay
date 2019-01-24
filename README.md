# 카카오페이 사전과제

```
백엔트
  1) JAVA8
  2) 스프링 MVC
  3) JPA
  4) Tomcat8.5
  5) Maven
  
프론트
  1) Bootstrap
  2) JSP
  3) JQUERY

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
  1) 데이터 규격
  
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
![contens](https://user-images.githubusercontent.com/18481841/51687623-489bbe80-2036-11e9-84a6-51a1d0993536.PNG)

