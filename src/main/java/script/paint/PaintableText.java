package script.paint;

import javax.swing.*;
import java.awt.*;

public class PaintableText extends JComponent {

    private final Color fontColor;
    private final Color backgroundColor;
    private final Color borderColor;
    private String[] infoToPaint = null;
    private String textToPaint;
    private final int borderThickness;
    private int leftmostX;
    private int rightmostX;
    private int lowestY;
    private int highestY;
    private final int horizontalPadding;
    private final int verticalPadding;
    private int xCoord;
    private int yCoord;
    private int[] xCoords, yCoords;

    /**
     * PaintableText Constructor for use when wanting only one background for all text.
     *
     * @param fontColor         Font color to use for the font in the paint.
     * @param backgroundColor   Background color to use in the paint.
     * @param borderColor       Border color to use in the paint.
     * @param infoToPaint       String array containing the info to paint on screen.
     * @param borderThickness   How thick to draw the border around the background
     * @param horizontalPadding How much space to have between the left/right edge of the background and the text
     * @param verticalPadding   How much space to have between the top/bottom edge of the background and the text
     * @param xCoords           X Coordinates to use to paint the strings, one per string
     * @param yCoords           Y Coordinates to use to paint the strings, one per string.
     */
    public PaintableText(Color fontColor, Color backgroundColor, Color borderColor, String[] infoToPaint, int borderThickness, int horizontalPadding, int verticalPadding, int[] xCoords, int[] yCoords) {
        this.fontColor = fontColor;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.infoToPaint = infoToPaint;
        this.borderThickness = borderThickness;
        this.horizontalPadding = horizontalPadding;
        this.verticalPadding = verticalPadding;
        this.xCoords = xCoords;
        this.yCoords = yCoords;
        this.leftmostX = getMinValue(xCoords);
        this.rightmostX = getMaxValue(xCoords);
        this.highestY = getMinValue(yCoords);
        this.lowestY = getMaxValue(yCoords);
    }

    /**
     * PaintableText Constructor for use when wanting only one piece of text per background.
     *
     * @param fontColor         Font color to use for the font in the paint.
     * @param backgroundColor   Background color to use in the paint.
     * @param borderColor       Border color to use in the paint.
     * @param textToPaint       Text to paint on screen.
     * @param borderThickness   How thick to draw the border around the background
     * @param horizontalPadding How much space to have between the left/right edge of the background and the text
     * @param verticalPadding   How much space to have between the top/bottom edge of the background and the text
     * @param xCoord            X Coordinate to use to paint the string.
     * @param yCoord            Y Coordinate to use to paint the string.
     */
    public PaintableText(Color fontColor, Color backgroundColor, Color borderColor, String textToPaint, int borderThickness, int horizontalPadding, int verticalPadding, int xCoord, int yCoord) {
        this.fontColor = fontColor;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.textToPaint = textToPaint;
        this.borderThickness = borderThickness;
        this.horizontalPadding = horizontalPadding;
        this.verticalPadding = verticalPadding;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    /**
     * Method used to paint the component on the screen.
     *
     * @param graphics Graphics object passed.
     */
    @Override
    public void paintComponent(Graphics graphics) {
        if (infoToPaint != null) { //Flag to switch between printing multiple backgrounds, or just one. In this case, just one background.
            highestY = highestY - (graphics.getFontMetrics().getHeight()); //Moves the highestY point to the top of where the text will be drawn. Text must be drawn at the bottom left corner, whereas rectangles are drawn at top left.
            rightmostX = leftmostX + graphics.getFontMetrics().stringWidth(getWidestString(infoToPaint, graphics)); //Determines the farthest right point by adding the width of the widest string to the leftmost point.
            graphics.setColor(backgroundColor);
            graphics.fillRect(leftmostX - horizontalPadding, highestY - verticalPadding, (rightmostX + horizontalPadding) - (leftmostX - horizontalPadding), (lowestY + verticalPadding) - (highestY - verticalPadding));
            Graphics2D graphics2D = (Graphics2D) graphics;
            if (borderThickness > 0) { //Only draw border if the border width is greater than 0, otherwise you don't want a border.
                graphics2D.setColor(borderColor);
                graphics2D.setStroke(new BasicStroke(borderThickness)); //Sets graphics2D to draw a border of the specified width.
                graphics2D.drawRect(leftmostX - horizontalPadding, highestY - verticalPadding, (rightmostX + horizontalPadding) - (leftmostX - horizontalPadding) + borderThickness, (lowestY + verticalPadding) - (highestY - verticalPadding) + borderThickness);
            }
            graphics.setColor(fontColor);
            for (int i = 0; i < infoToPaint.length; i++) {
                graphics.drawString(infoToPaint[i], xCoords[i], yCoords[i]); //Draws the Strings at their appropriate coordinates.
            }
        } else {
            Graphics2D graphics2D = (Graphics2D) graphics;
            Rectangle textRectangle = graphics.getFontMetrics(graphics.getFont()).getStringBounds(textToPaint, graphics).getBounds(); //Creates a rectangle object that is the size of the String to paint.
            int textHeight = (int) textRectangle.getHeight(); //Creates textHeight variable so I can modify the rectangle.
            textRectangle.setLocation(xCoord - horizontalPadding + borderThickness, yCoord - verticalPadding + borderThickness); //Moves the rectangle to the appropriate place for the background.
            textRectangle.setSize((int) textRectangle.getWidth() + horizontalPadding + horizontalPadding, (int) textRectangle.getHeight() + verticalPadding + verticalPadding); //Increases the size of the rectangle to account for padding
            graphics2D.setColor(backgroundColor);
            graphics2D.fill(textRectangle);
            graphics2D.setColor(fontColor);
            graphics2D.drawString(textToPaint, xCoord, yCoord + (int) (textHeight * 0.80)); //Multiplies the textHeight by 0.80 to try and center it vertically a bit better. If you think it's off, feel free to toy around with that value.
            if (borderThickness > 0) {
                graphics2D.setStroke(new BasicStroke(borderThickness));
                graphics2D.setColor(borderColor);
                graphics2D.drawRect(xCoord - horizontalPadding, yCoord - verticalPadding, (int) textRectangle.getWidth() + borderThickness, (int) textRectangle.getHeight() + borderThickness);
            }
        }
    }

    /**
     * Gets the highest number in an integer array.
     *
     * @param numbers Array to check through
     * @return Highest number in the array.
     */
    public int getMaxValue(int[] numbers) {
        if (numbers.length < 1) {
            return Integer.MIN_VALUE;
        }
        int maxValue = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > maxValue) {
                maxValue = numbers[i];
            }
        }
        return maxValue;
    }

    /**
     * Gets the lowest number in an integer array.
     *
     * @param numbers Array to check through
     * @return Lowest number in the array.
     */
    public int getMinValue(int[] numbers) {
        int minValue = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < minValue) {
                minValue = numbers[i];
            }
        }
        return minValue;
    }

    /**
     * Returns the String which will be the widest when drawn, out of an array of Strings
     *
     * @param stringArray Array to check through
     * @param graphics    Graphics object that will be used to paint the Strings
     * @return The String that will take up the most space.
     */
    private String getWidestString(String[] stringArray, Graphics graphics) {
        if (stringArray.length < 1) {
            return null;
        } else if (stringArray.length == 1) {
            return stringArray[0];
        }
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int index = 0;
        int elementWidth = fontMetrics.stringWidth(stringArray[0]);
        for (int i = 1; i < stringArray.length; i++) {
            if (fontMetrics.stringWidth(stringArray[i]) > elementWidth) {
                index = i;
                elementWidth = fontMetrics.stringWidth(stringArray[i]);
            }
        }
        return stringArray[index];
    }
}
