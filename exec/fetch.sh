#!/bin/bash

# API 서버의 주소
SERVER_URL="http://192.168.0.67:8080/manage/new"

# 로그 파일 경로
LOGFILE="/home/yousunmok/fetch.log"

# 현재 시간 로그에 기록
echo "[$(date)] Starting fetch.sh" >> $LOGFILE

# 요청에 필요한 매개변수 설정
HOST="172.31.239.152"   # FTP 서버의 호스트명 또는 IP 주소
PORT=21                  # FTP 포트 (일반적으로 21번 사용)
USERNAME=""       # FTP 사용자 이름
PASSWORD=""       # FTP 비밀번호

# curl 명령을 사용하여 GET 요청을 보내고 결과로 파일 ID를 받습니다
response=$(curl -G -X GET "$SERVER_URL" \
  --data-urlencode "host=$HOST" \
  --data-urlencode "port=$PORT" \
  --data-urlencode "username=$USERNAME" \
  --data-urlencode "password=$PASSWORD")

# 응답 결과에서 파일 ID 추출 (JSON 형식으로 가정)
file_id=$(echo $response | jq -r '.')

# 저장할 파일 경로 설정
output_file="/path/to/save/code/$file_id.py"

# 파일 다운로드
ftp -inv $HOST <<EOF
user $USERNAME $PASSWORD
cd / # 파일이 업로드된 디렉토리로 이동
get $file_id.py $output_file
bye
EOF

# 응답 결과 출력 (디버깅을 위해 필요시 추가)
echo "New submission fetched and saved to $output_file"

