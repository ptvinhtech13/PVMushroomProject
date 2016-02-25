package com.example.kevin.pvmushroom.Object;

/**
 * Created by kevin on 1/26/2016.
 */
public class Khach_Hang {
    int ID;
    String HoTen;

    public Khach_Hang(int ID, String hoTen) {
        this.ID = ID;
        HoTen = hoTen;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
