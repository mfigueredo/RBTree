// Code by Mickael R. C. Figueredo
// Master Student from Federal University of Rio Grande do Norte
// Questions? mickaelfigueredo@ppgsc.ufrn.br
// 
// To Run the code onde Linux :
// 1 - Ctrl+Alt+t
// 2 - user$ javac Main.java
// 3 - user$ java Main <path to dictionary>
//     ex: java Main dicionario.txt
//



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main
{

    public static void main(String[] args)
    {

        String dict = args[0];
        RBTree t = new RBTree();
        UseDict(dict, t);
        t.RBPrint(t);
        t.RBCheck(t.root);
    }

    //Function to use our dictonary
    public static  void UseDict(String path, 
                                RBTree t){
        FileReader fr = null;
        BufferedReader br = null;
        String line, name, action;
        String[] elements;

        //Opem out file
        try 
        {

            fr = new FileReader(path);
            br = new BufferedReader(fr);

            //Read each line 
            while((line = br.readLine()) != null)
            {
                elements = line.split(" ");
                name = elements[0];
                action = elements[1];
                //If the word to be insert or deleted is empty, stop our process.
                if(name.equals(null))
                {
                    System.out.println("It is not possible insert a blank value");
                    break;
                //If the line contain the 1 parameter, insert it.    
                }else if(action.equals("1") && !t.ElementExist(t.root, name)){
                    RBElement in = new RBElement();
                    in.key = name;
                    System.out.println("Inserting "+name);
                    t.RBInsert(t, in);
                //If the parameter is 0, delete it
                }else if(action.equals("0") && t.ElementExist(t.root, name)){
                    System.out.println("Deleting "+name);
                    RBElement in = t.TreeSearch(t.root, name);
                    t.RBDelete(t, in);
                    System.out.println("This is how our tree looks like now:");
                    t.RBPrint(t);
                    t.RBCheck(t.root);    
                //Cases which we got problems              
                }else if(action.equals("1") && t.ElementExist(t.root, name)){
                    System.out.println("It is not possible insert "+name+". It already exist!");
                }else if(action.equals("0") && t.ElementExist(t.root, name)){
                    System.out.println("It is not possible delete "+name+". It not exist!");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}