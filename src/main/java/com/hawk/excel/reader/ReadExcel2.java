package com.hawk.excel.reader;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel2 {

	public static void read(String filePath) throws IOException {
		try {
			File inputWorkbook = new File(filePath);
			Workbook w = Workbook.getWorkbook(inputWorkbook);
			Sheet sheet = w.getSheet(0);

			for (int i = 0; i < sheet.getColumns(); i++) {
				for (int j = 1; j < sheet.getRows(); j++) {
					Cell cell = sheet.getCell(i, j);
					if (cell.getType() != CellType.EMPTY) {
						switch (i) {
						case 0:
							System.out.println(cell.getContents());
							break;
						case 2:
							System.out.println(cell.getContents());
							break;
						case 3:
							System.out.println(cell.getContents());
							break;
						default:
							System.out.println(cell.getContents());
							break;
						}
					}
				}
				System.out.println();
			}
		} catch (BiffException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		read("./src/main/java/com/hawk/excel/cc.xls");
	}

}
