package org.mm.renderer;

import org.mm.parser.node.ReferenceNode;

import java.util.Optional;

public interface ReferenceRenderer
{
	int getDefaultValueEncoding();

	int getDefaultReferenceType();

	int getDefaultOWLPropertyType();

	int getDefaultOWLPropertyAssertionObjectType();

	int getDefaultOWLDataPropertyValueType();

	void setDefaultValueEncoding(int defaultValueEncoding);

	void setDefaultReferenceType( int defaultReferenceType);

	void setDefaultOWLPropertyType(int defaultOWLPropertyType);

	void setDefaultOWLPropertyAssertionObjectType(int defaultOWLPropertyAssertionObjectType);

	void setDefaultOWLDataPropertyValueType(int defaultOWLDataPropertyValueType);

	Optional<? extends Rendering> renderReference(ReferenceNode referenceNode) throws RendererException;
}