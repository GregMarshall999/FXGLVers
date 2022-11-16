package com.isekario.fxglvars;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.Vec2;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.addUINode;
import static com.almasb.fxgl.dsl.FXGL.getInput;
import static com.almasb.fxgl.dsl.FXGL.getUIFactoryService;
import static com.almasb.fxgl.dsl.FXGL.getWorldProperties;
import static com.almasb.fxgl.dsl.FXGL.getb;
import static com.almasb.fxgl.dsl.FXGL.getd;
import static com.almasb.fxgl.dsl.FXGL.getip;
import static com.almasb.fxgl.dsl.FXGL.inc;
import static com.almasb.fxgl.dsl.FXGL.onKeyDown;
import static com.almasb.fxgl.dsl.FXGL.set;
import static com.almasb.fxgl.dsl.FXGLForKtKt.geto;

public class Vars extends GameApplication
{
    @Override
    protected void initSettings(GameSettings gameSettings) {

    }

    @Override
    protected void initInput() {
        onKeyDown(KeyCode.Q, "boolean", () -> set("testBoolean", !getb("testBoolean")));
        onKeyDown(KeyCode.W, "int", () -> inc("lives", +2));
        onKeyDown(KeyCode.E, "double", () -> inc("testDouble", +1.5));
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("testDouble", -1.5);
        vars.put("testBoolean", true);
        vars.put("vector", new Vec2(1, 1));
        vars.put("score", 0);
        vars.put("lives", 3);
    }

    @Override
    protected void initGame() {
        getWorldProperties().<Vec2>addListener("vector", (prev, now) -> System.out.println(prev + " " + now));

        System.out.println(getd("testDouble"));
        System.out.println(getb("testBoolean"));

        Vec2 vector = geto("vector");

        System.out.println(vector.x);
        System.out.println(getWorldProperties().<Vec2>objectProperty("vector").get().y);

        set("vector", new Vec2(300, 300));
    }

    @Override
    protected void initUI() {
        Text uiScore = getUIFactoryService().newText("", Color.BLACK, 16.0);
        uiScore.textProperty().bind(getip("score").asString());
        uiScore.translateXProperty().bind(getInput().mouseXUIProperty());
        uiScore.translateYProperty().bind(getInput().mouseYUIProperty());

        addUINode(uiScore);
    }

    @Override
    protected void onUpdate(double tpf) {
        inc("score", +1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
