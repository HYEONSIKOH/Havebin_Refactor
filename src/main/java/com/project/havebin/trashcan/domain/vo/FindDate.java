package com.project.havebin.trashcan.domain.vo;

import java.util.Date;

public record FindDate(String value) {
    // 기본 생성자: 현재 날짜/시간 자동 생성
    public FindDate() {
        this(formatDate(new Date()));
    }

    // YOLO로 탐색했을 경우, 날짜를 고정
    public FindDate(boolean isYolo) {
        this(isYolo ? "240326 00:00:00" : formatDate(new Date()));
    }

    private static String formatDate(Date date) {
        return String.format("%ty%tm%td %tH:%tM:%tS", date, date, date, date, date, date);
    }
}
