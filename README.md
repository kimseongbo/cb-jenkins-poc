# cb-jenkins-poc

Jenkins CI에서 실행된 단위 테스트(JUnit) 결과를 Codebeamer(ALM)에 자동 업로드하는 **PoC(Proof of Concept)** 프로젝트입니다.
테스트 실행 → XML 생성 → Codebeamer Test Run 업데이트까지의 전체 연계 플로우를 검증하기 위해 제작되었습니다.

## 📌 프로젝트 목적

- Jenkins에서 `gradlew test` 실행  
- JUnit XML 테스트 결과 자동 생성  
- Python 스크립트가 XML을 파싱  
- Codebeamer API로 Test Run 업데이트  
- PASS/FAIL 기록 자동 반영  

CI·ALM 통합 환경에서 사용할 수 있는 최소 자동화 파이프라인을 만드는 것이 목표입니다.

## 📂 프로젝트 구조

cb-jenkins-poc/
 ├── build.gradle
 ├── settings.gradle
 ├── gradlew / gradlew.bat
 ├── gradle/
 ├── src/
 │   ├── main/java/
 │   │   ├── BatteryService.java
 │   │   └── ThermalService.java
 │   └── test/java/
 │       ├── BatteryServiceTest.java
 │       └── ThermalServiceTest.java
 └── Jenkinsfile (예시)

## ⚙️ 실행 방법

### 1. Gradle Wrapper 생성 (최초 1회)
gradle wrapper

### 2. 테스트 실행
Windows:  
gradlew.bat test

macOS / Linux:  
./gradlew test

### 3. JUnit XML 출력 경로  
build/test-results/junit/

이 XML 파일들을 Jenkins 및 Codebeamer 연동에 사용합니다.

## 🚀 병렬 테스트 실행

본 프로젝트는 **JUnit5 + Gradle** 병렬 실행을 지원합니다.

- JVM 내부 테스트 병렬 실행  
- Gradle Test Executor 병렬 실행  
- CPU 코어 수에 따라 테스트 실행 시간이 크게 단축됨  

## 🧪 단위 테스트 구성

### BatteryServiceTest
- 20개 테스트  
- 전부 PASS

### ThermalServiceTest
- 20개 테스트  
- FAIL intentionally 포함 (5개)  
- Codebeamer FAIL 처리 검증용

## 🔗 Jenkins 연동 예시

pipeline {
    agent any

    stages {
        stage('Run Tests') {
            steps {
                bat 'gradlew.bat test'
                junit 'build/test-results/junit/*.xml'
            }
        }

        stage('Upload to Codebeamer') {
            steps {
                bat 'python upload-to-codebeamer.py'
            }
        }
    }
}

## 🔌 Codebeamer 연동 요약

1. JUnit XML 파싱  
2. 클래스명.테스트메서드 기반 automationKey 생성  
3. Codebeamer Test Case 조회  
4. Test Run 생성 또는 기존 Test Run 업데이트  
5. PASS/FAIL 기록 반영  

## 🏁 PoC 성과

- 테스트 자동 실행 검증  
- 병렬 처리 성능 검증  
- XML 생성 정상  
- Codebeamer Test Run 자동 업데이트 성공  
- PASS/FAIL 혼합 처리 검증  

## 🔮 향후 확장 방향

- 테스트케이스 1,000개 이상 확장  
- Codebeamer Bulk TestResult API 적용  
- Jenkins 빌드 번호 기반 Test Run 자동 생성  
- 알림·리포트 자동화 고도화  

## 📄 License

This project is for internal PoC and evaluation purposes only.
