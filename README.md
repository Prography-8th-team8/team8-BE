<img src="https://github.com/Prography-8th-team8/team8-BE/assets/43109589/a2d04c49-a351-48b0-9df6-b979ffbb81df" width="300" height="300"/>

# 케이크크

## 프로젝트 설명

위치기반 케이크샵 지도 어플로, 원하는 케이크샵을 확인하고 상세정보를 확인할 수 있습니다.

(현재 서울지역에서 서비스 중)

## 기술스택

**`Backend`**: **Java, Spring Boot**

**`Database`**: **queryDSL, JPA, PostgreSQL**

**`CI/CD`**: **Docker, Jenkins, Github actions**

**`AWS`**: **EC2, RDS, S3**

**`Test`**: **K6, Grafana, Prometheus**

**`외부API`** : **Naver 서비스 API**

**`Etc`**: **Swagger**

## 개발 내용

1. admin 케이크샵 이미지 업로드 
2. admin 케이크샵 카테고리 업로드
3. 케이크샵 리스트 조회
    1. 지역별로 조회
    2. 이 지역 재검색
4. 케이크샵 상세 조회
5. 케이크샵 카테고리 조회
6. 케이크샵 관련 네이버 블로그 조회
7. 케이크샵 관련 네이버 가게 정보 조회
8. 랜덤 케이크 사진으로 피드 조회

## ERD

<img width="941" alt="pic1" src="https://github.com/Prography-8th-team8/team8-BE/assets/43109589/3b9c7c92-8f9a-4902-9993-3f8f8752b7e2">


## 시스템 아키텍처

<img width="639" alt="pic2" src="https://github.com/Prography-8th-team8/team8-BE/assets/43109589/e3e16e9e-48b4-454a-a3ab-1c22ae9e30ff">

## Github Flow

![pic3](https://github.com/Prography-8th-team8/team8-BE/assets/43109589/a4fbc440-aa5a-421f-8d84-b202481f2857)


## Commit Convention

### Structure

feat(store): 스토어 필터링 기능 추가 #52

### Type

- feat : 기능 구현 시
- fix : 구현된 기능 관련 오류 수정 시
- style : 코드 포맷 변경
- chore : 환경 설정 시
- docs : 문서 작업 시
- test: 테스트 코드 관련 작업 시

### How to use

1. Commit Type 명시
2. 관련 domain 명시
3. 개발 내용 명료하게 서술
4. 관련 Issue 번호 명시

## Swagger

![pic4](https://github.com/Prography-8th-team8/team8-BE/assets/43109589/327292b1-4f32-479e-8d78-d22e848e3352)
