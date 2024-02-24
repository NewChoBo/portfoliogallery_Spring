1. 민감한 정보의 기입을 막기 위해 secrets.properties에서 민감한 값들은 주석처리하고, application.properties에서 참조하도록 변경하였음.
2. firebase.json은 .gitIgnore에 추가해두었으니, api 키라던가 그런 정보들은 필요한 사람들이 알아서 발급받고 채워넣길 바람

DB 설정정보 secrets에 넣어두고, properties 클래스에서 등록 설정

swagger : http://localhost:20001/swagger-ui/index.html

## Jenkins 파이프라인 구성

이 프로젝트는 지속적인 통합 및 배포를 위해 Jenkins 파이프라인으로 설정되어 있습니다. 파이프라인에는 GitHub 저장소를 복제하는 단계, 구성 및 비밀 파일을 설정하는
단계, 애플리케이션을 빌드하는 단계, 배포하는 단계가 포함됩니다.

Jenkins 파이프라인 단계 개요:

- **GitHub 복제**: 지정된 자격 증명을 사용하여 저장소를 복제합니다.
- **구성 및 비밀 파일 설정**: Jenkins 자격 증명에서 필요한 구성 및 비밀 파일을 설정합니다.
- **빌드**: 실행 가능한 JAR 파일로 스프링 부트 애플리케이션을 빌드합니다.
- **배포**: 지정된 포트에 애플리케이션을 배포하며, 포트 충돌을 처리하는 로직을 포함합니다.

## 배포

Jenkins 파이프라인의 최종 단계는 라즈베리 파이 서버에 애플리케이션을 배포합니다. 지정된 포트가 사용 가능한지 확인하고, 그 포트를 차지하고 있는 프로세스를 중지하기 전에
배포를 진행합니다.

```pipeline
pipeline {
agent any

    stages {
        stage('github Clone') {
            steps {
               git credentialsId: 'NewChoBo', url: 'https://github.com/NewChoBo/portfoliogallery_Spring.git'
            }
        }
        stage('Setup Config & Secret Files') {
            steps {
                script {
                    withCredentials([
                        file(credentialsId: 'PortfolioGallery_Spring_firebase.json', variable: 'FIREBASE_FILE'),
                        file(credentialsId: 'PortfolioGallery_Spring_secrets.properties', variable: 'SECRETS_FILE')
                    ]) {
                        sh \'''
                            rm -f ./firebase.json
                            rm -f ./src/main/resources/secrets.properties
                            cp "$FIREBASE_FILE" ./firebase.json
                            cp "$SECRETS_FILE" ./src/main/resources/secrets.properties
                        \'''
                    }
                }
            }
        }
        stage('build') {
            steps {
               sh \''' 
                echo 'start bootJar' 
                chmod +x gradlew
                ./gradlew clean bootJar
                \'''
            }
        }
        stage('Deploy') {
            steps {
                script {
                    def port = 20001 // 배포할 포트 번호
                    def isPortOccupied = sh(script: "nc -z localhost ${port}", returnStatus: true) == 0
                    if (isPortOccupied) {
                        echo "Port ${port} is already in use. Trying to stop the process..."
                        sh "fuser -k ${port}/tcp"
                        sleep 5
                    }
                    echo 'Deploying to Raspberry Pi'
                    sh 'java -jar ./build/libs/portfoliogallery*.jar'
                }
            }
        }
    }

}
```

