1. 민감한 정보의 기입을 막기 위해 secrets.properties에서 민감한 값들은 주석처리하고, application.properties에서 참조하도록 변경하였음.
2. firebase.json은 .gitIgnore에 추가해두었으니, api 키라던가 그런 정보들은 필요한 사람들이 알아서 발급받고 채워넣길 바람

DB 설정정보 secrets에 넣어두고, properties 클래스에서 등록 설정

swagger : http://localhost:20001/swagger-ui/index.html

Jenkins pipeline
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
                        sh '''
                            rm -f ./firebase.json
                            rm -f ./src/main/resources/secrets.properties
                            cp "$FIREBASE_FILE" ./firebase.json
                            cp "$SECRETS_FILE" ./src/main/resources/secrets.properties
                        '''
                    }
                }
            }
        }
        stage('build') {
            steps {
               sh ''' 
                echo 'start bootJar' 
                chmod +x gradlew
                ./gradlew clean bootJar
                '''
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
