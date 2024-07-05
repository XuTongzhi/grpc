package function;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryLogCompressor {

    public static void main(String[] args) throws IOException {
        // 设置要扫描的目录和输出文件
        String dirPath = "./data/history_log/";
        String outputDir = "./data/compressed/";

        // 获取目录下的所有文件
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null) {
            System.err.println("Error: Invalid directory.");
            System.exit(1);
        }

        // 创建 tar 包输出流
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH");
        String dateStr = format.format(new Date());
        String outputFileName = "via-" + dateStr + ".tar.bz2";
        String outputPath = outputDir + outputFileName;
        FileOutputStream fos = new FileOutputStream(outputPath);
        BZip2CompressorOutputStream bz2os = new BZip2CompressorOutputStream(fos);
        TarArchiveOutputStream taros = new TarArchiveOutputStream(bz2os);

        // 遍历文件，创建对应的 TarArchiveEntry，并将文件添加到对应的 entry 中
        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName();
                Date fileDate = parseDateFromFileName(fileName);
                if (fileDate == null) {
                    continue; // 文件名不符合要求，忽略
                }
                TarArchiveEntry entry = new TarArchiveEntry(file);
                entry.setName(fileName);
                entry.setModTime(fileDate.getTime());
                taros.putArchiveEntry(entry);
                FileInputStream fis = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) != -1) {
                    taros.write(buffer, 0, len);
                }
                fis.close();
                taros.closeArchiveEntry();
            }
        }

        // 关闭输出流
        taros.finish();
        taros.close();
        bz2os.close();
        fos.close();

        System.out.println("Compression finished: " + outputPath);
    }

    private static Date parseDateFromFileName(String fileName) {
        // 文件名格式示例: via-2023031512.0.log
        if (!fileName.startsWith("via-")) {
            return null;
        }
        String dateStr = fileName.substring(4, 14);
        String hourStr = fileName.substring(14, 16);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH");
        try {
            return format.parse(dateStr + hourStr);
        } catch (Exception e) {
            return null;
        }
    }
}
