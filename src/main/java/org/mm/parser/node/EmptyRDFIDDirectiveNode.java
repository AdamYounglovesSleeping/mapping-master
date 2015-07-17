
package org.mm.parser.node;

import org.mm.parser.ASTEmptyRDFIDSetting;
import org.mm.parser.MappingMasterParserConstants;
import org.mm.parser.ParseException;
import org.mm.parser.ParserUtil;

public class EmptyRDFIDDirectiveNode implements MMNode, MappingMasterParserConstants
{
  private int emptyRDFIDSetting;
  
  public EmptyRDFIDDirectiveNode(ASTEmptyRDFIDSetting node) throws ParseException
  {
  	emptyRDFIDSetting = node.emptyRDFIDSetting;
  }
  
  public int getEmptyRDFIDSetting() { return emptyRDFIDSetting; }
  
  public boolean isErrorIfEmpty() { return emptyRDFIDSetting == MM_ERROR_IF_EMPTY_ID; }
  public boolean isWarningIfEmpty() { return emptyRDFIDSetting == MM_WARNING_IF_EMPTY_ID; }
  public boolean isSkipIfEmpty() { return emptyRDFIDSetting == MM_SKIP_IF_EMPTY_ID; }
  public boolean isProcessIfEmpty() { return emptyRDFIDSetting == MM_PROCESS_IF_EMPTY_ID; }

  @Override public String getNodeName()
  {
    return "EmptyRDFIDDirective";
  }

  public String toString() { return ParserUtil.getTokenName(emptyRDFIDSetting); }
}
