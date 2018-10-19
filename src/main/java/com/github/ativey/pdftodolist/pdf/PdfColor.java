package com.github.ativey.pdftodolist.pdf;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;

public class PdfColor {
    
    private Color colour;
    private String hexString;


    private PdfColor(int red, int green, int blue) {
        int value = blue + (green << 8) + (red << 16);
        this.hexString = Integer.toHexString(value);
        this.colour = new DeviceRgb(red, green, blue);
    }

    public Color getColour() {
        return colour;
    }

    public String getHexString() {
        return hexString;
    }

    // Red colors
    public static final PdfColor LIGHT_SALMON = new PdfColor(255, 160, 122);
    public static final PdfColor SALMON = new PdfColor(250, 128, 114);
    public static final PdfColor DARK_SALMON = new PdfColor(233, 150, 122);
    public static final PdfColor LIGHT_CORAL = new PdfColor(240, 128, 128);
    public static final PdfColor INDIAN_RED = new PdfColor(205, 92, 92);
    public static final PdfColor CRIMSON = new PdfColor(220, 20, 60);
    public static final PdfColor FIREBRICK = new PdfColor(178, 34, 34);
    public static final PdfColor RED = new PdfColor(255, 0, 0);
    public static final PdfColor DARK_RED = new PdfColor(139, 0, 0);

    // Orange colors
    public static final PdfColor CORAL = new PdfColor(255, 127, 80);
    public static final PdfColor TOMATO = new PdfColor(255, 99, 71);
    public static final PdfColor ORANGE_RED = new PdfColor(255, 69, 0);
    public static final PdfColor GOLD = new PdfColor(255, 215, 0);
    public static final PdfColor ORANGE = new PdfColor(255, 165, 0);
    public static final PdfColor DARK_ORANGE = new PdfColor(255, 140, 0);

    // Yellow colors
    public static final PdfColor LIGHT_YELLOW = new PdfColor(255, 255, 224);
    public static final PdfColor LEMON_CHIFFON = new PdfColor(255, 250, 205);
    public static final PdfColor LIGHT_GOLDENROD_YELLOW = new PdfColor(250, 250, 210);
    public static final PdfColor PAPAYAWHIP = new PdfColor(255, 239, 213);
    public static final PdfColor MOCCASIN = new PdfColor(255, 228, 181);
    public static final PdfColor PEACHPUFF = new PdfColor(255, 218, 185);
    public static final PdfColor PALE_GOLDENROD = new PdfColor(238, 232, 170);
    public static final PdfColor KHAKI = new PdfColor(240, 230, 140);
    public static final PdfColor DARK_KHAKI = new PdfColor(189, 183, 107);
    public static final PdfColor YELLOW = new PdfColor(255, 255, 0);

    // Green colors
    public static final PdfColor LAWN_GREEN = new PdfColor(124, 252, 0);
    public static final PdfColor CHARTREUSE = new PdfColor(127, 255, 0);
    public static final PdfColor LIME_GREEN = new PdfColor(50, 205, 50);
    public static final PdfColor LIME = new PdfColor(50, 255, 50);
    public static final PdfColor FOREST_GREEN = new PdfColor(34, 139, 34);
    public static final PdfColor GREEN = new PdfColor(0, 128, 0);
    public static final PdfColor DARK_GREEN = new PdfColor(0, 100, 0);
    public static final PdfColor GREEN_YELLOW = new PdfColor(173, 255, 47);
    public static final PdfColor YELLOW_GREEN = new PdfColor(154, 205, 50);
    public static final PdfColor SPRING_GREEN = new PdfColor(0, 255, 127);
    public static final PdfColor MEDIUM_SPRING_GREEN = new PdfColor(0, 250, 154);
    public static final PdfColor LIGHT_GREEN = new PdfColor(144, 238, 144);
    public static final PdfColor PALE_GREEN = new PdfColor(152, 251, 152);
    public static final PdfColor DARK_SEA_GREEN = new PdfColor(143, 188, 143);
    public static final PdfColor MEDIUM_SEA_GREEN = new PdfColor(60, 179, 113);
    public static final PdfColor SEA_GREEN = new PdfColor(46, 139, 87);
    public static final PdfColor OLIVE = new PdfColor(128, 128, 0);
    public static final PdfColor DARK_OLIVE_GREEN = new PdfColor(85, 107, 47);
    public static final PdfColor OLIVE_DRAB = new PdfColor(107, 142, 35);

    // Cyan colors
    public static final PdfColor LIGHT_CYAN = new PdfColor(224, 255, 255);
    public static final PdfColor CYAN = new PdfColor(0, 255, 255);
    public static final PdfColor AQUA = new PdfColor(0, 255, 255);
    public static final PdfColor AQUAMARINE = new PdfColor(127, 255, 212);
    public static final PdfColor MEDIUM_AQUAMARINE = new PdfColor(102, 205, 170);
    public static final PdfColor PALE_TURQUOISE = new PdfColor(175, 238, 238);
    public static final PdfColor TURQUOISE = new PdfColor(64, 224, 208);
    public static final PdfColor MEDIUM_TURQUOISE = new PdfColor(72, 209, 204);
    public static final PdfColor DARK_TURQUOISE = new PdfColor(0, 206, 209);
    public static final PdfColor LIGHT_SEA_GREEN = new PdfColor(32, 178, 170);
    public static final PdfColor CADET_BLUE = new PdfColor(95, 158, 160);
    public static final PdfColor DARK_CYAN = new PdfColor(0, 139, 139);
    public static final PdfColor TEAL = new PdfColor(0, 128, 128);

    // Blue colors
    public static final PdfColor POWDER_BLUE = new PdfColor(176, 224, 230);
    public static final PdfColor LIGHT_BLUE = new PdfColor(173, 216, 230);
    public static final PdfColor LIGHT_SKY_BLUE = new PdfColor(135, 206, 250);
    public static final PdfColor SKY_BLUE = new PdfColor(135, 206, 235);
    public static final PdfColor DEEP_SKY_BLUE = new PdfColor(0, 191, 255);
    public static final PdfColor LIGHT_STEEL_BLUE = new PdfColor(176, 196, 222);
    public static final PdfColor DODGER_BLUE = new PdfColor(30, 144, 255);
    public static final PdfColor CORNFLOWER_BLUE = new PdfColor(100, 149, 237);
    public static final PdfColor STEEL_BLUE = new PdfColor(70, 130, 180);
    public static final PdfColor ROYAL_BLUE = new PdfColor(65, 105, 225);
    public static final PdfColor BLUE = new PdfColor(0, 0, 255);
    public static final PdfColor MEDIUM_BLUE = new PdfColor(0, 0, 205);
    public static final PdfColor DARK_BLUE = new PdfColor(0, 0, 139);
    public static final PdfColor NAVY = new PdfColor(0, 0, 128);
    public static final PdfColor MIDNIGHT_BLUE = new PdfColor(25, 25, 112);
    public static final PdfColor MEDIUM_SLATE_BLUE = new PdfColor(123, 104, 238);
    public static final PdfColor SLATE_BLUE = new PdfColor(106, 90, 205);
    public static final PdfColor DARK_SLATE_BLUE = new PdfColor(72, 61, 139);

    // Purple colors
    public static final PdfColor LAVENDER = new PdfColor(230, 230, 250);
    public static final PdfColor THISTLE = new PdfColor(216, 191, 216);
    public static final PdfColor PLUM = new PdfColor(221, 160, 221);
    public static final PdfColor VIOLET = new PdfColor(238, 130, 238);
    public static final PdfColor ORCHID = new PdfColor(218, 112, 214);
    public static final PdfColor FUCHSIA = new PdfColor(255, 0, 255);
    public static final PdfColor MAGENTA = new PdfColor(255, 0, 255);
    public static final PdfColor MEDIUM_ORCHID = new PdfColor(186, 85, 211);
    public static final PdfColor MEDIUM_PURPLE = new PdfColor(147, 112, 219);
    public static final PdfColor BLUE_VIOLET = new PdfColor(138, 43, 226);
    public static final PdfColor DARK_VIOLET = new PdfColor(148, 0, 211);
    public static final PdfColor DARK_ORCHID = new PdfColor(153, 50, 204);
    public static final PdfColor DARK_MAGENTA = new PdfColor(139, 0, 139);
    public static final PdfColor PURPLE = new PdfColor(128, 0, 128);
    public static final PdfColor REBECCA_PURPLE = new PdfColor(102, 51, 153);
    public static final PdfColor INDIGO = new PdfColor(75, 0, 130);

    // Pink colors
    public static final PdfColor PINK = new PdfColor(255, 192, 203);
    public static final PdfColor LIGHT_PINK = new PdfColor(255, 182, 193);
    public static final PdfColor HOT_PINK = new PdfColor(255, 105, 180);
    public static final PdfColor DEEP_PINK = new PdfColor(255, 20, 147);
    public static final PdfColor PALE_VIOLET_RED = new PdfColor(219, 112, 147);
    public static final PdfColor MEDIUM_VIOLET_RED = new PdfColor(199, 21, 133);

    // White colors
    public static final PdfColor WHITE = new PdfColor(255, 255, 255);
    public static final PdfColor SNOW = new PdfColor(255, 250, 250);
    public static final PdfColor HONEYDEW = new PdfColor(240, 255, 240);
    public static final PdfColor MINTCREAM = new PdfColor(245, 255, 250);
    public static final PdfColor AZURE = new PdfColor(240, 255, 255);
    public static final PdfColor ALICE_BLUE = new PdfColor(240, 248, 255);
    public static final PdfColor GHOSTWHITE = new PdfColor(248, 248, 255);
    public static final PdfColor WHITESMOKE = new PdfColor(245, 245, 245);
    public static final PdfColor SEASHELL = new PdfColor(255, 245, 238);
    public static final PdfColor BEIGE = new PdfColor(245, 245, 220);
    public static final PdfColor OLD_LACE = new PdfColor(253, 245, 230);
    public static final PdfColor FLORAL_WHITE = new PdfColor(255, 250, 240);
    public static final PdfColor IVORY = new PdfColor(255, 255, 240);
    public static final PdfColor ANTIQUE_WHITE = new PdfColor(250, 235, 215);
    public static final PdfColor LINEN = new PdfColor(250, 240, 230);
    public static final PdfColor LAVENDER_BLUSH = new PdfColor(255, 240, 245);
    public static final PdfColor MISTY_ROSE = new PdfColor(255, 228, 225);

    // Gray colors
    public static final PdfColor GAINSBORO = new PdfColor(220, 220, 220);
    public static final PdfColor LIGHT_GRAY = new PdfColor(211, 211, 211);
    public static final PdfColor SILVER = new PdfColor(192, 192, 192);
    public static final PdfColor DARK_GRAY = new PdfColor(169, 169, 169);
    public static final PdfColor GRAY = new PdfColor(128, 128, 128);
    public static final PdfColor DIM_GRAY = new PdfColor(105, 105, 105);
    public static final PdfColor LIGHT_SLATE_GRAY = new PdfColor(119, 136, 153);
    public static final PdfColor SLATE_GRAY = new PdfColor(112, 128, 144);
    public static final PdfColor DARK_SLATE_GRAY = new PdfColor(47, 79, 79);
    public static final PdfColor BLACK = new PdfColor(0, 0, 0);

    // Brown colors
    public static final PdfColor CORN_SILK = new PdfColor(255, 248, 220);
    public static final PdfColor BLANCHED_ALMOND = new PdfColor(255, 235, 205);
    public static final PdfColor BISQUE = new PdfColor(255, 228, 196);
    public static final PdfColor NAVAJO_WHITE = new PdfColor(255, 222, 173);
    public static final PdfColor WHEAT = new PdfColor(245, 222, 179);
    public static final PdfColor BURLY_WOOD = new PdfColor(222, 184, 135);
    public static final PdfColor TAN = new PdfColor(210, 180, 140);
    public static final PdfColor ROSY_BROWN = new PdfColor(188, 143, 143);
    public static final PdfColor SANDY_BROWN = new PdfColor(244, 164, 96);
    public static final PdfColor GOLDENROD = new PdfColor(218, 165, 32);
    public static final PdfColor PERU = new PdfColor(205, 133, 63);
    public static final PdfColor CHOCOLATE = new PdfColor(210, 105, 30);
    public static final PdfColor SADDLE_BROWN = new PdfColor(139, 69, 19);
    public static final PdfColor SIENNA = new PdfColor(160, 82, 45);
    public static final PdfColor BROWN = new PdfColor(165, 42, 42);
    public static final PdfColor MAROON = new PdfColor(128, 0, 0);

}
