package main;

public class Sensor {
    int factor;
    double dg, gg, value;
    String degree, type;

    public Sensor() {
        type = " ";
        dg = 1;
        gg = 1;
        value = 1;
        degree = " ";
        factor = 0;
    }

    public Sensor(String type, String degree, double dg, double gg, int factor) {
        this.type = type;
        this.degree = degree;
        this.factor = factor;
        this.dg = dg;
        this.gg = gg;
        this.value = dg + Math.random() * (gg - dg);
        int temp = (int) (value * factor);
        this.value = (double) temp / factor;
    }

    public Sensor(String type) {
        this.type = type;
        int temp;
        if (type.equals("Temperature")) {
            this.factor = 10;
            this.dg = -3266.8;
            this.gg = 3266.8;
            this.degree = "°C";
            this.value = dg + Math.random() * (gg - dg);
            temp = (int) (value * factor);
            this.value = (double) temp / factor;
        }
        else if (type.equals("Pressure")) {
            this.factor = 1000;
            this.dg = 0;
            this.gg = 65.336;
            this.degree ="Bar";
            this.value = dg + Math.random() * (gg - dg);
            temp = (int) (value * factor);
            this.value = (double) temp / factor;
        }
        else if (type.equals("1min1hour1day")) {
            this.factor = 0;
            this.value = Math.random() * 65336;
            this.degree = "litre";
        }
        else if (type.equals("1week1month1year")) {
            this.factor = 10;
            this.value = Math.random() * 6533.6;
            this.degree = "m3";
        }
        else {
            throw new RuntimeException("This is an exception message");
        }
    }

    public String feedback() {
        if (this.type.equals("Temperature")  || this.type.equals("Pressure")) {
            return type + " " + value + " " + degree + " ";
        }
        else if (this.type.equals("1min1hour1day")) {
            return "1 min: " + value + " " + degree + "\n"  + "1 hour: " + value * 60 + " " + degree + "\n"  + "1 day: " + value * 60  * 24 + " " + degree + "\n";
        }
        else {
            return "1 week: " + value + " " + degree + "\n"   + "1 month: " + value * 4 + " " + degree + "\n"  + "1 year: " + value * 4 * 12 + " " + degree + "\n";
        }
    }
}