package org.mm.workbook;

import org.mm.parser.node.ReferenceNode;
import org.mm.parser.node.SourceSpecificationNode;
import org.mm.renderer.RendererException;

import java.util.List;
import java.util.Optional;

/**
 * Represents the workbook used as the data source for importing the axioms.
 *
 * @author Josef Hardi <josef.hardi@stanford.edu> <br>
 *         Stanford Center for Biomedical Informatics Research
 */
public interface Workbook {

   String getLocationValue(SpreadsheetLocation location, ReferenceNode node) throws RendererException;

   String getLocationValue(SpreadsheetLocation location) throws RendererException;

   String getLocationValueWithShifting(SpreadsheetLocation location, ReferenceNode node) throws RendererException;

   void setCurrentLocation(SpreadsheetLocation location);

   Optional<SpreadsheetLocation> getCurrentLocation();

   boolean hasCurrentLocation();

   SpreadsheetLocation resolveLocation(SourceSpecificationNode node) throws RendererException;

   Sheet getSheet(String sheetName);

   List<Sheet> getSheets();

   List<String> getSheetNames();
}
