1. 민감한 정보의 기입을 막기 위해 secrets.properties에서 민감한 값들은 주석처리하고, application.properties에서 참조하도록 변경하였음.
2. firebase.json은 .gitIgnore에 추가해두었으니, api 키라던가 그런 정보들은 필요한 사람들이 알아서 발급받고 채워넣길 바람

DB 설정정보 secrets에 넣어두고, properties 클래스에서 등록 설정

swagger : http://localhost:20001/swagger-ui/index.html
