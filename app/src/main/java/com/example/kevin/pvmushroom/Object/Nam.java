package com.example.kevin.pvmushroom.Object;

import java.util.ArrayList;

/**
 * Created by kevin on 1/29/2016.
 */
public class Nam {
    int IDNam;
    ArrayList<Vu_Mua> ds_vumua;



    public Nam(int IDNam, ArrayList<Vu_Mua> ds_vumua) {
        this.IDNam = IDNam;
        this.ds_vumua = new ArrayList<>();
        if(ds_vumua != null)
        {
            this.ds_vumua = ds_vumua;
        }
    }

    public void addVuMua(Vu_Mua vu_mua)
    {
        ds_vumua.add(vu_mua);
    }

    public int getIDNam() {
        return IDNam;
    }

    public void setIDNam(int IDNam) {
        this.IDNam = IDNam;
    }

    public ArrayList<Vu_Mua> getDs_vumua() {
        return ds_vumua;
    }

    public void setDs_vumua(ArrayList<Vu_Mua> ds_vumua) {
        this.ds_vumua = ds_vumua;
    }


}
