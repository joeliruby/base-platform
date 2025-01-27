package com.matariky.excel;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.Map;

@Data
@AllArgsConstructor
public class SelectedSheetWriteHandler implements SheetWriteHandler {

	private final Map<Integer, ExcelSelectedResolve> selectedMap;

	/**
	 * Configuration阈值 ,避免 Generation 的Import Import 下拉值 Retrieve不到 ,可自行
	 * Configuration Quantity大小
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
		// 这里可以对cell进行任何 Operation
		Sheet sheet = writeSheetHolder.getSheet();
		DataValidationHelper helper = sheet.getDataValidationHelper();
		selectedMap.forEach((k, v) -> {
			// Configuration下拉 Pagination 的行： 首行 ,末行 ,首列 ,末列
			CellRangeAddressList rangeList = new CellRangeAddressList(v.getFirstRow(), v.getLastRow(), k, k);
			// 如果下拉值总数大于25 ,则使用 one 新sheet Storage ,避免 Generation 的Import Import 下拉值
			// Retrieve不到
			if (v.getSource().length > LIMIT_NUMBER) {
				// 定义sheet的 Name
				// 1.Create one 隐藏的sheet Name为 hidden + k
				String sheetName = "hidden" + k;
				Workbook workbook = writeWorkbookHolder.getWorkbook();
				Sheet hiddenSheet = workbook.createSheet(sheetName);
				for (int i = 0, length = v.getSource().length; i < length; i++) {
					// Start 的行数i ,列数k
					hiddenSheet.createRow(i).createCell(k).setCellValue(v.getSource()[i]);
				}
				Name category1Name = workbook.createName();
				category1Name.setNameName(sheetName);
				String excelLine = getExcelLine(k);
				// =hidden!$H:$1:$H$50 sheet为hidden的 H1列 Start H50行 Data Retrieve下拉数组
				String refers = "=" + sheetName + "!$" + excelLine + "$1:$" + excelLine + "$"
						+ (v.getSource().length + 1);
				// 将刚才 Configuration的sheet引用到你的下拉 Pagination 中
				DataValidationConstraint constraint = helper.createFormulaListConstraint(refers);
				DataValidation dataValidation = helper.createValidation(constraint, rangeList);
				writeSheetHolder.getSheet().addValidationData(dataValidation);
				// Configuration Storage 下拉列值得sheet为隐藏
				int hiddenIndex = workbook.getSheetIndex(sheetName);
				if (!workbook.isSheetHidden(hiddenIndex)) {
					workbook.setSheetHidden(hiddenIndex, true);
				}
			}
			// Configuration下拉 Pagination 的值
			DataValidationConstraint constraint = helper.createExplicitListConstraint(v.getSource());
			// Configuration约束
			DataValidation validation = helper.createValidation(constraint, rangeList);
			// 阻止输入非 Drop Down Box项的值
			validation.setErrorStyle(DataValidation.ErrorStyle.STOP);
			validation.setShowErrorBox(true);
			// validation.setSuppressDropDownArrow(true);
			validation.createErrorBox("提示", "请输入  Drop Down Box项中的  Content");
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
