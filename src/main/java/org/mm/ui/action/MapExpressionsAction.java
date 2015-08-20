package org.mm.ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.mm.core.MappingExpression;
import org.mm.exceptions.MappingMasterException;
import org.mm.renderer.RendererException;
import org.mm.ss.SpreadSheetDataSource;
import org.mm.ss.SpreadSheetUtil;
import org.mm.ss.SpreadsheetLocation;
import org.mm.ui.MMApplication;
import org.mm.ui.dialog.MMApplicationDialogManager;
import org.mm.ui.model.DataSourceModel;
import org.mm.ui.model.MMApplicationModel;
import org.mm.ui.model.MappingsExpressionsModel;
import org.mm.ui.view.MMApplicationView;

public class MapExpressionsAction implements ActionListener
{
  private final MMApplication application;

  public MapExpressionsAction(MMApplication application) { this.application = application; }

  public void actionPerformed(ActionEvent e)
  {
    if (!getMappingExpressionsModel().hasMappingExpressions())
      getApplicationDialogManager().showMessageDialog(getApplicationView(), "No mappings defined!");
    else if (!getDataSourceModel().hasDataSource())
      getApplicationDialogManager().showMessageDialog(getApplicationView(), "No data source loaded!");
    else {
      try {
        Set<MappingExpression> mappingExpressions = getMappingExpressionsModel().getMappingExpressions(true);
        SpreadSheetDataSource dataSource = getDataSourceModel().getDataSource();
        Workbook workbook = dataSource.getWorkbook();

        for (MappingExpression mappingExpression : mappingExpressions) {
          String sheetName = mappingExpression.getSourceSheetName();
          Sheet sheet = workbook.getSheet(sheetName);
          int startColumnNumber = SpreadSheetUtil.columnName2Number(mappingExpression.getStartColumn());
          int startRowNumber = SpreadSheetUtil.row2Number(mappingExpression.getStartRow());
          int finishColumnNumber = mappingExpression.hasFinishColumnWildcard() ?
            sheet.getRow(startRowNumber).getLastCellNum() :
            SpreadSheetUtil.columnName2Number(mappingExpression.getFinishColumn());
          int finishRowNumber = mappingExpression.hasFinishRowWildcard() ?
            sheet.getLastRowNum() :
            SpreadSheetUtil.row2Number(mappingExpression.getFinishRow());

          if (startColumnNumber > finishColumnNumber)
            throw new RendererException("start column after finish column in expression " + mappingExpression);
          if (startRowNumber > finishRowNumber)
            throw new RendererException("start row after finish row in expression " + mappingExpression);

          SpreadsheetLocation finishLocation = new SpreadsheetLocation(sheetName, finishColumnNumber, finishRowNumber);
          SpreadsheetLocation startLocation = new SpreadsheetLocation(sheetName, startColumnNumber, startRowNumber);
          SpreadsheetLocation currentLocation = new SpreadsheetLocation(sheetName, startColumnNumber, startRowNumber);

          dataSource.setCurrentLocation(currentLocation);
          //TODO renderer.renderExpression(mappingExpression);
          while (!currentLocation.equals(finishLocation)) {
            currentLocation = incrementLocation(currentLocation, startLocation, finishLocation);
            dataSource.setCurrentLocation(currentLocation);
            // TODO renderer.renderExpression(mappingExpression);
          }
        }
        getApplicationDialogManager().showMessageDialog(getApplicationView(), "Mappings performed successfully.");
      } catch (MappingMasterException | RendererException ex) {
        ex.printStackTrace();
        getApplicationDialogManager().showErrorMessageDialog(getApplicationView(), "Mapping error: " + ex.getMessage());
      }
    }
  }

  private static SpreadsheetLocation incrementLocation(SpreadsheetLocation currentLocation,
    SpreadsheetLocation startLocation, SpreadsheetLocation endLocation) throws RendererException
  {
    if (currentLocation.getPhysicalRowNumber() < endLocation.getPhysicalRowNumber())
      return new SpreadsheetLocation(
          currentLocation.getSheetName(), currentLocation.getPhysicalColumnNumber(), currentLocation.getPhysicalRowNumber());
    else if (currentLocation.getPhysicalRowNumber() == endLocation.getPhysicalRowNumber()) {
      if (currentLocation.getPhysicalColumnNumber() < endLocation.getPhysicalColumnNumber()) {
        return new SpreadsheetLocation(
            currentLocation.getSheetName(), currentLocation.getPhysicalColumnNumber(), startLocation.getPhysicalRowNumber());
      } else {
        throw new RendererException("incrementLocation called redundantly");
      }
    } else
      throw new RendererException("incrementLocation called redundantly");
  }

  private MappingsExpressionsModel getMappingExpressionsModel()
  {
    return this.application.getApplicationModel().getMappingExpressionsModel();
  }

  private DataSourceModel getDataSourceModel() { return this.application.getApplicationModel().getDataSourceModel(); }

  private MMApplicationView getApplicationView() { return this.application.getApplicationView(); }

  private MMApplicationModel getApplicationModel() { return this.application.getApplicationModel(); }

  private MMApplicationDialogManager getApplicationDialogManager()
  {
    return getApplicationView().getApplicationDialogManager();
  }
}
