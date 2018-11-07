package com.github.ativey.pdftodolist.pdf;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.util.Optional;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="fonts")
public class PdfFontConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(PdfFontConfiguration.class);

    // Fallback standard fonts
    private static final String STANDARD_FALLBACK = StandardFonts.HELVETICA;
    private static final String STANDARD_FALLBACK_BOLD = StandardFonts.HELVETICA_BOLD;
    private static final String STANDARD_FALLBACK_ITALIC = StandardFonts.HELVETICA_OBLIQUE;
    private static final String STANDARD_FALLBACK_BOLD_ITALIC = StandardFonts.HELVETICA_BOLDOBLIQUE;

    // Second choice application fonts
    private static final String APP_BODY = "/fonts/junicode-1.001/Junicode-RegularCondensed.ttf";
    private static final String APP_BOLD = "/fonts/junicode-1.001/Junicode-BoldCondensed.ttf";
    private static final String APP_ITALIC = "/fonts/junicode-1.001/Junicode-ItalicCondensed.ttf";
    private static final String APP_BOLD_ITALIC = "/fonts/junicode-1.001/Junicode-BoldItalicCondensed.ttf";

    // First choice user fonts
    private String bodyFontName = APP_BODY;
    private String boldFontName = APP_BOLD;
    private String italicFontName = APP_ITALIC;
    private String boldItalicFontName = APP_BOLD_ITALIC;


    @Bean
    @Scope("prototype")
    FontProgram bodyFontProgram() {
        return createFont(bodyFontName, STANDARD_FALLBACK);
    }

    @Bean
    @Scope("prototype")
    FontProgram boldFontProgram() {
        return createFont(boldFontName, STANDARD_FALLBACK_BOLD);
    }

    @Bean
    @Scope("prototype")
    FontProgram italicFontProgram() {
        return createFont(italicFontName, STANDARD_FALLBACK_ITALIC);
    }

    @Bean
    @Scope("prototype")
    FontProgram boldItalicFontProgram() {
        return createFont(boldItalicFontName, STANDARD_FALLBACK_BOLD_ITALIC);
    }


    /**
     * The default font to be used for the main body of the text
     *
     * @param bodyFontName The standard fontname, or path to a font file
     */
    public void setBodyFontName(String bodyFontName) {
        this.bodyFontName = bodyFontName;
    }

    /**
     * The default font to be used for bold text
     *
     * @param boldFontName The standard fontname, or path to a font file
     */
    public void setBoldFontName(String boldFontName) {
        this.boldFontName = boldFontName;
    }


    // FontMetrics fontMetrics = font.getFontProgram().getFontMetrics();
    // float textHeight = 1000.0f * fontMetrics.getAscender() - fontMetrics.getDescender();
    private FontProgram createFont(String userFontName, String standardFallback) {

        Optional<PdfFont> optional = createFont(userFontName);
        if (optional.isPresent()) {
            return optional.get().getFontProgram();
        }
        logger.warn(String.format("wibble", userFontName));

        optional = createFont(standardFallback);
        if (optional.isEmpty()) {
            logger.warn(String.format("wibble", userFontName));
        }
        return optional.get().getFontProgram();
    }


    private Optional<PdfFont> createFont(String fontName) {
        try {
            byte[] fontData = this.getClass().getResourceAsStream(fontName).readAllBytes();
            PdfFont ret = PdfFontFactory.createFont(fontData, true);
            return Optional.of(ret);
        } catch (IOException exp) {
            logger.warn("wibble", fontName);
            return Optional.empty();
        }
    }


}
