package com.clarkparsia.pellet.datatypes.types.text;

import org.mindswap.pellet.utils.ATermUtils;
import org.mindswap.pellet.utils.Namespaces;

import aterm.ATermAppl;

import com.clarkparsia.pellet.datatypes.AbstractBaseDatatype;
import com.clarkparsia.pellet.datatypes.Datatype;
import com.clarkparsia.pellet.datatypes.RestrictedDatatype;
import com.clarkparsia.pellet.datatypes.exceptions.InvalidLiteralException;

/**
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * <p>
 * Company: Clark & Parsia, LLC. <http://www.clarkparsia.com>
 * </p>
 * 
 * @author H�ctor P�rez-Urbina
 */
public class XSDNMToken extends AbstractBaseDatatype<ATermAppl> {

	private static final XSDNMToken			instance;
	private static final RDFPlainLiteral	RDF_PLAIN_LITERAL;

	static {
		RDF_PLAIN_LITERAL = RDFPlainLiteral.getInstance();

		instance = new XSDNMToken();
		RestrictedTextDatatype.addPermittedDatatype( instance.getName() );
	}

	public static XSDNMToken getInstance() {
		return instance;
	}

	private final RestrictedDatatype<ATermAppl>	dataRange;

	private XSDNMToken() {
		super(ATermUtils.makeTermAppl(Namespaces.XSD + "NMToken"));
		dataRange = new RestrictedTextDatatype(this, RestrictedTextDatatype.NMTOKEN);
	}

	public RestrictedDatatype<ATermAppl> asDataRange() {
		return dataRange;
	}

	public ATermAppl getCanonicalRepresentation(ATermAppl input) throws InvalidLiteralException {
		return getValue( input );
	}

	public ATermAppl getLiteral(Object value) {
		throw new UnsupportedOperationException();
	}

	public Datatype<?> getPrimitiveDatatype() {
		return RDF_PLAIN_LITERAL;
	}

	public ATermAppl getValue(ATermAppl literal) throws InvalidLiteralException {
		final String lexicalForm = getLexicalForm( literal );
		return RDF_PLAIN_LITERAL.getCanonicalRepresentation(
				ATermUtils.makePlainLiteral( lexicalForm ) );
	}

	public boolean isPrimitive() {
		return false;
	}
}
