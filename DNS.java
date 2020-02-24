import java.io.*;
import java.net.*;
import java.util.HashMap;

class DNS {
    public static void main(String[] args) throws IOException {
        ServerSocket sok = new ServerSocket(50130);
        Socket s;
        DataInputStream din;
        DataOutputStream dout;
        String s1;
        boolean flag = true;
        HashMap<String,String> map = new HashMap<String,String>();
        while(flag) {
            s = sok.accept();
            System.out.println("DNS Running....");
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            String req = din.readUTF();
            String[] reqAr = req.split("_");
            System.out.println(reqAr[0]);
            if(reqAr[0].equals("s"))
            {
                System.out.println("Got a Register request...");
                if(reqAr[2].equals("remove"))
                {
                    map.remove(reqAr[1]);
                    s1 = "Server is De-registered: "+reqAr[1];
                    System.out.println(s1);
                    dout.writeUTF(s1);
                }else {
                    map.put(reqAr[1], reqAr[2]);
                    s1 = "Server is Registered: "+reqAr[1]+"::"+reqAr[2];
                    System.out.println(s1);
                    dout.writeUTF(s1);
                }

            }else if(reqAr[0].equals("c")){
                System.out.println("Got a Client request...");
                String data  = map.get(reqAr[1]);
                s1 = "Server Found::"+reqAr[1] +"::"+data;
                System.out.println(s1);
                dout.writeUTF(data);

            }else if(reqAr[0].equals("stop"))
            {
                System.out.println("DNS Server Stopping Service.");
                flag = false;
            }else if(reqAr[0].equals("i"))
            {
                map.remove(reqAr[1]);
                map.put(reqAr[2], reqAr[3]);
                System.out.println("DNS keys repalced"+ reqAr[1]+"with new Hacker values "+reqAr[2]+"::"+reqAr[3]);
                dout.writeUTF("DNS Hacked");
            }
            else {
                System.out.println("Not a valid request");
            }
            din.close();
            dout.close();
            s.close();
        }
        sok.close();

    }
}