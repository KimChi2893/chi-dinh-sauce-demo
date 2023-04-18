package commons;

import java.io.File;

public class GlobalConstants {
	public static final long LONG_TIMEOUT = 30;
	public static final String FILE_SEPARATOR = File.separator;
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String TEST_DATA_EXCEL_FILENAME = "testdata.xlsx";
	public static final String TEST_DATA_EXCEL_FILE_SHEET = "testdata";
	// ExcelFileSheet
	private static String getFolderSeparator(String folderName) {
		return PROJECT_PATH + FILE_SEPARATOR + folderName + FILE_SEPARATOR;
	}
}
