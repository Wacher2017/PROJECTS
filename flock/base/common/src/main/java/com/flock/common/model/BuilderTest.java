package com.flock.common.model;

import java.util.*;

/**
 * @author Chunming_Wang
 */
public class BuilderTest {

    private String name;
    private int age;
    private int bust;
    private int waist;
    private int hips;
    private List<String> hobby;
    private String birthday;
    private String address;
    private String mobile;
    private String email;
    private String hairColor;
    private Map<String, String> gift;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBust() {
        return bust;
    }

    public void setBust(int bust) {
        this.bust = bust;
    }

    public int getWaist() {
        return waist;
    }

    public void setWaist(int waist) {
        this.waist = waist;
    }

    public int getHips() {
        return hips;
    }

    public void setHips(int hips) {
        this.hips = hips;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public Map<String, String> getGift() {
        return gift;
    }

    public void setGift(Map<String, String> gift) {
        this.gift = gift;
    }

    @Override
    public String toString() {
        return "BuilderTest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", bust=" + bust +
                ", waist=" + waist +
                ", hips=" + hips +
                ", hobby=" + hobby +
                ", birthday='" + birthday + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", hairColor='" + hairColor + '\'' +
                ", gift=" + gift +
                '}';
    }

    // 为了演示方便，加几个聚合方法
    public void addHobby(String hobby) {
        this.hobby = Optional.ofNullable(this.hobby).orElse(new ArrayList<>());
        this.hobby.add(hobby);
    }

    public void addGift(String day, String gift) {
        this.gift = Optional.ofNullable(this.gift).orElse(new HashMap<>());
        this.gift.put(day, gift);
    }

    public void setVitalStatistics(int bust, int waist, int hips) {
        this.bust = bust;
        this.waist = waist;
        this.hips = hips;
    }

    public static void main(String[] args) {
        BuilderTest test = Builder.of(BuilderTest::new)
                .with(BuilderTest::setName, "小美")
                .with(BuilderTest::setAge, 18)
                .with(BuilderTest::setVitalStatistics, 33, 23, 33)
                .with(BuilderTest::setBirthday, "2001-10-26")
                .with(BuilderTest::setAddress, "上海浦东")
                .with(BuilderTest::setMobile, "18688888888")
                .with(BuilderTest::setEmail, "pretty-xiaomei@qq.com")
                .with(BuilderTest::setHairColor, "浅棕色带点微卷")
                .with(BuilderTest::addHobby, "逛街")
                .with(BuilderTest::addHobby, "购物")
                .with(BuilderTest::addHobby, "买东西")
                .with(BuilderTest::addGift, "情人节礼物", "LBR 1912女王时代")
                .with(BuilderTest::addGift, "生日礼物", "迪奥烈焰蓝金")
                .with(BuilderTest::addGift, "纪念日礼物", "阿玛尼红管唇釉")
                // 等等等等 ...
                .build();
        System.out.println(test);
    }
}
