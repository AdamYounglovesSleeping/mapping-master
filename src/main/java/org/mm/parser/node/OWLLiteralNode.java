package org.mm.parser.node;

import org.mm.parser.ASTOWLLiteral;
import org.mm.parser.ASTBooleanLiteral;
import org.mm.parser.ASTFloatLiteral;
import org.mm.parser.ASTIntegerLiteral;
import org.mm.parser.ASTStringLiteral;
import org.mm.parser.InternalParseException;
import org.mm.parser.Node;
import org.mm.parser.ParseException;
import org.mm.parser.ParserUtil;

public class OWLLiteralNode implements  MMNode
{
  private IntegerLiteralNode integerLiteralNode;
  private FloatLiteralNode floatLiteralNode;
  private StringLiteralNode stringLiteralNode;
  private BooleanLiteralNode booleanLiteralNode;

  public OWLLiteralNode(ASTOWLLiteral node) throws ParseException
  {
    if (node.jjtGetNumChildren() != 1)
      throw new InternalParseException("expecting one child of node " + getNodeName());
    else {
      Node child = node.jjtGetChild(0);
      if (ParserUtil.hasName(child, "IntegerLiteral"))
        integerLiteralNode = new IntegerLiteralNode((ASTIntegerLiteral)child);
      else if (ParserUtil.hasName(child, "FloatLiteral"))
        floatLiteralNode = new FloatLiteralNode((ASTFloatLiteral)child);
      else if (ParserUtil.hasName(child, "StringLiteral"))
        stringLiteralNode = new StringLiteralNode((ASTStringLiteral)child);
      else if (ParserUtil.hasName(child, "BooleanLiteral"))
        booleanLiteralNode = new BooleanLiteralNode((ASTBooleanLiteral)child);
      else
        throw new InternalParseException("unexpected child node " + child.toString() + " for node " + getNodeName());
    }
  }

  public boolean isInteger()
  {
    return this.integerLiteralNode != null;
  }

  public boolean isFloat()
  {
    return floatLiteralNode != null;
  }

  public boolean isString()
  {
    return stringLiteralNode != null;
  }

  public boolean isBoolean()
  {
    return booleanLiteralNode != null;
  }

  public IntegerLiteralNode getIntLiteralNode()
  {
    return integerLiteralNode;
  }

  public FloatLiteralNode getFloatLiteralNode()
  {
    return floatLiteralNode;
  }

  public StringLiteralNode getStringLiteralNode()
  {
    return stringLiteralNode;
  }

  public BooleanLiteralNode getBooleanLiteralNode()
  {
    return booleanLiteralNode;
  }

  @Override public String getNodeName()
  {
    return "OWLLiteral";
  }

  public String toString()
  {
    if (isInteger())
      return integerLiteralNode.toString();
    else if (isFloat())
      return floatLiteralNode.toString();
    else if (isString())
      return stringLiteralNode.toString();
    else if (isBoolean())
      return booleanLiteralNode.toString();
    else
      return "";
  }
}
