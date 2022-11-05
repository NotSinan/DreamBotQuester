package script.paint;

import java.awt.*;
import java.util.Arrays;

public class CustomPaint {

    private static final long START_TIME = System.currentTimeMillis();
    private final PaintInfo paintInfo;
    private final PaintLocations location;
    private Color[] fontColors, textBackgroundColors, textBackgroundBorderColors;
    private String[] paintInformationArray;
    private String fontName;
    private final int borderThickness;
    private int numberOfItems;
    private final int horizontalPadding;
    private final int verticalPadding;
    private int[] xCoords, yCoords;
    private final boolean singleBackground;
    private PaintableText[] paintableTexts;
    private PaintableText paintableText;
    private final GraphicsEnvironment graphicsEnvironment;
    private final int spaceBetweenFields;
    /**
     * Constructor to create the paint, arguments passed define how the paint will appear.
     *
     * @param paintInfo                  The paintInfo object you created, usually this will be the script class that extends PaintInfo. This object allows us to update the paint info for things like script runtime, exp/hr, etc.
     * @param location                   The location on the screen in which the paint is to be placed, from the enum defined above.
     * @param fontColors                 Array of Color which represents the font colors to use. If the array is longer than the number of Strings in the info array it will be shortened. If it is shorter the
     *                                   last element of the array will be copied until the array is the same length as the number of Strings in the info array.
     * @param fontName                   Font name to use to print the text, as a String. This name is checked, and will be replaced if it is unavailable.
     * @param textBackgroundColors       Array of Color which represents the background colors to use. Backgrounds will be drawn behind the text in the paint. If you do not want a background,
     *                                   make the Color completely transparent. If the array is longer than the number of Strings in the info array it will be shortened. If it is shorter the
     *                                   last element of the array will be copied until the array is the same length as the number of Strings in the info array.
     * @param textBackgroundBorderColors Array of Color which represents the border colors to use. Borders will be drawn around where the background of the text is on the paint. If you do
     *                                   not want a background, make the Color completely transparent. If the array is longer than the number of Strings in the info array it will be shortened.
     *                                   If it is shorter the last element of the array will be copied until the array is the same length as the number of Strings in the info array.
     * @param borderThickness            Thickness of the border to draw. If you do not want a border, make the border thickness 0.
     * @param singleBackground           A boolean, true if you want one background behind all text, false if you want backgrounds drawn individually.
     * @param spaceBetweenFields         The space in pixels to place between the painted Strings. If using multiple backgrounds the space accounts for the backgrounds, and will be the space
     *                                   between where the backgrounds end.
     * @param horizontalPadding          Padding to add horizontally to the printed Strings, measured in pixels. Padding is whitespace, and will create space between the text and edge of background
     * @param verticalPadding            Padding to add vertically to the printed Strings, measured in pixels. Padding is whitespace, and will create space between the text and edge of background
     */
    public CustomPaint(PaintInfo paintInfo, PaintLocations location, Color[] fontColors, String fontName, Color[] textBackgroundColors, Color[] textBackgroundBorderColors, int borderThickness, boolean singleBackground, int spaceBetweenFields, int horizontalPadding, int verticalPadding) {
        this.paintInfo = paintInfo;
        this.paintInformationArray = new String[]{""};
        this.location = location;
        this.graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.numberOfItems = 0;
        this.fontColors = copyOfNoNulls(fontColors, numberOfItems == 0 ? fontColors.length : numberOfItems);
        this.fontName = Arrays.stream(graphicsEnvironment.getAvailableFontFamilyNames()).anyMatch(fontName::equals) ? fontName : graphicsEnvironment.getAvailableFontFamilyNames()[0]; //StackExchange one liner to match a String.
        this.textBackgroundColors = copyOfNoNulls(textBackgroundColors, numberOfItems == 0 ? textBackgroundColors.length : numberOfItems);
        this.textBackgroundBorderColors = copyOfNoNulls(textBackgroundBorderColors, numberOfItems == 0 ? textBackgroundBorderColors.length : numberOfItems);
        this.borderThickness = borderThickness;
        this.singleBackground = singleBackground;
        this.xCoords = new int[numberOfItems];
        this.yCoords = new int[numberOfItems];
        this.spaceBetweenFields = spaceBetweenFields;
        this.horizontalPadding = horizontalPadding;
        this.verticalPadding = verticalPadding;
    }

    /**
     * Returns the baseColor with the applied transparency to make the alpha conversion easier.
     *
     * @param baseColor    Color to make transparent
     * @param transparency How transparent to make the color, 100 is completely transparent, 0 is completely opaque
     * @return BaseColor with the applied transparency
     */
    public static Color makeTransparentColor(Color baseColor, int transparency) {
        return new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), (int) (((double) ((transparency - 100) * -1) / 100) * 255));
    }

    /**
     * Method used to create a single PaintableText object, used for painting text with a single background.
     *
     * @param graphics - Graphics object.
     * @return PaintableText object which contains the text to be painted, the background, border, etc.
     */
    private PaintableText createPaintableText(Graphics graphics) {
        graphics.setFont(Font.getFont(fontName));
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int textHeight = fontMetrics.getHeight();
        int xMultiplier = location.isXCoordinateAtRight() ? -1 : 0;
        int startingX = location.isXCoordinateAtRight() ? location.getXCoordinate() - horizontalPadding : location.getXCoordinate() + horizontalPadding;
        int startingY = location.isYCoordinateAtBottom() ? location.getYCoordinate() - verticalPadding - ((textHeight + spaceBetweenFields) * numberOfItems - 1) : location.getYCoordinate() + verticalPadding;
        for (int i = 0; i < numberOfItems; i++) {
            xCoords[i] = startingX + (xMultiplier * (int) fontMetrics.getStringBounds(paintInformationArray[i], graphics).getWidth());
            yCoords[i] = startingY + ((int) fontMetrics.getStringBounds(paintInformationArray[i], graphics).getHeight() + spaceBetweenFields) * (i + 1);
        }
        return new PaintableText(fontColors[0], textBackgroundColors[0], textBackgroundBorderColors[0], paintInformationArray, borderThickness, horizontalPadding, verticalPadding, xCoords, yCoords);
    }

    /**
     * Method used to create an array of PaintableText objects, used for painting text with separate backgrounds.
     *
     * @param graphics - Graphics object.
     * @return PaintableText object array each object contains the text to be painted, the background, border, etc.
     */
    private PaintableText[] createPaintableTexts(Graphics graphics) {
        PaintableText[] paintableTexts = new PaintableText[numberOfItems];
        graphics.setFont(Font.getFont(fontName));
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int textHeight = fontMetrics.getHeight();
        int xMultiplier = location.isXCoordinateAtRight() ? -1 : 0;
        int startingX = location.isXCoordinateAtRight() ? location.getXCoordinate() - horizontalPadding : location.getXCoordinate() + horizontalPadding;
        int startingY = location.isYCoordinateAtBottom() ? location.getYCoordinate() - ((((verticalPadding * 2) + (borderThickness * 2) + textHeight + spaceBetweenFields) * (numberOfItems)) + verticalPadding + borderThickness) : location.getYCoordinate() + verticalPadding;
        for (int i = 0; i < numberOfItems; i++) {
            xCoords[i] = startingX + (xMultiplier * (int) fontMetrics.getStringBounds(paintInformationArray[i], graphics).getWidth());
            yCoords[i] = startingY + (textHeight * i) + ((spaceBetweenFields + (verticalPadding * 2) + (borderThickness * 2)) * i);
            paintableTexts[i] = new PaintableText(fontColors[i], textBackgroundColors[i], textBackgroundBorderColors[i], paintInformationArray[i], borderThickness, horizontalPadding, verticalPadding, xCoords[i], yCoords[i]);
        }
        return paintableTexts;
    }

    /**
     * Paints the information on the screen by creating the PaintableText objects and painting them individually.
     *
     * @param graphics
     */
    public void paint(Graphics graphics) {
        if (paintInfo.getPaintInfo() == null || paintInfo.getPaintInfo().length < 1) {
            return;
        }
        if (Arrays.equals(paintInformationArray, paintInfo.getPaintInfo())) { //Used to avoid recalling the creation methods if nothing has changed.
            if (!singleBackground) {
                for (PaintableText text : paintableTexts) {
                    text.paintComponent(graphics);
                }
            } else {
                paintableText.paintComponent(graphics);
            }
        } else {
            if (numberOfItems == 0) { //Only occurs when there's nothing to paint, or it hasn't loaded due to the paint loading before the script.
                reinitializeVariables();
            } else { //If the numberOfItems != 0 then the only thing that's changed is the text to paint, so we'll just update that.
                this.paintInformationArray = paintInfo.getPaintInfo();
                reinitializeVariables();
            }
            if (!singleBackground) {
                this.paintableTexts = createPaintableTexts(graphics);
                for (PaintableText text : paintableTexts) {
                    text.paintComponent(graphics);
                }
            } else {
                this.paintableText = createPaintableText(graphics);
                paintableText.paintComponent(graphics);
            }
        }
    }

    /**
     * Reinitializes all variables if something has changed, used when the info to paint has changed from what it was previously.
     * Used to save on unnecessary method calls if the paint text hasn't changed, as the Paint thread runs frequently.
     */
    private void reinitializeVariables() {
        this.paintInformationArray = paintInfo.getPaintInfo();
        this.numberOfItems = paintInformationArray.length;
        this.fontColors = copyOfNoNulls(fontColors, numberOfItems);
        this.fontName = Arrays.stream(graphicsEnvironment.getAvailableFontFamilyNames()).anyMatch(fontName::equals) ? fontName : graphicsEnvironment.getAvailableFontFamilyNames()[0]; //StackExchange one liner to match a String.
        this.textBackgroundColors = copyOfNoNulls(textBackgroundColors, numberOfItems);
        this.textBackgroundBorderColors = copyOfNoNulls(textBackgroundBorderColors, numberOfItems);
        this.xCoords = new int[numberOfItems];
        this.yCoords = new int[numberOfItems];
    }

    /**
     * Returns the time that the current script has ran for. Based off of the start time created when the script starts.
     * Could use the method in the script, but wanted to avoid passing a script object.
     *
     * @return The current runtime of the script as a String.
     */
    public String getRuntimeString() {
        long millis = System.currentTimeMillis() - START_TIME;
        long second = (millis / 1000) % 60;
        long minute = (millis / (1000 * 60)) % 60;
        long hour = (millis / (1000 * 60 * 60)) % 24;
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    /**
     * Copies the passed array into the specified length. Will copy the last element multiple times to fill the remaining space, or cut the array to shorten.
     *
     * @param original    Starting array
     * @param arrayLength Length to make the resulting array
     * @param <T>         Generic type.
     * @return Array of passed type at the required length.
     */
    private <T> T[] copyOfNoNulls(T[] original, int arrayLength) {
        T[] newArray = Arrays.copyOf(original, arrayLength);
        if (original.length < arrayLength) {
            for (int i = original.length; i < newArray.length; i++) {
                newArray[i] = newArray[i] == null ? original[original.length - 1] : newArray[i];
            }
        }
        return newArray;
    }

    /**
     * Enum containing predefined locations for the paint. Each location contains a single x and y coord, with two booleans to indicate if the
     * y coordinate is at the bottom of where the text should be painted, and a second boolean to indicate if the x coordinate is at the right of where the text should be painted.
     */
    public enum PaintLocations {

        BOTTOM_LEFT_PLAY_SCREEN(5, 337, true, false),
        BOTTOM_RIGHT_PLAY_SCREEN(513, 337, true, true),
        TOP_LEFT_PLAY_SCREEN(5, 30, false, false),
        TOP_RIGHT_PLAY_SCREEN(513, 5, false, true),
        TOP_LEFT_CHATBOX(8, 345, false, false),
        TOP_RIGHT_CHATBOX(509, 345, false, true),
        BOTTOM_LEFT_CHATBOX(8, 474, true, false),
        BOTTOM_RIGHT_CHATBOX(509, 474, true, true),
        INVENTORY_AREA(551, 207, false, false);

        private final int xCoordinate;
        private final int yCoordinate;
        private final boolean yCoordinateAtBottom;
        private final boolean xCoordinateAtRight;

        PaintLocations(int xCoordinate, int yCoordinate, boolean yCoordinateAtBottom, boolean xCoordinateAtRight) {
            this.xCoordinate = xCoordinate;
            this.yCoordinate = yCoordinate;
            this.yCoordinateAtBottom = yCoordinateAtBottom;
            this.xCoordinateAtRight = xCoordinateAtRight;
        }

        public int getXCoordinate() {
            return xCoordinate;
        }

        public int getYCoordinate() {
            return yCoordinate;
        }

        public boolean isYCoordinateAtBottom() {
            return yCoordinateAtBottom;
        }

        public boolean isXCoordinateAtRight() {
            return xCoordinateAtRight;
        }
    }

}
