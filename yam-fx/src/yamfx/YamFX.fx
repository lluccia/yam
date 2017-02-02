/*
 * YamFX.fx
 *
 * Created on Dec 26, 2008, 5:57:01 PM
 */

package yamfx;

import java.lang.System;



import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.Scene;
import javafx.scene.Cursor;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author Leandro Conca
 */


Stage {
    title: "Yam"
    width: 620
    height: 460
    resizable: false

    onClose: function() {
        java.lang.System.exit(0);
    }
    visible: true

    scene: Scene {
        fill: Color.DARKCYAN
        content: [
            CartelaFX {}
        ]
    
    }
}