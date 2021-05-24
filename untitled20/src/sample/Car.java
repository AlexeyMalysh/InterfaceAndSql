package sample;
/*Class for creating model and mark objects for further interaction with it and with the table
            in SQL*/
public class Car {
    private String model;
    private String mark;
    public Car(String mark, String model){
        setMark(mark);
        setModel(model);
    }

    public String getModel() {
        return model;
    }

    public String getMark() {
        return mark;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "mark: " + mark;
    }
}
