/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.missionexpress.model;

import java.util.Date;

/**
 *
 * @author dohko
 */
public class DatosBasicos extends Imagen {

    private String addres;
    private String latitude;
    private String longitude;
    private Date hourLocal;
    private Date hourInternational;
    private Date date;
    private String altitude;

    /**
     * @return the addres
     */
    public String getAddres() {
        return addres;
    }

    /**
     * @param addres the addres to set
     */
    public void setAddres(String addres) {
        this.addres = addres;
    }

    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the hourLocal
     */
    public Date getHourLocal() {
        return hourLocal;
    }

    /**
     * @param hourLocal the hourLocal to set
     */
    public void setHourLocal(Date hourLocal) {
        this.hourLocal = hourLocal;
    }

    /**
     * @return the hourInternational
     */
    public Date getHourInternational() {
        return hourInternational;
    }

    /**
     * @param hourInternational the hourInternational to set
     */
    public void setHourInternational(Date hourInternational) {
        this.hourInternational = hourInternational;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the altitude
     */
    public String getAltitude() {
        return altitude;
    }

    /**
     * @param altitude the altitude to set
     */
    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

}
