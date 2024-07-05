package function;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class filetotar {
    public static void main(String[] args) throws IOException {
        System.out.println("1");
        //文件路径
        List<String> filelist = new ArrayList<>();
        filelist.add("C:\\Users\\Administrator\\Desktop\\pwd");
//        filelist.add("C:\\Users\\Administrator\\Desktop\\pwd2");
//        filelist.add("/data/hz-viaduct-1.reqfs.cn/history_log/");
//        filelist.add("/data/hz-viaduct-2.reqfs.cn/history_log/");
//        filelist.add("/data/hz-viaduct-3.reqfs.cn/history_log/");
//        filelist.add("/data/hz-viaduct-4.reqfs.cn/history_log/");
        for (String dirPath : filelist) {
            File dir = new File(dirPath);
            File[] files = dir.listFiles();
            Map<String, List<String>> outputmap = new HashMap<>();
            //获取路径下文件分类
            for (File file : files) {
                String substringName = file.getName().substring(0, 14);
                String name = file.getName();
                if (outputmap.containsKey(substringName)) {
                    List<String> fileslist = outputmap.get(substringName);
                    fileslist.add(name);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(name);
                    outputmap.put(substringName, list);
                }
            }
            //遍历每个分类
            extracted(dir, outputmap);
            System.out.println(dirPath + "压缩完成");
        }
        System.out.println("全部压缩完成");
    }
    private static void extracted(File dir, Map<String, List<String>> outputmap) throws IOException {
        for (Map.Entry<String, List<String>> entry : outputmap.entrySet()) {
            String key = entry.getKey();
            String outputFileName = dir + "\\" + key + ".tar";
            System.out.println(outputFileName);
            TarArchiveOutputStream tarOutput = new TarArchiveOutputStream(new FileOutputStream(outputFileName));

            List<String> values = entry.getValue();
            //遍历每个分类下的文件
            for (String value : values) {
                String filePath = dir + "/" + value;
                File file = new File(filePath);
                TarArchiveEntry archiveEntry = new TarArchiveEntry(file.getName());
                archiveEntry.setSize(file.length());
                tarOutput.putArchiveEntry(archiveEntry);
                FileInputStream input = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = input.read(buffer)) > 0) {
                    tarOutput.write(buffer, 0, len);
                }
                input.close();
                tarOutput.closeArchiveEntry();
                file.delete();
            }
            tarOutput.close();

            BZip2CompressorOutputStream bzip2Output = new BZip2CompressorOutputStream(new FileOutputStream(outputFileName + ".bz2"));

            FileInputStream input = new FileInputStream(outputFileName);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) > 0) {
                bzip2Output.write(buffer, 0, len);
            }
            input.close();
            bzip2Output.close();
            File tarfile = new File(outputFileName);
            tarfile.delete();
        }
    }

}
