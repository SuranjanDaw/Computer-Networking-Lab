import java.io.*;
import java.net.*;

class Client {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true)
        {
            System.out.println("1 ->To connect to server\n2-> To close Connections ");
            int i = Integer.parseInt(br.readLine());
            if(i == 1)
            {
                System.out.println("Enter Server name:");
                String s1 = br.readLine();
                String[] r = connectToDNS(s1);
                if(s1.equals("ChangeCase"))
                {
                    System.out.println("ll");
                    talkToServer(r, "s_bcjjJBVJEBU");
                }else if(s1.equals("Math"))
                {
                    talkToServer(r, "s_b_5_6_+");
                }
            } else if(i == 2)
            {
                break;
            }
        }
        


        
    }

    private static String[] connectToDNS(String server)
    {
        Socket s;
        DataInputStream din;
        DataOutputStream dout;

        String[] resAr = null;
        try {
            s = new Socket("localhost",50130);
        System.out.println("Client Runnig for DNS...");
        din = new DataInputStream(s.getInputStream());
        dout = new DataOutputStream(s.getOutputStream());

        dout.writeUTF("c_"+server);
        String response = din.readUTF();
        System.out.println(response);
        resAr = response.split(":");
        din.close();
        dout.close();
        s.close();
        
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return resAr;
        
    }
    private static void talkToServer(String[] resAr, String outStr) {
        try {
            Socket s = new Socket(resAr[0],Integer.parseInt(resAr[1]));
            System.out.println("Client Runnig for Server...");
            DataInputStream din;
            DataOutputStream dout;
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            System.out.println(outStr);
            dout.writeUTF(outStr);
            String response = din.readUTF();
            System.out.println(response);
                
            din.close();
            dout.close();
            s.close();

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        


    }
}