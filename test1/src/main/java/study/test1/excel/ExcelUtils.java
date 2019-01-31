package study.test1.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author chao
 * @since 2019-01-21
 */
public class ExcelUtils {

    private void readExcel(String path) throws Exception {
        File file = new File(path);
        Workbook book;
        try {
            book = new XSSFWorkbook(file);
        } catch (FileNotFoundException e) {
            throw e;
        } catch (Exception e) {
            book = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(file)));
        }

        if (book.getNumberOfSheets() > 0) {
            Sheet sheet = book.getSheetAt(0);
            Row row;
            Cell cell;
            for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                cell = row.getCell(0);
                if (cell != null) {
                    cell.setCellType(CellType.STRING);
                    System.out.println(cell.getStringCellValue());
                }
            }
        }
    }
}
