package com.project.havebin.trashcan.domain.vo;

import lombok.Getter;

import java.util.Date;

@Getter
public class FindDate {
    private final String value;

    public FindDate() {
        Date date = new Date();
        this.value = String.format("%ty%tm%td %tH:%tM:%tS", date, date, date, date, date, date);
    }

    public FindDate(String value) {
        this.value = value;
    }

    // YOLO로 탐색했을 경우, 날짜를 스태틱으로 고정
    public FindDate(boolean isYolo) {
        if (isYolo) { this.value = "240326 00:00:00"; }
        else {
            Date date = new Date();
            this.value = String.format("%ty%tm%td %tH:%tM:%tS", date, date, date, date, date, date);
        }
    }
}
