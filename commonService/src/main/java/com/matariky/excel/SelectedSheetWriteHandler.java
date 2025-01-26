package com.matariky.excel;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.Map;

@Data
@AllArgsConstructor
public class SelectedSheetWriteHandler implements SheetWriteHandler {

	private final Map<Integer, ExcelSelectedResolve> selectedMap;

	/**
	 * 设置阈值，避免 Generation 的Import Import 下拉值获取不到，可自行设置 Quantity大小
	 */
	private static final Integer LIMIT_NUMBER = 25;

	/**
	 * Called before create the sheet
	 */
	@Override
	public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

	}

	/**
	 * Called after the sheet is created
	 */
	@Override
	public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
		// 这里可以对cell进行任何操作
		Sheet sheet = writeSheetHolder.getSheet();
		DataValidationHelper helper = sheet.getDataValidationHelper();
		selectedMap.forEach((k, v) -> {
			// 设置下拉列表的行： 首行，末行，首列，末列
			CellRangeAddressList rangeList = new CellRangeAddressList(v.getFirstRow(), v.getLastRow(), k, k);
			// 如果下拉值总数大于25，则使用一个新sheet存储，避免 Generation 的Import Import 下拉值获取不到
			if (v.getSource().length > LIMIT_NUMBER) {
				//定义sheet的 Name
				//1.创建一个隐藏的sheet  Name为 hidden + k
				String sheetName = "hidden" + k;
				Workbook workbook = writeWorkbookHolder.getWorkbook();
				Sheet hiddenSheet = workbook.createSheet(sheetName);
				for (int i = 0, length = v.getSource().length; i < length; i++) {
					// 开始的行数i，列数k
					hiddenSheet.createRow(i).createCell(k).setCellValue(v.getSource()[i]);
				}
				Name category1Name = workbook.createName();
				category1Name.setNameName(sheetName);
				String excelLine = getExcelLine(k);
				// =hidden!$H:$1:$H$50  sheet为hidden的 H1列开始H50行 Data 获取下拉数组
				String refers = "=" + sheetName + "!$" + excelLine + "$1:$" + excelLine + "$" + (v.getSource().length + 1);
				// 将刚才设置的sheet引用到你的下拉列表中
				DataValidationConstraint constraint = helper.createFormulaListConstraint(refers);
				DataValidation dataValidation = helper.createValidation(constraint, rangeList);
				writeSheetHolder.getSheet().addValidationData(dataValidation);
				// 设置存储下拉列值得sheet为隐藏
				int hiddenIndex = workbook.getSheetIndex(sheetName);
				if (!workbook.isSheetHidden(hiddenIndex)) {
					workbook.setSheetHidden(hiddenIndex, true);
				}
			}
			// 设置下拉列表的值
			DataValidationConstraint constraint = helper.createExplicitListConstraint(v.getSource());
			// 设置约束
			DataValidation validation = helper.createValidation(constraint, rangeList);
			// 阻止输入非下拉选项的值
			validation.setErrorStyle(DataValidation.ErrorStyle.STOP);
			validation.setShowErrorBox(true);
//			validation.setSuppressDropDownArrow(true);
			validation.createErrorBox("提示", "请输入下拉选项中的内容");
			sheet.addValidationData(validation);
		});
	}


	/**
	 * 返回excel列标A-Z-AA-ZZ
	 *
	 * @param num 列数
	 * @return java.lang.String
	 */
	private String getExcelLine(int num) {
		String line = "";
		int first = num / 26;
		int second = num % 26;
		if (first > 0) {
			line = (char) ('A' + first - 1) + "";
		}
		line += (char) ('A' + second) + "";
		return line;
	}
}
