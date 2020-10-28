package com.example.temperature_detect;

public class Detail {
    private String id;
    private String roomtemp;
    private String roomhumidity;
    private String bodypulse;
    private String bodytemp;


    public Detail(String roomtemp, String roomhumidity, String bodypulse, String bodytemp) {
        this.roomtemp = roomtemp;
        this.roomhumidity = roomhumidity;
        this.bodypulse = bodypulse;
        this.bodytemp = bodytemp;
    }

    public String getRoomtemp() {
        return roomtemp;
    }

    public void setRoomtemp(String roomtemp) {
        this.roomtemp = roomtemp;
    }

    public String getRoomhumidity() {
        return roomhumidity;
    }

    public void setRoomhumidity(String roomhumidity) {
        this.roomhumidity = roomhumidity;
    }

    public String getBodypulse() {
        return bodypulse;
    }

    public void setBodypulse(String bodypulse) {
        this.bodypulse = bodypulse;
    }

    public String getBodytemp() {
        return bodytemp;
    }

    public void setBodytemp(String bodytemp) {
        this.bodytemp = bodytemp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
