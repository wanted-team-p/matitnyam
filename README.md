# 맛있냠(Matitnyam) | 맛집 검색 서비스 

___

## 개요
* 본 서비스는 주변 맛집을 검색하고, 이에 대한 리뷰를 남길 수 있는 서비스입니다.

### 주요 기능
1. **맛집 관련 기능**
    - 사용자가 걸어갈 만한 맛집 목록을 검색할 수 있습니다.
    - 사용자가 차를 타고 이동할 만한 맛집 목록을 검색할 수 있습니다.
    - 사용자는 맛집에 대한 상세 정보를 확인할 수 있습니다.


2. **맛집에 대한 리뷰 관련 기능**
    - 사용자는 맛집에 대한 리뷰를 작성할 수 있습니다.
    - 사용자는 맛집에 대한 리뷰를 수정할 수 있습니다.
    - 사용자는 맛집에 대한 리뷰를 삭제할 수 있습니다.
    - 사용자는 해당 맛집에 대한 리뷰 목록을 조회할 수 있습니다.

___

## 목차
* [개요](#개요)
* [기술 스택](#기술-스택)
* [패키지 구조](#패키지-구조)
* [프로젝트 주제](#프로젝트-주제)
* [기능 목록](#API-목록)
* [DB 설계](#DB-설계)
* [학습 내용](#학습-내용)
* [저자](#저자)

___

## 기술 스택
![SPRING BOOT](https://img.shields.io/badge/spring_boot-6DB33F?style=for-the-badge&logoColor=ffffff)
![SPRING DATA JPA](https://img.shields.io/badge/spring_data_jpa-6DB33F?style=for-the-badge&logoColor=ffffff)
![JAVA](https://img.shields.io/badge/java-007396?style=for-the-badge)
![H2 database engine](https://img.shields.io/badge/h2_database-004088?style=for-the-badge&logoColor=white)

___

## 패키지 구조

<details>
<summary> 클릭하여 펼치기 </summary>

```
src
  ├─main
  │  ├─java
  │  │  └─com
  │  │      └─wanted
  │  │          └─matitnyam
  │  │              ├─config
  │  │              ├─controller
  │  │              ├─domain
  │  │              │  ├─csvparser
  │  │              │  └─xmlparser
  │  │              ├─dto
  │  │              ├─exception
  │  │              ├─repository
  │  │              │  └─impl
  │  │              ├─resolver
  │  │              ├─scheduler
  │  │              ├─service
  │  │              └─util
  │  └─resources
  │      ├─data
  │      └─test
  └─test
      └─java
          └─com
              └─wanted
                  └─matitnyam
                      ├─domain
                      │  ├─csvparser
                      │  └─xmlparser
                      ├─dto
                      ├─repository
                      ├─service
                      └─util
```

</details>

___

## 프로젝트 주제
### 서버 구현
* 스프링 프레임워크를 활용한 REST API 개발
    - REST API 가이드에 맞춘 URI 설계


* JWT 로그인
    - jjwt 라이브러리 활용


* 관계형 데이터베이스 설계
    - JPA Criteria를 활용한 쿼리


* ORM 활용
    - JPA Persistence 활용한 엔티티 지정


* 테스트 코드 작성
    - JUnit5를 활용한 단위 테스트
    - Postman을 활용한 API 호출 테스트


### 데이터 수집 및 처리
* OpenApi를 활용한 데이터 수집
    - 스케쥴러를 활용한 주기적 데이터 수집
    - JAXB를 활용한 XML 데이터 파싱


* 시군구 정보 업로드
    - csv 데이터 파싱 및 저장

___

## API 목록
### 1. 사용자 관련 기능
#### 1-1. 회원가입
<details> <summary>클릭하여 상세 보기</summary>

* 계정 이름과 비밀번호를 통해 회원가입을 요청
* HTTP 요청: `POST: {{base}}/members/`
* 호출 예시: `{{base}}/members/?name=neppiness&password=1234`
* 응답 예시: `201 Created`

    ```json
    {
        "name": "neppiness",
        "authority": "USER",
        "latitude": null,
        "longitude": null
    }
    ```
</details>


#### 1-2. 로그인
<details> <summary>클릭하여 상세 보기</summary>

* 계정 이름과 비밀번호를 통해 사용자 인증을 위한 토큰을 요청
* HTTP 요청: `GET: {{base}}/members/`
* 호출 예시: `{{base}}/members/?name=neppiness&password=1234`
* 응답 예시: `200 OK`

    ```json
    {
        "token": "eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZXBwaW5lc3MiLCJpYXQiOjE3MDI5NzQ0NzIsImV4cCI6MTcwMjk3NjI3MiwiYXV0aCI6IlVTRVIifQ.Kk7IEJZq06jVD0PIMg0ZVCsiXUdAdsJVw0fb2J7GboA"
    }
    ```
</details>
      
    
#### 1-3. 사용자 위치 업데이트
<details> <summary>클릭하여 상세 보기</summary>

* 인증된 사용자가 위도와 경도를 입력하여 사용자 정보를 갱신하고 해당 정보를 담은 토큰을 요청
* HTTP 요청: `PUT: {{base}}/members/update`
* 호출 예시: `{{base}}/members/update?latitude=3.141592&longitude=6.594989`
* 응답 예시: 200 OK

    ```json
    {
        "token": "eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZXBwaW5lc3MiLCJpYXQiOjE3MDI2MTkxNjMsImV4cCI6MTcwMjYyMDk2MywiYXV0aCI6IlVTRVIifQ.w45I-GOHjcEkaFWGwjLq4hXCsBeIvvMg_7ZTIGAQ7bY"
    }
    ```
</details>


#### 1-4. 사용자 상세 정보 요청
<details> <summary>클릭하여 상세 보기</summary>

* 인증된 사용자가 자신에 대한 상세 정보를 요청
* HTTP 요청: `GET: {{base}}/members/detail`
* 응답 예시: 200 OK

    ```json
    {
        "seq": 1,
        "name": "neppiness",
        "authority": "USER",
        "latitude": 3.141592,
        "longitude": 6.594989
    }
    ```
</details>

<br>

### 2. 지역(시/도, 시군구) 관련 기능
#### 2-1. 시군구 정보를 조회해서 시/도 정보를 조회
<details> <summary>클릭하여 상세 보기</summary>

* 인증된 사용자가 시군구 정보를 입력해 해당하는 시/도 목록을 요청
* HTTP 요청: `GET: {{base}}/regions/find`
* 호출 예시: `{{base}}/regions/find?sgg=동구`
* 응답 예시: 200 OK

    ```json
    [
        "광주시", "대구시", "대전시", "부산시", "울산시", "인천시"
    ]
    ```
</details>

    
#### 2-2. 시/도 정보를 통해서 시군구 정보를 조회
<details> <summary>클릭하여 상세 보기</summary>

* 인증된 사용자가 시/도 정보를 통해 해당하는 시군구 목록을 요청
* HTTP 요청: `GET: {{base}}/regions/find`
* 호출 예시: `{{base}}/regions/find?dosi=경기`
* 응답 예시: 200 OK

    ```json
    [
        "가평군", "강화군", "고양시", "과천시", "광명시", "광주시", "구리시", "군포시", "김포시", "남양주시",
        "동두천시", "부천시", "성남시", "수원시", "시흥시", "안산시", "안성시", "안양시", "양주시", "양평군",
        "여주군", "여주시", "연천군", "오산시", "옹진군", "용인시", "의왕시", "의정부시", "이천시", "파주시",
        "평택시", "포천시", "하남시", "화성시", "인천시"
    ]
    ```
</details>

<br>

### 3. 맛집 관련 기능
#### 3-1-i. 내 위치 기반 맛집 검색: 도보
<details> <summary>클릭하여 상세 보기</summary>

* 인증된 사용자가 도보를 통해 이동할 수 있는 맛집 목록을 요청
    - 도보: 내 주변 반경 1 km 이내에 위치한 맛집을 반환
* 결과는 거리 순으로 정렬
* HTTP 요청 : `GET: {{base}}/restaurants/my-location-based/search`
* 호출 예시: `{{base}}/restaurants/my-location-based/search?mobility=walk`
* 응답 예시: 200 OK
  
    ```json
    [
        {
            "name": "카페그라시아",
            "closeOrOpen": "영업",
            "typeOfFoods": "까페",
            "addressAsRoadName": "경기도 오산시 대원로 8-5, 제1,2동 1층 (원동)",
            "rating": 0.0,
            "distance": 0.0
        },
  
        (중략)
  
        {
            "name": "오산조은참치본점",
            "closeOrOpen": "영업",
            "typeOfFoods": "일식",
            "addressAsRoadName": "경기도 오산시 성호대로 123 (오산동,그린프라자 111,112호)",
            "rating": 0.0,
            "distance": 0.6078200704365677
        },
        {
            "name": "차이란",
            "closeOrOpen": "영업",
            "typeOfFoods": "중국식",
            "addressAsRoadName": "경기도 오산시 동부대로 516 (원동, 1층~3층)",
            "rating": 0.0,
            "distance": 0.8419067021153999
        }
    ]
    ```
</details> 

#### 3-1-ii. 내 위치 기반 맛집 검색: 대중교통
<details> <summary>클릭하여 상세 보기</summary>

* 인증된 사용자가 대중교통으로 이동할 수 있는 맛집 목록을 요청
    - 대중교통: 내 주변 반경 5 km 이내에 위치한 맛집을 반환
* 결과는 거리 순으로 정렬
* HTTP 요청 : `GET: {{base}}/restaurants/my-location-based/search`
* HTTP 호출 예시: `{{base}}/restaurants/my-location-based/search?mobility=transportation`
* 응답 예시: 200 OK

    ```json
    [
        {
            "name": "카페그라시아",
            "closeOrOpen": "영업",
            "typeOfFoods": "까페",
            "addressAsRoadName": "경기도 오산시 대원로 8-5, 제1,2동 1층 (원동)",
            "rating": 0,
            "distance": 0
        },
        {
            "name": "오산장재윤짬뽕",
            "closeOrOpen": "영업",
            "typeOfFoods": "중국식",
            "addressAsRoadName": "경기도 오산시 역광장로 46, 1층 (오산동)",
            "rating": 0,
            "distance": 0.2123805526871727
        },
  
        (중략)
  
        {
            "name": "참치세상",
            "closeOrOpen": "영업",
            "typeOfFoods": "일식",
            "addressAsRoadName": "경기도 오산시 수목원로468번길 6-3, 1층 106호 (금암동)",
            "rating": 0,
            "distance": 3.6715334606040795
        },
        {
            "name": "어바웃스토리",
            "closeOrOpen": "영업",
            "typeOfFoods": "까페",
            "addressAsRoadName": "경기도 오산시 세마역로19번길 6, 1층 (세교동)",
            "rating": 0,
            "distance": 4.5247272874290925
        }
    ] 
    ```
</details>
      
#### 3-2. 지역명 기반 검색
<details> <summary>클릭하여 상세 보기</summary>

* 인증된 사용자가 시/도 정보와 시군구 정보를 전달해 해당 지역 중심으로부터 10 km 이내에 위치한 맛집 목록을 요청
* HTTP 요청: `GET: {{base}}/restaurants/region-name-based/search`
* 호출 예시: `{{base}}/restaurants/region-name-based/search?dosi=경기&sgg=용인시`
* 응답 예시: 200 OK

    ```json
    [
        {
            "name": "대게좋은날",
            "closeOrOpen": "영업",
            "typeOfFoods": "일식",
            "addressAsRoadName": "경기도 용인시 처인구 중부대로 1360, 한솔빌딩 201호 (김량장동)",
            "rating": 0.0,
            "distance": 0.8010778101522742
        },
        {
            "name": "주문진회집",
            "closeOrOpen": "영업",
            "typeOfFoods": "일식",
            "addressAsRoadName": "경기도 용인시 처인구 금령로 163 (마평동)",
            "rating": 0.0,
            "distance": 0.9999897716924954
        },
      
        (중략)
      
        {
            "name": "참치연어집",
            "closeOrOpen": "영업",
            "typeOfFoods": "일식",
            "addressAsRoadName": "경기도 화성시 동탄순환대로29길 60, 강산프라자 3층 301호의 일부(7호)호 (영천동)",
            "rating": 0.0,
            "distance": 8.935618690831769
        },
        {
            "name": "레드(RED)",
            "closeOrOpen": "폐업",
            "typeOfFoods": "까페",
            "addressAsRoadName": "경기도 용인시 기흥구 구갈로60번길 19 (구갈동,금강프라자 108호)",
            "rating": 0.0,
            "distance": 9.643096009493654
        }
    ]
    ```
</details>

#### 3-3. 고급 검색 기능
<details> <summary>클릭하여 상세 보기</summary>

* 인증된 사용자가 위도, 경도, 반경 길이, 정렬 기준을 통해서 주변 맛집 목록을 요청
* HTTP 요청: `GET: {{base}}/restaurants/search`
* 호출 예시: `{{base}}/restaurantts/search?lat=37.2040&lon=127.07596&range=5&sort=거리순`
* 응답 예시: 200 OK

    ```json
    [
        {
            "name": "라본테이블&카페",
            "closeOrOpen": "영업",
            "typeOfFoods": "까페",
            "addressAsRoadName": "경기도 화성시 동탄반석로 172 (반송동, 동탄파라곤 201,202호)",
            "rating": 0.0,
            "distance": 0.3358702703531362
        },
        {
            "name": "윤이",
            "closeOrOpen": "영업",
            "typeOfFoods": "까페",
            "addressAsRoadName": "경기도 화성시 노작로4길 22-21, 1층 (반송동)",
            "rating": 0.0,
            "distance": 0.6081005574865153
        },

        (중략)

        {
            "name": "청어람기찬짬뽕",
            "closeOrOpen": "영업",
            "typeOfFoods": "중국식",
            "addressAsRoadName": "경기도 용인시 기흥구 공세로 83 (공세동, 1층)",
            "rating": 0.0,
            "distance": 4.279022046142842
        },
        {
            "name": "카페인(cafe人)",
            "closeOrOpen": "영업",
            "typeOfFoods": "까페",
            "addressAsRoadName": "경기도 오산시 은여울로43번길 13 (은계동, 104호)",
            "rating": 0.0,
            "distance": 4.959839520447254
        }
    ]
    ```
</details>

#### 3-4. 맛집 상세 정보 조회
<details> <summary>클릭하여 상세 보기</summary>

* 인증된 사용자가 맛집의 고유번호를 통해서 상세 정보를 요청
* HTTP 요청: `GET: {{base}}/restaurants/detail/{id}`
* 호출 예시: `{{base}}/restaurantts/detail/1`
* 응답 예시: 200 OK

    ```json
    {
        "city": "의정부시",
        "name": "금화성",
        "closeOrOpen": "영업",
        "typeOfFoods": "중국식",
        "addressAsLocationName": "경기도 의정부시 가능동 701-42 지상1층",
        "addressAsRoadName": "경기도 의정부시 호국로1135번길 3 (가능동,지상1층)",
        "numberOfReviews": 0,
        "rating": 0.0
    }
    ```
</details>

#### 3-5. 맛집 리뷰 조회
<details> <summary>클릭하여 상세 보기</summary>

* 인증된 사용자가 맛집의 고유번호를 통해서 맛집 리뷰 목록을 요청
* HTTP 요청: `GET: {{base}}/restaurants/reviews-of/{id}`
* 호출 예시: `{{base}}/restaurants/reviews-of/1`
* 응답 예시: 200 OK

    ```json
    [
        {
            "username": "neppiness",
            "rating": 5,
            "opinionInShort": "음식이 맛있어요!"
        },
        {
            "username": "neppiness",
            "rating": 4,
            "opinionInShort": "맛이 조금 변한 것 같네요ㅠ 그래도 꽤 괜찮았습니다"
        }
    ]
   ```
</details>

<br>

### 4. 리뷰 관련 기능
#### 4-1. 리뷰 작성
<details> <summary>클릭하여 상세 보기</summary>

* 인증된 사용자가 맛집에 대한 리뷰의 생성을 요청
* HTTP 요청: `POST: {{base}}/reviews`
* 호출 예시: `{{base}}/reviews?restaurant-id=1&rating=5&opinion=맛있게 잘 먹었습니다!`
* 응답 예시: 201 Created

    ```json
    {
        "seq": 1,
        "memberResponse": {
            "name": "neppiness",
            "authority": "USER",
            "latitude": 3.141592,
            "longitude": 6.594989
        },
        "restaurantDetailResponse": {
            "city": "의정부시",
            "name": "금화성",
            "closeOrOpen": "영업",
            "typeOfFoods": "중국식",
            "addressAsLocationName": "경기도 의정부시 가능동 701-42 지상1층",
            "addressAsRoadName": "경기도 의정부시 호국로1135번길 3 (가능동,지상1층)",
            "numberOfReviews": 1,
            "rating": 5
        },
        "rating": 5,
        "opinion": "맛있게 잘 먹었습니다!"
    }
    ```
</details>

#### 4-2. 리뷰 수정
<details> <summary>클릭하여 상세 보기</summary>

* 인증된 사용자가 자신이 작성한 리뷰에 대한 수정을 요청
* HTTP 요청: `PUT: {{base}}/reviews/{id}`
* 호출 예시: `{{base}}/reviews/1?restaurant-id=1&rating=4&opinion=꽤 괜찮았어요`
* 응답 예시: 201 Created

    ```json
    {
        "seq": 1,
        "memberResponse": {
            "name": "neppiness",
            "authority": "USER",
            "latitude": 3.141592,
            "longitude": 6.594989
        },
        "restaurantDetailResponse": {
            "city": "의정부시",
            "name": "금화성",
            "closeOrOpen": "영업",
            "typeOfFoods": "중국식",
            "addressAsLocationName": "경기도 의정부시 가능동 701-42 지상1층",
            "addressAsRoadName": "경기도 의정부시 호국로1135번길 3 (가능동,지상1층)",
            "numberOfReviews": 2,
            "rating": 4.5
        },
        "rating": 4,
        "opinion": "탕수육 그래도 괜찮았던 것 같아요!"
    }
    ```
    </details>

#### 4-3. 리뷰 삭제
<details> <summary>클릭하여 상세 보기</summary>

* 인증된 사용자가 자신이 작성한 리뷰 삭제를 요청
* HTTP 요청: `DELETE: {{base}}/reviews/{id}`
* 호출 예시: `{{base}}/reviews/1`
* 응답 예시: 204 No Content
</details>

___

## DB 설계
### 사용자(Member) 테이블
<details>
<summary>클릭하여 상세보기</summary>

| Name      | Type    | NotNull | Default | Primary Key | Foreign Key | Description |
|-----------|---------|---------|---------|-------------|-------------|-------------|
| seq       | BIGINT  | O       |         | O           |             | 고유번호        |
| name      | VARCHAR | O       |         |             |             | 사용자 이름(id)  |
| password  | VARCHAR | O       |         |             |             | 비밀번호        |
| authority | VARCHAR | O       | USER    |             |             | 권한          |
| latitude  | FLOAT   |         |         |             |             | 위도          |
| longitude | FLOAT   |         |         |             |             | 경도          |

</details>

### 맛집(Restaurant) 테이블
<details>
<summary>클릭하여 상세보기</summary>

| Name                     | Type    | NotNull | Default | Primary Key | Foreign Key | Description |
|--------------------------|---------|---------|---------|-------------|-------------|-------------|
| seq                      | BIGINT  | O       |         | O           |             | 고유번호        |
| city                     | VARCHAR | O       |         |             |             | 소속 시군구      |
| name                     | VARCHAR | O       |         |             |             | 이름          |
| close_or_open            | VARCHAR | O       |         |             |             | 영업/폐업       |
| type_of_foods            | VARCHAR | O       |         |             |             | 음식 종류       |
| address_as_location_name | VARCHAR | O       |         |             |             | 지명 주소       |
| address_as_road_name     | VARCHAR | O       |         |             |             | 도로명 주쇼      |
| latitude                 | FLOAT   | O       |         |             |             | 위도          |
| longitude                | FLOAT   | O       |         |             |             | 경도          |
| number_of_reviews        | BIGINT  | O       | 0       |             |             | 리뷰 수        |
| total_ratings            | BIGINT  | O       | 0       |             |             | 평점 총합       |
| rating                   | FLOAT   | O       | 0.0     |             |             | 평점          |
| reviews                  | FLOAT   | O       | 0.0     |             |             | 평점          |

</details>

### 리뷰(Review) 테이블
<details>
<summary>클릭하여 상세보기</summary>

| Name           | Type    | NotNull | Default | Primary Key | Foreign Key | Description |
|----------------|---------|---------|---------|-------------|-------------|-------------|
| seq            | BIGINT  | O       |         | O           |             | 고유번호        |
| member_seq     | BIGINT  | O       |         |             | O           | 유저 고유번호     |
| restaurant_seq | BIGINT  | O       |         |             | O           | 맛집 고유번호     |
| rating         | INTEGER | O       |         |             |             | 평점          |
| opinion        | VARCHAR | O       |         |             |             | 평가 상세 의견    |

</details>

___

## 학습 내용
### Custom Annotation
<details>
<summary>클릭하여 상세보기</summary>

* `@Retention(RetentionPolicy.RUNTIME)`
    - 어노테이트된 인터페이스가 얼마나 유지되는지 알려준다.
    - 만약 다른 값이 없으면 기본적으로 RetentionPolicy는 CLASS로 지정된다.
    - 본 서비스의 경우 서버가 동작되는 RUNTIME에도 인터페이스가 유지되어야 하므로 RUNTIME으로 지정하였다.

* `@Target(ElementType.METHOD)`
    - 타겟 어노테이션이 적용된 인터페이스가 어디에 적용될지에 대해 알려준다.
    - ElementType은 아래와 같은 enum들이 있다
        + TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, ANNOTATION_TYPE, PACKAGE 등
    - 우리가 구현하려는 Principal 어노테이션은 컨트롤러의 인자에 적용할 것이므로 `ElementType.PARAMETER`가 사용된다.

* 위와 같은 설정을 통해 Principal 어노테이션을 구현할 수 있었다.

```java
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Principal {
}
```

* 이를 인식시키기 위해선 WebMvcConfigurer 인터페이스를 구현해야 함.
    - Config으로 구현한 클래스에 Configuration, EnableWebMvc 어노테이션을 추가.
    - addArgumentResolvers 메소드를 통해 우리가 임의로 만든 ArgumentResolver를 추가할 수 있음.

```java
@RequiredArgsConstructor
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    private final PrincipalArgumentResolver principalArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(principalArgumentResolver);
    }

}
```

* 최종적으로 컨트롤러 메소드는 아래와 같이 구현됨.
```java
public class MemberController {
    
    // 컨트롤러 관련 인자들
    
    @GetMapping(path = "/detail")
    public ResponseEntity<MemberDetailResponse> getDetail(@Principal PrincipalDto principal) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberService.getDetail(principal));
    }
    
}
```
</details>

### HandlerMethodArgumentResolver
<details>
<summary>클릭하여 상세보기</summary>

* Principal 어노테이션이 붙은 인자를 가진 컨트롤러에 사전 정보를 정리해 인자로 넘겨주고자 할 때 활용
* boolean을 반환하는 supportsParameter 메소드를 구현
    - 이 HandlerArgumentResolver가 적용될 인자에 대해 true를 반환
    - 이 HandlerArgumentResolver가 적용되지 않을 인자에 대해선 false를 반환함
* 이를 아래와 같이 구현함.

```java
public class PrincipalArgumentResolver implements HandlerMethodArgumentResolver {

    // PrincipalArgumentResolver의 필드

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(PrincipalDto.class) &&
                parameter.hasParameterAnnotation(Principal.class);
    }
    
}
```

* 이로써 PrincipalDto라는 타입을 가지면서 Principal이라는 어노테이션을 가진 메소드에 대해서 이 Resolver가 동작함.
    - 그 동작 로직은 Object resolverArgument를 통해 구현함.

```java

public class PrincipalArgumentResolver implements HandlerMethodArgumentResolver {

    // PrincipalArgumentResolver의 필드

    // supportsParameter 메소드
    
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String authorizationString = request.getHeader("Authorization");
        if (authorizationString == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        String[] splitAuthorizationString = authorizationString.split(" ");
        String token = splitAuthorizationString[1];
        return authTokenService.parseToken(token);
    }
    
}
```

* 아래와 같은 세 동작을 수행함.
    1. 요청 헤더에 접근이 가능해짐
    2. Authorization 헤더에서 토큰을 가져와 파싱
    3. 파싱한 정보를 PrincipalDto 형태로 컨트롤러에 전달함.

</details>

### Scheduler
<details>
<summary>클릭하여 상세보기</summary>

* 동작시킬 어플리케이션의 클래스에 EnableScheduling 어노테이션을 붙여서 스케쥴러를 인식시킬 수 있음.
* 이후, 스케쥴링할 메소드에 Scheduled 어노테이션을 추가하고, cron expression으로 동작 주기를 설정할 수 있음.
    - 이를 통해 스케쥴링한 메소드는 아래와 같음.

```java
public class Scheduler {

    // Scheduler 클래스 필드
    
    @Scheduled(cron = "*/10 * * * * *")
    public void collect() throws IOException, JAXBException {
        collect(ChineseRestaurants.class, chineseRestaurantUrl + keyUrl);
        collect(JapaneseRestaurants.class, japaneseRestaurantUrl + keyUrl);
        collect(Cafes.class, cafeUrl + keyUrl);
    }

}
```
</details>

### Cron expression
<details>
<summary>클릭하여 상세보기</summary>

* 크론 익스프레션(cron expression)은 CronTrigger의 인스턴스들을 설정하는 데 활용됨.
* 아래와 같은 여섯개 표현으로 동작 주기를 설정함.

```
 ┌───────────── second (0-59)
 │ ┌───────────── minute (0 - 59)
 │ │ ┌───────────── hour (0 - 23)
 │ │ │ ┌───────────── day of the month (1 - 31)
 │ │ │ │ ┌───────────── month (1 - 12) (or JAN-DEC)
 │ │ │ │ │ ┌───────────── day of the week (0 - 7)
 │ │ │ │ │ │          (0 or 7 is Sunday, or MON-SUN)
 │ │ │ │ │ │
 * * * * * *
```

* 특수문자들
    1. `,`: value list separator, 한 항목에 대한 값들을 나열할 때 활용
    2. `-`: range of values, 한 항목에 대한 범위를 지정
    3. `*`: any value, 어떤 값이든 해당.
    4. `/`: step value, 얼마 간격으로 수행할 것인지 표현.
    5. `?`: any value가 따로 있는데 `?`가 있는 이유는 뭔지 잘 모르겠음.

* `?` 표시는 day of month나 day of week에서 * 대신에 쓸 수 있는 값이라고 함.
    - 기본적으로는 같은 표현이라 볼 수 있음.

* 참고자료: [Class CronExpression](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/support/CronExpression.html)
</details>

### JAXB
<details>
<summary>클릭하여 상세보기</summary>

* xml -> object, object -> xml 변환 시 활용되는 라이브러리
    - Marshall: object -> xml 변환
    - Unmarshall: xml -> object 변환
* JAXBContext를 설정하여 원하는 클래스의 인스턴스로 xml 파일을 파싱할 수 있음.
    - 해당 클래스에는 XmlElement 어노테이션으로 xml 파일의 어떤 태그값이 어떤 필드에 매칭되어야 할지 설정함.
* 가장 추상화된 데이터 파서는 아래와 같이 작성됨.

```java
@Component
public class RestaurantsDataParser {

    public RestaurantsData parse(Class<?> restaurantsDataClass, InputStream inputStream) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(restaurantsDataClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (RestaurantsData) unmarshaller.unmarshal(inputStream);
    }

}
```

* JAXBContext를 특정 클래스로 설정. 
    - JABXContext로부터 Unmarshaller를 생서
    - Unmarshaller에 InputStream을 넣고 unmarshall 수행.

</details>

___

## 기타 링크
### [matitnyam | Postman API Network](https://www.postman.com/spacecraft-technologist-86595620/workspace/matitnyam)
* Members, Regions, Restaurants, Reviews 콜렉션으로 구분
* 상술한 기능들에 대한 HTTP 요청 테스트 용도로 활용

___

## 저자
* <a href="https://github.com/neppiness">김정현(neppiness)</a> (클릭 시 깃허브로 이동)