package kr.heythisway.contacts.domain;

/** 주소록 데이터 클래스 - POJO (Pure Old Java Object)
 * Created by SMARTHINK_MBL13 on 2017. 6. 1..
 */
// 데이터베이스 중심 설계
public class Data {
    private int id;
    private String name;
    private String tel;
    private String address;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
