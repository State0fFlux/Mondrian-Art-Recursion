/**
 * Brady Manske
 * 11/8/2023
 * CSE 123
 * C2: Mondrian Art
 * TA: Anthony Chung
 */

import java.util.*;
import java.awt.*;

/**
 * This is a class of Mondrian art generators, which provides tools to randomly generate
 *    art of any size, inspired by Piet Mondrian. It allows a user to create either "basic"
 *    Mondrian art that is more reminiscent of Mondrian's style and color palette,
 *    or a more complex & stylized variation of Mondrian art
 */
public class Mondrian {

    /**
     * Updates a given canvas to store a "basic" piece of Mondrian art,
     *    featuring Mondrian's classic color palette
     * 
     * @param pixels - A 2D array of Colors representing a canvas
     */
    public void paintBasicMondrian(Color[][] pixels) {
        Color[] palette = {Color.RED, Color.YELLOW, Color.CYAN,Color.WHITE};
        paintBasicMondrian(pixels, 0, pixels[0].length - 1, 0, pixels.length - 1,
                           new Random(), palette);
    }

    /**
     * Updates a given canvas to store a "basic" piece of Mondrian art,
     *    featuring the colors from a given palette
     * 
     * @param pixels - A 2D array of Colors representing a canvas
     * @param xStart - A number representing the starting x-coordinate of the region of
     *    the canvas that is currently being generated
     * @param xEnd - A number representing the ending x-coordinate of the active region
     * @param yStart - A number representing the starting y-coordinate of the active region
     * @param yEnd - A number representing the ending y-coordinate of the active region
     * @param RNG - A random value generator
     * @param palette - An array of Colors representing a color palette
     */
    private void paintBasicMondrian(Color[][] pixels, int xStart, int xEnd, int yStart, int yEnd,
                                    Random RNG, Color[] palette) {
        if (((xEnd - xStart) >= (pixels[0].length / 4)) || ((yEnd - yStart) >=
                                (pixels.length / 4))) { // recursive case, splitting is necessary
            // randomly decides on which way to attempt to split first (horizontal or vertical)
            if (RNG.nextBoolean()) {
                tryHoriSplitBasic(pixels, xStart, xEnd, yStart, yEnd, RNG, palette);
                tryVertSplitBasic(pixels, xStart, xEnd, yStart, yEnd, RNG, palette);
            } else {
                tryVertSplitBasic(pixels, xStart, xEnd, yStart, yEnd, RNG, palette);
                tryHoriSplitBasic(pixels, xStart, xEnd, yStart, yEnd, RNG, palette);
            }
        } else { // base case, region too small
            Color selected = palette[RNG.nextInt(palette.length)];
            // redefining border
            for (int i = yStart; i < yEnd + 1; i++) {
                for (int j = xStart; j < xEnd + 1; j++) {
                    pixels[i][j] = Color.BLACK;
                }
            }
            for (int i = yStart + 1; i < yEnd; i++) {
                for (int j = xStart + 1; j < xEnd; j++) {
                    pixels[i][j] = selected;
                }
            }
        }
    }

    /**
     * Attempts to split a given canvas into two parts horizontally,
     *    generating two new basic Mondrian art pieces within each randomly-sized half
     *    if successful, or doing nothing if the canvas is too short to split horizontally
     * @param pixels - A 2D array of Colors representing a canvas
     * @param xStart - A number representing the starting x-coordinate of the region of
     *    the canvas that is currently being generated
     * @param xEnd - A number representing the ending x-coordinate of the active region
     * @param yStart - A number representing the starting y-coordinate of the active region
     * @param yEnd - A number representing the ending y-coordinate of the active region
     * @param RNG - A random value generator
     * @param palette - An array of Colors representing a color palette
     */
    private void tryHoriSplitBasic(Color[][] pixels, int xStart, int xEnd, int yStart, int yEnd,
                                   Random RNG, Color[] palette) {
        if ((yEnd - yStart) >= (pixels.length / 4)) {
            int horiSplit = RNG.nextInt(yEnd - yStart) + yStart;
            paintBasicMondrian(pixels, xStart, xEnd, yStart, horiSplit, RNG, palette);
            paintBasicMondrian(pixels, xStart, xEnd, horiSplit, yEnd, RNG, palette);
        }
    }

    /**
     * Attempts to split a given canvas into two parts vertically,
     *    generating two new basic Mondrian art pieces within each randomly-sized half
     *    if successful, or doing nothing if the canvas is too short to split vertically
     * @param pixels - A 2D array of Colors representing a canvas
     * @param xStart - A number representing the starting x-coordinate of the region of
     *    the canvas that is currently being generated
     * @param xEnd - A number representing the ending x-coordinate of the active region
     * @param yStart - A number representing the starting y-coordinate of the active region
     * @param yEnd - A number representing the ending y-coordinate of the active region
     * @param RNG - A random value generator
     * @param palette - An array of Colors representing a color palette
     */
    private void tryVertSplitBasic(Color[][] pixels, int xStart, int xEnd, int yStart, int yEnd,
                                   Random RNG, Color[] palette) {
        if ((xEnd - xStart) >= (pixels[0].length / 4)) {
            int vertSplit = RNG.nextInt(xEnd - xStart) + xStart;
            paintBasicMondrian(pixels, xStart, vertSplit, yStart, yEnd, RNG, palette);
            paintBasicMondrian(pixels, vertSplit, xEnd, yStart, yEnd, RNG, palette);
        }
    }

    /**
     * Updates a given canvas to store a stylized variation of Mondrian art,
     *    featuring a color palette of purples and blues. This variation incorporates a
     *    randomly-generated wavelength pattern into each colored box on the canvas 
     * 
     * @param pixels - A 2D array of Colors representing a canvas
     */
    public void paintComplexMondrian(Color[][] pixels) {
        Color[] palette = {new Color(92, 59, 168), new Color(118, 74, 209),
                           new Color(12, 7, 53), new Color(176, 109, 240),
                           new Color(29, 18, 64), new Color(19, 12, 56),
                           new Color(25, 14, 57), new Color(32, 16, 116),
                           new Color(43, 32, 119), new Color(18, 9, 47)};
        paintComplexMondrian(pixels, 0, pixels[0].length - 1, 0, pixels.length - 1,
                             new Random(), palette);
    }
    
    /**
     * Updates a given canvas to store a stylized variation of Mondrian art,
     *    featuring the colors from a given palette. This variation incorporates a
     *    randomly-generated wavelength pattern into each colored box on the canvas 
     * 
     * @param pixels - A 2D array of Colors representing a canvas
     * @param xStart - A number representing the starting x-coordinate of the region of
     *    the canvas that is currently being generated
     * @param xEnd - A number representing the ending x-coordinate of the active region
     * @param yStart - A number representing the starting y-coordinate of the active region
     * @param yEnd - A number representing the ending y-coordinate of the active region
     * @param RNG - A random value generator
     * @param palette - An array of Colors representing a color palette
     */
    private void paintComplexMondrian(Color[][] pixels, int xStart, int xEnd, int yStart, int yEnd,
                                      Random RNG, Color[] palette) {
          if (((xEnd - xStart) >= (pixels[0].length / 4)) || ((yEnd - yStart) >=
                                  (pixels.length / 4))) { // recursive case, splitting is necessary
            // randomly decides on which way to attempt to split first (horizontal or vertical)
            if (RNG.nextBoolean()) {
                tryHoriSplitComplex(pixels, xStart, xEnd, yStart, yEnd, RNG, palette);
                tryVertSplitComplex(pixels, xStart, xEnd, yStart, yEnd, RNG, palette);
            } else {
                tryVertSplitComplex(pixels, xStart, xEnd, yStart, yEnd, RNG, palette);
                tryHoriSplitComplex(pixels, xStart, xEnd, yStart, yEnd, RNG, palette);
            }
        } else { // base case, region too small
            Color selected = palette[RNG.nextInt(palette.length)];
            int shorterSide = xEnd - xStart;
            if ((yEnd - yStart) < shorterSide) {
                shorterSide = yEnd - yStart;
            }
            // redefining border
            for (int i = yStart; i < yEnd + 1; i++) {
                for (int j = xStart; j < xEnd + 1; j++) {
                    pixels[i][j] = Color.BLACK;
                }
            }
            if (xEnd - xStart == shorterSide) { // creating a vertical wave
                for (int i = yStart + 1, c = RNG.nextInt((shorterSide + 2) / 2); i < yEnd;
                     i++, c = RNG.nextInt((shorterSide + 2) / 2)) {
                    for (int j = (xStart + c); j < (xEnd - c); j++) {
                        pixels[i][j] = selected;
                    }
                }
            } else { // creating a horizontal wave
                for (int i = xStart + 1, c = RNG.nextInt((shorterSide + 2) / 2); i < xEnd;
                     i++, c = RNG.nextInt((shorterSide + 2) / 2)) {
                    for (int j = (yStart + c); j < (yEnd - c); j++) {
                        pixels[j][i] = selected;
                    }
                }
            }
        }
    }

    /**
     * Attempts to split a given canvas into two parts horizontally,
     *    generating two new complex Mondrian art pieces within each randomly-sized half
     *    if successful, or doing nothing if the canvas is too short to split horizontally
     * @param pixels - A 2D array of Colors representing a canvas
     * @param xStart - A number representing the starting x-coordinate of the region of
     *    the canvas that is currently being generated
     * @param xEnd - A number representing the ending x-coordinate of the active region
     * @param yStart - A number representing the starting y-coordinate of the active region
     * @param yEnd - A number representing the ending y-coordinate of the active region
     * @param RNG - A random value generator
     * @param palette - An array of Colors representing a color palette
     */
    private void tryHoriSplitComplex(Color[][] pixels, int xStart, int xEnd, int yStart, int yEnd,
                                     Random RNG, Color[] palette) {
        if ((yEnd - yStart) >= (pixels.length / 4)) {
            // decide upon dividing line & do 2 recursive calls
            int horiSplit = RNG.nextInt(yEnd - yStart) + yStart;
            paintComplexMondrian(pixels, xStart, xEnd, yStart, horiSplit, RNG, palette);
            paintComplexMondrian(pixels, xStart, xEnd, horiSplit, yEnd, RNG, palette);
        }
    }
    
    /**
     * Attempts to split a given canvas into two parts vertically,
     *    generating two new complex Mondrian art pieces within each randomly-sized half
     *    if successful, or doing nothing if the canvas is too short to split vertically
     * @param pixels - A 2D array of Colors representing a canvas
     * @param xStart - A number representing the starting x-coordinate of the region of
     *    the canvas that is currently being generated
     * @param xEnd - A number representing the ending x-coordinate of the active region
     * @param yStart - A number representing the starting y-coordinate of the active region
     * @param yEnd - A number representing the ending y-coordinate of the active region
     * @param RNG - A random value generator
     * @param palette - An array of Colors representing a color palette
     */
    private void tryVertSplitComplex(Color[][] pixels, int xStart, int xEnd, int yStart, int yEnd,
                                     Random RNG, Color[] palette) {
        if ((xEnd - xStart) >= (pixels[0].length / 4)) {
            // decide upon dividing line & do 2 recursive calls
            int vertSplit = RNG.nextInt(xEnd - xStart) + xStart;
            paintComplexMondrian(pixels, xStart, vertSplit, yStart, yEnd, RNG, palette);
            paintComplexMondrian(pixels, vertSplit, xEnd, yStart, yEnd, RNG, palette);
        }
    }
}
