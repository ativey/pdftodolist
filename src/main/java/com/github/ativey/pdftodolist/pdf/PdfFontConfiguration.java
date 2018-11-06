package com.github.ativey.pdftodolist.pdf;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    // Second choice application fonts
    private static final String APP_BODY = "/fonts/junicode-1.001/Junicode-RegularCondensed.ttf";
    private static final String APP_BOLD = "/fonts/junicode-1.001/Junicode-BoldCondensed.ttf";

    // First choice user fonts
    private String bodyFontName = APP_BODY;
    private String boldFontName = APP_BOLD;


    @Bean
    PdfFont bodyFont() {
        return createFont(bodyFontName, STANDARD_FALLBACK);
    }

    @Bean
    PdfFont boldFont() {
        return createFont(boldFontName, STANDARD_FALLBACK_BOLD);
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
    private PdfFont createFont(String userFontName, String standardFallback) {

        Optional<PdfFont> optional = createFont(userFontName);
        if (optional.isPresent()) {
            return optional.get();
        }
        logger.warn(String.format("wibble", userFontName));

        optional = createFont(standardFallback);
        if (optional.isEmpty()) {
            logger.warn(String.format("wibble", userFontName));
        }
        return optional.get();
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
