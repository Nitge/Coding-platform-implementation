#!/bin/bash

LOGFILE="/home/yousunmok/grade.log"

# 현재 시간 로그에 기록
echo "[$(date)] Starting grade.sh" >> $LOGFILE

ANSWER_FILE="/home/yousunmok/answer.txt"
SUBMISSION_DIR="/home/yousunmok/submissions"

for file in $SUBMISSION_DIR/*.py; do
    id=$(basename "$file" .py)
    stdout_file="$SUBMISSION_DIR/${id}.stdout"
    stderr_file="$SUBMISSION_DIR/${id}.stderr"

    # 코드를 실행하고 STDOUT, STDERR를 파일에 저장
    python3 "$file" > "$stdout_file" 2> "$stderr_file"

    # 정답 파일과 비교
    if diff -q "$stdout_file" "$ANSWER_FILE" > /dev/null; then
        status="CORRECT"
    else
        status="INCORRECT"
    fi

    # STDERR가 비어있지 않으면 ERROR로 설정
    if [ -s "$stderr_file" ]; then
        status="ERROR"
    fi

    # API 서버에 결과 전송
    curl -X PATCH -H "Content-Type: application/json" -d "{\"id\": $id, \"status\": \"$status\"}" http://192.168.0.67:8080/manage/submission

    # 테스트 출력을 위해 echo 사용
    # echo "ID: $id, Status: $status"
done

