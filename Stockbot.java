import java.io.*;
import java.util.StringTokenizer;

public class Stockbot {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st;

        int len = 400;
        double[] arr = new double[len];
        for (int i = 0; i < arr.length; i++) {
            st = new StringTokenizer(r.readLine());
            st.nextToken();  
            arr[i] = Double.parseDouble(st.nextToken());
        }


        
        double overallHigh = Double.NEGATIVE_INFINITY;
        double overallLow = Double.POSITIVE_INFINITY;
        double lastHigh = Double.NEGATIVE_INFINITY;
        double lastLow = Double.POSITIVE_INFINITY;
        double oldHigh = Double.NEGATIVE_INFINITY;
        
        
        double[] percentages = {23.6, 38.2, 50, 61.8, 78.6};
        boolean Up;
        if(arr[len-1]-arr[len-9]>0){
            Up = true;
        }else{
            Up=false;
        }
        
        
        for (int i = arr.length-1; i >= 0; i--) {

            boolean perce = true;
            double currentValue = arr[i];
            boolean isNewHigh = currentValue > overallHigh;
            boolean isNewLow = currentValue < overallLow;

            if(isNewHigh){
                overallHigh = currentValue;
                pw.println("index " +  i + " " + "New High " + arr[i]);
                Up = true;
            }else{
                oldHigh = overallHigh;
            }
            if(isNewLow){
                overallLow = currentValue;
                pw.println("index " +  i + " " + "New Low " + arr[i]);
                Up = false;
            }
             double percent = (overallHigh-overallLow) * 0.01;
             double olpercy = (oldHigh - overallLow) * 0.01;
            if(i>11 && i<arr.length-11){
                for(double perc: percentages){
                   
                    double level = overallLow + percent*perc;
                if(arr[i]> level - 5.0 * percent && arr[i]<level + 5.0 * percent){
                    perce = false;
                    if(arr[i+10]>arr[i] && arr[i]< arr[i-10]){
                        pw.println("index "+ i +" percent " + perc + "% @ price "+ arr[i] + " rebound (DOWN TO UP - SUPPORT)");
                        Up = true;
                    }else
                    if(arr[i+10]<arr[i] && arr[i]>arr[i-10]){
                        pw.println("index "+ i +" percent " + perc + "% @ price "+ arr[i] + " reverse (UP TO DOWN - HEAVY RESISTANCE)");
                        Up = false;
                    }else{
                        pw.println("index "+ i +" percent " + perc + "% @ price "+ arr[i] + " no change(NO CHANGE - ADDED RESISTANCE)");
                    }
                }
                }
            }

            if(perce){
                if(i>11 && i<arr.length-11){
                 if(arr[i+10]>arr[i] && arr[i]< arr[i-10]){
                        pw.println("index "+ i +  "@ price "+ arr[i] + " rebound (DOWN TO UP)");
                        Up = true;
                    }else
                    if(arr[i+10]<arr[i] && arr[i]>arr[i-10]){
                        pw.println("index "+ i + " @ price "+ arr[i] + " reverse (UP TO DOWN)");
                        Up = false;
                    }
            }
            }
            if(i%1==0){
            pw.print(i);
            if(Up){
                pw.print(" Up - ");
            }else{
                pw.print(" Down - ");
            }
            if(Up==true && arr[i] == overallHigh){
                pw.println(" Forecasting Either Immediate Reverse or Continuing Growth until " + (161.8 * olpercy + overallLow) + " or even " + (261.8 * olpercy + overallLow));
            }else if(Up == true && arr[i]> (78.6 * percent + overallLow)){
                pw.println(" Forecasting Fall or Coninuing Growth Until "+  overallHigh + " or " + (161.8 * percent + overallLow) + " or even " + (261.8 * percent + overallLow));
            }else if(Up == true && arr[i]> (61.8 * percent + overallLow)){
                pw.println(" Forecasting Growth, most likely up to "+ (78.6 * percent + overallLow) + " but possibly even "+ (100.0 * percent + overallLow));
            }else if(Up == true && arr[i]> (50.0 * percent + overallLow)){
                pw.println(" Forecasting Growth, most likely up to "+ (61.8 * percent + overallLow) + " but possibly even "+ (78.6 * percent + overallLow));
            }else if(Up == true && arr[i]>= (38.2 * percent + overallLow)){
                pw.println(" Forecasting Growth, most likely up to "+ (61.8 * percent + overallLow) + " but possibly even "+ (78.6 * percent + overallLow));
            }else if(Up == true && arr[i]>= (21.6 * percent + overallLow)){
                pw.println(" Forecasting Growth, most likely up to "+ (61.8* percent + overallLow) + " but possibly even "+ (78.6 * percent + overallLow));
            }
            else if(Up == false && arr[i]> (78.6 * percent + overallLow)){
                pw.println(" Forecasting Fall Until "+  (78.6 * percent + overallLow) + " or" + (61.8 * percent + overallLow));
            }else if(Up == false && arr[i]> (61.8 * percent + overallLow)){
                pw.println(" Forecasting fall until "+(61.8*percent+overallLow) +" and will afterwards either rebound back to " + (78.6 * percent + overallLow) +" or continue to fall, most likely up to "+ (61.8 * percent + overallLow ));
            }else if(Up == false && arr[i]> (50.0 * percent + overallLow)){
                pw.println(" Forecasting Big Fall, most likely up to "+ (21.6 * percent + overallLow ));
            }else if(Up == false && arr[i]>= (38.2 * percent + overallLow)){
                pw.println(" Forecasting Fall, most likely up to "+ overallLow +" but possibly "+ (21.6 * percent + overallLow ) + " Rebound may afterwards occur back to " +  (61.8 * percent + overallLow ));
            }else{
                pw.println(" SELL SELL SELL THE BOTTOM HAS DROPPED OUT");
            }
            
            }
        }
        pw.close(); 
    }
}
