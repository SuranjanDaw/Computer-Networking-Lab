import java.io.*;
import java.net.*;

class IntruderServer {
    public static void main(String[] args) throws IOException {
        hackDNS("ChangeCase","localhost","50130","localhost","50131");
        ServerSocket sok = new ServerSocket(50131);
        System.out.println("Hacker Running...");
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
                System.out.println("Sending to Client: I am Hacker");
                dout.writeUTF(" I am Hacker");
            }else if(s1.charAt(0) == '0')
            {
                System.out.println("De-register Intruder Server");
                de_registerToDNS("ChangeCase", "localhost", "50131");
                dout.writeUTF("De-registed");
                flag = false;
            }
            dout.close();
            din.close();
            s.close();
        }
        sok.close();
        
    }

    private static void hackDNS(String victimServer, String DNS_ip, String DNS_port, String myip, String myport) {
        try {
            Socket s = new Socket(DNS_ip,Integer.parseInt(DNS_port));

            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            String s1 = "i_"+victimServer+"_"+victimServer+"_"+myip+":"+myport;
            dout.writeUTF(s1);
            String res = din.readUTF();
            System.out.println(res);
            din.close();
            dout.close();
            s.close();


        }catch (Exception  e){
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