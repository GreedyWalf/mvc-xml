import java.io.File;
import java.io.FileInputStream;

public class TestImg {

    public static void main(String[] args) throws Exception {
        String filePath = "/Users/qinyupeng/Desktop/图片/ceshi.jpeg";
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        byte[] buff = new byte[1024];
        int len = 0;
        while (-1 != (len = fileInputStream.read(buff))) {
            for(byte b : buff){
                System.out.print(b);
            }
        }
    }
}
