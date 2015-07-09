
package org.mm.parser.node;

import org.mm.parser.ParseException;
import org.mm.parser.ASTName;

public class NameNode
{
	private String name;
	private boolean isQuotedName;

	public NameNode(ASTName node) throws ParseException
	{
		name = node.name;
		isQuotedName = node.isQuotedName;
	}

	public String getName()
	{
		return name;
	}

	public boolean isQuoted()
	{
		return isQuotedName;
	}

	public String toString()
	{
		if (isQuotedName)
			return "'" + name + "'";
		else
			return name;
	}
}