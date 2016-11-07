package org.mm.workbook;

/**
 * @author Josef Hardi <josef.hardi@stanford.edu> <br>
 *         Stanford Center for Biomedical Informatics Research
 */
public class CellLocation {

   private final String sheetName;
   private final int columnNumber, rowNumber;

   /**
    * Constructs a cell location, or a cell coordinate in a spreadsheet
    *
    * @param sheetName
    *           The name of the sheet
    * @param columnNumber
    *           The physical column number (start from 1)
    * @param rowNumber
    *           The physical row number (start from 1)
    */
   public CellLocation(String sheetName, int columnNumber, int rowNumber) {
      this.sheetName = sheetName;
      this.columnNumber = columnNumber;
      this.rowNumber = rowNumber;
   }

   public String getSheetName() {
      return sheetName;
   }

   /**
    * Get the logical column number (0-based)
    *
    * @return The column number, starts from 0.
    */
   public int getColumnIndex() {
      return columnNumber - 1;
   }

   /**
    * Get the physical column number as usually presented in a spreadsheet
    * application (1-based)
    *
    * @return The column number, starts from 1.
    */
   public int getPhysicalColumnNumber() {
      return columnNumber;
   }

   public String getColumnName() {
      return WorkbookUtils.columnNumber2Name(getPhysicalColumnNumber());
   }

   /**
    * Get the logical row number (0-based)
    *
    * @return The row number, starts from 0.
    */
   public int getRowIndex() {
      return rowNumber - 1;
   }

   /**
    * Get the physical row number as usually presented in a spreadsheet
    * application (1-based).
    *
    * @return The row number, starts from 1.
    */
   public int getPhysicalRowNumber() {
      return rowNumber;
   }

   /**
    * Get the physical cell location (1-based)
    *
    * @return The cell location in format [COLUMN_NAME][ROW_NUMBER], e.g., A1,
    *         B3, H24
    */
   public String getCellLocation() {
      return getColumnName() + getPhysicalRowNumber();
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      CellLocation that = (CellLocation) o;

      if (columnNumber != that.columnNumber) return false;
      if (rowNumber != that.rowNumber) return false;
      return !(sheetName != null ? !sheetName.equals(that.sheetName) : that.sheetName != null);

   }

   @Override
   public int hashCode() { // TODO: Use Guava
      int result = sheetName != null ? sheetName.hashCode() : 0;
      result = 31 * result + columnNumber;
      result = 31 * result + rowNumber;
      return result;
   }

   public String getFullyQualifiedLocation() {
      return "'" + getSheetName() + "'!" + getCellLocation();
   }

   public String toString() { // TODO: Use Guava
      return getFullyQualifiedLocation();
   }
}
