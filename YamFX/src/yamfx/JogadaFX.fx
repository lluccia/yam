/*
 * JogadaFX.fx
 *
 * Created on Dec 29, 2008, 6:45:30 PM
 */

package yamfx;

/**
 * @author Leandro Conca
 */

import javafx.scene.CustomNode;
import javafx.scene.Group;
import javafx.scene.Node;

/**
 * @author Leandro Conca
 */

public class JogadaFX extends CustomNode {

    public override function create(): Node {
        return Group {
            content: [

            ] //content
        }; //Group
    } //Node
} //CustomNode