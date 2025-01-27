package com.matariky.excel;

import java.lang.annotation.*;

/**
 * 标注 Export 的列为下拉框Type ,并为下拉框 Configuration Content
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelSelected {
	/**
	 * 固定下拉 Content
	 */
	String[] source() default {};

	/**
	 * 动态下拉 Content
	 */
	Class<? extends ExcelDynamicSelect>[] sourceClass() default {};

	/**
	 * Configuration下拉框的起始行 ,默认为第二行
	 */
	int firstRow() default 1;

	/**
	 * Configuration下拉框的结束行 ,默认为最后一行
	 */
	int lastRow() default 0x10000;
}
