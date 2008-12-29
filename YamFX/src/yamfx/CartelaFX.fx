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
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;



/**
 * @author Leandro Conca
 */

public class CartelaFX extends CustomNode {

    public var posX: Number = 40;
    public var posY: Number = 40;
    public var cellWidth: Number = 50;
    public var cellHeight: Number = 20;

    public override function create(): Node {
        return Group {
            content: [
                for (i in [0..3])
                    for (j in [0..18])
                        Rectangle {
                            x: posX + cellWidth*i
                            y: posY + cellHeight*j
                            width: cellWidth
                            height: cellHeight
                            fill: Color.CYAN
                            stroke: Color.BLACK
                        } //Rectangle
            ] //content
        }; //Group

    } //Node
} //CustomNode
