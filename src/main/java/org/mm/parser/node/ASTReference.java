/* Generated By:JJTree: Do not edit this line. ASTReference.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.mm.parser.node;

import javax.annotation.Nonnull; /* XXX: Manually added */

import org.mm.directive.ReferenceDirectives; /* XXX: Manually added */
import org.mm.parser.MappingMasterParser;
import org.mm.parser.NodeVisitor;

public
class ASTReference extends SimpleNode {

  ReferenceDirectives referenceDirectives; /* XXX: Manually added */

  public ASTReference(int id) {
    super(id);
  }

  public ASTReference(MappingMasterParser p, int id) {
    super(p, id);
  }

  @Nonnull
  public ReferenceDirectives getDirectives() { /* XXX: Manually added */
     return referenceDirectives;
  }

  @Override
  public void accept(NodeVisitor visitor) { /* XXX: Manually added */
    visitor.visit(this);
  }
}
/* JavaCC - OriginalChecksum=a9ef9dc174d09ff7bc13bfe6b966a6cd (do not edit this line) */