/* Generated By:JJTree: Do not edit this line. ASTOWLDataSomeValuesFromCategory.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.mm.parser.node;

import org.mm.parser.MappingMasterParser;
import org.mm.parser.NodeVisitor;

public
class ASTDataSomeValuesFrom extends SimpleNode {

  public int datatype; /* XXX: Manually added */

  public ASTDataSomeValuesFrom(int id) {
    super(id);
  }

  public ASTDataSomeValuesFrom(MappingMasterParser p, int id) {
    super(p, id);
  }

  public int getDatatype() {  /* XXX: Manually added */
    return datatype;
  }

  @Override
  public void accept(NodeVisitor visitor) { /* XXX: Manually added */
    visitor.visit(this);
  }
}
/* JavaCC - OriginalChecksum=965bffac9c44656228cc6266f05d1d20 (do not edit this line) */
