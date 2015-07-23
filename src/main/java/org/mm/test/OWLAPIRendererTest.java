package org.mm.test;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.mm.exceptions.MappingMasterException;
import org.mm.parser.ASTExpression;
import org.mm.parser.MappingMasterParser;
import org.mm.parser.ParseException;
import org.mm.parser.SimpleNode;
import org.mm.parser.node.ExpressionNode;
import org.mm.renderer.text.TextRenderer;
import org.mm.rendering.text.TextRendering;
import org.mm.ss.SpreadSheetDataSource;
import org.mm.ss.SpreadsheetLocation;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class OWLAPIRendererTest
{
	public static void main(String args[])
	{

		try {
			String expressionText = "Class: @A1";
			MappingMasterParser parser = new MappingMasterParser(new ByteArrayInputStream(expressionText.getBytes()));
			OWLOntologyManager ontologyManager = OWLManager.createOWLOntologyManager();
			OWLOntology ontology = ontologyManager.createOntology();
			File file = File.createTempFile("temp", "xlsx");
			WritableWorkbook writableWorkbook = Workbook.createWorkbook(file);
			WritableSheet sheet = writableWorkbook.createSheet("s1", 0);
			Label label0 = new Label(0, 0, "Derp");
			sheet.addCell(label0);
			writableWorkbook.write();
			writableWorkbook.close();

			Workbook workbook = Workbook.getWorkbook(file);

			SpreadSheetDataSource dataSource = new SpreadSheetDataSource(workbook);
			SpreadsheetLocation currentLocation = new SpreadsheetLocation("s1", 1, 1);

			dataSource.setCurrentLocation(currentLocation);
			//OWLAPICoreRenderer coreRenderer = new OWLAPICoreRenderer(ontology, dataSource);
			TextRenderer renderer = new TextRenderer(dataSource);

			SimpleNode simpleNode = parser.expression();
			ExpressionNode expressionNode = new ExpressionNode((ASTExpression)simpleNode);

			//simpleNode.dump(" ");

			if (expressionNode.hasMMExpression()) {
				//System.out.println("expressionNode.toString(): " + expressionNode.toString());
				Optional<? extends TextRendering> rendering = renderer.renderMMExpression(expressionNode.getMMExpressionNode());
				if (rendering.isPresent())
					System.out.println("textRendering: " + rendering.get().getTextRendering());
				else
					System.out.println("Nothing rendered!");
//				if (rendering.isPresent()) {
//					Set<OWLAxiom> axioms = rendering.get().getOWLAxioms();
//				}
			}

		} catch (MappingMasterException | ParseException | OWLOntologyCreationException | IOException | jxl.read.biff.BiffException | WriteException e) {
			System.err.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void Usage()
	{
		System.err.println("Usage: " + OWLAPIRendererTest.class.getName() + " <Expression>");
		System.exit(-1);
	}
}