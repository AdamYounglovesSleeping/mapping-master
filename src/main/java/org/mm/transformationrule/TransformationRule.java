package org.mm.transformationrule;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Represents the data model for the MappingMaster transformation rule
 *
 * @author Josef Hardi <josef.hardi@stanford.edu> <br>
 *         Stanford Center for Biomedical Informatics Research
 */
public class TransformationRule {

   public static final String END_COLUMN_WILDCARD = "+";
   public static final String END_ROW_WILDCARD = "+";

   private final String sheetName;
   private final String startColumn;
   private final String endColumn;
   private final String startRow;
   private final String endRow;
   private final String comment;
   private final String ruleExpression;

   public TransformationRule(@Nonnull String sheetName, @Nonnull String startColumn, @Nonnull String endColumn,
         @Nonnull String startRow, @Nonnull String endRow, @Nonnull String comment, @Nonnull String ruleExpression) {
      this.sheetName = checkNotNull(sheetName);
      this.startColumn = checkNotNull(startColumn);
      this.endColumn = checkNotNull(endColumn);
      this.startRow = checkNotNull(startRow);
      this.endRow = checkNotNull(endRow);
      this.comment = checkNotNull(comment);
      this.ruleExpression = checkNotNull(ruleExpression);
   }

   @Nonnull
   public String getRuleExpression() {
      return ruleExpression;
   }

   @Nonnull
   public String getComment() {
      return comment;
   }

   @Nonnull
   public String getSheetName() {
      return sheetName;
   }

   @Nonnull
   public String getStartColumn() {
      return startColumn;
   }

   @Nonnull
   public String getEndColumn() {
      return endColumn;
   }

   @Nonnull
   public String getStartRow() {
      return startRow;
   }

   @Nonnull
   public String getEndRow() {
      return endRow;
   }

   public boolean hasEndColumnWildcard() {
      return endColumn.equals(END_COLUMN_WILDCARD);
   }

   public boolean hasEndRowWildcard() {
      return endRow.equals(END_ROW_WILDCARD);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
     }
     if (obj == this) {
         return true;
     }
     if (!(obj instanceof TransformationRule)) {
         return false;
     }
     TransformationRule other = (TransformationRule) obj;
     return sheetName.equals(other.sheetName) && startColumn.equals(other.startColumn)
           && endColumn.equals(other.endColumn) && startRow.equals(other.startRow)
           && endRow.equals(other.endRow) && comment.equals(other.comment)
           && ruleExpression.equals(other.ruleExpression);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(sheetName, startColumn, endColumn, startRow, endRow, comment, ruleExpression);
   }

   @Override
   public String toString() {
      return MoreObjects.toStringHelper(this)
            .add("sheetName", sheetName)
            .add("startColumn", startColumn)
            .add("endColumn", endColumn)
            .add("startRow", startRow)
            .add("endRow", endRow)
            .add("comment", comment)
            .add("ruleExpression", ruleExpression)
            .toString();
   }
}