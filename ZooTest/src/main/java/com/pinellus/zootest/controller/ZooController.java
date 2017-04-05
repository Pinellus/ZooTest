package com.pinellus.zootest.controller;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pinellus.zootest.domain.Domanda;
import com.pinellus.zootest.domain.TestZoo;
import com.pinellus.zootest.jaxb.JaxbUtil;
import com.pinellus.zootest.pdf.style.PdfCreator;
import com.pinellus.zootest.repository.DomandeRepository;

@Controller
public class ZooController {
	
	@Autowired
    DataSource dataSource;

    @Autowired
    DomandeRepository domandeRepository;
    
    @RequestMapping("/")
    public String index(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) throws IOException {
        
        return "index";
    }
    
    @RequestMapping("/test")
    public String test(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) throws IOException {
        
    	File file = new File("test");
    	String[] directories = file.list(new FilenameFilter() {
    	  @Override
    	  public boolean accept(File current, String name) {
    	    return new File(current, name).isDirectory();
    	  }
    	});
    	
    	model.addAttribute("dirList", directories);
    	
        return "choseTest";
    }
    
    @RequestMapping(value = "/generatePdf", method = { RequestMethod.POST })
    public String generatePdf(Model model, HttpServletResponse res, HttpServletRequest req) throws  IOException {
    	
    	String nomeTest = req.getParameter("nome");
    	Integer numTest = Integer.parseInt(req.getParameter("testnum"));
    	Integer numDom = Integer.parseInt(req.getParameter("dnum"));
    	
    	model.addAttribute("numTest", numTest);
    	model.addAttribute("numDom", numDom);
    	
    	String rootdir = "test/"+nomeTest;
    	
    	File dir = new File(rootdir);
    	dir.mkdir();
    	
    	//carico le domande
    	List<Domanda> domande = domandeRepository.findAll();
    	
	    for (int i = 1; i <= numTest; i++) {
	    	
	    	System.out.println(i);
	    	//Randomizzazione delle domande
	    	long seed = System.nanoTime();
	        Collections.shuffle(domande, new Random(seed));
	    	
	        String countFile = String.format("%02d", i);
	        
	        String pdfFileName = rootdir+"/"+countFile+"_ZooTest.pdf";
	        String xmlFileName = rootdir+"/"+countFile+"_ZooTest.xml";
	        
	        TestZoo testZoo = new TestZoo(nomeTest, numTest, numDom, xmlFileName, i, new PdfCreator().Table(pdfFileName, domande, numDom));

			JaxbUtil.Marshal(testZoo);
	    	
        }
    	
    	return "index2";
    }
    
    @RequestMapping(value = "/checkTest", method = { RequestMethod.POST })
    public String checkTest(Model model, HttpServletResponse res, HttpServletRequest req) throws  IOException {
    	
    	String nomeTest = req.getParameter("testName");
    	Integer numTest = Integer.parseInt(req.getParameter("testnum"));
    	
    	String rootdir = "test/"+nomeTest;
    	
    	String countFile = String.format("%02d", numTest);
        
        String xmlFileName = rootdir+"/"+countFile+"_ZooTest.xml";
        
        TestZoo testZoo = JaxbUtil.Unmarshal(xmlFileName);
        
        model.addAttribute("testZoo", testZoo);
    	
    	return "checkTest";
    }
    
    @RequestMapping("/hello")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) throws IOException {
        model.addAttribute("name", name);
        
        System.out.println("DATASOURCE = " + dataSource);

        model.addAttribute("domanda", domandeRepository.findOne(new Long(200)));
        
        List<Domanda> domande = domandeRepository.findAll();
        
        long seed = System.nanoTime();
        Collections.shuffle(domande, new Random(seed));
        
        new PdfCreator().Table("Test.pdf", domande, 30);
        
        /*
        try {
            String k = "<html><body> This is my Project </body></html>";
            OutputStream file = new FileOutputStream(new File("Test.pdf"));
            
            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, file);
            writer.setPageEvent(new MyFooter());
            document.open();
            //InputStream is = new ByteArrayInputStream(k.getBytes());
            //XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
            document.add(TableBuilder.createTable(domandeRepository.findOne(new Long(200))));
            document.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        
        return "index";
    }


}
