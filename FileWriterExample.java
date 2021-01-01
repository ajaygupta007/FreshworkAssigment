import org.json.simple.JSONObject;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
public class FileWriterExample {
    private String FilePath;
    private static HashMap<String, Boolean> KeyData=new HashMap<String, Boolean>();
    public FileWriterExample()
    {

    }
    public static void GenerateKey(String FilePath)
    {
        try {
            Scanner scanner = new Scanner(new File(FilePath));
            while (scanner.hasNextLine()) {
                String temp=scanner.nextLine();
                String tempkey = "";
                for(int i=0;i<32;i++)
                    tempkey+=temp.charAt(i);
                   String tempval=tempkey.trim();
                    KeyData.put(tempval,TRUE);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void delete(String key,String FilePath)
    {
        KeyData.put(key,FALSE);
    }
    public static void Insert(String key ,String FilePath) throws IOException
    {
        Scanner in = new Scanner (System.in);
        if(key.length()>32)
        {
         System.out.println("Operation failed due to key length exceeded the limit(32chars)");
         return ;
        }
        if(KeyData.get(key)==TRUE) {
            System.out.println("Operation failed due to key already available");
            return ;
        }
        KeyData.put(key,TRUE);
        for(int i=key.length()-1;i<32;i++)
            key+=' ';
        System.out.println("Enter Your Name: ");
        String Name=in.next();
        System.out.println("Enter Your City");
        String City=in.next();
        System.out.println("Enter Your College Name");
        String  CollegeName=in.next();
        JSONObject obj=new JSONObject();
        obj.put("Name",Name);
        obj.put("City",City);
        obj.put("CollegeName",CollegeName);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(FilePath,true)));
        pw.print(key);
        pw.print(obj);
        pw.print('\n');
        System.out.println("Details added successfully.");
        pw.close();
    }
    public static void Read(String key,String FilePath) throws IOException
    {
        File file=new File(FilePath);    //creates a new file instance
        FileReader fr=new FileReader(file);   //reads the file
        BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
        StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters
        String line,ans="",res = "Key Does not exist";
        while((line=br.readLine())!=null)
        {
            for(int i=0;i<32;i++)
                ans+=line.charAt(i);
            ans=ans.trim();
                if(ans.equals(key)) {
                    res = line.substring(32);
                }
        }
        fr.close();    //closes the stream and release the resources
        System.out.println("Contents of File: ");
        System.out.println(res);
    }
    public FileWriterExample(String FilePath) {
        this.FilePath = FilePath;
    }
    public static void main(String[] args) throws IOException {
        System.out.println("Do you want to use an existing file or create a file Y/N");
        Scanner sc=new Scanner(System.in);
        char flag=sc.next().charAt(0);
        String FilePath;
        if(flag=='Y')
        {
            System.out.println("Enter File Name");
            FilePath=sc.next();
        }
        else
        {
             FilePath = "DataBase-File" + Integer.valueOf((int) (Math.random() * 1000000L) + "");
            System.out.println("You are Using a Random File  of Name "+ FilePath);
        }
        GenerateKey(FilePath);
        while(TRUE)
        {
            System.out.println("Enter 1 for Create\nEnter 2 for  Read\nEnter 3 for Delete\n");

            char val;
            val = sc.next().charAt(0);
            if (val == '1') {
                System.out.println("Enter the key");
                String key=sc.next();
                Insert(key,FilePath);
            } else if (val == '2') {
                System.out.println("Enter the key");
                String key=sc.next();
                Read(key,FilePath);
            } else if(val=='3') {
                System.out.println("Enter the key");
                String key=sc.next();
                delete(key,FilePath);
            }
            else
                System.out.println("Wrong value Enter");

            System.out.println("Do You Want To Continue Y/N");
             flag=sc.next().charAt(0);
            if(flag=='N')
                break;
        }
}
}
