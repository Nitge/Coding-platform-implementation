#!/bin/bash

# 관리 서버의 주소와 포트
MANAGE_SERVER="http://localhost:8080/manage/submission"

# HTTP GET 요청을 보내는 curl 명령어
curl -s $MANAGE_SERVER  # -s 옵션으로 silent 모드로 출력을 제한
