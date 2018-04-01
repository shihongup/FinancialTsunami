
package financialtsunami;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Shifeng Yuan
 * GWid: G32115270
 */
public class FinancialTsunami {
    static ArrayList<Integer> unsafe = new ArrayList<Integer>();
    public static void main(String[] args) {
        //n is the number of banks
        //if a bank's balance plus its loans lend to other banks under a certain limit, it is a unsafe bank.
        int n;
        double limit;
        n = Integer.parseInt(JOptionPane.showInputDialog("Please define the number of banks n:"));
        limit = Double.parseDouble(JOptionPane.showInputDialog("Please define the limit:"));
        //balance is the money which the bank currently have
        double balance[] = new double[n];
        for(int i=0; i<n; i++){
            double selfBalance;
            selfBalance = Double.parseDouble(JOptionPane.showInputDialog("Please tell me the balance of bank "+i+":"));
            balance[i] = selfBalance;
        }
        System.out.println("Balances of banks");
        for(int i=0; i<n; i++){
            System.out.println("bank "+i+" balance:"+balance[i]+"; ");
        }
        System.out.println();
        System.out.println();
        //borrowers[i][j] represents the loans bank i lend to bank j
        //if bank i does not lend money to bank j, it should be defined as 0
        double borrowers[][] = new double[n][n];
        for(int i=0; i<n;i++){
            for(int j=0; j<n; j++){
                double money;
                money = Double.parseDouble(JOptionPane.showInputDialog("Please define the money bowrrowed form bank "+i+" to "+j+":"));
                borrowers[i][j] = money;
            }
        }
        System.out.println("Cases of banks' loans:");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(borrowers[i][j]!=0){
                System.out.println("bank "+i+" lends "+"bank "+j+" "+borrowers[i][j]+";");
                }
            }
        }
        System.out.println();
        findUnsafeBank(borrowers,balance,n,limit);
        System.out.println("Unsafe banks are:");
        for(int bank:unsafe){
            System.out.println("Bank "+bank);
        }
    }
    public static void findUnsafeBank(double borrowers[][], double balance[], int n, double limit){
        double totalBalance[] = new double[n];
        //the unsafe banks cannot return the loans to their lenders
        //which means borrowers[i][j] should be 0
        //counter counts the number of unsafe banks
        //if counter > 0, we got new unsafe banks
        int counter = 0;
        do{
        counter = 0;
        for(int i=0; i<n; i++){
                totalBalance[i] = balance[i];
            }
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                totalBalance[i] += borrowers[i][j];
            }
        }
        for(int i=0; i<n;i++){
            if((totalBalance[i] < limit)&&(notAdded(i,n))){
                unsafe.add(i);
                counter++;
                for(int k=0; k<n; k++){
                    borrowers[k][i] = 0;
                }
            }
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                totalBalance[i] = balance[i];
            }
        }
        }while(counter>0);
    }
    //determine if bank i is already considered a unsafe bank
    public static boolean notAdded(int i,int n){
        int count = 0;
        for(int num:unsafe){
            if(i==num){
                count++;
            }
        }
        if(count>0){
            return false;
        }else{
            return true;
        }
    }
    
}
