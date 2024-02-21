package wildberries.deprecated;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Deprecated(since = "We don't need read excel, because we get information in json format from Wildberries server")
public class ExcelReader {

    private List<String> columns = Arrays.asList("Предмет", "Бренд", "Артикул поставщика", "Тип документа", "Обоснование для оплаты", "Дата заказа покупателем", "Дата продажи", "Кол-во", "К перечислению Продавцу за реализованный Товар", "Количество доставок", "Количество возврата", "Услуги по доставке товара покупателю", "Общая сумма штрафов", "Доплаты", "Виды логистики, штрафов и доплат");

    private Set<String> columnNames = new HashSet<>(columns);

    public static LinkedList<List<Object>> read(String path, String sheetname) throws IOException {
        FileInputStream ip = new FileInputStream(path);
        Workbook wb = new XSSFWorkbook(ip);
        Sheet sheet = wb.getSheet(sheetname);
        Iterator<Row> rowIterator = sheet.rowIterator();
        LinkedList<List<Object>> allSheet = new LinkedList<>();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            //iterate over the columns of the current row
            Iterator<Cell> cellIterator = row.cellIterator();
            LinkedList<Object> currentRaw = new LinkedList<>();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case STRING:
                        String curValue = cell.getStringCellValue();
                        System.out.println(curValue);
                        currentRaw.add(curValue);
                        break;
                    case NUMERIC:
                        String curNumValue = String.valueOf(cell.getNumericCellValue());
                        System.out.println(curNumValue);
                        currentRaw.add(curNumValue);
                        break;
                    default:
                        System.out.println("--------- " + cell.getCellType());
                        break;
                }
            }
            //append empty line
            System.out.println();
            allSheet.add(currentRaw);
        }

        ip.close();
        return allSheet;
    }
}
