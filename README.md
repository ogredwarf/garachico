
# 책 검색 사이트 구현

##개요
- [**카카오 API**][1] 를 이용한 책 검색 사이트 구현 
- 로그인, 즐겨찾기, 최신 검색 기능
- Spring boot + Spring-security + Thymeleaf 

## 프로젝트 명
- **garachico** 라스팔마스에 있는 도시 이름 
- 윤식당2에 나온 도시 이름으로 기억하기 편하라고 지었습니다. 

## 개발 환경
> JAVA SDK 버전 : 1.8
> Spring Boot:  2.0.3.RELEASE
> DB: H2

### 설치된 라이브러리
> jQuery : 1.12.4
> jQuery.confirm: 3.3.0
> google Gson : 2.8.5
>  

## 설치
```
$ git clone git://github.com/ogredwarf/garachico.git
$ cd garachico
```

## 실행
- 사용되는 DB는 실행 때마다 초기화 됩니다. 
```
$ gradle clean build 
$ gradle bootRun
```

[1]: https://developers.kakao.com/docs/restapi/search#%EC%B1%85-%EA%B2%80%EC%83%89
