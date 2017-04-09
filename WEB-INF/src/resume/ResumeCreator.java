package resume;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

public class ResumeCreator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ResumeCreator.class.getName());
	FileHandler fileHandler;
	String path;

	public void init() throws ServletException {
		try {
			fileHandler = new FileHandler("./LogFile.log");
			logger.addHandler(fileHandler);
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Hello");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ResumeCreator fOPPdfDemo = new ResumeCreator();
		path = getServletContext().getRealPath("/");

		try {
			PrintWriter writer = new PrintWriter(new File("./ResumeHi.xml"));
			writer.println("<?xml version=\"1.0\"?>");
			writer.println("<resume>");
			writer.println("<fullname>" + request.getParameter("fname") + "</fullname>");
			writer.println("<mobile>"+request.getParameter("mobile")+"</mobile>");
			writer.println("<email>"+request.getParameter("email")+"</email>");
			writer.println("<careerobjective>");
			writer.println(request.getParameter("cobj"));
			writer.println("</careerobjective>");
			// Education
			writer.println("<education>");
			writer.println("<institute>"+request.getParameter("course1")+"</institute>");
			writer.println("<course>"+request.getParameter("inst1")+"</course>");
			writer.println("<grade>"+request.getParameter("grade1")+"</grade>");
			writer.println("</education>");
			writer.println("<education>");
			writer.println("<institute>"+request.getParameter("course2")+"</institute>");
			writer.println("<course>"+request.getParameter("inst2")+"</course>");
			writer.println("<grade>"+request.getParameter("grade2")+"</grade>");
			writer.println("</education>");
			writer.println("<education>");
			writer.println("<institute>"+request.getParameter("course3")+"</institute>");
			writer.println("<course>"+request.getParameter("inst3")+"</course>");
			writer.println("<grade>"+request.getParameter("grade3")+"</grade>");
			writer.println("</education>");
		
			// Skills
			writer.println("<skill>");
			writer.println("<skillname>"+request.getParameter("skill1")+"</skillname>");
			writer.println("</skill>");
			writer.println("<skill>");
			writer.println("<skillname>"+request.getParameter("skill2")+"</skillname>");
			writer.println("</skill>");
			writer.println("<skill>");
			writer.println("<skillname>"+request.getParameter("skill3")+"</skillname>");
			writer.println("</skill>");
			writer.println("<skill>");
			writer.println("<skillname>"+request.getParameter("skill4")+"</skillname>");
			writer.println("</skill>");
			writer.println("<skill>");
			writer.println("<skillname>"+request.getParameter("skill5")+"</skillname>");
			writer.println("</skill>");
			writer.println("<skill>");
			writer.println("<skillname>"+request.getParameter("skill6")+"</skillname>");
			writer.println("</skill>");
			// Avtivities
			writer.println("<activity>");
			writer.println("<name>"+request.getParameter("activity1")+"</name>");
			writer.println("</activity>");
			writer.println("<activity>");
			writer.println("<name>"+request.getParameter("activity2")+"</name>");
			writer.println("</activity>");
			writer.println("<activity>");
			writer.println("<name>"+request.getParameter("activity3")+"</name>");
			writer.println("</activity>");
			// Achievement
			writer.println("<achievement>");
			writer.println("<name>"+request.getParameter("achievement1")+"</name>");
			writer.println("</achievement>");
			writer.println("<achievement>");
			writer.println("<name>"+request.getParameter("achievement2")+"</name>");
			writer.println("</achievement>");
			writer.println("<achievement>");
			writer.println("<name>"+request.getParameter("achievement3")+"</name>");
			writer.println("</achievement>");

			writer.println("</resume>");
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// File xsltFile = new File("C:/Users/vsnick/Desktop/style.xsl");
		// File xsltFile = new File("C:/Users/vsnick/Desktop/style.xsl");
		File xsltFile = new File(path + "style.xsl");
		// File("C:/Users/vsnick/Desktop/ResumeHi.xml"));
		// StreamSource xmlSource = new StreamSource(new File("C:/Users/vsnick/Desktop/ResumeHi.xml"));
		StreamSource xmlSource = new StreamSource(new File("./ResumeHi.xml"));
		FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
		FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
		OutputStream out;
		out = new java.io.FileOutputStream("./yo.pdf");
		try {
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));
			Result res = new SAXResult(fop.getDefaultHandler());
			transformer.transform(xmlSource, res);
		} catch (FOPException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		response.setContentType("application/pdf");
		OutputStream output = response.getOutputStream();
		// FileInputStream in = new FileInputStream(new File("C:/Users/vsnick/Desktop/yo.pdf"));
		File resume = new File("./yo.pdf");
		FileInputStream in = new FileInputStream(resume);
		byte[] buffer = new byte[4096];
		int length;
		while ((length = in.read(buffer)) > 0) {
			output.write(buffer, 0, length);
		}
		in.close();
		out.flush();
	}

	/**
	 * Method Creates XML from the details provided by the user
	 * 
	 * @throws FileNotFoundException
	 */
	public void createXml() throws FileNotFoundException {
		// PrintWriter writer = new PrintWriter(new File("C:/Users/vsnick/Desktop/ResumeHi.xml"));

		logger.info("Xml file is created");
	}

	/**
	 * This method will convert the given XML to XSL-FO
	 * 
	 * @throws IOException
	 * @throws FOPException
	 * @throws TransformerException
	 */
	public void convertToFO() throws IOException, FOPException, TransformerException {
		// the XSL FO file
		File xsltFile = new File("F:\\Temp\\template.xsl");

		/*
		 * TransformerFactory factory = TransformerFactory.newInstance();
		 * Transformer transformer = factory.newTransformer(new
		 * StreamSource("F:\\Temp\\template.xsl"));
		 */

		// the XML file which provides the input
		StreamSource xmlSource = new StreamSource(new File("F:\\Temp\\Employees.xml"));

		// a user agent is needed for transformation
		/* FOUserAgent foUserAgent = fopFactory.newFOUserAgent(); */
		// Setup output
		OutputStream out;

		out = new java.io.FileOutputStream("F:\\Temp\\temp.fo");

		try {
			// Setup XSLT
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

			// Resulting SAX events (the generated FO) must be piped through to
			// FOP
			// Result res = new SAXResult(fop.getDefaultHandler());

			Result res = new StreamResult(out);

			// Start XSLT transformation and FOP processing
			transformer.transform(xmlSource, res);

			// Start XSLT transformation and FOP processing
			// That's where the XML is first transformed to XSL-FO and then
			// PDF is created
			transformer.transform(xmlSource, res);
		} finally {
			out.close();
		}
	}

}
