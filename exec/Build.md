
## 💻 사용 도구 및 버전
<img src="https://img.shields.io/badge/vue-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white">
<img src="https://img.shields.io/badge/node.js-339933?style=for-the-badge&logo=Node.js&logoColor=white">
<img src="https://img.shields.io/badge/npm-CB3837?style=for-the-badge&logo=npm&logoColor=white">
<img src="https://img.shields.io/badge/IntelliJ-000000?style=for-the-badge&logo=IntelliJ&logoColor=white">
<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
<br/>
<img src="https://img.shields.io/badge/nginx-009639?style=for-the-badge&logo=nginx&logoColor=white">
<img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white">
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">
<img src="https://img.shields.io/badge/Jira-0052CC?style=for-the-badge&logo=Jira&logoColor=white">
<img src="https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=Jenkins&logoColor=white">
<br/>

- vue@3.0.11
- openjdk version "1.8.0_192" <br/>
  OpenJDK Runtime Environment (Zulu 8.33.0.1-win64) (build 1.8.0_192-b01) <br/>
  OpenJDK 64-Bit Server VM (Zulu 8.33.0.1-win64) (build 25.192-b01, mixed mode)
- Nginx WEB Server, Apache Tomcat
- mysql  Ver 14.14 Distrib 5.7.35, for Win64 (x86_64)
- node.js v14.17.0
- npm     6.14.13
- IntelliJ gradle-6.7
- nginx/1.18.0 (Ubuntu)

 ## 🔨 빌드 환경 변수
- git clone : <a>https://lab.ssafy.com/s06-webmobile1-sub2/S06P12E207.git
- Mysql 설정: application.properties에 DB 설정 추가
- 프로트엔드: VSCode에 npm install,npm run serve 하여 실행
- 백엔드: IntelliJ에 import 하여 실행

 ## 🚢 배포
- AWS EC2 이용하여 배포
- Mysql 설치
- Nginx 설치
- conf 파일 설정
  - /home/ubuntu/S06P12E207/frontend/nginx/nginx.conf
```
server {
        listen 80 default_server;
        listen [::]:80 default_server;

        server_name i6e207.p.ssafy.io;

        location / {
                 alias /usr/share/nginx/html;
                 try_files $uri $uri/ /index.html;
                 return 301 https://$server_name$request_uri;
        }

        index index.html;
}

server {
        listen 443 ssl;
        listen [::]:443 ssl;

        server_name i6e207.p.ssafy.io;

        ssl_certificate /var/www/html/fullchain.pem;
        ssl_certificate_key /var/www/html/privkey.pem;

        #root /usr/share/nginx/html;
        #index index.html;

        location / {
                try_files $uri $uri/ /index.html;
                proxy_connect_timeout 300s;
                proxy_read_timeout 600s;
                proxy_send_timeout 600s;
                proxy_buffers 8 16k;
                proxy_buffer_size 32k;

                        root /usr/share/nginx/html;
                        index index.html;

                proxy_hide_header Access-Controller-Allow-Origin;
                add_header 'Access-Control-Allow-Origin' '*';

                proxy_http_version 1.1;
                proxy_ssl_server_name on;
	    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
                  proxy_set_header X-Real-IP $remote_addr;
                  proxy_set_header Host $http_host;
          }
 
          location /api/v1 {
                  proxy_http_version 1.1;
                  proxy_pass https://i6e207.p.ssafy.io:8443;
                  charset utf-8;
 
                  proxy_set_header Host $http_host;
                  proxy_set_header X-Real-IP $remote_addr;
                  proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
                  proxy_set_header X-Forwarded-Proto $scheme;
                  proxy_set_header X-Forwarded-Host $host;
                  proxy_set_header X-Forwarded-Port $server_port;
          }
 
          location ~* /openvidu
          {
                  proxy_set_header X-Real-IP $remote_addr;
                  proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
                  proxy_set_header Host $http_host;
                  proxy_set_header X-NginX-Proxy false;
                  proxy_pass https://i6e207.p.ssafy.io:4443;
                  proxy_redirect off;
                  proxy_http_version 1.1;
                  proxy_set_header Upgrade $http_upgrade;
                  proxy_set_header Connection "upgrade";
          }
 }
```
- git lab, jenkins 연동완료
- jenkins에서 지정한 타켓 브랜치로 git push가 이루어질 경우 git clone, gradle build, docker image 생성

## 📃 DB 접속 정보
- Driver = com.mysql.cj.jdbc.Driver
- ConnectionURL = jdbc:mysql://i6e207.p.ssafy.io:3306/ssafy_web_db?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true
- Username = E207dhdlfskasu
- Password = aemvLK7XruldNEc7r56msV2

### AWS S3

- ACCESS KEY는 제출 1주 후 파기 예정