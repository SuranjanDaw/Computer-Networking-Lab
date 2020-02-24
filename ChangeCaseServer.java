import java.io.*;
import java.net.*;
class ChangeCaseServer {
    public static void main(String[] args)throws IOException{
        registerToDNS("ChangeCase", "localhost","50129");

        ServerSocket sok = new ServerSocket(50129);
        Socket s;
        DataInputStream din;
        DataOutputStream dout;
        String s1;
        boolean flag = true;
        while(flag)
        {
            s = sok.accept();
            dout = new DataOutputStream(s.getOutputStream());
            din = new DataInputStream(s.getInputStream());
            s1 = din.readUTF();
            if(s1.charAt(0) == 's')
            {
                System.out.println("Sending to Client: "+change(s1));
                dout.writeUTF(change(s1));
                
            }else if(s1.charAt(0) == '0'){
                de_registerToDNS("ChangeCase", "localhost", "50129");
                dout.writeUTF("De-registed");
                flag = false;
            }
            dout.close();
            din.close();
            s.close();
        }
        sok.close();
        

    }

    private static String change(String s1) {
        int k =0;
        String res ="";
        while(true)
        {
            char c = s1.charAt(k);
            k++;

            if(Character.isUpperCase(c))
            {
                res+= (char ) Character.toLowerCase(c);
            } else {
                res+= (char ) Character.toUpperCase(c);
            }
            if(k == s1.length())
            {
                break;
            }
        }
        return res;
    }

    private static void registerToDNS(String name, String IP, String port)
    {
        try {
            Socket s = new Socket("localhost",50130);
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            String sent = "s_"+name+"_"+IP+":"+port;
            dout.writeUTF(sent);

            String rec = din.readUTF();
            System.out.println(rec);
            din.close();
            dout.close();
            s.close();

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        

    }
    private static void de_registerToDNS(String name, String IP, String port)
    {
        try {
            Socket s = new Socket("localhost",50130);
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            String sent = "s_"+name+"_remove";
            dout.writeUTF(sent);

            String rec = din.readUTF();
            System.out.println(rec);
            din.close();
            dout.close();
            s.close();

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        

    }
}