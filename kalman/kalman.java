import java.util.*;

class Kalman{

    public Kalman(){
    }

    public double gaussian(double mu, double var, double x){
        return 1.0/Math.sqrt(2.0*3.14*var) * Math.exp(Math.pow(-0.5*(x-mu), 2)/var);
    }

    public ArrayList<Double> update(double mu1, double var1, double mu2, double var2){
        double mu = (var2*mu1 + var1*mu2)/(var1 + var2);
        double var = 1/(1/var2 + 1/var1);
        ArrayList<Double> arr = new ArrayList<Double>();
        arr.add(mu);
        arr.add(var);

        return arr;
    }

    public ArrayList<Double> predict(double mu1, double var1, double mu2, double var2){
        double mu = mu1 + mu2;
        double var = var1 + var2;
        ArrayList<Double> arr = new ArrayList<Double>();
        arr.add(mu);
        arr.add(var);

        return arr;
    }

    public static void main(String args[]) {
        Kalman k = new Kalman();
        Scanner s = new Scanner(System.in);
        System.out.println("Enter your no. of measurements: ");
        int num = s.nextInt();
        int num2 = num;
        System.out.println("Enter your measurements(space seperated): ");
        ArrayList<Double> measurements = new ArrayList<Double>();
        while(num-->0){
            measurements.add(s.nextDouble());
        }
        System.out.println("Enter your measurement variance: ");
        Double measurements_var = s.nextDouble();

        System.out.println("Enter your motions(space seperated): ");
        ArrayList<Double> motion = new ArrayList<Double>();
        while(num2-->0){
            motion.add(s.nextDouble());
        }
        if(motion.size()!=measurements.size()){
            System.out.println("Enter same no. of elements for measurements and motion");
            System.exit(0);
        }
        System.out.println("Enter your measurement variance: ");
        Double motion_var = s.nextDouble();
        Double mu = 0.0;
        Double var = 10000.0;

        for(int i=0;i<measurements.size();i++){
            ArrayList<Double> temp = k.update(mu, var, measurements.get(i), measurements_var);
            mu = temp.get(0);
            var = temp.get(1);
            System.out.println("Update: [" + mu + ", " + var + "]");
            ArrayList<Double> temp2 = k.predict(mu, var, motion.get(i), motion_var);
            mu = temp2.get(0);
            var = temp2.get(1);
            System.out.println("Predict: [" + mu + ", " + var + "]");
        }

        System.out.println("Final Position: [" + mu + ", " + var + "]");

        s.close();
    }

}