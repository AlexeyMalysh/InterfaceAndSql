package sample;
/*Class for creating transmission objects for further interaction with it and with the table
            in SQL*/
public class Transmission {
    private String drive;
    private String speed;

    public Transmission(String drive, String speed) {
        setDrive(drive);
        setSpeed(speed);
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDrive() {
        return drive;
    }

    public String getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "Drive: " + drive + " \nSpeed: " + speed;
    }
}
