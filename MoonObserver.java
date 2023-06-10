public class MoonObserver {
    float latitude;
    float longitude;
    String date;

    public MoonObserver(float latitude, float longitude, String date) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
