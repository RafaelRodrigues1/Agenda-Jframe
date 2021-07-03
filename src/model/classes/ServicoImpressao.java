package model.classes;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.time.format.DateTimeFormatter;

/**
 * @author RafaelRodrigues1
 */
public final class ServicoImpressao {
    
    private static final DateTimeFormatter dataForm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter horaForm = DateTimeFormatter.ofPattern("HH:mm");
    private static List<Compromisso> compromissoList;
    private static Document documento;
    private static PdfPTable tabela;
    
    public static void run(List<Compromisso> compromissoL){
        compromissoList = compromissoL;
        documento = new Document();
        tabela = new PdfPTable(4);
        try {
            PdfWriter.getInstance(documento, new FileOutputStream("Relatório.pdf"));
            documento.open();
            cabecalho();
            corpo();
            documento.add(tabela);
            imprimir();
        } catch (FileNotFoundException | DocumentException ex) {
            System.out.println(ex);
        }
    }
    
    public static void cabecalho(){
        try {
            Paragraph titulo = new Paragraph();
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.add(new Chunk("Relatório de compromissos", new Font(Font.FontFamily.HELVETICA, 20f)));
            documento.add(titulo);
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            PdfPCell cel1 = new PdfPCell(new Paragraph("Data", new Font(Font.FontFamily.TIMES_ROMAN, 14f)));
            PdfPCell cel2 = new PdfPCell(new Paragraph("Horário", new Font(Font.FontFamily.TIMES_ROMAN, 14f)));
            PdfPCell cel3 = new PdfPCell(new Paragraph("Descrição", new Font(Font.FontFamily.TIMES_ROMAN, 14f)));
            PdfPCell cel4 = new PdfPCell(new Paragraph("Local", new Font(Font.FontFamily.TIMES_ROMAN, 14f)));
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            tabela.addCell(cel3);
            tabela.addCell(cel4);
        } catch (DocumentException ex) {
            System.out.println(ex);
        }
    }
    
    public static void corpo(){
        for(Compromisso compromisso : compromissoList){
            String data = dataForm.format(compromisso.getData());
            String hora = horaForm.format(compromisso.getHora());
            PdfPCell cel1 = new PdfPCell(new Paragraph(data, new Font(Font.FontFamily.TIMES_ROMAN, 11f)));
            PdfPCell cel2 = new PdfPCell(new Paragraph(hora, new Font(Font.FontFamily.TIMES_ROMAN, 11f)));
            PdfPCell cel3 = new PdfPCell(new Paragraph(compromisso.getDescricao(), new Font(Font.FontFamily.TIMES_ROMAN, 11f)));
            PdfPCell cel4 = new PdfPCell(new Paragraph(compromisso.getLocal(), new Font(Font.FontFamily.TIMES_ROMAN, 11f)));
            tabela.addCell(cel1);
            tabela.addCell(cel2);
            tabela.addCell(cel3);
            tabela.addCell(cel4);
        }
    }
    
    public static void imprimir(){
        if(documento != null && documento.isOpen()){
            documento.close();
            try {
                Desktop.getDesktop().open(new File("Relatório.pdf"));
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
}
