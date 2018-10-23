package com.github.ativey.pdftodolist;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class AppConfig {


    @Bean
    PdfFont bodyFont() {
        try {
            System.err.println("I'm trying to create a body font");
            return PdfFontFactory.createFont(StandardFonts.HELVETICA);
        } catch (IOException exp) {

            throw new RuntimeException("Unable to create fallback font '"+StandardFonts.HELVETICA+"'", exp);
        }
    }


}
