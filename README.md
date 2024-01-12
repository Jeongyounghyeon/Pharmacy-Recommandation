## Pharmacy-Recommendation

### 프로젝트 목적

---
 fastcampus 온라인 웹개발 프로젝트를 학습하고 배포까지 진행해본다. 
 강의의 내용과 다르게 Spring 버전을 최신 버전으로 하여 발생하는 문제를 스스로 해결하고, 
 api와 docker, aws에 서버를 올리기까지의 대한 과정을 이해하고 기록하기 위함


### 요구사항 분석

---
- 약국 찾기 서비스 요구사항
  - 약국 현황 데이터(공공 데이터)를 관리하고 있다 라고 가정하고, 약국 현황 데이터는 위도 경도의 위치 정보 데이터를 가지고 있다.
  - 해당 서비스로 주소 정보를 입력하여 요청하면 위치 기준에서 가까운 약국 3 곳을 추출 한다.
  - 주소는 도로명 주소 또는 지번을 입력하여 요청 받는다.
    - 정확한 주소를 입력 받기 위해 [Kakao 우편번호 서비스](https://postcode.map.daum.net/guide) 사용
  - 주소는 정확한 상세 주소(동, 호수)를 제외한 주소 정보를 이용하여 추천 한다.
    - ex) 서울 성북구 종암로 10길
  - 입력 받은 주소를 위도, 경도로 변환 하여 기존 약국 데이터와 비교 및 가까운 약국을 찾는다.
    - 지구는 평면이 아니기 때문에, 구면에서 두 점 사이의 최단 거리 구하는 공식이 필요
    - 두 위 경도 좌표 사이의 거리를 [haversine formula](https://en.wikipedia.org/wiki/Haversine_formula)로 계산
    - 지구가 완전한 구형이 아니 므로 아주 조금의 오차가 있다.
  - 입력한 주소 정보에서 정해진 반경(10km) 내에 있는 약국만 추천 한다.
  - 추출한 약국 데이터는 길안내 URL 및 로드뷰 URL로 제공 한다.
    - ex)    
      길안내 URL : https://map.kakao.com/link/map/우리회사,37.402056,127.108212    
      로드뷰 URL : https://map.kakao.com/link/roadview/37.402056,127.108212

  - 길안내 URL은 고객에게 제공 되기 때문에 가독성을 위해 shorten url로 제공 한다.
  - shorten url에 사용 되는 key값은 인코딩하여 제공 한다.
    - ex) http://localhost:8080/dir/nqxtX
    - base62를 통한 인코딩
  - shorten url의 유효 기간은 30일로 제한 한다.

### Tech Stack

--- 
 - JDK 17
 - Spring Boot 3.1.7
 - Spring Data JPA
 - Gradle
 - thymeleaf (기존 강의에 있는 `Handlebars` 대체)
 - Lombok
 - Github
 - Docker
 - AWS EC2
 - Redis
 - MariaDB
 - Spock
 - Testcontainers

### Result

---
![Pharmacy-Recommendation_result](img%2FPharmacy-Recommendation_result.png)