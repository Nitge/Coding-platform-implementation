#!/bin/bash

# 관리 서버의 주소와 포트
MANAGE_SERVER="http://localhost:8080/manage/submission"

curl -s $MANAGE_SERVER
