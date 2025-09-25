package com.example.shift_management;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // データベースのテーブルを示す
public class DbEntity {
    @Id //ユーザーを区別するためのID
    @GeneratedValue(strategy = GenerationType.AUTO) //IDの自動生成
    private long id;
    private String personName;
    private String shiftDate;
    private String shiftTime;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPersonName() {
        return this.personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getShiftDate() {
        return this.shiftDate;
    }

    public void setShiftDate(String shiftDate) {
        this.shiftDate = shiftDate;
    }

    public String getShiftTime() {
        return this.shiftTime;
    }

    public void setShiftTime(String shiftTime) {
        this.shiftTime = shiftTime;
    }
}
