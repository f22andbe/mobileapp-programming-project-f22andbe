package com.example.project;


public class AminoAcid {

    /* these are the three attributes from our json data outside
     * of auxdata that we collect
     */

    String ID;
    String name;
    String type; //this is Login

    /* here is where we collect the attributes that are inside
     * auxdata:{...} in our json data
     */
    WikiData auxdata;

    public AminoAcid(String ID, String name, String type, WikiData auxdata) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.auxdata = auxdata;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public WikiData getAuxdata() {
        return auxdata;
    }

    public void setAuxdata(WikiData auxdata) {
        this.auxdata = auxdata;
    }



    @Override
    public String toString() {
        return "AminoAcid{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", auxdata=" + auxdata +
                '}';
    }
}
