package org.mm.parser.node;

import org.mm.core.ReferenceDirectives;
import org.mm.parser.ASTDefaultDataValue;
import org.mm.parser.ASTDefaultID;
import org.mm.parser.ASTDefaultLabel;
import org.mm.parser.ASTDefaultLocationValue;
import org.mm.parser.ASTEmptyDataValueSetting;
import org.mm.parser.ASTEmptyLocationSetting;
import org.mm.parser.ASTEmptyRDFIDSetting;
import org.mm.parser.ASTEmptyRDFSLabelSetting;
import org.mm.parser.ASTEntityType;
import org.mm.parser.ASTIfExistsDirective;
import org.mm.parser.ASTIfNotExistsDirective;
import org.mm.parser.ASTLanguage;
import org.mm.parser.ASTNamespace;
import org.mm.parser.ASTPrefix;
import org.mm.parser.ASTShiftSetting;
import org.mm.parser.ASTSourceSpecification;
import org.mm.parser.ASTTypes;
import org.mm.parser.ASTValueEncoding;
import org.mm.parser.ASTValueExtractionFunction;
import org.mm.parser.MappingMasterParserConstants;
import org.mm.parser.ParseException;
import org.mm.parser.ParserUtil;
import org.mm.renderer.RendererException;
import org.mm.ss.SpreadsheetLocation;
import org.mm.parser.ASTReference;
import org.mm.parser.InternalParseException;
import org.mm.parser.Node;

import java.util.ArrayList;
import java.util.List;

public class ReferenceNode implements TypeNode, MappingMasterParserConstants
{
  private SourceSpecificationNode sourceSpecificationNode;
  private EntityTypeNode entityTypeNode = null;
  private PrefixNode prefixNode = null;
  private NamespaceNode namespaceNode = null;
  private LanguageNode languageNode = null;
  private final List<ValueEncodingNode> valueEncodingsNodes = new ArrayList<ValueEncodingNode>();
  private TypesNode typesNode = null;
  private DefaultLocationValueNode defaultLocationValueNode = null;
  private DefaultDataValueNode defaultDataValueNode = null;
  private DefaultIDNode defaultRDFIDNode = null;
  private DefaultLabelNode defaultRDFSLabelNode = null;
  private EmptyLocationSettingNode emptyLocationSettingNode = null;
  private EmptyDataValueSettingNode emptyDataValueSettingNode = null;
  private EmptyRDFIDSettingNode emptyRDFIDSettingNode = null;
  private EmptyRDFSLabelSettingNode emptyRDFSLabelSettingNode = null;
  private IfExistsDirectiveNode ifExistsDirectiveNode = null;
  private IfNotExistsDirectiveNode ifNotExistsDirectiveNode = null;
  private ValueExtractionFunctionNode valueExtractionFunctionNode = null;
  private ShiftSettingNode shiftSettingNode = null;

  private final ReferenceDirectives referenceDirectives;

  public ReferenceNode(ASTReference node) throws ParseException
  {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      Node child = node.jjtGetChild(i);

      if (ParserUtil.hasName(child, "SourceSpecification")) {
        sourceSpecificationNode = new SourceSpecificationNode((ASTSourceSpecification)child);
      } else if (ParserUtil.hasName(child, "OWLEntityType")) {
        entityTypeNode = new EntityTypeNode((ASTEntityType)child);
      } else if (ParserUtil.hasName(child, "Prefix")) {
        if (prefixNode != null)
          throw new RendererException("only one prefix directive can be specified for a Reference");
        prefixNode = new PrefixNode((ASTPrefix)child);
      } else if (ParserUtil.hasName(child, "Language")) {
        if (languageNode != null)
          throw new RendererException("only one language directive can be specified for a Reference");
        languageNode = new LanguageNode((ASTLanguage)child);
      } else if (ParserUtil.hasName(child, "Namespace")) {
        if (namespaceNode != null)
          throw new RendererException("only one namespace directive can be specified for a Reference");
        namespaceNode = new NamespaceNode((ASTNamespace)child);
      } else if (ParserUtil.hasName(child, "ValueEncoding")) {
        valueEncodingsNodes.add(new ValueEncodingNode((ASTValueEncoding)child));
      } else if (ParserUtil.hasName(child, "DefaultLocationValue")) {
        if (defaultLocationValueNode != null)
          throw new RendererException("only one default location value directive can be specified for a Reference");
        defaultLocationValueNode = new DefaultLocationValueNode((ASTDefaultLocationValue)child);
      } else if (ParserUtil.hasName(child, "DefaultDataValue")) {
        if (defaultDataValueNode != null)
          throw new RendererException("only one default data value directive can be specified for a Reference");
        defaultDataValueNode = new DefaultDataValueNode((ASTDefaultDataValue)child);
      } else if (ParserUtil.hasName(child, "DefaultID")) {
        if (defaultRDFIDNode != null)
          throw new RendererException("only one default ID directive can be specified for a Reference");
        defaultRDFIDNode = new DefaultIDNode((ASTDefaultID)child);
      } else if (ParserUtil.hasName(child, "DefaultLabel")) {
        if (defaultRDFSLabelNode != null)
          throw new RendererException("only one default label directive can be specified for a Reference");
        defaultRDFSLabelNode = new DefaultLabelNode((ASTDefaultLabel)child);
      } else if (ParserUtil.hasName(child, "EmptyLocationSetting")) {
        if (emptyLocationSettingNode != null)
          throw new RendererException("only one empty location directive can be specified for a Reference");
        emptyLocationSettingNode = new EmptyLocationSettingNode((ASTEmptyLocationSetting)child);
      } else if (ParserUtil.hasName(child, "IfExistsDirective")) {
        if (ifExistsDirectiveNode != null)
          throw new RendererException("only one if exists directive can be specified for a Reference");
        ifExistsDirectiveNode = new IfExistsDirectiveNode((ASTIfExistsDirective)child);
      } else if (ParserUtil.hasName(child, "IfNotExistsDirective")) {
        if (ifNotExistsDirectiveNode != null)
          throw new RendererException("only one if not exists directive can be specified for a Reference");
        ifNotExistsDirectiveNode = new IfNotExistsDirectiveNode((ASTIfNotExistsDirective)child);
      } else if (ParserUtil.hasName(child, "EmptyDataValueSetting")) {
        if (emptyDataValueSettingNode != null)
          throw new RendererException("only one empty data value directive can be specified for a Reference");
        emptyDataValueSettingNode = new EmptyDataValueSettingNode((ASTEmptyDataValueSetting)child);
      } else if (ParserUtil.hasName(child, "EmptyRDFIDSetting")) {
        if (emptyRDFIDSettingNode != null)
          throw new RendererException("only one empty rdf:ID directive can be specified for a Reference");
        emptyRDFIDSettingNode = new EmptyRDFIDSettingNode((ASTEmptyRDFIDSetting)child);
      } else if (ParserUtil.hasName(child, "EmptyRDFSLabelSetting")) {
        if (emptyRDFSLabelSettingNode != null)
          throw new RendererException("only one empty rdfs:Label directive can be specified for a Reference");
        emptyRDFSLabelSettingNode = new EmptyRDFSLabelSettingNode((ASTEmptyRDFSLabelSetting)child);
      } else if (ParserUtil.hasName(child, "ShiftSetting")) {
        if (shiftSettingNode != null)
          throw new RendererException("only one shift setting directive can be specified for a Reference");
        shiftSettingNode = new ShiftSettingNode((ASTShiftSetting)child);
      } else if (ParserUtil.hasName(child, "ValueExtractionFunction")) {
        if (valueExtractionFunctionNode != null)
          throw new RendererException("only one value extraction directive can be specified for a Reference");
        valueExtractionFunctionNode = new ValueExtractionFunctionNode((ASTValueExtractionFunction)child);
      } else if (ParserUtil.hasName(child, "Types")) {
        typesNode = new TypesNode((ASTTypes)child);
      } else
        throw new InternalParseException("invalid child node " + child.toString() + " for ReferenceNode");
    }

    referenceDirectives = new ReferenceDirectives(node.defaultReferenceDirectives);

    if (sourceSpecificationNode == null)
      throw new RendererException("missing source specification in reference " + toString());

    if (entityTypeNode == null) { // No entity type specified by the user - use default type
      entityTypeNode = new EntityTypeNode(node.defaultReferenceDirectives.getDefaultEntityType());
    } else
      referenceDirectives.setExplicitlySpecifiedEntityType(entityTypeNode.getEntityType());

    if (valueEncodingsNodes.isEmpty()) {
      valueEncodingsNodes.add(new ValueEncodingNode(node.defaultReferenceDirectives.getDefaultValueEncoding()));
    } else {
      referenceDirectives.setHasExplicitlySpecifiedValueEncodings();
      for (ValueEncodingNode valueEncoding : getValueEncodingNodes()) {
        if (valueEncoding.useLocationEncoding())
          referenceDirectives.setUsesLocationEncoding();
        if (valueEncoding.hasLocationWithDuplicatesEncoding())
          referenceDirectives.setUsesLocationWithDuplicatesEncoding();
      }
    }

    if (defaultLocationValueNode != null)
      referenceDirectives
        .setExplicitlySpecifiedDefaultLocationValue(defaultLocationValueNode.getDefaultLocationValue());

    if (defaultDataValueNode != null)
      referenceDirectives.setExplicitlySpecifiedDefaultDataValue(defaultDataValueNode.getDefaultDataValue());

    if (defaultRDFIDNode != null)
      referenceDirectives.setHasExplicitlySpecifiedDefaultID(defaultRDFIDNode.getDefaultRDFID());

    if (defaultRDFSLabelNode != null)
      referenceDirectives.setHasExplicitlySpecifiedDefaultLabel(defaultRDFSLabelNode.getDefaultRDFSLabel());

    if (languageNode != null)
      referenceDirectives.setHasExplicitlySpecifiedLanguage(languageNode.getLanguage());

    if (prefixNode != null)
      referenceDirectives.setHasExplicitlySpecifiedPrefix(prefixNode.getPrefix());

    if (namespaceNode != null)
      referenceDirectives.setHasExplicitlySpecifiedNamespace(namespaceNode.getNamespace());

    if (emptyLocationSettingNode != null)
      referenceDirectives
        .setHasExplicitlySpecifiedEmptyLocationDirective(emptyLocationSettingNode.getEmptyLocationSetting());

    if (emptyDataValueSettingNode != null)
      referenceDirectives
        .setHasExplicitlySpecifiedEmptyDataValueDirective(emptyDataValueSettingNode.getEmptyDataValueSetting());

    if (emptyRDFIDSettingNode != null)
      referenceDirectives.setHasExplicitlySpecifiedEmptyRDFIDDirective(emptyRDFIDSettingNode.getEmptyRDFIDSetting());

    if (emptyRDFSLabelSettingNode != null)
      referenceDirectives
        .setHasExplicitlySpecifiedEmptyRDFSLabelDirective(emptyRDFSLabelSettingNode.getEmptyRDFSLabelSetting());

    if (shiftSettingNode != null)
      referenceDirectives.setHasExplicitlySpecifiedShiftDirective(shiftSettingNode.getShiftSetting());

    if (ifExistsDirectiveNode != null)
      referenceDirectives.setHasExplicitlySpecifiedIfExistsDirective(ifExistsDirectiveNode.getIfExistsSetting());

    if (ifNotExistsDirectiveNode != null)
      referenceDirectives
        .setHasExplicitlySpecifiedIfNotExistsDirective(ifNotExistsDirectiveNode.getIfNotExistsSetting());

    if (typesNode != null)
      referenceDirectives.setHasExplicitlySpecifiedTypes();

    checkValueEncodings();
    checkInvalidExplicitDirectives();
  }

  @Override public String getNodeName()
  {
    return "Reference";
  }

  public ReferenceDirectives getReferenceDirectives()
  {
    return referenceDirectives;
  }

  public SourceSpecificationNode getSourceSpecificationNode()
  {
    return sourceSpecificationNode;
  }

  public void updateEntityType(int type)
  {
    entityTypeNode = new EntityTypeNode(type);
  }

  public EntityTypeNode getEntityTypeNode()
  {
    return entityTypeNode;
  }

  public List<ValueEncodingNode> getValueEncodingNodes()
  {
    return valueEncodingsNodes;
  }

  public TypesNode getTypesNode()
  {
    return typesNode;
  }

  public DefaultDataValueNode getDefaultDataValueNode()
  {
    return defaultDataValueNode;
  }

  public DefaultIDNode getDefaultRDFIDNode()
  {
    return defaultRDFIDNode;
  }

  public DefaultLabelNode getDefaultRDFSLabelNode()
  {
    return defaultRDFSLabelNode;
  }

  public ShiftSettingNode getShiftSettingNode()
  {
    return shiftSettingNode;
  }

  public IfExistsDirectiveNode getIfExistsDirectiveNode()
  {
    return ifExistsDirectiveNode;
  }

  public IfNotExistsDirectiveNode getIfNotExistsDirectiveNode()
  {
    return ifNotExistsDirectiveNode;
  }

  public EmptyDataValueSettingNode getEmptyDataValueSettingNode()
  {
    return emptyDataValueSettingNode;
  }

  public EmptyLocationSettingNode getEmptyLocationSettingNode()
  {
    return emptyLocationSettingNode;
  }

  public EmptyRDFIDSettingNode getEmptyRDFIDSettingNode()
  {
    return emptyRDFIDSettingNode;
  }

  public EmptyRDFSLabelSettingNode getEmptyRDFSLabelSettingNode()
  {
    return emptyRDFSLabelSettingNode;
  }

  public PrefixNode getPrefixNode()
  {
    return prefixNode;
  }

  public LanguageNode getLanguageNode()
  {
    return languageNode;
  }

  public NamespaceNode getNamespaceNode()
  {
    return namespaceNode;
  }

  public boolean hasExplicitlySpecifiedEntityType()
  {
    return referenceDirectives.hasExplicitlySpecifiedEntityType();
  }

  public boolean hasExplicitlySpecifiedPrefix()
  {
    return referenceDirectives.hasExplicitlySpecifiedPrefix();
  }

  public boolean hasExplicitlySpecifiedNamespace()
  {
    return referenceDirectives.hasExplicitlySpecifiedNamespace();
  }

  public boolean hasExplicitlySpecifiedLanguage()
  {
    return referenceDirectives.hasExplicitlySpecifiedLanguage();
  }

  public boolean hasExplicitlySpecifiedValueEncodings()
  {
    return referenceDirectives.hasExplicitlySpecifiedValueEncodings();
  }

  public boolean hasExplicitlySpecifiedDefaultLocationValue()
  {
    return referenceDirectives.hasExplicitlySpecifiedDefaultLocationValue();
  }

  public boolean hasExplicitlySpecifiedDefaultDataValue()
  {
    return referenceDirectives.hasExplicitlySpecifiedDefaultDataValue();
  }

  public boolean hasExplicitlySpecifiedDefaultID()
  {
    return referenceDirectives.hasExplicitlySpecifiedDefaultID();
  }

  public boolean hasExplicitlySpecifiedDefaultLabel()
  {
    return referenceDirectives.hasExplicitlySpecifiedDefaultLabel();
  }

  public boolean hasExplicitlySpecifiedEmptyDataValueDirective()
  {
    return referenceDirectives.hasExplicitlySpecifiedEmptyDataValueDirective();
  }

  public boolean hasExplicitlySpecifiedEmptyLocationDirective()
  {
    return referenceDirectives.hasExplicitlySpecifiedEmptyLocationDirective();
  }

  public boolean hasExplicitlySpecifiedEmptyRDFIDDirective()
  {
    return referenceDirectives.hasExplicitlySpecifiedEmptyRDFIDDirective();
  }

  public boolean hasExplicitlySpecifiedEmptyRDFSLabelDirective()
  {
    return referenceDirectives.hasExplicitlySpecifiedEmptyRDFSLabelDirective();
  }

  public boolean hasExplicitlySpecifiedShiftDirective()
  {
    return referenceDirectives.hasExplicitlySpecifiedShiftDirective();
  }

  public boolean hasExplicitlySpecifiedTypes()
  {
    return referenceDirectives.hasExplicitlySpecifiedTypes();
  }

  public boolean hasExplicitlySpecifiedIfExistsDirective()
  {
    return referenceDirectives.hasExplicitlySpecifiedIfExistsDirective();
  }

  public boolean hasExplicitlySpecifiedIfNotExistsDirective()
  {
    return referenceDirectives.hasExplicitlySpecifiedIfNotExistsDirective();
  }

  public String getActualLanguage()
  {
    return referenceDirectives.getActualLanguage();
  }

  public int getActualEmptyLocationDirective()
  {
    return referenceDirectives.getActualEmptyLocationDirective();
  }

  public int getActualEmptyDataValueDirective()
  {
    return referenceDirectives.getActualEmptyDataValueDirective();
  }

  public int getActualEmptyRDFSLabelDirective()
  {
    return referenceDirectives.getActualEmptyRDFSLabelDirective();
  }

  public int getActualShiftDirective()
  {
    return referenceDirectives.getActualShiftDirective();
  }

  public String getActualDefaultLocationValue()
  {
    return referenceDirectives.getActualDefaultLocationValue();
  }

  public String getActualDefaultDataValue()
  {
    return referenceDirectives.getActualDefaultDataValue();
  }

  public String getActualDefaultRDFID()
  {
    return referenceDirectives.getActualDefaultRDFID();
  }

  public String getActualDefaultRDFSLabel()
  {
    return referenceDirectives.getActualDefaultRDFSLabel();
  }

  public DefaultLocationValueNode getDefaultLocationValueNode()
  {
    return defaultLocationValueNode;
  }

  public boolean hasValueExtractionFunction()
  {
    return valueExtractionFunctionNode != null;
  }

  public ValueExtractionFunctionNode getValueExtractionFunctionNode()
  {
    return valueExtractionFunctionNode;
  }

  public boolean hasShiftedLocation()
  {
    return referenceDirectives.getShiftedLocation() != null;
  }

  public void setShiftedLocation(SpreadsheetLocation location)
  {
    this.referenceDirectives.setShiftedLocation(location);
  }

  public SpreadsheetLocation getShiftedLocation()
  {
    return referenceDirectives.getShiftedLocation();
  }

  public void setDefaultShiftSetting(int defaultShiftSetting)
  {
    this.referenceDirectives.setDefaultShiftDirective(defaultShiftSetting);
  }

  public boolean hasExplicitOptions()
  {
    return this.referenceDirectives.hasExplicitlySpecifiedOptions();
  }

  public boolean hasRDFSLabelValueEncoding()
  {
    if (hasExplicitlySpecifiedValueEncodings()) {
      return hasExplicitlySpecifiedRDFSLabelValueEncoding();
    } else
      return referenceDirectives.isDefaultRDFSLabelValueEncoding();
  }

  public boolean hasRDFIDValueEncoding()
  {
    if (hasExplicitlySpecifiedValueEncodings()) {
      return hasExplicitlySpecifiedRDFIDValueEncoding();
    } else
      return referenceDirectives.isDefaultRDFIDValueEncoding();
  }

  public boolean hasLiteralValueEncoding()
  {
    if (getEntityTypeNode().getEntityType().isOWLDataValue())
      return true;
    else if (hasExplicitlySpecifiedValueEncodings())
      return hasExplicitlySpecifiedLiteralValueEncoding();
    else
      return referenceDirectives.isDefaultDataValueEncoding();
  }

  public boolean hasExplicitlySpecifiedRDFSLabelValueEncoding()
  {
    if (hasExplicitlySpecifiedValueEncodings()) {
      for (ValueEncodingNode valueEncoding : getValueEncodingNodes())
        if (valueEncoding.hasRDFSLabelEncoding())
          return true;
      return false;
    } else
      return false;
  }

  public boolean hasExplicitlySpecifiedRDFIDValueEncoding()
  {
    if (hasExplicitlySpecifiedValueEncodings()) {
      for (ValueEncodingNode valueEncoding : getValueEncodingNodes())
        if (valueEncoding.hasRDFIDEncoding())
          return true;
      return false;
    } else
      return false;
  }

  public boolean hasExplicitlySpecifiedLiteralValueEncoding()
  {
    if (hasExplicitlySpecifiedValueEncodings()) {
      for (ValueEncodingNode valueEncoding : getValueEncodingNodes())
        if (valueEncoding.hasDataValueEncoding())
          return true;
      return false;
    } else
      return false;
  }

  public ValueEncodingNode getRDFIDValueEncodingNode()
  {
    for (ValueEncodingNode valueEncoding : getValueEncodingNodes())
      if (valueEncoding.hasRDFIDEncoding())
        return valueEncoding;

    return null;
  }

  public ValueEncodingNode getRDFSLabelValueEncodingNode()
  {
    for (ValueEncodingNode valueEncoding : getValueEncodingNodes())
      if (valueEncoding.hasRDFSLabelEncoding())
        return valueEncoding;

    return null;
  }

  public ValueEncodingNode getLiteralValueEncodingNode()
  {
    for (ValueEncodingNode valueEncoding : getValueEncodingNodes())
      if (valueEncoding.hasDataValueEncoding())
        return valueEncoding;

    return null;
  }

  @Override public String toString()
  {
    String representation = "";
    boolean atLeastOneOptionProcessed = false;

    representation += getSourceSpecificationNode();

    if (hasExplicitOptions())
      representation += "(";

    if (hasExplicitlySpecifiedEntityType()) {
      representation += entityTypeNode;
      atLeastOneOptionProcessed = true;
    }

    if (hasValueExtractionFunction()) {
      if (atLeastOneOptionProcessed)
        representation += " ";
      else
        atLeastOneOptionProcessed = true;
      representation += valueExtractionFunctionNode;
    }

    if (hasExplicitlySpecifiedValueEncodings()) {
      boolean isFirst = true;
      if (atLeastOneOptionProcessed)
        representation += " ";
      for (ValueEncodingNode valueEncoding : valueEncodingsNodes) {
        if (!isFirst)
          representation += " ";
        representation += valueEncoding;
        isFirst = false;
      }
      atLeastOneOptionProcessed = true;
    }

    if (hasExplicitlySpecifiedDefaultLocationValue()) {
      if (atLeastOneOptionProcessed)
        representation += " ";
      representation += defaultLocationValueNode;
      atLeastOneOptionProcessed = true;
    }

    if (hasExplicitlySpecifiedDefaultDataValue()) {
      if (atLeastOneOptionProcessed)
        representation += " ";
      representation += defaultDataValueNode;
      atLeastOneOptionProcessed = true;
    }

    if (hasExplicitlySpecifiedDefaultID()) {
      if (atLeastOneOptionProcessed)
        representation += " ";
      representation += defaultRDFIDNode;
      atLeastOneOptionProcessed = true;
    }

    if (hasExplicitlySpecifiedDefaultLabel()) {
      if (atLeastOneOptionProcessed)
        representation += " ";
      representation += defaultRDFSLabelNode;
      atLeastOneOptionProcessed = true;
    }

    if (hasExplicitlySpecifiedLanguage()) {
      if (atLeastOneOptionProcessed)
        representation += " ";
      representation += languageNode;
      atLeastOneOptionProcessed = true;
    }

    if (hasExplicitlySpecifiedPrefix()) {
      if (atLeastOneOptionProcessed)
        representation += " ";
      representation += prefixNode;
      atLeastOneOptionProcessed = true;
    }

    if (hasExplicitlySpecifiedNamespace()) {
      if (atLeastOneOptionProcessed)
        representation += " ";
      representation += namespaceNode;
      atLeastOneOptionProcessed = true;
    }

    if (hasExplicitlySpecifiedEmptyLocationDirective()) {
      if (atLeastOneOptionProcessed)
        representation += " ";
      representation += emptyLocationSettingNode;
      atLeastOneOptionProcessed = true;
    }

    if (hasExplicitlySpecifiedEmptyDataValueDirective()) {
      if (atLeastOneOptionProcessed)
        representation += " ";
      representation += emptyDataValueSettingNode;
      atLeastOneOptionProcessed = true;
    }

    if (hasExplicitlySpecifiedEmptyRDFIDDirective()) {
      if (atLeastOneOptionProcessed)
        representation += " ";
      representation += emptyRDFIDSettingNode;
      atLeastOneOptionProcessed = true;
    }

    if (hasExplicitlySpecifiedEmptyRDFSLabelDirective()) {
      if (atLeastOneOptionProcessed)
        representation += " ";
      representation += emptyRDFSLabelSettingNode;
      atLeastOneOptionProcessed = true;
    }

    if (hasExplicitlySpecifiedIfExistsDirective()) {
      if (atLeastOneOptionProcessed)
        representation += " ";
      representation += ifExistsDirectiveNode;
      atLeastOneOptionProcessed = true;
    }

    if (hasExplicitlySpecifiedIfNotExistsDirective()) {
      if (atLeastOneOptionProcessed)
        representation += " ";
      representation += ifNotExistsDirectiveNode;
      atLeastOneOptionProcessed = true;
    }

    if (hasExplicitlySpecifiedShiftDirective()) {
      if (atLeastOneOptionProcessed)
        representation += " ";
      representation += shiftSettingNode;
      atLeastOneOptionProcessed = true;
    }

    if (hasExplicitlySpecifiedTypes()) {
      if (atLeastOneOptionProcessed)
        representation += " ";
      representation += getTypesNode();
      atLeastOneOptionProcessed = true;
    }

    if (hasExplicitOptions())
      representation += ")";

    return representation;
  }

  private void checkValueEncodings() throws RendererException
  {
    boolean location = false, dataValue = false, locationWithDuplicates = false, rdfID = false, rdfsLabel = false;

    if (valueEncodingsNodes.isEmpty())
      throw new RendererException("empty value encoding in reference");

    for (ValueEncodingNode valueEncoding : valueEncodingsNodes) {

      if (valueEncoding.hasDataValueEncoding())
        dataValue = true;

      if (valueEncoding.useLocationEncoding())
        location = true;

      if (valueEncoding.hasLocationWithDuplicatesEncoding())
        locationWithDuplicates = true;

      if (valueEncoding.hasRDFIDEncoding())
        rdfID = true;

      if (valueEncoding.hasRDFSLabelEncoding())
        rdfsLabel = true;
    }

    if (dataValue && (location || locationWithDuplicates))
      throw new RendererException(
        "location encoding in reference " + toString() + " invalid because it also uses data value encoding");

    if ((location || locationWithDuplicates) && (rdfID || rdfsLabel))
      throw new RendererException(
        "location encoding in reference " + toString() + " invalid because it also uses rdf:ID or rdfs:label encoding");

    if (dataValue && (rdfID || rdfsLabel))
      throw new RendererException("data value encoding in reference " + toString()
        + " invalid because it also uses rdf:ID or rdfs:label encoding");
  }

  private void checkInvalidExplicitDirectives() throws ParseException
  {
    if (referenceDirectives.hasExplicitlySpecifiedLanguage() && entityTypeNode.getEntityType().isOWLDataValue())
      throw new ParseException(
        "use of language specification in reference " + toString() + " invalid because it is an OWL data value");

    if (referenceDirectives.hasExplicitlySpecifiedPrefix() && entityTypeNode.getEntityType().isOWLDataValue())
      throw new ParseException("use of prefix in reference " + toString() + " invalid because it is an OWL data value");

    if (referenceDirectives.hasExplicitlySpecifiedNamespace() && entityTypeNode.getEntityType().isOWLDataValue())
      throw new ParseException(
        "use of namespace in reference " + toString() + " invalid because it is an OWL data value");

    if (referenceDirectives.hasExplicitlySpecifiedEmptyDataValueDirective() && !entityTypeNode.getEntityType()
      .isOWLDataValue())
      throw new ParseException(
        "use of empty data value setting in reference " + toString() + " invalid because it is not an OWL data value");

    if (referenceDirectives.hasExplicitlySpecifiedEntityType() && entityTypeNode.getEntityType().isOWLDataValue()
      && hasExplicitlySpecifiedTypes())
      throw new ParseException(
        "entity type " + entityTypeNode.getEntityType().getTypeName() + " in reference " + toString()
          + " should not have defining types because it is an OWL data value");
  }

}