package org.mm.parser.node;

import org.mm.parser.ASTOWLClassExpression;
import org.mm.parser.ASTOWLSubclassOf;
import org.mm.parser.InternalParseException;
import org.mm.parser.Node;
import org.mm.parser.ParseException;
import org.mm.parser.ParserUtil;

import java.util.ArrayList;
import java.util.List;

public class OWLSubclassOfNode implements  MMNode
{
  private List<OWLClassExpressionNode> classExpressionNodes;

  public OWLSubclassOfNode(ASTOWLSubclassOf node) throws ParseException
  {
    classExpressionNodes = new ArrayList<>();

    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      Node child = node.jjtGetChild(i);

      if (ParserUtil.hasName(child, "OWLClassExpression")) {
        OWLClassExpressionNode owlClassExpression = new OWLClassExpressionNode((ASTOWLClassExpression)child);
        classExpressionNodes.add(owlClassExpression);
      } else
        throw new InternalParseException(getNodeName() +
          "node expecting OWLClassExpression child, got " + child.toString());
    }
  }

  public List<OWLClassExpressionNode> getClassExpressionNodes()
  {
    return classExpressionNodes;
  }

  @Override public String getNodeName()
  {
    return "OWLSubclassOf";
  }

  public String toString()
  {
    String representation = " SubClassOf: ";

    if (classExpressionNodes.size() == 1)
      representation += classExpressionNodes.get(0).toString();
    else {
      boolean isFirst = true;

      for (OWLClassExpressionNode owlClassExpression : classExpressionNodes) {
        if (!isFirst)
          representation += ", ";
        representation += owlClassExpression.toString();
        isFirst = false;
      }
    }

    return representation;
  }
}
