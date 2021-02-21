public class Calcing implements Operation {

    double val1;
    double val2;
    Calcing(double val1, double val2)
    {
        this.val1=val1;
        this.val2=val2;
    }
    @Override
    public double dod() {
        return val1+val2;
    }

    @Override
    public double mno() {
        return val1*val2;
    }

    @Override
    public double dziel() {
        return val1/val2;
    }

    @Override
    public double odej() {
        return val1-val2;
    }
}
