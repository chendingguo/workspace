package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.IndexedColors;

public class ExcelTool {
	static HSSFWorkbook xwb;

	public static void main(String[] args) {
		File file = new File("D:/excel/test.xls");
		String fileOutPath = "d:/excel/out.xls";
		int SHEET_NUMBER = 33;
		try {
			xwb = new HSSFWorkbook(new FileInputStream(file));

			for (int i = 0; i < SHEET_NUMBER; i++) {
				List<List<Object>> list = readExcel(file, i);
				double sumG = 0, sumF = 0;
				for (List<Object> row : list) {
					// f列求和
					sumF += Double.parseDouble(String.valueOf(row.get(3)));
					// g列求和
					sumG += Double.parseDouble(String.valueOf(row.get(5)));

				}

				NumberFormat numberFormat = NumberFormat.getInstance();

				// 设置精确到小数点后2位
				numberFormat.setMaximumFractionDigits(2);
				// 求比例
				String rate = numberFormat.format((float) sumG / (float) sumF * 100);
				// System.out.println(sumF);
				// System.out.println(sumG);
				// System.out.println(rate);

				changeCell(fileOutPath, i, list.size() + 4, sumG, rate);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static List<List<Object>> readExcel(File file, int sheetNo) throws IOException {

		List<List<Object>> list = new LinkedList<List<Object>>();

		// HSSFWorkbook xwb = new HSSFWorkbook(new FileInputStream(file));

		HSSFSheet sheet = xwb.getSheetAt(sheetNo);
		HSSFRow row;
		HSSFCell cell = null;
		FormulaEvaluator evaluator = xwb.getCreationHelper().createFormulaEvaluator();
		// System.out.println("读取excel内容如下：");

		for (int i = 4; i <= sheet.getPhysicalNumberOfRows() - 3; i++) {

			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				CellValue cellValue = evaluator.evaluate(cell);
				if (cellValue == null) {
					continue;
				}
				DecimalFormat df = new DecimalFormat("0");// 格式化 number String
				// 字符
				DecimalFormat nf = new DecimalFormat("0.000");// 格式化数字

				switch (cellValue.getCellType()) {
				case Cell.CELL_TYPE_BOOLEAN:
					System.out.print(cellValue.getBooleanValue() + "\t");
					linked.add(cellValue.getBooleanValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					System.out.print(nf.format(cellValue.getNumberValue()) + "\t");
					linked.add(nf.format(cellValue.getNumberValue()));
					break;
				case Cell.CELL_TYPE_STRING:
					System.out.print(cellValue.getStringValue() + "\t");

					linked.add(cellValue.getStringValue());
					break;
				case Cell.CELL_TYPE_BLANK:
					System.out.print("\t");
					linked.add(cellValue.getBooleanValue());
					break;
				case Cell.CELL_TYPE_ERROR:
					break;

				// CELL_TYPE_FORMULA will never happen
				case Cell.CELL_TYPE_FORMULA:
					break;
				}

			}
			if (i > 2) {
				list.add(linked);
			}
			System.out.println("\n");
		}
		return list;
	}

	public static void changeCell(String filePath, int sheetNo, int rowNo, double sumG,
			String rateF) {
		String fileToBeRead = filePath; // excel位置
		try {
			DecimalFormat nf = new DecimalFormat("0.000");// 格式化数字
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fileToBeRead));
			HSSFSheet sheet = workbook.getSheetAt(sheetNo);

			HSSFFont font2 = workbook.createFont();
			font2.setFontName("Courier New");
			font2.setColor(IndexedColors.GREEN.getIndex());
			font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
			font2.setFontHeightInPoints((short) 12);
			HSSFCellStyle style = workbook.createCellStyle();
			style.setFont(font2);
			style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFRow row = sheet.getRow(rowNo);
			if (null == row) {
				return;
			} else {
				HSSFCell cell1 = row.getCell(5);
				if (null == cell1) {
					return;
				} else {

					// cell1.setCellStyle(style);
					cell1.setCellStyle(style);
					cell1.setCellValue(Double.parseDouble(rateF));
				}

				HSSFCell cell2 = row.getCell(6);
				if (null == cell2) {
					return;
				} else {
					cell2.setCellStyle(style);
					cell2.setCellValue(nf.format(sumG));
				}
			}
			FileOutputStream out = null;
			try {
				out = new FileOutputStream(fileToBeRead);
				workbook.write(out);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
