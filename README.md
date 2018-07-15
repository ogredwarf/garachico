
책 검색 사이트 구현
===

garachico?
---
- **garachico** 라스팔마스에 있는 도시 이름 입니다.
  - 윤식당2에 나온 도시 이름으로 기억하기 편하라고 지었습니다. 
- [**카카오 API**][1] 를 이용한 책 검색 사이트 구현 
- Spring boot + Spring-security + Thymeleaf
- 동일한 방식을 다양한 방식으로 작성하여 이해하기 쉽도록 구현을 목표...  

주요 기능
---
- 책 검색 및 상세 보기 
  - 다음 책 검색 사이트와 연동
- 로그인, 즐겨찾기, 최신 검색 히스토리 조회 기능
- Feign을 이용한 KAKAO API 연동

개발 환경
---
> * JAVA SDK 버전 : 1.8
> * Spring Boot:  2.0.3.RELEASE
> * DB: H2

### 설치된 라이브러리
> * [jQuery][2] : 1.12.4
> * [jQuery.confirm][3]: 3.3.0
> * google Gson : 2.8.5

설치 및 실행 
---
### 설치
```
$ git clone git://github.com/ogredwarf/garachico.git
$ cd garachico
```

### 테스트
```
$ gradle test
```

### 빌드 및 실행
- 포트번호: 8080 으로 실행됩니다. 
- 사용되는 DB는 실행 때마다 초기화 됩니다. 
  - 회원 가입을 하셔야 정상적인 서비스 이용이 가능합니다.
```
$ gradle clean build 
$ gradle bootRun
```

#### 추가한 기능
- 중복로그인 방지
- 오류 페이지 등록 

#### 미흡한 기능 
- 디자인....( 특히 로그인 및 회원가입)
- 자바스크립트 스타일 공통 코드화 


결론..
---
![시간과 예산](src/main/resources/static/images/error.jpg)
- 시간과 예산을 조금만 더 주신다면...

[1]: https://developers.kakao.com/docs/restapi/search#%EC%B1%85-%EA%B2%80%EC%83%89
[2]: https://jquery.com/
[3]: https://craftpip.github.io/jquery-confirm/
