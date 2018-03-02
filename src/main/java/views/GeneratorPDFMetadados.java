package views;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

public class GeneratorPDFMetadados {

    public static void main(String[] args) {
        // criação do objeto documento
        Document document = new Document();
        java.util.Date d = new Date();
        String dStr = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
        try {

            PdfWriter.getInstance(document, new FileOutputStream("/home/raquelmelo/PDF_DevMedia.pdf"));
            document.open();
            // adicionando um parágrafo ao documento
            Paragraph titulo = new Paragraph("Arquidiocese de Teresina\nParóquia Nossa Senhora de Fátima\nEncontro de Casais com Cristo-ECC\nTeresina " + dStr);

            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            
            Paragraph espacoEMBRANCO = new Paragraph("\n");
            document.add(espacoEMBRANCO); 
            
            Chunk glue = new Chunk(new VerticalPositionMark());
            Paragraph p = new Paragraph("Acolhida");
            p.add(new Chunk(glue));
            p.add("Diretor Espiritual");
            p.add(new Chunk(glue));
            p.add("Apresentador");
            document.add(p); 
            //adicionarei os integrantes abaixo de cada titulo
            
            Chunk glue1 = new Chunk(new VerticalPositionMark());
            Paragraph p2 = new Paragraph("Canto");
            p2.add(new Chunk(glue));
            p2.add("Boa Vontade");
            p2.add(new Chunk(glue));
            p2.add("Recepção");
            document.add(p2);
            
            Chunk glue2 = new Chunk(new VerticalPositionMark());
            Paragraph p3 = new Paragraph("Palestra");
            p3.add(new Chunk(glue));
            p3.add("Compras");
            p3.add(new Chunk(glue));
            p3.add("Visitação");
            document.add(p3);
            
            Chunk glue3 = new Chunk(new VerticalPositionMark());
            Paragraph p4 = new Paragraph("Secretária");
            p4.add(new Chunk(glue));
            p4.add("Circulo Estudo");
            p4.add(new Chunk(glue));
            p4.add("Liturgia");
            document.add(p4);
            
            Chunk glue4 = new Chunk(new VerticalPositionMark());
            Paragraph p5 = new Paragraph("Mini Mercado e Café");
            p5.add(new Chunk(glue));
            p5.add("Cozinha");
            p5.add(new Chunk(glue));
            p5.add("Ordem e Limpeza");
            document.add(p5);
            
            Chunk glue5 = new Chunk(new VerticalPositionMark());
            Paragraph p6 = new Paragraph("Vigília na Comunidade");
            p6.add(new Chunk(glue));
            p6.add("Ficha");
            p6.add(new Chunk(glue));
            p6.add("Financas");
            document.add(p6);
            
            Chunk glue6 = new Chunk(new VerticalPositionMark());
            Paragraph p7 = new Paragraph("Pós Encontro");
            p7.add(new Chunk(glue));
            p7.add("Montagem e Palestra");
            p7.add(new Chunk(glue));
            p7.add("");
            document.add(p7);

            document.addSubject("Encontro de Casais com Cristo");
            document.addKeywords("ECC");
            document.addCreator("Raquel Melo e Kenad Araújo");
            document.addAuthor("Raquel Melo");
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        document.close();
    }

    public Map<String, String> formaMap() {
        Map<String, String> mapaNomes = new HashMap<String, String>();
        mapaNomes.put("Coodenador", "");
        mapaNomes.put("Maria do Carmo", "");
        mapaNomes.put("Claudinei Silva", "");

        return mapaNomes;
    }
}
