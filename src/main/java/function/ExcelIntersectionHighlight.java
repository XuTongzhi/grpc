package function;

import org.apache.commons.csv.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class CsvIntersectionHighlight {

    public static void main(String[] args) {
        try {
            // 读取第一个CSV文件
            CSVParser csvParser1 = new CSVParser(new FileReader("C:\\Users\\Administrator\\Documents\\WXWork\\1688856031425440\\Cache\\File\\2024-01\\tianjin_leads.csv\\ls.csv"), CSVFormat.DEFAULT);
            Iterator<CSVRecord> iterator1 = csvParser1.iterator();

            // 读取第二个CSV文件
            CSVParser csvParser2 = new CSVParser(new FileReader("C:\\Users\\Administrator\\Documents\\WXWork\\1688856031425440\\Cache\\File\\2024-01\\tianjin_leads.csv\\tianjin_leads.csv"), CSVFormat.DEFAULT);
            CSVPrinter csvPrinter = new CSVPrinter(new FileWriter("C:\\Users\\Administrator\\Documents\\WXWork\\1688856031425440\\Cache\\File\\2024-01\\tianjin_leads.csv\\tianjin_leads.csv"), CSVFormat.DEFAULT);

            // 读取第一个文件的第一列数据
            Set<String> column1Values = readColumnValues(iterator1, 0);


            // 在第二个文件中标记与第一个文件有交集的记录
            highlightIntersection(csvParser2, csvPrinter, 1, 4, column1Values); // 在第2列检查，如果存在，标记第5列为1

            // 关闭CSVPrinter
            csvPrinter.close();

            System.out.println("处理完成。");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Set<String> readColumnValues(Iterator<CSVRecord> iterator, int columnIndex) {
        Set<String> values = new HashSet<>();
        while (iterator.hasNext()) {
            CSVRecord record = iterator.next();
            String columnValue = record.get(columnIndex).trim();
            values.add(columnValue);
        }
        return values;
    }

    private static void highlightIntersection(CSVParser csvParser, CSVPrinter csvPrinter, int checkColumnIndex, int markColumnIndex, Set<String> valuesToHighlight) throws IOException {
        for (CSVRecord record : csvParser) {
            String checkColumnValue = record.get(checkColumnIndex).trim();
            String highlightValue = valuesToHighlight.contains(checkColumnValue) ? "1" : "0";

            // 在第markColumnIndex列添加数值标记
            csvPrinter.printRecord(
                    record.get(0),
                    record.get(1),
                    record.get(2),
                    record.get(3),
                    highlightValue,  // 在第markColumnIndex列添加数值标记
                    record.get(5)
                    // ... 继续添加其他列
            );
        }
    }
}



