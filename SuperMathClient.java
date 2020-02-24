import java.io.*;
import java.net.*;

class SuperMathClient {
    public static void main(String[] args)throws IOException {
        Socket s = new Socket("localhost",50128);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        dout.writeUTF(s1);
        String s2 = din.readUTF();
        System.out.println(s2);

        din.close();
        dout.close();
        s.close();
    }
}