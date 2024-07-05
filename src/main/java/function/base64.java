package function;

import java.io.*;
import java.util.Base64;

public class base64 {
    public static void main(String[] args) {
        // 指定 CSV 文件路径
        String filePath = "D:\\gzgtui\\uid.csv";

        try {
            // 读取 CSV 文件内容并转换为指定格式字符串
            String formattedString = readAndFormatCSV(filePath);
            System.out.println(formattedString);

            // 使用 Base64 编码器进行 Base64 编码
            String base64EncodedString = Base64.getEncoder().encodeToString(formattedString.getBytes());

            // 打印编码后的字符串
            System.out.println(base64EncodedString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String readAndFormatCSV(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis,"GBK"));

        StringBuilder formattedContent = new StringBuilder();

        // 读取 CSV 文件内容并进行格式化
        String line;
        while ((line = reader.readLine()) != null) {
            // 根据 CSV 文件格式进行拼接
            String[] parts = line.split(",");
            if (parts.length >= 2) {
                String formattedLine = parts[0] + "," + parts[1] + "\n";
                formattedContent.append(formattedLine);
            }
        }

        reader.close();
        fis.close();

        return formattedContent.toString();
    }

}
