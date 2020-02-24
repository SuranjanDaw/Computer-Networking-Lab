import java.io.*;
import java.net.*;
class MathServer {
    public static void main(String[] args)throws IOException{
        registerToDNS("Math", "localhost","50128");

        ServerSocket sok = new ServerSocket(50128);
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
                //Echo Server for now
                System.out.println("Echoing for now: "+s1);
                dout.writeUTF(s1);
                
            }else if(s1.charAt(0) == '0'){
                de_registerToDNS("Math", "localhost", "50128");
                dout.writeUTF("De-registed");
                flag = false;
            }
            dout.close();
            din.close();
            s.close();
        }
        sok.close();
        

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