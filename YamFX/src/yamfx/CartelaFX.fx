/*
 * CartelaFX.fx
 *
 * Created on Dec 29, 2008, 6:45:30 PM
 */

package yamfx;

import javafx.scene.CustomNode;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextOrigin;



/**
 * @author Leandro Conca
 */

public class CartelaFX extends CustomNode {

    public var posX: Number = 40;
    public var posY: Number = 40;
    public var cellWidth: Number = 40;
    public var cellHeight: Number = 20;

    var cellFillNormal =
    LinearGradient {
        startX: 0
        startY: 0
        endX: 1
        endY: 0
        stops: [
            Stop {
                offset: 0.0
                color: Color.web("#cff5fd",1)
            },
            Stop {
                offset: 1.0
                color: Color.web("#cff5fd",0)
            }
        ] // stops
    } //LinearGradient

    var cellFillTotal =
    LinearGradient {
        startX: 0
        startY: 0
        endX: 1
        endY: 0
        stops: [
            Stop {
                offset: 0.0
                color: Color.web("#919191",1)
            },
            Stop {
                offset: 1.0
                color: Color.web("#cff5fd",0)
            }
        ] // stops
    } //LinearGradient

    public override function create(): Node {
        return Group {
            content: [
                for (i in [0..3])
                for (j in [0..17])
                Group {
                    content: [
                        Rectangle {
                            x: posX + cellWidth * i
                            y: posY + cellHeight * j
                            width: cellWidth
                            height: cellHeight
                            stroke: Color.BLACK
                            fill:
                            if (j > 5 and j < 9 or j > 15) {
                                cellFillTotal
                            } else {
                                cellFillNormal
                            }
                        } //Rectangle
                        Text {

                            x: posX + cellWidth * i + cellWidth / 2
                            y: posY + cellHeight * j + cellHeight / 4
                            textAlignment: TextAlignment.CENTER
                            textOrigin: TextOrigin.TOP 
                            content: (
                            i + j).toString()
                            
                        } //Text
                    ]//content


                } //Group
            ] //content
        }; //Group

    } //Node
} //CustomNode
