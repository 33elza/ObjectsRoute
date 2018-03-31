package com.example.el.objectsroute.dataclass.http;

/**
 * Created by el on 21.02.2018.
 */

public class ObjectVisitationResponse {

    private long id;
    private boolean isVisited;
    private String name;
    private String address;
    private float lat;
    private float lng;
    private String priority;
    private String work;
    private int time;
    private String instruments;

    public ObjectVisitationResponse(long id, boolean isVisited, String name, String address, float lat, float lng, String priority, String work, int time, String instruments) {
        this.id = id;
        this.isVisited = isVisited;
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.priority = priority;
        this.work = work;
        this.time = time;
        this.instruments = instruments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getInstruments() {
        return instruments;
    }

    public void setInstruments(String instruments) {
        this.instruments = instruments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectVisitationResponse that = (ObjectVisitationResponse) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
