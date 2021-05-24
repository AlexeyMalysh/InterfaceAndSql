package sample;
/*Class for creating engine objects for further interaction with it and with the table
            in SQL*/
public class Engine {
    private String nameEngine;
    private String horsePower;
    private String volume;

    public Engine(String nameEngine, String horsePower, String volume) {
        setNameEngine(nameEngine);
        setHorsePower(horsePower);
        setVolume(volume);
    }

    public String getVolume() {
        return volume;
    }

    public String getHorsePower() {
        return horsePower;
    }

    public String getNameEngine() {
        return nameEngine;
    }

    public void setHorsePower(String horsePower) {
        this.horsePower = horsePower;
    }

    public void setNameEngine(String nameEngine) {
        this.nameEngine = nameEngine;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Engine: " + nameEngine + " HorsePower: " + horsePower + " \nVolume: " + volume;
    }
}
